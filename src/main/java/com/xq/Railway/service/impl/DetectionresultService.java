package com.xq.Railway.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xq.Railway.dao.detectionresultMapper;
import com.xq.Railway.dao.filedatatableMapper;
import com.xq.Railway.dao.measurementprojectMapper;
import com.xq.Railway.dao.measurementstandardMapper;
import com.xq.Railway.model.detectionresult;
import com.xq.Railway.model.filedatatable;
import com.xq.Railway.model.measurementproject;
import com.xq.Railway.model.measurementstandard;
import com.xq.Railway.service.idetectionresultService;
import com.xq.Railway.util.DecimalUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Service("ids")
public class DetectionresultService implements idetectionresultService{
	
	@Autowired
	private detectionresultMapper idm;
	
	@Autowired
	private  measurementstandardMapper imm;
	
	@Autowired
	private  measurementprojectMapper impm;
	
	@Autowired
	private filedatatableMapper ifm;
	
	@Override
	public JSONObject instert(detectionresult d) {
		JSONObject jsonObject = new JSONObject();
		
		boolean flag = true;
		if ("".equals(d.getMid()) ||d.getMid() == null ) {
			flag = false;
		}
		if ("".equals(d.getStandardid()) || d.getStandardid() == null ) {
			flag = false;
		}
		detectionresult de = null ;
		if (flag) {
			 de  =   idm.selectbysidmid(d.getMid(), d.getStandardid());
		}
		if (de != null) {
			int n1 =  idm.deleteByPrimaryKey(de.getId());
		}
		int a = idm.insertSelective(d);
		int lastid =  idm.selectLastId();
		
		
		List<detectionresult> lists =  idm.selectbymidresults(d.getMid(), "不合格");
		measurementproject record = new measurementproject();
		record.setId(Integer.parseInt(d.getMid()));
		record.setWarningstatistics(lists.size()+"");
		impm.updateByPrimaryKeySelective(record);
		
		if (a > 0) {
			jsonObject.put("stats", "success");
			jsonObject.put("code", "200");
			jsonObject.put("message", d);
			jsonObject.put("lastid", lastid);
		}else {
			jsonObject.put("stats", "fail");
			jsonObject.put("code", "200");
			jsonObject.put("message", "");
			jsonObject.put("lastid", lastid);
		}
		return jsonObject;
	}
	
