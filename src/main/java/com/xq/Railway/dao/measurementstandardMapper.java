package com.xq.Railway.dao;

import java.util.List;

import com.xq.Railway.model.measurementstandard;

public interface measurementstandardMapper {
    int deleteByPrimaryKey(String id);

//    int insert(measurementstandard record);

    int insertSelective(measurementstandard record);

    measurementstandard selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(measurementstandard record);


	List<measurementstandard> selectAll(int pageNum, int pageSize);

	List<measurementstandard> selectAllCount();

	List<measurementstandard> selectAll1(int pageNum, int pageSize);

	List<measurementstandard> selectAllCount1();
}