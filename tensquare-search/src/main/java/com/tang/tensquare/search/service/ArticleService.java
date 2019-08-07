package com.tang.tensquare.search.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tang.tensquare.common.util.IdWorker;
import com.tang.tensquare.search.Dao.ArticleDao;
import com.tang.tensquare.search.model.Article;

@Service
public class ArticleService {

	@Autowired
	private ArticleDao articleDao;
	
	@Autowired
	private IdWorker idWorker;
	
	public void save(Article article) {
		article.setId(idWorker.nextId() + "");
		articleDao.save(article);
	}
	
	public Page<Article> search(String key, int page, int size){
		Pageable pageable = PageRequest.of(page - 1, size);
		return articleDao.findByTitleOrContentLike(key, key, pageable );
	}

	public List<Article> getAll() {
		List<Article> list = new ArrayList<Article>();
		articleDao.findAll().forEach(article->list.add(article));
		return list;
	}
	
}
