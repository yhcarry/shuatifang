package com.jiecheng.subject.application.interceptor;

/**
 * @Author: CYH
 * @Date: 2024/12/29 15:12
 */

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {

    @Bean
    public RequestInterceptor requestInterceptor(){
        return new FeignRequestInterceptor();
    }

}
