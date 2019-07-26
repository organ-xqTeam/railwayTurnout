package com.xq.Railway.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xq.Railway.logAop.MethodLog;
import com.xq.Railway.model.trainroute;
import com.xq.Railway.service.impl.TrainrouteService;

import io.swagger.annotations.Api;
import net.sf.json.JSONObject;
/**
 * 
 * 线路
 * @author XingPanST
 *
 */
@Api(tags="线路")
@RestController
@RequestMapping("/trainroute")
public class trainrouteController {
	private static final Logger LOG = LoggerFactory.getLogger(trainrouteController.class);
	@Autowired
	private TrainrouteService its;
	
	@Autowired
	private HttpServletRequest request;
	
	/**
	 * 新增 线路
	 * http://localhost:8089/trainroute/insert?routename=%E6%B2%88%E9%98%B3%E7%AB%99
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/insert" , method = RequestMethod.POST)
	@MethodLog(remark = "新增线路信息")
	public JSONObject insert(@RequestBody trainroute m) {
		JSONObject result = null;
		try {
			result = its.instert(m);
		} catch (Exception e) {
			LOG.error(e.getClass().getName() + ":" + e.getMessage());
			e.printStackTrace();
		}
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
	@RequestMapping(value = "/selectbyid", method = RequestMethod.GET)
	public trainroute selectbyid(String rid) {
		trainroute ma = null;
		try {
			ma = its.selectbyid(rid);
		} catch (Exception e) {
			LOG.error(e.getClass().getName() + ":" + e.getMessage());
			e.printStackTrace();
		}
		return ma;
		
	}
	/**
	 *
	 *
	 * 
	 *    http://localhost:8089/trainroute/updatebyid?routename=%E6%B2%88%E9%98%B3%E7%AB%991&id=4
	 */
	@RequestMapping(value = "/updatebyid", method = RequestMethod.POST)
	@MethodLog(remark = "更新线路信息")
	public int updatebyid(@RequestBody trainroute m) {
		int ma = 0;
		try {
			ma = its.updatetrainroute(m);
		} catch (Exception e) {
			LOG.error(e.getClass().getName() + ":" + e.getMessage());
			e.printStackTrace();
		}
		return ma;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@MethodLog(remark = "删除线路")
	public JSONObject deletebyid(String  id) {
		JSONObject ma = null;
		try {
			ma = its.deletebyid(id);
		} catch (Exception e) {
			LOG.error(e.getClass().getName() + ":" + e.getMessage());
			e.printStackTrace();
		}
		return ma;
	}
	
	/**
	 * 
	 * 
	 * http://localhost:8089/trainroute/selectAll
	 * 
	 * @return
	 */
	@RequestMapping(value = "/selectAll" , method = RequestMethod.GET)
	@MethodLog(remark = "查询所有线路")
	public JSONObject selectAll(Integer  pageNum,Integer  pageSize) {
		JSONObject jsonObject = new JSONObject();
		JSONObject json =  (JSONObject) request.getSession().getAttribute("login");
//		String aid = json.getString("aid");//管理员id
		String jc;
		try {
//			jc = json.getString("jurisdiction");
			jc = "高级";
		} catch (Exception e) {
			LOG.error(e.getClass().getName() + ":" + e.getMessage());
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
	@RequestMapping(value = "/selectAlltrainAndline" , method = RequestMethod.GET)
	public JSONObject selectAlltrainAndline(Integer  pageNum,Integer  pageSize) {
		JSONObject result = null;
		try {
			result = its.selectAlltrainAndline(pageNum,pageSize);
		} catch (Exception e) {
			LOG.error(e.getClass().getName() + ":" + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	 * 查询所有线路和站点ForAndroid
	 * @return
	 */
	@RequestMapping(value = "/selectTLForAndroid" , method = RequestMethod.GET)
	@MethodLog(remark = "查询所有线路并站点信息安卓")
	public JSONObject selectTLForAndroid() {
		JSONObject result = null;
		try {
			result = its.selectTLForAndroid();
		} catch (Exception e) {
			LOG.error(e.getClass().getName() + ":" + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	
	
}		
