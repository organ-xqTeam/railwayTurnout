package com.xq.Railway.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;

/**
 * MD5通用类
 * 
 * @author 浩令天下
 * @since 2017.04.15
 * @version 1.0.0_1
 * 
 */
public class MD5 {
	
	
	
	

	public static String md5check( String key,String text) throws Exception {
		// 加密后的字符串
		String encodeStr = DigestUtils.md5Hex(text + key);
		System.out.println(key);
		System.out.println("MD5加密后的字符串为:encodeStr=" + encodeStr);
		return encodeStr;
	}

//	public static void main(String[] args) {
//		try {
//			new  MD5().md5("123456admin");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	
	
}