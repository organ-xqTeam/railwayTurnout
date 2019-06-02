package com.xq.Railway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xq.Railway.dao.measurementstandardMapper;
import com.xq.Railway.model.measurementstandard;
import com.xq.Railway.service.imeasurementstandardService;

import net.sf.json.JSONObject;
@Service("imss")
public class MeasurementstandardService implements imeasurementstandardService{
	
	@Autowired
	private  measurementstandardMapper imm;
	
	@Override
	public JSONObject instert(measurementstandard m) {
		JSONObject jsonObject = new JSONObject();
//		m.setState("2");
		int a = imm.insertSelective(m);
		if (a > 0) {
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

	@Override
	public measurementstandard selectbyid(String id) {
		measurementstandard measure = imm.selectByPrimaryKey(id);
		return measure;
	}

	@Override
	public int updatemeasurementstandard(measurementstandard m) {
		if ("1".equals(m.getId())) {
			return -1;
		}
		if ("2".equals(m.getId())) {
			return -1;
		}
		if ("3".equals(m.getId())) {
			return -1;
		}
		
		int n = imm.updateByPrimaryKeySelective(m);
		return n;
	}

	@Override
	public int deletebyid(String id) {
		if ("1".equals(id)) {
			return -1;
		}
		if ("2".equals(id)) {
			return -1;
		}
		if ("3".equals(id)) {
			return -1;
		}
		int a = imm.deleteByPrimaryKey(id);
		return a;
	}

	@Override
	public JSONObject selectAll(int pageNum, int pageSize) {
		List<measurementstandard> list = imm.selectAll( pageNum*pageSize,  pageSize);
		List<measurementstandard> listCount = imm.selectAllCount();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", "200");
		jsonObject.put("state", "success");
		jsonObject.put("list", list);
		jsonObject.put("listCount", listCount.size());
		return jsonObject;
	}

	@Override
	public JSONObject selectAll1(int pageNum, int pageSize) {
		List<measurementstandard> list = imm.selectAll1( pageNum*pageSize,  pageSize);
		List<measurementstandard> listCount = imm.selectAllCount1();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", "200");
		jsonObject.put("state", "success");
		jsonObject.put("list", list);
		jsonObject.put("listCount", listCount.size());
		return jsonObject;
	}

	@Override
	public JSONObject findbystandard(String id,int pageNum, int pageSize) {
		List<measurementstandard> list  = imm.findbystandard(id, pageNum*pageSize,  pageSize);
		List<measurementstandard> listCount  = imm.findbystandardAll(id);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("list", list);
		jsonObject.put("listCount", listCount.size());
		return jsonObject;
		
	}

	@Override
	public JSONObject findbyproject(String id, Integer pageNum, Integer pageSize) {
		
		List<measurementstandard> list  = imm.findbyproject(id, pageNum*pageSize,  pageSize);
		List<measurementstandard> listCount  = imm.findbyprojectAll(id);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("list", list);
		jsonObject.put("listCount", listCount.size());
		return jsonObject;
		
		
	}

}
