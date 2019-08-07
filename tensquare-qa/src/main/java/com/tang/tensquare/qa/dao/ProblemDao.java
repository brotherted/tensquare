package com.tang.tensquare.qa.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.tang.tensquare.qa.model.Problem;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{

	@Query(value = "SELECT p.* FROM tb_problem p, tb_pl l WHERE p.id = l.problemid AND l.labelid = ? ORDER BY p.createtime DESC" ,nativeQuery = true)
	List<Problem> newestProblemList(String labelid, Pageable pageable);

	@Query(value = "SELECT p.* FROM tb_problem p, tb_pl l WHERE p.id = l.problemid AND l.labelid = ? ORDER BY p.reply DESC" ,nativeQuery = true)
	List<Problem> hottestProblemList(String labelid, Pageable pageable);

	@Query(value = "SELECT p.* FROM tb_problem p, tb_pl l WHERE p.id = l.problemid AND p.reply = 0 AND l.labelid = ?" ,nativeQuery = true)
	List<Problem> waitAnswerProblemList(String labelid, Pageable pageable);
	
}
