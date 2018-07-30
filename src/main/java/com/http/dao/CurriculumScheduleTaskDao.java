package com.http.dao;

import java.util.List;
import java.util.Map;

import com.http.model.CurriculumScheduleTask;

public interface CurriculumScheduleTaskDao extends BaseDao<CurriculumScheduleTask>{

	public void saveList(List<CurriculumScheduleTask> curriculumScheduleTaskList);
	
	public void updateCurriculumScheduleByUuid(Map<String, Object> param);
	
	public List<CurriculumScheduleTask> queryTaskList(CurriculumScheduleTask curriculumScheduleTask);
	
	public List<CurriculumScheduleTask> queryByScheduleId(CurriculumScheduleTask curriculumScheduleTask);
	
	public void updateCsTaskList(CurriculumScheduleTask curriculumScheduleTask);
	
	public void updateListByOperateUuid(CurriculumScheduleTask curriculumScheduleTask);
	
}
