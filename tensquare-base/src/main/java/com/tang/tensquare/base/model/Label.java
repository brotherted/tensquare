package com.tang.tensquare.base.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_label")
public class Label {
	
	@Id
	private String id;
	private String labelname;
	private Integer count;
	private String state;
	private String recommend;
	private Integer fans;
	
}
