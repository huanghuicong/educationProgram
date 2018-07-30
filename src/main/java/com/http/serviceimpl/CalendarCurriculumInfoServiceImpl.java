package com.http.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.http.dao.CalendarCurriculumInfoDao;
import com.http.model.CalendarCurriculumInfo;
import com.http.service.CalendarCurriculumInfoService;
@Service
public class CalendarCurriculumInfoServiceImpl implements CalendarCurriculumInfoService {

	@Autowired
	private CalendarCurriculumInfoDao calendarCurriculumInfoDao;
	@Override
	public void save(CalendarCurriculumInfo calendarCurriculumInfo) {
		calendarCurriculumInfoDao.insert(calendarCurriculumInfo);
	}
	@Override
	public void update(CalendarCurriculumInfo calendarCurriculumInfo) {
		calendarCurriculumInfoDao.update(calendarCurriculumInfo);
	}
	@Override
	public List<CalendarCurriculumInfo> queryByObject(CalendarCurriculumInfo calendarCurriculumInfo) {
		return calendarCurriculumInfoDao.queryByObject(calendarCurriculumInfo);
	}
	@Override
	public List<CalendarCurriculumInfo> queryCurriculumInfoList(CalendarCurriculumInfo calendarCurriculumInfo) {
		return calendarCurriculumInfoDao.queryCurriculumInfoList(calendarCurriculumInfo);
	}
	@Override
	public void updateByName(CalendarCurriculumInfo calendarCurriculumInfo) {
		calendarCurriculumInfoDao.updateByName(calendarCurriculumInfo);
	}

}
