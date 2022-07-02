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

package org.cadmium.nichijo.service;

import com.google.gson.Gson;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

public abstract class BasicService {

    @Autowired
    private Gson gson;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    public <E> void cache(String key, E value, long ttl) {
        redisTemplate.opsForValue()
            .set(key, gson.toJson(value), ttl, TimeUnit.MILLISECONDS);
    }

    
    public void clean(String keys) {
        Try.of(() -> redisTemplate.keys(keys))
            .andThen(r -> redisTemplate.delete(r));
    }
    

    public <E> String toJson(E obj) {
        return gson.toJson(obj);
    }
    

    public <E> E fromJson(String json, Class<E> classOf) {
        return gson.fromJson(json, classOf);
    }

}
