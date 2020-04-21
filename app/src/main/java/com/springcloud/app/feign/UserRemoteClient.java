package com.springcloud.app.feign;

import com.springcloud.app.ajax.APIResponse;
import com.springcloud.app.model.auth.User;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.codec.Encoder;
import feign.form.FormEncoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;


@FeignClient(value = "support",path = "/user",configuration = UserRemoteClient.Configuration.class)
public interface UserRemoteClient {

    @RequestMapping(value = "/registration",method = RequestMethod.POST)
    APIResponse registration(@RequestBody User user);


    @RequestMapping(value = "/login",method = RequestMethod.POST, consumes = APPLICATION_FORM_URLENCODED_VALUE)
    APIResponse login(@RequestBody Map<String,?> params);

    class Configuration {

        @Bean
        Encoder feignFormEncoder(ObjectFactory<HttpMessageConverters> converters) {
            return new SpringFormEncoder(new SpringEncoder(converters));
        }
    }

}
