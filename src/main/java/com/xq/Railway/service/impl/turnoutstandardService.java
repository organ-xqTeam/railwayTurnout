package com.xq.Railway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xq.Railway.dao.qualitystandardMapper;
import com.xq.Railway.dao.turnoutstandardMapper;
import com.xq.Railway.dao.turnouttypeMapper;
import com.xq.Railway.model.qualitystandard;
import com.xq.Railway.model.turnoutstandard;
import com.xq.Railway.model.turnouttype;
import com.xq.Railway.util.StringUtil;

import net.sf.json.JSONObject;
@Service
public class turnoutstandardService {
	/**
	 * 标准
	 * 
	 * 
	 */
	@Autowired
	private turnoutstandardMapper repository;
	//分类
	@Autowired
	private turnouttypeMapper tmapper;
	//质量标准
	@Autowired
	private qualitystandardMapper qMapper;
	public JSONObject put(turnoutstandard user) {
		 JSONObject jo = new JSONObject();
		 
		 
		 
		String qid =  user.getQualityid();
		String tid =  user.getTurnoutid();
		 List<qualitystandard> q = qMapper.getbyidentifiernum(qid);
		if (q == null || q.size() == 0) {
			jo.put("r", "质量分析标准不存在");
			jo.put("s", "error");
			return jo;
		}
		
		 turnouttype t = tmapper.findbyid(tid);
		if (t == null) {
			jo.put("r", "道岔分类标准不存在");
			jo.put("s", "error");
			return jo;
		}
			
		//判断名称是否重复
		String name = user.getTurnoutstandard();
		if (name == null && "".equals(name)) {
			jo.put("r", "name is null");
			jo.put("s", "error");
			return jo;
		}
		turnoutstandard tt = repository.findbyTurnoutstandard(name);
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
			jo.put("r", user.getTurnoutstandard());
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

	public turnoutstandard get(String id) {
		turnoutstandard tt = repository.selectByPrimaryKey(id);
		return tt;
	}

	public List<turnoutstandard> values() {
		List<turnoutstandard> turnouttypes = repository.values();
		return turnouttypes;
	}

	public JSONObject update(turnoutstandard u) {
		JSONObject jo = new JSONObject();

		String qid = u.getQualityid();
		String tid = u.getTurnoutid();
		List<qualitystandard> q = qMapper.getbyidentifiernum(qid);
		if (q == null || q.size() == 0) {
			jo.put("r", "质量分析标准不存在");
			jo.put("s", "error");
			return jo;
		}

		turnouttype t = tmapper.findbyid(tid);
		if (t == null) {
			jo.put("r", "道岔分类标准不存在");
			jo.put("s", "error");
			return jo;
		}

		// 判断名称是否重复
		String name = u.getTurnoutstandard();
		if (name == null && "".equals(name)) {
			jo.put("r", "name is null");
			jo.put("s", "error");
			return jo;
		}
		turnoutstandard tt = repository.findbyTurnoutstandard(name);
		if (tt != null) {
			jo.put("r", "name is Repetitive");
			jo.put("s", "error");
			return jo;
		}
		int s = repository.updateByPrimaryKey(u);
		if (s > 0) {
			jo.put("r", u.getId());
			jo.put("s", "ok");
			return jo;
		} else {
			jo.put("r", u.getTurnoutstandard());
			jo.put("s", "error");
			return jo;
		}

	}
	
	
	

}
