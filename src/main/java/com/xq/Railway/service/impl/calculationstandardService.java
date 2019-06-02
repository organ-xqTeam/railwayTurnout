package com.xq.Railway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xq.Railway.dao.calculationstandardMapper;
import com.xq.Railway.model.calculationstandard;

import net.sf.json.JSONObject;

@Service
public class calculationstandardService {
	@Autowired
	private calculationstandardMapper mapper;

	public JSONObject standerd() {
		List<calculationstandard> r = mapper.selectall();
		JSONObject jo = new JSONObject();
		jo.put("r", r);
		jo.put("s", "ok");
		return jo;
	}
	
	
	
	
	
	
}
