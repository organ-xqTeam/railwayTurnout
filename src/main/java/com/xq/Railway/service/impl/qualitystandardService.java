package com.xq.Railway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xq.Railway.dao.qualitystandardMapper;
import com.xq.Railway.dao.turnoutstandardMapper;
import com.xq.Railway.model.qualitystandard;
import com.xq.Railway.model.turnoutstandard;
import com.xq.Railway.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Service
public class qualitystandardService {

	@Autowired
	private qualitystandardMapper repository;
	//道岔标准
	@Autowired
	private turnoutstandardMapper mapper;
	
	public JSONObject put(qualitystandard quality) {
		JSONObject jo = new JSONObject();
		
		
		String num =  quality.getNum();
		try {
			int n = Integer.parseInt(num);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			jo.put("r", e.getClass().getName() + ":" + e.getMessage());
			jo.put("s", "error");
			return jo;
		}
		quality.setId(StringUtil.getRanduuid());
		int s = repository.insertSelective(quality);
		if (s > 0) {
			jo.put("r", quality.getId());
			jo.put("s", "ok");
			return jo;
		} else {
			jo.put("r", quality.getMsg());
			jo.put("s", "error");
			return jo;
		}
	}

	public JSONObject remove(String id) {
		JSONObject jo = new JSONObject();
		
		//判断 如果有关联就不能删除
		turnoutstandard a = mapper.findbyqualitystandardid(id);
		
		if (a != null) {
			jo.put("r", "");
			jo.put("s", "error");
			return jo;
		}
		
		int n = repository.deleteByPrimaryKey(id);
		
		if (n > 0) {
			jo.put("r", "");
			jo.put("s", "ok");
			return jo;
		}else {
			jo.put("r", "");
			jo.put("s", "error");
			return jo;
		}
	}

	public qualitystandard get(String id) {
		qualitystandard tt = repository.selectByPrimaryKey(id);
		return tt;
	}

	public List<qualitystandard> values() {
		List<qualitystandard> turnouttypes = repository.values();
		return turnouttypes;
	}

	public JSONObject update(qualitystandard quality) {
		 JSONObject jo = new JSONObject();
			//判断名称是否重复
			int s = repository.updateByPrimaryKey(quality);
			if (s > 0 ) {
				jo.put("r", quality.getId());
				jo.put("s", "ok");
				return jo;
			}else {
				jo.put("r", quality.getMsg());
				jo.put("s", "error");
				return jo;
			}
		
	}

	public List<qualitystandard> getbyidentifiernum(String id) {
		List<qualitystandard> li = repository.getbyidentifiernum(id);
		return li;
	}

	public JSONArray valuesidentifiernum() {
		List<qualitystandard> li = repository.valuesidentifiernum();
		JSONArray ja = new JSONArray();
		for (qualitystandard qualitystandard : li) {
			
			JSONObject js = new JSONObject();
			js.put("id", qualitystandard.getIdentifiernum());
			js.put("msg", qualitystandard.getMsg1());
			
			ja.add(js);
		}
		
		return ja;
	}

	
	
	

}
