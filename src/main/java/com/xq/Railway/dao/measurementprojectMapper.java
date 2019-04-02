package com.xq.Railway.dao;

import java.util.List;
import java.util.Map;

import com.xq.Railway.model.measurementproject;

public interface measurementprojectMapper {
    int deleteByPrimaryKey(String id);

//    int insert(measurementproject record);

    int insertSelective(measurementproject record);

    measurementproject selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(measurementproject record);

    
    
    int selectLastId();
    /**
     * 
     * 
     * @return
     */
	List<measurementproject> selectAll(int pageNum,int pageSize);

	List<measurementproject> selectAllCount();

	List<measurementproject> selectbymid(String mid, int pageNum,int pageSize);

	List<measurementproject> selectbymidCount(String mid);

	List<measurementproject> selectByName(String mName,int pageNum,int pageSize);

	List<measurementproject> selectByNameCount(String mName);

	List<Map> selectBy(String mid, String mName, int pageNum, int pageSize);

	List<Map> selectByCount(String mid, String mName);

	List<String> selectAllpName(String id);
	
	
	/**
	 * 根据站点 查询 合格率
	 * @param id
	 * @return
	 */
	List<Map> selectGetEchar(Integer id);

	List<Map> GetPageECharts(int parseInt);
	
	
	
	
    
}