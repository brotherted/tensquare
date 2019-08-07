package com.tang.tensquare.base.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tang.tensquare.base.model.Label;

public interface LabelDao extends JpaRepository<Label, String>, JpaSpecificationExecutor<Label> {

}
