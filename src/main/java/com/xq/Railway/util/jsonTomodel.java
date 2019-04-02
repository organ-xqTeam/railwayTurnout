package com.xq.Railway.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;

import com.xq.Railway.model.administrator;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import net.sf.json.JSONObject;

public class jsonTomodel {
	
	public static administrator jsontoadmin(JSONObject object) {
		administrator admin = new administrator();
		admin.setAemile(object.getString("aemile"));
		admin.setAfoundtime(object.getString("afoundtime"));
		admin.setAname(object.getString("aname"));
		admin.setAphonenum(object.getString("aphonenum"));
		admin.setAusername(object.getString("ausername"));
		return null;
	}
	public static  List<String[]> Read2003xls(String filePath) throws Exception {
		if (!filePath.endsWith(".xls")) {
			throw new Exception("不是excel文件！");
//			return null;
		}
		File file=new File(filePath);
		List<String[]> result=new ArrayList<String[]>();
		Workbook workbook=null;
		try {
			workbook=Workbook.getWorkbook(file);
			Sheet sheet=workbook.getSheet(0);
			Cell cell=null;
			for(int i=0;i<sheet.getRows();i++){
				String[] str=new String[sheet.getColumns()];
				for(int j=0;j<sheet.getColumns();j++){
					cell=sheet.getCell(j, i);
					str[j]=cell.getContents();
				}
				result.add(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		finally{
			workbook.close();
		}
		return result;

	}
}
