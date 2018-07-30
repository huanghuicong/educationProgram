package com.http.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.http.model.CalendarChildInfo;
import com.http.model.CalendarCurriculumInfo;
import com.http.model.RequestBaseModel;
import com.http.model.ResponseBaseModel;
import com.http.service.CalendarChildInfoService;
import com.http.service.CalendarCurriculumInfoService;
import com.http.util.DateTool;
import com.http.util.ErrorUtil;
import com.http.util.FileUpload;
import com.http.util.ParamCheck;
import com.http.util.UUIDTool;
import com.http.util.ErrorMsg.MobileService;

@Controller
@RequestMapping("/childInfo")
public class CalendarChildInfoController {

	@Autowired
	private CalendarChildInfoService calendarChildInfoService;
	@Autowired
	private CalendarCurriculumInfoService calendarCurriculumInfoService;
	
	@ResponseBody
	@RequestMapping("/addChildInfo")
	public String addChildInfo(HttpServletRequest request,HttpServletResponse response,@RequestParam(required=false,value="childUrl")MultipartFile file) throws IOException{
		JSONObject jsonObject = JSONObject.parseObject(request.getParameter("requestBaseModel").toString());
		CalendarChildInfo calendarChildInfo = JSON.parseObject(jsonObject.getString("params"), CalendarChildInfo.class);
		if(ParamCheck.checkIsNull(calendarChildInfo.getOpenId())){
	        return ErrorUtil.errorToMessage(MobileService.M300001.getErrCode());
	    }
		if(ParamCheck.checkIntIsNull(calendarChildInfo.getChildType())){
	        return ErrorUtil.errorToMessage(MobileService.M300034.getErrCode());
	    }
		//上传至文件服务器
		if(file!=null){
			String url = FileUpload.upload(file,request,response);
			calendarChildInfo.setChildHeadPortrait(url);
		}
		calendarChildInfo.setUuid(UUIDTool.getUUID());
		calendarChildInfo.setCreateTime(DateTool.parseDates2(new Date()));
		calendarChildInfo.setOperateTime(DateTool.parseDates2(new Date()));
		calendarChildInfo.setState(0);
		
		Properties properties = new Properties();
        // 使用ClassLoader加载properties配置文件生成对应的输入流
		properties.load(this.getClass().getResourceAsStream("/message.properties"));
        //获取key对应的value值
		String boyImage = properties.getProperty("image_1");
		String girlImage = properties.getProperty("image_2");
		//上传头像文件为空
		if(calendarChildInfo.getChildHeadPortrait()==null||"".equals(calendarChildInfo.getChildHeadPortrait())){
			//男孩
			if(calendarChildInfo.getChildType()!=null&&0==calendarChildInfo.getChildType()){
				//服务器
				calendarChildInfo.setChildHeadPortrait(boyImage);
			}
			//女孩
			if(calendarChildInfo.getChildType()!=null&&1==calendarChildInfo.getChildType()){
				//服务器
				calendarChildInfo.setChildHeadPortrait(girlImage);
			}
		}
		
        calendarChildInfo.setOperateTime(DateTool.parseDates2(new Date()));
		calendarChildInfoService.save(calendarChildInfo);
		//生成默认课程
		//插入默认课程信息
		CalendarCurriculumInfo curriculumInfo1 = new CalendarCurriculumInfo();
		curriculumInfo1.setUuid(UUIDTool.getUUID());
		curriculumInfo1.setCalendarChildId(calendarChildInfo.getId());
		curriculumInfo1.setCurriculumName("游泳");
		curriculumInfo1.setCurriculumType("1");
		curriculumInfo1.setCurriculumTag("1");
		curriculumInfo1.setState(0);
		curriculumInfo1.setCreateTime(DateTool.parseDates2(new Date()));
		curriculumInfo1.setOperateTime(DateTool.parseDates2(new Date()));
		calendarCurriculumInfoService.save(curriculumInfo1);
		
		CalendarCurriculumInfo curriculumInfo2 = new CalendarCurriculumInfo();
		curriculumInfo2.setUuid(UUIDTool.getUUID());
		curriculumInfo2.setCalendarChildId(calendarChildInfo.getId());
		curriculumInfo2.setCurriculumName("英语培训");
		curriculumInfo2.setCurriculumType("1");
		curriculumInfo2.setCurriculumTag("2");
		curriculumInfo2.setState(0);
		curriculumInfo2.setCreateTime(DateTool.parseDates2(new Date()));
		curriculumInfo2.setOperateTime(DateTool.parseDates2(new Date()));
		calendarCurriculumInfoService.save(curriculumInfo2);
		
		CalendarCurriculumInfo curriculumInfo3 = new CalendarCurriculumInfo();
		curriculumInfo3.setUuid(UUIDTool.getUUID());
		curriculumInfo3.setCalendarChildId(calendarChildInfo.getId());
		curriculumInfo3.setCurriculumName("钢琴");
		curriculumInfo3.setCurriculumType("1");
		curriculumInfo3.setCurriculumTag("3");
		curriculumInfo3.setState(0);
		curriculumInfo3.setCreateTime(DateTool.parseDates2(new Date()));
		curriculumInfo3.setOperateTime(DateTool.parseDates2(new Date()));
		calendarCurriculumInfoService.save(curriculumInfo3);
		
		
		ResponseBaseModel responseBaseModel = new ResponseBaseModel();
		responseBaseModel.setResultObject(calendarChildInfo);
        responseBaseModel.setResultTime(DateTool.parseDates(new Date())); 
		return JSON.toJSONString(responseBaseModel);
	}
	
