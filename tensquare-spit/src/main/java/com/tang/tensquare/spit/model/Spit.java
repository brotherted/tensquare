package com.tang.tensquare.spit.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Spit implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1138656208640610843L;
	
	@Id
	private String _id;
	private String content;
	private Date publishtime;
	private String userid;
	private String nickname;
	private Integer visits;
	private Integer thumbup;
	private Integer share;
	private Integer comment;
	private String state;
	private String parentid;
	
}
