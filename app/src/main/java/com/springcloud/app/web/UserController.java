package com.springcloud.app.web;


import com.google.common.collect.Maps;
import com.springcloud.app.ajax.APIResponse;
import com.springcloud.app.feign.UserRemoteClient;
import com.springcloud.app.model.auth.User;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// swagger address
// http://localhost:8002/swagger-ui.html
@Api(value = "API - NewsController", description = "")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRemoteClient userRemoteClient;


    @RequestMapping(value = "register",method = RequestMethod.POST)
    public APIResponse register(@RequestBody User user) throws  Exception{
        return userRemoteClient.registration(user);

    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public APIResponse login(@RequestBody User user) throws  Exception{
        HashMap<String,String> params= Maps.newHashMap();
        params.put("username",user.getUsername());
        params.put("password",user.getPassword());
        return userRemoteClient.login(params);

    }


}
