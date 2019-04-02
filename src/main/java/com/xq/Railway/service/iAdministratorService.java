package com.xq.Railway.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.xq.Railway.model.administrator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public interface iAdministratorService {

	
	/**
	 * 新增管理员
	 * yangweihang
	 * @Date 2018年9月11日 下午12:07:16
	 * @param a	管理员类
	 * @return
	 */
	JSONObject insertadmin(administrator a);
	
	
	
	/**
	 * 按用户名和密码查询
	 * yangweihang
	 * @Date 2018年9月11日 下午1:32:11
	 * @param ausername	用户名
	 * @param apwd	密码
	 * @return
	 */
	JSONObject selectadmin(String ausername,String apwd);
	
	/**
	 * 按aid查询
	 * yangweihang
	 * @Date 2018年9月13日 下午2:32:24
	 * @param aid	用户id
	 * @return
	 */
	administrator selectByAid(String aid);
	
	/**
	 * 
	 * 
	 * 查询所有超级管理员
	 * @param gid
	 * @return
	 */
	JSONObject selectAllgid(int pageNum,int pageSize);
	
	
	int updateAdmin(administrator a);
	
	
	int DeleteAdmin(String id);



	JSONObject adminlogin(String ausername, String apwd);


	
	
	

}
