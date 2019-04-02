package com.xq.Railway.util;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

/**
 * 提供精确计算的工具类
 *
 * @author xiaqing
 * @date 2017/11/25.
 */
public class DecimalUtil {
    /**
     * 格式化输出结果
     */
    private static final DecimalFormat df = new DecimalFormat("0.00");

    /**
     * 精确的加法
     * @param x double类型的数字
     * @param y double类型的数字
     * @return
     */
    public static String add(double x, double y) {
        BigDecimal b1 = new BigDecimal(Double.toString(x));
        BigDecimal b2 = new BigDecimal(Double.toString(y));
        return df.format(b1.add(b2));
    }

    /**
     * 精确的加法
     * @param x String类型的数字
     * @param y String类型的数字
     * @return
     */
    public static String add(String x, String y) {
        BigDecimal b1 = new BigDecimal(x);
        BigDecimal b2 = new BigDecimal(y);
        return df.format(b1.add(b2));
    }

    /**
     * 精确的减法
     * @param x double类型的数字
     * @param y double类型的数字
     * @return
     */
    public static String subtract(double x, double y) {
        BigDecimal b1 = new BigDecimal(Double.toString(x));
        BigDecimal b2 = new BigDecimal(Double.toString(y));
        return df.format(b1.subtract(b2));
    }

    /**
     * 精确的减法
     * @param x String类型的数字
     * @param y String类型的数字
     * @return
     */
    public static String subtract(String x, String y) {
        BigDecimal b1 = new BigDecimal(x);
        BigDecimal b2 = new BigDecimal(y);
        return df.format(b1.subtract(b2));
    }

    /**
     * 精确的乘法
     * @param x double类型的数字
     * @param y double类型的数字
     * @return
     */
    public static String multiply(double x, double y) {
        BigDecimal b1 = new BigDecimal(Double.toString(x));
        BigDecimal b2 = new BigDecimal(Double.toString(y));
        return df.format(b1.multiply(b2));
    }

    /**
     * 精确的乘法
     * @param x String类型的数字
     * @param y String类型的数字
     * @return
     */
    public static String multiply(String x, String y) {
        BigDecimal b1 = new BigDecimal(x);
        BigDecimal b2 = new BigDecimal(y);
        return df.format(b1.multiply(b2));
    }

    /**
     * 精确的除法
     * @param x String类型的数字
     * @param y String类型的数字
     * @return
     */
    public static String divide(double x, double y) {
        BigDecimal b1 = new BigDecimal(Double.toString(x));
        BigDecimal b2 = new BigDecimal(Double.toString(y));
        //scale指的是小数点后的位数,这里的2表示精确到小数点后面的两位小数
        //roundingMode是小数的保留模式。它们都是BigDecimal中的常量字段,有很多种。
        //比如：BigDecimal.ROUND_HALF_UP表示的就是4舍5入
        return df.format(b1.divide(b2,2,BigDecimal.ROUND_HALF_UP));
    }

    /**
     * 精确的乘法
     * @param x String类型的数字
     * @param y String类型的数字
     * @return
     */
    public static String divide(String x, String y) {
        BigDecimal b1 = new BigDecimal(x);
        BigDecimal b2 = new BigDecimal(y);
        return df.format(b1.divide(b2,2,BigDecimal.ROUND_HALF_UP));
    }
    public static String ContainNumbers(String a){
//		String a = "12.09";
		String regEx = "(([-]?[1-9]\\d*\\.?\\d*)|([-]?0\\.\\d*[1-9]))";
		Pattern pattern = Pattern.compile(regEx, Pattern.MULTILINE | Pattern.DOTALL);
		Matcher matcher;
		matcher = pattern.matcher(a);
		if (matcher.find()) {
			String str = matcher.group();
//			System.out.println(str);
			return str;
			
		}
		return null;
	}
    /**
     *         表格上传数据进行格式化
     * 
     * @param list
     * @return
     * @throws Exception
     */
    public static JSONObject sdateformat(List<String[]> list) throws Exception {
    	BigDecimal decimal;
    	JSONObject jsonObject = new JSONObject();
    	for (int i = 0; i < list.size(); i++) {
    		if ("1".equals(list.get(i)[0])) {
    			if (ContainNumbers(list.get(i)[2]) == null) {
    				throw new Exception("数据格式不对");
    			}
    			decimal = new BigDecimal(list.get(i)[2]);
    			jsonObject.put("1", decimal);
			}else if ("2".equals(list.get(i)[0])) {
				if (ContainNumbers(list.get(i)[2]) == null) {
					throw new Exception("数据格式不对");
				}
    			decimal = new BigDecimal(list.get(i)[2]);
    			jsonObject.put("2", decimal);
			}else if ("3".equals(list.get(i)[0])) {
				if (ContainNumbers(list.get(i)[2]) == null) {
					throw new Exception("数据格式不对");
				}
    			decimal = new BigDecimal(list.get(i)[2]);
    			jsonObject.put("3", decimal);
			}
		}
		return jsonObject;
    }
    /**
     * 
     *         计算水平
     *                            
     * @param x 左高程
     * @param y 右高程
     * @return
     */
    public static BigDecimal levelData(String x, String y) {
    	BigDecimal b1 = new BigDecimal(x);
        BigDecimal b2 = new BigDecimal(y);
        return b1.subtract(b2);
    }
     /**
      * 
      *                计算高低  
      * @param x
      * @param y
      * @return
      */
    public static BigDecimal HsData(String x, String y) {
    	BigDecimal b1 = new BigDecimal(x);
        BigDecimal b2 = new BigDecimal(y);
        BigDecimal dd = new BigDecimal(2);
        return (b1.add(b2)).divide(dd,2,BigDecimal.ROUND_HALF_UP);
    }
    
    
    
}

