package com.tang.tensquare.article.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.tang.tensquare.article.model.Article;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ArticleDao extends JpaRepository<Article,String>,JpaSpecificationExecutor<Article>{

	@Modifying
	@Query(value="update tb_article set state = 1 where id = ?", nativeQuery = true)
	void auditArticle(String articleId);
	
	@Modifying
	@Query(value="update tb_article set thumbup = thumbup + 1 where id = ?", nativeQuery = true)
	void thumbupArticle(String articleId);
	
}
