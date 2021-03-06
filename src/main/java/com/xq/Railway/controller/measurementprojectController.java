package com.xq.Railway.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.xq.Railway.logAop.MethodLog;
import com.xq.Railway.model.JsonResult;
import com.xq.Railway.model.measurementproject;
import com.xq.Railway.service.impl.DetectionresultService;
import com.xq.Railway.service.impl.FiledatatableService;
import com.xq.Railway.service.impl.MeasurementprojectService;
import com.xq.Railway.util.StringUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 
 * 
 *   测量项目
 * 检测项目
 * @author XingPanST
 *
 */
@Api(tags = "检测项目")
@RestController
@RequestMapping("/measurementproject")
public class measurementprojectController {
	private static final Logger LOG = LoggerFactory.getLogger(measurementprojectController.class);
	@Autowired
	private MeasurementprojectService imps;

	@Autowired
	private FiledatatableService ifs;
	
	
	@Autowired
	private DetectionresultService ids;
	
	
	@Value("${springurl.fileurl}")
	private String url;
	/**
	 * 
	 * @param m
	 * @return
	 */
	@ApiOperation(value="新增测量项目", notes="根据measurementproject对象新增测量项目")
	@ApiImplicitParam(name = "m", value = "详细实体measurementproject", required = true, dataType = "measurementproject")
	@RequestMapping(value = "/insert" , method = RequestMethod.POST)
	@MethodLog(remark = "新增测量项目")
	public ResponseEntity<JsonResult> insert(@RequestBody measurementproject m) {
		JsonResult r = new JsonResult();
		try {
			JSONObject result = imps.instert(m);
//			JSONObject jo =  ser.standerd();
			r.setResult(result);
			r.setStatus("ok");
		} catch (Exception e) {
			LOG.error(e.getClass().getName() + ":" + e.getMessage());
			r.setResult(e.getClass().getName() + ":" + e.getMessage());
			r.setStatus("error");
			e.printStackTrace();
		}
		return ResponseEntity.ok(r);
	}
	
	
	/**
	 * 文件上传
	 * @param files
	 * @return
	 */
	@RequestMapping(value = "/multipleSave", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	@MethodLog(remark = "新增项目并上传小车数据")
	public @ResponseBody JSONObject multipleSave(@RequestParam("file") MultipartFile[] files
			,@RequestParam("lid")String lid,@RequestParam("tid")String tid,@RequestParam("aid")String aid,@RequestParam("projectName")String projectName,@RequestParam("id")String id,HttpServletRequest request) {
		
//		administrator admin = (administrator) request.getSession().getAttribute("login");
		
		boolean flag = "".equals(id) || id == null;
		
		if (!flag) {
			JSONObject jsonObject = new JSONObject();
			String realPath = url;   
			System.out.println(realPath);
			measurementproject m = new measurementproject();
			int mid = Integer.parseInt(id);
			m.setId(mid);
			m.setLid(lid);//站点id
			m.setTid(tid);//线路id
			m.setAid(aid);//关联人id
			m.setPname(projectName);//项目名
			JSONObject object = null;
			try {
				object =  StringUtil.fileUpload(files, realPath, ifs);
			} catch (Exception e) {
				LOG.error(e.getClass().getName() + ":" + e.getMessage());
				jsonObject.put("stats", "fail"); 
				jsonObject.put("code", "500");
				jsonObject.put("message", e);
				return jsonObject;
			}
			
			if (object != null) {
				JSONArray array =  object.getJSONArray("list");
				String rAltitude = null;
				String lAltitude = null;
				String distance = null;
				for (int i = 0; i < array.size(); i++) {
					JSONArray array2 = array.getJSONArray(i);
					if ("轨距".equals(array2.get(1))) {
						distance = (String) array2.get(2);
					}
					if ("左高程".equals(array2.get(1))) {
						lAltitude = (String) array2.get(2);
					}
					if ("右高程".equals(array2.get(1))) {
						rAltitude = (String) array2.get(2);
					}
				}
				m.setlAltitude(lAltitude);
				m.setrAltitude(rAltitude);
				m.setDistance(distance);
			}
			int  n =  imps.updatemeasurementproject(m);
			if (n > 0) {
				jsonObject.put("stats", "success");
				jsonObject.put("code", "200");
				jsonObject.put("message", "");
			}else {
				jsonObject.put("stats", "fail");
				jsonObject.put("code", "200");
				jsonObject.put("message", "");
			}
			
			if (object != null) {
				JSONObject object2 =  ids.insert(id, object);
				jsonObject.put("fileSave", object2);
			}
			return jsonObject;
		}
		
		JSONObject jsonObject = new JSONObject();
		String realPath = url;   
		System.out.println(realPath);
		measurementproject m = new measurementproject();
		m.setLid(tid);//站点id
		m.setTid(lid);//线路id
		m.setAid(aid);
		m.setPname(projectName);//项目名
		JSONObject object = null;
		try {
			object =  StringUtil.fileUpload(files, realPath, ifs);
		} catch (Exception e) {
			LOG.error(e.getClass().getName() + ":" + e.getMessage());
			jsonObject.put("stats", "fail"); 
			jsonObject.put("code", "500");
			jsonObject.put("message", e);
			return jsonObject;
		}
		
		if (object != null) {
		JSONArray array =  object.getJSONArray("list");
			
			String rAltitude = null;
			String lAltitude = null;
			String distance = null;
			for (int i = 0; i < array.size(); i++) {
				JSONArray array2 = array.getJSONArray(i);
				if ("轨距".equals(array2.get(1))) {
					distance = (String) array2.get(2);
				}
				if ("左高程".equals(array2.get(1))) {
					lAltitude = (String) array2.get(2);
				}
				if ("右高程".equals(array2.get(1))) {
					rAltitude = (String) array2.get(2);
				}
			}
			m.setlAltitude(lAltitude);
			m.setrAltitude(rAltitude);
			m.setDistance(distance);
		}
		JSONObject jsonObject2 =  imps.instert(m);
		if (object != null) {
			JSONObject object2 =  ids.insert(jsonObject2.getJSONObject("project").getString("aid"), object);
			jsonObject2.put("fileSave", object2);
		}
		return jsonObject2;
	}
	
	
	
	
	/**
	 * 
	 *  查询单个项目    
	 * 
	 * @param rid    
	 * @return
	 */
	@RequestMapping(value = "/selectbyid" , method = RequestMethod.GET)
	@MethodLog(remark = "查看单个项目")
	public measurementproject selectbyid(String rid) {
		measurementproject ma = null;
		try {
			ma = imps.selectbyid(rid);
		} catch (Exception e) {
			LOG.error(e.getClass().getName() + ":" + e.getMessage());
			e.printStackTrace();
		}
		return ma;
		
	}
	/**
	 *
	 *
	 *  根据id 修改 站点信息
	 *    
	 */
	@RequestMapping(value = "/updatebyid" , method = RequestMethod.POST)
	@MethodLog(remark = "更新项目信息")
	public int updatebyid(@RequestBody measurementproject m) {
		int ma = 0;
		try {
			ma = imps.updatemeasurementproject(m);
		} catch (Exception e) {
			LOG.error(e.getClass().getName() + ":" + e.getMessage());
			e.printStackTrace();
		}
		return ma;
	}
	
	
	@RequestMapping(value = "/delete" , method = RequestMethod.GET)
	@MethodLog(remark = "删除项目")
	public int deletebyid(String id) {
		int ma = 0;
		try {
			ma = imps.deletebyid(id);
		} catch (Exception e) {
			LOG.error(e.getClass().getName() + ":" + e.getMessage());
			e.printStackTrace();
		}
		return ma;
	}
	
	/**
	 * 
	 *   查询所有项目
	 * @return
	 */
	@RequestMapping(value = "/selectAll" , method = RequestMethod.GET)
	@MethodLog(remark = "查询所有项目")
	public JSONObject  selectAll(Integer  pageNum,Integer  pageSize) {
		JSONObject ma = null;
		try {
			ma = imps.selectAll(pageNum, pageSize);
		} catch (Exception e) {
			LOG.error(e.getClass().getName() + ":" + e.getMessage());
			e.printStackTrace();
		}
		return ma;
	}
	
	/**
	 * 
	 * 
	 * 根据站点id 查询项目
	 * @param mid
	 * @return
	 */
	@RequestMapping(value = "/selectBymid" , method = RequestMethod.GET)
	@MethodLog(remark = "查询站点下 所有项目信息")
	public JSONObject  selectBymid(String mid,String  pageNum,String pageSize) {
		
		JSONObject ma = null;
		try {
			ma = imps.selectBymid(mid, Integer.parseInt(pageNum),Integer.parseInt (pageSize));
		} catch (Exception e) {
			LOG.error(e.getClass().getName() + ":" + e.getMessage());
			e.printStackTrace();
		}
		return ma;
	}
	
	/**
	 * 
	 * 根据项目名 模糊查询 项目
	 * @param mName
	 * @return
	 */
	@RequestMapping(value = "/selectByName" , method = RequestMethod.GET)
	@MethodLog(remark = "项目名查询项目信息")
	public JSONObject  selectByName(String mName,Integer  pageNum,Integer  pageSize) {
		JSONObject ma = null;
		try {
			ma = imps.selectByName(mName, pageNum, pageSize);
		} catch (Exception e) {
			LOG.error(e.getClass().getName() + ":" + e.getMessage());
			e.printStackTrace();
		}
		return ma;
	}
	
	/**
	 * 站点id 项目名查询  三个页面查询
	 * 
	 * @param id
	 * @param mName
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/selectBy" , method = RequestMethod.GET)
	@MethodLog(remark = "根据id和项目名查询项目信息")
	public JSONObject  selectBy(String id,String mName,Integer  pageNum,Integer  pageSize) {
		JSONObject ma = null;
		try {
			ma = imps.selectBy(id,mName, pageNum, pageSize);
		} catch (Exception e) {
			LOG.error(e.getClass().getName() + ":" + e.getMessage());
			e.printStackTrace();
		}
		return ma;
	}
	/**
	 * 
	 * 查询站点下 所有项目名
	 * @param id
	 * @return
	 */
	
	@RequestMapping(value = "/selectAllpName" , method = RequestMethod.GET)
	public JSONObject  selectAllpName(String id) {
		JSONObject ma = null;
		try {
			ma = imps.selectAllpName(id);
		} catch (Exception e) {
			LOG.error(e.getClass().getName() + ":" + e.getMessage());
			e.printStackTrace();
		}
		return ma;
	}
	
	
	/**
	 * 
	 * 获取首页 柱状图 id为 站点id
	 * http://localhost:8089/measurementproject/GetHomeECharts?id=37
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/GetHomeECharts" , method = RequestMethod.GET)
	@MethodLog(remark = "获取首页 柱状图 id为 站点id")
	public JSONObject GetHomeECharts(String id) {
		JSONObject ma = null;
		try {
			ma = imps.GetHomeECharts(id);
		} catch (Exception e) {
			LOG.error(e.getClass().getName() + ":" + e.getMessage());
			e.printStackTrace();
		}
		return ma;
	}
	/**
	 * 
	 * 获取统计页面 曲线图 id为 线路id
	 * http://localhost:8089/measurementproject/GetPageECharts?id=44
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/GetPageECharts" , method = RequestMethod.GET)
	@MethodLog(remark = "获取统计页面 曲线图 id为 线路id")
	public JSONObject GetPageECharts(String id) {
		JSONObject ma = null;
		try {
			ma = imps.GetPageECharts(id);
		} catch (Exception e) {
			LOG.error(e.getClass().getName() + ":" + e.getMessage());
			e.printStackTrace();
		}
		return ma;
	}
	
	
	
	
	
	
}
