package com.xq.Railway.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xq.Railway.dao.administratorMapper;
import com.xq.Railway.dao.linesiteMapper;
import com.xq.Railway.dao.mainpageinformactionMapper;
import com.xq.Railway.model.administrator;
import com.xq.Railway.model.linesite;
import com.xq.Railway.model.mainpageinformaction;

import net.sf.json.JSONObject;
@Service
public class MainpageinformactionService {
	
	@Autowired
	private mainpageinformactionMapper imm;
	
	@Autowired
	private administratorMapper iam;
	
	
	@Autowired
	private linesiteMapper ilm;

	
//	@Autowired
//	private HttpSession session;

	
	public JSONObject instert(mainpageinformaction mainpageinformaction) {
//		JSONObject json =  (JSONObject) session.getAttribute("login");
//		String aid = json.getString("aid");//管理员id
		String aid = mainpageinformaction.getAid();//管理员id
		
		
		
		JSONObject jsonObject = new JSONObject();
		String rid = mainpageinformaction.getRid();
		
		if ("".equals(aid) || aid == null) {
			jsonObject.put("stats", "fail");
			jsonObject.put("code", "200");
			jsonObject.put("message", "aid 不能为空");
			return jsonObject;
		}
		if ("".equals(rid) || rid == null) {
			jsonObject.put("stats", "fail");
			jsonObject.put("code", "200");
			jsonObject.put("message", "站点信息不能为空");
			return jsonObject;
		}
		mainpageinformaction aol = imm.selectByPrimaryKeyrid(mainpageinformaction.getRid());
		if (aol != null) {
			jsonObject.put("stats", "fail");
			jsonObject.put("code", "500");
			jsonObject.put("message", "站点设置重复");
			return jsonObject;
		}
		linesite line =  ilm.selectByPrimaryKey(mainpageinformaction.getRid());
		if (line == null) {
			jsonObject.put("stats", "fail");
			jsonObject.put("code", "500");
			jsonObject.put("message", "站点id有误");
			return jsonObject;
		}
		administrator record = new administrator();
		record.setAid(aid);
		record.setSiteid(mainpageinformaction.getRid());
		record.setLineid(line.getRouteid());
		int num = iam.updateByPrimaryKeySelective(record);
		int n = imm.insertSelective(mainpageinformaction);
		if (n > 0 && num > 0) {
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

	
	public mainpageinformaction selectbyid(String id) {
		mainpageinformaction mp = imm.selectByPrimaryKey(id);
		return mp;
	}

	
	public JSONObject selectbyrid(String id) {
		JSONObject jsonObject = new JSONObject();
		mainpageinformaction mp= imm.selectByPrimaryKeyrid(id);
		jsonObject.put("code", "200");
		jsonObject.put("state", "success");
		jsonObject.put("msg", mp);
		return jsonObject;
	}

	
	public int updatemainpageinformaction(mainpageinformaction mainpageinformaction) {
		
//		JSONObject json =  (JSONObject) session.getAttribute("login");
		String aid=mainpageinformaction.getAid();
		
		
		if ("".equals(aid) || aid == null) {
			return -1;
		}
//		try {
//			aid = json.getString("aid");
//		} catch (Exception e) {
//			return -1;
//		}
		
		String id =  mainpageinformaction.getId();
		if ("".equals(id) || id == null) {
			return 0;
		}
		linesite line =  ilm.selectByPrimaryKey(mainpageinformaction.getRid());
		if (line == null) {
			return -1;
		}
		administrator record = new administrator();
		record.setAid(aid);
		record.setSiteid(mainpageinformaction.getRid());
		record.setLineid(line.getRouteid());
		int num = iam.updateByPrimaryKeySelective(record);
		
		int n = imm.updateByPrimaryKeySelective(mainpageinformaction);
		return n+num;
	}

}
