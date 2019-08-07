package com.tang.tensquare.base.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tang.tensquare.base.Dao.LabelDao;
import com.tang.tensquare.base.model.Label;

@Service
@Transactional
public class LabelSevice {

	@Autowired
	private LabelDao labelDao;
	
	public void save(Label label) {
		labelDao.save(label);
	}
	
	public void update(Label label) {
		labelDao.save(label);
	}
	
	public void delete(String labelId) {
		labelDao.deleteById(labelId);
	}
	
	public Label getById(String labelId) {
		return labelDao.findById(labelId).get();
	}
	
	public List<Label> getAll(){
		return labelDao.findAll();
	}

	public Page<Label> searchByPage(Label label, Integer page, Integer size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		return labelDao.findAll(new Specification<Label>() {
			@Override
			public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<>();
				if(!StringUtils.isEmpty(label.getLabelname())) {
					Predicate labelName = cb.like(root.get("labelname").as(String.class), "%"+label.getLabelname()+"%");
					predicateList.add(labelName);
				}
				if(!StringUtils.isEmpty(label.getState())) {
					Predicate state = cb.equal(root.get("state").as(String.class), label.getState());
					predicateList.add(state);
				}
				if(!StringUtils.isEmpty(label.getRecommend())) {
					Predicate recommend = cb.equal(root.get("recommend").as(String.class), label.getRecommend());
					predicateList.add(recommend);
				}
				return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
			}
		}, pageable);
	}

	public List<Label> search(Label label) {
		return labelDao.findAll(new Specification<Label>() {

			@Override
			public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<>();
				if(!StringUtils.isEmpty(label.getLabelname())) {
					Predicate labelName = cb.like(root.get("labelname").as(String.class), "%"+label.getLabelname()+"%");
					predicateList.add(labelName);
				}
				if(!StringUtils.isEmpty(label.getState())) {
					Predicate state = cb.equal(root.get("state").as(String.class), label.getState());
					predicateList.add(state);
				}
				if(!StringUtils.isEmpty(label.getRecommend())) {
					Predicate recommend = cb.equal(root.get("recommend").as(String.class), label.getRecommend());
					predicateList.add(recommend);
				}
				return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
			}
		});
	}
}
