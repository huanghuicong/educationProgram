package com.http.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.annotation.W3CDomHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.http.model.CalendarChildInfo;
import com.http.model.CalendarCurriculumInfo;
import com.http.model.CurriculumScheduleInfo;
import com.http.model.CurriculumScheduleTask;
import com.http.model.RequestBaseModel;
import com.http.model.ResponseBaseModel;
import com.http.model.WeChatUserFormId;
import com.http.service.CalendarChildInfoService;
import com.http.service.CalendarCurriculumInfoService;
import com.http.service.CurriculumScheduleInfoService;
import com.http.service.CurriculumScheduleTaskService;
import com.http.service.WeChatUserFormIdService;
import com.http.util.CalendarTool;
import com.http.util.DateTool;
import com.http.util.ErrorUtil;
import com.http.util.ParamCheck;
import com.http.util.UUIDTool;
import com.http.util.ErrorMsg.MobileService;

@Controller
@RequestMapping("/scheduleInfo")
public class CurriculumScheduleInfoController {

	@Autowired
	private CurriculumScheduleInfoService curriculumScheduleInfoService;
	@Autowired
	private CurriculumScheduleTaskService curriculumScheduleTaskService;
	@Autowired
	private WeChatUserFormIdService weChatUserFormIdService;
	@Autowired
	private CalendarCurriculumInfoService calendarCurriculumInfoService;
	@Autowired
	private CalendarChildInfoService calendarChildInfoService;
	
