package com.http.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.http.model.CalendarBasicInfo;
import com.http.model.CalendarChildInfo;
import com.http.model.CalendarCurriculumInfo;
import com.http.model.OpenIdInfoModel;
import com.http.model.RequestBaseModel;
import com.http.model.ResponseBaseModel;
import com.http.model.SmsInfo;
import com.http.model.WeChatUserFormId;
import com.http.service.CalendarBasicInfoService;
import com.http.service.CalendarChildInfoService;
import com.http.service.CalendarCurriculumInfoService;
import com.http.service.SmsInfoService;
import com.http.service.WeChatUserFormIdService;
import com.http.util.AESDecodeUtils;
import com.http.util.CalendarTool;
import com.http.util.CommonUtil;
import com.http.util.DateTool;
import com.http.util.ErrorMsg.MobileService;
import com.http.util.ErrorUtil;
import com.http.util.FileUpload;
import com.http.util.ParamCheck;
import com.http.util.SmsUtil;
import com.http.util.UUIDTool;

@Controller
@RequestMapping("/basicInfo")
public class CalendarBasicInfoController {

	@Autowired
	private CalendarBasicInfoService calendarBasicInfoService;
	@Autowired
	private CalendarChildInfoService calendarChildInfoService;
	@Autowired
	private CalendarCurriculumInfoService calendarCurriculumInfoService;
	@Autowired
	private SmsInfoService smsInfoService;
	@Autowired
	private WeChatUserFormIdService weChatUserFormIdService;
	
	/*
	 * 获取用户openid
	 * */
	@ResponseBody
	@RequestMapping("/getOpenId")
	public String getOpenId(HttpServletRequest request,@RequestBody RequestBaseModel params){
		OpenIdInfoModel openIdInfoModel = JSON.parseObject(params.getParams(), OpenIdInfoModel.class);
		String appid = "wx004f12fa0761c01c";
		String secret = "4eb271c623da52d039bf521317d70cda";
		String jsCode = openIdInfoModel.getJsCode();
		String grantType = "authorization_code";
		String result = "";
        String status = "1";
        String msg = "ok";
        String WX_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
        try {
            if(StringUtils.isBlank(jsCode)){
                status = "0";//失败状态
                msg = "code为空";
            }else {
                String requestUrl = WX_URL.replace("APPID", appid).
                        replace("SECRET", secret).replace("JSCODE", jsCode).
                        replace("authorization_code", grantType);
                // 发起GET请求获取凭证
                result = CommonUtil.httpsRequest(requestUrl, "GET", null);
            }
        } catch (Exception e) {
            return "";
        }
        return result;
	}
	
	
	@ResponseBody
	@RequestMapping("/register")
	public String calendarUserRegister(HttpServletRequest request,@RequestBody RequestBaseModel params){
		CalendarBasicInfo calendarBasicInfo = JSON.parseObject(params.getParams(), CalendarBasicInfo.class);
		if(ParamCheck.checkIsNull(calendarBasicInfo.getOpenId())){
	        return ErrorUtil.errorToMessage(MobileService.M300001.getErrCode());
	    }
		if(ParamCheck.checkIsNull(calendarBasicInfo.getWeChatHeadPortrait())){
	        return ErrorUtil.errorToMessage(MobileService.M300002.getErrCode());
	    }
		if(ParamCheck.checkIsNull(calendarBasicInfo.getWeChatName())){
	        return ErrorUtil.errorToMessage(MobileService.M300003.getErrCode());
	    }
		List<CalendarBasicInfo> calendarBasicInfoList = calendarBasicInfoService.queryByObject(calendarBasicInfo);
		if(calendarBasicInfoList.size()>0){
			return ErrorUtil.errorToMessage(MobileService.M300036.getErrCode());
		}
		calendarBasicInfo.setUuid(UUIDTool.getUUID());
		calendarBasicInfo.setCreateTime(DateTool.parseDates2(new Date()));
		calendarBasicInfo.setOperateTime(DateTool.parseDates2(new Date()));
		calendarBasicInfo.setState(0);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		String nowDate = simpleDateFormat.format(new Date());
		calendarBasicInfo.setLastLoginTime(nowDate);
		calendarBasicInfoService.save(calendarBasicInfo);
		ResponseBaseModel responseBaseModel = new ResponseBaseModel();
        responseBaseModel.setResultObject(calendarBasicInfo);
        responseBaseModel.setResultTime(DateTool.parseDates(new Date())); 
        System.out.println("cea");
		return JSON.toJSONString(responseBaseModel);
	}
	
