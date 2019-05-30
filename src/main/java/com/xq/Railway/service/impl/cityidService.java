package com.xq.Railway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xq.Railway.dao.cityidMapper;
import com.xq.Railway.model.cityid;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Service
public class cityidService{
	
	@Autowired
	private cityidMapper repository;
	

	public JSONObject  values() {
		JSONObject jo = new JSONObject();
		
		List<String> turnouttypes = repository.findbycity1();
		//city1
		JSONArray array = new JSONArray();
		
		//city1
		for (int i = 0; i < turnouttypes.size(); i++) {
			
			String city = turnouttypes.get(i);
			JSONArray ja = new JSONArray();
			
			//city2
			List<String> li = repository.findbycity2(city);
			//city2
			for (int j = 0; j < li.size(); j++) {
				String city2 = li.get(j);
				//city3
				List<cityid> l = repository.findbycity3(city2);
				JSONArray jao = new JSONArray();
				for (cityid cityid : l) {
					JSONObject object = new JSONObject();
					object.put("id", cityid.getId());
					object.put("name", cityid.getCity3());
					jao.add(object);
				}
				
				
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("name", city2);
				jsonObject.put("li",jao);
				ja.add(jsonObject);
			}
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("name", city);
			jsonObject.put("li", ja);
			array.add(jsonObject);
		}
		jo.put("r",array);
		jo.put("s","ok");
		return jo;
	}
	

	
	
	

}
