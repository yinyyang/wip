package com.springcloud.content.web.news;


import com.springcloud.content.model.news.TNews;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.springcloud.content.service.news.ITNewsService;

import java.util.List;
import java.util.Map;


// swagger address
// http://localhost:8005/swagger-ui.html
@Api(value = "API - TNewsController", description = "")
@RestController
@RequestMapping("/t-news")
public class TNewsController {
    private static Logger logger = LoggerFactory.getLogger(TNewsController.class);

	@Autowired
    private ITNewsService service;

    @RequestMapping(value = "findNews",method = RequestMethod.POST)
     public List<TNews> findNews(@RequestBody TNews tNews){
         return service.findNews(tNews);
     }

    @RequestMapping(value = "addNews",method = RequestMethod.POST)
    public void addNews(@RequestBody TNews tNews){
         service.addNews(tNews);
    }

    @RequestMapping(value = "modifyNews",method = RequestMethod.POST)
    public int modifyNews(@RequestBody TNews tNews){
        return service.modifyNews(tNews);
    }

    @RequestMapping(value = "deleteNews/{id}",method = RequestMethod.DELETE)
    public int deleteNews(@PathVariable("id") Integer id){
        return service.deleteNews(id);
    }

}
