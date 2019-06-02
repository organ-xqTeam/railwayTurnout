package com.xq.Railway.util;

import java.math.BigDecimal;
import java.util.List;

public class algorithm {

	public static String check(String id,BigDecimal b1, List<BigDecimal> bigDecimals) {
		String reset="";
		switch (id) {
		case "1"://正负
			BigDecimal b2 = bigDecimals.get(0);
			reset = check1(b1, b2);
			break;
		case "2"://小于等于
			BigDecimal b21 = bigDecimals.get(0);
			reset = check2(b1, b21);
			break;
		case "3":

			break;
		case "4":

			break;

		default:
			break;
		}
		
		return reset;

	}
	/**
	 * 
	 * 
	 * @param b1
	 * @param b2
	 * @return
	 */
	public static String check1(BigDecimal b1, BigDecimal b2) {
		// .negate() 返回负数
		// b2 减b1
//			BigDecimal bg1 = b2.subtract(b1);
//			BigDecimal bg2 = b1.add(b2);
		int r1 = b1.compareTo(b2); // 和0，Zero比较
		int r2 = b1.compareTo(b2.negate()); // 和0，Zero比较
//			if(r==0) //等于
//			if(r==1) //大于
//			if(r==-1) //小于
		System.out.println(r1 == -1);
		System.out.println(r2 != -1);
		if (r1 == -1 && r2 != -1) {
			return "合格";
		} else {
			return "不合格";
		}
	}

	// 水平 高低
	public static String check2(BigDecimal b1, BigDecimal b2) {
		// .negate() 返回负数
		// b2 减b1
		BigDecimal bg1 = b2.subtract(b1);
		int r1 = bg1.compareTo(BigDecimal.ZERO); // 和0，Zero比较
//			if(r==0) //等于
//			if(r==1) //大于
//			if(r==-1) //小于
		if (r1 != -1) {
			return "合格";
		} else {
			return "不合格";
		}
	}
}
