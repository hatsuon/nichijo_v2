package org.cadmium.nichijo.controller.admin;

import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.cadmium.nichijo.common.constant.WebPath;
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

    @Autowired
    private UserService userService;


    @GetMapping
    public String login() {
        return WebPath.ADMIN_LOGIN;
    }

    @PostMapping
    public String loginForm(LoginDto dto, HttpSession session, RedirectAttributes attributes) {
        User result = userService.find(dto);
        if (Objects.isNull(result)) {
            attributes.addFlashAttribute("message", "登陆失败, 用户不存在!");
            return "redirect:/admin";
        }
        session.setAttribute("user", result);
        return "/admin/index";
    }

    @GetMapping
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/admin";
    }

}
