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

package org.cadmium.nichijo.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.cadmium.nichijo.entity.User;
import org.cadmium.nichijo.entity.dto.LoginDto;
import org.cadmium.nichijo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/admin")
public class LoginController {
    
    private final String REDIRECT_ADMIN = "redirect:/admin";
    private final String ADMIN_INDEX = "/admin/index";
    private final String ADMIN_LOGIN = "/admin/login";
    
    @Autowired
    private UserService userService;


    @GetMapping
    public String login(HttpSession session) {
        if (session.getAttribute("user") != null) {
            return ADMIN_INDEX;
        }
        return ADMIN_LOGIN;
    }

    
    @PostMapping
    public String loginForm(LoginDto dto, HttpSession session, RedirectAttributes attributes) {
        User result = userService.find(dto);
        if (Objects.isNull(result)) {
            attributes.addFlashAttribute("message", "登陆失败, 用户不存在!");
            return REDIRECT_ADMIN;
        }
        session.setAttribute("user", result);
        return ADMIN_INDEX;
    }

    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return REDIRECT_ADMIN;
    }

}
