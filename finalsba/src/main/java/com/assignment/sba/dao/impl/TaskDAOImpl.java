package com.assignment.sba.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.assignment.sba.dao.ITaskDAO;
import com.assignment.sba.entities.Task;
import com.assignment.sba.repositories.ITaskRepository;

@Repository
public class TaskDAOImpl implements ITaskDAO {

	
	private ITaskRepository taskRepository;
	
	@Autowired
	public void setTaskRepository(ITaskRepository taskRepository) {
		this.taskRepository=taskRepository;
	}
	/*
	 * Save a Task to DB
	 */
	@Override
	public Task save(Task task) {
		return taskRepository.save(task);
	}
	/*
	 * To search
	 */
	@Override
	public List<Task> findAll(){
		return taskRepository.findAll();
	}
	
	/*
	 * A container object which may or may not contain a non-null value. 
	 * If a value is present, isPresent() will return true 
	 * and get() will return the value. 
	 */
	@Override
	public Optional<Task> findTask(Integer taskId){
		return taskRepository.findById(taskId);
	}
	
	@Override
	public long findNextId(){
		return taskRepository.count()+1;
	}
	
	
}
