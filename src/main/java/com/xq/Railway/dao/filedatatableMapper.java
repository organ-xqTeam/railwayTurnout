package com.xq.Railway.dao;

import java.util.List;

import com.xq.Railway.model.filedatatable;

public interface filedatatableMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(filedatatable record);

    int insertSelective(filedatatable record);

    filedatatable selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(filedatatable record);

	int insertSelectives(List<filedatatable> d);

	List<filedatatable> getfileByid(Integer id);


}