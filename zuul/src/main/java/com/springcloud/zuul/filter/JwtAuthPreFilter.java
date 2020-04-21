package com.springcloud.zuul.filter;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.springcloud.zuul.common.Result;
import com.springcloud.zuul.config.DataFilterConfig;
import com.springcloud.zuul.util.JwtUtil;
import com.springcloud.zuul.util.PathUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

@Component
public class JwtAuthPreFilter extends ZuulFilter {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private DataFilterConfig dataFilterConfig;

    /**
     * pre
     * routing
     * post
     * error
     *
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }


    @Override
    public int filterOrder() {
        return 2;
    }

    /**
     * shouldFilter： data browser trigger
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        for (String pathPattern : dataFilterConfig.getAuthPath()) {
            if (PathUtil.isPathMatch(pathPattern, ctx.getRequest().getRequestURI())) {
                return true;
            }
        }
        return false;
    }


    /**
     * validate token when execute filter
     *
     * @return
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String token = request.getHeader("token");
        Claims claims;
        try {
            claims = jwtUtil.parseJWT(token);
            ctx.setSendZuulResponse(true);
            ctx.addZuulRequestHeader("userId", claims.get("userId").toString());
        } catch (ExpiredJwtException expiredJwtEx) {
            //expired
            ctx.setSendZuulResponse(false);
            responseError(ctx, "402", "token expired");
        } catch (Exception ex) {
            //failure
            ctx.setSendZuulResponse(false);
            responseError(ctx, "401", "invalid token");
        }

        return null;
    }


    /**
     *
     */
    private void responseError(RequestContext ctx, String code, String message) {
        HttpServletResponse response = ctx.getResponse();
        Result errResult = new Result();
        errResult.setCode(code);
        errResult.setMessage(message);
        ctx.setResponseBody(toJsonString(errResult));
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType("application/json;charset=utf-8");
    }

    private String toJsonString(Object o) {
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}