	@ResponseBody
	@RequestMapping("/saveScheduleInfo")
	public String saveScheduleInfo(HttpServletRequest request,HttpServletResponse response,@RequestBody RequestBaseModel params) throws ParseException{
		CurriculumScheduleInfo curriculumScheduleInfo = JSON.parseObject(params.getParams(), CurriculumScheduleInfo.class);
		/*if(ParamCheck.checkIsNull(curriculumScheduleInfo.getToUser())){
	        return ErrorUtil.errorToMessage(MobileService.M300001.getErrCode());
	    }*/
		/*if(ParamCheck.checkIsNull(curriculumScheduleInfo.getFormId())){
	        return ErrorUtil.errorToMessage(MobileService.M300011.getErrCode());
	    }*/
		/*if(ParamCheck.checkIsNull(curriculumScheduleInfo.getTemplateId())){
	        return ErrorUtil.errorToMessage(MobileService.M300012.getErrCode());
	    }*/
		if(ParamCheck.checkLongIsNull(curriculumScheduleInfo.getCurriculumId())){
			if(ParamCheck.checkLongIsNull(curriculumScheduleInfo.getChildId())){
				return ErrorUtil.errorToMessage(MobileService.M300028.getErrCode());
			}
			if(ParamCheck.checkIsNull(curriculumScheduleInfo.getCurriculumTag())){
				return ErrorUtil.errorToMessage(MobileService.M300027.getErrCode());
			}
			if(ParamCheck.checkIsNull(curriculumScheduleInfo.getCurriculumType())){
				return ErrorUtil.errorToMessage(MobileService.M300028.getErrCode());
			}
	    }
		if(ParamCheck.checkIsNull(curriculumScheduleInfo.getCurriculumName())){
	        return ErrorUtil.errorToMessage(MobileService.M300008.getErrCode());
	    }
		if(ParamCheck.checkIsNull(curriculumScheduleInfo.getFirstTime())){
	        return ErrorUtil.errorToMessage(MobileService.M300014.getErrCode());
	    }
		if(ParamCheck.checkIsNull(curriculumScheduleInfo.getStartTime())){
	        return ErrorUtil.errorToMessage(MobileService.M300015.getErrCode());
	    }
		if(ParamCheck.checkIsNull(curriculumScheduleInfo.getEndTime())){
	        return ErrorUtil.errorToMessage(MobileService.M300016.getErrCode());
	    }
		/*if(ParamCheck.checkIsNull(curriculumScheduleInfo.getRemindingTime())){
	        return ErrorUtil.errorToMessage(MobileService.M300017.getErrCode());
	    }*/
		if(ParamCheck.checkIsNull(curriculumScheduleInfo.getRemindingType())){
	        return ErrorUtil.errorToMessage(MobileService.M300018.getErrCode());
	    }
		if(ParamCheck.checkIsNull(curriculumScheduleInfo.getWeekDay())){
	        return ErrorUtil.errorToMessage(MobileService.M300019.getErrCode());
	    }
		//当课程不存在，先创建课程
		if(curriculumScheduleInfo.getCurriculumId()==null||"".equals(curriculumScheduleInfo.getCurriculumId())){
			CalendarCurriculumInfo ccInfo = new CalendarCurriculumInfo();
			ccInfo.setUuid(UUIDTool.getUUID());
			ccInfo.setCreateTime(DateTool.parseDates2(new Date()));
			ccInfo.setOperateTime(DateTool.parseDates2(new Date()));
			if("0".equals(curriculumScheduleInfo.getIsCreateCurriculum())){
				ccInfo.setState(0);
			}
			if("1".equals(curriculumScheduleInfo.getIsCreateCurriculum())){
				//底部标签不存入
				ccInfo.setState(2);
			}
			ccInfo.setCurriculumTag(curriculumScheduleInfo.getCurriculumTag());
			ccInfo.setCurriculumType(curriculumScheduleInfo.getCurriculumType());
			ccInfo.setCalendarChildId(curriculumScheduleInfo.getChildId());
			ccInfo.setCurriculumName(curriculumScheduleInfo.getCurriculumName());
			//插入课程
			calendarCurriculumInfoService.save(ccInfo);
			//读取课程id
			curriculumScheduleInfo.setCurriculumId(ccInfo.getId());
		}
		//操作uuid用来区分是哪次操作
		String operateUuid = UUIDTool.getUUID();
		
		//插入日程定时任务表
		//就这一次
		if("2".equals(curriculumScheduleInfo.getRemindingType())){
			//插入日程（一次日程）
			curriculumScheduleInfo.setUuid(UUIDTool.getUUID());
			curriculumScheduleInfo.setCreateTime(DateTool.parseDates2(new Date()));
			curriculumScheduleInfo.setOperateTime(DateTool.parseDates2(new Date()));
			curriculumScheduleInfo.setState(0);
			curriculumScheduleInfo.setOperateUuid(operateUuid);
			curriculumScheduleInfoService.save(curriculumScheduleInfo);
			//插入通知消息
			CurriculumScheduleTask curriculumScheduleTask = new CurriculumScheduleTask();
			curriculumScheduleTask.setOperateUuid(operateUuid);
			curriculumScheduleTask.setUuid(UUIDTool.getUUID());
			curriculumScheduleTask.setOperateTime(DateTool.parseDates2(new Date()));
			curriculumScheduleTask.setCreateTime(DateTool.parseDates2(new Date()));
			curriculumScheduleTask.setState(0);
			curriculumScheduleTask.setScheduleId(curriculumScheduleInfo.getId());
			curriculumScheduleTask.setCurriculumId(curriculumScheduleInfo.getCurriculumId());
			curriculumScheduleTask.setCurriculumName(curriculumScheduleInfo.getCurriculumName());
			curriculumScheduleTask.setFormId(curriculumScheduleInfo.getFormId());
			curriculumScheduleTask.setToUser(curriculumScheduleInfo.getToUser());
			curriculumScheduleTask.setTemplateId(curriculumScheduleInfo.getTemplateId());
			curriculumScheduleTask.setMessage(curriculumScheduleInfo.getMemorandum());
			curriculumScheduleTask.setPlace(curriculumScheduleInfo.getPlace());
			curriculumScheduleTask.setStartTime(curriculumScheduleInfo.getStartTime());
			curriculumScheduleTask.setEndTime(curriculumScheduleInfo.getEndTime());
			//准时提醒
			if("1".equals(curriculumScheduleInfo.getRemindingTime())){
				String startTime = curriculumScheduleInfo.getFirstTime()+curriculumScheduleInfo.getStartTime();
				curriculumScheduleTask.setSendTime(startTime);
			}
			//提前5分钟提醒
			if("2".equals(curriculumScheduleInfo.getRemindingTime())){
				String startTime = curriculumScheduleInfo.getFirstTime()+curriculumScheduleInfo.getStartTime();
				String sendTime = CalendarTool.getBeforeMinutes(startTime, 5);
				curriculumScheduleTask.setSendTime(sendTime);
			}
			//提前15分钟提醒
			if("3".equals(curriculumScheduleInfo.getRemindingTime())){
				String startTime = curriculumScheduleInfo.getFirstTime()+curriculumScheduleInfo.getStartTime();
				String sendTime = CalendarTool.getBeforeMinutes(startTime, 15);
				curriculumScheduleTask.setSendTime(sendTime);
			}
			//提前30分钟提醒
			if("4".equals(curriculumScheduleInfo.getRemindingTime())){
				String startTime = curriculumScheduleInfo.getFirstTime()+curriculumScheduleInfo.getStartTime();
				String sendTime = CalendarTool.getBeforeMinutes(startTime, 30);
				curriculumScheduleTask.setSendTime(sendTime);
			}
			//提前60分钟提醒
			if("5".equals(curriculumScheduleInfo.getRemindingTime())){
				String startTime = curriculumScheduleInfo.getFirstTime()+curriculumScheduleInfo.getStartTime();
				String sendTime = CalendarTool.getBeforeMinutes(startTime, 60);
				curriculumScheduleTask.setSendTime(sendTime);
			}
			//提前120分钟提醒
			if("6".equals(curriculumScheduleInfo.getRemindingTime())){
				String startTime = curriculumScheduleInfo.getFirstTime()+curriculumScheduleInfo.getStartTime();
				String sendTime = CalendarTool.getBeforeMinutes(startTime, 120);
				curriculumScheduleTask.setSendTime(sendTime);
			}
			//提前一天提醒
			if("7".equals(curriculumScheduleInfo.getRemindingTime())){
				String startTime = curriculumScheduleInfo.getFirstTime()+curriculumScheduleInfo.getStartTime();
				String sendTime = CalendarTool.getBeforeDays(startTime, 1);
				curriculumScheduleTask.setSendTime(sendTime);
			}
			//提前二天提醒
			if("8".equals(curriculumScheduleInfo.getRemindingTime())){
				String startTime = curriculumScheduleInfo.getFirstTime()+curriculumScheduleInfo.getStartTime();
				String sendTime = CalendarTool.getBeforeDays(startTime, 2);
				curriculumScheduleTask.setSendTime(sendTime);
			}
			//提前一周提醒
			if("9".equals(curriculumScheduleInfo.getRemindingTime())){
				String startTime = curriculumScheduleInfo.getFirstTime()+curriculumScheduleInfo.getStartTime();
				String sendTime = CalendarTool.getBeforeDays(startTime, 7);
				curriculumScheduleTask.setSendTime(sendTime);
			}
			//插入定时任务表
			if(!"0".equals(curriculumScheduleInfo.getRemindingTime())){
				curriculumScheduleTaskService.save(curriculumScheduleTask);
			}
		}
		
		//每周固定时刻
		if("1".equals(curriculumScheduleInfo.getRemindingType())){
			//初始计算周期时间向前推相应的时间
			String firstCycleDay = curriculumScheduleInfo.getFirstTime();
			if("7".equals(curriculumScheduleInfo.getRemindingTime())){
			    firstCycleDay = CalendarTool.getBeforeDays1(curriculumScheduleInfo.getFirstTime(), 1);
			}
			if("8".equals(curriculumScheduleInfo.getRemindingTime())){
			    firstCycleDay = CalendarTool.getBeforeDays1(curriculumScheduleInfo.getFirstTime(), 2);
			}
			if("9".equals(curriculumScheduleInfo.getRemindingTime())){
				firstCycleDay = CalendarTool.getBeforeDays1(curriculumScheduleInfo.getFirstTime(), 7);
			}
			List<CurriculumScheduleInfo> curriculumScheduleInfoList = new ArrayList<CurriculumScheduleInfo>();
			//获取今年从传入时间计算的天数之后的每周的这一天
			List<String> timeList = new ArrayList<String>();
			//如果次数为空插入本年的日程
			if(curriculumScheduleInfo.getScheduleInfoNum()==null){
				//插入每周/月 固定时刻日程
				curriculumScheduleInfoList = curriculumScheduleList(firstCycleDay,curriculumScheduleInfo,null,curriculumScheduleInfo.getRemindingType());
				timeList = CalendarTool.getAfterWeekForYear(firstCycleDay);
			}else{
				//插入每周/月 固定时刻日程
				curriculumScheduleInfoList = curriculumScheduleList(firstCycleDay,curriculumScheduleInfo,curriculumScheduleInfo.getScheduleInfoNum(),curriculumScheduleInfo.getRemindingType());
				timeList = CalendarTool.getAfterWeekByNum(firstCycleDay,curriculumScheduleInfo.getScheduleInfoNum());
			}
			//通知列表
			List<CurriculumScheduleTask> curriculumScheduleTaskList = new ArrayList<CurriculumScheduleTask>();
			//循环插入日程的列表
			for(int i=0;i<curriculumScheduleInfoList.size();i++){
				CurriculumScheduleInfo csInfo = new CurriculumScheduleInfo();
				CurriculumScheduleTask csTask = new CurriculumScheduleTask();
				csInfo = curriculumScheduleInfoList.get(i);
				csInfo.setOperateUuid(operateUuid);
				curriculumScheduleInfoService.save(csInfo);
				csTask.setScheduleId(csInfo.getId());
				//准时提醒
				if("1".equals(curriculumScheduleInfo.getRemindingTime())){
					csTask.setSendTime(timeList.get(i)+curriculumScheduleInfo.getStartTime()+"00");
				}
				//提前5分钟
				if("2".equals(curriculumScheduleInfo.getRemindingTime())){
					String hourStr = CalendarTool.getBeforeMinutes1(curriculumScheduleInfo.getStartTime(), 5);
					csTask.setSendTime(timeList.get(i)+hourStr+"00");
				}
				//提前15分钟
				if("3".equals(curriculumScheduleInfo.getRemindingTime())){
					String hourStr = CalendarTool.getBeforeMinutes1(curriculumScheduleInfo.getStartTime(), 15);
					csTask.setSendTime(timeList.get(i)+hourStr+"00");
				}
				//提前30分钟
				if("4".equals(curriculumScheduleInfo.getRemindingTime())){
					String hourStr = CalendarTool.getBeforeMinutes1(curriculumScheduleInfo.getStartTime(), 30);
					csTask.setSendTime(timeList.get(i)+hourStr+"00");
				}
				//提前60分钟
				if("5".equals(curriculumScheduleInfo.getRemindingTime())){
					String hourStr = CalendarTool.getBeforeMinutes1(curriculumScheduleInfo.getStartTime(), 60);
					csTask.setSendTime(timeList.get(i)+hourStr+"00");
				}
				//提前120分钟
				if("6".equals(curriculumScheduleInfo.getRemindingTime())){
					String hourStr = CalendarTool.getBeforeMinutes1(curriculumScheduleInfo.getStartTime(), 120);
					csTask.setSendTime(timeList.get(i)+hourStr+"00");
				}
				//提前一天提醒
				if("7".equals(curriculumScheduleInfo.getRemindingTime())){
					csTask.setSendTime(timeList.get(i)+curriculumScheduleInfo.getStartTime()+"00");
				}
				//提前二天提醒
				if("8".equals(curriculumScheduleInfo.getRemindingTime())){
					csTask.setSendTime(timeList.get(i)+curriculumScheduleInfo.getStartTime()+"00");
				}
				//提前一周提醒
				if("9".equals(curriculumScheduleInfo.getRemindingTime())){
					csTask.setSendTime(timeList.get(i)+curriculumScheduleInfo.getStartTime()+"00");
				}
				csTask.setOperateUuid(operateUuid);
				csTask.setUuid(UUIDTool.getUUID());
				csTask.setOperateTime(DateTool.parseDates2(new Date()));
				csTask.setCreateTime(DateTool.parseDates2(new Date()));
				csTask.setState(0);
				csTask.setCurriculumId(csInfo.getCurriculumId());
				csTask.setCurriculumName(csInfo.getCurriculumName());
				csTask.setFormId(curriculumScheduleInfo.getFormId());
				csTask.setToUser(curriculumScheduleInfo.getToUser());
				csTask.setTemplateId(curriculumScheduleInfo.getTemplateId());
				csTask.setMessage(csInfo.getMemorandum());
				csTask.setPlace(csInfo.getPlace());
				csTask.setStartTime(csInfo.getStartTime());
				csTask.setEndTime(csInfo.getEndTime());
				curriculumScheduleTaskList.add(csTask);
			}
			//插入定时任务表
			//当提醒存在时
			if(!"0".equals(curriculumScheduleInfo.getRemindingTime())){
				curriculumScheduleTaskService.saveList(curriculumScheduleTaskList);
			}
			
		}
		    //每月固定时刻
			if("3".equals(curriculumScheduleInfo.getRemindingType())){
				//初始计算周期时间向前推相应的时间
				String firstCycleDay = curriculumScheduleInfo.getFirstTime();
				if("7".equals(curriculumScheduleInfo.getRemindingTime())){
				    firstCycleDay = CalendarTool.getBeforeDays1(curriculumScheduleInfo.getFirstTime(), 1);
				}
				if("8".equals(curriculumScheduleInfo.getRemindingTime())){
				    firstCycleDay = CalendarTool.getBeforeDays1(curriculumScheduleInfo.getFirstTime(), 2);
				}
				if("9".equals(curriculumScheduleInfo.getRemindingTime())){
					firstCycleDay = CalendarTool.getBeforeDays1(curriculumScheduleInfo.getFirstTime(), 7);
				}
				List<CurriculumScheduleInfo> curriculumScheduleInfoList = new ArrayList<CurriculumScheduleInfo>();
				//获取今年从传入时间计算的天数之后的每周/月的这一天
				List<String> timeList = new ArrayList<String>();
			
				//如果次数为空插入本年的日程
				if(curriculumScheduleInfo.getScheduleInfoNum()==null){
					//插入每月固定时刻日程
					curriculumScheduleInfoList = curriculumScheduleList(firstCycleDay,curriculumScheduleInfo,null,curriculumScheduleInfo.getRemindingType());
					timeList = CalendarTool.getAfterWeekForYear(firstCycleDay);
				}else{
					//插入每月固定时刻日程
					curriculumScheduleInfoList = curriculumScheduleList(firstCycleDay,curriculumScheduleInfo,curriculumScheduleInfo.getScheduleInfoNum(),curriculumScheduleInfo.getRemindingType());
					timeList = CalendarTool.getAfterWeekByNum(firstCycleDay,curriculumScheduleInfo.getScheduleInfoNum());
				}
				//通知列表
				List<CurriculumScheduleTask> curriculumScheduleTaskList = new ArrayList<CurriculumScheduleTask>();
				//循环插入日程的列表
				for(int i=0;i<curriculumScheduleInfoList.size();i++){
					CurriculumScheduleInfo csInfo = new CurriculumScheduleInfo();
					CurriculumScheduleTask csTask = new CurriculumScheduleTask();
					csInfo = curriculumScheduleInfoList.get(i);
					csInfo.setOperateUuid(operateUuid);
					curriculumScheduleInfoService.save(csInfo);
					csTask.setScheduleId(csInfo.getId());
					//准时提醒
					if("1".equals(curriculumScheduleInfo.getRemindingTime())){
						csTask.setSendTime(timeList.get(i)+curriculumScheduleInfo.getStartTime()+"00");
					}
					//提前5分钟
					if("2".equals(curriculumScheduleInfo.getRemindingTime())){
						String hourStr = CalendarTool.getBeforeMinutes1(curriculumScheduleInfo.getStartTime(), 5);
						csTask.setSendTime(timeList.get(i)+hourStr+"00");
					}
					//提前15分钟
					if("3".equals(curriculumScheduleInfo.getRemindingTime())){
						String hourStr = CalendarTool.getBeforeMinutes1(curriculumScheduleInfo.getStartTime(), 15);
						csTask.setSendTime(timeList.get(i)+hourStr+"00");
					}
					//提前30分钟
					if("4".equals(curriculumScheduleInfo.getRemindingTime())){
						String hourStr = CalendarTool.getBeforeMinutes1(curriculumScheduleInfo.getStartTime(), 30);
						csTask.setSendTime(timeList.get(i)+hourStr+"00");
					}
					//提前60分钟
					if("5".equals(curriculumScheduleInfo.getRemindingTime())){
						String hourStr = CalendarTool.getBeforeMinutes1(curriculumScheduleInfo.getStartTime(), 60);
						csTask.setSendTime(timeList.get(i)+hourStr+"00");
					}
					//提前120分钟
					if("6".equals(curriculumScheduleInfo.getRemindingTime())){
						String hourStr = CalendarTool.getBeforeMinutes1(curriculumScheduleInfo.getStartTime(), 120);
						csTask.setSendTime(timeList.get(i)+hourStr+"00");
					}
					//提前一天提醒
					if("7".equals(curriculumScheduleInfo.getRemindingTime())){
						csTask.setSendTime(timeList.get(i)+curriculumScheduleInfo.getStartTime()+"00");
					}
					//提前二天提醒
					if("8".equals(curriculumScheduleInfo.getRemindingTime())){
						csTask.setSendTime(timeList.get(i)+curriculumScheduleInfo.getStartTime()+"00");
					}
					//提前一周提醒
					if("9".equals(curriculumScheduleInfo.getRemindingTime())){
						csTask.setSendTime(timeList.get(i)+curriculumScheduleInfo.getStartTime()+"00");
					}
					csTask.setOperateUuid(operateUuid);
					csTask.setUuid(UUIDTool.getUUID());
					csTask.setOperateTime(DateTool.parseDates2(new Date()));
					csTask.setCreateTime(DateTool.parseDates2(new Date()));
					csTask.setState(0);
					csTask.setCurriculumId(csInfo.getCurriculumId());
					csTask.setCurriculumName(csInfo.getCurriculumName());
					csTask.setFormId(curriculumScheduleInfo.getFormId());
					csTask.setToUser(curriculumScheduleInfo.getToUser());
					csTask.setTemplateId(curriculumScheduleInfo.getTemplateId());
					csTask.setMessage(csInfo.getMemorandum());
					csTask.setPlace(csInfo.getPlace());
					csTask.setStartTime(csInfo.getStartTime());
					csTask.setEndTime(csInfo.getEndTime());
					curriculumScheduleTaskList.add(csTask);
				}
				//插入定时任务表
				//当提醒存在时
				if(!"0".equals(curriculumScheduleInfo.getRemindingTime())){
					curriculumScheduleTaskService.saveList(curriculumScheduleTaskList);
				}
				
			}
			
			//每隔固定天数|每天
			if("4".equals(curriculumScheduleInfo.getRemindingType())||"5".equals(curriculumScheduleInfo.getRemindingType())){
				//初始计算周期时间向前推相应的时间
				String firstCycleDay = curriculumScheduleInfo.getFirstTime();
				if("7".equals(curriculumScheduleInfo.getRemindingTime())){
				    firstCycleDay = CalendarTool.getBeforeDays1(curriculumScheduleInfo.getFirstTime(), 1);
				}
				if("8".equals(curriculumScheduleInfo.getRemindingTime())){
				    firstCycleDay = CalendarTool.getBeforeDays1(curriculumScheduleInfo.getFirstTime(), 2);
				}
				if("9".equals(curriculumScheduleInfo.getRemindingTime())){
					firstCycleDay = CalendarTool.getBeforeDays1(curriculumScheduleInfo.getFirstTime(), 7);
				}
				List<CurriculumScheduleInfo> curriculumScheduleInfoList = new ArrayList<CurriculumScheduleInfo>();
				//获取今年从传入时间计算的天数之后的每周/月/固定天数的这一天
				List<String> timeList = new ArrayList<String>();
			
				//如果次数为空插入本年的日程
				if(curriculumScheduleInfo.getScheduleInfoNum()==null){
					curriculumScheduleInfoList = curriculumScheduleList(firstCycleDay,curriculumScheduleInfo,null,curriculumScheduleInfo.getRemindingType());
					timeList = CalendarTool.getAfterDayByYear(firstCycleDay,curriculumScheduleInfo.getScheduleInfoDay());
				}else{
					curriculumScheduleInfoList = curriculumScheduleList(firstCycleDay,curriculumScheduleInfo,curriculumScheduleInfo.getScheduleInfoNum(),curriculumScheduleInfo.getRemindingType());
					timeList = CalendarTool.getAfterDayByNum(firstCycleDay,curriculumScheduleInfo.getScheduleInfoNum(),curriculumScheduleInfo.getScheduleInfoDay());
				}
				//通知列表
				List<CurriculumScheduleTask> curriculumScheduleTaskList = new ArrayList<CurriculumScheduleTask>();
				//循环插入日程的列表
				for(int i=0;i<curriculumScheduleInfoList.size();i++){
					CurriculumScheduleInfo csInfo = new CurriculumScheduleInfo();
					CurriculumScheduleTask csTask = new CurriculumScheduleTask();
					csInfo = curriculumScheduleInfoList.get(i);
					csInfo.setOperateUuid(operateUuid);
					curriculumScheduleInfoService.save(csInfo);
					csTask.setScheduleId(csInfo.getId());
					//准时提醒
					if("1".equals(curriculumScheduleInfo.getRemindingTime())){
						csTask.setSendTime(timeList.get(i)+curriculumScheduleInfo.getStartTime()+"00");
					}
					//提前5分钟
					if("2".equals(curriculumScheduleInfo.getRemindingTime())){
						String hourStr = CalendarTool.getBeforeMinutes1(curriculumScheduleInfo.getStartTime(), 5);
						csTask.setSendTime(timeList.get(i)+hourStr+"00");
					}
					//提前15分钟
					if("3".equals(curriculumScheduleInfo.getRemindingTime())){
						String hourStr = CalendarTool.getBeforeMinutes1(curriculumScheduleInfo.getStartTime(), 15);
						csTask.setSendTime(timeList.get(i)+hourStr+"00");
					}
					//提前30分钟
					if("4".equals(curriculumScheduleInfo.getRemindingTime())){
						String hourStr = CalendarTool.getBeforeMinutes1(curriculumScheduleInfo.getStartTime(), 30);
						csTask.setSendTime(timeList.get(i)+hourStr+"00");
					}
					//提前60分钟
					if("5".equals(curriculumScheduleInfo.getRemindingTime())){
						String hourStr = CalendarTool.getBeforeMinutes1(curriculumScheduleInfo.getStartTime(), 60);
						csTask.setSendTime(timeList.get(i)+hourStr+"00");
					}
					//提前120分钟
					if("6".equals(curriculumScheduleInfo.getRemindingTime())){
						String hourStr = CalendarTool.getBeforeMinutes1(curriculumScheduleInfo.getStartTime(), 120);
						csTask.setSendTime(timeList.get(i)+hourStr+"00");
					}
					//提前一天提醒
					if("7".equals(curriculumScheduleInfo.getRemindingTime())){
						csTask.setSendTime(timeList.get(i)+curriculumScheduleInfo.getStartTime()+"00");
					}
					//提前二天提醒
					if("8".equals(curriculumScheduleInfo.getRemindingTime())){
						csTask.setSendTime(timeList.get(i)+curriculumScheduleInfo.getStartTime()+"00");
					}
					//提前一周提醒
					if("9".equals(curriculumScheduleInfo.getRemindingTime())){
						csTask.setSendTime(timeList.get(i)+curriculumScheduleInfo.getStartTime()+"00");
					}
					csTask.setOperateUuid(operateUuid);
					csTask.setUuid(UUIDTool.getUUID());
					csTask.setOperateTime(DateTool.parseDates2(new Date()));
					csTask.setCreateTime(DateTool.parseDates2(new Date()));
					csTask.setState(0);
					csTask.setCurriculumId(csInfo.getCurriculumId());
					csTask.setCurriculumName(csInfo.getCurriculumName());
					csTask.setFormId(curriculumScheduleInfo.getFormId());
					csTask.setToUser(curriculumScheduleInfo.getToUser());
					csTask.setTemplateId(curriculumScheduleInfo.getTemplateId());
					csTask.setMessage(csInfo.getMemorandum());
					csTask.setPlace(csInfo.getPlace());
					csTask.setStartTime(csInfo.getStartTime());
					csTask.setEndTime(csInfo.getEndTime());
					curriculumScheduleTaskList.add(csTask);
				}
				//插入定时任务表
				//当提醒存在时
				if(!"0".equals(curriculumScheduleInfo.getRemindingTime())){
					curriculumScheduleTaskService.saveList(curriculumScheduleTaskList);
				}
				
			}
		
			//插入formid和openid关系表
			WeChatUserFormId weChatUserFormId = new WeChatUserFormId();
			weChatUserFormId.setUuid(UUIDTool.getUUID());
			weChatUserFormId.setOperateTime(DateTool.parseDates2(new Date()));
			weChatUserFormId.setCreateTime(DateTool.parseDates2(new Date()));
			weChatUserFormId.setState(0);
		    weChatUserFormId.setFailureTime(CalendarTool.getAfterDays1(DateTool.parseDates2(new Date()),7));
		    weChatUserFormId.setTemplateId(curriculumScheduleInfo.getTemplateId());
		    weChatUserFormId.setOpenId(curriculumScheduleInfo.getToUser());
		    weChatUserFormId.setFormId(curriculumScheduleInfo.getFormId());
		    String sendTime = CalendarTool.getAfterDays1(DateTool.parseDates2(new Date()),7);
		    weChatUserFormId.setSendTime(CalendarTool.getBeforeMinutes2(sendTime, 60));
		    weChatUserFormIdService.save(weChatUserFormId);
			
			ResponseBaseModel responseBaseModel = new ResponseBaseModel();
	        responseBaseModel.setResultObject(curriculumScheduleInfo);
	        responseBaseModel.setResultTime(DateTool.parseDates(new Date())); 
			return JSON.toJSONString(responseBaseModel);
	}
	
