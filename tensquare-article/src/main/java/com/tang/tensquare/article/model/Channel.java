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
@Table(name="tb_channel")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Channel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4003939276009725210L;



	@Id
	private String id;//ID


	
	private String name;//频道名称
	private String state;//状态

	
}
