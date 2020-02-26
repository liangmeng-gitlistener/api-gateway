package com.spring.cloud.example.apigateway.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

public interface Constants {

    interface CookieConstant{
        String TOKEN = "token";
        String OPENID = "openid";
        /**
         * 过期时间（单位：秒）
         */
        Integer expire = 7200;
    }

    interface RedisConstant {

        String TOKEN_TEMPLATE = "token_%s";
    }
}
