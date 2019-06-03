package com.xq.Railway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
@Api(tags="测试接口")
@RestController
public class TestController {
	/**
	 * 测试
	 */
	@RequestMapping(value=  "/Test" , method = RequestMethod.GET)
	public String Test() {
		return "index/login2";
	}
	
}
