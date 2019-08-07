package com.tang.tensquare.search.Dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.tang.tensquare.search.model.Article;

public interface ArticleDao extends ElasticsearchRepository<Article, String> {

	Page<Article> findByTitleOrContentLike(String title, String content, Pageable pageable);
}
