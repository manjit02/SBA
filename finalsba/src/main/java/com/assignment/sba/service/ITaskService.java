package com.assignment.sba.service;

import java.util.List;

import com.assignment.sba.entities.Task;

public interface ITaskService {

	public Task createTask(Task task) ;

	public List<Task> findAll() ;

	public Task findTask(Integer taskId) ;

	public Task end(Integer taskId) ;

	public Task updateTask(Task taskDetails) ;
	
	public long findNextId();
}
