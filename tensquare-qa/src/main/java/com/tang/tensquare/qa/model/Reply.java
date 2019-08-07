package com.tang.tensquare.qa.model;

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
@Table(name="tb_reply")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reply implements Serializable{
	
	private static final long serialVersionUID = 5233757657299394997L;

	@Id
	private String id;//编号
	private String problemid;//问题ID
	private String content;//回答内容
	private java.util.Date createtime;//创建日期
	private java.util.Date updatetime;//更新日期
	private String userid;//回答人ID
	private String nickname;//回答人昵称

	
}
