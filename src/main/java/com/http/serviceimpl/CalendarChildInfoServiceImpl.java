package com.http.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.http.dao.CalendarChildInfoDao;
import com.http.model.CalendarChildInfo;
import com.http.service.CalendarChildInfoService;

@Service
public class CalendarChildInfoServiceImpl implements CalendarChildInfoService {

	@Autowired
	private CalendarChildInfoDao calendarChildInfoDao;
	@Override
	public void save(CalendarChildInfo calendarChildInfo) {
		calendarChildInfoDao.insert(calendarChildInfo);
	}
	@Override
	public List<CalendarChildInfo> queryByOpenId(Map<String, Object> childParam) {
		return calendarChildInfoDao.queryByOpenId(childParam);
	}
	@Override
	public void update(CalendarChildInfo calendarChildInfo) {
		calendarChildInfoDao.update(calendarChildInfo);
	}
	@Override
	public CalendarChildInfo queryChildById(CalendarChildInfo calendarChildInfo) {
		return calendarChildInfoDao.queryChildById(calendarChildInfo);
	}
	@Override
	public List<CalendarChildInfo> queryByOpenIdandLast(Map<String, Object> childParam) {
		return calendarChildInfoDao.queryByOpenIdandLast(childParam);
	}

}
