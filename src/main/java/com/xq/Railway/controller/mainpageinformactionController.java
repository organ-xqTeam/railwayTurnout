package com.xq.Railway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xq.Railway.logAop.MethodLog;
import com.xq.Railway.model.mainpageinformaction;
import com.xq.Railway.service.iMainpageinformactionService;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/mainpageinformaction")
public class mainpageinformactionController {
	
	
	@Autowired
	private iMainpageinformactionService imis;
	
	/**
	 * 新增 站点 天气。。
	 * 
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/insert" )
	@MethodLog(remark = "新增站点信息")
	public JSONObject insert(mainpageinformaction m) {
		
		JSONObject result = imis.instert(m);
		return result;
	}
	/**
	 * 
	 * 
	 *   根据站点查询
	 * 
	 * @param rid
	 * @return
	 */
	@RequestMapping(value = "/selectbyrid")
	@MethodLog(remark = "查询站点信息")
	public JSONObject selectbyid(String rid) {
		JSONObject ma = imis.selectbyrid(rid);
		return ma;
		
	}
	/**
	 *
	 *
	 *  根据id 修改 站点信息
	 *    
	 */
	@RequestMapping(value = "/updatebyid")
	@MethodLog(remark = "更新站点信息")
	public int updatebyid(mainpageinformaction m) {
		int ma = imis.updatemainpageinformaction(m);
		return ma;
	}
	
	
	
}