	@ResponseBody
	@RequestMapping("/queryCalendarUser")
	public String queryCalendarUser(HttpServletRequest request,@RequestBody RequestBaseModel params){
		CalendarBasicInfo calendarBasicInfo = JSON.parseObject(params.getParams(), CalendarBasicInfo.class);
		if(ParamCheck.checkIsNull(calendarBasicInfo.getOpenId())){
	        return ErrorUtil.errorToMessage(MobileService.M300001.getErrCode());
	    }
		List<CalendarBasicInfo> calendarBasicInfoList = calendarBasicInfoService.queryByObject(calendarBasicInfo);
		if(calendarBasicInfoList.size()>0){
			//用户已被注册
			ResponseBaseModel responseBaseModel = new ResponseBaseModel();
		    responseBaseModel.setResultObject(calendarBasicInfoList.get(0));
		    responseBaseModel.setResultTime(DateTool.parseDates(new Date())); 
		    return JSON.toJSONString(responseBaseModel, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
		}else{
			return ErrorUtil.errorToMessage(MobileService.M300033.getErrCode());
		}
	}
	
	@ResponseBody
	@RequestMapping("/queryLastLogin")
	public String queryLastLogin(HttpServletRequest request,@RequestBody RequestBaseModel params){
		CalendarBasicInfo calendarBasicInfo = JSON.parseObject(params.getParams(), CalendarBasicInfo.class);
		if(ParamCheck.checkIsNull(calendarBasicInfo.getOpenId())){
	        return ErrorUtil.errorToMessage(MobileService.M300001.getErrCode());
	    }
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		String nowDate = simpleDateFormat.format(new Date());
		calendarBasicInfo.setLastLoginTime(nowDate);
		List<CalendarBasicInfo> calendarBasicInfoList = calendarBasicInfoService.queryLastLoginByObject(calendarBasicInfo);
		//修改最后登录时间
		calendarBasicInfoService.update(calendarBasicInfo);
		if(calendarBasicInfoList.size()>0){
		    //用户今天登录过
			return ErrorUtil.errorToMessage(MobileService.M300037.getErrCode());
		}else{
			//用户今天未登录
			return ErrorUtil.errorToMessage(MobileService.M300038.getErrCode());
		}
	}
	
	@ResponseBody
	@RequestMapping("/saveWeChatFormId")
	public String saveWeChatFormId(HttpServletRequest request,@RequestBody RequestBaseModel params) throws ParseException{
		WeChatUserFormId weChatUserFormId = JSON.parseObject(params.getParams(), WeChatUserFormId.class);
		if(ParamCheck.checkIsNull(weChatUserFormId.getOpenId())){
           return ErrorUtil.errorToMessage(MobileService.M300001.getErrCode());
        }
	    if(ParamCheck.checkIsNull(weChatUserFormId.getFormId())){
           return ErrorUtil.errorToMessage(MobileService.M300011.getErrCode());
        }
	    if(ParamCheck.checkIsNull(weChatUserFormId.getTemplateId())){
           return ErrorUtil.errorToMessage(MobileService.M300012.getErrCode());
        }
		//插入formid和openid关系表
		weChatUserFormId.setUuid(UUIDTool.getUUID());
		weChatUserFormId.setOperateTime(DateTool.parseDates2(new Date()));
		weChatUserFormId.setCreateTime(DateTool.parseDates2(new Date()));
		weChatUserFormId.setState(0);
	    weChatUserFormId.setFailureTime(CalendarTool.getAfterDays1(DateTool.parseDates2(new Date()),7));
	    String sendTime = CalendarTool.getAfterDays1(DateTool.parseDates2(new Date()),7);
	    weChatUserFormId.setSendTime(CalendarTool.getBeforeMinutes2(sendTime, 60));
	    weChatUserFormIdService.save(weChatUserFormId);
	    ResponseBaseModel responseBaseModel = new ResponseBaseModel();
        responseBaseModel.setResultObject(weChatUserFormId);
        responseBaseModel.setResultTime(DateTool.parseDates(new Date())); 
		return JSON.toJSONString(responseBaseModel);
	}
	
	@ResponseBody
	@RequestMapping("/updateCalendarUser")
	public String updateCalendarUser(HttpServletRequest request,@RequestBody RequestBaseModel params) throws Exception{
		CalendarBasicInfo calendarBasicInfo = JSON.parseObject(params.getParams(), CalendarBasicInfo.class);
		if(ParamCheck.checkIsNull(calendarBasicInfo.getOpenId())){
	        return ErrorUtil.errorToMessage(MobileService.M300001.getErrCode());
	    }
		/*if(ParamCheck.checkIsNull(calendarBasicInfo.getSmsCode())){
	        return ErrorUtil.errorToMessage(MobileService.M300025.getErrCode());
	    }
		SmsInfo smsInfo = new SmsInfo();
		smsInfo.setUuid(calendarBasicInfo.getSmsUuid());
		smsInfo.setSmsCode(calendarBasicInfo.getSmsCode());
		smsInfo.setFailureTime(DateTool.parseDates2(new Date()));
		List<SmsInfo> smsInfoList = smsInfoService.queryList(smsInfo);
		if(smsInfoList.size()>0){
		}else{
			return ErrorUtil.errorToMessage(MobileService.M300026.getErrCode());
		}*/
		//解密电话号码
		String phoneNumber = getWechatPhone(calendarBasicInfo.getJsCode(),calendarBasicInfo.getEncrypData(), calendarBasicInfo.getIvData());
		calendarBasicInfo.setWeChatMobile(phoneNumber);
		calendarBasicInfo.setOperateTime(DateTool.parseDates2(new Date()));
		calendarBasicInfoService.update(calendarBasicInfo);
		ResponseBaseModel responseBaseModel = new ResponseBaseModel();
        responseBaseModel.setResultObject(calendarBasicInfo);
        responseBaseModel.setResultTime(DateTool.parseDates(new Date())); 
		return JSON.toJSONString(responseBaseModel);
	}
	
	@ResponseBody
	@RequestMapping("/sendSms")
	public String sendSms(HttpServletRequest request,@RequestBody RequestBaseModel params) throws ServerException, ClientException, ParseException{
		SmsInfo smsInfo = JSON.parseObject(params.getParams(), SmsInfo.class);
		if(ParamCheck.checkIsNull(smsInfo.getMobile())){
	        return ErrorUtil.errorToMessage(MobileService.M300021.getErrCode());
	    }
		if(ParamCheck.checkIsNull(smsInfo.getSignName())){
	        return ErrorUtil.errorToMessage(MobileService.M300022.getErrCode());
	    }
		if(ParamCheck.checkIsNull(smsInfo.getTemplateCode())){
	        return ErrorUtil.errorToMessage(MobileService.M300023.getErrCode());
	    }
		int smsCode = (int)((Math.random()*9+1)*100000);
		String smsCodeStr = String.valueOf(smsCode);
		//发送短信
		SmsUtil.sendMessage(smsInfo.getMobile(),smsCodeStr,smsInfo.getSignName(),smsInfo.getTemplateCode());
		//存入短信信息
		smsInfo.setUuid(UUIDTool.getUUID());
		smsInfo.setCreateTime(DateTool.parseDates2(new Date()));
		smsInfo.setOperateTime(DateTool.parseDates2(new Date()));
		smsInfo.setState(0);
		smsInfo.setSmsCode(smsCodeStr);
		smsInfo.setSendTime(DateTool.parseDates2(new Date()));
		String failureTime = CalendarTool.getAfterMinutes(DateTool.parseDates2(new Date()), 30);
		smsInfo.setFailureTime(failureTime);
		//插入短信信息
		smsInfoService.save(smsInfo);
		
		ResponseBaseModel responseBaseModel = new ResponseBaseModel();
        responseBaseModel.setResultObject(smsInfo);
        responseBaseModel.setResultTime(DateTool.parseDates(new Date())); 
		return JSON.toJSONString(responseBaseModel);
	}
	
	@ResponseBody
	@RequestMapping("/queryIsOpenPlan")
	public String queryIsOpenPlan(HttpServletRequest request,@RequestBody RequestBaseModel params){
		CalendarBasicInfo calendarBasicInfo = JSON.parseObject(params.getParams(), CalendarBasicInfo.class);
		if(ParamCheck.checkIsNull(calendarBasicInfo.getOpenId())){
	        return ErrorUtil.errorToMessage(MobileService.M300001.getErrCode());
	    }
		Map<String, Object> childParam = new HashMap<String, Object>();
		childParam.put("openId", calendarBasicInfo.getOpenId());
		List<CalendarChildInfo> childInfoList = calendarChildInfoService.queryByOpenId(childParam);
		if(childInfoList.size()>0){
			return ErrorUtil.errorToMessage(MobileService.M300031.getErrCode());
		}else{
			return ErrorUtil.errorToMessage(MobileService.M300032.getErrCode());
		}
	}
	
	@ResponseBody
	@RequestMapping("/openPlan")
	public String calendarOpenPlan(HttpServletRequest request,@RequestBody RequestBaseModel params) throws IOException{
		CalendarChildInfo calendarChildInfo = JSON.parseObject(params.getParams(), CalendarChildInfo.class);
		if(ParamCheck.checkIsNull(calendarChildInfo.getOpenId())){
	        return ErrorUtil.errorToMessage(MobileService.M300001.getErrCode());
	    }
		if(ParamCheck.checkTwoAge(calendarChildInfo.getBoyNum(),calendarChildInfo.getGirlNum())){
	        return ErrorUtil.errorToMessage(MobileService.M300004.getErrCode());
	    }
		//判断验证码是否有用
		/*if(ParamCheck.checkIsNull(calendarChildInfo.getSmsUuid())){
	        return ErrorUtil.errorToMessage(MobileService.M300024.getErrCode());
	    }*/
		/*if(ParamCheck.checkIsNull(calendarChildInfo.getSmsCode())){
	        return ErrorUtil.errorToMessage(MobileService.M300025.getErrCode());
	    }*/
		/*SmsInfo smsInfo = new SmsInfo();
		smsInfo.setUuid(calendarChildInfo.getSmsUuid());
		smsInfo.setSmsCode(calendarChildInfo.getSmsCode());
		smsInfo.setFailureTime(DateTool.parseDates2(new Date()));
		List<SmsInfo> smsInfoList = smsInfoService.queryList(smsInfo);
		if(smsInfoList.size()>0){
		}else{
			return ErrorUtil.errorToMessage(MobileService.M300026.getErrCode());
		}*/
		//根据openId取出用户信息
		/*CalendarBasicInfo calendarBasicInfo = new CalendarBasicInfo();
		calendarBasicInfo.setOpenId(calendarChildInfo.getOpenId());
		List<CalendarBasicInfo> calendarBasicInfos = calendarBasicInfoService.queryByObject(calendarBasicInfo);
		if(calendarBasicInfos.size()>0){
		     calendarBasicInfo = calendarBasicInfos.get(0);
		     calendarBasicInfo.setWeChatMobile(calendarChildInfo.getWeChatMobile());
		     calendarBasicInfo.setOperateTime(DateTool.parseDates2(new Date()));
		     //电话号码插入微信账户信息表
		     calendarBasicInfoService.update(calendarBasicInfo);
		}else{
			 return ErrorUtil.errorToMessage(MobileService.M900001.getErrCode());
		}*/
		//查询是否开启宝贝计划
		Map<String, Object> cParam = new HashMap<String, Object>();
		cParam.put("openId", calendarChildInfo.getOpenId());
		List<CalendarChildInfo> childInfoList = calendarChildInfoService.queryByOpenId(cParam);
		if(childInfoList.size()>0){
			return ErrorUtil.errorToMessage(MobileService.M300031.getErrCode());
		}
		
		Properties properties = new Properties();
        // 使用ClassLoader加载properties配置文件生成对应的输入流
		properties.load(this.getClass().getResourceAsStream("/message.properties"));
        //获取key对应的value值
		String boyImage = properties.getProperty("image_1");
		String girlImage = properties.getProperty("image_2");
		//插入孩子信息和默认课程信息
		if(calendarChildInfo.getBoyNum()!=null && calendarChildInfo.getBoyNum()>0){
			for(int i=0;i<calendarChildInfo.getBoyNum();i++){
				int k = i+1;
				CalendarChildInfo childInfo = new CalendarChildInfo();
				childInfo.setOpenId(calendarChildInfo.getOpenId());
				childInfo.setUuid(UUIDTool.getUUID());
				childInfo.setCreateTime(DateTool.parseDates2(new Date()));
				childInfo.setOperateTime(DateTool.parseDates2(new Date()));
				childInfo.setState(0);
				//男孩
				childInfo.setChildType(0);
				//只有一个孩子
				if(calendarChildInfo.getBoyNum()==1){
					childInfo.setChildName("男宝");
				}else{
					childInfo.setChildName("男宝"+k);
				}
				//服务器
				childInfo.setChildHeadPortrait(boyImage);
				//childInfo.setChildHeadPortrait("http://localhost:18080/educationProgram/static/images/1.jpg");
				if(i==0) {
					childInfo.setLastOperate("1");
				}
				calendarChildInfoService.save(childInfo);
				//插入默认课程信息
				CalendarCurriculumInfo curriculumInfo1 = new CalendarCurriculumInfo();
				curriculumInfo1.setUuid(UUIDTool.getUUID());
				curriculumInfo1.setCalendarChildId(childInfo.getId());
				curriculumInfo1.setCurriculumName("游泳");
				curriculumInfo1.setCurriculumType("1");
				curriculumInfo1.setCurriculumTag("1");
				curriculumInfo1.setState(0);
				curriculumInfo1.setCreateTime(DateTool.parseDates2(new Date()));
				curriculumInfo1.setOperateTime(DateTool.parseDates2(new Date()));
				calendarCurriculumInfoService.save(curriculumInfo1);
				
				CalendarCurriculumInfo curriculumInfo2 = new CalendarCurriculumInfo();
				curriculumInfo2.setUuid(UUIDTool.getUUID());
				curriculumInfo2.setCalendarChildId(childInfo.getId());
				curriculumInfo2.setCurriculumName("英语培训");
				curriculumInfo2.setCurriculumType("1");
				curriculumInfo2.setCurriculumTag("2");
				curriculumInfo2.setState(0);
				curriculumInfo2.setCreateTime(DateTool.parseDates2(new Date()));
				curriculumInfo2.setOperateTime(DateTool.parseDates2(new Date()));
				calendarCurriculumInfoService.save(curriculumInfo2);
				
				CalendarCurriculumInfo curriculumInfo3 = new CalendarCurriculumInfo();
				curriculumInfo3.setUuid(UUIDTool.getUUID());
				curriculumInfo3.setCalendarChildId(childInfo.getId());
				curriculumInfo3.setCurriculumName("钢琴");
				curriculumInfo3.setCurriculumType("1");
				curriculumInfo3.setCurriculumTag("3");
				curriculumInfo3.setState(0);
				curriculumInfo3.setCreateTime(DateTool.parseDates2(new Date()));
				curriculumInfo3.setOperateTime(DateTool.parseDates2(new Date()));
				calendarCurriculumInfoService.save(curriculumInfo3);
			}
		}
		if(calendarChildInfo.getGirlNum()!=null&&calendarChildInfo.getGirlNum()>0){
			for(int i=0;i<calendarChildInfo.getGirlNum();i++){
				int k = i+1;
				CalendarChildInfo childInfo = new CalendarChildInfo();
				childInfo.setOpenId(calendarChildInfo.getOpenId());
				childInfo.setUuid(UUIDTool.getUUID());
				childInfo.setCreateTime(DateTool.parseDates2(new Date()));
				childInfo.setOperateTime(DateTool.parseDates2(new Date()));
				childInfo.setState(0);
				//女孩
				childInfo.setChildType(1);
				//只有一个孩子
				if(calendarChildInfo.getGirlNum()==1){
					childInfo.setChildName("女宝");
				}else{
					childInfo.setChildName("女宝"+k);
				}
				childInfo.setChildHeadPortrait(girlImage);
				//childInfo.setChildHeadPortrait("http://localhost:18080/educationProgram/static/images/2.jpg");
				if(calendarChildInfo.getBoyNum()==null||calendarChildInfo.getBoyNum()==0&&i==0) {
					childInfo.setLastOperate("1");
				}
				calendarChildInfoService.save(childInfo);
				
				//插入默认课程信息
				CalendarCurriculumInfo curriculumInfo1 = new CalendarCurriculumInfo();
				curriculumInfo1.setUuid(UUIDTool.getUUID());
				curriculumInfo1.setCalendarChildId(childInfo.getId());
				curriculumInfo1.setCurriculumName("游泳");
				curriculumInfo1.setCurriculumType("1");
				curriculumInfo1.setCurriculumTag("1");
				curriculumInfo1.setState(0);
				curriculumInfo1.setCreateTime(DateTool.parseDates2(new Date()));
				curriculumInfo1.setOperateTime(DateTool.parseDates2(new Date()));
				calendarCurriculumInfoService.save(curriculumInfo1);
				
				CalendarCurriculumInfo curriculumInfo2 = new CalendarCurriculumInfo();
				curriculumInfo2.setUuid(UUIDTool.getUUID());
				curriculumInfo2.setCalendarChildId(childInfo.getId());
				curriculumInfo2.setCurriculumName("英语培训");
				curriculumInfo2.setCurriculumType("1");
				curriculumInfo2.setCurriculumTag("2");
				curriculumInfo2.setState(0);
				curriculumInfo2.setCreateTime(DateTool.parseDates2(new Date()));
				curriculumInfo2.setOperateTime(DateTool.parseDates2(new Date()));
				calendarCurriculumInfoService.save(curriculumInfo2);
				
				CalendarCurriculumInfo curriculumInfo3 = new CalendarCurriculumInfo();
				curriculumInfo3.setUuid(UUIDTool.getUUID());
				curriculumInfo3.setCalendarChildId(childInfo.getId());
				curriculumInfo3.setCurriculumName("钢琴");
				curriculumInfo3.setCurriculumType("1");
				curriculumInfo3.setCurriculumTag("3");
				curriculumInfo3.setState(0);
				curriculumInfo3.setCreateTime(DateTool.parseDates2(new Date()));
				curriculumInfo3.setOperateTime(DateTool.parseDates2(new Date()));
				calendarCurriculumInfoService.save(curriculumInfo3);
			}
		}
		Map<String, Object> childParam = new HashMap<String, Object>();
		childParam.put("openId", calendarChildInfo.getOpenId());
		List<CalendarChildInfo> calendarChildInfoList = calendarChildInfoService.queryByOpenId(childParam);
		ResponseBaseModel responseBaseModel = new ResponseBaseModel();
        responseBaseModel.setResultList(calendarChildInfoList);
        responseBaseModel.setResultTime(DateTool.parseDates(new Date())); 
		return JSON.toJSONString(responseBaseModel);
	}
	
	@ResponseBody
	@RequestMapping("/updateChild")
	public String updateChild(HttpServletRequest request,HttpServletResponse response,@RequestParam(required=false,value="childUrl")CommonsMultipartFile[] file){
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
	@RequestMapping("/deleteChild")
	public String deleteChild(HttpServletRequest request,HttpServletResponse response,@RequestBody RequestBaseModel params){
		CalendarChildInfo calendarChildInfo = JSON.parseObject(params.getParams(), CalendarChildInfo.class);
		if(ParamCheck.checkLongIsNull(calendarChildInfo.getId())){
	        return ErrorUtil.errorToMessage(MobileService.M300005.getErrCode());
	    }
		calendarChildInfo.setState(1);
		calendarChildInfo.setOperateTime(DateTool.parseDates2(new Date()));
		calendarChildInfoService.update(calendarChildInfo);
		return JSON.toJSONString("删除成功");
	}
	
	private String getWechatPhone(String jsCode,String encrypDataStr, String ivDataStr) throws Exception{
	
		//获取sessionKey
		String appid = "wx004f12fa0761c01c";
		String secret = "4eb271c623da52d039bf521317d70cda";
		String grantType = "authorization_code";
		String result = "";
        String status = "1";
        String msg = "ok";
        String WX_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
        try {
            if(StringUtils.isBlank(jsCode)){
                status = "0";//失败状态
                msg = "code为空";
            }else {
                String requestUrl = WX_URL.replace("APPID", appid).
                        replace("SECRET", secret).replace("JSCODE", jsCode).
                        replace("authorization_code", grantType);
                // 发起GET请求获取凭证
                result = CommonUtil.httpsRequest(requestUrl, "GET", null);
            }
        } catch (Exception e) {
            return "";
        }
        JSONObject obj = JSONObject.parseObject(result);
        String phoneNumber = "";
        if(obj.get("session_key")!=null){
           String sessionKeyStr = obj.get("session_key").toString();
           byte[] encrypData = Base64.decodeBase64(encrypDataStr);  
           byte[] ivData = Base64.decodeBase64(ivDataStr);  
           byte[] sessionKey = Base64.decodeBase64(sessionKeyStr);  
           String userResult = AESDecodeUtils.decrypt(sessionKey, ivData, encrypData);
           JSONObject userObj = JSONObject.parseObject(userResult);
           phoneNumber = userObj.get("phoneNumber").toString();
        }
        
        return phoneNumber;
	}
}
