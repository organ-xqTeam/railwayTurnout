package com.xq.Railway.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xq.Railway.logAop.MethodLog;
import com.xq.Railway.model.JsonResult;
import com.xq.Railway.model.turnoutstandard;
import com.xq.Railway.service.impl.turnoutstandardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;

/**
 * 
 * 
 * 道岔标准
 * @author XingPanST
 *
 */
@Api(tags = "道岔标准")
@RestController
@RequestMapping("/turnoutstandard")
public class turnoutstandardController {
	private static final Logger LOG = LoggerFactory.getLogger(turnoutstandardController.class);
	@Autowired
	private turnoutstandardService service;
	
	/**
	 * 添加道岔标准
	 * @param user
	 * @return
	 */
	@ApiOperation(value="创建道岔标准", notes="根据turnouttype对象创建标准")
	@ApiImplicitParam(name = "turnout", value = "详细实体turnouttype", required = true, dataType = "turnoutstandard")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@MethodLog(remark = "创建道岔标准")
	public ResponseEntity<JsonResult> add (@RequestBody turnoutstandard turnout){
		JsonResult r = new JsonResult();
		try {
			JSONObject oj =  service.put(turnout);
			r.setResult(oj.getString("r"));
			r.setStatus(oj.getString("s"));
		} catch (Exception e) {
			LOG.error(e.getClass().getName() + ":" + e.getMessage());
			r.setResult(e.getClass().getName() + ":" + e.getMessage());
			r.setStatus("error");
			e.printStackTrace();
		}
		return ResponseEntity.ok(r);
	}
	/**
	 * 根据id删除道岔
	 * @param id
	 * @return
	 */
	@ApiOperation(value="删除标准", notes="根据标准的id来指定删除标准")
	@ApiImplicitParam(name = "id", value = "标准ID", required = true, dataType = "String", paramType = "path")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@MethodLog(remark = "删除标准")
	public ResponseEntity<JsonResult> delete (@PathVariable(value = "id") String id){
		JsonResult r = new JsonResult();
		try {
			JSONObject jo =  service.remove(id);
			r.setResult(jo.getString("r"));
			r.setStatus(jo.getString("s"));
		} catch (Exception e) {
			LOG.error(e.getClass().getName() + ":" + e.getMessage());
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
	@ApiOperation(value="获取道岔标准详细信息", notes="根据标准的id来获取道岔标准详细信息")
	@ApiImplicitParam(name = "id", value = "标准ID", required = true, dataType = "String", paramType = "path")
	@RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
	@MethodLog(remark = "获取道岔标准详细信息")
	public ResponseEntity<JsonResult> getUserById (@PathVariable(value = "id") String id){
		JsonResult r = new JsonResult();
		try {
			turnoutstandard user = service.get(id);
			r.setResult(user);
			r.setStatus("ok");
		} catch (Exception e) {
			LOG.error(e.getClass().getName() + ":" + e.getMessage());
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
	@ApiOperation(value="获取道岔标准列表", notes="获取道岔标准列表")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@MethodLog(remark = "获取道岔标准列表")
	public ResponseEntity<JsonResult> getUserList (){
		JsonResult r = new JsonResult();
		try {
			List<turnoutstandard> userList = new ArrayList<turnoutstandard>(service.values());
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
	@ApiOperation(value="更新信息", notes="根据url的id来指定更新标准信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "String",paramType = "path"),
			@ApiImplicitParam(name = "turnout", value = "实体turnoutstandard", required = true, dataType = "turnoutstandard")
	})
	@RequestMapping(value = "/save/{id}", method = RequestMethod.POST)
	@MethodLog(remark = "更新信息")
	public ResponseEntity<JsonResult> update (@PathVariable("id") String id, @RequestBody turnoutstandard turnout){
		JsonResult r = new JsonResult();
		try {
//			turnouttype u = turnoutser.get(id);
//			u.setTurnouttypename(turnout.getTurnouttypename());
			turnout.setId(id);
			JSONObject js=  service.update(turnout);
			r.setResult(js.getString("r"));
			r.setStatus(js.getString("s"));
		} catch (Exception e) {
			LOG.error(e.getClass().getName() + ":" + e.getMessage());
			r.setResult(e.getClass().getName() + ":" + e.getMessage());
			r.setStatus("error");
			e.printStackTrace();
		}
		return ResponseEntity.ok(r);
	}
	
	
}
