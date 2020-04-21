package com.springcloud.app.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;



@EnableFeignClients(basePackages = "com.springcloud.app.feign")
@Configuration
public class FeignConfig {


}
