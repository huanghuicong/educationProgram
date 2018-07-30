package com.http.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.http.model.AccessToken;
import com.http.model.CurriculumScheduleTask;
import com.http.model.Template;
import com.http.model.TemplateParam;
import com.http.model.WeChatUserFormId;
import com.http.service.AccessTokenService;
import com.http.service.CurriculumScheduleTaskService;
import com.http.service.WeChatUserFormIdService;
import com.http.util.CalendarTool;
import com.http.util.DateTool;
import com.http.util.HttpUtil;
import com.http.util.WeixinUtil;

public class CalendarTask {

	@Autowired
	private CurriculumScheduleTaskService curriculumScheduleTaskService;
	@Autowired
	private WeChatUserFormIdService weChatUserFormIdService;
	@Autowired
	private AccessTokenService accessTokenService;
	/*
	 * 查询通知消息
	 * */
	public void findCurriculumSchedule() throws ParseException{
		//获取当前有效的accesstoken
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("appId", "wx004f12fa0761c01c");
		List<AccessToken> accessTokenList = accessTokenService.queryList(params);
		AccessToken accessToken = new AccessToken();
		if(accessTokenList.size()>0){
			accessToken = accessTokenList.get(0);
		}else{
			accessToken = WeixinUtil.getAccessToken("wx004f12fa0761c01c", "4eb271c623da52d039bf521317d70cda");
			accessToken.setAppId("wx004f12fa0761c01c");
			accessToken.setCreateTime(DateTool.parseDates2(new Date()));
			accessToken.setState(0);
			accessTokenService.save(accessToken);
		}
		CurriculumScheduleTask curriculumScheduleTask = new CurriculumScheduleTask();
		curriculumScheduleTask.setStartTime(CalendarTool.getBeforeMinutes2(DateTool.parseDates2(new Date()),4));
		curriculumScheduleTask.setEndTime(CalendarTool.getAfterMinutes(DateTool.parseDates2(new Date()),1));
		List<CurriculumScheduleTask> curriculumScheduleTaskList = curriculumScheduleTaskService.queryTaskList(curriculumScheduleTask);
		if(curriculumScheduleTaskList.size()>0){
			//循环发送消息
			for(int i=0;i<curriculumScheduleTaskList.size();i++){
				//获取当前对象的对应formId
				WeChatUserFormId weChatUserFormId = new WeChatUserFormId();
				weChatUserFormId.setOpenId(curriculumScheduleTaskList.get(i).getToUser());
				String formId = "";
				List<WeChatUserFormId> weChatUserFormIdList = weChatUserFormIdService.queryExpireFormIdList(weChatUserFormId);
				if(weChatUserFormIdList.size()>0){
					formId = weChatUserFormIdList.get(0).getFormId();
					weChatUserFormId.setUuid(weChatUserFormIdList.get(0).getUuid());
				}
				String toUser = curriculumScheduleTaskList.get(i).getToUser();
				String templateId = curriculumScheduleTaskList.get(i).getTemplateId();
				String url = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token="+accessToken.getToken();
				Template template = new Template();
				template.setTemplateId(templateId);
				template.setToUser(toUser);
				template.setFormId(formId);
				template.setPage("pages/index/index");
				List<TemplateParam> paras=new ArrayList<TemplateParam>();  
				paras.add(new TemplateParam("keyword1",""+curriculumScheduleTaskList.get(i).getCurriculumName()+"","#FF3333"));  
				StringBuffer timeStr = new StringBuffer(curriculumScheduleTaskList.get(i).getStartTime());  
				timeStr.insert(2,":");  
				paras.add(new TemplateParam("keyword2",""+DateTool.getToday()+" "+timeStr.toString()+"","#0044BB"));
				paras.add(new TemplateParam("keyword3",""+curriculumScheduleTaskList.get(i).getMessage()+"","#0044BB"));
				template.setTemplateParamList(paras);
				String result = HttpUtil.sendPost(url,template.toJSON()); 
				System.out.println(result);
				//发送之后修改状态
				Map<String, Object> scheduleParam = new HashMap<String,Object>();
				scheduleParam.put("uuid", curriculumScheduleTaskList.get(i).getUuid());
				//修改日程发送状态
				curriculumScheduleTaskService.updateCurriculumScheduleByUuid(scheduleParam);
				//修改表单id发送状态
				weChatUserFormIdService.updateExpireFormIdByUuid(weChatUserFormId);
			}
			
		}
	}
	/*
	 *查询快要到期(提前一天提醒)的openID 
	 * */
	public void findExpireOpenId() throws ParseException{
		//获取当前有效的accesstoken
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("appId", "wx004f12fa0761c01c");
		List<AccessToken> accessTokenList = accessTokenService.queryList(params);
		AccessToken accessToken = new AccessToken();
		if(accessTokenList.size()>0){
			accessToken = accessTokenList.get(0);
		}else{
			accessToken = WeixinUtil.getAccessToken("wx004f12fa0761c01c", "4eb271c623da52d039bf521317d70cda");
			accessToken.setAppId("wx004f12fa0761c01c");
			accessToken.setCreateTime(DateTool.parseDates2(new Date()));
			accessToken.setState(0);
			accessTokenService.save(accessToken);
		}
		WeChatUserFormId weChatUserFormId = new WeChatUserFormId();
		String failureTime = CalendarTool.getAfterDays2(DateTool.parseDates4(new Date()),1);
		weChatUserFormId.setFailureTime(failureTime);
		List<WeChatUserFormId> weChatUserFormIdList = weChatUserFormIdService.queryExpireFormIdList(weChatUserFormId);
		if(weChatUserFormIdList.size()>0){
			//循环list发送推送消息
			for(int i=0;i<weChatUserFormIdList.size();i++){
				String toUser = weChatUserFormIdList.get(i).getOpenId();
				String templateId = weChatUserFormIdList.get(i).getTemplateId();
				String formId = weChatUserFormIdList.get(i).getFormId();
				String data = weChatUserFormIdList.get(i).getMessage();
				String url = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token="+accessToken.getToken();
				Template template = new Template();
				template.setTemplateId(templateId);
				template.setToUser(toUser);
				template.setFormId(formId);
				template.setPage("../pages/index/index");
				List<TemplateParam> paras=new ArrayList<TemplateParam>();  
				paras.add(new TemplateParam("keyword1","微信通知到期提醒","#FF3333"));  
				paras.add(new TemplateParam("keyword2",""+DateTool.parseDates(new Date())+"","#0044BB"));
				paras.add(new TemplateParam("keyword3","您的微信通知即将到期","#0044BB"));
				template.setTemplateParamList(paras);
				String result = HttpUtil.sendPost(url,template.toJSON()); 
			}
			//发送之后修改状态
			weChatUserFormIdService.updateExpireFormIdList(weChatUserFormId);
		}
	}
	
	/*
	 * 定时获取access_token传入数据库
	 * */
	public void findAccessToken(){
		AccessToken accessToken = WeixinUtil.getAccessToken("wx004f12fa0761c01c", "4eb271c623da52d039bf521317d70cda");
		accessToken.setAppId("wx004f12fa0761c01c");
		accessToken.setCreateTime(DateTool.parseDates2(new Date()));
		accessToken.setState(0);
		accessTokenService.save(accessToken);
	}
}
