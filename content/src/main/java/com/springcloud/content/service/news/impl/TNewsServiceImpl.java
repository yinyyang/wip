package com.springcloud.content.service.news.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springcloud.content.elasticrepo.NewsRepo;
import com.springcloud.content.model.news.TNews;
import com.springcloud.content.mapper.news.TNewsMapper;
import com.springcloud.content.service.news.ITNewsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.hibernate.validator.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service
public class TNewsServiceImpl extends ServiceImpl<TNewsMapper, TNews> implements ITNewsService {

    private static Logger logger = LoggerFactory.getLogger(TNewsServiceImpl.class);


    @Autowired
    private NewsRepo newsRepo;
    @Resource
    private TNewsMapper dao;

    @Override
    public List<TNews> findNews(TNews tNews) {


            if(tNews.getTitle()!=null || tNews.getContent()!=null) {
                List<TNews> list = newsRepo.findByTitleOrContent(tNews.getTitle(), tNews.getContent());

                return  list;
            }


        return dao.findNews(tNews);
    }

    @Override
    public void addNews(TNews tNews) {


         dao.insert(tNews);
         newsRepo.save(tNews);

    }

    @Override
    public int modifyNews(TNews tNews) {
        return modifyNews(tNews);
    }

    @Override
    public int deleteNews(Integer id) {
        newsRepo.deleteById((long)id);
        return dao.deleteNews(id);
    }


}
