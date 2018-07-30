package com.http.dao;

import java.util.List;
import java.util.Map;

import com.http.model.CalendarChildInfo;

public interface CalendarChildInfoDao extends BaseDao<CalendarChildInfo>{

	public List<CalendarChildInfo> queryByOpenId(Map<String, Object> childParam);
	public CalendarChildInfo queryChildById(CalendarChildInfo calendarChildInfo);
	public List<CalendarChildInfo> queryByOpenIdandLast(Map<String, Object> childParam);
}
