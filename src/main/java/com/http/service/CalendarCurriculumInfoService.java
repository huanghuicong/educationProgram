package com.http.service;

import java.util.List;

import com.http.model.CalendarCurriculumInfo;

public interface CalendarCurriculumInfoService {

	public void save(CalendarCurriculumInfo calendarCurriculumInfo);
	public void update(CalendarCurriculumInfo calendarCurriculumInfo);
	public void updateByName(CalendarCurriculumInfo calendarCurriculumInfo);
	public List<CalendarCurriculumInfo> queryByObject(CalendarCurriculumInfo calendarCurriculumInfo);
	public List<CalendarCurriculumInfo> queryCurriculumInfoList(CalendarCurriculumInfo calendarCurriculumInfo);
}
