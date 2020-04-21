package com.springcloud.admin.web;


import com.springcloud.admin.ajax.APIResponse;
import com.springcloud.admin.feign.TNewsRemoteClient;
import com.springcloud.admin.model.news.TNews;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// swagger address
// http://localhost:8005/swagger-ui.html
@Api(value = "API - NewsController", description = "")
@RestController
@RequestMapping("/news")
public class NewsController {
    private static Logger logger = LoggerFactory.getLogger(NewsController.class);


    @Autowired
    private TNewsRemoteClient tNewsRemoteClient;

    @RequestMapping(value = "findNews",method = RequestMethod.POST)
     public APIResponse findNews(@RequestBody TNews tNews) throws  Exception{

         List<TNews> list =  tNewsRemoteClient.findNews(tNews);
         return APIResponse.success(list);

     }

    @RequestMapping(value = "addNews",method = RequestMethod.POST)
    public APIResponse addNews(@RequestBody TNews tNews) throws  Exception{
         tNewsRemoteClient.addNews(tNews);
         return APIResponse.success();
    }

    @RequestMapping(value = "modifyNews",method = RequestMethod.POST)
    public APIResponse modifyNews(@RequestBody TNews tNews) throws  Exception{
        int result =tNewsRemoteClient.modifyNews(tNews);
        return  APIResponse.success(result);
    }

    @RequestMapping(value = "deleteNews/{id}",method = RequestMethod.DELETE)
    public APIResponse deleteNews(@PathVariable("id") Integer id) throws  Exception{
        int result =tNewsRemoteClient.deleteNews(id);
        return  APIResponse.success(result);
    }

}
