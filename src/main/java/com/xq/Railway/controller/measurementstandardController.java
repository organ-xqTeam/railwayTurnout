package com.xq.Railway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xq.Railway.logAop.MethodLog;
import com.xq.Railway.model.measurementstandard;
import com.xq.Railway.service.imeasurementstandardService;

import net.sf.json.JSONObject;
import springfox.documentation.annotations.ApiIgnore;
/**
 * 
 * 检测项点
 * @author XingPanST
 *
 */
@ApiIgnore
@RestController
@RequestMapping("/measurementstandard")
public class measurementstandardController {

	@Autowired
	private imeasurementstandardService imss;
	
	/**
	 * 新增 测量标准
	 * http://localhost:8089/measurementstandard/insert?measurementitem=%E6%B5%8B%E9%87%8F%E9%A1%B9&standard=%E6%B5%8B%E9%87%8F%E6%A0%87%E5%87%86
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/insert" )
	@MethodLog(remark = "新增检测项点信息")
	public JSONObject insert(measurementstandard m) {
		JSONObject result = imss.instert(m);
		return result;
	}
	
	/**
	 *
	 *
	 *    
	 */
	@RequestMapping(value = "/updatebyid")
	@MethodLog(remark = "更新项点信息")
	public int updatebyid(measurementstandard m) {
		int ma = imss.updatemeasurementstandard(m);
		return ma;
	}
	
	
	@RequestMapping(value = "/delete")
	@MethodLog(remark = "删除项点")
	public int deletebyid(String id) {
		int ma = imss.deletebyid(id);
		return ma;
	}
	
	@RequestMapping(value = "/selectAll")
	@MethodLog(remark = "移动端查询所有项点")
	public JSONObject  selectAll(Integer  pageNum,Integer  pageSize) {
		JSONObject ma = imss.selectAll(pageNum, pageSize);
		return ma;
	}
	
	@RequestMapping(value = "/selectAll1")
	@MethodLog(remark = "pc端查询所有项点")
	public JSONObject  selectAll1(Integer  pageNum,Integer  pageSize) {
		JSONObject ma = imss.selectAll1(pageNum, pageSize);
		return ma;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param rid
	 * @return
	 */
	@RequestMapping(value = "/selectbyid")
	public measurementstandard selectbyid(String rid) {
		measurementstandard ma = imss.selectbyid(rid);
		return ma;
	}
}
