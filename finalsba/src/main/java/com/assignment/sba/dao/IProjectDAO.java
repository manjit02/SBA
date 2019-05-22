package com.assignment.sba.dao;

import java.util.List;
import java.util.Optional;

import com.assignment.sba.entities.Project;

public interface IProjectDAO {

	public Project save(Project project);
	
	public List<Project> findAll();
	
	public Optional<Project> findProject(Integer projectId);
	
	public void delete(Project project);
}
