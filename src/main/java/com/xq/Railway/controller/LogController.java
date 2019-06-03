package com.xq.Railway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xq.Railway.logAop.MethodLog;
import com.xq.Railway.service.impl.logSelectService;

import net.sf.json.JSONObject;
import springfox.documentation.annotations.ApiIgnore;



/**
 * 
 * 
 * 日志
 * @author XingPanST
 *
 */
@ApiIgnore
@RestController
@RequestMapping("/Systemlog")
public class LogController {
	
	@Autowired
	private logSelectService ilss;
	
	@RequestMapping(value = "/selectAlllog" )
	@MethodLog(remark = "查询系统日志")
	public JSONObject insert(Integer  pageNum,Integer  pageSize) {
		JSONObject result = ilss.selectAll(pageNum, pageSize);
		return result;
	}
	
	
	
}
