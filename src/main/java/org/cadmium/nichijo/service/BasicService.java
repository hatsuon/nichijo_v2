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
