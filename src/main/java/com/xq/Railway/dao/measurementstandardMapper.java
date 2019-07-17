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

	List<measurementstandard> findbystandard(String id, int pageNum, int pageSize);

	List<measurementstandard> findbystandardAll(String id);

	List<measurementstandard> findbyproject(String id, int pageNum, Integer pageSize);

	List<measurementstandard> findbyprojectAll(String id);

	List<measurementstandard> findbyturnoutstandard(String id, int pageNum, Integer pageSize);

	List<measurementstandard> findbyturnoutstandardAll(String id);
}