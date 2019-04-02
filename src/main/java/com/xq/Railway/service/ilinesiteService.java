package com.xq.Railway.service;

import com.xq.Railway.model.linesite;

import net.sf.json.JSONObject;
/**
 * 
 * 
 *站点
 * @author XingPanST
 *
 */
public interface ilinesiteService {
	
	JSONObject instert(linesite l);
	
	linesite selectbyid(String id);
	
	
	/**
	 * 
	 * 
	 * 查询所有站点
	 * @return
	 */
	JSONObject selectAll();
	
	int updatelinesite(linesite l);
	
	int deletebyid(String id);

	JSONObject selectbyrouteid(String routeid,Integer  pageNum,Integer  pageSize);
}
