package com.xq.Railway.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xq.Railway.dao.linesiteMapper;
import com.xq.Railway.dao.trainrouteMapper;
import com.xq.Railway.model.linesite;
import com.xq.Railway.model.trainroute;
import com.xq.Railway.service.itrainrouteService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Service("its")
public class TrainrouteService implements itrainrouteService{
	
	
	@Autowired
	private linesiteMapper ilm;
	
	@Autowired
	private trainrouteMapper itm;
	
	@Override
	public JSONObject instert(trainroute t) {
		JSONObject jsonObject = new JSONObject();
		t.setIsdelete("0");
		int a =  itm.insertSelective(t);
		
		if (a > 0) {
			jsonObject.put("stats", "success");
			jsonObject.put("code", "200");
			jsonObject.put("message", "");
		}else {
			jsonObject.put("stats", "fail");
			jsonObject.put("code", "200");
			jsonObject.put("message", "");
		}
		return jsonObject;
	}

	@Override
	public trainroute selectbyid(String id) {
		trainroute t =  itm.selectByPrimaryKey(id);
		return t;
	}

	@Override
	public int updatetrainroute(trainroute t) {
		int a = itm.updateByPrimaryKeySelective(t);
		return a;
	}

	@Override
	public JSONObject deletebyid(String id) {
		JSONObject jsonObject = new JSONObject();
		List<linesite> li =  ilm.selectByrid(id);
		if (li.size() > 0 ) {
			jsonObject.put("stats", "fail");
			jsonObject.put("code", "200");
			jsonObject.put("message", "请先删除该线路的站点");
			return jsonObject;
		}
		int a =  itm.deleteByPrimaryKey(id);
		
		if (a > 0) {
			jsonObject.put("stats", "success");
			jsonObject.put("code", "200");
			jsonObject.put("message", "删除成功");
		}else {
			jsonObject.put("stats", "fail");
			jsonObject.put("code", "200");
			jsonObject.put("message", "删除失败");
		}
		
		return jsonObject;
	}

	@Override
	public JSONObject selectAll(String jc,Integer pageNum, Integer pageSize) {
		JSONObject jsonObject = new JSONObject();
		if ("100".equals(jc) || "".equals(jc)) {
			jsonObject.put("state","fail");
			jsonObject.put("massage","没有访问权限");
			jsonObject.put("list", "");
			return jsonObject;
		}
		List<trainroute> list =  itm.selectAll1(pageNum*pageSize,  pageSize);
		jsonObject.put("state","success");
		jsonObject.put("list", list);
		return jsonObject;
	}

	@Override
	public JSONObject selectAlltrainAndline(int pageNum, int pageSize) {
		List<Map> map =  itm.selectAlltrainAndline( pageNum,  pageSize);
		List<Map> mapCount =  itm.selectAlltrainAndlineCount();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("map",map);
		jsonObject.put("mapCount",mapCount.size());
		return jsonObject;
	}
	/**
	 * 
	 * {
    "cates": [
      {
        "cateid": "0_1",
        "catename": "瓜菜类",
        "children": [
          {
            "cateid": "1_2",
            "catename": "甜瓜"
          },
          {
            "cateid": "1_3",
            "catename": "黄瓜"
          }
        ]
      },
      {
        "cateid": "0_5",
        "catename": "玉米类",
        "children": [
          {
            "cateid": "5_6",
            "catename": "菜用玉米"
          }
        ]
      }
    ]
}
	 * t.id as id, t.routename as routename,l.id as lid,l.sitename
	 * 
	 */
	@Override
	public JSONObject selectTLForAndroid() {
		List<trainroute> list =  itm.selectAll();
		JSONObject jsonObject = new JSONObject();
		JSONArray array = new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			trainroute train = list.get(i);
			JSONObject jsonO = new JSONObject();
			jsonO.put("id", train.getId());
			jsonO.put("name", train.getRoutename());
			List<linesite> la =  ilm.selectByrid(train.getId());
			JSONArray array2 = new JSONArray();
			for (int j = 0; j < la.size(); j++) {
				linesite line = la.get(j);
				JSONObject object = new JSONObject();
				object.put("id",line.getId());
				object.put("name",line.getSitename());
				array2.add(object);
			}
			jsonO.put("children", array2);
			array.add(jsonO);
		}
		if (list.size() > 0) {
			jsonObject.put("code", "200");
			jsonObject.put("state", "success");
		}
		jsonObject.put("LineSite", array);
		return jsonObject;
	}

	

}
