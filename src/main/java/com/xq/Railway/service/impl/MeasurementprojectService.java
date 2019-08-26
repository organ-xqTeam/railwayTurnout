package com.xq.Railway.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.xq.Railway.dao.detectionresultMapper;
import com.xq.Railway.dao.gaugestandardMapper;
import com.xq.Railway.dao.linesiteMapper;
import com.xq.Railway.dao.measurementprojectMapper;
import com.xq.Railway.dao.measurementstandardMapper;
import com.xq.Railway.dao.turnoutstandardMapper;
import com.xq.Railway.model.detectionresult;
import com.xq.Railway.model.gaugestandard;
import com.xq.Railway.model.linesite;
import com.xq.Railway.model.measurementproject;
import com.xq.Railway.model.measurementstandard;
import com.xq.Railway.model.turnoutstandard;
import com.xq.Railway.util.StringUtil;
import com.xq.Railway.util.algorithm;
import com.xq.Railway.util.jsonTomodel;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Service
public class MeasurementprojectService {
	
	@Autowired
	private detectionresultMapper idm;
	@Autowired
	private measurementprojectMapper imm;
	@Autowired
	private measurementstandardMapper measurement;
	@Autowired
	private turnoutstandardMapper mapper;
	@Autowired
	private  gaugestandardMapper gaugmapper;
	@Autowired
	private linesiteMapper ilm;
	
	
	@Value("${springurl.fileurl}")
	private String url;
	/**
	 * 
	 * 
	 *  新增测量项目 
	 */
	
