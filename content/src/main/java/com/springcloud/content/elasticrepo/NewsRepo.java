package com.springcloud.content.elasticrepo;


import com.springcloud.content.model.news.TNews;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface NewsRepo extends ElasticsearchRepository<TNews,Long>, PagingAndSortingRepository<TNews,Long> {

    List<TNews> findByTitleOrContent(String title, String content);

}
