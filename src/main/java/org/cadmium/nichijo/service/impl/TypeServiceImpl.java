package org.cadmium.nichijo.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.cadmium.nichijo.entity.Type;
import org.cadmium.nichijo.mapper.TypeMapper;
import org.cadmium.nichijo.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

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
            .get();
        return result;
    }


    @Override
    public int delete(Integer id) {

        Integer result = Try.of(() -> typeMapper.deleteByPrimary(id))
            .andThenTry(() -> redisTemplate.opsForValue().get(CACHE_TYPE + id))
            .andThen(ok -> redisTemplate.delete(CACHE_TYPE + id))
            .get();
        return result;
    }


    @Override
    public int update(Type type) {

        Integer result = Try.of(() -> typeMapper.updateByPrimary(type))
            .andThenTry(() -> redisTemplate.opsForValue()
                .get(CACHE_TYPE + type.getId()))
            .andThen(ok -> redisTemplate.delete(CACHE_TYPE + type.getId()))
            .andThen(this::clean).get();

        return result;
    }


    @Override
    public Type get(Integer id) {

        String json = redisTemplate.opsForValue()
            .get(CACHE_TYPE + id);
        if (StringUtils.hasLength(json)) {
            log.info("Data from redis cache.");
            return gson.fromJson(json, Type.class);
        }

        Type result = Try.of(() -> typeMapper.selectByPrimary(id))
            .andThen(ok -> cache(CACHE_TYPE + id, ok, TYPE_TOTAL))
            .get();

        return result;
    }


    @Override
    public List<Type> typePage(Integer pageNum) {

        String json = redisTemplate.opsForValue()
            .get(CACHE_TYPE_PAGE + pageNum);
        if (StringUtils.hasLength(json)) {
            log.info("Data from redis cache.");
            return gson.fromJson(json, new TypeToken<List<Type>>() {}.getType());
        }

        List<Type> result = Try.of(() -> typeMapper.page((pageNum - 1) * PAGE_SIZE, PAGE_SIZE))
            .andThen(r -> cache(CACHE_TYPE_PAGE + pageNum, r, TYPE_TOTAL))
            .get();

        return result;
    }


    /**
     * 清除分页缓存在新增，删除，更新时调用
     */
    private void clean() {
        Try.of(() -> redisTemplate.keys(CACHE_TYPE_PAGE + "*"))
            .andThen(r -> redisTemplate.delete(r));
    }

    private <E> void cache(String key, E value, long ttl) {
        redisTemplate.opsForValue()
            .set(key, this.gson.toJson(value), ttl, TimeUnit.MILLISECONDS);
    }

}
