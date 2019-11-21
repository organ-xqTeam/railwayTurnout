package com.xq.Railway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.xq.Railway.dao.administratorMapper;
import com.xq.Railway.dao.linesiteMapper;
import com.xq.Railway.dao.trainrouteMapper;
import com.xq.Railway.model.administrator;
import com.xq.Railway.model.linesite;
import com.xq.Railway.model.trainroute;
import com.xq.Railway.util.MD5;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class AdministratorService  {
	
	@Autowired
	private administratorMapper iam;
	

	@Autowired
	private trainrouteMapper itm;
	
	@Autowired
	private linesiteMapper ilm;
	
	@Value("${default.PASSWORD}")
	private String pwd ;
	
	
	@Value("${key.MD5}")
	private String key ;
	
	
	
	public JSONObject insertadmin(administrator a) {
		JSONObject jsonObject = new JSONObject();
		a.setAfoundtime(System.currentTimeMillis()+"");
		if ("".equals(a.getAusername()) || a.getAusername() == null) {
			jsonObject.put("stats", "fail");
			jsonObject.put("code", "500");
			jsonObject.put("message", "账号不能为空！");
			return jsonObject;
		}
		administrator admin =  iam.selectusername(a.getAusername());
		if (admin != null) {
			jsonObject.put("stats", "fail");
			jsonObject.put("code", "500");
			jsonObject.put("message", "账户名不能重复！");
			return jsonObject;
		}
		
		try {
			a.setApwd(MD5.md5check(key,pwd));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		a.setIsdelete("0");
		int n =  iam.insertSelective(a);
		if (n > 0) {
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
	public JSONObject adminlogin(String ausername, String apwd) {
		JSONObject jsonObject = new JSONObject();
		String mpwd ;
		try {
			mpwd =  MD5.md5check(key,apwd);
		} catch (Exception e) {
			jsonObject.put("stats", "fail");
			jsonObject.put("code", "500");
			jsonObject.put("message", e);
			return jsonObject;
		}
		administrator admin = iam.selectusernamepwd(ausername, mpwd);
		if (admin != null) {
			if ("admin".equals(ausername)) {
				jsonObject.put("stats", "fail");
				jsonObject.put("code", "200");
				jsonObject.put("message", "超级管理员不能登陆");
				jsonObject.put("data", "");
				
			}else {
				jsonObject.put("stats", "success");
				jsonObject.put("code", "200");
				jsonObject.put("message", "");
				
				String xid = admin.getLineid();//线路
				String zid = admin.getSiteid();//站点
				trainroute train =  itm.selectByPrimaryKey(xid);//线路
				linesite line = ilm.selectByPrimaryKey(zid);//站点
				
				if (train == null ||line == null) {
//					jsonObject.put("trainroute", "");
//					jsonObject.put("linesite", "");
//					admin.setLineid("");
//					admin.setSiteid("");
				}else {
//					jsonObject.put("trainroute", train.getRoutename());
//					jsonObject.put("linesite", line.getSitename());
					admin.setLinename(train.getRoutename());
					admin.setSitename(line.getSitename());
				}
				jsonObject.put("data", admin);
				
			}
//			JSONObject object = new JSONObject();
//			object.put("username", admin.getAusername());
//			object.put("time", admin.getAfoundtime());
//			object.put("jurisdiction", admin.getJurisdiction());
//			object.put("line", admin.getLineid());
//			object.put("site", admin.getSiteid());
//			object.put("aid", admin.getAid());
//			session.setAttribute("login", object);
		}else {
			jsonObject.put("stats", "fail");
			jsonObject.put("code", "200");
			jsonObject.put("message", "账户号密码错误");
			jsonObject.put("data", "");
		}
		return jsonObject;
	
	}
	public JSONObject selectadmin(String ausername, String apwd) {
		JSONObject jsonObject = new JSONObject();
		
		String mpwd ;
		try {
			mpwd =  MD5.md5check(key,apwd);
		} catch (Exception e) {
			jsonObject.put("stats", "fail");
			jsonObject.put("code", "500");
			jsonObject.put("message", e);
			jsonObject.put("data", "");
			return jsonObject;
		}
		administrator admin = iam.selectusernamepwd(ausername, mpwd);
		if (admin != null) {
			jsonObject.put("stats", "success");
			jsonObject.put("code", "200");
			if ("admin".equals(ausername)) {
				jsonObject.put("message", "1");
			}else {
				jsonObject.put("message", "2");
			}
			JSONObject object = new JSONObject();
			object.put("username", admin.getAusername());
			object.put("time", admin.getAfoundtime());
			object.put("jurisdiction", admin.getJurisdiction());
			object.put("line", admin.getLineid());
			object.put("site", admin.getSiteid());
			object.put("aid", admin.getAid());
			jsonObject.put("obj", object);
		}else {
			jsonObject.put("stats", "fail");
			jsonObject.put("code", "200");
			jsonObject.put("message", "账户号密码错误");
		}
		
		
		
		return jsonObject;
	}

	public administrator selectByAid(String aid) {
		administrator a = iam.selectByPrimaryKey(aid);
		return a;
	}

	public JSONObject selectAllgid(int pageNum,int pageSize) {
		
		
		JSONObject object1 = new JSONObject();
		List<administrator> list = iam.selectAll(pageNum*pageSize,pageSize);
		
		List<administrator> listconut = iam.selectAlls();
		
		
		JSONArray array = new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			administrator value = list.get(i);
			JSONObject object = new  JSONObject();
			object.put("id", value.getAid());
			object.put("title", value.getJurisdiction());
			object.put("name", value.getAusername());
			array.add(object);
		}
		object1.put("array", array);
		object1.put("listconut", listconut.size());
		
		return object1;
	}

	public int updateAdmin(administrator a) {
		if ("".equals(a.getAid()) ||a.getAid() == null ) {
			return 0;
		}
		if ("".equals(a.getAusername()) ||a.getAusername() == null ) {
			return 0;
		}
		if ("".equals(a.getApwd()) ||a.getApwd() == null ) {
			return 0;
		}
		
		String pwd =  a.getApwd();
		try {
			a.setApwd(MD5.md5check(key,pwd));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int n = iam.updateByPrimaryKeySelective(a);
		return n;
	}

	public int DeleteAdmin(String id) {
//		int a =  iam.deleteByPrimaryKey(id);
		
		administrator a = new administrator();
		a.setAid(id);
		a.setIsdelete("1");
		int n = iam.updateByPrimaryKeySelective(a);
		
		return n;
	}

	

}
