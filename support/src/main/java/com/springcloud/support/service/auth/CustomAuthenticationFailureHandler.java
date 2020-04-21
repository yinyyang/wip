package com.springcloud.support.service.auth;

import com.google.gson.Gson;
import com.springcloud.support.ajax.APIResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class CustomAuthenticationFailureHandler  implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setStatus(401);
        PrintWriter writer = httpServletResponse.getWriter();
        Gson gson = new Gson();
        writer.write(gson.toJson(APIResponse.fail("login fail")));
        writer.flush();
        writer.close();

    }
}