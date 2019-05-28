package com.xq.Railway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xq.Railway.logAop.MethodLog;
import com.xq.Railway.service.ifiledatatableService;

import net.sf.json.JSONObject;
import springfox.documentation.annotations.ApiIgnore;
@ApiIgnore
@RestController
@RequestMapping("/filedatatable")
public class filedatatableController {
	
	
	@Autowired
	private ifiledatatableService ifs;
	/**
	 * 
	 * 
	 *    获取所有项点图片
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/selectbyid")
	@MethodLog(remark = "根据项目id 查结果")
	public JSONObject  selectbyid(String id) {
		JSONObject  ma = ifs.selectbyid(id);
		return ma;
	}
	
	
	
}
