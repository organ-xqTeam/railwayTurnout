package com.xq.Railway.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xq.Railway.model.JsonResult;
import com.xq.Railway.service.impl.calculationstandardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;



/**
 * 
 * @ClassName
 * @Author 
 * @Date 2018年9月11日 下午12:15:41
 */
@Api(tags="计算标准")
@RestController
@RequestMapping("/calculationstandard")
public class calculationstandardController {
	
	@Autowired
	private calculationstandardService ser;
	
	@ApiOperation(value="获取算法列表", notes="获取算法列表")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<JsonResult> getUserList (){
		JsonResult r = new JsonResult();
		try {
			JSONObject jo =  ser.standerd();
			r.setResult(jo.getString("r"));
			r.setStatus(jo.getString("s"));
		} catch (Exception e) {
			r.setResult(e.getClass().getName() + ":" + e.getMessage());
			r.setStatus("error");
			e.printStackTrace();
		}
		return ResponseEntity.ok(r);
	}
	
}
