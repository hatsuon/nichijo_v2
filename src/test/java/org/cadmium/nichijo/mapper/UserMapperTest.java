package org.cadmium.nichijo.mapper;

import org.cadmium.nichijo.entity.User;
import org.cadmium.nichijo.entity.dto.LoginDto;
import org.cadmium.nichijo.utils.Md5Utils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;


    @Test
    void testSelectByUsernameAndPassword() {

        LoginDto dto = new LoginDto();
        dto.setUsername("root");
        dto.setPassword(Md5Utils.encrypt("0000"));


        User result = userMapper.selectByUsernameAndPassword(dto);
        System.out.println(result);
    }

}