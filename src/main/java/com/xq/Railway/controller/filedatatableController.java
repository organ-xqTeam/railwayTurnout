package com.xq.Railway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xq.Railway.logAop.MethodLog;
import com.xq.Railway.service.ifiledatatableService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
@Api(tags="文件管理")
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
	@ApiOperation(value="获取所有项点图片", notes="根据id获取所有项点图片")
	@ApiImplicitParam(name = "id", value = "项目id", required = true,  dataType = "String", paramType = "path")
	@RequestMapping(value = "/selectbyid", method = RequestMethod.GET)
	@MethodLog(remark = "根据项目id 查结果")
	public JSONObject  selectbyid(String id) {
		JSONObject  ma = ifs.selectbyid(id);
		return ma;
	}
	
	
	
}
