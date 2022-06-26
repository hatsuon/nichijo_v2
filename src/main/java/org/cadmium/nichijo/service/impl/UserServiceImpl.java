package org.cadmium.nichijo.service.impl;

import com.google.gson.Gson;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.cadmium.nichijo.common.constant.CacheTTL;
import org.cadmium.nichijo.entity.User;
import org.cadmium.nichijo.entity.dto.LoginDto;
import org.cadmium.nichijo.mapper.UserMapper;
import org.cadmium.nichijo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.cadmium.nichijo.common.constant.CacheTTL.TYPE_TOTAL;
import static org.cadmium.nichijo.common.constant.Prefix.CACHE_USER;
import static org.cadmium.nichijo.common.constant.Prefix.LOGIN_TOKEN;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private Gson gson;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @Override
    public User find(LoginDto dto) {

        User result = userMapper.selectByUsernameAndPassword(dto);
        if (Objects.isNull(result)) {
            return null;
        }

        redisTemplate.opsForValue()
                .set(CACHE_USER + dto.getUsername(), gson.toJson(result), TYPE_TOTAL, TimeUnit.MILLISECONDS);
        return result;
    }

}
