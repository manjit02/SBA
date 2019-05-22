package com.assignment.sba.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assignment.sba.dao.IProjectDAO;
import com.assignment.sba.entities.Project;
import com.assignment.sba.service.IProjectService;

@Transactional
@Service
public class ProjectServiceImpl implements IProjectService{
	

	
	private IProjectDAO projectDAO;
	
	@Autowired
	public void setProjectDAO(IProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}

	/* (non-Javadoc)
	 * @see com.cts.casestudy.projectmanager.service.ProjectService#createProject(com.cts.casestudy.projectmanager.entities.Project)
	 */
	public Project createProject(Project project) {
		return projectDAO.save(project);
	}

	/*
	 * To search
	 */
	/* (non-Javadoc)
	 * @see com.cts.casestudy.projectmanager.service.ProjectService#findAll()
	 */
	public List<Project> findAll() {
		return projectDAO.findAll();
	}

	/**
	 * @param projectId
	 * @return
	 */
	public Project findProject(Integer projectId) {
		Optional<Project> optional = projectDAO.findProject(projectId);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	/**
	 * Delete a project from database
	 * 
	 * @param projectId
	 */
	public void delete(Integer projectId) {
		Optional<Project> optional = projectDAO.findProject(projectId);
		if (optional.isPresent()) {
			projectDAO.delete(optional.get());
		}
	}
	

	/**
	 * Update an employee if exist
	 * @param projectDetails
	 * @return
	 */
	public Project updateProject(Project projectDetails) {
		Optional<Project> optional = projectDAO.findProject(new Integer(projectDetails.getProjectId()));
		if (optional.isPresent()) {
			Project project = optional.get();
			project.setEndDate(projectDetails.getEndDate());
			project.setPriority(projectDetails.getPriority());
			project.setStartDate(projectDetails.getStartDate());
			project.setTitle(projectDetails.getTitle());
			project.setProjectManagerId(projectDetails.getProjectManagerId());
			Project updatedProject = createProject(project);
			return updatedProject;
		}
		return null;
	}
}
