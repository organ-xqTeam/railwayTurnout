package com.xq.Railway.dao;

import com.xq.Railway.model.mainpageinformaction;

public interface mainpageinformactionMapper {
    int deleteByPrimaryKey(String id);

//    int insert(mainpageinformaction record);

    int insertSelective(mainpageinformaction record);

    mainpageinformaction selectByPrimaryKey(String id);
    
    /**
     * 
     *根据线路id查询
     * @param rid
     * @return
     */
    mainpageinformaction selectByPrimaryKeyrid(String rid);
    

    int updateByPrimaryKeySelective(mainpageinformaction record);

}