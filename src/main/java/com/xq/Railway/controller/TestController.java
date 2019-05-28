package com.xq.Railway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import springfox.documentation.annotations.ApiIgnore;
@ApiIgnore
@Controller
public class TestController {
	/**
	 * 测试
	 */
	@RequestMapping("/Test")
	public String Test() {
		return "index/login2";
	}
	
}
