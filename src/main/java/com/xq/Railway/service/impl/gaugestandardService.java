package com.xq.Railway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xq.Railway.dao.gaugestandardMapper;
import com.xq.Railway.model.gaugestandard;
import com.xq.Railway.util.StringUtil;

import net.sf.json.JSONObject;
@Service
public class gaugestandardService{
	@Autowired
	private gaugestandardMapper repository;
	
	public JSONObject put(gaugestandard user) {
		 JSONObject jo = new JSONObject();
		
		
		user.setId(StringUtil.getRanduuid());
		int s = repository.insertSelective(user);
		if (s > 0 ) {
			jo.put("r", user.getId());
			jo.put("s", "ok");
			return jo;
		}else {
			jo.put("r", user.getGauge());
			jo.put("s", "error");
			return jo;
		}
	}

	public JSONObject remove(String id) {
		JSONObject jo = new JSONObject();
		
		//判断 如果有关联就不能删除
		
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

	public gaugestandard get(String id) {
		gaugestandard tt = repository.selectByPrimaryKey(id);
		return tt;
	}

	public List<gaugestandard> values() {
		List<gaugestandard> turnouttypes = repository.values();
		return turnouttypes;
	}

	public JSONObject update(gaugestandard u) {
		 JSONObject jo = new JSONObject();
			//判断名称是否重复
			
			int s = repository.updateByPrimaryKey(u);
			if (s > 0 ) {
				jo.put("r", u.getId());
				jo.put("s", "ok");
				return jo;
			}else {
				jo.put("r", u.getGauge());
				jo.put("s", "error");
				return jo;
			}
		
	}
	
	
	

}
