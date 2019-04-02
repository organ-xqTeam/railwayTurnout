package com.xq.Railway.service;

import com.xq.Railway.model.mainpageinformaction;

import net.sf.json.JSONObject;

public interface iMainpageinformactionService {
	
	
	JSONObject instert(mainpageinformaction mainpageinformaction);
	
	mainpageinformaction selectbyid(String id);
	
	JSONObject selectbyrid(String id);
	
	
	int updatemainpageinformaction(mainpageinformaction mainpageinformaction);
	
}
