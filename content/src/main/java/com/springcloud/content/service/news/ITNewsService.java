package com.springcloud.content.service.news;

import com.springcloud.content.model.news.TNews;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface ITNewsService extends IService<TNews> {

    List<TNews> findNews(TNews tNews);
    void addNews(TNews tNews);
    int modifyNews(TNews tNews);
    int deleteNews(Integer id);


}
