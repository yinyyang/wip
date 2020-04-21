package com.springcloud.admin.feign;

import com.springcloud.admin.model.news.TNews;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "content",path = "/t-news")
public interface TNewsRemoteClient {

    @RequestMapping(value = "findNews",method = RequestMethod.POST)
    public List<TNews> findNews(@RequestBody TNews tNews);

    @RequestMapping(value = "addNews",method = RequestMethod.POST)
    public void addNews(@RequestBody TNews tNews);

    @RequestMapping(value = "modifyNews",method = RequestMethod.POST)
    public int modifyNews(@RequestBody TNews tNews);

    @RequestMapping(value = "deleteNews/{id}",method = RequestMethod.DELETE)
    public int deleteNews(@PathVariable("id") Integer id);
}
