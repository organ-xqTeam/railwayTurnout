package com.xq.Railway.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xq.Railway.dao.administratorMapper;
import com.xq.Railway.dao.linesiteMapper;
import com.xq.Railway.model.administrator;
import com.xq.Railway.model.linesite;

import net.sf.json.JSONObject;
@Service
public class LinesiteService{
	
	@Autowired
	private linesiteMapper ilm;
	
	@Autowired
	private administratorMapper iam;
	
	@Autowired
	private HttpServletRequest request;
	
	
	public JSONObject instert(linesite l) {
		JSONObject jsonObject = new JSONObject();
		
		
		String rid = l.getRouteid();
		if ("".equals(rid)|| rid == null) {
			jsonObject.put("stats", "fail");
			jsonObject.put("code", "500");
			jsonObject.put("message", "线路id不能为空");
			return jsonObject;
		}
		
		if ("".equals(l.getSitename())|| l.getSitename() == null) {
			jsonObject.put("stats", "fail");
			jsonObject.put("code", "500");
			jsonObject.put("message", "站点名称不能为空");
			return jsonObject;
		}
		l.setIsdelete("0");
		int a  = ilm.insertSelective(l);
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

	
	public linesite selectbyid(String id) {
		linesite linesite = ilm.selectByPrimaryKey(id);
		return linesite;
	}

	
	public int updatelinesite(linesite l) {
		int n =  ilm.updateByPrimaryKeySelective(l);
		return n;
	}

	
	public int deletebyid(String id) {
		/**
		 * 
		 * 查询是否有关联站点的 人
		 */
		List<administrator> list =  iam.selectAllsid(id);
		if (list.size() > 0) {
			return -1;
		}
		
		int n = ilm.deleteByPrimaryKey(id);
		return n;
	}

	
	public JSONObject selectAll() {
		List<linesite> li =  ilm.selectAll();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("li", li);
		return jsonObject;
	}

	
	public JSONObject selectbyrouteid(String routeid,Integer  pageNum,Integer  pageSize) {
		List<linesite> li =  ilm.selectByrid1(routeid,pageNum*pageSize,pageSize);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("list", li);
		return jsonObject;
	}

}
