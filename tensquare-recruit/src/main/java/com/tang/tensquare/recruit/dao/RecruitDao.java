package com.tang.tensquare.recruit.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tang.tensquare.recruit.model.Recruit;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface RecruitDao extends JpaRepository<Recruit,String>,JpaSpecificationExecutor<Recruit>{

	List<Recruit> findTop3ByState(String state);

	List<Recruit> findTop3ByStateNotOrderByCreatetimeDesc(String state);

	List<Recruit> findTop2ByOrderByCreatetimeDesc();
	
}