	/*
	 * 生成插入的通知消息列表
	 * */
	public List<CurriculumScheduleTask> curriculumScheduleTaskList(String hourStr,String timeStr,CurriculumScheduleInfo curriculumScheduleInfo) throws ParseException{
		List<CurriculumScheduleTask> curriculumScheduleTaskList = new ArrayList<CurriculumScheduleTask>();
		//获取今年从当天之后的每周的这一天
		List<String> timeList = CalendarTool.getAfterWeekForYear(timeStr);
		for(int i=0;i<timeList.size();i++){
			CurriculumScheduleTask curriculumScheduleTask = new CurriculumScheduleTask();
			curriculumScheduleTask.setUuid(UUIDTool.getUUID());
			curriculumScheduleTask.setOperateTime(DateTool.parseDates2(new Date()));
			curriculumScheduleTask.setCreateTime(DateTool.parseDates2(new Date()));
			curriculumScheduleTask.setState(0);
			curriculumScheduleTask.setScheduleId(curriculumScheduleInfo.getId());
			curriculumScheduleTask.setCurriculumId(curriculumScheduleInfo.getCurriculumId());
			curriculumScheduleTask.setCurriculumName(curriculumScheduleInfo.getCurriculumName());
			curriculumScheduleTask.setFormId(curriculumScheduleInfo.getFormId());
			curriculumScheduleTask.setToUser(curriculumScheduleInfo.getToUser());
			curriculumScheduleTask.setTemplateId(curriculumScheduleInfo.getTemplateId());
			curriculumScheduleTask.setMessage(curriculumScheduleInfo.getMemorandum());
			curriculumScheduleTask.setPlace(curriculumScheduleInfo.getPlace());
			curriculumScheduleTask.setStartTime(curriculumScheduleInfo.getStartTime());
			curriculumScheduleTask.setEndTime(curriculumScheduleInfo.getEndTime());
			String sendTime = timeList.get(i)+hourStr+"00";
			curriculumScheduleTask.setSendTime(sendTime);
			curriculumScheduleTaskList.add(curriculumScheduleTask);
		}
		return curriculumScheduleTaskList;
	}
	
