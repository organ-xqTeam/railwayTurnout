package com.xq.Railway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xq.Railway.dao.filedatatableMapper;
import com.xq.Railway.model.filedatatable;

import net.sf.json.JSONObject;
@Service
public class FiledatatableService {
	
	
	@Autowired
	private filedatatableMapper ifm;
	
	public JSONObject instert(filedatatable d) {
		JSONObject jsonObject = new JSONObject();
		int  n =  ifm.insertSelective(d);
		if (n > 0) {
			jsonObject.put("stats", "success");
			jsonObject.put("code", "200");
			jsonObject.put("message", "");
		}else {
			jsonObject.put("stats", "fail");
			jsonObject.put("code", "200");
			jsonObject.put("message", "");
		}
		return jsonObject;
	}
	public JSONObject instert(List<filedatatable> d) {
		JSONObject jsonObject = new JSONObject();
		int  n =  ifm.insertSelectives(d);
		if (n > 0) {
			jsonObject.put("stats", "success");
			jsonObject.put("code", "200");
			jsonObject.put("message", "");
		}else {
			jsonObject.put("stats", "fail");
			jsonObject.put("code", "200");
			jsonObject.put("message", "");
		}
		return jsonObject;
	}
	public filedatatable getfileByid(String fileName) {
		filedatatable list = ifm.selectByPrimaryKey(Integer.parseInt(fileName));
		return list;
	}
	public JSONObject selectbyid(String id) {
		List<filedatatable> li =  ifm.getfileByid(Integer.parseInt(id));
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("list", li);
		return jsonObject;
	}
	

}
