package com.xq.Railway.service;

import javax.servlet.http.HttpSession;

import com.xq.Railway.model.trainroute;

import net.sf.json.JSONObject;
/**
 * 
 * 
 * 线路
 * @author XingPanST
 *
 */
public interface itrainrouteService {
	
	
	
	JSONObject instert(trainroute t);
	
	trainroute selectbyid(String id);
	
	
	int updatetrainroute(trainroute t);
	
	JSONObject deletebyid(String id);
	/**
	 * 
	 * 查询所有线路
	 * @return
	 */
	JSONObject selectAll(String jc,Integer  pageNum,Integer  pageSize);
	/**
	 * 
	 * 查询 站点和线路关联
	 * @param pageSize 
	 * @param pageNum 
	 * @return
	 */
	JSONObject selectAlltrainAndline(int pageNum, int pageSize);

	JSONObject selectTLForAndroid();
}
