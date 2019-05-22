package com.assignment.sba.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.assignment.sba.dao.IProjectDAO;
import com.assignment.sba.entities.Project;
import com.assignment.sba.repositories.IProjectRepository;

@Repository
public class ProjectDAOImpl implements IProjectDAO {

	
	private IProjectRepository projectRepository;
	
	@Autowired
	public void setProjectRepository(IProjectRepository projectRepository) {
		this.projectRepository=projectRepository;
	}
	/*
	 * Save a Project to DB
	 */
	@Override
	public Project save(Project project) {
		return projectRepository.save(project);
	}
	/*
	 * To search
	 */
	@Override
	public List<Project> findAll(){
		return projectRepository.findAll();
	}
	
	/*
	 * A container object which may or may not contain a non-null value. 
	 * If a value is present, isPresent() will return true 
	 * and get() will return the value. 
	 */
	@Override
	public Optional<Project> findProject(Integer projectId){
		return projectRepository.findById(projectId);
	}
	
	/**
	 * Delete a task from database
	 * @param task
	 */
	@Override
	public void delete(Project project) {
		projectRepository.delete(project);
	}
	
	//update
	
	
}
