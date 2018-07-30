package com.http.dao;

import java.util.List;

import com.http.model.CalendarCurriculumInfo;

public interface CalendarCurriculumInfoDao extends BaseDao<CalendarCurriculumInfo>{

	public List<CalendarCurriculumInfo> queryByObject(CalendarCurriculumInfo calendarCurriculumInfo);
	public List<CalendarCurriculumInfo> queryCurriculumInfoList(CalendarCurriculumInfo calendarCurriculumInfo);
	public void updateByName(CalendarCurriculumInfo calendarCurriculumInfo);
}
