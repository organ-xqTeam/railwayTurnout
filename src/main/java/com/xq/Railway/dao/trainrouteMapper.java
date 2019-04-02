package com.xq.Railway.dao;

import java.util.List;
import java.util.Map;

import com.xq.Railway.model.trainroute;

public interface trainrouteMapper {
    int deleteByPrimaryKey(String id);

//    int insert(trainroute record);

    int insertSelective(trainroute record);

    trainroute selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(trainroute record);

    
	List<trainroute> selectAll1(Integer pageNum, Integer pageSize);
	
	List<trainroute> selectAll();
	/**
	 * 
	 * 站点 线路关联查询 所有
	 * @param pageSize 
	 * @param pageNum 
	 * @return
	 */
	List<Map> selectAlltrainAndline(int pageNum, int pageSize);
	
	List<Map> selectAlltrainAndlineCount();
}