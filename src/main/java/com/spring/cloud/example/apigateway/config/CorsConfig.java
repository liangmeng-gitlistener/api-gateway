package com.spring.cloud.example.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * 跨域配置
 * CrossOriginResourceSharing
 */
@Component
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter(){
        final UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfig = new CorsConfiguration();

        //支持cookie跨域
        corsConfig.setAllowCredentials(true);
        //支持哪些原始域，http://www.a.com
        corsConfig.setAllowedOrigins(Arrays.asList("*"));
        //允许的头
        corsConfig.setAllowedHeaders(Arrays.asList("*"));
        //允许的方法，GET、POST
        corsConfig.setAllowedMethods(Arrays.asList("*"));
        //缓存时间，时间间隔内不会再校验跨域，单位：秒
        corsConfig.setMaxAge(300l);

        configSource.registerCorsConfiguration("/**",corsConfig);
        return new CorsFilter(configSource);
    }
}
