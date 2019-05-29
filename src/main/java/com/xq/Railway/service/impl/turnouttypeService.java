package com.xq.Railway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xq.Railway.dao.turnouttypeMapper;
import com.xq.Railway.model.turnouttype;
import com.xq.Railway.util.StringUtil;

import net.sf.json.JSONObject;
@Service
public class turnouttypeService{
	@Autowired
	private turnouttypeMapper repository;
	
	public JSONObject put(turnouttype user) {
		 JSONObject jo = new JSONObject();
		//判断名称是否重复
		String name = user.getTurnouttypename();
		if (name == null && "".equals(name)) {
			jo.put("r", "name is null");
			jo.put("s", "error");
			return jo;
		}
		turnouttype tt = repository.findbyturnouttypename(name);
		if (tt != null) {
			jo.put("r", "name is Repetitive");
			jo.put("s", "error");
			return jo;
		}
		
		user.setId(StringUtil.getRanduuid());
		int s = repository.insertSelective(user);
		if (s > 0 ) {
			jo.put("r", user.getId());
			jo.put("s", "ok");
			return jo;
		}else {
			jo.put("r", user.getTurnouttypename());
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

	public turnouttype get(String id) {
		turnouttype tt = repository.findbyid(id);
		return tt;
	}

	public List<turnouttype> values() {
		List<turnouttype> turnouttypes = repository.values();
		return turnouttypes;
	}

	public JSONObject update(turnouttype u) {
		 JSONObject jo = new JSONObject();
			//判断名称是否重复
			String name = u.getTurnouttypename();
			if (name == null && "".equals(name)) {
				jo.put("r", "name is null");
				jo.put("s", "error");
				return jo;
			}
			turnouttype tt = repository.findbyturnouttypename(name);
			if (tt != null) {
				jo.put("r", "name is Repetitive");
				jo.put("s", "error");
				return jo;
			}
			int s = repository.update(u);
			if (s > 0 ) {
				jo.put("r", u.getId());
				jo.put("s", "ok");
				return jo;
			}else {
				jo.put("r", u.getTurnouttypename());
				jo.put("s", "error");
				return jo;
			}
		
	}
	
	
	

}
