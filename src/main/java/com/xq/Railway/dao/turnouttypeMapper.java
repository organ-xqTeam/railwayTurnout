package com.xq.Railway.dao;

import com.xq.Railway.model.turnouttype;

public interface turnouttypeMapper {
    int insert(turnouttype record);

    int insertSelective(turnouttype record);
}