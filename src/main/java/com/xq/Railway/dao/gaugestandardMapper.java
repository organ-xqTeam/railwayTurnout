package com.xq.Railway.dao;

import com.xq.Railway.model.gaugestandard;

public interface gaugestandardMapper {
    int deleteByPrimaryKey(String id);

    int insert(gaugestandard record);

    int insertSelective(gaugestandard record);

    gaugestandard selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(gaugestandard record);

    int updateByPrimaryKey(gaugestandard record);
}