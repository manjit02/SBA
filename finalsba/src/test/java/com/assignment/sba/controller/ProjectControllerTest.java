package com.assignment.sba.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.assignment.sba.AbstractTest;
import com.assignment.sba.entities.Project;

public class ProjectControllerTest extends AbstractTest {

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}
	@Test
	public void getAllProjects() throws Exception {
		String uri = "/finalsba/allprojects";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Project[] parentlist = super.mapFromJson(content, Project[].class);
		assertTrue(parentlist.length > 0);
	}
	
	
	@Test
	public void getParentBy() throws Exception {
		String uri = "/finalsba/viewproject/1";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Project parent = super.mapFromJson(content, Project.class);
		assertEquals(1,parent.getProjectId());
	}
	
	@Test
	public void createProject() throws Exception {
		String uri = "/finalsba/createproject";
		Project project = new Project();
		project.setTitle("JunitProject");
		project.setPriority(2);
		project.setStartDate(new Date());
		project.setProjectManagerId(1);
		String inputJson = super.mapToJson(project);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@Test
	public void updateProject() throws Exception {
		String uri = "/finalsba/editproject/30";
		Project project = new Project();
		project.setTitle("JunitProject");
		project.setProjectId(24);
		project.setPriority(2);
		project.setStartDate(new Date());
		project.setEndDate(new Date());
		project.setProjectManagerId(1);
		
		String inputJson = super.mapToJson(project);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@Test
	public void getError() throws Exception {
		String uri = "/finalsba/parent/error";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@Test
	public void getErrorPath() throws Exception {
		ProjectController controller = new ProjectController();
		assertEquals("/project/error",controller.getErrorPath());
	}

}
