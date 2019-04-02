package com.xq.Railway.service;

import com.xq.Railway.model.measurementproject;

import net.sf.json.JSONObject;

public interface imeasurementprojectService {
	JSONObject instert(measurementproject m);
	
	measurementproject selectbyid(String id);
	
	
	int updatemeasurementproject(measurementproject m);
	
	int deletebyid(String id);
	/**
	 * 
	 * 
	 * 查询所有项目 排序
	 * @return
	 */
	JSONObject selectAll(int pageNum,int pageSize);
	
	/**
	 * 
	 * 
	 * 查询 项目  by 站点
	 * @param mid
	 * @return
	 */
	JSONObject selectBymid(String mid,int pageNum,int pageSize);
	/**
	 * 
	 * 项目名模糊查询
	 * @param mName
	 * @return
	 */
	JSONObject selectByName(String mName,int pageNum,int pageSize);
	/**
	 * 
	 * 
	 * @param id  站点id
	 * @param mName  项目名
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	JSONObject selectBy(String id, String mName, int pageNum, int pageSize);
	/**
	 * 查询站点下所有 项目名
	 * @param id
	 * @return
	 */
	JSONObject selectAllpName(String id);

	JSONObject GetHomeECharts(String id);

	JSONObject GetPageECharts(String id);
}
