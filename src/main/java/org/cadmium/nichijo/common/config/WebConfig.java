package org.cadmium.nichijo.common.config;

import org.cadmium.nichijo.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
            .addPathPatterns("/admin/**")
            .excludePathPatterns("/admin")
            .excludePathPatterns("/admin/login")
            .order(0);
    }
    
}
