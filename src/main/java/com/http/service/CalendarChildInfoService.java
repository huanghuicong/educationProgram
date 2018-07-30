package com.http.service;

import java.util.List;
import java.util.Map;

import com.http.model.CalendarChildInfo;

public interface CalendarChildInfoService {

	public void save(CalendarChildInfo calendarChildInfo);
	public void update(CalendarChildInfo calendarChildInfo);
	public List<CalendarChildInfo> queryByOpenId(Map<String, Object> childParam);
	public List<CalendarChildInfo> queryByOpenIdandLast(Map<String, Object> childParam);
	public CalendarChildInfo queryChildById(CalendarChildInfo calendarChildInfo);
}
