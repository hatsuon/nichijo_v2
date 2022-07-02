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
import org.cadmium.nichijo.mapper.ArticleMapper;
import org.cadmium.nichijo.service.ArticleService;
import org.cadmium.nichijo.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static org.cadmium.nichijo.common.constant.CacheTTL.TTL;
import static org.cadmium.nichijo.common.constant.Prefix.*;

@Slf4j
@Service
public class ArticleServiceImpl implements ArticleService {
    
    @Autowired
    private Gson gson;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    
    
    @Override
    public Article get(Integer id) {
        String json = redisTemplate.opsForValue()
            .get(CACHE_ARTICLE + id);
        if (!StringUtils.isEmpty(json)) {
            log.info("Article from cache {} ", new Date());
            return gson.fromJson(json, Article.class);
        }
        
        Article result = articleMapper.selectByPrimary(id);
        json = gson.toJson(result);
        
        redisTemplate.opsForValue()
            .set(CACHE_ARTICLE + id, json, TTL, TimeUnit.MILLISECONDS);
        log.info("Article cache to redis {}", new Date());
        return result;
    }
    
    @Override
    public int save(Article article) {
        Integer result = Try.of(() -> articleMapper.insertOne(article))
            .andThen(this::clean)
            .andThen(r -> log.info("Clean article page cache {}", new Date())).get();
        return result;
    }
    
    
    @Override
    public int update(Article article) {
        Try.of(() -> redisTemplate.opsForValue().get(CACHE_ARTICLE + article.getId()))
            .andThen(r -> redisTemplate.delete(CACHE_ARTICLE + article.getId()))
            .andThen(r -> log.info("Clean all article cache {} ", new Date()))
            .andThen(this::clean);
        
        int result = articleMapper.updateOne(article);
        return result;
    }
    
    
    @Override
    public int delete(Integer id) {
        Try.of(() -> redisTemplate.opsForValue().get(CACHE_ARTICLE + id))
            .andThen(r -> redisTemplate.delete(CACHE_ARTICLE + id))
            .andThen(r -> log.info("Clean all article cache {} ", new Date()))
            .andThen(this::clean);
    
        int result  = articleMapper.deleteByPrimary(id);
        return result;
    }
    
    
    @Override
    public PageInfo<Article> articlePage(Integer pageNum) {
    
        String json = redisTemplate.opsForValue().get(CACHE_ARTICLE_PAGE + pageNum);
        if (!StringUtils.isEmpty(json)) {
            log.info("Article page from cache {} ", new Date());
            return gson.fromJson(json, new TypeToken<PageInfo<Article>>(){}.getType());
        }
    
        PageHelper.startPage(pageNum, PAGE_SIZE);
        PageInfo<Article> result = new PageInfo<>(articleMapper.list());
        json = gson.toJson(result);
    
        log.info("Article page cache to redis {}", new Date());
        redisTemplate.opsForValue()
            .set(CACHE_TAG_PAGE + pageNum, json, TTL, TimeUnit.MILLISECONDS);
        return result;
    }
    
    
    private void clean() {
        Try.of(() -> redisTemplate.keys(CACHE_ARTICLE_PAGE + "*"))
            .andThen(r -> redisTemplate.delete(r));
    }
    
}
