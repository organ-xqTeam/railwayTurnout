package com.xq.Railway.dao;

import java.util.List;

import com.xq.Railway.model.Syslog;

public interface OperateLogMapper {

	int saveSysLog(Syslog sysLog);
	
	
	List<Syslog> selectAll(int pageNum ,int pageSize);
	
	List<Syslog> selectAlls();

}
