package com.xq.Railway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xq.Railway.dao.qualitystandardMapper;
import com.xq.Railway.model.qualitystandard;
import com.xq.Railway.util.StringUtil;

import net.sf.json.JSONObject;
@Service
public class qualitystandardService {

	@Autowired
	private qualitystandardMapper repository;
	
	public JSONObject put(qualitystandard quality) {
		 JSONObject jo = new JSONObject();
		 
		quality.setId(StringUtil.getRanduuid());
		int s = repository.insertSelective(quality);
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

	public JSONObject remove(String id) {
		JSONObject jo = new JSONObject();
		
		//判断 如果有关联就不能删除
		
		int n = repository.remove(id);
		
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

	
	
	

}
