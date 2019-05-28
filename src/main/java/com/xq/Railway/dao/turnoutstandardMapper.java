package com.xq.Railway.dao;

import com.xq.Railway.model.turnoutstandard;

public interface turnoutstandardMapper {
    int deleteByPrimaryKey(String id);

    int insert(turnoutstandard record);

    int insertSelective(turnoutstandard record);

    turnoutstandard selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(turnoutstandard record);

    int updateByPrimaryKey(turnoutstandard record);
}