	/*
	 * 生成插入的课程列表
	 * */
	public List<CurriculumScheduleInfo> curriculumScheduleList(String timeStr,CurriculumScheduleInfo curriculumScheduleInfo,Integer scheduleInfoNum,String remindingType) throws ParseException{
		List<CurriculumScheduleInfo> curriculumScheduleList = new ArrayList<CurriculumScheduleInfo>();
		List<String> timeList = new ArrayList<String>();
		//获取今年从当天之后的每周/月的这一天
		if(scheduleInfoNum==null){
			//每周
			if("1".equals(remindingType)){
				timeList = CalendarTool.getAfterWeekForYear(timeStr);
			}
			//每月
			if("3".equals(remindingType)){
				timeList = CalendarTool.getAfterMonthByYear(timeStr);
			}
			//每隔多少天
			if("4".equals(remindingType)){
				timeList = CalendarTool.getAfterDayByYear(timeStr,curriculumScheduleInfo.getScheduleInfoDay());
			}
			//每天
			if("5".equals(remindingType)){
				timeList = CalendarTool.getAfterDayByYear(timeStr,curriculumScheduleInfo.getScheduleInfoDay());
			}
		}else{
			//每周
			if("1".equals(remindingType)){
				timeList = CalendarTool.getAfterWeekByNum(timeStr,scheduleInfoNum);
			}
			//每月
			if("3".equals(remindingType)){
				timeList = CalendarTool.getAfterMonthByNum(timeStr,scheduleInfoNum);
			}
			//每隔多少天
			if("4".equals(remindingType)){
				timeList = CalendarTool.getAfterDayByNum(timeStr,scheduleInfoNum,curriculumScheduleInfo.getScheduleInfoDay());
			}
			//每天
			if("5".equals(remindingType)){
				timeList = CalendarTool.getAfterDayByNum(timeStr,scheduleInfoNum,curriculumScheduleInfo.getScheduleInfoDay());
			}
		}
		for(int i=0;i<timeList.size();i++){
			CurriculumScheduleInfo scheduleInfo = new CurriculumScheduleInfo();
			scheduleInfo.setCurriculumId(curriculumScheduleInfo.getCurriculumId());
			scheduleInfo.setCurriculumName(curriculumScheduleInfo.getCurriculumName());
			scheduleInfo.setMemorandum(curriculumScheduleInfo.getMemorandum());
			scheduleInfo.setScheduleInfoColor(curriculumScheduleInfo.getScheduleInfoColor());
			scheduleInfo.setWeekDay(CalendarTool.dateToWeek(timeList.get(i)));
			scheduleInfo.setStartTime(curriculumScheduleInfo.getStartTime());
			scheduleInfo.setEndTime(curriculumScheduleInfo.getEndTime());
			scheduleInfo.setRemindingTime(curriculumScheduleInfo.getRemindingTime());
			scheduleInfo.setRemindingType(curriculumScheduleInfo.getRemindingType());
			scheduleInfo.setScheduleInfoNum(curriculumScheduleInfo.getScheduleInfoNum());
			scheduleInfo.setScheduleInfoDay(curriculumScheduleInfo.getScheduleInfoDay());
			scheduleInfo.setPlace(curriculumScheduleInfo.getPlace());
			scheduleInfo.setLocationAddress(curriculumScheduleInfo.getLocationAddress());
			scheduleInfo.setUuid(UUIDTool.getUUID());
			scheduleInfo.setCreateTime(DateTool.parseDates2(new Date()));
			scheduleInfo.setOperateTime(DateTool.parseDates2(new Date()));
			scheduleInfo.setState(0);
			if("7".equals(curriculumScheduleInfo.getRemindingTime())){
				scheduleInfo.setFirstTime(CalendarTool.getAfterDays2(timeList.get(i), 1)) ;
			}else if ("8".equals(curriculumScheduleInfo.getRemindingTime())) {
				scheduleInfo.setFirstTime(CalendarTool.getAfterDays2(timeList.get(i), 2));
			}else if ("9".equals(curriculumScheduleInfo.getRemindingTime())) {
				scheduleInfo.setFirstTime(CalendarTool.getAfterDays2(timeList.get(i), 7));
			}else{
				scheduleInfo.setFirstTime(timeList.get(i));
			}
			curriculumScheduleList.add(scheduleInfo);
		}
		return curriculumScheduleList;
	}
	/*
	 * 查询孩子下所有日程
	 * */
	@ResponseBody
	@RequestMapping("/queryScheduleInfoList")
	public String queryScheduleInfoList(HttpServletRequest request,HttpServletResponse response,@RequestBody RequestBaseModel params) throws ParseException{
		CurriculumScheduleInfo curriculumScheduleInfo = JSON.parseObject(params.getParams(), CurriculumScheduleInfo.class);
		if(ParamCheck.checkLongIsNull(curriculumScheduleInfo.getChildId())){
	        return ErrorUtil.errorToMessage(MobileService.M300020.getErrCode());
	    }
		//如果没有结束时间默认查7天
		if(curriculumScheduleInfo.getStartTime()!=null&&!"".equals(curriculumScheduleInfo.getStartTime())){
			if(curriculumScheduleInfo.getEndTime()==null||"".equals(curriculumScheduleInfo.getEndTime())){
				curriculumScheduleInfo.setEndTime(CalendarTool.getAfterDays2(curriculumScheduleInfo.getStartTime(), 6));
			}
		}
		CalendarChildInfo calendarChildInfo = new CalendarChildInfo();
		calendarChildInfo.setId(curriculumScheduleInfo.getChildId());
		calendarChildInfo = calendarChildInfoService.queryChildById(calendarChildInfo);
		List<CurriculumScheduleInfo> curriculumScheduleInfoList = curriculumScheduleInfoService.queryScheduleInfoList(curriculumScheduleInfo);
		for(int i=0;i<curriculumScheduleInfoList.size();i++){
			curriculumScheduleInfoList.get(i).setChildHeadPortrait(calendarChildInfo.getChildHeadPortrait());
			curriculumScheduleInfoList.get(i).setChildName(calendarChildInfo.getChildName());
		}
		ResponseBaseModel responseBaseModel = new ResponseBaseModel();
        responseBaseModel.setResultList(curriculumScheduleInfoList);
        responseBaseModel.setResultTime(DateTool.parseDates(new Date())); 
        return JSON.toJSONString(responseBaseModel);
	}
	
