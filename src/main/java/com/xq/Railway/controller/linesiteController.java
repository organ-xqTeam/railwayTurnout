package com.xq.Railway.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static final Logger LOG = LoggerFactory.getLogger(linesiteController.class);
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
		JSONObject result = null;
		try {
			result = ils.instert(m);
		} catch (Exception e) {
			LOG.error(e.getClass().getName() + ":" + e.getMessage());
			e.printStackTrace();
		}
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
		JSONObject result = null;
		try {
			result = ils.selectAll();
		} catch (Exception e) {
			LOG.error(e.getClass().getName() + ":" + e.getMessage());
			e.printStackTrace();
		}
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
		JSONObject ma = null;
		try {
			ma = ils.selectbyrouteid(routeid,  pageNum,  pageSize);
		} catch (Exception e) {
			LOG.error(e.getClass().getName() + ":" + e.getMessage());
			e.printStackTrace();
		}
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
		linesite ma = null;
		try {
			ma = ils.selectbyid(id);
		} catch (Exception e) {
			LOG.error(e.getClass().getName() + ":" + e.getMessage());
			e.printStackTrace();
		}
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
		int ma = 0;
		try {
			ma = ils.updatelinesite(m);
		} catch (Exception e) {
			LOG.error(e.getClass().getName() + ":" + e.getMessage());
			e.printStackTrace();
		}
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
		
		int ma = 0;
		try {
			ma = ils.deletebyid(id);
		} catch (Exception e) {
			LOG.error(e.getClass().getName() + ":" + e.getMessage());
			e.printStackTrace();
		}
		return ma;
	}
}
