package com.xq.Railway.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static final Logger LOG = LoggerFactory.getLogger(LogController.class);
	@Autowired
	private logSelectService ilss;
	
	@RequestMapping(value = "/selectAlllog" )
	@MethodLog(remark = "查询系统日志")
	public JSONObject insert(Integer  pageNum,Integer  pageSize) {
		JSONObject result = null;
		try {
			result = ilss.selectAll(pageNum, pageSize);
		} catch (Exception e) {
			LOG.error(e.getClass().getName() + ":" + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	
	
}