	public JSONObject instert(measurementproject m) {
		JSONObject jsonObject = new JSONObject();
		
		String turnoutstandardid = m.getTurnoutstandardid();
		
		
		m.setPtime(StringUtil.Dateform(new Date()));
		m.setWarningstatistics("0");
		Integer a = imm.insertSelective(m);
		int aid =  imm.selectLastId();
		
		List<measurementstandard> ls = measurement.findbystandardAll1(turnoutstandardid);
		
		
		
		
		measurementstandard m1 = null;//轨距
		measurementstandard m2 = null;//水平
		measurementstandard m3 = null;//高低
		measurementstandard m4 = null;//方向
		
//		String str1 = "";//轨距
//		String strid1 = "";//轨距 项点id
//		String str2 = "";//水平
//		String strid2 = "";//水平 项点id
//		String str3 = "";//高低
//		String strid3 = "";//高低 项点id
//		String str4 = "";//方向
//		String strid4 = "";//方向 项点id
		
		for (measurementstandard measurementstandard : ls) {
			if ("1".equals(measurementstandard.getDcbh())) {
				m1 = measurementstandard;
//				str1 = measurementstandard.getRanges();
//				strid1 = measurementstandard.getId();
			} else if("2".equals(measurementstandard.getDcbh())) {
				m2 = measurementstandard;
//				str2 = measurementstandard.getRanges();
//				strid2 = measurementstandard.getId();
			} else if("3".equals(measurementstandard.getDcbh())) {
				m3 = measurementstandard;
//				str3 = measurementstandard.getRanges();
//				strid3 = measurementstandard.getId();
			} else if("4".equals(measurementstandard.getDcbh())) {
				m4 = measurementstandard;
//				str4 = measurementstandard.getRanges();
//				strid4 = measurementstandard.getId();
			}else {
				continue;
			}
		}
		
//		String state 
		
		if (m.getFile() == null ||"".equals( m.getFile())) {
			
		}else {
			
			String urlname = url+m.getFile();
			List<String[]> li = jsonTomodel.readCSV(urlname);
			
			
 			List<gaugestandard> gaug = gaugmapper.selectByturnoutid(m1.getId());
			
			for (int i = 1; i < li.size(); i++) {
				String[] sr = li.get(i);
//				System.out.println(sr[0]);
				if (sr.length <  8 ) {
					break;
				}
//				sr[1];//轨枕编号
//				sr[2];//里程
//				sr[3];//轨距
//				sr[4];//左
//				sr[5];//右
//				sr[6];//方向
//				sr[7];//高低
//				sr[8];//水平
				
				//轨距start
				//有详细计算标准
				String measureddata1 = sr[1];
				String gauge = "";
				
				//通过轨枕编号锁定 计算的
				for (int i1 = 0; i1 < gaug.size(); i1++) {
					int as1 = Integer.parseInt(measureddata1);
					int as2 = Integer.parseInt(gaug.get(i1).getRownum());
					
					if (as1 == as2) {
						gauge = gaug.get(i1).getGauge();
						break;
					}
				}
				
				String range1 = m1.getRange1();
				//结果的 数字
				double a1 = Double.parseDouble(sr[3]);
				double a2 = Double.parseDouble(gauge);
				
				BigDecimal b1 = new BigDecimal((double)(a1-a2));
				List<BigDecimal> bigDecimals = new ArrayList<BigDecimal>();
				BigDecimal big = new BigDecimal(m1.getRanges());
				bigDecimals.add(big);
				String res = algorithm.check(range1, b1, bigDecimals);
				detectionresult re = new detectionresult();
				re.setMid(aid+"");//项目id
				re.setStandardid(m1.getId());
				re.setPname(m1.getMeasurementitem());
				re.setMeasureddata(sr[3]);
				re.setMeasureddata1(sr[1]);
				re.setMeasuredresults(res);
				int n =  idm.insertSelective(re);
				//轨距end
				
				//水平start
				//没有详细计算标准--不是轨距
				range1 = m2.getRange1();
				//结果的 数字
				b1 = new BigDecimal(sr[8]);
				//计算的数字
				bigDecimals = new ArrayList<BigDecimal>();
				big = new BigDecimal(m2.getRanges());
//				BigDecimal big2 = new BigDecimal(m2.getStandard());
				bigDecimals.add(big);
//				bigDecimals.add(big2);
				res = algorithm.check(range1, b1, bigDecimals);
				re.setMid(aid+"");//项目id
				re.setStandardid(m2.getId());
				re.setPname(m2.getMeasurementitem());
				re.setMeasureddata(sr[8]);
				re.setMeasuredresults(res);
				n =  idm.insertSelective(re);
				//水平end
				
				//高低start
				//没有详细计算标准--不是轨距
				range1 = m3.getRange1();
				//结果的 数字
				b1 = new BigDecimal(sr[7]);
				//计算的数字
				bigDecimals = new ArrayList<BigDecimal>();
				big = new BigDecimal(m3.getRanges());
//				big2 = new BigDecimal(m3.getStandard());
				bigDecimals.add(big);
//				bigDecimals.add(big2);
				res = algorithm.check(range1, b1, bigDecimals);
				re.setMid(aid+"");//项目id
				re.setStandardid(m3.getId());
				re.setPname(m3.getMeasurementitem());
				re.setMeasureddata(sr[7]);
				re.setMeasuredresults(res);
				n =  idm.insertSelective(re);
				//高低end
				
				
				//方向start
				//没有详细计算标准--不是轨距
				range1 = m4.getRange1();
				//结果的 数字
				b1 = new BigDecimal(sr[6]);
				//计算的数字
				bigDecimals = new ArrayList<BigDecimal>();
				big = new BigDecimal(m4.getRanges());
//				big2 = new BigDecimal(m4.getStandard());
				bigDecimals.add(big);
//				bigDecimals.add(big2);
				res = algorithm.check(range1, b1, bigDecimals);
				re.setMid(aid+"");//项目id
				re.setStandardid(m4.getId());
				re.setPname(m4.getMeasurementitem());
				re.setMeasureddata(sr[6]);
				re.setMeasuredresults(res);
				n =  idm.insertSelective(re);
				//方向end
			}
		}
		
		
		
		m.setAid(aid+"");
		if (a > 0) {
			jsonObject.put("stats", "success");
			jsonObject.put("code", "200");
			jsonObject.put("message", "");
			jsonObject.put("project", m);
		}else {
			jsonObject.put("stats", "fail");
			jsonObject.put("code", "500");
			jsonObject.put("message", "");
			jsonObject.put("project", m);
		}
		return jsonObject;
	}
	
	
	public measurementproject selectbyid(String id) {
		measurementproject measurementproject = imm.selectByPrimaryKey(id);
		return measurementproject;
	}

	
	public int updatemeasurementproject(measurementproject m) {
		int a = imm.updateByPrimaryKeySelective(m);
		return a;
	}

	
	public int deletebyid(String id) {
		int a  = imm.deleteByPrimaryKey(id);
		return a;
	}

	
	public JSONObject selectAll(int pageNum,int pageSize) {
		List<measurementproject> list  = imm.selectAll( pageNum*pageSize, pageSize);
		List<measurementproject> listCount  = imm.selectAllCount();
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("list", list);
		jsonObject.put("listCount", listCount.size());
		return jsonObject;
	}

	
	public JSONObject selectBymid(String mid,int pageNum,int pageSize) {
		List<measurementproject> list  = imm.selectbymid(mid, pageNum*pageSize, pageSize);
		List<measurementproject> listCount  = imm.selectbymidCount(mid);
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("code", "200");
		jsonObject.put("state", "success");
		jsonObject.put("list", list);
		jsonObject.put("listCount", listCount.size());
		return jsonObject;
	}

	
	public JSONObject selectByName(String mName,int pageNum,int pageSize) {
		List<measurementproject> list  = imm.selectByName(mName, pageNum*pageSize, pageSize);
		List<measurementproject> listCount  = imm.selectByNameCount(mName);
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("list", list);
		jsonObject.put("listCount", listCount.size());
		return jsonObject;
	}

	
	public JSONObject selectBy(String id, String mName, int pageNum, int pageSize) {
		if ("".equals(mName)) {
			mName = null;
		}
		List<Map> list  = imm.selectBy(id,mName, pageNum*pageSize, pageSize);
		List<Map> listCount  = imm.selectByCount(id,mName);
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("list", list);
		jsonObject.put("listCount", listCount.size());
		return jsonObject;
	}
	/**
	 * 
	 * 查询站点下所有项目民
	 */
	
	public JSONObject selectAllpName(String id) {
		List<String> list  = imm.selectAllpName(id);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("list", list);
		return jsonObject;
	}

	
	public JSONObject GetHomeECharts(String id) {
		List<Map> listm =  imm.selectGetEchar(Integer.parseInt(id));
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("listm", listm);
		return jsonObject;
	}

	
	public JSONObject GetPageECharts(String id) {
		List<Map> listm =  imm.GetPageECharts(Integer.parseInt(id));
		
		Set<String> set  = new HashSet<String>();
		for (int i = 0; i < listm.size(); i++) {
			Map map = listm.get(i);
			String lid =  (String) map.get("lid");
			set.add(lid);
		}
		
		JSONArray array = new JSONArray();
		
		Iterator<String> it =  set.iterator();
		while (it.hasNext()) {
			String st = (String) it.next();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("lid", st);
			linesite site =  ilm.selectByPrimaryKey(st);
			jsonObject.put("lidname", site.getSitename());
			
			JSONArray jsonArray = new JSONArray();
			for (int i = 0; i < listm.size(); i++) {
				Map map = listm.get(i);
				String lid =  (String) map.get("lid");
				if (lid.equals(st)) {
					jsonArray.add(map);
				}
			}
			jsonObject.put("jsonArray", jsonArray);
			array.add(jsonObject);
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("listm", array);
		return jsonObject;
	}

}
