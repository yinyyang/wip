package com.springcloud.zuul.filter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.springcloud.zuul.common.Result;
import com.springcloud.zuul.config.DataFilterConfig;
import com.springcloud.zuul.util.JwtUtil;
import com.springcloud.zuul.util.PathUtil;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;


import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;

@Component
public class LoginAddJwtPostFilter extends ZuulFilter {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private DataFilterConfig dataFilterConfig;

    /**
     * pre：
     * routing：
     * post：
     * error：
     *
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    /**
     * filterOrder：
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER - 2;
    }

    /**
     * shouldFilter: login path trigger
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        //path match， execute current filter
        RequestContext ctx = RequestContext.getCurrentContext();
        for (String pathPattern : dataFilterConfig.getUserLoginPath()) {
            if (PathUtil.isPathMatch(pathPattern, ctx.getRequest().getRequestURI())) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     *
     * @return
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        try {
            InputStream stream = ctx.getResponseDataStream();
            String body = StreamUtils.copyToString(stream, StandardCharsets.UTF_8);
            Gson gson = new Gson();
            HashMap result=gson.fromJson(body,HashMap.class);
            //result.getCode() == 0  means that  login success
            if (result.get("code") == "0") {
                HashMap<String, Object> jwtClaims = new HashMap<String, Object>() {{
                    put("userId", result.get("data"));
                }};
                Date expDate = DateTime.now().plusDays(7).toDate(); //expired days: 7
                String token = jwtUtil.createJWT(expDate, jwtClaims);
                //body json add token
                result.put("token", token);
                body = objectMapper.writeValueAsString(result);
                ctx.setResponseBody(body);
                ctx.addZuulResponseHeader("token", token);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}