package com.xq.Railway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xq.Railway.dao.OperateLogMapper;
import com.xq.Railway.model.Syslog;

import net.sf.json.JSONObject;
@Service
public class logSelectService{
	
	
	@Autowired
	private OperateLogMapper iolm;
	
	public JSONObject selectAll(int pageNum, int pageSize ) {
		List<Syslog> list =  iolm.selectAll(pageNum*pageSize, pageSize);
		List<Syslog> listconut =  iolm.selectAlls();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("list", list);
		jsonObject.put("count", listconut.size());
		
		
		return jsonObject;
	}

}
