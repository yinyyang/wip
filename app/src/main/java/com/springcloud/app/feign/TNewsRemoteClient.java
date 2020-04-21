package com.springcloud.app.feign;

import com.springcloud.app.model.news.TNews;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "content",path = "/t-news")
public interface TNewsRemoteClient {

    @RequestMapping(value = "findNews",method = RequestMethod.POST)
    public List<TNews> findNews(@RequestBody TNews tNews);

}
