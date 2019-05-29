package com.xq.Railway.dao;


import java.util.List;

import org.springframework.data.repository.query.Param;

import com.xq.Railway.model.turnouttype;

public interface turnouttypeMapper{
	
	
    int insert(turnouttype record);
    
    int insertSelective(turnouttype record);
    
	turnouttype findbyturnouttypename(@Param(value="turnouttypename")String turnouttypename);

	int remove(String id);

	turnouttype findbyid(String id);

	List<turnouttype> values();

	int update(turnouttype u);
	
}