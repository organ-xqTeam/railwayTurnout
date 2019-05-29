package com.xq.Railway.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

public class WeatherHelper {

    /**
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
//        System.out.println(getWeather("101010100"));//北京的天气信息
        System.out.println(getWeather("101010100").get("date").toString());
    }
    
    /**
     * 获得天气信息
     * @param cityid 城市ID
     * @return 天气信息
     * @throws IOException 
     */
    public static Map<String, String> getWeather(String cityid) throws IOException{ 
        String weather = "";
        URLConnection connectionData;
        BufferedReader br;// 读取data数据流 
        StringBuilder sb = null;
        
        // 连接中央气象台的API 
        URL url = new URL("http://t.weather.sojson.com/api/weather/city/" + cityid + ".html"); 
        connectionData = url.openConnection(); 
        connectionData.setConnectTimeout(1000); 
        try { 
            br = new BufferedReader(new InputStreamReader(connectionData.getInputStream(), "UTF-8")); 
            sb = new StringBuilder(); 
            String line = null; 
            while ((line = br.readLine()) != null) 
                sb.append(line); 
        } catch (SocketTimeoutException e) { 
            System.out.println("连接超时"); 
        } catch (FileNotFoundException e) { 
            System.out.println("加载文件出错"); 
        } 
        weather = sb.toString();   
        System.out.println(weather);
        JSONObject jsonData = JSONObject.fromObject(weather);
        JSONObject jsonWeather = jsonData.getJSONObject("weatherinfo");
        Map<String, String> weatherInfo = new HashMap<String, String>();
        weatherInfo.put("cityId", jsonWeather.getString("cityid").toString());//id
        weatherInfo.put("cityName", jsonWeather.getString("city").toString());//名称
        weatherInfo.put("date", jsonWeather.getString("date_y").toString());//日期
        weatherInfo.put("week", jsonWeather.getString("week").toString());//星期
        weatherInfo.put("temp", jsonWeather.getString("temp1").toString());//温度
        weatherInfo.put("weather", jsonWeather.getString("weather1").toString());//天气现象
        weatherInfo.put("wind", jsonWeather.getString("wind1").toString());//风
        weatherInfo.put("fl", jsonWeather.getString("fl1").toString());//风力
        weatherInfo.put("fx", jsonWeather.getString("fx1").toString());//风向
        
        return weatherInfo;
    }

}
