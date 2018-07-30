package com.http.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.http.model.CalendarChildInfo;
import com.http.model.CalendarCurriculumInfo;
import com.http.model.RequestBaseModel;
import com.http.model.ResponseBaseModel;
import com.http.service.CalendarChildInfoService;
import com.http.service.CalendarCurriculumInfoService;
import com.http.util.DateTool;
import com.http.util.ErrorUtil;
import com.http.util.ParamCheck;
import com.http.util.UUIDTool;
import com.http.util.ErrorMsg.MobileService;

@Controller
@RequestMapping("/curriculumInfo")
public class CalendarCurriculumInfoController {

	@Autowired
	private CalendarCurriculumInfoService calendarCurriculumInfoService;
	@Autowired
	private CalendarChildInfoService calendarChildInfoService;
	
	@ResponseBody
	@RequestMapping("/saveCurriculumInfo")
	public String addCurriculumInfo(HttpServletRequest request,@RequestBody RequestBaseModel params){
		CalendarCurriculumInfo calendarCurriculumInfo = JSON.parseObject(params.getParams(), CalendarCurriculumInfo.class);
		if(ParamCheck.checkLongIsNull(calendarCurriculumInfo.getCalendarChildId())){
	        return ErrorUtil.errorToMessage(MobileService.M300009.getErrCode());
	    }
		if(ParamCheck.checkIsNull(calendarCurriculumInfo.getCurriculumName())){
	        return ErrorUtil.errorToMessage(MobileService.M300008.getErrCode());
	    }
		if(ParamCheck.checkIsNull(calendarCurriculumInfo.getCurriculumType())){
	        return ErrorUtil.errorToMessage(MobileService.M300006.getErrCode());
	    }
		if(ParamCheck.checkIsNull(calendarCurriculumInfo.getCurriculumTag())){
	        return ErrorUtil.errorToMessage(MobileService.M300007.getErrCode());
	    }
		if(ParamCheck.checkIsNull(calendarCurriculumInfo.getRemindingTime())){
	        return ErrorUtil.errorToMessage(MobileService.M300010.getErrCode());
	    }
		calendarCurriculumInfo.setUuid(UUIDTool.getUUID());
		calendarCurriculumInfo.setCreateTime(DateTool.parseDates2(new Date()));
		calendarCurriculumInfo.setOperateTime(DateTool.parseDates2(new Date()));
		calendarCurriculumInfo.setState(0);
		calendarCurriculumInfoService.save(calendarCurriculumInfo);
		ResponseBaseModel responseBaseModel = new ResponseBaseModel();
        responseBaseModel.setResultObject(calendarCurriculumInfo);
        responseBaseModel.setResultTime(DateTool.parseDates(new Date())); 
		return JSON.toJSONString(responseBaseModel);
	}
	
	@ResponseBody
	@RequestMapping("/updateCurriculumInfo")
	public String updateCurriculumInfo(HttpServletRequest request,@RequestBody RequestBaseModel params){
		CalendarCurriculumInfo calendarCurriculumInfo = JSON.parseObject(params.getParams(), CalendarCurriculumInfo.class);
		if(ParamCheck.checkLongIsNull(calendarCurriculumInfo.getId())){
	        return ErrorUtil.errorToMessage(MobileService.M300005.getErrCode());
	    }
		calendarCurriculumInfo.setOperateTime(DateTool.parseDates2(new Date()));
		calendarCurriculumInfoService.update(calendarCurriculumInfo);
		ResponseBaseModel responseBaseModel = new ResponseBaseModel();
        responseBaseModel.setResultObject(calendarCurriculumInfo);
        responseBaseModel.setResultTime(DateTool.parseDates(new Date())); 
		return JSON.toJSONString(responseBaseModel);
	}
	
	@ResponseBody
	@RequestMapping("/deleteCurriculumInfo")
	public String deleteCurriculumInfo(HttpServletRequest request,@RequestBody RequestBaseModel params){
		CalendarCurriculumInfo calendarCurriculumInfo = JSON.parseObject(params.getParams(), CalendarCurriculumInfo.class);
		if("3".equals(calendarCurriculumInfo.getDeleteType())){
			calendarCurriculumInfo.setState(3);
		}
		if("1".equals(calendarCurriculumInfo.getDeleteType())){
			calendarCurriculumInfo.setState(1);
		}
		calendarCurriculumInfo.setOperateTime(DateTool.parseDates2(new Date()));
		calendarCurriculumInfoService.updateByName(calendarCurriculumInfo);
		return ErrorUtil.errorToMessage(MobileService.M000000.getErrCode());
	}
	
	/*
	 * 孩子下所有的课程
	 * */
	@ResponseBody
	@RequestMapping("/queryCurriculumInfoList")
	public String queryCurriculumInfoList(HttpServletRequest request,@RequestBody RequestBaseModel params){
		CalendarCurriculumInfo calendarCurriculumInfo = JSON.parseObject(params.getParams(), CalendarCurriculumInfo.class);
		if(ParamCheck.checkLongIsNull(calendarCurriculumInfo.getCalendarChildId())){
	        return ErrorUtil.errorToMessage(MobileService.M300020.getErrCode());
	    }
		List<CalendarCurriculumInfo> calendarCurriculumInfoList = calendarCurriculumInfoService.queryCurriculumInfoList(calendarCurriculumInfo);
		for(int i=0;i<calendarCurriculumInfoList.size();i++){
			if(calendarCurriculumInfoList.get(i).getRemindingTime()==null||"".equals(calendarCurriculumInfoList.get(i).getRemindingTime())){
				calendarCurriculumInfoList.get(i).setRemindingTime("0");
			}
		}
		CalendarChildInfo calendarChildInfo = new CalendarChildInfo();
		calendarChildInfo.setId(calendarCurriculumInfo.getCalendarChildId());
		calendarChildInfo = calendarChildInfoService.queryChildById(calendarChildInfo);
		for(int i=0;i<calendarCurriculumInfoList.size();i++){
			calendarCurriculumInfoList.get(i).setChildHeadPortrait(calendarChildInfo.getChildHeadPortrait());
			calendarCurriculumInfoList.get(i).setChildName(calendarChildInfo.getChildName());
		}
		ResponseBaseModel responseBaseModel = new ResponseBaseModel();
        responseBaseModel.setResultList(calendarCurriculumInfoList);
        responseBaseModel.setResultTime(DateTool.parseDates(new Date())); 
		return JSON.toJSONString(responseBaseModel);
	}
}
