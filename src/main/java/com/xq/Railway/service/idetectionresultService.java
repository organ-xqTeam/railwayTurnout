package com.xq.Railway.service;

import java.util.List;

import com.xq.Railway.model.detectionresult;

import net.sf.json.JSONObject;
/**
 * 
 * 测量结果
 * @author XingPanST
 *
 */
public interface idetectionresultService {
	JSONObject instert(detectionresult d);
	
	detectionresult selectbyid(String id);
	
	JSONObject  selectbyrid(String id);
	
	List<detectionresult>  selectAll(Integer  pageNum,Integer  pageSize);
	
	
	int updatedetectionresult(detectionresult d);
	
	/**
	 * 
	 *     小车数据 计算后插入
	 * @param id
	 * @param object
	 * @return
	 */
	JSONObject insert(String id,JSONObject object);
	
	
	JSONObject updatedetectionresult(String id,JSONObject object);

	JSONObject deletebyid(String id);

	JSONObject selectbypname(String id,String pname,Integer  pageNum,Integer  pageSize);

	List<detectionresult> selectbymid(String id);

	
}
