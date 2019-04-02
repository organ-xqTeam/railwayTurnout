package com.xq.Railway.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.web.multipart.MultipartFile;

import com.xq.Railway.model.filedatatable;
import com.xq.Railway.service.ifiledatatableService;

import net.sf.json.JSONObject;

public class StringUtil {

	public static String getRandNum(int i) {
		return System.currentTimeMillis()+"";
	}
	
	
	public static boolean isNotEmpty(String ip) {
		
		if (ip != null) {
			return true;
		}
		// TODO Auto-generated method stub
		return false;
	}
	
	public static String Dateform(Date date) {
		SimpleDateFormat Dateform = new SimpleDateFormat("yyyy-MM-dd");
		return Dateform.format(date);
	}
	
	
	public static JSONObject fileUpload(MultipartFile[] files,String realPath,ifiledatatableService ifs) throws Exception{
		JSONObject jsonObject = new JSONObject();
		String fileName = null;
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
						filedatatable filedatatable = new filedatatable();
						filedatatable.setOriginName(oldName);
						filedatatable.setFilepath(realPath + fileName);
						filedatatable.setThumb(fileName);
						filedatatable.setType(prefix);
						filedatatable.setCreatetime(new Date().getTime()+"");
//						filedatatable.setCreatenameid(admin.getAusername());
						filedatatable.setCreatenameid("上传人");
						ifs.instert(filedatatable);
						List<String[]> list =  jsonTomodel.Read2003xls(realPath + fileName);
//						for (String[] strings : list) {
//							for (String strings2 : strings) {
//								System.out.print(strings2+"\t");
//							}
//							System.out.println();
//						}
						/**
						 * 上传上传文件待处理
						 * 
						 */
						jsonObject.put("stats", "success");
						jsonObject.put("code", "200");
						jsonObject.put("message", "");
						jsonObject.put("list", list);
						jsonObject.put("fileName", fileName);
						return jsonObject;
				}
			} else {
//				jsonObject.put("stats", "fail"); 
//				jsonObject.put("code", "500");
//				jsonObject.put("message", "无文件");
//				throw new Exception(jsonObject.toString());
			}
		} catch (Exception e) {
			jsonObject.put("stats", "fail");
			jsonObject.put("code", "500");
			jsonObject.put("message", "无文件"+e);
			throw new Exception(jsonObject.toString());
		}
		return null;
	}

}
