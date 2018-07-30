package com.http.dao;

import java.util.List;

import com.http.model.CurriculumScheduleInfo;

public interface CurriculumScheduleInfoDao extends BaseDao<CurriculumScheduleInfo>{

	public void saveList(List<CurriculumScheduleInfo> curriculumScheduleInfoList);
	public List<CurriculumScheduleInfo> queryScheduleInfoList(CurriculumScheduleInfo curriculumScheduleInfo);
	public List<CurriculumScheduleInfo> queryList(CurriculumScheduleInfo curriculumScheduleInfo);
	public void updateCsInfoList(CurriculumScheduleInfo curriculumScheduleInfo);
	public void updateListByOperateUuid(CurriculumScheduleInfo curriculumScheduleInfo);
	public List<CurriculumScheduleInfo> screenScheduleInfoList(CurriculumScheduleInfo curriculumScheduleInfo);
	public List<CurriculumScheduleInfo> pointScheduleInfoList(CurriculumScheduleInfo curriculumScheduleInfo);
	public List<CurriculumScheduleInfo> screenNotTodayScheduleInfoList(CurriculumScheduleInfo curriculumScheduleInfo);
}
