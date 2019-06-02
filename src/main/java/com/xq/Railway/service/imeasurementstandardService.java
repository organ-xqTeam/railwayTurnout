package com.xq.Railway.service;

import com.xq.Railway.model.measurementstandard;

import net.sf.json.JSONObject;

public interface imeasurementstandardService {

	
	
	JSONObject instert(measurementstandard m);
	
	measurementstandard selectbyid(String id);
	
	
	int updatemeasurementstandard(measurementstandard m);
	
	int deletebyid(String id);

	JSONObject selectAll(int pageNum, int pageSize);

	JSONObject selectAll1(int pageNum, int pageSize);

	JSONObject findbystandard(String id,int pageNum, int pageSize);

	JSONObject findbyproject(String id, Integer pageNum, Integer pageSize);
}
