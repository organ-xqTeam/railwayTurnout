package com.xq.Railway.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xq.Railway.model.JsonResult;
import com.xq.Railway.service.impl.cityidService;
import com.xq.Railway.util.postgetUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;

/**
 * 
 * 城市天气
 * @author XingPanST
 *
 */
@Api(tags = "城市天气")
@RestController
@RequestMapping("/city")
public class cityidController {
	private static final Logger LOG = LoggerFactory.getLogger(cityidController.class);
	@Autowired
	private cityidService cityser;
	
	/**
	 * 
	 * @return
	 */
	@ApiOperation(value="获取城市id列表", notes="获取城市id列表")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<JsonResult> getUserList (){
		JsonResult r = new JsonResult();
		try {
			JSONObject jo =  cityser.values();
			r.setResult(jo.getString("r"));
			r.setStatus(jo.getString("s"));
		} catch (Exception e) {
			LOG.error(e.getClass().getName() + ":" + e.getMessage());
			r.setResult(e.getClass().getName() + ":" + e.getMessage());
			r.setStatus("error");
			e.printStackTrace();
		}
		return ResponseEntity.ok(r);
	}
	
	@ApiOperation(value="获取城市天气", notes="获取城市天气")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<JsonResult> getUserList1 (@PathVariable(value = "id") String id){
		JsonResult r = new JsonResult();
		try {
			String js =  postgetUtil.Get("http://www.weather.com.cn/data/sk/"+id+".html", "");
//			System.out.println(js);
//			JSONObject jo =  cityser.values();
			r.setResult(js);
			r.setStatus("ok");
		} catch (Exception e) {
			LOG.error(e.getClass().getName() + ":" + e.getMessage());
			r.setResult(e.getClass().getName() + ":" + e.getMessage());
			r.setStatus("error");
			e.printStackTrace();
		}
		return ResponseEntity.ok(r);
	}
	
	
	
	
	
	
}
