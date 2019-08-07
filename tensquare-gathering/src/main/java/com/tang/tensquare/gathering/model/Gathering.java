package com.tang.tensquare.gathering.model;

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
@Table(name="tb_gathering")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gathering implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8226248237725293977L;



	@Id
	private String id;//编号


	
	private String name;//活动名称
	private String summary;//大会简介
	private String detail;//详细说明
	private String sponsor;//主办方
	private String image;//活动图片
	private java.util.Date starttime;//开始时间
	private java.util.Date endtime;//截止时间
	private String address;//举办地点
	private java.util.Date enrolltime;//报名截止
	private String state;//是否可见
	private String city;//城市


	
}
