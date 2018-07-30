package com.http.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.http.dao.CalendarBasicInfoDao;
import com.http.model.CalendarBasicInfo;
import com.http.service.CalendarBasicInfoService;

@Service
public class CalendarBasicInfoImpl implements CalendarBasicInfoService{

	@Autowired
	private CalendarBasicInfoDao calendarBasicInfoDao;
	@Override
	public void save(CalendarBasicInfo calendarBasicInfo) {
		calendarBasicInfoDao.insert(calendarBasicInfo);
	}
	@Override
	public void update(CalendarBasicInfo calendarBasicInfo) {
		calendarBasicInfoDao.update(calendarBasicInfo);
	}
	@Override
	public List<CalendarBasicInfo> queryByObject(CalendarBasicInfo calendarBasicInfo) {
		return calendarBasicInfoDao.queryByObject(calendarBasicInfo);
	}
	@Override
	public List<CalendarBasicInfo> queryLastLoginByObject(CalendarBasicInfo calendarBasicInfo) {
		return calendarBasicInfoDao.queryLastLoginByObject(calendarBasicInfo);
	}

}
