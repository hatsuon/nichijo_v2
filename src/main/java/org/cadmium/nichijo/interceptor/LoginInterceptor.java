package org.cadmium.nichijo.interceptor;

import org.cadmium.nichijo.entity.User;
import org.cadmium.nichijo.utils.UserHandler;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class LoginInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    
        User loginUser = (User) request.getSession().getAttribute("user");
        if (Objects.isNull(loginUser)) {
            response.sendRedirect("/admin");
            return false;
        }
    
        UserHandler.set(loginUser);
        return true;
    }
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserHandler.remove();
    }
}
