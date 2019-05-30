package com.xq.Railway.dao;

import java.util.List;

import com.xq.Railway.model.cityid;

public interface cityidMapper {
	List<cityid>  findAll();
	
	List<String>  findbycity1();
	
	List<String>  findbycity2(String str);
	
	List<cityid>  findbycity3(String str);
	
}
