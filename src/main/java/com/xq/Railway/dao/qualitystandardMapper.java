package com.xq.Railway.dao;

import com.xq.Railway.model.qualitystandard;

public interface qualitystandardMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(qualitystandard record);

    int insertSelective(qualitystandard record);

    qualitystandard selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(qualitystandard record);

    int updateByPrimaryKey(qualitystandard record);
}