	@ResponseBody
	@RequestMapping("/updateChildInfo")
	public String updateChildInfo(HttpServletRequest request,HttpServletResponse response,@RequestParam(required=false,value="childUrl")CommonsMultipartFile[] file){
		JSONObject jsonObject = JSONObject.parseObject(request.getParameter("requestBaseModel").toString());
		CalendarChildInfo calendarChildInfo = JSON.parseObject(jsonObject.getString("params"), CalendarChildInfo.class);
		if(ParamCheck.checkLongIsNull(calendarChildInfo.getId())){
	        return ErrorUtil.errorToMessage(MobileService.M300005.getErrCode());
	    }
		//上传至文件服务器
		if(file!=null){
			String url = FileUpload.upload(file[0],request,response);
			calendarChildInfo.setChildHeadPortrait(url);
		}
        calendarChildInfo.setOperateTime(DateTool.parseDates2(new Date()));
		calendarChildInfoService.update(calendarChildInfo);
		ResponseBaseModel responseBaseModel = new ResponseBaseModel();
		responseBaseModel.setResultObject(calendarChildInfo);
        responseBaseModel.setResultTime(DateTool.parseDates(new Date())); 
		return JSON.toJSONString(responseBaseModel);
	}
	
	
	@ResponseBody
	@RequestMapping("/updateChildInfoNoImg")
	public String updateChildInfoNoImg(HttpServletRequest request,HttpServletResponse response,@RequestBody RequestBaseModel params){
		CalendarChildInfo calendarChildInfo = JSON.parseObject(params.getParams(), CalendarChildInfo.class);
		if(ParamCheck.checkLongIsNull(calendarChildInfo.getId())){
	        return ErrorUtil.errorToMessage(MobileService.M300005.getErrCode());
	    }
		
        calendarChildInfo.setOperateTime(DateTool.parseDates2(new Date()));
		calendarChildInfoService.update(calendarChildInfo);
		ResponseBaseModel responseBaseModel = new ResponseBaseModel();
		responseBaseModel.setResultObject(calendarChildInfo);
        responseBaseModel.setResultTime(DateTool.parseDates(new Date())); 
		return JSON.toJSONString(responseBaseModel);
	}
	

	@ResponseBody
	@RequestMapping("/lastChildOperate")
	public String lastChildOperate(HttpServletRequest request,HttpServletResponse response,@RequestBody RequestBaseModel params){
		CalendarChildInfo calendarChildInfo = JSON.parseObject(params.getParams(), CalendarChildInfo.class);
		if(ParamCheck.checkLongIsNull(calendarChildInfo.getId())){
	        return ErrorUtil.errorToMessage(MobileService.M300005.getErrCode());
	    }
		if(ParamCheck.checkIsNull(calendarChildInfo.getOpenId())){
	        return ErrorUtil.errorToMessage(MobileService.M300001.getErrCode());
	    }
		//查询当前openId下的所有孩子
		Map<String,Object> childParam = new HashMap<String,Object>(); 
		childParam.put("openId", calendarChildInfo.getOpenId());
		List<CalendarChildInfo> childInfoList = calendarChildInfoService.queryByOpenId(childParam);
		//修改最后一次操作状态
		for(int i=0;i<childInfoList.size();i++){
			if(calendarChildInfo.getId().equals(childInfoList.get(i).getId())){
				childInfoList.get(i).setLastOperate("1");
				calendarChildInfoService.update(childInfoList.get(i));
				calendarChildInfo = childInfoList.get(i);
			}else{
				childInfoList.get(i).setLastOperate("0");
				calendarChildInfoService.update(childInfoList.get(i));
			}
		}
		if(calendarChildInfo.getChildName()==null||"".equals(calendarChildInfo.getChildName())){
			if(calendarChildInfo.getChildType()!=null&&0==calendarChildInfo.getChildType()){
				calendarChildInfo.setChildName("男孩");
			}else{
				calendarChildInfo.setChildName("女孩");
			}
		}
		if(calendarChildInfo.getChildAge()==null){
			calendarChildInfo.setChildAge(0);
		}
		ResponseBaseModel responseBaseModel = new ResponseBaseModel();
		responseBaseModel.setResultObject(calendarChildInfo);
        responseBaseModel.setResultTime(DateTool.parseDates(new Date())); 
		return JSON.toJSONString(responseBaseModel);
	}
	
