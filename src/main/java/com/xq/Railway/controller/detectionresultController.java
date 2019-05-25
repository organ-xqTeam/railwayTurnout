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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.xq.Railway.model.measurementstandard;
import com.xq.Railway.service.idetectionresultService;
import com.xq.Railway.service.ifiledatatableService;
import com.xq.Railway.service.imeasurementstandardService;
import com.xq.Railway.util.StringUtil;
import com.xq.Railway.util.jsonTomodel;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/detectionresult")
public class detectionresultController {

	@Autowired
	private idetectionresultService ids;
	
	@Autowired
	private ifiledatatableService ifs;
	
	@Autowired
	private imeasurementstandardService imss;
	
	@Value("${springurl.fileurl}")
	private String url;
	
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
	@RequestMapping("/getApp/{filename}")
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
			// TODO Auto-generated catch block
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
	@RequestMapping(value = "/showImg/{fileName}", method = RequestMethod.GET)
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

	@RequestMapping(value = "/getSmartCarData")
	public String getSmartCarData(HttpServletRequest request) {
//		String realPath = request.getSession().getServletContext().getRealPath("file/");
		String realPath = "/Users/apple/Desktop/";
		JSONObject jsonObject = new JSONObject();
		JSONArray array = new JSONArray();
		//获取 对比规则
		JSONObject jsono = imss.selectAll1(0, 10);
		JSONArray jo =  (JSONArray) jsono.get("list");
		
		String str1 = "";//轨距
		String str2 = "";//水平
		String str3 = "";//高低
		for (int i = 0; i < jo.size(); i++) {
			if ("1".equals(jo.getJSONObject(i).get("id"))) {
				str1 = (String) jo.getJSONObject(i).get("standard");
			}else if ("2".equals(jo.getJSONObject(i).get("id"))) {
				str2 = (String) jo.getJSONObject(i).get("standard");
			}else if ("3".equals(jo.getJSONObject(i).get("id"))) {
				str3 = (String) jo.getJSONObject(i).get("standard");
			}else {
				continue;
			}
		}
		if ("".equals(str1)||"".equals(str2)||"".equals(str3)) {
			jsonObject.put("code", 500);
			jsonObject.put("state", "fail");
			jsonObject.put("msg", "计算标准为空！");
			return jsonObject.toString();
		}
		BigDecimal bigDecimal1 = new BigDecimal(str1);
		BigDecimal bigDecimal2 = new BigDecimal(str2);
		BigDecimal bigDecimal3 = new BigDecimal(str3);
		
		try {
			List<String[]> li = jsonTomodel.Read2003xls(realPath + "123.xls");
			if (li.size() == 0) {
				jsonObject.put("code", 500);
				jsonObject.put("state", "fail");
				jsonObject.put("msg", "li is null");
				return jsonObject.toString();
			}
			
			JSONArray array1 = new JSONArray();//轨距
			JSONArray array2 = new JSONArray();//水平
			JSONArray array3 = new JSONArray();//高低
			int isnot1=0;//轨距 合格数量
			int isnot2=0;//水平 合格数量
			int isnot3=0;//高低 合格数量
			for (int i = 1; i < li.size(); i++) {
				String[] str = li.get(i);
				JSONObject j1 = new JSONObject();
				j1.put("num", str[1]);
				String ss1 = check1(new BigDecimal(str[1]),bigDecimal1);
				j1.put("sitename", ss1);
				if ("合格".equals(ss1)) {
					isnot1++;
				}
				array1.add(j1);//轨距
				
				JSONObject j2 = new JSONObject();
				j2.put("num", str[2]);
				String ss2 = check2(new BigDecimal(str[2]),bigDecimal2);
				if ("合格".equals(ss2)) {
					isnot2++;
				}
				j2.put("sitename", ss2);
				array2.add(j2);//水平
				
				JSONObject j3 = new JSONObject();
				j3.put("num", str[3]);
				String ss3 = check2(new BigDecimal(str[3]),bigDecimal3);
				j3.put("sitename", ss3);
				if ("合格".equals(ss3)) {
					isnot3++;
				}
				array3.add(j3);//高低
			}
			
			JSONObject o1 = new JSONObject();
			o1.put("list", array1);
			o1.put("name", "轨距");
			o1.put("code", (isnot1/array1.size())*100+"%");
			array.add(o1);
			
			o1 = new JSONObject();
			o1.put("list", array2);
			o1.put("name", "水平");
			o1.put("code", (isnot2/array2.size())*100+"%");
			array.add(o1);
			
			o1 = new JSONObject();
			o1.put("list", array3);
			o1.put("name", "高低");
			o1.put("code", (isnot3/array3.size())*100+"%");
			array.add(o1);
			
			
		} catch (Exception e) {
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
		BigDecimal bg1 = b2.subtract(b1);
		
		BigDecimal bg2 = b1.add(b2.negate());
		int r1=bg1.compareTo(BigDecimal.ZERO); //和0，Zero比较
		int r2=bg2.compareTo(BigDecimal.ZERO); //和0，Zero比较
//		if(r==0) //等于
//		if(r==1) //大于
//		if(r==-1) //小于
		if (r1 != -1 && r2 != -1) {
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
