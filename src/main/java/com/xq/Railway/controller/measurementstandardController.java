package com.xq.Railway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xq.Railway.logAop.MethodLog;
import com.xq.Railway.model.JsonResult;
import com.xq.Railway.model.measurementstandard;
import com.xq.Railway.service.imeasurementstandardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
/**
 * 
 * 检测项点
 * @author XingPanST
 *
 */
@Api(tags = "检测项点")
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
	@ApiOperation(value="根据测量标准ID查询所有测量项", notes="根据测量标准ID查询所有测量项")
	@ApiImplicitParam(name = "id", value = "测量标准ID", required = true, dataType = "String", paramType = "path")
	@RequestMapping(value = "/standard/{id}" , method = RequestMethod.GET)
	public ResponseEntity<JsonResult> findbystandard(@PathVariable(value = "id") String id,Integer pageNum, Integer pageSize){
		JsonResult r = new JsonResult();
		try {
			JSONObject jos = imss.findbystandard(id,pageNum,pageSize);
			r.setResult(jos);
			r.setStatus("ok");
		} catch (Exception e) {
			r.setResult(e.getClass().getName() + ":" + e.getMessage());
			r.setStatus("error");
			e.printStackTrace();
		}
		return ResponseEntity.ok(r);
		
	}
	@ApiOperation(value="根据项目ID查询所有测量项", notes="根据项目ID查询所有测量项")
	@ApiImplicitParam(name = "id", value = "项目ID", required = true, dataType = "String", paramType = "path")
	@RequestMapping(value = "/project/{id}" , method = RequestMethod.GET)
	public ResponseEntity<JsonResult> findbyproject(@PathVariable(value = "id") String id,Integer pageNum, Integer pageSize){
		JsonResult r = new JsonResult();
		try {
			JSONObject jos = imss.findbyproject(id,pageNum,pageSize);
			r.setResult(jos);
			r.setStatus("ok");
		} catch (Exception e) {
			r.setResult(e.getClass().getName() + ":" + e.getMessage());
			r.setStatus("error");
			e.printStackTrace();
		}
		return ResponseEntity.ok(r);
		
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
