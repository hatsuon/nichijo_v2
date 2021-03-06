/*
 * Copyright (c) 2022
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.cadmium.nichijo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.cadmium.nichijo.entity.Article;
import org.cadmium.nichijo.entity.Type;
import org.cadmium.nichijo.mapper.TypeMapper;
import org.cadmium.nichijo.service.TypeService;
import org.cadmium.nichijo.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.cadmium.nichijo.common.constant.CacheTTL.TTL;
import static org.cadmium.nichijo.common.constant.CacheTTL.TYPE_TOTAL;
import static org.cadmium.nichijo.common.constant.Prefix.*;

@Slf4j
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private Gson gson;
    @Autowired
    private TypeMapper typeMapper;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @Override
    public int save(Type type) {
        Integer result = Try.of(() -> typeMapper.insertOne(type.getName()))
            .andThen(this::clean)
            .andThen(r -> log.debug("Clean Type Page Cache!"))
            .get();

        assert result != null;
        return result;
    }


    @Override
    public int delete(Integer id) {

        Integer result = Try.of(() -> typeMapper.deleteByPrimary(id))
            .andThenTry(() -> redisTemplate.opsForValue().get(CACHE_TYPE + id))
            .andThen(ok -> redisTemplate.delete(CACHE_TYPE + id))
            .andThen(this::clean)
            .andThen(ok -> log.info("Clean type cache {} ", id))
            .get();

        assert result != null;
        return result;
    }


    @Override
    public int update(Type type) {

        Integer result = Try.of(() -> typeMapper.updateByPrimary(type))
            .andThenTry(() -> redisTemplate.opsForValue()
            .get(CACHE_TYPE + type.getId()))
            .andThen(ok -> redisTemplate.delete(CACHE_TYPE + type.getId()))
            .andThen(this::clean).get();

        assert result != null;
        return result;
    }


    @Override
    public Type get(Integer id) {

        String json = redisTemplate.opsForValue()
            .get(CACHE_TYPE + id);
        if (!StringUtils.isEmpty(json)) {
            log.info("Data from redis cache {} ", new Date());
            return gson.fromJson(json, Type.class);
        }

        Type result = Try.of(() -> typeMapper.selectByPrimary(id))
            .andThen(ok -> cache(CACHE_TYPE + id, ok, TYPE_TOTAL))
            .get();

        assert result != null;
        return result;
    }


    @Override
    public PageInfo<Type> typePage(Integer pageNum) {

        String json = redisTemplate.opsForValue().get(CACHE_TYPE_PAGE + pageNum);
        if (!StringUtils.isEmpty(json)) {
            log.info("Type page from cache {} ", new Date());
            return gson.fromJson(json, new TypeToken<PageInfo<Type>>() {}.getType() );
        }

        PageHelper.startPage(pageNum, PAGE_SIZE);
        PageInfo<Type> result = new PageInfo<>(typeMapper.list());

        json = gson.toJson(result);
        redisTemplate.opsForValue()
                .set(CACHE_TYPE_PAGE + pageNum, json, TYPE_TOTAL, TimeUnit.MILLISECONDS);

        return result;
    }
    
    @Override
    public PageInfo<Type> typePage(Integer pageNum, Article article) {
        return null;
    }
    
    
    @Override
    public boolean isExist(String typeName) {
        return typeMapper.selectByName(typeName) != null;
    }
    
    
    @Override
    public List<Type> list() {
    
        String json = redisTemplate.opsForValue().get(CACHE_TYPE_LIST);
        if (!StringUtils.isEmpty(json)) {
            log.info("Type list from cache {}", new Date());
            return gson.fromJson(json, new TypeToken<List<Type>>() {}.getType());
        }
        
        List<Type> result = typeMapper.list();
        json = gson.toJson(result);
        redisTemplate.opsForValue()
            .set(CACHE_TYPE_LIST, json, TTL, TimeUnit.MILLISECONDS);
        log.info("Type list cache to redis {} ", new Date());
        
        return result;
    }
    
    
    private void clean() {
        Try.of(() -> redisTemplate.keys(CACHE_TYPE_PAGE + "*"))
            .andThen(r -> redisTemplate.delete(r));
    }

    
    private <E> void cache(String key, E value, long ttl) {
        redisTemplate.opsForValue()
            .set(key, this.gson.toJson(value), ttl, TimeUnit.MILLISECONDS);
    }

}
