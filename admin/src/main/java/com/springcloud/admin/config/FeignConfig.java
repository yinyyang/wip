package com.springcloud.admin.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients(basePackages = "com.springcloud.admin.feign")
@Configuration
public class FeignConfig {

}
