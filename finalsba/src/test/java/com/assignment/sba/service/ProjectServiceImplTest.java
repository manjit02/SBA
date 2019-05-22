package com.assignment.sba.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.assignment.sba.dao.impl.ProjectDAOImpl;
import com.assignment.sba.entities.Project;
import com.assignment.sba.entities.User;
import com.assignment.sba.repositories.ProjectRepositiryTestStub;
import com.assignment.sba.service.impl.ProjectServiceImpl;

public class ProjectServiceImplTest {

	private ProjectServiceImpl projectService;

	private ProjectDAOImpl projectDAO;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		projectService = new ProjectServiceImpl();
		projectDAO= new ProjectDAOImpl();
		projectService.setProjectDAO(projectDAO);
		projectDAO.setProjectRepository(new ProjectRepositiryTestStub());
		
	}

	/**
	 * Test method for {@link com.cts.casestudy.projectmanager.service.IProjectService#createProject(com.cts.casestudy.projectmanager.entities.Project)}.
	 */
	@Test
	public void testCreateProject() {
		Project project= new Project();
		project.setProjectId(1);
		Project savedProject=projectService.createProject(project);
		assertEquals(savedProject.getProjectId(),1);
	}

	/**
	 * Test method for {@link com.cts.casestudy.projectmanager.service.IProjectService#findAll()}.
	 */
	@Test
	public void testFindAll() {
		List<Project> projectList=projectService.findAll();
		assertEquals(projectList.size(),1);
	}

	/**
	 * Test method for {@link com.cts.casestudy.projectmanager.service.IProjectService#findAll()}.
	 */
	@Test
	public void findProject() {
		Project project=projectService.findProject(1);
		assertEquals(project,null);
	}
	/**
	 * Test method for {@link com.cts.casestudy.projectmanager.service.IProjectService#findAll()}.
	 */
	@Test
	public void endProject() {
		projectService.delete(1);
		Project project=projectService.findProject(1);
		assertEquals(project,null);
	}
	/**
	 * Test method for {@link com.cts.casestudy.usermanager.service.IUserService#findAll()}.
	 */
	@Test
	public void updateProject() {
		Project project=new Project();
		project.setProjectId(1);
		assertEquals(projectService.updateProject(project),null);
	}
}
