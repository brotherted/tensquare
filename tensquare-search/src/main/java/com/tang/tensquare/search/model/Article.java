package com.tang.tensquare.search.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import lombok.Data;

@Data
@Document(indexName="tensquare", type="article")
public class Article {

	@Id
	private String id;
	@Field(index=true, analyzer="ik_max_word", searchAnalyzer="ik_max_word")
	private String title;
	@Field(index=true, analyzer="ik_max_word", searchAnalyzer="ik_max_word")
	private String content;
	private String state;
}
