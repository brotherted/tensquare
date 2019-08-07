package com.tang.tensquare.article.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
/**
 * 实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="tb_column")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Column implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3520941572218804713L;



	@Id
	private String id;//ID


	
	private String name;//专栏名称
	private String summary;//专栏简介
	private String userid;//用户ID
	private java.util.Date createtime;//申请日期
	private java.util.Date checktime;//审核日期
	private String state;//状态

	
}
