package com.lee.online_store.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.lee.online_store.auth.interceptor.TokenInterceptor;
import com.lee.online_store.common.token.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * WebMvc 自动装配配置
 *
 * @author Ray
 * @since 2020/10/16
 */
@Configuration
@Slf4j
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final TokenService tokenService;

    /**
     * 配置消息转换器
     *
     * @param converters 消息转换器列表
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();

        JavaTimeModule javaTimeModule = new JavaTimeModule();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        javaTimeModule.addSerializer(LocalDateTime.class,new LocalDateTimeSerializer(dateTimeFormatter));
        javaTimeModule.addDeserializer(LocalDateTime.class,new LocalDateTimeDeserializer(dateTimeFormatter));
        objectMapper.registerModule(javaTimeModule);



        // 处理 Long 和 BigInteger 类型，避免前端精度丢失问题
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(BigInteger.class, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);


        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);


        jackson2HttpMessageConverter.setObjectMapper(objectMapper);
        converters.add(1, jackson2HttpMessageConverter);
    }

    //拦截器配置
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenInterceptor(tokenService))
                .addPathPatterns("/api/v1/**")
                .excludePathPatterns("/api/v1/auth/login", "/api/v1/auth/register","api/v1/auth/logout");
    }

}
