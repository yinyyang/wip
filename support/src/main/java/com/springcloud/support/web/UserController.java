package com.springcloud.support.web;


import com.springcloud.support.ajax.APIResponse;
import com.springcloud.support.model.auth.User;
import com.springcloud.support.service.user.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Api(value = "API - UserController", description = "")
@RestController
@RequestMapping("/user")
//http://localhost:8003/swagger-ui.html
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/registration",method = RequestMethod.POST)
    public APIResponse registration(@RequestBody  User user) {


        userService.save(user);

        return APIResponse.success();
    }
//    @RequestMapping(value = "/login",method = RequestMethod.POST)
//    public APIResponse login(@RequestBody  User user) {
//
//
//
//        return APIResponse.success();
//    }

}