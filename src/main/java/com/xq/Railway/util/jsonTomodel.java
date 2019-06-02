package com.xq.Railway.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

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
	public static List<String[]> readPoi(String filePath) {
		if (!filePath.endsWith(".xlsx")) {
			System.out.println("不是07版本");
			return null;
		}
		File file = new File(filePath);
		if (!file.exists()) {
			System.out.println("文件不存在" + filePath);
			return null;
		}
		HSSFWorkbook xwb = null;
		FileInputStream strPath = null;
		List<String[]> list = new ArrayList<String[]>();
		try {
			strPath = new FileInputStream(file);
			xwb = new HSSFWorkbook(strPath);
			HSSFSheet sheet = xwb.getSheetAt(0);
			HSSFRow row;
			String cell;
			int FristRows = sheet.getFirstRowNum();
			int LastRows = sheet.getPhysicalNumberOfRows();
			for (int i = FristRows; i < LastRows; i++) {
				row = sheet.getRow(i);
				int num = row.getPhysicalNumberOfCells();
				String[] str = new String[num];
				int FristCell = row.getFirstCellNum();
				int LastCell = row.getPhysicalNumberOfCells();
				for (int j = FristCell; j <LastCell; j++) {
					cell = row.getCell(j).toString();
					str[j] = cell;
				}
				list.add(str);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
//				xwb.close();
				if (strPath != null) {
					strPath.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return list;

	}

	public static List<String[]> readCSV(String filePath) {
		if (!filePath.endsWith(".csv")) {
			System.out.println("不是csv文件");
			return null;
		}
		List<String[]> list = new ArrayList<String[]>();
		try {
			File csv = new File(filePath); // CSV数据文件
			if (!csv.exists()) {
				System.out.println("文件不存在" + filePath);
				return null;
			}
			InputStreamReader isr = new InputStreamReader(new FileInputStream(csv), "GBK");
			BufferedReader reader = new BufferedReader(isr);// 换成你的文件名
//			reader.readLine();// 第一行信息，为标题信息，不用,如果需要，注释掉
			String line = null;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				String item[] = line.split(",");// CSV格式文件为逗号分隔符文件，这里根据逗号切分
				list.add(item);
				String last = item[item.length - 1];// 这就是你要的数据了
				// int value = Integer.parseInt(last);//如果是数值，可以转化为数值
				System.out.println(last);
			}
			reader.close();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			
		}
		return list;
	}
}
