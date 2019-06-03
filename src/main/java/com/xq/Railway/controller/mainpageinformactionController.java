package com.xq.Railway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xq.Railway.logAop.MethodLog;
import com.xq.Railway.model.mainpageinformaction;
import com.xq.Railway.service.impl.MainpageinformactionService;

import io.swagger.annotations.Api;
import net.sf.json.JSONObject;


/**
 * 
 * 
 * 站点
 * @author XingPanST
 *
 */
@Api(tags="站点首页信息")
@RestController
@RequestMapping("/mainpageinformaction")
public class mainpageinformactionController {
	
	
	@Autowired
	private MainpageinformactionService imis;
	
	/**
	 * 新增 站点 天气。。
	 * 
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/insert" , method = RequestMethod.POST)
	@MethodLog(remark = "新增站点信息")
	public JSONObject insert(@RequestBody mainpageinformaction m) {
		
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
	@RequestMapping(value = "/selectbyrid", method = RequestMethod.GET)
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
	@RequestMapping(value = "/updatebyid", method = RequestMethod.POST)
	@MethodLog(remark = "更新站点信息")
	public int updatebyid(@RequestBody mainpageinformaction m) {
		int ma = imis.updatemainpageinformaction(m);
		return ma;
	}
	
	
	
}
