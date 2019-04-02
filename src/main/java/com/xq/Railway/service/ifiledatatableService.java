package com.xq.Railway.service;

import java.util.List;

import com.xq.Railway.model.filedatatable;

import net.sf.json.JSONObject;

public interface ifiledatatableService {
	JSONObject instert(List<filedatatable> d);
	
	
	
	JSONObject instert(filedatatable d);



	filedatatable getfileByid(String fileName);



	JSONObject selectbyid(String id);
}
