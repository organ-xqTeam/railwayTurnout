package com.xq.Railway.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xq.Railway.model.JsonResult;
import com.xq.Railway.model.qualitystandard;
import com.xq.Railway.service.impl.qualitystandardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * 质量分析标准
 * @author XingPanST
 *
 */
@Api(tags = "质量分析标准")
@RestController
@RequestMapping("/qualitystandard")
public class qualitystandardController {
	
	@Autowired
	private qualitystandardService qualitystandard;
	
	/**
	 * 添加质量分析标准
	 * @param user
	 * @return
	 */
	@ApiOperation(value="创建质量分析标准", notes="根据turnouttype对象创建分类")
	@ApiImplicitParam(name = "quality", value = "详细实体qualitystandard", required = true, dataType = "qualitystandard")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<JsonResult> add (@RequestBody qualitystandard quality){
		JsonResult r = new JsonResult();
		try {
			JSONObject oj =  qualitystandard.put(quality);
			r.setResult(oj.getString("r"));
			r.setStatus(oj.getString("s"));
		} catch (Exception e) {
			r.setResult(e.getClass().getName() + ":" + e.getMessage());
			r.setStatus("error");
			e.printStackTrace();
		}
		return ResponseEntity.ok(r);
	}
	/**
	 * 根据id删除质量分析标准
	 * @param id
	 * @return
	 */
	@ApiOperation(value="删除质量分析标准", notes="根据分类的id来指定删除质量分析标准")
	@ApiImplicitParam(name = "id", value = "分类ID", required = true, dataType = "String", paramType = "path")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ResponseEntity<JsonResult> delete (@PathVariable(value = "id") String id){
		JsonResult r = new JsonResult();
		try {
			JSONObject jo =  qualitystandard.remove(id);
			r.setResult(jo.getString("r"));
			r.setStatus(jo.getString("s"));
		} catch (Exception e) {
			r.setResult(e.getClass().getName() + ":" + e.getMessage());
			r.setStatus("error");
			e.printStackTrace();
		}
		return ResponseEntity.ok(r);
	}
	
	/**
	 * 根据ID查询道岔
	 * @param id
	 * @return
	 */
	@ApiOperation(value="获取质量分析标准详细信息", notes="根据道岔的id来获取质量分析标准")
	@ApiImplicitParam(name = "id", value = "质量分析标准ID", required = true, dataType = "Integer", paramType = "path")
	@RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
	public ResponseEntity<JsonResult> getUserById (@PathVariable(value = "id") String id){
		JsonResult r = new JsonResult();
		try {
			qualitystandard user = qualitystandard.get(id);
			r.setResult(user);
			r.setStatus("ok");
		} catch (Exception e) {
			r.setResult(e.getClass().getName() + ":" + e.getMessage());
			r.setStatus("error");
			e.printStackTrace();
		}
		return ResponseEntity.ok(r);
	}
	
	@ApiOperation(value="获取类型下质量分析标准", notes="根据道岔的identifiernum来获取质量分析标准")
	@ApiImplicitParam(name = "id", value = "质量分析标准identifiernum", required = true, dataType = "String", paramType = "path")
	@RequestMapping(value = "/identifiernum/{id}", method = RequestMethod.GET)
	public ResponseEntity<JsonResult> getUserByidentifiernum (@PathVariable(value = "id") String id){
		JsonResult r = new JsonResult();
		try {
			List<qualitystandard> user = qualitystandard.getbyidentifiernum(id);
			r.setResult(user);
			r.setStatus("ok");
		} catch (Exception e) {
			r.setResult(e.getClass().getName() + ":" + e.getMessage());
			r.setStatus("error");
			e.printStackTrace();
		}
		return ResponseEntity.ok(r);
	}
	
	
	
	/**
	 * 查询道岔列表
	 * @return
	 */
	@ApiOperation(value="获取质量分析标准列表", notes="获取质量分析标准列表")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<JsonResult> getUserList (){
		JsonResult r = new JsonResult();
		try {
			List<qualitystandard> userList = new ArrayList<qualitystandard>(qualitystandard.values());
			r.setResult(userList);
			r.setStatus("ok");
		} catch (Exception e) {
			r.setResult(e.getClass().getName() + ":" + e.getMessage());
			r.setStatus("error");
			e.printStackTrace();
		}
		return ResponseEntity.ok(r);
	}
	/**
	 * 查询道岔列表
	 * @return
	 */
	@ApiOperation(value="获取质量分析标准分类", notes="获取质量分析标准分类")
	@RequestMapping(value = "/findidentifiernum", method = RequestMethod.GET)
	public ResponseEntity<JsonResult> getlevel (){
		JsonResult r = new JsonResult();
		try {
			JSONArray userList = qualitystandard.valuesidentifiernum();
			r.setResult(userList);
			r.setStatus("ok");
		} catch (Exception e) {
			r.setResult(e.getClass().getName() + ":" + e.getMessage());
			r.setStatus("error");
			e.printStackTrace();
		}
		return ResponseEntity.ok(r);
	}

	
	
	/**
	 * 根据id修改道岔信息
	 * @param user
	 * @return
	 */
	@ApiOperation(value="更新质量分析标准", notes="根据质量分析标准的id来指定更新质量分析标准")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "String",paramType = "path"),
			@ApiImplicitParam(name = "quality", value = "实体quality", required = true, dataType = "qualitystandard")
	})
	@RequestMapping(value = "/save/{id}", method = RequestMethod.POST)
	public ResponseEntity<JsonResult> update (@PathVariable("id") String id, @RequestBody qualitystandard quality){
		JsonResult r = new JsonResult();
		try {
			quality.setId(id);
			JSONObject js=  qualitystandard.update(quality);
			r.setResult(js.getString("r"));
			r.setStatus(js.getString("s"));
		} catch (Exception e) {
			r.setResult(e.getClass().getName() + ":" + e.getMessage());
			r.setStatus("error");
			e.printStackTrace();
		}
		return ResponseEntity.ok(r);
	}
	
	
}
