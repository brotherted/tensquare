package com.tang.tensquare.spit.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.tang.tensquare.spit.model.Spit;

public interface SpitDao extends MongoRepository<Spit, String> {

	Page<Spit> findByParentid(String parentid, Pageable pageable);

}
