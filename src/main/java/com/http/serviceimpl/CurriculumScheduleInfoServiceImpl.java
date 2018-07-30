package com.http.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.http.dao.CurriculumScheduleInfoDao;
import com.http.model.CurriculumScheduleInfo;
import com.http.service.CurriculumScheduleInfoService;
@Service
public class CurriculumScheduleInfoServiceImpl implements CurriculumScheduleInfoService{

	@Autowired
	private CurriculumScheduleInfoDao curriculumScheduleInfoDao;
	@Override
	public void save(CurriculumScheduleInfo curriculumScheduleInfo) {
		curriculumScheduleInfoDao.insert(curriculumScheduleInfo);
	}
	@Override
	public List<CurriculumScheduleInfo> queryScheduleInfoList(CurriculumScheduleInfo curriculumScheduleInfo) {
		return curriculumScheduleInfoDao.queryScheduleInfoList(curriculumScheduleInfo);
	}
	@Override
	public void saveList(List<CurriculumScheduleInfo> curriculumScheduleInfoList) {
		curriculumScheduleInfoDao.saveList(curriculumScheduleInfoList);
	}
	@Override
	public void update(CurriculumScheduleInfo curriculumScheduleInfo) {
		curriculumScheduleInfoDao.update(curriculumScheduleInfo);
	}
	@Override
	public List<CurriculumScheduleInfo> queryList(CurriculumScheduleInfo curriculumScheduleInfo) {
		return curriculumScheduleInfoDao.queryList(curriculumScheduleInfo);
	}
	@Override
	public void updateCsInfoList(CurriculumScheduleInfo curriculumScheduleInfo) {
		curriculumScheduleInfoDao.updateCsInfoList(curriculumScheduleInfo);
	}
	@Override
	public void updateListByOperateUuid(CurriculumScheduleInfo curriculumScheduleInfo) {
		curriculumScheduleInfoDao.updateListByOperateUuid(curriculumScheduleInfo);
	}
	@Override
	public List<CurriculumScheduleInfo> screenScheduleInfoList(CurriculumScheduleInfo curriculumScheduleInfo) {
		return curriculumScheduleInfoDao.screenScheduleInfoList(curriculumScheduleInfo);
	}
	@Override
	public List<CurriculumScheduleInfo> pointScheduleInfoList(CurriculumScheduleInfo curriculumScheduleInfo) {
		return curriculumScheduleInfoDao.pointScheduleInfoList(curriculumScheduleInfo);
	}
	@Override
	public List<CurriculumScheduleInfo> screenNotTodayScheduleInfoList(CurriculumScheduleInfo curriculumScheduleInfo) {
		return curriculumScheduleInfoDao.screenNotTodayScheduleInfoList(curriculumScheduleInfo);
	}

}
