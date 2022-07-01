package org.cadmium.nichijo.service.impl;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.cadmium.nichijo.entity.User;
import org.cadmium.nichijo.entity.dto.LoginDto;
import org.cadmium.nichijo.mapper.UserMapper;
import org.cadmium.nichijo.service.UserService;
import org.cadmium.nichijo.utils.Md5Utils;
import org.cadmium.nichijo.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import static org.cadmium.nichijo.common.constant.Prefix.CACHE_USER;


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

        String json = redisTemplate.opsForValue()
                .get(CACHE_USER + Md5Utils.encrypt(dto.getUsername()));
        if (StringUtils.isEmpty(json)) {
            return gson.fromJson(json, User.class);
        }

        dto.setPassword(Md5Utils.encrypt(dto.getPassword()));
        User result = userMapper.selectByUsernameAndPassword(dto);

        json = gson.toJson(result);
        redisTemplate.opsForValue()
                .set(CACHE_USER + Md5Utils.encrypt(dto.getUsername()), json, 604800000, TimeUnit.MILLISECONDS);

        return result;
    }

    @Override
    public void clean(String username) {

        String suffix = Md5Utils.encrypt(username);

        String json = redisTemplate.opsForValue()
                .get(CACHE_USER + suffix);
        if (StringUtils.isEmpty(json)) {
            redisTemplate.delete(CACHE_USER + suffix);
        }

    }

}
