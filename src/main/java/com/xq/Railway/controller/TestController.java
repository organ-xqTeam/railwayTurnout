package com.xq.Railway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.xq.Railway.model.JsonResult;

import io.swagger.annotations.Api;
@Api(tags="测试接口")
@RestController
public class TestController {
	
	private static final Logger LOG = LoggerFactory.getLogger(TestController.class);
	/**
	 * 测试
	 */
	@RequestMapping(value=  "/Test" , method = RequestMethod.GET)
	public ResponseEntity<JsonResult> Test() {
		JsonResult r = new JsonResult();
		
		try {
			r.setResult("hello world!!!");
			r.setStatus("ok");
		} catch (Exception e) {
			LOG.error(e.getClass().getName() + ":" + e.getMessage());
			r.setResult(e.getClass().getName() + ":" + e.getMessage());
			r.setStatus("error");
			e.printStackTrace();
		}
		return ResponseEntity.ok(r);
	}
	
}
