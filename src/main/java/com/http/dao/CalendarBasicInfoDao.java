package com.http.dao;

import java.util.List;

import com.http.model.CalendarBasicInfo;

public interface CalendarBasicInfoDao extends BaseDao<CalendarBasicInfo> {

	public List<CalendarBasicInfo> queryByObject(CalendarBasicInfo calendarBasicInfo);
	public List<CalendarBasicInfo> queryLastLoginByObject(CalendarBasicInfo calendarBasicInfo);
}