	@Override
	public JSONObject insert(String id, JSONObject object) {
		JSONObject jsonObject = new JSONObject();
		if ("success".equals(object.getString("stats"))) {
			List<List> li =  (List<List>) object.get("list");
			BigDecimal bigDecimal = null ;//轨距
			String lDecimal = null ;//左高程
			String rDecimal = null ;//右高程
			try {
				for (int i = 0; i < li.size(); i++) {
					if ("".equals(li.get(i).get(0))) {
						continue;
					}
					if ("1".equals(li.get(i).get(0))) {
						bigDecimal = new BigDecimal((String) li.get(i).get(2));
					}
					if ("2".equals(li.get(i).get(0))) {
						lDecimal= (String) li.get(i).get(2);
					}
					if ("3".equals(li.get(i).get(0))) {
						rDecimal =(String) li.get(i).get(2);
					}
				}
			} catch (Exception e) {
				jsonObject.put("stats", "fail");
				jsonObject.put("code", "200");
				jsonObject.put("message", e);
				return jsonObject;
			}
			
			int no=0;
			
			/**
			 * 
			 * 轨距
			 */
			List<detectionresult> list = new ArrayList<detectionresult>();
			measurementstandard mea =  imm.selectByPrimaryKey("1");
			String str =  mea.getRange();
			String str1 =  mea.getStandard();
			double d1 = Double.parseDouble(str1);
			if ("+-".equals(str)) {
				double d =  bigDecimal.doubleValue();
				detectionresult detection = new detectionresult();
				if (d <= d1 && d >= (d1*-1)) {
					//合格
					detection.setId(null);
					detection.setMid(id);//测量项目id
					detection.setPname(mea.getMeasurementitem());//相点
					detection.setStandards(str+str1);//标准
					detection.setMeasureddata(d+"");
					detection.setMeasuredresults("合格");
				}else {
					//不合格
					detection.setId(null);
					detection.setMid(id);//测量项目id
					detection.setPname(mea.getMeasurementitem());//相点
					detection.setStandards(str+str1);//标准
					detection.setMeasureddata(d+"");
					detection.setMeasuredresults("不合格");
					no++;
				}
			
				list.add(detection);
			}
			/**
			 * 
			 * 
			 * 水平
			 */
			mea =  imm.selectByPrimaryKey("2");
			str =  mea.getRange();
			str1 =  mea.getStandard();
			d1 = Double.parseDouble(str1);
			if ("<=".equals(str)) {
				detectionresult detection = new detectionresult();
				double d =  DecimalUtil.levelData(lDecimal, rDecimal).doubleValue();
				if (d <= d1) {
					//合格
					detection.setId(null);
					detection.setMid(id);//测量项目id
					detection.setPname(mea.getMeasurementitem());//相点
					detection.setStandards(str+str1);//标准
					detection.setMeasureddata(d+"");
					detection.setMeasuredresults("合格");
				}else {
					//不合格
					detection.setId(null);
					detection.setMid(id);//测量项目id
					detection.setPname(mea.getMeasurementitem());//相点
					detection.setStandards(str+str1);//标准
					detection.setMeasureddata(d+"");
					detection.setMeasuredresults("不合格");
					no++;
				}
				list.add(detection);
			}
			/**
			 * 
			 * 高低
			 */
			mea =  imm.selectByPrimaryKey("3");
			str =  mea.getRange();
			str1 =  mea.getStandard();
			d1 = Double.parseDouble(str1);
			if ("<=".equals(str)) {
				detectionresult detection = new detectionresult();
				double d =  DecimalUtil.HsData(lDecimal, rDecimal).doubleValue();
				if (d <= d1) {
					//合格
					detection.setId(null);
					detection.setMid(id);//测量项目id
					detection.setPname(mea.getMeasurementitem());//相点
					detection.setStandards(str+str1);//标准
					detection.setMeasureddata(d+"");
					detection.setMeasuredresults("合格");
				}else {
					//不合格
					detection.setId(null);
					detection.setMid(id);//测量项目id
					detection.setPname(mea.getMeasurementitem());//相点
					detection.setStandards(str+str1);//标准
					detection.setMeasureddata(d+"");
					detection.setMeasuredresults("不合格");
					no++;
				}
				list.add(detection);
			}
//			int a=0;
//			for (detectionresult detectionresult : list) {
//				a +=  idm.insertSelective(detectionresult);
//			}
			
			
			
			int a = idm.insertSelectives(list);
			measurementproject record = new measurementproject();
			record.setId(Integer.parseInt(id));
			record.setWarningstatistics(no+"");
			
			impm.updateByPrimaryKeySelective(record );
			
			jsonObject.put("stats", "success");
			jsonObject.put("code", "200");
			jsonObject.put("message", "小车数据存储"+a+"条");
		}else {
			jsonObject.put("stats", "fail");
			jsonObject.put("code", "200");
			jsonObject.put("message", "文件处理失败");
		}
		
		
		return jsonObject;
	}
	
	
	@Override
	public JSONObject updatedetectionresult(String id, JSONObject object) {
		
		
		List<detectionresult> detectionresultlist =  idm.selectByMid(id);
		JSONObject jsonObject = new JSONObject();
		if ("success".equals(object.getString("stats"))) {
			List<List> li =  (List<List>) object.get("list");
			BigDecimal bigDecimal = null ;//轨距
			String lDecimal = null ;//左高程
			String rDecimal = null ;//右高程
			try {
				for (int i = 0; i < li.size(); i++) {
					if ("".equals(li.get(i).get(0))) {
						continue;
					}
					if ("1".equals(li.get(i).get(0))) {
						bigDecimal = new BigDecimal((String) li.get(i).get(2));
					}
					if ("2".equals(li.get(i).get(0))) {
						lDecimal= (String) li.get(i).get(2);
					}
					if ("3".equals(li.get(i).get(0))) {
						rDecimal =(String) li.get(i).get(2);
					}
				}
			} catch (Exception e) {
				jsonObject.put("stats", "fail");
				jsonObject.put("code", "200");
				jsonObject.put("message", e);
				return jsonObject;
			}
			
			List<detectionresult> list = new ArrayList<detectionresult>();
			
			
			for (int i = 0; i < detectionresultlist.size(); i++) {
				
				detectionresult detectionresult = detectionresultlist.get(i);
				
				measurementstandard mea =  imm.selectByPrimaryKey(detectionresult.getStandardid());
				String str =  mea.getRange();
				String str1 =  mea.getStandard();
				double d1 = Double.parseDouble(str1);
				if ("+-".equals(str)) {
					double d =  bigDecimal.doubleValue();
					detectionresult detection = new detectionresult();
					if (d <= d1 && d >= (d1*-1)) {
						//合格
						detection.setId(detectionresult.getId());
						detection.setMid(id);//测量项目id
						detection.setPname(mea.getMeasurementitem());//相点
						detection.setStandardid(mea.getId());//相点id
						detection.setStandards(str+str1);//标准
						detection.setMeasureddata(d+"");
						detection.setMeasuredresults("合格");
					}else {
						//不合格
						detection.setId(null);
						detection.setMid(id);//测量项目id
						detection.setPname(mea.getMeasurementitem());//相点
						detection.setStandardid(mea.getId());//相点id
						detection.setStandards(str+str1);//标准
						detection.setMeasureddata(d+"");
						detection.setMeasuredresults("不合格");
					}
					
					list.add(detection);
				}
			}
			
			int a=0;
			for (detectionresult detectionresult : list) {
				a +=  idm.updateByPrimaryKeySelective(detectionresult);
			}
			
			List<detectionresult> lists =  idm.selectbymidresults(id, "不合格");
			measurementproject record = new measurementproject();
			record.setId(Integer.parseInt(id));
			record.setWarningstatistics(lists.size()+"");
			
			impm.updateByPrimaryKeySelective(record);
			
//			int a = idm.insertSelectives(list);
			
			
			jsonObject.put("stats", "success");
			jsonObject.put("code", "200");
			jsonObject.put("message", "小车数据存储"+a+"条");
		}else {
			jsonObject.put("stats", "fail");
			jsonObject.put("code", "200");
			jsonObject.put("message", "文件处理失败");
		}
		
		
		return jsonObject;
	
	}
	
	

