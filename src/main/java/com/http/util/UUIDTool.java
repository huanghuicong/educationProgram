package com.http.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

/**
 * 随机产生日期+uuid
 * 
 * 
 * 
 */
public class UUIDTool {

	public static String getUUID(){
        UUID uuid=UUID.randomUUID();
        String str = uuid.toString(); 
        String uuidStr=str.replace("-", "");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateStr = sdf.format(new Date());
        return dateStr+uuidStr;
      }
	
	public  void main(String[] args) throws IOException {
		System.out.println(getUUID());
		
		 Properties properties = new Properties();
         // 使用ClassLoader加载properties配置文件生成对应的输入流
	     // 使用properties对象加载输入流
		 properties.load(this.getClass().getResourceAsStream("/message.properties"));
         //获取key对应的value值
	     System.out.println(properties.getProperty("image_1")); 
	}
}