	/*
	 * 点击查询孩子下所有日程
	 * */
	@ResponseBody
	@RequestMapping("/pointScheduleInfoList")
	public String pointScheduleInfoList(HttpServletRequest request,HttpServletResponse response,@RequestBody RequestBaseModel params) throws ParseException{
		CurriculumScheduleInfo curriculumScheduleInfo = JSON.parseObject(params.getParams(), CurriculumScheduleInfo.class);
		if(ParamCheck.checkLongIsNull(curriculumScheduleInfo.getChildId())){
	        return ErrorUtil.errorToMessage(MobileService.M300020.getErrCode());
	    }
		//如果没有结束时间默认查7天
		if(curriculumScheduleInfo.getStartTime()!=null&&!"".equals(curriculumScheduleInfo.getStartTime())){
			if(curriculumScheduleInfo.getEndTime()==null||"".equals(curriculumScheduleInfo.getEndTime())){
				curriculumScheduleInfo.setEndTime(CalendarTool.getAfterDays2(curriculumScheduleInfo.getStartTime(), 6));
			}
		}
		CalendarChildInfo calendarChildInfo = new CalendarChildInfo();
		calendarChildInfo.setId(curriculumScheduleInfo.getChildId());
		calendarChildInfo = calendarChildInfoService.queryChildById(calendarChildInfo);
		List<CurriculumScheduleInfo> curriculumScheduleInfoList = curriculumScheduleInfoService.pointScheduleInfoList(curriculumScheduleInfo);
		for(int i=0;i<curriculumScheduleInfoList.size();i++){
			curriculumScheduleInfoList.get(i).setChildHeadPortrait(calendarChildInfo.getChildHeadPortrait());
			curriculumScheduleInfoList.get(i).setChildName(calendarChildInfo.getChildName());
		}
		ResponseBaseModel responseBaseModel = new ResponseBaseModel();
        responseBaseModel.setResultList(curriculumScheduleInfoList);
        responseBaseModel.setResultTime(DateTool.parseDates(new Date())); 
        return JSON.toJSONString(responseBaseModel);
	}
	
	/*
	 *筛选孩子下当天所有日程
	 * */
	@ResponseBody
	@RequestMapping("/screenTodayScheduleInfoList")
	public String screenScheduleInfoList(HttpServletRequest request,HttpServletResponse response,@RequestBody RequestBaseModel params) throws ParseException{
		CurriculumScheduleInfo curriculumScheduleInfo = JSON.parseObject(params.getParams(), CurriculumScheduleInfo.class);
		if(ParamCheck.checkLongIsNull(curriculumScheduleInfo.getChildId())){
	        return ErrorUtil.errorToMessage(MobileService.M300020.getErrCode());
	    }
		CalendarChildInfo calendarChildInfo = new CalendarChildInfo();
		calendarChildInfo.setId(curriculumScheduleInfo.getChildId());
		calendarChildInfo = calendarChildInfoService.queryChildById(calendarChildInfo);
		
		List<CurriculumScheduleInfo> curriculumScheduleInfoList = curriculumScheduleInfoService.screenScheduleInfoList(curriculumScheduleInfo);
		
		for(int i=0;i<curriculumScheduleInfoList.size();i++){
			curriculumScheduleInfoList.get(i).setChildHeadPortrait(calendarChildInfo.getChildHeadPortrait());
			curriculumScheduleInfoList.get(i).setChildName(calendarChildInfo.getChildName());
		}
		ResponseBaseModel responseBaseModel = new ResponseBaseModel();
        responseBaseModel.setResultList(curriculumScheduleInfoList);
        responseBaseModel.setResultTime(DateTool.parseDates(new Date())); 
        return JSON.toJSONString(responseBaseModel);
	}
	
	/*
	 *筛选孩子下非当天所有日程
	 * */
	@ResponseBody
	@RequestMapping("/screenNotTodayScheduleInfoList")
	public String screenNotTodayScheduleInfoList(HttpServletRequest request,HttpServletResponse response,@RequestBody RequestBaseModel params) throws ParseException{
		CurriculumScheduleInfo curriculumScheduleInfo = JSON.parseObject(params.getParams(), CurriculumScheduleInfo.class);
		if(ParamCheck.checkLongIsNull(curriculumScheduleInfo.getChildId())){
	        return ErrorUtil.errorToMessage(MobileService.M300020.getErrCode());
	    }
		CalendarChildInfo calendarChildInfo = new CalendarChildInfo();
		calendarChildInfo.setId(curriculumScheduleInfo.getChildId());
		calendarChildInfo = calendarChildInfoService.queryChildById(calendarChildInfo);
		
		List<CurriculumScheduleInfo> curriculumScheduleInfoList = curriculumScheduleInfoService.screenNotTodayScheduleInfoList(curriculumScheduleInfo);
		List<CurriculumScheduleInfo> cSInfoList = new ArrayList<CurriculumScheduleInfo>();
		if(curriculumScheduleInfoList.size()>2){
			cSInfoList.add(curriculumScheduleInfoList.get(0));
			cSInfoList.add(curriculumScheduleInfoList.get(1));
		}else{
			cSInfoList=curriculumScheduleInfoList;
		}
		for(int i=0;i<cSInfoList.size();i++){
			cSInfoList.get(i).setChildHeadPortrait(calendarChildInfo.getChildHeadPortrait());
			cSInfoList.get(i).setChildName(calendarChildInfo.getChildName());
			cSInfoList.get(i).setSumScheduleInfoNum(curriculumScheduleInfoList.size());
		}
		ResponseBaseModel responseBaseModel = new ResponseBaseModel();
        responseBaseModel.setResultList(cSInfoList);
        responseBaseModel.setResultTime(DateTool.parseDates(new Date())); 
        return JSON.toJSONString(responseBaseModel);
	}
	
    /*查询单个日程*/
	@ResponseBody
	@RequestMapping("/queryScheduleInfo")
	public String queryScheduleInfo(HttpServletRequest request,HttpServletResponse response,@RequestBody RequestBaseModel params) throws ParseException{
		CurriculumScheduleInfo curriculumScheduleInfo = JSON.parseObject(params.getParams(), CurriculumScheduleInfo.class);
		if(ParamCheck.checkLongIsNull(curriculumScheduleInfo.getId())){
	        return ErrorUtil.errorToMessage(MobileService.M300005.getErrCode());
	    }
		List<CurriculumScheduleInfo> curriculumScheduleInfoList = curriculumScheduleInfoService.queryList(curriculumScheduleInfo);
		if(curriculumScheduleInfoList.size()>0){
			ResponseBaseModel responseBaseModel = new ResponseBaseModel();
	        responseBaseModel.setResultObject(curriculumScheduleInfoList.get(0));
	        responseBaseModel.setResultTime(DateTool.parseDates(new Date())); 
	        return JSON.toJSONString(responseBaseModel);
		}else{
			 return ErrorUtil.errorToMessage(MobileService.M300035.getErrCode());
		}
	}
	
