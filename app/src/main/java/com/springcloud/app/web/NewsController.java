package com.springcloud.app.web;


import com.springcloud.app.ajax.APIResponse;
import com.springcloud.app.feign.TNewsRemoteClient;
import com.springcloud.app.model.news.TNews;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// swagger address
// http://localhost:8002/swagger-ui.html
@Api(value = "API - NewsController", description = "")
@RestController
@RequestMapping("/news")
public class NewsController {
    private static Logger logger = LoggerFactory.getLogger(NewsController.class);


    @Autowired
    private TNewsRemoteClient tNewsRemoteClient;

    /**
     * findNews support query one or multiple record
     * @param tNews
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "findNews",method = RequestMethod.POST)
     public APIResponse findNews(@RequestBody TNews tNews) throws  Exception{

         List<TNews> list =  tNewsRemoteClient.findNews(tNews);
         return APIResponse.success(list);

     }


}
