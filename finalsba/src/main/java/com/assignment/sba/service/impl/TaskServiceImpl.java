package com.assignment.sba.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assignment.sba.service.ITaskService;
import com.assignment.sba.dao.ITaskDAO;
import com.assignment.sba.entities.Task;

@Transactional
@Service
public class TaskServiceImpl implements ITaskService{
	

	
	private ITaskDAO taskDAO;
	
	@Autowired
	public void setTaskDAO(ITaskDAO taskDAO) {
		this.taskDAO = taskDAO;
	}

	/* (non-Javadoc)
	 * @see com.cts.casestudy.taskmanager.service.TaskService#createTask(com.cts.casestudy.taskmanager.entities.Task)
	 */
	public Task createTask(Task task) {
		return taskDAO.save(task);
	}

	/*
	 * To search
	 */
	/* (non-Javadoc)
	 * @see com.cts.casestudy.taskmanager.service.TaskService#findAll()
	 */
	public List<Task> findAll() {
		return taskDAO.findAll();
	}

	/**
	 * @param taskId
	 * @return
	 * @throws TaskNotFoundException
	 */
	public Task findTask(Integer taskId) {
		Optional<Task> optional = taskDAO.findTask(taskId);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	/**
	 * Delete a task from database
	 * 
	 * @param taskId
	 */
	public Task end(Integer taskId) {
		Optional<Task> optional = taskDAO.findTask(taskId);
		if (optional.isPresent()) {
			Task task = optional.get();
			task.setEndDate(new Date());
			task.setStatus("Complete");
			Task updatedTask = createTask(task);
			return updatedTask;
		}
		return null;
	}

	/**
	 * Update an employee if exist
	 * @param taskDetails
	 * @return
	 * @throws TaskNotFoundException
	 */
	public Task updateTask(Task taskDetails) {
		Optional<Task> optional = taskDAO.findTask(new Integer(taskDetails.getTaskId()));
		if (optional.isPresent()) {
			Task task = optional.get();
			task.setEndDate(taskDetails.getEndDate());
			task.setPriority(taskDetails.getPriority());
			task.setParentId(taskDetails.getParentId());
			task.setTitle(taskDetails.getTitle());
			Task updatedTask = createTask(task);
			return updatedTask;
		}
		return null;
	}

	@Override
	public long findNextId() {
		return taskDAO.findNextId();
	}
}
