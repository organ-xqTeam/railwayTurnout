package com.xq.Railway.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xq.Railway.logAop.MethodLog;
import com.xq.Railway.model.trainroute;
import com.xq.Railway.service.itrainrouteService;

import net.sf.json.JSONObject;
/**
 * 
 * 线路
 * @author XingPanST
 *
 */
@RestController
@RequestMapping("/trainroute")
public class trainrouteController {

	@Autowired
	private itrainrouteService its;
	
	@Autowired
	private HttpServletRequest request;
	
	/**
	 * 新增 线路
	 * http://localhost:8089/trainroute/insert?routename=%E6%B2%88%E9%98%B3%E7%AB%99
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/insert" )
	@MethodLog(remark = "新增线路信息")
	public JSONObject insert(trainroute m) {
		JSONObject result = its.instert(m);
		return result;
	}
	/**】
	 * 
	 * 
	 *   
	 * http://localhost:8089/trainroute/selectbyid?rid=1
	 * @param rid
	 * @return
	 */
	@RequestMapping(value = "/selectbyid")
	public trainroute selectbyid(String rid) {
		trainroute ma = its.selectbyid(rid);
		return ma;
		
	}
	/**
	 *
	 *
	 * 
	 *    http://localhost:8089/trainroute/updatebyid?routename=%E6%B2%88%E9%98%B3%E7%AB%991&id=4
	 */
	@RequestMapping(value = "/updatebyid")
	@MethodLog(remark = "更新线路信息")
	public int updatebyid(trainroute m) {
		int ma = its.updatetrainroute(m);
		return ma;
	}
	
	@RequestMapping(value = "/delete")
	@MethodLog(remark = "删除线路")
	public JSONObject deletebyid(String  id) {
		JSONObject ma = its.deletebyid(id);
		return ma;
	}
	
	/**
	 * 
	 * 
	 * http://localhost:8089/trainroute/selectAll
	 * 
	 * @return
	 */
	@RequestMapping(value = "/selectAll" )
	@MethodLog(remark = "查询所有线路")
	public JSONObject selectAll(Integer  pageNum,Integer  pageSize) {
		JSONObject jsonObject = new JSONObject();
		JSONObject json =  (JSONObject) request.getSession().getAttribute("login");
//		String aid = json.getString("aid");//管理员id
		String jc;
		try {
			jc = json.getString("jurisdiction");
		} catch (Exception e) {
			jsonObject.put("state","fail");
			jsonObject.put("massage","未登录");
			jsonObject.put("list", "");
			return jsonObject;
		}
		JSONObject result = its.selectAll(jc,pageNum,pageSize);
		return result;
	}
	
	/**
	 * 
	 * 查询所有线路和站点
	 * @return
	 */
	@RequestMapping(value = "/selectAlltrainAndline" )
	public JSONObject selectAlltrainAndline(Integer  pageNum,Integer  pageSize) {
		JSONObject result = its.selectAlltrainAndline(pageNum,pageSize);
		return result;
	}
	
	/**
	 * 
	 * 查询所有线路和站点ForAndroid
	 * @return
	 */
	@RequestMapping(value = "/selectTLForAndroid" )
	@MethodLog(remark = "查询所有线路并站点信息安卓")
	public JSONObject selectTLForAndroid() {
		JSONObject result = its.selectTLForAndroid();
		return result;
	}
	
	
	
}		
