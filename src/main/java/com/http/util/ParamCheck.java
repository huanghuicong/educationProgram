package com.http.util;
/**
 * @ClassName:ParamCheck 参数验证
 */
public class ParamCheck {

	//检测是否为空
	public static boolean checkIsNull(String value){
		if(value==null||"".equals(value)){
			return true;
		}else{
			return false;		
			}
	}
	//检测是否为空
	public static boolean checkLongIsNull(Long value){
		if(value==null){
			return true;
		}else{
			return false;		
			}
	}
	//检测是否为空
	public static boolean checkIntIsNull(Integer value){
		if(value==null){
			return true;
		}else{
			return false;		
			}
	}
	//检测手机号码格式
	public static boolean checkMobile(String value){
		if(value.matches("^1[3|4|5|7|8][0-9]\\d{4,8}$")){
			return true;
		}else{
			return false;		
			}
	}
	
	//检测分页是否为空
	public static boolean checkPageIsNull(Integer startNum,Integer pageNum){
		if(startNum==null||pageNum==null){
			return true;
		}else{
			return false;		
			}
	}
	
	//检测经纬度是否为空
	public static boolean checkLatitudeAndLongitude(String longitude,String latitude){
		if(longitude==null||"".equals(longitude)||latitude==null||"".equals(latitude)){
			return true;
		}else{
			return false;		
			}
	}
	
	//检测两个排序条件是否同时存在
	public static boolean checkSort(String distance,String defaultScore){
		if(distance!=null&&defaultScore!=null){
			return true;
		}else{
			return false;		
			}
	}
	
	//检测两个排序条件是否同时不存在
	public static boolean checkTwoAge(Integer value1,Integer value2){
		if(value1==null&&value2==null){
			return true;
		}else{
			return false;		
			}
	}
}
