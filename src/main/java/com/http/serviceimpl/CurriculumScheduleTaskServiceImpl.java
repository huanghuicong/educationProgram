package com.http.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.http.dao.CurriculumScheduleTaskDao;
import com.http.model.CurriculumScheduleTask;
import com.http.service.CurriculumScheduleTaskService;

@Service
public class CurriculumScheduleTaskServiceImpl implements CurriculumScheduleTaskService{

	@Autowired
	private CurriculumScheduleTaskDao curriculumScheduleTaskDao;
	@Override
	public void save(CurriculumScheduleTask curriculumScheduleTask) {
		curriculumScheduleTaskDao.insert(curriculumScheduleTask);
	}
	@Override
	public void saveList(List<CurriculumScheduleTask> curriculumScheduleTaskList) {
		curriculumScheduleTaskDao.saveList(curriculumScheduleTaskList);
	}
	@Override
	public List<CurriculumScheduleTask> queryTaskList(CurriculumScheduleTask curriculumScheduleTask) {
		return curriculumScheduleTaskDao.queryTaskList(curriculumScheduleTask);
	}
	@Override
	public void updateCurriculumScheduleByUuid(Map<String, Object> param) {
		curriculumScheduleTaskDao.updateCurriculumScheduleByUuid(param);
	}
	@Override
	public List<CurriculumScheduleTask> queryByScheduleId(CurriculumScheduleTask curriculumScheduleTask) {
		return curriculumScheduleTaskDao.queryByScheduleId(curriculumScheduleTask);
	}
	@Override
	public void update(CurriculumScheduleTask curriculumScheduleTask) {
		curriculumScheduleTaskDao.update(curriculumScheduleTask);
	}
	@Override
	public void updateCsTaskList(CurriculumScheduleTask curriculumScheduleTask) {
		curriculumScheduleTaskDao.updateCsTaskList(curriculumScheduleTask);
	}
	@Override
	public void updateListByOperateUuid(CurriculumScheduleTask curriculumScheduleTask) {
		curriculumScheduleTaskDao.updateListByOperateUuid(curriculumScheduleTask);
	}

}
