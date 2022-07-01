package org.cadmium.nichijo.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class LoginInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    
        Object loginUser = request.getSession().getAttribute("user");
        if (Objects.isNull(loginUser)) {
            response.sendRedirect("/admin");
            return false;
        }
        return true;
    }
    
    
}
