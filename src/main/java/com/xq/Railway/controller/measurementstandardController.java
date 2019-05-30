package com.xq.Railway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xq.Railway.logAop.MethodLog;
import com.xq.Railway.model.measurementstandard;
import com.xq.Railway.service.imeasurementstandardService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
/**
 * 
 * 检测项点
 * @author XingPanST
 *
 */
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
	@ApiOperation(value="新增测量标准", notes="根据measurementstandard对象 新增 测量标准")
	@ApiImplicitParam(name = "m", value = "详细实体measurementstandard", required = true, dataType = "measurementstandard")
	@RequestMapping(value = "/insert" , method = RequestMethod.POST)
	@MethodLog(remark = "新增检测项点信息")
	public JSONObject insert(@RequestBody measurementstandard m) {
		JSONObject result = imss.instert(m);
		return result;
	}
	
	/**
	 *
	 *
	 *    
	 */
	@RequestMapping(value = "/updatebyid" , method = RequestMethod.POST)
	@MethodLog(remark = "更新项点信息")
	public int updatebyid(@RequestBody measurementstandard m) {
		int ma = imss.updatemeasurementstandard(m);
		return ma;
	}
	
	
	@RequestMapping(value = "/delete" , method = RequestMethod.GET)
	@MethodLog(remark = "删除项点")
	public int deletebyid(String id) {
		int ma = imss.deletebyid(id);
		return ma;
	}
	
	@RequestMapping(value = "/selectAll" , method = RequestMethod.GET)
	@MethodLog(remark = "移动端查询所有项点")
	public JSONObject  selectAll(Integer  pageNum,Integer  pageSize) {
		JSONObject ma = imss.selectAll(pageNum, pageSize);
		return ma;
	}
	
	@RequestMapping(value = "/selectAll1" , method = RequestMethod.GET)
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
	@RequestMapping(value = "/selectbyid" , method = RequestMethod.GET)
	public measurementstandard selectbyid(String rid) {
		measurementstandard ma = imss.selectbyid(rid);
		return ma;
	}
}
