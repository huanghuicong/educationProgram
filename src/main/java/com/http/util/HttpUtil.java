package com.http.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;

public class HttpUtil {
	public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
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
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
	
	/** 
     * 向指定 URL 发送POST方法的请求 
     *  
     * @param url 
     *            发送请求的 URL 
     * @param param 
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。 
     * @return 所代表远程资源的响应结果 
     */  
    public static String sendPost(String url, String param) {  
        PrintWriter out = null;  
        BufferedReader in = null;  
        String result = "";  
        try {  
            URL realUrl = new URL(url);  
            // 打开和URL之间的连接  
            URLConnection conn = realUrl.openConnection();  
            //设置通用的请求属性  
            conn.setRequestProperty("user-agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:21.0) Gecko/20100101 Firefox/21.0)");  
            // 发送POST请求必须设置如下两行  
            conn.setDoOutput(true);  
            conn.setDoInput(true);  
            // 获取URLConnection对象对应的输出流  
            OutputStreamWriter outWriter = new OutputStreamWriter(conn.getOutputStream(), "utf-8");  
            out = new PrintWriter(outWriter);  
            // 发送请求参数  
            out.print(param);  
            // flush输出流的缓冲  
            out.flush();  
            // 定义BufferedReader输入流来读取URL的响应  
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));  
            String line;  
            while ((line = in.readLine()) != null) {  
                in = new BufferedReader(new InputStreamReader(conn.getInputStream()));result += line;  
            }  
        } catch (Exception e) {  
            System.out.println("发送 POST 请求出现异常！"+e);  
            e.printStackTrace();  
        }  
        //使用finally块来关闭输出流、输入流  
        finally{  
            try{  
                if(out!=null){  
                    out.close();  
                }  
                if(in!=null){  
                    in.close();  
                }  
            }  
            catch(IOException ex){  
                ex.printStackTrace();  
            }  
        }  
        return result;  
    }     

	public static void main(String[] args) {
		/* String adCode = "0571";
		 String subdistrict = "0";
		 String extensions = "base";
		 String url = "http://restapi.amap.com/v3/config/district";
		 String param = "key=1ff6f2c063dfd4ca16d435921b0846e0&keywords="+adCode+"&subdistrict="+subdistrict+"&extensions="+extensions+"";
		 String result =MapUtil.sendGet(url,param);  
		 System.out.println(result);*/
		 double start1 = 116.368904;
		 double start2 = 39.923423;
		 double end1 = 116.387271;
		 double end2 = 39.922501;
		 String s1="1.8";
		 String s2 = "1.8";
		 //String aa = computationalDistance(start1,start2,end1,end2);
         //System.out.println(aa);
		 System.out.println( s1.compareTo(s2) );
	}
	
	  /**
	      * 从输入流中读取数据
	      * 
	      * @param inStream
	      * @return
	      * @throws Exception
	      */
	     public static byte[] readInputStream(InputStream inStream) throws Exception {
	         ByteArrayOutputStream outStream = new ByteArrayOutputStream();
	         byte[] buffer = new byte[1024];
	         int len = 0;
	         while ((len = inStream.read(buffer)) != -1) {
	             outStream.write(buffer, 0, len);
	         }
	         byte[] data = outStream.toByteArray();
	         outStream.close();
	         inStream.close();
	         return data;
	     }
   
}
