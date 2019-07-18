package com.xq.Railway.controller;



import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xq.Railway.model.JsonResult;
import com.xq.Railway.service.impl.calculationstandardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;



/**
 * 
 * @ClassName
 * @Author 
 * @Date 2018年9月11日 下午12:15:41
 */
@Api(tags="计算标准")
@RestController
@RequestMapping("/calculationstandard")
public class calculationstandardController {
	private static final Logger LOG = LoggerFactory.getLogger(calculationstandardController.class);
	@Autowired
	private calculationstandardService ser;
	
	@Value("${springurl.fileurl}")
	private String url;
	
	
	@ApiOperation(value="获取算法列表", notes="获取算法列表")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<JsonResult> getUserList (){
		JsonResult r = new JsonResult();
		try {
			JSONObject jo =  ser.standerd();
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
	
	
	/**
	 * 
	 * 获取指定文件夹下，下车数据表格
	 * @return
	 */
	@ApiOperation(value="获取小车文件数据列表", notes="获取小车文件数据列表")
	@RequestMapping(value = "/car", method = RequestMethod.GET)
	public ResponseEntity<JsonResult> getCarList (){
		JsonResult r = new JsonResult();
		try {
			File file = new File(url);
			System.out.println(file.exists());
			if (!file.exists()) {
				r.setResult(file.exists());
				r.setStatus("error");
			}
			List<String> li = new ArrayList<String>();
			
			String [] fileName = file.list();
			for (int i = 0; i < fileName.length; i++) {
				if (fileName[i].contains("-")) {
					if ("railwayVehicleData".equals(fileName[i].split("-")[0]) && fileName[i].endsWith("csv")) {
						li.add(fileName[i]);
					}
				}
			}
			r.setResult(li);
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