	@ResponseBody
	@RequestMapping("/deleteChildInfo")
	public String deleteChildInfo(HttpServletRequest request,HttpServletResponse response,@RequestBody RequestBaseModel params){
		CalendarChildInfo calendarChildInfo = JSON.parseObject(params.getParams(), CalendarChildInfo.class);
		if(ParamCheck.checkLongIsNull(calendarChildInfo.getId())){
	        return ErrorUtil.errorToMessage(MobileService.M300005.getErrCode());
	    }
		//查询孩子信息得到openID
		calendarChildInfo = calendarChildInfoService.queryChildById(calendarChildInfo);
		String openId = calendarChildInfo.getOpenId();
		//删除孩子
		calendarChildInfo.setState(1);
		calendarChildInfo.setLastOperate("0");
		calendarChildInfo.setOperateTime(DateTool.parseDates2(new Date()));
		calendarChildInfoService.update(calendarChildInfo);
		//查询剩余孩子
		Map<String, Object> childParam = new HashMap<String, Object>();
		childParam.put("openId", openId);
		//根据openId查询剩余孩子中有没有lastOperate为1的孩子
		List<CalendarChildInfo> lastChildInfoList = calendarChildInfoService.queryByOpenIdandLast(childParam);
		if(lastChildInfoList.size()>0){

		}else{
			//已删除lastOperate为1的孩子
			List<CalendarChildInfo> newChildInfoList = calendarChildInfoService.queryByOpenId(childParam);
			if(newChildInfoList.size()>0){
				newChildInfoList.get(0).setLastOperate("1");
				calendarChildInfoService.update(newChildInfoList.get(0));
			}
		}
		//查询孩子名下的课程信息
		CalendarCurriculumInfo calendarCurriculumInfo = new CalendarCurriculumInfo();
		calendarCurriculumInfo.setCalendarChildId(calendarChildInfo.getId());
		List<CalendarCurriculumInfo> calendarCurriculumInfoList = calendarCurriculumInfoService.queryByObject(calendarCurriculumInfo);
		//删除孩子名下的课程信息
		for(int i=0;i<calendarCurriculumInfoList.size();i++){
			CalendarCurriculumInfo curriculumInfo = calendarCurriculumInfoList.get(i);
			curriculumInfo.setState(1);
			calendarCurriculumInfoService.update(curriculumInfo);
		}
		return ErrorUtil.errorToMessage(MobileService.M000000.getErrCode());
	}
	
	@ResponseBody
	@RequestMapping("/queryChildInfoList")
	public String queryChildInfoList(HttpServletRequest request,HttpServletResponse response,@RequestBody RequestBaseModel params){
		CalendarChildInfo calendarChildInfo = JSON.parseObject(params.getParams(), CalendarChildInfo.class);
		if(ParamCheck.checkIsNull(calendarChildInfo.getOpenId())){
	        return ErrorUtil.errorToMessage(MobileService.M300001.getErrCode());
	    }
		Map<String, Object> childParam = new HashMap<String, Object>();
		childParam.put("openId", calendarChildInfo.getOpenId());
		List<CalendarChildInfo> childInfoList = calendarChildInfoService.queryByOpenId(childParam);
		for(int i =0;i<childInfoList.size();i++){
			if(childInfoList.get(i).getChildName()==null||"".equals(childInfoList.get(i).getChildName())){
				if(childInfoList.get(i).getChildType()!=null&&0==childInfoList.get(i).getChildType()){
					childInfoList.get(i).setChildName("男孩");
				}else{
					childInfoList.get(i).setChildName("女孩");
				}
			}
			if(childInfoList.get(i).getChildAge()==null){
				childInfoList.get(i).setChildAge(0);
			}
			if(childInfoList.get(i).getLastOperate()==null||"".equals(childInfoList.get(i).getLastOperate())){
				childInfoList.get(i).setLastOperate("0");
			}
		}
		ResponseBaseModel responseBaseModel = new ResponseBaseModel();
		responseBaseModel.setResultList(childInfoList);
        responseBaseModel.setResultTime(DateTool.parseDates(new Date())); 
		return JSON.toJSONString(responseBaseModel);
	}
}
