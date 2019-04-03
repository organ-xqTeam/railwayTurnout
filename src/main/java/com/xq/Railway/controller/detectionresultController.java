package com.xq.Railway.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.xq.Railway.logAop.MethodLog;
import com.xq.Railway.model.detectionresult;
import com.xq.Railway.model.filedatatable;
import com.xq.Railway.service.idetectionresultService;
import com.xq.Railway.service.ifiledatatableService;

import net.sf.json.JSONObject;

@RestController
@RequestMapping("/detectionresult")
public class detectionresultController {

	@Autowired
	private idetectionresultService ids;
	
	@Autowired
	private ifiledatatableService ifs;
	
	/**
	 * 新增 结果   添加上传文件
	 * 
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/insert" )
	@MethodLog(remark = "新增项点测量结果")
	public JSONObject insert(detectionresult m,@RequestParam("file") MultipartFile[] files
			,HttpServletRequest request) {
		JSONObject result = ids.instert(m);
		
		if (!"success".equals(result.getString("stats"))) {
			return result;
		}
		JSONObject jsonObject = new JSONObject();
		String realPath = request.getSession().getServletContext().getRealPath("file/");   
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
			jsonObject.put("stats", "fail");
			jsonObject.put("code", "500");
			jsonObject.put("message", e);
			
			return jsonObject;
		}
	
		jsonObject.put("stats", "fail");
		jsonObject.put("code", "500");
		jsonObject.put("message", "");
		jsonObject.put("result", result);
		return jsonObject;
	}
	/**
	 * 
	 * 
	 *   查询 根据项目id 查结果
	 * 
	 * @param rid
	 * @return
	 */
	@RequestMapping(value = "/selectbyridpname")
	@MethodLog(remark = "根据项目id 查结果")
	public JSONObject  selectbyid(String rid) {
		JSONObject  ma = ids.selectbyrid(rid);
		return ma;
	}
	/**
	 * 
	 * 模糊查询 pname  项目rid 移动接口
	 * @param rid
	 * @param pname
	 * @return
	 */
	@RequestMapping(value = "/selectbypname")
	@MethodLog(remark = "模糊查询 pname  项目rid 移动接口")
	public JSONObject  selectbypname(String rid,String pname,Integer  pageNum,Integer  pageSize) {
		JSONObject  ma = ids.selectbypname(rid,pname,  pageNum,  pageSize);
		return ma;
	}
	
	@RequestMapping(value = "/selectAll")
	public List<detectionresult>  selectAll(Integer  pageNum,Integer  pageSize) {
		List<detectionresult>  ma = ids.selectAll(pageNum,  pageSize);
		return ma;
		
	}
	
	/**
	 *
	 *
	 *  根据id 修改 
	 *    
	 */
	@RequestMapping(value = "/updatebyid")
	@MethodLog(remark = "根据id 修改 ")
	public int updatebyid(detectionresult d) {
		int ma = ids.updatedetectionresult(d);
		return ma;
	}
	@RequestMapping(value = "/deletebyid")
	@MethodLog(remark = "根据id 删除")
	public JSONObject deletebyid(String id) {
		JSONObject ma = ids.deletebyid(id);
		return ma;
	}
	@RequestMapping("/getexcle")
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
	
	
	/**
	 * 
	 * 获取所有图片id
	 */
	
	
	
	
	/***
	 * 
	 * 
	 * 项点id 获取
	 * @param res
	 * @param fileName
	 */
	@RequestMapping(value = "/show/{fileName}", method = RequestMethod.GET)
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
			e.printStackTrace();
		} finally {
		}
	}

	
	
	
	
	
	
}
