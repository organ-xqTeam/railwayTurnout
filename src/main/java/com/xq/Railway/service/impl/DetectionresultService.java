package com.xq.Railway.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xq.Railway.dao.detectionresultMapper;
import com.xq.Railway.dao.filedatatableMapper;
import com.xq.Railway.dao.gaugestandardMapper;
import com.xq.Railway.dao.measurementprojectMapper;
import com.xq.Railway.dao.measurementstandardMapper;
import com.xq.Railway.model.detectionresult;
import com.xq.Railway.model.filedatatable;
import com.xq.Railway.model.gaugestandard;
import com.xq.Railway.model.measurementproject;
import com.xq.Railway.model.measurementstandard;
import com.xq.Railway.util.DecimalUtil;
import com.xq.Railway.util.MapTrunPojo;
import com.xq.Railway.util.algorithm;
import com.xq.Railway.util.jsonTomodel;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Service
public class DetectionresultService {
	
	@Autowired
	private detectionresultMapper idm;
	
	@Autowired
	private  measurementstandardMapper imm;
	
	@Autowired
	private  measurementprojectMapper impm;
	@Autowired
	private  gaugestandardMapper gaugmapper;
	
	@Autowired
	private filedatatableMapper ifm;
	
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
			String str =  mea.getRanges();
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
			str =  mea.getRanges();
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
			str =  mea.getRanges();
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
				String str =  mea.getRanges();
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
	
	

	
	public detectionresult selectbyid(String id) {
		detectionresult detectionresult = 	idm.selectByPrimaryKey(id);
		return detectionresult;
	}

	
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
	
	
	
	
	
