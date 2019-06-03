package com.xq.Railway.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class postgetUtil {
	
	public synchronized static String sendGet(String url, String param,String cookie) {  
        String result = "";  
        BufferedReader in = null;  
        try {  
            String urlName = url + "?" + param;  
            URL realUrl = new URL(urlName);  
            // 打开和URL之间的连接  
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("Cookie", cookie);  
            // 建立实际的连接  
            conn.connect();  
            // 获取所有响应头字段  
//            Map<String, List<String>> map = conn.getHeaderFields();  
            // 遍历所有的响应头字段  
//            for (String key : map.keySet()) {  
//                System.out.println(key + "--->" + map.get(key));  
//            }  
            // 定义BufferedReader输入流来读取URL的响应  
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf8"));  
            String line;  
            while ((line = in.readLine()) != null) {  
                result += "\n" + line;  
            }  
        } catch (Exception e) {  
            System.out.println("发送GET请求出现异常！" + e);  
            e.printStackTrace();  
        }  
        // 使用finally块来关闭输入流  
        finally {  
            try {  
                if (in != null) {  
                    in.close();  
                }  
            } catch (IOException ex) {   
                ex.printStackTrace();  
            }  
        }  
        return result;  
    } 
	
	
	public static synchronized String Get(String url, String param,String cookie) {  
        String result = "";  
        BufferedReader in = null;  
        try {  
            String urlName = url + "?" + param;  
            URL realUrl = new URL(urlName);  
            // 打开和URL之间的连接  
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("Cookie", cookie);  
            // 建立实际的连接  
            conn.connect();  
            // 获取所有响应头字段  
//            Map<String, List<String>> map = conn.getHeaderFields();  
            // 遍历所有的响应头字段  
//            for (String key : map.keySet()) {  
//                System.out.println(key + "--->" + map.get(key));  
//            }  
            // 定义BufferedReader输入流来读取URL的响应  
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf8"));  
            String line;  
            while ((line = in.readLine()) != null) {  
                result += "\n" + line;  
            }  
        } catch (Exception e) {  
            System.out.println("发送GET请求出现异常！" + e);  
            e.printStackTrace();  
        }  
        // 使用finally块来关闭输入流  
        finally {  
            try {  
                if (in != null) {  
                    in.close();  
                }  
            } catch (IOException ex) {   
                ex.printStackTrace();  
            }  
        }  
        return result;  
    } 
	public static synchronized String Get(String url, String param) {  
		String result = "";  
		BufferedReader in = null;  
		try {  
			String urlName = url + "?" + param;  
			URL realUrl = new URL(urlName);  
			// 打开和URL之间的连接  
			URLConnection conn = realUrl.openConnection();
//			conn.setRequestProperty("Cookie", cookie);  
			// 建立实际的连接  
			conn.connect();  
			// 获取所有响应头字段  
//            Map<String, List<String>> map = conn.getHeaderFields();  
			// 遍历所有的响应头字段  
//            for (String key : map.keySet()) {  
//                System.out.println(key + "--->" + map.get(key));  
//            }  
			// 定义BufferedReader输入流来读取URL的响应  
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf8"));  
			String line;  
			while ((line = in.readLine()) != null) {  
				result += "\n" + line;  
			}  
		} catch (Exception e) {  
			System.out.println("发送GET请求出现异常！" + e);  
			e.printStackTrace();  
		}  
		// 使用finally块来关闭输入流  
		finally {  
			try {  
				if (in != null) {  
					in.close();  
				}  
			} catch (IOException ex) {   
				ex.printStackTrace();  
			}  
		}  
		return result;  
	} 
}
