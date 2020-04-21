package com.springcloud.content.mapper.news;

import com.springcloud.content.model.news.TNews;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TNewsMapper extends BaseMapper<TNews> {

    List<TNews> findNews(TNews tNews);
    int addNews(TNews tNews);
    int modifyNews(TNews tNews);
    int deleteNews(Integer id);

}