	@Override
	public detectionresult selectbyid(String id) {
		detectionresult detectionresult = 	idm.selectByPrimaryKey(id);
		return detectionresult;
	}

	@Override
	public JSONObject selectbyrid(String id) {
		JSONObject jsonObject = new JSONObject();
		List<detectionresult> list =  idm.selectByMid(id);
		
		JSONArray array = new JSONArray();
		
		for (int i = 0; i < list.size(); i++) {
			
			detectionresult dete = list.get(i);
			List<filedatatable> ls =  ifm.getfileByid(Integer.parseInt(dete.getId()));
			JSONArray jsonArray = new JSONArray();
			for (int j = 0; j < ls.size(); j++) {
				filedatatable file = ls.get(j);
				jsonArray.add("/detectionresult/show/"+file.getId());
			}
			
			JSONObject value = new JSONObject();
			value.put("data",dete);
			value.put("jsonArray",jsonArray);
			array.add(value);
		}
		
		jsonObject.put("code", "200");
		jsonObject.put("state", "success");
		jsonObject.put("list", array);
		return jsonObject;
	}

	@Override
	public JSONObject selectbypname(String id,String pname,Integer  pageNum,Integer  pageSize) {
		JSONObject jsonObject = new JSONObject();
		List<detectionresult> list =  idm.selectbypname(id,pname,  pageNum*pageSize,  pageSize);
		
		
		JSONArray array = new JSONArray();
		
		for (int i = 0; i < list.size(); i++) {
			
			detectionresult dete = list.get(i);
			List<filedatatable> ls =  ifm.getfileByid(Integer.parseInt(dete.getId()));
			JSONArray jsonArray = new JSONArray();
			for (int j = 0; j < ls.size(); j++) {
				filedatatable file = ls.get(j);
				jsonArray.add("/detectionresult/show/"+file.getId());
			}
			
			JSONObject value = new JSONObject();
			value.put("data",dete);
			value.put("jsonArray",jsonArray);
			array.add(value);
		}
		
		jsonObject.put("code", "200");
		jsonObject.put("state", "success");
		jsonObject.put("list", array);
		
		
		return jsonObject;
	}
	
	
	
	
	@Override
	public int updatedetectionresult(detectionresult d) {
		int n = idm.updateByPrimaryKeySelective(d);
		List<detectionresult> lists =  idm.selectbymidresults(d.getMid(), "不合格");
		measurementproject record = new measurementproject();
		record.setId(Integer.parseInt(d.getMid()));
		record.setWarningstatistics(lists.size()+"");
		impm.updateByPrimaryKeySelective(record);
		return n;
	}

	@Override
	public List<detectionresult> selectAll(Integer  pageNum,Integer  pageSize) {
		return idm.selectAll(  pageNum*pageSize,  pageSize);
	}

	@Override
	public JSONObject deletebyid(String id) {
		int a =  idm.deleteByPrimaryKey(id);
		JSONObject jsonObject = new JSONObject();
		if (a > 0) {
			jsonObject.put("code","200");
			jsonObject.put("state","success");
			jsonObject.put("msg",a);
		}else {
			jsonObject.put("code","200");
			jsonObject.put("state","fail");
			jsonObject.put("msg",a);
		}
		return jsonObject;
	}

	@Override
	public List<detectionresult> selectbymid(String id) {
		List<detectionresult> list =  idm.selectByMid(id);
		return list;
	}

	

	


	

}
