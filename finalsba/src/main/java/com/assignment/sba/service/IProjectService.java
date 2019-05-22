package com.assignment.sba.service;

import java.util.List;

import com.assignment.sba.entities.Project;

public interface IProjectService {

	public Project createProject(Project project) ;

	public List<Project> findAll() ;

	public Project findProject(Integer projectId) ;

	public void delete(Integer projectId) ;

	public Project updateProject(Project projectDetails) ;
}
