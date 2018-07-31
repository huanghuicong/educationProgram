package com.http.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.http.model.TestModel;

import freemarker.template.utility.StringUtil;

@Controller
@RequestMapping("/test")
public class TestController {

	@RequestMapping
	public String calendarUserRegister(){
		Map<String, String> map1 = new HashMap<>();
		return "index";
	}
	
	public static void main(String[] args) {
		Map<String, String> map1 = new HashMap<>();
		Map<String, String> map2 = new Hashtable<>();
		Map<String, String> map3 = new ConcurrentHashMap<>();
		Collections.synchronizedList(new ArrayList<>());
		java.util.List<TestModel> list = Collections.synchronizedList(new ArrayList<TestModel>());
		list.add(new TestModel());
		TestModel testModel = new TestModel();
	/*	pageNo = pageNo == null ? 1 : pageNo;
	    pageCount = pageCount == null ? 20 : pageCount;
	    List<RecommendShopDo> recommendShopDos = this.recommendShopDAO
	    		.findRelateShopTagsCityByPage(cityEncode, (pageNo - 1) * pageCount, pageCount);*/
		System.out.println("12311231aaaaa");
	}
}
