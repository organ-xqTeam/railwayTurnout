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
import com.xq.Railway.model.turnouttype;
import com.xq.Railway.service.impl.turnouttypeService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;


@RestController
@RequestMapping("/turnouttype")
public class turnouttypeController {
	
	@Autowired
	private turnouttypeService turnoutser;
	
	/**
	 * 添加道岔
	 * @param user
	 * @return
	 */
	@ApiOperation(value="创建道岔分类", notes="根据turnouttype对象创建分类")
	@ApiImplicitParam(name = "turnout", value = "详细实体turnouttype", required = true, dataType = "turnouttype")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<JsonResult> add (@RequestBody turnouttype turnout){
		JsonResult r = new JsonResult();
		try {
			JSONObject oj =  turnoutser.put(turnout);
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
	 * 根据id删除道岔
	 * @param id
	 * @return
	 */
	@ApiOperation(value="删除分类", notes="根据分类的id来指定删除分类")
	@ApiImplicitParam(name = "id", value = "分类ID", required = true, dataType = "String", paramType = "path")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ResponseEntity<JsonResult> delete (@PathVariable(value = "id") String id){
		JsonResult r = new JsonResult();
		try {
			JSONObject jo =  turnoutser.remove(id);
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
	@ApiOperation(value="获取道岔分类详细信息", notes="根据道岔的id来获取道岔详细信息")
	@ApiImplicitParam(name = "id", value = "道岔ID", required = true, dataType = "String", paramType = "path")
	@RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
	public ResponseEntity<JsonResult> getUserById (@PathVariable(value = "id") String id){
		JsonResult r = new JsonResult();
		try {
			turnouttype user = turnoutser.get(id);
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
	@ApiOperation(value="获取道岔列表", notes="获取道岔列表")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<JsonResult> getUserList (){
		JsonResult r = new JsonResult();
		try {
			List<turnouttype> userList = new ArrayList<turnouttype>(turnoutser.values());
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
	@ApiOperation(value="更新信息", notes="根据url的id来指定更新道岔分类信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "String",paramType = "path"),
			@ApiImplicitParam(name = "turnout", value = "实体turnout", required = true, dataType = "turnouttype")
	})
	@RequestMapping(value = "/save/{id}", method = RequestMethod.POST)
	public ResponseEntity<JsonResult> update (@PathVariable("id") String id, @RequestBody turnouttype turnout){
		JsonResult r = new JsonResult();
		try {
//			turnouttype u = turnoutser.get(id);
//			u.setTurnouttypename(turnout.getTurnouttypename());
			turnout.setId(id);
			JSONObject js=  turnoutser.update(turnout);
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
