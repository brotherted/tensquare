package com.tang.tensquare.spit.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tang.tensquare.common.util.IdWorker;
import com.tang.tensquare.spit.dao.SpitDao;
import com.tang.tensquare.spit.model.Spit;

@Service
@Transactional
public class SpitService {

	@Autowired
	private SpitDao spitDao;
	
	@Autowired
	private IdWorker idWorker;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public List<Spit> findAll(){
		return spitDao.findAll();
	}
	
	public void save(Spit spit) {
		spit.set_id(idWorker.nextId() + "");
		spit.setComment(0);
		spit.setPublishtime(new Date());
		spit.setThumbup(0);
		spit.setVisits(0);
		spit.setShare(0);
		spit.setState("1");
		if(!StringUtils.isEmpty(spit.getParentid())) {
			Query query = new Query();
			query.addCriteria(Criteria.where("_id").is(spit.get_id()));
			Update update = new Update();
			update.inc("comment", 1);
			mongoTemplate.updateFirst(query, update, "spit");
		}
		spitDao.save(spit);
	}
	
	public void update(Spit spit) {
		spitDao.save(spit);
	}
	
	public void delete(String _id) {
		spitDao.deleteById(_id);
	}
	
	public Spit getById(String _id) {
		return spitDao.findById(_id).get();
	}

	public List<Spit> search(Spit spit) {
		Example<Spit> example = Example.of(spit);
		return spitDao.findAll(example);
	}

	public Page<Spit> search(Spit spit, int page, int size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		Example<Spit> example = Example.of(spit);   
		return spitDao.findAll(example , pageable);
	}

	public Page<Spit> findByParenid(String parentid, int page, int size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		return spitDao.findByParentid(parentid, pageable);
	}

	public void updateThumbUp(String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		Update update = new Update();
		update.inc("thumbup", 1);
		mongoTemplate.updateFirst(query, update, "spit");
	}
}
