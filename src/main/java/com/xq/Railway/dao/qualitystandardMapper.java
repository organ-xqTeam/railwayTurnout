package com.xq.Railway.dao;

import java.util.List;

import com.xq.Railway.model.qualitystandard;
 
public interface qualitystandardMapper {
    int deleteByPrimaryKey(String id);

    int insert(qualitystandard record);

    int insertSelective(qualitystandard record);

    qualitystandard selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(qualitystandard record);

    int updateByPrimaryKey(qualitystandard record);

	int remove(String id);

	List<qualitystandard> values();
	

}