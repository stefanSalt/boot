package com.lee.reservation.config;

import com.lee.reservation.common.filter.TokenFilter;
import com.lee.reservation.common.token.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;  
import org.springframework.context.annotation.Configuration;  
  
@Configuration
@RequiredArgsConstructor
public class FilterConfig {
    private  final TokenService tokenService;

  
    @Bean
    public FilterRegistrationBean<TokenFilter> timingFilterRegistration() {
        FilterRegistrationBean<TokenFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new TokenFilter(tokenService));
        //过滤器排除
//        registration.addInitParameter("excludeUrls", "/login");
        registration.addUrlPatterns("/api/v1/*");
        registration.setOrder(-1);
        return registration;
    }
}
