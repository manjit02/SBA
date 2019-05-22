package com.assignment.sba.dao;

import java.util.List;
import java.util.Optional;

import com.assignment.sba.entities.Task;

public interface ITaskDAO {

	public Task save(Task task);
	
	public List<Task> findAll();
	
	public Optional<Task> findTask(Integer taskId);
	
	public long findNextId();
}
