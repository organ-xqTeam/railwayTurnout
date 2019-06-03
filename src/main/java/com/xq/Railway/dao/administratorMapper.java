package com.xq.Railway.dao;

import java.util.List;

import com.xq.Railway.model.administrator;

public interface administratorMapper {
	
	
	/* 
	 *  查看账号是否重复
	 * 
	 */
	
	administrator selectusername(String username);
	
	/**
	 * 
	 *    登陆验证 账号密码
	 * @param username
	 * @param pwd
	 * @return
	 */
	administrator selectusernamepwd(String username,String pwd);
	
	
	
	
	/**
	 *  查看所有管理员 过滤超管
	 * @return
	 */
	List<administrator> selectAll(int pageNum,int pageSize);
	/**
	 * 获取所有管理员
	 * @return
	 */
	List<administrator> selectAlls();
	
    int deleteByPrimaryKey(String aid);

//    int insert(administrator record);

    int insertSelective(administrator record);

    administrator selectByPrimaryKey(String aid);

    int updateByPrimaryKeySelective(administrator record);
    /**
	 * 判断 删除站点前是否鱼关联的人
	 * @param id
	 * @return
	 */
	List<administrator> selectAllsid(String id);

}