	/*
	 * 修改日程
	 * */
	@ResponseBody
	@RequestMapping("/updateScheduleInfo")
	public String updateScheduleInfo(HttpServletRequest request,HttpServletResponse response,@RequestBody RequestBaseModel params) throws ParseException{
		CurriculumScheduleInfo curriculumScheduleInfo = JSON.parseObject(params.getParams(), CurriculumScheduleInfo.class);
		if(ParamCheck.checkLongIsNull(curriculumScheduleInfo.getCurriculumId())){
			if(ParamCheck.checkLongIsNull(curriculumScheduleInfo.getChildId())){
				return ErrorUtil.errorToMessage(MobileService.M300028.getErrCode());
			}
			if(ParamCheck.checkIsNull(curriculumScheduleInfo.getCurriculumTag())){
				return ErrorUtil.errorToMessage(MobileService.M300027.getErrCode());
			}
			if(ParamCheck.checkIsNull(curriculumScheduleInfo.getCurriculumType())){
				return ErrorUtil.errorToMessage(MobileService.M300028.getErrCode());
			}
	    }
		if(ParamCheck.checkIsNull(curriculumScheduleInfo.getCurriculumName())){
	        return ErrorUtil.errorToMessage(MobileService.M300008.getErrCode());
	    }
		if(ParamCheck.checkIsNull(curriculumScheduleInfo.getFirstTime())){
	        return ErrorUtil.errorToMessage(MobileService.M300014.getErrCode());
	    }
		if(ParamCheck.checkIsNull(curriculumScheduleInfo.getStartTime())){
	        return ErrorUtil.errorToMessage(MobileService.M300015.getErrCode());
	    }
		if(ParamCheck.checkIsNull(curriculumScheduleInfo.getEndTime())){
	        return ErrorUtil.errorToMessage(MobileService.M300016.getErrCode());
	    }
		/*if(ParamCheck.checkIsNull(curriculumScheduleInfo.getRemindingTime())){
	        return ErrorUtil.errorToMessage(MobileService.M300017.getErrCode());
	    }*/
		if(ParamCheck.checkIsNull(curriculumScheduleInfo.getRemindingType())){
	        return ErrorUtil.errorToMessage(MobileService.M300018.getErrCode());
	    }
		if(ParamCheck.checkIsNull(curriculumScheduleInfo.getWeekDay())){
	        return ErrorUtil.errorToMessage(MobileService.M300019.getErrCode());
	    }
		
		CurriculumScheduleInfo csScheduleInfo = new CurriculumScheduleInfo();
		csScheduleInfo.setFirstTime(DateTool.parseDates4(new Date()));
		csScheduleInfo.setOperateUuid(curriculumScheduleInfo.getOperateUuid());
		//查询删除日程数据
		curriculumScheduleInfoService.updateListByOperateUuid(curriculumScheduleInfo);
		//查询修改删除日程通知
		CurriculumScheduleTask cSTaskU = new CurriculumScheduleTask();
		cSTaskU.setOperateUuid(curriculumScheduleInfo.getOperateUuid());
		curriculumScheduleTaskService.updateListByOperateUuid(cSTaskU);
		//当课程不存在，先创建课程
		if(curriculumScheduleInfo.getCurriculumId()==null||"".equals(curriculumScheduleInfo.getCurriculumId())){
			CalendarCurriculumInfo ccInfo = new CalendarCurriculumInfo();
			ccInfo.setUuid(UUIDTool.getUUID());
			ccInfo.setCreateTime(DateTool.parseDates2(new Date()));
			ccInfo.setOperateTime(DateTool.parseDates2(new Date()));
			if("0".equals(curriculumScheduleInfo.getIsCreateCurriculum())){
				ccInfo.setState(0);
			}
			if("1".equals(curriculumScheduleInfo.getIsCreateCurriculum())){
				//底部标签不存入
				ccInfo.setState(2);
			}
			ccInfo.setCurriculumTag(curriculumScheduleInfo.getCurriculumTag());
			ccInfo.setCurriculumType(curriculumScheduleInfo.getCurriculumType());
			ccInfo.setCalendarChildId(curriculumScheduleInfo.getChildId());
			ccInfo.setCurriculumName(curriculumScheduleInfo.getCurriculumName());
			//插入课程
			calendarCurriculumInfoService.save(ccInfo);
			//读取课程id
			curriculumScheduleInfo.setCurriculumId(ccInfo.getId());
		}else{
			//修改课程名字
			CalendarCurriculumInfo ccInfo = new CalendarCurriculumInfo();
			ccInfo.setUuid(UUIDTool.getUUID());
			ccInfo.setCreateTime(DateTool.parseDates2(new Date()));
			ccInfo.setOperateTime(DateTool.parseDates2(new Date()));
			ccInfo.setCurriculumName(curriculumScheduleInfo.getCurriculumName());
			ccInfo.setId(curriculumScheduleInfo.getCurriculumId());
			//插入课程
			calendarCurriculumInfoService.update(ccInfo);
		}
		//修改底部标签显示
		if(curriculumScheduleInfo.getCurriculumId()!=null&&"0".equals(curriculumScheduleInfo.getIsCreateCurriculum())){
			CalendarCurriculumInfo ccInfo1 = new CalendarCurriculumInfo();
			ccInfo1.setId(curriculumScheduleInfo.getCurriculumId());
			ccInfo1.setState(0);
			ccInfo1.setOperateTime(DateTool.parseDates2(new Date()));
			ccInfo1.setCurriculumName(curriculumScheduleInfo.getCurriculumName());
			//修改课程
			calendarCurriculumInfoService.update(ccInfo1);
		}
		//操作uuid用来区分是哪次操作
		String operateUuid = UUIDTool.getUUID();
		
		//插入日程定时任务表
		//就这一次
		if("2".equals(curriculumScheduleInfo.getRemindingType())){
			//插入日程（一次日程）
			curriculumScheduleInfo.setUuid(UUIDTool.getUUID());
			curriculumScheduleInfo.setCreateTime(DateTool.parseDates2(new Date()));
			curriculumScheduleInfo.setOperateTime(DateTool.parseDates2(new Date()));
			curriculumScheduleInfo.setState(0);
			curriculumScheduleInfo.setOperateUuid(operateUuid);
			curriculumScheduleInfoService.save(curriculumScheduleInfo);
			//插入通知消息
			CurriculumScheduleTask curriculumScheduleTask = new CurriculumScheduleTask();
			curriculumScheduleTask.setOperateUuid(operateUuid);
			curriculumScheduleTask.setUuid(UUIDTool.getUUID());
			curriculumScheduleTask.setOperateTime(DateTool.parseDates2(new Date()));
			curriculumScheduleTask.setCreateTime(DateTool.parseDates2(new Date()));
			curriculumScheduleTask.setState(0);
			curriculumScheduleTask.setScheduleId(curriculumScheduleInfo.getId());
			curriculumScheduleTask.setCurriculumId(curriculumScheduleInfo.getCurriculumId());
			curriculumScheduleTask.setCurriculumName(curriculumScheduleInfo.getCurriculumName());
			curriculumScheduleTask.setFormId(curriculumScheduleInfo.getFormId());
			curriculumScheduleTask.setToUser(curriculumScheduleInfo.getToUser());
			curriculumScheduleTask.setTemplateId(curriculumScheduleInfo.getTemplateId());
			curriculumScheduleTask.setMessage(curriculumScheduleInfo.getMemorandum());
			curriculumScheduleTask.setPlace(curriculumScheduleInfo.getPlace());
			curriculumScheduleTask.setStartTime(curriculumScheduleInfo.getStartTime());
			curriculumScheduleTask.setEndTime(curriculumScheduleInfo.getEndTime());
			//准时提醒
			if("1".equals(curriculumScheduleInfo.getRemindingTime())){
				String startTime = curriculumScheduleInfo.getFirstTime()+curriculumScheduleInfo.getStartTime();
				curriculumScheduleTask.setSendTime(startTime);
			}
			//提前5分钟提醒
			if("2".equals(curriculumScheduleInfo.getRemindingTime())){
				String startTime = curriculumScheduleInfo.getFirstTime()+curriculumScheduleInfo.getStartTime();
				String sendTime = CalendarTool.getBeforeMinutes(startTime, 5);
				curriculumScheduleTask.setSendTime(sendTime);
			}
			//提前15分钟提醒
			if("3".equals(curriculumScheduleInfo.getRemindingTime())){
				String startTime = curriculumScheduleInfo.getFirstTime()+curriculumScheduleInfo.getStartTime();
				String sendTime = CalendarTool.getBeforeMinutes(startTime, 15);
				curriculumScheduleTask.setSendTime(sendTime);
			}
			//提前30分钟提醒
			if("4".equals(curriculumScheduleInfo.getRemindingTime())){
				String startTime = curriculumScheduleInfo.getFirstTime()+curriculumScheduleInfo.getStartTime();
				String sendTime = CalendarTool.getBeforeMinutes(startTime, 30);
				curriculumScheduleTask.setSendTime(sendTime);
			}
			//提前60分钟提醒
			if("5".equals(curriculumScheduleInfo.getRemindingTime())){
				String startTime = curriculumScheduleInfo.getFirstTime()+curriculumScheduleInfo.getStartTime();
				String sendTime = CalendarTool.getBeforeMinutes(startTime, 60);
				curriculumScheduleTask.setSendTime(sendTime);
			}
			//提前120分钟提醒
			if("6".equals(curriculumScheduleInfo.getRemindingTime())){
				String startTime = curriculumScheduleInfo.getFirstTime()+curriculumScheduleInfo.getStartTime();
				String sendTime = CalendarTool.getBeforeMinutes(startTime, 120);
				curriculumScheduleTask.setSendTime(sendTime);
			}
			//提前一天提醒
			if("7".equals(curriculumScheduleInfo.getRemindingTime())){
				String startTime = curriculumScheduleInfo.getFirstTime()+curriculumScheduleInfo.getStartTime();
				String sendTime = CalendarTool.getBeforeDays(startTime, 1);
				curriculumScheduleTask.setSendTime(sendTime);
			}
			//提前二天提醒
			if("8".equals(curriculumScheduleInfo.getRemindingTime())){
				String startTime = curriculumScheduleInfo.getFirstTime()+curriculumScheduleInfo.getStartTime();
				String sendTime = CalendarTool.getBeforeDays(startTime, 2);
				curriculumScheduleTask.setSendTime(sendTime);
			}
			//提前一周提醒
			if("9".equals(curriculumScheduleInfo.getRemindingTime())){
				String startTime = curriculumScheduleInfo.getFirstTime()+curriculumScheduleInfo.getStartTime();
				String sendTime = CalendarTool.getBeforeDays(startTime, 7);
				curriculumScheduleTask.setSendTime(sendTime);
			}
			//插入定时任务表
			if(!"0".equals(curriculumScheduleInfo.getRemindingTime())){
				curriculumScheduleTaskService.save(curriculumScheduleTask);
			}
		}
		
		    //每周固定时刻
		    if("1".equals(curriculumScheduleInfo.getRemindingType())){
			//初始计算周期时间向前推相应的时间
			String firstCycleDay = curriculumScheduleInfo.getFirstTime();
			if("7".equals(curriculumScheduleInfo.getRemindingTime())){
			    firstCycleDay = CalendarTool.getBeforeDays1(curriculumScheduleInfo.getFirstTime(), 1);
			}
			if("8".equals(curriculumScheduleInfo.getRemindingTime())){
			    firstCycleDay = CalendarTool.getBeforeDays1(curriculumScheduleInfo.getFirstTime(), 2);
			}
			if("9".equals(curriculumScheduleInfo.getRemindingTime())){
				firstCycleDay = CalendarTool.getBeforeDays1(curriculumScheduleInfo.getFirstTime(), 7);
			}
			List<CurriculumScheduleInfo> curriculumScheduleInfoList = new ArrayList<CurriculumScheduleInfo>();
			//获取今年从传入时间计算的天数之后的每周的这一天
			List<String> timeList = new ArrayList<String>();
			//如果次数为空插入本年的日程
			if(curriculumScheduleInfo.getScheduleInfoNum()==null){
				//插入每周/月 固定时刻日程
				curriculumScheduleInfoList = curriculumScheduleList(firstCycleDay,curriculumScheduleInfo,null,curriculumScheduleInfo.getRemindingType());
				timeList = CalendarTool.getAfterWeekForYear(firstCycleDay);
			}else{
				//插入每周/月 固定时刻日程
				curriculumScheduleInfoList = curriculumScheduleList(firstCycleDay,curriculumScheduleInfo,curriculumScheduleInfo.getScheduleInfoNum(),curriculumScheduleInfo.getRemindingType());
				timeList = CalendarTool.getAfterWeekByNum(firstCycleDay,curriculumScheduleInfo.getScheduleInfoNum());
			}
			//通知列表
			List<CurriculumScheduleTask> curriculumScheduleTaskList = new ArrayList<CurriculumScheduleTask>();
			//循环插入日程的列表
			for(int i=0;i<curriculumScheduleInfoList.size();i++){
				CurriculumScheduleInfo csInfo = new CurriculumScheduleInfo();
				CurriculumScheduleTask csTask = new CurriculumScheduleTask();
				csInfo = curriculumScheduleInfoList.get(i);
				csInfo.setOperateUuid(operateUuid);
				curriculumScheduleInfoService.save(csInfo);
				csTask.setScheduleId(csInfo.getId());
				//准时提醒
				if("1".equals(curriculumScheduleInfo.getRemindingTime())){
					csTask.setSendTime(timeList.get(i)+curriculumScheduleInfo.getStartTime()+"00");
				}
				//提前5分钟
				if("2".equals(curriculumScheduleInfo.getRemindingTime())){
					String hourStr = CalendarTool.getBeforeMinutes1(curriculumScheduleInfo.getStartTime(), 5);
					csTask.setSendTime(timeList.get(i)+hourStr+"00");
				}
				//提前15分钟
				if("3".equals(curriculumScheduleInfo.getRemindingTime())){
					String hourStr = CalendarTool.getBeforeMinutes1(curriculumScheduleInfo.getStartTime(), 15);
					csTask.setSendTime(timeList.get(i)+hourStr+"00");
				}
				//提前30分钟
				if("4".equals(curriculumScheduleInfo.getRemindingTime())){
					String hourStr = CalendarTool.getBeforeMinutes1(curriculumScheduleInfo.getStartTime(), 30);
					csTask.setSendTime(timeList.get(i)+hourStr+"00");
				}
				//提前60分钟
				if("5".equals(curriculumScheduleInfo.getRemindingTime())){
					String hourStr = CalendarTool.getBeforeMinutes1(curriculumScheduleInfo.getStartTime(), 60);
					csTask.setSendTime(timeList.get(i)+hourStr+"00");
				}
				//提前120分钟
				if("6".equals(curriculumScheduleInfo.getRemindingTime())){
					String hourStr = CalendarTool.getBeforeMinutes1(curriculumScheduleInfo.getStartTime(), 120);
					csTask.setSendTime(timeList.get(i)+hourStr+"00");
				}
				//提前一天提醒
				if("7".equals(curriculumScheduleInfo.getRemindingTime())){
					csTask.setSendTime(timeList.get(i)+curriculumScheduleInfo.getStartTime()+"00");
				}
				//提前二天提醒
				if("8".equals(curriculumScheduleInfo.getRemindingTime())){
					csTask.setSendTime(timeList.get(i)+curriculumScheduleInfo.getStartTime()+"00");
				}
				//提前一周提醒
				if("9".equals(curriculumScheduleInfo.getRemindingTime())){
					csTask.setSendTime(timeList.get(i)+curriculumScheduleInfo.getStartTime()+"00");
				}
				csTask.setOperateUuid(operateUuid);
				csTask.setUuid(UUIDTool.getUUID());
				csTask.setOperateTime(DateTool.parseDates2(new Date()));
				csTask.setCreateTime(DateTool.parseDates2(new Date()));
				csTask.setState(0);
				csTask.setCurriculumId(csInfo.getCurriculumId());
				csTask.setCurriculumName(csInfo.getCurriculumName());
				csTask.setFormId(curriculumScheduleInfo.getFormId());
				csTask.setToUser(curriculumScheduleInfo.getToUser());
				csTask.setTemplateId(curriculumScheduleInfo.getTemplateId());
				csTask.setMessage(csInfo.getMemorandum());
				csTask.setPlace(csInfo.getPlace());
				csTask.setStartTime(csInfo.getStartTime());
				csTask.setEndTime(csInfo.getEndTime());
				curriculumScheduleTaskList.add(csTask);
			}
			//插入定时任务表
			//当提醒存在时
			if(!"0".equals(curriculumScheduleInfo.getRemindingTime())){
				curriculumScheduleTaskService.saveList(curriculumScheduleTaskList);
			}
			
		}
	    //每月固定时刻
		if("3".equals(curriculumScheduleInfo.getRemindingType())){
			//初始计算周期时间向前推相应的时间
			String firstCycleDay = curriculumScheduleInfo.getFirstTime();
			if("7".equals(curriculumScheduleInfo.getRemindingTime())){
			    firstCycleDay = CalendarTool.getBeforeDays1(curriculumScheduleInfo.getFirstTime(), 1);
			}
			if("8".equals(curriculumScheduleInfo.getRemindingTime())){
			    firstCycleDay = CalendarTool.getBeforeDays1(curriculumScheduleInfo.getFirstTime(), 2);
			}
			if("9".equals(curriculumScheduleInfo.getRemindingTime())){
				firstCycleDay = CalendarTool.getBeforeDays1(curriculumScheduleInfo.getFirstTime(), 7);
			}
			List<CurriculumScheduleInfo> curriculumScheduleInfoList = new ArrayList<CurriculumScheduleInfo>();
			//获取今年从传入时间计算的天数之后的每周/月的这一天
			List<String> timeList = new ArrayList<String>();
		
			//如果次数为空插入本年的日程
			if(curriculumScheduleInfo.getScheduleInfoNum()==null){
				//插入每月固定时刻日程
				curriculumScheduleInfoList = curriculumScheduleList(firstCycleDay,curriculumScheduleInfo,null,curriculumScheduleInfo.getRemindingType());
				timeList = CalendarTool.getAfterWeekForYear(firstCycleDay);
			}else{
				//插入每月固定时刻日程
				curriculumScheduleInfoList = curriculumScheduleList(firstCycleDay,curriculumScheduleInfo,curriculumScheduleInfo.getScheduleInfoNum(),curriculumScheduleInfo.getRemindingType());
				timeList = CalendarTool.getAfterWeekByNum(firstCycleDay,curriculumScheduleInfo.getScheduleInfoNum());
			}
			//通知列表
			List<CurriculumScheduleTask> curriculumScheduleTaskList = new ArrayList<CurriculumScheduleTask>();
			//循环插入日程的列表
			for(int i=0;i<curriculumScheduleInfoList.size();i++){
				CurriculumScheduleInfo csInfo = new CurriculumScheduleInfo();
				CurriculumScheduleTask csTask = new CurriculumScheduleTask();
				csInfo = curriculumScheduleInfoList.get(i);
				csInfo.setOperateUuid(operateUuid);
				curriculumScheduleInfoService.save(csInfo);
				csTask.setScheduleId(csInfo.getId());
				//准时提醒
				if("1".equals(curriculumScheduleInfo.getRemindingTime())){
					csTask.setSendTime(timeList.get(i)+curriculumScheduleInfo.getStartTime()+"00");
				}
				//提前5分钟
				if("2".equals(curriculumScheduleInfo.getRemindingTime())){
					String hourStr = CalendarTool.getBeforeMinutes1(curriculumScheduleInfo.getStartTime(), 5);
					csTask.setSendTime(timeList.get(i)+hourStr+"00");
				}
				//提前15分钟
				if("3".equals(curriculumScheduleInfo.getRemindingTime())){
					String hourStr = CalendarTool.getBeforeMinutes1(curriculumScheduleInfo.getStartTime(), 15);
					csTask.setSendTime(timeList.get(i)+hourStr+"00");
				}
				//提前30分钟
				if("4".equals(curriculumScheduleInfo.getRemindingTime())){
					String hourStr = CalendarTool.getBeforeMinutes1(curriculumScheduleInfo.getStartTime(), 30);
					csTask.setSendTime(timeList.get(i)+hourStr+"00");
				}
				//提前60分钟
				if("5".equals(curriculumScheduleInfo.getRemindingTime())){
					String hourStr = CalendarTool.getBeforeMinutes1(curriculumScheduleInfo.getStartTime(), 60);
					csTask.setSendTime(timeList.get(i)+hourStr+"00");
				}
				//提前120分钟
				if("6".equals(curriculumScheduleInfo.getRemindingTime())){
					String hourStr = CalendarTool.getBeforeMinutes1(curriculumScheduleInfo.getStartTime(), 120);
					csTask.setSendTime(timeList.get(i)+hourStr+"00");
				}
				//提前一天提醒
				if("7".equals(curriculumScheduleInfo.getRemindingTime())){
					csTask.setSendTime(timeList.get(i)+curriculumScheduleInfo.getStartTime()+"00");
				}
				//提前二天提醒
				if("8".equals(curriculumScheduleInfo.getRemindingTime())){
					csTask.setSendTime(timeList.get(i)+curriculumScheduleInfo.getStartTime()+"00");
				}
				//提前一周提醒
				if("9".equals(curriculumScheduleInfo.getRemindingTime())){
					csTask.setSendTime(timeList.get(i)+curriculumScheduleInfo.getStartTime()+"00");
				}
				csTask.setOperateUuid(operateUuid);
				csTask.setUuid(UUIDTool.getUUID());
				csTask.setOperateTime(DateTool.parseDates2(new Date()));
				csTask.setCreateTime(DateTool.parseDates2(new Date()));
				csTask.setState(0);
				csTask.setCurriculumId(csInfo.getCurriculumId());
				csTask.setCurriculumName(csInfo.getCurriculumName());
				csTask.setFormId(curriculumScheduleInfo.getFormId());
				csTask.setToUser(curriculumScheduleInfo.getToUser());
				csTask.setTemplateId(curriculumScheduleInfo.getTemplateId());
				csTask.setMessage(csInfo.getMemorandum());
				csTask.setPlace(csInfo.getPlace());
				csTask.setStartTime(csInfo.getStartTime());
				csTask.setEndTime(csInfo.getEndTime());
				curriculumScheduleTaskList.add(csTask);
			}
			//插入定时任务表
			//当提醒存在时
			if(!"0".equals(curriculumScheduleInfo.getRemindingTime())){
				curriculumScheduleTaskService.saveList(curriculumScheduleTaskList);
			}
			
		}
		
		//每隔固定天数|每天
		if("4".equals(curriculumScheduleInfo.getRemindingType())||"5".equals(curriculumScheduleInfo.getRemindingType())){
			//初始计算周期时间向前推相应的时间
			String firstCycleDay = curriculumScheduleInfo.getFirstTime();
			if("7".equals(curriculumScheduleInfo.getRemindingTime())){
			    firstCycleDay = CalendarTool.getBeforeDays1(curriculumScheduleInfo.getFirstTime(), 1);
			}
			if("8".equals(curriculumScheduleInfo.getRemindingTime())){
			    firstCycleDay = CalendarTool.getBeforeDays1(curriculumScheduleInfo.getFirstTime(), 2);
			}
			if("9".equals(curriculumScheduleInfo.getRemindingTime())){
				firstCycleDay = CalendarTool.getBeforeDays1(curriculumScheduleInfo.getFirstTime(), 7);
			}
			List<CurriculumScheduleInfo> curriculumScheduleInfoList = new ArrayList<CurriculumScheduleInfo>();
			//获取今年从传入时间计算的天数之后的每周/月/固定天数的这一天
			List<String> timeList = new ArrayList<String>();
		
			//如果次数为空插入本年的日程
			if(curriculumScheduleInfo.getScheduleInfoNum()==null){
				curriculumScheduleInfoList = curriculumScheduleList(firstCycleDay,curriculumScheduleInfo,null,curriculumScheduleInfo.getRemindingType());
				timeList = CalendarTool.getAfterDayByYear(firstCycleDay,curriculumScheduleInfo.getScheduleInfoDay());
			}else{
				curriculumScheduleInfoList = curriculumScheduleList(firstCycleDay,curriculumScheduleInfo,curriculumScheduleInfo.getScheduleInfoNum(),curriculumScheduleInfo.getRemindingType());
				timeList = CalendarTool.getAfterDayByNum(firstCycleDay,curriculumScheduleInfo.getScheduleInfoNum(),curriculumScheduleInfo.getScheduleInfoDay());
			}
			//通知列表
			List<CurriculumScheduleTask> curriculumScheduleTaskList = new ArrayList<CurriculumScheduleTask>();
			//循环插入日程的列表
			for(int i=0;i<curriculumScheduleInfoList.size();i++){
				CurriculumScheduleInfo csInfo = new CurriculumScheduleInfo();
				CurriculumScheduleTask csTask = new CurriculumScheduleTask();
				csInfo = curriculumScheduleInfoList.get(i);
				csInfo.setOperateUuid(operateUuid);
				curriculumScheduleInfoService.save(csInfo);
				csTask.setScheduleId(csInfo.getId());
				//准时提醒
				if("1".equals(curriculumScheduleInfo.getRemindingTime())){
					csTask.setSendTime(timeList.get(i)+curriculumScheduleInfo.getStartTime()+"00");
				}
				//提前5分钟
				if("2".equals(curriculumScheduleInfo.getRemindingTime())){
					String hourStr = CalendarTool.getBeforeMinutes1(curriculumScheduleInfo.getStartTime(), 5);
					csTask.setSendTime(timeList.get(i)+hourStr+"00");
				}
				//提前15分钟
				if("3".equals(curriculumScheduleInfo.getRemindingTime())){
					String hourStr = CalendarTool.getBeforeMinutes1(curriculumScheduleInfo.getStartTime(), 15);
					csTask.setSendTime(timeList.get(i)+hourStr+"00");
				}
				//提前30分钟
				if("4".equals(curriculumScheduleInfo.getRemindingTime())){
					String hourStr = CalendarTool.getBeforeMinutes1(curriculumScheduleInfo.getStartTime(), 30);
					csTask.setSendTime(timeList.get(i)+hourStr+"00");
				}
				//提前60分钟
				if("5".equals(curriculumScheduleInfo.getRemindingTime())){
					String hourStr = CalendarTool.getBeforeMinutes1(curriculumScheduleInfo.getStartTime(), 60);
					csTask.setSendTime(timeList.get(i)+hourStr+"00");
				}
				//提前120分钟
				if("6".equals(curriculumScheduleInfo.getRemindingTime())){
					String hourStr = CalendarTool.getBeforeMinutes1(curriculumScheduleInfo.getStartTime(), 120);
					csTask.setSendTime(timeList.get(i)+hourStr+"00");
				}
				//提前一天提醒
				if("7".equals(curriculumScheduleInfo.getRemindingTime())){
					csTask.setSendTime(timeList.get(i)+curriculumScheduleInfo.getStartTime()+"00");
				}
				//提前二天提醒
				if("8".equals(curriculumScheduleInfo.getRemindingTime())){
					csTask.setSendTime(timeList.get(i)+curriculumScheduleInfo.getStartTime()+"00");
				}
				//提前一周提醒
				if("9".equals(curriculumScheduleInfo.getRemindingTime())){
					csTask.setSendTime(timeList.get(i)+curriculumScheduleInfo.getStartTime()+"00");
				}
				csTask.setOperateUuid(operateUuid);
				csTask.setUuid(UUIDTool.getUUID());
				csTask.setOperateTime(DateTool.parseDates2(new Date()));
				csTask.setCreateTime(DateTool.parseDates2(new Date()));
				csTask.setState(0);
				csTask.setCurriculumId(csInfo.getCurriculumId());
				csTask.setCurriculumName(csInfo.getCurriculumName());
				csTask.setFormId(curriculumScheduleInfo.getFormId());
				csTask.setToUser(curriculumScheduleInfo.getToUser());
				csTask.setTemplateId(curriculumScheduleInfo.getTemplateId());
				csTask.setMessage(csInfo.getMemorandum());
				csTask.setPlace(csInfo.getPlace());
				csTask.setStartTime(csInfo.getStartTime());
				csTask.setEndTime(csInfo.getEndTime());
				curriculumScheduleTaskList.add(csTask);
			}
			//插入定时任务表
			//当提醒存在时
			if(!"0".equals(curriculumScheduleInfo.getRemindingTime())){
				curriculumScheduleTaskService.saveList(curriculumScheduleTaskList);
			}
			
		}
		
		
		
		
		ResponseBaseModel responseBaseModel = new ResponseBaseModel();
        responseBaseModel.setResultObject(curriculumScheduleInfo);
        responseBaseModel.setResultTime(DateTool.parseDates(new Date())); 
        return JSON.toJSONString(responseBaseModel);
	}
	
