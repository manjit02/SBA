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
import com.assignment.sba.entities.Task;
public class TaskControllerTest extends AbstractTest {

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void getTaskList() throws Exception {
		String uri = "/finalsba/alltasks";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Task[] tasklist = super.mapFromJson(content, Task[].class);
		assertTrue(tasklist.length > 0);
	}
	
	@Test
	public void getTaskBy() throws Exception {
		String uri = "/finalsba/viewtask/1";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Task task = super.mapFromJson(content, Task.class);
		assertEquals(1,task.getTaskId());
	}

	@Test
	public void createTask() throws Exception {
		String uri = "/finalsba/createtask";
		Task task = new Task();
		task.setProjectId(1);
		task.setTitle("JunitTask");
		task.setStartDate(new Date());
		task.setPriority(1);
		task.setUserId(1);
		task.setParent(false);
		task.setParentId(1);
		task.setStatus("Complete");
		
		String inputJson = super.mapToJson(task);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	public void updateTask() throws Exception {
		String uri = "/finalsba/edittask/25";
		Task task = new Task();
		task.setTitle("Lemon");
		task.setTaskId(25);

		String inputJson = super.mapToJson(task);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	public void endTask() throws Exception {
		String uri = "/finalsba/endtask/25";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@Test
	public void deleteTask() throws Exception {
		String uri = "/finalsba/deleteTask/id/25";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	@Test
	public void getNextId() throws Exception {
		String uri = "/finalsba/nexttaskid";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	@Test
	public void getError() throws Exception {
		String uri = "/finalsba/error";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@Test
	public void getErrorPath() throws Exception {
		TaskController controller = new TaskController();
		assertEquals(controller.getErrorPath(), "/error");
	}
}
