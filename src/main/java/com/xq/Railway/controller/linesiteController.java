package com.xq.Railway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xq.Railway.logAop.MethodLog;
import com.xq.Railway.model.linesite;
import com.xq.Railway.service.impl.LinesiteService;

import io.swagger.annotations.Api;
import net.sf.json.JSONObject;
/**
 * 
 * 
 * 站点
 * @author XingPanST
 *
 */
@Api(tags="站点")
@RestController
@RequestMapping("/linesite")
public class linesiteController {

	@Autowired
	private LinesiteService ils;
	
	/**
	 * 新增 站点 
	 * http://localhost:8089/linesite/insert?sitename=%E6%B2%88%E9%98%B3%E7%AB%99%E4%B8%AD%E7%AB%99&routeid=4
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/insert" , method = RequestMethod.POST)
	@MethodLog(remark = "新增站点")
	public JSONObject insert(@RequestBody linesite m) {
		JSONObject result = ils.instert(m);
		return result;
	}
	
	/**
	 * 
	 * 
	 * http://localhost:8089/linesite/selectAll
	 * @return
	 */
	
	@RequestMapping(value = "/selectAll" , method = RequestMethod.GET)
	@MethodLog(remark = "查询所有站点")
	public JSONObject selectAll() {
		JSONObject result = ils.selectAll();
		return result;
	}
	
	
	
	/**】
	 * 
	 * 
	 *   查询指定线路 所有站点
	 * http://localhost:8089/linesite/selectbyrouteid?routeid=4
	 * @param rid
	 * @return
	 */
	@RequestMapping(value = "/selectbyrouteid" , method = RequestMethod.GET)
	@MethodLog(remark = "查询指定线路站点")
	public JSONObject selectbyrouteid(String routeid,Integer  pageNum,Integer  pageSize) {
		JSONObject  ma = ils.selectbyrouteid(routeid,  pageNum,  pageSize);
		return ma;
	}
	/**
	 * 
	 * 根据id查单个站点
	 * http://localhost:8089/linesite/selectbyid?id=5
	 * @param id
	 * @return
	 */
	
	@RequestMapping(value = "/selectbyid" , method = RequestMethod.GET)
	@MethodLog(remark = "查看单个站点")
	public linesite selectbyid(String id) {
		linesite ma = ils.selectbyid(id);
		return ma;
	}
	
	
	/**
	 *
	 *根据 id 修改站点名
	 *  http://localhost:8089/linesite/updatebyid?id=5&sitename=%E6%B2%88%E9%98%B3%E7%AB%991111
	 *    
	 */
	@RequestMapping(value = "/updatebyid", method = RequestMethod.POST)
	@MethodLog(remark = "修改站点")
	public int updatebyid(@RequestBody linesite m) {
		int ma = ils.updatelinesite(m);
		return ma;
	}
	/**
	 * 
	 * 根据id 删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@MethodLog(remark = "删除站点")
	public int deletebyid(String id) {
		
		int ma = ils.deletebyid(id);
		return ma;
	}
}
