package com.springcloud.support.service.auth;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.springcloud.support.ajax.APIResponse;
import com.springcloud.support.model.auth.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        httpServletResponse.setHeader("Content-Type", "application/json;charset=utf-8");
        httpServletResponse.setStatus(200);
        PrintWriter writer = httpServletResponse.getWriter();
        Gson gson = new Gson();
        writer.write(gson.toJson(APIResponse.success(authentication.getPrincipal())));
        writer.flush();
        writer.close();

    }


}