package com.tang.tensquare.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tang.tensquare.common.controller.BaseController;
import com.tang.tensquare.common.entity.Result;
import com.tang.tensquare.common.entity.Result.PageResult;
import com.tang.tensquare.common.entity.StatusCode;
import com.tang.tensquare.search.model.Article;
import com.tang.tensquare.search.service.ArticleService;

@RestController
@RequestMapping("article")
public class ArticleController extends BaseController {

	@Autowired
	private ArticleService articleService;
	
	@GetMapping
	public Result getAll( ) {
		return new Result(true, StatusCode.OK, "success", articleService.getAll());
	}
	
	@PostMapping
	public Result save(@RequestBody Article article) {
		articleService.save(article);
		return new Result(true, StatusCode.OK, "success", null);
	}
	
	@GetMapping("/search/{key}/{page}/{size}")
	public Result search(@PathVariable String key, @PathVariable int page, @PathVariable int size) {
		Page<Article> searchPage = articleService.search(key, page, size);
		return new Result(true, StatusCode.OK, "success", new PageResult<>(searchPage.getTotalElements(), searchPage.getContent()));
	}
}
