package com.xq.Railway.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xq.Railway.dao.linesiteMapper;
import com.xq.Railway.dao.measurementprojectMapper;
import com.xq.Railway.model.linesite;
import com.xq.Railway.model.measurementproject;
import com.xq.Railway.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Service
public class MeasurementprojectService {
	
	
	@Autowired
	private measurementprojectMapper imm;
	
	@Autowired
	private linesiteMapper ilm;
	
	/**
	 * 
	 * 
	 *  新增测量项目 
	 */
	
	public JSONObject instert(measurementproject m) {
		JSONObject jsonObject = new JSONObject();
		m.setPtime(StringUtil.Dateform(new Date()));
		m.setWarningstatistics("0");
		Integer a = imm.insertSelective(m);
		int aid =  imm.selectLastId();
		m.setAid(aid+"");
		if (a > 0) {
			jsonObject.put("stats", "success");
			jsonObject.put("code", "200");
			jsonObject.put("message", "");
			jsonObject.put("project", m);
		}else {
			jsonObject.put("stats", "fail");
			jsonObject.put("code", "500");
			jsonObject.put("message", "");
			jsonObject.put("project", m);
		}
		return jsonObject;
	}

	
	public measurementproject selectbyid(String id) {
		measurementproject measurementproject = imm.selectByPrimaryKey(id);
		return measurementproject;
	}

	
	public int updatemeasurementproject(measurementproject m) {
		int a = imm.updateByPrimaryKeySelective(m);
		return a;
	}

	
	public int deletebyid(String id) {
		int a  = imm.deleteByPrimaryKey(id);
		return a;
	}

	
	public JSONObject selectAll(int pageNum,int pageSize) {
		List<measurementproject> list  = imm.selectAll( pageNum*pageSize, pageSize);
		List<measurementproject> listCount  = imm.selectAllCount();
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("list", list);
		jsonObject.put("listCount", listCount.size());
		return jsonObject;
	}

	
	public JSONObject selectBymid(String mid,int pageNum,int pageSize) {
		List<measurementproject> list  = imm.selectbymid(mid, pageNum*pageSize, pageSize);
		List<measurementproject> listCount  = imm.selectbymidCount(mid);
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("code", "200");
		jsonObject.put("state", "success");
		jsonObject.put("list", list);
		jsonObject.put("listCount", listCount.size());
		return jsonObject;
	}

	
	public JSONObject selectByName(String mName,int pageNum,int pageSize) {
		List<measurementproject> list  = imm.selectByName(mName, pageNum*pageSize, pageSize);
		List<measurementproject> listCount  = imm.selectByNameCount(mName);
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("list", list);
		jsonObject.put("listCount", listCount.size());
		return jsonObject;
	}

	
	public JSONObject selectBy(String id, String mName, int pageNum, int pageSize) {
		if ("".equals(mName)) {
			mName = null;
		}
		List<Map> list  = imm.selectBy(id,mName, pageNum*pageSize, pageSize);
		List<Map> listCount  = imm.selectByCount(id,mName);
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("list", list);
		jsonObject.put("listCount", listCount.size());
		return jsonObject;
	}
	/**
	 * 
	 * 查询站点下所有项目民
	 */
	
	public JSONObject selectAllpName(String id) {
		List<String> list  = imm.selectAllpName(id);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("list", list);
		return jsonObject;
	}

	
	public JSONObject GetHomeECharts(String id) {
		List<Map> listm =  imm.selectGetEchar(Integer.parseInt(id));
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("listm", listm);
		return jsonObject;
	}

	
	public JSONObject GetPageECharts(String id) {
		List<Map> listm =  imm.GetPageECharts(Integer.parseInt(id));
		
		Set<String> set  = new HashSet<String>();
		for (int i = 0; i < listm.size(); i++) {
			Map map = listm.get(i);
			String lid =  (String) map.get("lid");
			set.add(lid);
		}
		
		JSONArray array = new JSONArray();
		
		Iterator<String> it =  set.iterator();
		while (it.hasNext()) {
			String st = (String) it.next();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("lid", st);
			linesite site =  ilm.selectByPrimaryKey(st);
			jsonObject.put("lidname", site.getSitename());
			
			JSONArray jsonArray = new JSONArray();
			for (int i = 0; i < listm.size(); i++) {
				Map map = listm.get(i);
				String lid =  (String) map.get("lid");
				if (lid.equals(st)) {
					jsonArray.add(map);
				}
			}
			jsonObject.put("jsonArray", jsonArray);
			array.add(jsonObject);
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("listm", array);
		return jsonObject;
	}

}
