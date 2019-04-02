package com.xq.Railway.dao;

import java.util.List;

import com.xq.Railway.model.linesite;

public interface linesiteMapper {
	
	//删除
    int deleteByPrimaryKey(String id);

//    int insert(linesite record);
    //新增
    int insertSelective(linesite record);
    //根据id查询
    linesite selectByPrimaryKey(String id);
    //根据线路id查询
    List<linesite> selectByrid1(String rid,Integer  pageNum,Integer  pageSize);
    
    
    List<linesite> selectByrid(String rid);
    
    
    List<linesite> selectAll();
    
    
    int updateByPrimaryKeySelective(linesite record);


}