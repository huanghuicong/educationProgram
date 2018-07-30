package com.http.service;

import java.util.List;

import com.http.model.CalendarBasicInfo;

public interface CalendarBasicInfoService {

	public void save(CalendarBasicInfo calendarBasicInfo);
	public void update(CalendarBasicInfo calendarBasicInfo);
	public List<CalendarBasicInfo> queryByObject(CalendarBasicInfo calendarBasicInfo);
	public List<CalendarBasicInfo> queryLastLoginByObject(CalendarBasicInfo calendarBasicInfo);
}
