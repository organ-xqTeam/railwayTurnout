package com.xq.Railway.controller;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.xq.Railway.logAop.MethodLog;
import com.xq.Railway.model.JsonResult;
import com.xq.Railway.model.detectionresult;
import com.xq.Railway.model.filedatatable;
import com.xq.Railway.service.impl.DetectionresultService;
import com.xq.Railway.service.impl.FiledatatableService;
import com.xq.Railway.service.impl.MeasurementstandardService;
import com.xq.Railway.util.jsonTomodel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 * 测量结果
 * @author XingPanST
 *
 */
@Api(tags = "测量结果")
@RestController
@RequestMapping("/detectionresult")
public class detectionresultController {
	private static final Logger LOG = LoggerFactory.getLogger(detectionresultController.class);
	@Autowired
	private DetectionresultService ids;
	
	@Autowired
	private FiledatatableService ifs;
	
	@Autowired
	private MeasurementstandardService imss;
	
	@Value("${springurl.fileurl}")
	private String url;
	
	@ApiOperation(value="根据项目查看监测结果", notes="根据measurementprojectid项目查看监测结果")
	@ApiImplicitParam(name = "id", value = "项目id")
	@RequestMapping(value = "/getresultbyprojectid" , method = RequestMethod.GET)
	@MethodLog(remark = "根据项目查看监测结果")
	public ResponseEntity<JsonResult> selsctbymeasurementprojectid(String id) {
		JsonResult r = new JsonResult();
		try {
			JSONObject result = ids.selsctbymeasurementprojectid(id);
			r.setResult(result.getString("r"));
			r.setStatus(result.getString("s"));
		} catch (Exception e) {
			LOG.error(e.getClass().getName() + ":" + e.getMessage());
			r.setResult(e.getClass().getName() + ":" + e.getMessage());
			r.setStatus("error");
			e.printStackTrace();
		}
		return ResponseEntity.ok(r);
	}
	
	
	
	
	
	
	
	
	/**
	 * 新增 结果   添加上传文件
	 * 
	 * @param m
	 * @return
	 */
//	@ApiOperation(value="新增项点测量结果", notes="根据detectionresult对象 新增 测量结果")
//	@ApiImplicitParam(name = "m", value = "详细实体detectionresult", required = true, dataType = "detectionresult")
	@RequestMapping(value = "/insert" , method = RequestMethod.POST)
	@MethodLog(remark = "新增项点测量结果")
	public JSONObject insert(detectionresult m,@RequestParam("file") MultipartFile[] files
			,HttpServletRequest request) {
		JSONObject result = ids.instert(m);
		
		if (!"success".equals(result.getString("stats"))) {
			return result;
		}
		JSONObject jsonObject = new JSONObject();
		String realPath = url;   
		String fileName = null;
		List<filedatatable> list = new ArrayList<filedatatable>();
		try {
			if (files != null && files.length > 0) {
				for (int i = 0; i < files.length; i++) {
					fileName = files[i].getOriginalFilename();
					String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
						byte[] bytes = files[i].getBytes();
						String oldName = fileName;
						fileName = new Date().getTime() + "_" + new Random().nextInt(1000) + "." + prefix;// 新的文件名
						BufferedOutputStream buffStream = new BufferedOutputStream(
								new FileOutputStream(new File(realPath + fileName)));
						buffStream.write(bytes);
						buffStream.close();
						filedatatable d = new  filedatatable();
						d.setOriginName(oldName);
						d.setThumb(fileName);
						d.setCreatetime(new Date().getTime()+"");
						d.setFilepath(realPath + fileName);
						d.setType(prefix);
						d.setAbbreviatedName(result.getString("lastid"));
						list.add(d);
					}
				JSONObject object =  ifs.instert(list);
				object.put("result", result);
				return object;
				}
		}catch (Exception e) {
			LOG.error(e.getClass().getName() + ":" + e.getMessage());
			jsonObject.put("stats", "fail");
			jsonObject.put("code", "500");
			jsonObject.put("message", e);
			
			return jsonObject;
		}
	
		jsonObject.put("stats", "success");
		jsonObject.put("code", "200");
		jsonObject.put("message", "");
		jsonObject.put("result", result);
		return jsonObject;
	}
	@ApiOperation(value="新增项点测量结果", notes="根据detectionresult对象 新增 测量结果")
	@ApiImplicitParam(name = "m", value = "详细实体detectionresult", required = true, dataType = "detectionresult")
	@RequestMapping(value = "/insertnew" , method = RequestMethod.POST)
	@MethodLog(remark = "新增项点测量结果")
	public ResponseEntity<JsonResult> insertnew(@RequestBody  detectionresult m) {
		JsonResult r = new JsonResult();
		try {
			JSONObject result = ids.instertnew(m);
			r.setResult(result.getString("r"));
			r.setStatus(result.getString("s"));
		} catch (Exception e) {
			LOG.error(e.getClass().getName() + ":" + e.getMessage());
			r.setResult(e.getClass().getName() + ":" + e.getMessage());
			r.setStatus("error");
			e.printStackTrace();
		}
		return ResponseEntity.ok(r);
	}
	@ApiOperation(value="新增小车项点测量结果", notes="根据选择的小车文件新增测量结果")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "urlname", value = "小车文件名",  required = true, dataType = "String", paramType = "path"),
		@ApiImplicitParam(name = "mid", value = "测量项目id", required = true,  dataType = "String", paramType = "path")
	})
	@RequestMapping(value = "/insertnewcar/{urlname}" , method = RequestMethod.GET)
	@MethodLog(remark = "新增项点测量结果")
	public ResponseEntity<JsonResult> insertnewcar(@PathVariable(value = "urlname") String urlname,String mid) {
		JsonResult r = new JsonResult();
		try {
			JSONObject result = ids.insertnewcar(url+urlname, mid);
			r.setResult(result.getString("r"));
			r.setStatus(result.getString("s"));
		} catch (Exception e) {
			LOG.error(e.getClass().getName() + ":" + e.getMessage());
			r.setResult(e.getClass().getName() + ":" + e.getMessage());
			r.setStatus("error");
			e.printStackTrace();
		}
		return ResponseEntity.ok(r);
	}
	/**
	 * 
	 * 
	 *   查询 根据项目id 查结果
	 * 
	 * @param rid
	 * @return
	 */
	@ApiOperation(value="根据项目id 查结果", notes="根据项目id 查结果")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "rid", value = "项目id",  required = true, dataType = "String", paramType = "path"),
	})
	@RequestMapping(value = "/selectbyridpname", method = RequestMethod.GET)
	@MethodLog(remark = "根据项目id 查结果")
	public JSONObject  selectbyid(String rid) {
		JSONObject ma = null;
		try {
			ma = ids.selectbyrid(rid);
		} catch (Exception e) {
			LOG.error(e.getClass().getName() + ":" + e.getMessage());
			e.printStackTrace();
		}
		return ma;
	}
	/**
	 * 
	 * 模糊查询 pname  项目rid 移动接口
	 * @param rid
	 * @param pname
	 * @return
	 */
	@ApiOperation(value="模糊查询 pname  项目rid 移动接口", notes="模糊查询 pname  项目rid 移动接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "rid", value = "项目id",  required = true, dataType = "String", paramType = "path"),
		@ApiImplicitParam(name = "pname", value = "模糊查询",  required = true, dataType = "String", paramType = "path"),
		@ApiImplicitParam(name = "pageNum", value = "页码",  required = true, dataType = "Integer", paramType = "path"),
		@ApiImplicitParam(name = "pageSize", value = "每页数量",  required = true, dataType = "Integer", paramType = "path")
	})
	@RequestMapping(value = "/selectbypname", method = RequestMethod.GET)
	@MethodLog(remark = "模糊查询 pname  项目rid 移动接口")
	public JSONObject  selectbypname(String rid,String pname,Integer  pageNum,Integer  pageSize) {
		JSONObject ma = null;
		try {
			ma = ids.selectbypname(rid,pname,  pageNum,  pageSize);
		} catch (Exception e) {
			LOG.error(e.getClass().getName() + ":" + e.getMessage());
			e.printStackTrace();
		}
		return ma;
	}
	@ApiOperation(value="查询所有", notes="查询所有")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNum", value = "页码"),
		@ApiImplicitParam(name = "pageSize", value = "每页数量")
	})
	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	@MethodLog(remark = "查询所有-监测结果")
	public List<detectionresult>  selectAll(Integer  pageNum,Integer  pageSize) {
		List<detectionresult> ma = null;
		try {
			ma = ids.selectAll(pageNum,  pageSize);
		} catch (Exception e) {
			LOG.error(e.getClass().getName() + ":" + e.getMessage());
			e.printStackTrace();
		}
		return ma;
	}
	@ApiOperation(value="查询结果by项目id和项点id", notes="查询所有")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "mid", value = "项目id"),
		@ApiImplicitParam(name = "starid", value = "项点id")
	})
	@RequestMapping(value = "/selectbymidsid", method = RequestMethod.GET)
	@MethodLog(remark = "查询所有-监测结果")
	public List<detectionresult>  selectbymidsid(String mid,String starid) {
		List<detectionresult> ma = null;
		try {
			ma = ids.selectbymidsid(mid,starid);
		} catch (Exception e) {
			LOG.error(e.getClass().getName() + ":" + e.getMessage());
			e.printStackTrace();
		}
		return ma;
	}
	
	
	
	
	
	/**
	 *
	 *
	 *  根据id 修改 
	 *    
	 */
	@ApiOperation(value="根据id 修改 结果", notes="根据id 修改 detectionresult")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "d", value = "detectionresult实体类",  required = true, dataType = "detectionresult")
	})
	@RequestMapping(value = "/updatebyid", method = RequestMethod.POST)
	@MethodLog(remark = "根据id 修改 ")
	public int updatebyid(@RequestBody detectionresult d) {
		int ma = 0;
		try {
			ma = ids.updatedetectionresult(d);
		} catch (Exception e) {
			LOG.error(e.getClass().getName() + ":" + e.getMessage());
			e.printStackTrace();
		}
		return ma;
	}
	@ApiOperation(value="根据id 删除结果", notes="根据id 删除detectionresult")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "id",  required = true, dataType = "String" ,paramType = "path")
	})
	@RequestMapping(value = "/deletebyid", method = RequestMethod.GET)
	@MethodLog(remark = "根据id 删除")
	public JSONObject deletebyid(String id) {
		JSONObject ma = null;
		try {
			ma = ids.deletebyid(id);
		} catch (Exception e) {
			LOG.error(e.getClass().getName() + ":" + e.getMessage());
			e.printStackTrace();
		}
		return ma;
	}
	@ApiOperation(value="根据id 到处表格", notes="根据id 到处表格")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "id",  required = true, dataType = "String" ,paramType = "path"),
		@ApiImplicitParam(name = "filename", value = "导出文件名",  required = true, dataType = "String" ,paramType = "path")
	})
	@RequestMapping(value = "/getexcle", method = RequestMethod.GET)
	@MethodLog(remark = "根据id 到处表格")
	public void getexcle(HttpServletResponse response,String id,String filename) {//设置响应为下载
		if (filename == null || "".equals(filename)) {
			filename = "export";
		}
		try {
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-disposition",
					"attachment; filename=" + new String((filename+".xls").getBytes("gb2312"), "iso8859-1"));
			//第一步创建workbook 
			HSSFWorkbook wb = new HSSFWorkbook();
			//第二步创建sheet 
			HSSFSheet sheet = wb.createSheet("测量标准");
			//第三步创建行row:添加表头0行 
			HSSFRow row = sheet.createRow(0);
			HSSFCellStyle style = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中 
			//第四步创建单元格 
			HSSFCell cell = row.createCell(0);//第一个单元格 
			cell.setCellValue("序号");//设定值 
			cell.setCellStyle(style);//内容居中 
			cell = row.createCell(1);//第二个单元格   
			cell.setCellValue("检查项目");
			cell.setCellStyle(style);
			cell = row.createCell(2);//第三个单元格  
			cell.setCellValue("检查标准或要求");
			cell.setCellStyle(style);
			cell = row.createCell(3);//第四个单元格  
			cell.setCellValue("实测数据");
			cell.setCellStyle(style);
			cell = row.createCell(4);//第五个单元格  
			cell.setCellValue("是否合格");
			cell.setCellStyle(style);
			//第五步插入数据 
			List<detectionresult> list =  ids.selectbymid(id);
//			List<Map<String, Object>> list = ios.selectByOname(hcode,null, starttime, endtime, pageNum, pageSize);
			for (int i = 0; i < list.size(); i++) {
				detectionresult m = list.get(i);
				//创建行 
				row = sheet.createRow(i + 1);
				//创建单元格并且添加数据 
				row.createCell(0).setCellValue(i + 1);
				row.createCell(1).setCellValue(m.getPname());
				row.createCell(2).setCellValue(m.getStandards());
				row.createCell(3).setCellValue(m.getMeasureddata());
				row.createCell(4).setCellValue(m.getMeasuredresults());
			}
			//第六步将生成excel文件保存到指定路径下 
			wb.write(response.getOutputStream());
		} catch (Exception e) {
			LOG.error(e.getClass().getName() + ":" + e.getMessage());
		}
	}
	@ApiOperation(value="下载app", notes="下载app")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "filename", value = "下载文件文件名",  required = true, dataType = "String" ,paramType = "path")
	})
	@RequestMapping(value = "/getApp/{filename}", method = RequestMethod.GET)
	@MethodLog(remark = "下载app")
	public void getApp(HttpServletResponse response, @PathVariable String filename) {// 设置响应为下载
		try {
//			String fileurl = "D:\\Users\\Desktop\\";
			String fileurl = url;
			ServletOutputStream out = response.getOutputStream();
			File file = new File(fileurl + filename + ".apk");
			System.out.println(file.exists());
			if (!file.exists()) {
				return;
			}
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-disposition",
					"attachment; filename=" + new String(("道岔参数系统.apk").getBytes("gb2312"), "iso8859-1"));
			System.out.println(file.getName());
			FileInputStream fileInputStream = null;
			try {
				fileInputStream = new FileInputStream(file);
			} catch (Exception e) {
			}

			if (fileInputStream != null) {
				int filelen = fileInputStream.available();
				byte a[] = new byte[filelen];
				fileInputStream.read(a);
				out.write(a);
			}
			fileInputStream.close();
			out.close();
		} catch (Exception e1) {
			LOG.error(e1.getClass().getName() + ":" + e1.getMessage());
			e1.printStackTrace();
		}
	}
	/**
	 * 文件上传
	 * @param files
	 * @return
	 */
	@RequestMapping(value = "/multipleSave", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	@MethodLog(remark = "项点图片上传")
	public @ResponseBody JSONObject multipleSave(@RequestParam("file") MultipartFile[] files
			,@RequestParam("id")String id,HttpServletRequest request) {
		JSONObject jsonObject = new JSONObject();
		String realPath = request.getSession().getServletContext().getRealPath("file/");  
		System.out.println("文件上传路径"+realPath);
		String fileName = null;
		List<filedatatable> list = new ArrayList<filedatatable>();
		try {
			if (files != null && files.length > 0) {
				for (int i = 0; i < files.length; i++) {
					fileName = files[i].getOriginalFilename();
					String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
						byte[] bytes = files[i].getBytes();
						String oldName = fileName;
						fileName = new Date().getTime() + "_" + new Random().nextInt(1000) + "." + prefix;// 新的文件名
						BufferedOutputStream buffStream = new BufferedOutputStream(
								new FileOutputStream(new File(realPath + fileName)));
						buffStream.write(bytes);
						buffStream.close();
						filedatatable d = new  filedatatable();
						d.setOriginName(oldName);
						d.setThumb(fileName);
						d.setCreatetime(new Date().getTime()+"");
						d.setFilepath(realPath + fileName);
						d.setType(prefix);
						d.setAbbreviatedName(id);
						list.add(d);
					}
				JSONObject object =  ifs.instert(list);
				return object;
				}
		}catch (Exception e) {
			LOG.error(e.getClass().getName() + ":" + e.getMessage());
			jsonObject.put("stats", "fail");
			jsonObject.put("code", "500");
			jsonObject.put("message", e);
			
			return jsonObject;
		}
	
		jsonObject.put("stats", "fail");
		jsonObject.put("code", "500");
		jsonObject.put("message", "");
		
		return jsonObject;

	}
	/***
	 * 
	 * 
	 * 项点id 获取
	 * @param res
	 * @param fileName
	 */
	@RequestMapping(value = "/show/{fileName}", method = RequestMethod.GET)
	@MethodLog(remark = "显示图片")
	public void Download(HttpServletResponse res, @PathVariable String fileName) {
		filedatatable filedatatables = ifs.getfileByid(fileName);
		String filePath = filedatatables.getFilepath();
		System.out.println("文件读取路径"+filePath);
			fileName = filedatatables.getOriginName();
		try {
			ServletOutputStream out = res.getOutputStream();
			File file = new File(filePath);
			FileInputStream fileInputStream = null;
			try {
				fileInputStream = new FileInputStream(file);
			} catch (Exception e) {
			}
			if (fileName != null && fileName.length() > 0) {
				// res.setContentType("application/x-msdownload");
				res.setHeader("Content-Disposition", "attachment; filename="
						+ new String(fileName.getBytes("utf-8"), "iso8859-1"));
				if (fileInputStream != null) {
					int filelen = fileInputStream.available();
					byte a[] = new byte[filelen];
					fileInputStream.read(a);
					out.write(a);
				}
				fileInputStream.close();
				out.close();
			}
		} catch (IOException e) {
			LOG.error(e.getClass().getName() + ":" + e.getMessage());
			e.printStackTrace();
		} finally {
		}
	}
	@RequestMapping(value = "/showImg/{fileName}", method = RequestMethod.GET)
	@MethodLog(remark = "显示图片")
	public void showImg(HttpServletResponse res, @PathVariable String fileName) {
		String downloadPath = url + fileName + ".jpg";
		
		File file = new File(downloadPath);
		System.out.println(file.exists());
		if (!file.exists()) {
			return;
		}
		
		res.setContentType("image/jpeg");
//		res.setDateHeader("expries", -1);
//		res.setHeader("Cache-Control", "no-cache");
//		res.setHeader("Pragma", "no-cache");
		BufferedImage buffImg;
		
		try {
			buffImg = toBufferedImage(Toolkit.getDefaultToolkit().getImage(downloadPath));
//			System.out.println(buffImg.getHeight());
			ImageIO.write(buffImg, "jpg", res.getOutputStream());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			LOG.error(e.getClass().getName() + ":" + e.getMessage());
			e.printStackTrace();
		}

	}
	@RequestMapping(value = "/showImgid/{fileName}", method = RequestMethod.GET)
	@MethodLog(remark = "显示图片")
	public void showImgid(HttpServletResponse res, @PathVariable String fileName) {
		
		filedatatable filedatatables = ifs.getfileByid(fileName);
		String filePath = filedatatables.getThumb();
		
		String downloadPath = url +filePath;
		
		File file = new File(downloadPath);
		System.out.println(file.exists());
		if (!file.exists()) {
			return;
		}
		
		res.setContentType("image/jpeg");
//		res.setDateHeader("expries", -1);
//		res.setHeader("Cache-Control", "no-cache");
//		res.setHeader("Pragma", "no-cache");
		BufferedImage buffImg;
		
		try {
			buffImg = toBufferedImage(Toolkit.getDefaultToolkit().getImage(downloadPath));
//			System.out.println(buffImg.getHeight());
			ImageIO.write(buffImg, "jpg", res.getOutputStream());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			LOG.error(e.getClass().getName() + ":" + e.getMessage());
			e.printStackTrace();
		}
		
	}
	

	@RequestMapping(value = "/getSmartCarData", method = RequestMethod.GET)
//	@MethodLog(remark = "实施获取小车数据")
	public String getSmartCarData(HttpServletRequest request) {
		String realPath = url;
//		String realPath = "D:/Users/Desktop/";
		JSONObject jsonObject = new JSONObject();
		JSONArray array = new JSONArray();
		//获取 对比规则
		JSONObject jsono = imss.selectAll1(0, 10);
		JSONArray jo =  (JSONArray) jsono.get("list");
		
		String str1 = "";//轨距
		String str2 = "";//水平
		String str3 = "";//高低
		String str4 = "";//方向
		for (int i = 0; i < jo.size(); i++) {
			if ("1".equals(jo.getJSONObject(i).get("id"))) {
				str1 = (String) jo.getJSONObject(i).get("standard");
			}else if ("2".equals(jo.getJSONObject(i).get("id"))) {
				str2 = (String) jo.getJSONObject(i).get("standard");
			}else if ("3".equals(jo.getJSONObject(i).get("id"))) {
				str3 = (String) jo.getJSONObject(i).get("standard");
			}else if ("4".equals(jo.getJSONObject(i).get("id"))) {
				str4 = (String) jo.getJSONObject(i).get("standard");
				String str41 = "";
				for (int i1 = 0; i1 < str4.length(); i1++) {
					if (str4.charAt(i1) >= 48 && str4.charAt(i1) <= 57) {
						str41 += str4.charAt(i1);
					}
				}
				str4 = str41;
			}else {
				continue;
			}
		}
		System.out.println(str4);
		if ("".equals(str1)||"".equals(str2)||"".equals(str3)) {
			jsonObject.put("code", 500);
			jsonObject.put("state", "fail");
			jsonObject.put("msg", "计算标准为空！");
			return jsonObject.toString();
		}
		BigDecimal bigDecimal1 = new BigDecimal(str1);
		BigDecimal bigDecimal2 = new BigDecimal(str2);
		BigDecimal bigDecimal3 = new BigDecimal(str3);
		BigDecimal bigDecimal4 = new BigDecimal(str4);
		
		try {
//			List<String[]> li = jsonTomodel.Read2003xls(realPath + "railwayVehicleDataFresh.xls");
//			List<String[]> li = jsonTomodel.readPoi(realPath + "railwayVehicleDataFresh.xlsx");
			List<String[]> li = jsonTomodel.readCSV(realPath + "railwayVehicleDataFresh.csv");
			if (li.size() == 0) {
				jsonObject.put("code", 500);
				jsonObject.put("state", "fail");
				jsonObject.put("msg", "li is null");
				return jsonObject.toString();
			}
			
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
			
			for (int i = 1; i < li.size(); i++) {
				
				String[] str = li.get(i);
				if ("".equals(str[0].trim())|| str[0].trim().length() <= 0) {
					break;
				}
				
				for (int j = 0; j < title.length; j++) {
					if ("".equals(title[j].trim())|| title.length <= 0) {
						continue;
					}
					if ("轨距".equals(title[j])) {
						JSONObject j1 = new JSONObject();
						
						j1.put("num", str[j]);
						String ss1 = check1(new BigDecimal(str[j]),bigDecimal1);
						j1.put("sitename", ss1);
						if ("合格".equals(ss1)) {
							isnot1++;
						}
						array1.add(j1);//轨距
					}else if("水平".equals(title[j])) {
						JSONObject j2 = new JSONObject();
						j2.put("num", str[j]);
						String ss2 = check2(new BigDecimal(str[j]),bigDecimal2);
						if ("合格".equals(ss2)) {
							isnot2++;
						}
						j2.put("sitename", ss2);
						array2.add(j2);//水平
					}else if("高低".equals(title[j])) {
						
						JSONObject j3 = new JSONObject();
						j3.put("num", str[j]);
						String ss3 = check2(new BigDecimal(str[j]),bigDecimal3);
						j3.put("sitename", ss3);
						if ("合格".equals(ss3)) {
							isnot3++;
						}
						array3.add(j3);//高低
					}else if("方向".equals(title[j])) {
						
						JSONObject j4 = new JSONObject();
						j4.put("num", str[j]);
						String ss4 = check2(new BigDecimal(str[j]),bigDecimal4);
						j4.put("sitename", ss4);
						if ("合格".equals(ss4)) {
							isnot4++;
						}
						array4.add(j4);//高低
					}else if ("里程".equals(title[j])){
						mileage = str[j];
						
					}else {
						JSONObject o5 = new JSONObject();
						o5.put("title", title[j]);
						o5.put("num", j == str.length ?"-":str[j]);
						o5.put("sitename", "-");
						array5.add(o5);
					}
				}
			}
			
			JSONObject mileagejs = new JSONObject();
			
			mileagejs.put("name", "里程");
			mileagejs.put("num", mileage);
			
			JSONObject o1 = new JSONObject();
			o1.put("list", array1);
			o1.put("name", "轨距");
			o1.put("mileage", mileagejs);
			o1.put("code", (isnot1/array1.size())*100+"%");
			array.add(o1);
			
			o1 = new JSONObject();
			o1.put("list", array2);
			o1.put("name", "水平");
			o1.put("mileage", mileagejs);
			o1.put("code", (isnot2/array2.size())*100+"%");
			array.add(o1);
			
			o1 = new JSONObject();
			o1.put("list", array3);
			o1.put("name", "高低");
			o1.put("mileage", mileagejs);
			o1.put("code", (isnot3/array3.size())*100+"%");
			array.add(o1);
			
			o1 = new JSONObject();
			o1.put("list", array4);
			o1.put("name", "方向");
			o1.put("mileage", mileagejs);
			o1.put("code", (isnot4/array4.size())*100+"%");
			array.add(o1);
			
			for (int i = 0; i < array5.size(); i++) {
				JSONObject object = array5.getJSONObject(i);
				
				o1 = new JSONObject();
				JSONArray jso = new JSONArray();
				jso.add(object);
				o1.put("list", jso);
				
				o1.put("mileage", mileagejs);
				o1.put("name", object.getString("title"));
				o1.put("code", 100+"%");
				array.add(o1);
			}
			
			
		} catch (Exception e) {
			LOG.error(e.getClass().getName() + ":" + e.getMessage());
			e.printStackTrace();
			jsonObject.put("code", 500);
			jsonObject.put("state", "fail");
			jsonObject.put("msg", e);
			return jsonObject.toString();
		}

//		String fileaddr = "[{\"list\":[{\"num\":\"69\",\"sitename\":\"合格\"},{\"num\":\"69\",\"sitename\":\"不合格\"}],\"name\":\"水平\",\"code\":\"a\"},{\"list\":[{\"num\":\"69\",\"sitename\":\"不合格\"},{\"num\":\"69\",\"sitename\":\"不合格\"}],\"name\":\"垂直\",\"code\":\"b\"}]";
		jsonObject.put("code", 200);
		jsonObject.put("state", "success");
		jsonObject.put("msg", array);

		return jsonObject.toString();

	}
	//轨距
	public String check1(BigDecimal b1,BigDecimal b2){
		//.negate()     返回负数
		//b2 减b1
//		BigDecimal bg1 = b2.subtract(b1);
//		BigDecimal bg2 = b1.add(b2);
		int r1=b1.compareTo(b2); //和0，Zero比较
		int r2=b1.compareTo(b2.negate()); //和0，Zero比较
//		if(r==0) //等于
//		if(r==1) //大于
//		if(r==-1) //小于
		System.out.println(r1== -1);
		System.out.println(r2!= -1);
		if (r1 == -1 && r2 != -1) {
			return "合格";
		}else {
			return "不合格";
		}	
	}
	//水平 高低
	public String check2(BigDecimal b1,BigDecimal b2){
		//.negate()     返回负数
		//b2 减b1
		BigDecimal bg1 = b2.subtract(b1);
		int r1=bg1.compareTo(BigDecimal.ZERO); //和0，Zero比较
//		if(r==0) //等于
//		if(r==1) //大于
//		if(r==-1) //小于
		if (r1 != -1 ) {
			return "合格";
		}else {
			return "不合格";
		}	
	}
	
	
	public BufferedImage toBufferedImage(Image image) {
		if (image instanceof BufferedImage) {
			return (BufferedImage) image;
		}
		// This code ensures that all the pixels in the image are loaded
		image = new ImageIcon(image).getImage();
		BufferedImage bimage = null;
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		try {
			int transparency = Transparency.OPAQUE;
			GraphicsDevice gs = ge.getDefaultScreenDevice();
			GraphicsConfiguration gc = gs.getDefaultConfiguration();
			bimage = gc.createCompatibleImage(image.getWidth(null), image.getHeight(null), transparency);
		} catch (HeadlessException e) {
			// The system does not have a screen
		}
		if (bimage == null) {
			// Create a buffered image using the default color model
			int type = BufferedImage.TYPE_INT_RGB;
			bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
		}
		// Copy image to buffered image
		Graphics g = bimage.createGraphics();
		// Paint the image onto the buffered image
		g.drawImage(image, 0, 0, null);
		g.dispose();
		return bimage;
	}

	
	
	
	
	
	
}
