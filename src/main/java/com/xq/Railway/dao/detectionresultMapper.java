package com.xq.Railway.dao;

import java.util.List;

import com.xq.Railway.model.detectionresult;

public interface detectionresultMapper {
    int deleteByPrimaryKey(String id);

//    int insert(detectionresult record);

    int insertSelective(detectionresult record);
    
    /**
     * 获取最近一条生成的id
     * @return
     */
    int selectLastId();
    
    
    int insertSelectives(List<detectionresult> list);

    detectionresult selectByPrimaryKey(String id);
    
    /**
     * 根据 项目id查询
     * @param mid
     * @return
     */
    List<detectionresult> selectByMid(String mid);
    
    List<detectionresult> selectAll(Integer  pageNum,Integer  pageSize);

    int updateByPrimaryKeySelective(detectionresult record);

    /**
     * 
     *  模糊查询名
     * @param pname
     * @return
     */
	List<detectionresult> selectbypname(String mid,String pname,Integer  pageNum,Integer  pageSize);
	
	
	
	
	detectionresult selectbysidmid(String mid,String sid);

	List<detectionresult> selectbymidresults(String mid, String string);

	
	
}