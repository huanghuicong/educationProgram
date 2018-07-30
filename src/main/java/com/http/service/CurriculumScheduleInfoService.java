package com.http.service;

import java.util.List;

import com.http.model.CurriculumScheduleInfo;

public interface CurriculumScheduleInfoService {

	public void save(CurriculumScheduleInfo curriculumScheduleInfo);
	public void update(CurriculumScheduleInfo curriculumScheduleInfo);
	public void saveList(List<CurriculumScheduleInfo> curriculumScheduleInfoList);
	public List<CurriculumScheduleInfo> queryScheduleInfoList(CurriculumScheduleInfo curriculumScheduleInfo);
	public List<CurriculumScheduleInfo> queryList(CurriculumScheduleInfo curriculumScheduleInfo);
	public void updateCsInfoList(CurriculumScheduleInfo curriculumScheduleInfo);
	public void updateListByOperateUuid(CurriculumScheduleInfo curriculumScheduleInfo);
	public List<CurriculumScheduleInfo> screenScheduleInfoList(CurriculumScheduleInfo curriculumScheduleInfo);
	public List<CurriculumScheduleInfo> pointScheduleInfoList(CurriculumScheduleInfo curriculumScheduleInfo);
	public List<CurriculumScheduleInfo> screenNotTodayScheduleInfoList(CurriculumScheduleInfo curriculumScheduleInfo);
	
}
