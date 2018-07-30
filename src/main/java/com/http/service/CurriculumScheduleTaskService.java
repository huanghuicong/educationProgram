package com.http.service;

import java.util.List;
import java.util.Map;

import com.http.model.CurriculumScheduleTask;

public interface CurriculumScheduleTaskService {
	
	public List<CurriculumScheduleTask> queryByScheduleId(CurriculumScheduleTask curriculumScheduleTask);
	
	public void save(CurriculumScheduleTask curriculumScheduleTask);
	
	public void update(CurriculumScheduleTask curriculumScheduleTask);
	
	public List<CurriculumScheduleTask> queryTaskList(CurriculumScheduleTask curriculumScheduleTask);
	
	public void saveList(List<CurriculumScheduleTask> curriculumScheduleTaskList);
	
	public void updateCurriculumScheduleByUuid(Map<String, Object> param);
	
	public void updateCsTaskList(CurriculumScheduleTask curriculumScheduleTask);
	
	public void updateListByOperateUuid(CurriculumScheduleTask curriculumScheduleTask);
	
}