	/*
	 * 删除课程
	 * */
	@ResponseBody
	@RequestMapping("/deleteScheduleInfo")
	public String deleteScheduleInfo(HttpServletRequest request,HttpServletResponse response,@RequestBody RequestBaseModel params) throws ParseException{
		CurriculumScheduleInfo curriculumScheduleInfo = JSON.parseObject(params.getParams(), CurriculumScheduleInfo.class);
		if(ParamCheck.checkLongIsNull(curriculumScheduleInfo.getId())){
	        return ErrorUtil.errorToMessage(MobileService.M300005.getErrCode());
	    }
		//删除单条数据
		if("1".equals(curriculumScheduleInfo.getDeleteType())){
			//查询删除日程数据
			List<CurriculumScheduleInfo> curriculumScheduleInfoList = curriculumScheduleInfoService.queryList(curriculumScheduleInfo);
			curriculumScheduleInfo.setState(1);
			curriculumScheduleInfo.setOperateTime(DateTool.parseDates2(new Date()));
			curriculumScheduleInfoService.update(curriculumScheduleInfo);
			//删除提醒
			if(curriculumScheduleInfoList.size()>0){
				CurriculumScheduleTask curriculumScheduleTask = new CurriculumScheduleTask();
				curriculumScheduleTask.setScheduleId(curriculumScheduleInfoList.get(0).getId());
				List<CurriculumScheduleTask> curriculumScheduleTaskList = curriculumScheduleTaskService.queryByScheduleId(curriculumScheduleTask);
				//存在通知提醒
				if(curriculumScheduleTaskList.size()>0){
					curriculumScheduleTask = curriculumScheduleTaskList.get(0);
					curriculumScheduleTask.setState(1);
					curriculumScheduleTask.setOperateTime(DateTool.parseDates2(new Date()));
					curriculumScheduleTaskService.update(curriculumScheduleTask);
				}
				
			}
		}
		
		//删除全部数据
		if("2".equals(curriculumScheduleInfo.getDeleteType())){
			//查询删除的全部日程数据
			CurriculumScheduleInfo csInfo = new CurriculumScheduleInfo();
			csInfo.setStartTime(curriculumScheduleInfo.getStartTime());
			csInfo.setOperateUuid(curriculumScheduleInfo.getOperateUuid());
		    curriculumScheduleInfoService.updateCsInfoList(csInfo);
			//删除全部日程提醒
			CurriculumScheduleTask csTask = new CurriculumScheduleTask();
			csTask.setStartTime(curriculumScheduleInfo.getStartTime());
			csTask.setOperateUuid(curriculumScheduleInfo.getOperateUuid());
			curriculumScheduleTaskService.updateCsTaskList(csTask);
		}
	
		ResponseBaseModel responseBaseModel = new ResponseBaseModel();
        responseBaseModel.setResultObject(curriculumScheduleInfo);
        responseBaseModel.setResultTime(DateTool.parseDates(new Date())); 
        return JSON.toJSONString(responseBaseModel);
	}
}
