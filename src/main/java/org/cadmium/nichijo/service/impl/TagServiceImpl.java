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
import org.cadmium.nichijo.entity.Tag;
import org.cadmium.nichijo.mapper.TagMapper;
import org.cadmium.nichijo.service.TagService;
import org.cadmium.nichijo.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.cadmium.nichijo.common.constant.CacheTTL.TAG_TOTAL;
import static org.cadmium.nichijo.common.constant.Prefix.CACHE_TAG;
import static org.cadmium.nichijo.common.constant.Prefix.CACHE_TAG_PAGE;

@Slf4j
@Service
public class TagServiceImpl implements TagService {
    
    final private Integer PAGE_SIZE = 4;
    
    @Autowired
    private Gson gson;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    
    
    @Override
    public boolean isExist(String tagName) {
        return tagMapper.selectByName(tagName) != null;
    }
    
    @Override
    public Tag get(Integer id) {
    
        String json = redisTemplate.opsForValue().get(CACHE_TAG + id);
        if (!StringUtils.isEmpty(json)) {
            log.info("Tag from cache");
            return gson.fromJson(json, Tag.class);
        }
    
        Tag result = tagMapper.selectByPrimary(id);
        json = gson.toJson(result);
        
        redisTemplate.opsForValue()
            .set(CACHE_TAG + id, json, TAG_TOTAL, TimeUnit.MILLISECONDS);
        
        return result;
    }
    
    @Override
    public int save(Tag tag) {
        Integer result = Try.of(() -> tagMapper.insertOne(tag))
            .andThen(this::clean)
            .get();
        return result;
    }
    
    
    @Override
    public int update(Tag tag) {
        Integer result = Try.of(() -> tagMapper.updateByPrimary(tag))
            .andThen(this::clean)
            .andThen(r -> redisTemplate.opsForValue().get(CACHE_TAG + tag.getId()))
            .andThen(r -> redisTemplate.delete(CACHE_TAG + tag.getId()))
            .get();
        return result;
    }
    
    
    @Override
    public int delete(Integer id) {
        Integer result = Try.of(() -> tagMapper.deleteByPrimary(id))
            .andThen(this::clean)
            .andThen(r -> redisTemplate.opsForValue().get(CACHE_TAG + id))
            .andThen(r -> redisTemplate.delete(CACHE_TAG + id))
            .get();
        return result;
    }
    
    @Override
    public PageInfo<Tag> tagPage(Integer pageNum) {
    
        String json = redisTemplate.opsForValue().get(CACHE_TAG_PAGE + pageNum);
        if (!StringUtils.isEmpty(json)) {
            log.info("Tag page from cache.");
            return gson.fromJson(json, new TypeToken<PageInfo<Tag>>(){}.getType());
        }
        
        PageHelper.startPage(pageNum, PAGE_SIZE);
        PageInfo<Tag> result = new PageInfo<>(tagMapper.list());
    
        json = gson.toJson(result);
        redisTemplate.opsForValue()
            .set(CACHE_TAG_PAGE + pageNum, json, TAG_TOTAL, TimeUnit.MILLISECONDS);
        
        return result;
    }
    
    
    private boolean clean() {
        Set<String> result = Try.of(() -> redisTemplate.keys(CACHE_TAG + "*"))
            .andThen(ok -> redisTemplate.delete(ok))
            .get();
        return result != null;
    }
    
}
