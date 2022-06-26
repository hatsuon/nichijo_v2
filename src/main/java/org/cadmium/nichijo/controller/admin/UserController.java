package org.cadmium.nichijo.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.cadmium.nichijo.common.model.Packet;
import org.cadmium.nichijo.entity.User;
import org.cadmium.nichijo.entity.dto.LoginDto;
import org.cadmium.nichijo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public Packet<String> login(LoginDto dto) {
        User result = userService.find(dto);

        if (Objects.isNull(result)) {
            return Packet.fail();
        }

        return Packet.ok(UUID.randomUUID().toString());
    }
}