	public int updatedetectionresult(detectionresult d) {
		int n = idm.updateByPrimaryKeySelective(d);
		List<detectionresult> lists =  idm.selectbymidresults(d.getMid(), "不合格");
		measurementproject record = new measurementproject();
		record.setId(Integer.parseInt(d.getMid()));
		record.setWarningstatistics(lists.size()+"");
		impm.updateByPrimaryKeySelective(record);
		return n;
	}

	
	public List<detectionresult> selectAll(Integer  pageNum,Integer  pageSize) {
		return idm.selectAll(pageNum*pageSize,pageSize);
	}

	
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

	
	public List<detectionresult> selectbymid(String id) {
		List<detectionresult> list =  idm.selectByMid(id);
		return list;
	}

	
	public JSONObject instertnew(detectionresult m) {
		JSONObject jo = new JSONObject();
		
		//查询项点 计算规则
		measurementstandard measurement = imm.selectByPrimaryKey(m.getPname());
		String state =  measurement.getState();
		if ("0".equals(state)) {
			//手动判断
			
			int n =  idm.insertSelective(m);
			if (n> 0 ) {
				jo.put("r", n);
				jo.put("s", "ok");
				return jo;
			}
			
		}else if("1".equals(state)) {
			//系统判断
			//为轨距 有详细计算标准
			//获取详细计算标准
			String isdetails = measurement.getIsdetails();
			if ("0".equals(isdetails)) {
				//没有详细计算标准--不是轨距
				String range1 = measurement.getRange1();
				//结果的 数字
				BigDecimal b1 = new BigDecimal(measurement.getRanges());
				//计算的数字
				List<BigDecimal> bigDecimals = new ArrayList<BigDecimal>();
				BigDecimal big = new BigDecimal(measurement.getRanges());
				BigDecimal big2 = new BigDecimal(measurement.getStandard());
				bigDecimals.add(big);
				bigDecimals.add(big2);
				String res = algorithm.check(range1, b1, bigDecimals);
				
				m.setMeasuredresults(res);
				int n =  idm.insertSelective(m);
				if (n> 0 ) {
					jo.put("r", n);
					jo.put("s", "ok");
					return jo;
				}
			}else if("1".equals(isdetails)) {
				
				String gaugestandardid= measurement.getGaugestandardid(); 
				//有详细计算标准
				List<gaugestandard> gaug = gaugmapper.selectByturnoutid(gaugestandardid);
				
				String measureddata1 = m.getMeasureddata1();
				
				String gauge = "";
				for (int i = 0; i < gaug.size(); i++) {
					if (measureddata1.equals(gaug.get(i).getMileage())) {
						gauge = gaug.get(i).getGauge();
						break;
					}
				}
				
				String range1 = measurement.getRange1();
				
				//结果的 数字
				BigDecimal b1 = new BigDecimal(measurement.getRanges());
				List<BigDecimal> bigDecimals = new ArrayList<BigDecimal>();
				BigDecimal big = new BigDecimal(gauge);
				bigDecimals.add(big);
				String res = algorithm.check(range1, b1, bigDecimals);
				m.setMeasuredresults(res);
				int n =  idm.insertSelective(m);
				if (n> 0 ) {
					jo.put("r", n);
					jo.put("s", "ok");
					return jo;
				}
			}else {
					jo.put("r", isdetails+"：状态不对");
					jo.put("s", "error");
					return jo;
			}
		}else {
			jo.put("r", state+"：状态不对"+m.getPname()+":参数不存在");
			jo.put("s", "error");
			return jo;
		}
		return jo;
	}

	
	public JSONObject insertnewcar(String urlname , String mid) {
		
		List<detectionresult> detectionresults = new ArrayList<detectionresult>();
		//获取 对比规则
		List<measurementstandard> jsono = imm.selectAll1(0, 10);
		
		String str1 = "";//轨距
		String strid1 = "";//轨距 项点id
		String str2 = "";//水平
		String strid2 = "";//水平 项点id
		String str3 = "";//高低
		String strid3 = "";//高低 项点id
		String str4 = "";//方向
		String strid4 = "";//方向 项点id
		
		String str1id="";//轨距对应详细算法id
		
		for (int i = 0; i < jsono.size(); i++) {
			if ("轨距".equals(jsono.get(i).getMeasurementitem())) {
				str1 = (String) jsono.get(i).getRanges();
				str1id = jsono.get(i).getState();
				strid1 = jsono.get(i).getId();
			}else if ("水平".equals(jsono.get(i).getMeasurementitem())) {
				str2 = (String) jsono.get(i).getRanges();
				strid2 = jsono.get(i).getId();
			}else if ("高低".equals(jsono.get(i).getMeasurementitem())) {
				str3 = (String) jsono.get(i).getRanges();
				strid3 = jsono.get(i).getId();
			}else if ("方向".equals(jsono.get(i).getMeasurementitem())) {
				str4 = (String) jsono.get(i).getRanges();
				strid4 = jsono.get(i).getId();
			}else {
				continue;
			}
		}
		
		BigDecimal bigDecimal1 = new BigDecimal(str1);//轨距
		BigDecimal bigDecimal2 = new BigDecimal(str2);//水平
		BigDecimal bigDecimal3 = new BigDecimal(str3);//高低
		BigDecimal bigDecimal4 = new BigDecimal(str4);//方向
		
		
		
		List<String[]> li = jsonTomodel.readCSV(urlname);
		
		JSONArray array1 = new JSONArray();//轨距
		JSONArray array2 = new JSONArray();//水平
		JSONArray array3 = new JSONArray();//高低
		JSONArray array4 = new JSONArray();//方向
		JSONArray array5 = new JSONArray();//方向
		int isnot1=0;//轨距 合格数量
		int isnot2=0;//水平 合格数量
		int isnot3=0;//高低 合格数量
		int isnot4=0;//方向 合格数量
		
		String title[] = li.get(0);
		
		String mileage = "";
		String num = "";
		
		
		
		for (int i = 1; i < li.size(); i++) {
			
			String[] str = li.get(i);
			if ("".equals(str[0].trim())|| str[0].trim().length() <= 0) {
				break;
			}
			
			boolean b1 = false;
			boolean b2 = false;
			
			for (int j = 0; j < title.length; j++) {
				if ("".equals(title[j].trim())|| title.length <= 0) {
					continue;
				}
				
				if ("轨距".equals(title[j]) || "里程".equals(title[j])) {
					if ("轨距".equals(title[j]) ) {
						b1 = true;
						num = str[j];
					}
					if ("里程".equals(title[j]) ) {
						b2 = true;
						mileage = str[j];
					}
					if (b2&&b1) {
						JSONObject j1 = new JSONObject();
						
						j1.put("num", num);
						List<BigDecimal> bigDecimals = new ArrayList<BigDecimal>();
						
						List<gaugestandard> gaug = gaugmapper.selectByturnoutid(str1id);
						
						String gauge = "";
						for (int i1 = 0; i1 < gaug.size(); i1++) {
							if (mileage.equals(gaug.get(i1).getMileage())) {
								gauge = gaug.get(i1).getGauge();
								break;
							}
						}
						
						bigDecimals.add(new BigDecimal(gauge));
						String ss1 = algorithm.check("1",new BigDecimal(num),bigDecimals);
						j1.put("sitename", ss1);
						if ("合格".equals(ss1)) {
							isnot1++;
						}
						
						detectionresult de = new detectionresult();
						de.setMeasureddata(num);
						de.setMeasureddata1(gauge);
						de.setMeasuredresults(ss1);
						de.setMid(mid);
						de.setPname(strid1);
						detectionresults.add(de);
						array1.add(j1);//轨距
					}
					
				}else if("水平".equals(title[j])) {
					JSONObject j2 = new JSONObject();
					j2.put("num", str[j]);
					
					List<BigDecimal> bigDecimals = new ArrayList<BigDecimal>();
					bigDecimals.add(bigDecimal2);
					String ss2 = algorithm.check("2",new BigDecimal(str[j]),bigDecimals);
					if ("合格".equals(ss2)) {
						isnot2++;
					}
					j2.put("sitename", ss2);
					
					detectionresult de = new detectionresult();
					de.setMeasureddata(num);
					de.setMeasuredresults(ss2);
					de.setMid(mid);
					de.setPname(strid2);
					detectionresults.add(de);
					
					array2.add(j2);//水平
				}else if("高低".equals(title[j])) {
					
					JSONObject j3 = new JSONObject();
					j3.put("num", str[j]);
					List<BigDecimal> bigDecimals = new ArrayList<BigDecimal>();
					bigDecimals.add(bigDecimal3);
					String ss3 = algorithm.check("2",new BigDecimal(str[j]),bigDecimals);
					j3.put("sitename", ss3);
					if ("合格".equals(ss3)) {
						isnot3++;
					}
					detectionresult de = new detectionresult();
					de.setMeasureddata(num);
					de.setMeasuredresults(ss3);
					de.setMid(mid);
					de.setPname(strid3);
					detectionresults.add(de);
					array3.add(j3);//高低
				}else if("方向".equals(title[j])) {
					
					JSONObject j4 = new JSONObject();
					j4.put("num", str[j]);
					List<BigDecimal> bigDecimals = new ArrayList<BigDecimal>();
					bigDecimals.add(bigDecimal4);
					String ss4 = algorithm.check("2",new BigDecimal(str[j]),bigDecimals);
					j4.put("sitename", ss4);
					if ("合格".equals(ss4)) {
						isnot4++;
					}
					detectionresult de = new detectionresult();
					de.setMeasureddata(num);
					de.setMeasuredresults(ss4);
					de.setMid(mid);
					de.setPname(strid4);
					detectionresults.add(de);
					array4.add(j4);//高低
				}else {
					JSONObject o5 = new JSONObject();
					o5.put("title", title[j]);
					o5.put("num", j == str.length ?"-":str[j]);
					o5.put("sitename", "-");
					array5.add(o5);
				}
			}
		}
		
		int n = idm.insertSelectives(detectionresults);
		
		JSONObject jo = new JSONObject();
		if (n > 0 ) {
			jo.put("r", n);
			jo.put("s","ok");
		}else {
			jo.put("r", n);
			jo.put("s","error");
		}
		return jo;
	}

	public JSONObject selsctbymeasurementprojectid(String id) {
		JSONObject jsonObject = new JSONObject();
		JSONArray array = new JSONArray();
		
		// id 为项目id
		//获取 项目中对应测量标准
		measurementproject measurement = impm.selectByPrimaryKey(id);
//		获取测量标准对应 测量项点
		List<measurementstandard> list = imm.findbyturnoutstandardAll(measurement.getTurnoutstandardid());
		
		for (measurementstandard measurementstandard : list) {
			//项点id
			
			Map<String, Object> ma =  MapTrunPojo.object2Map(measurementstandard);
			List<detectionresult> la = idm.selectbymidpname(id, measurementstandard.getId());
			
			int a = 0;
			
			for (detectionresult detection : la) {
				a++;
			}
			
			ma.put("s1", a);
			ma.put("s2", a);
			ma.put("s3", 0);
			
			array.add(ma);
		}
		

		jsonObject.put("r", array);
		jsonObject.put("s", "ok");
		return jsonObject;
	}
	
	
}
