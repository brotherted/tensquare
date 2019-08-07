package com.tang.tensquare.friend.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_friend")
@IdClass(Friend.class)
public class Friend implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3731063808958340001L;
	@Id
	private String userid;
	@Id
	private String friendid;
	private String islike;
}
