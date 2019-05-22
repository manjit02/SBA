package com.assignment.sba.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.assignment.sba.dao.impl.TaskDAOImpl;
import com.assignment.sba.entities.Task;
import com.assignment.sba.repositories.TaskRepositiryTestStub;
import com.assignment.sba.service.impl.TaskServiceImpl;

public class TaskServiceImplTest {

	private TaskServiceImpl taskService;

	private TaskDAOImpl taskDAO;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		taskService = new TaskServiceImpl();
		taskDAO= new TaskDAOImpl();
		taskService.setTaskDAO(taskDAO);
		taskDAO.setTaskRepository(new TaskRepositiryTestStub());
		
	}

	/**
	 * Test method for {@link com.cts.casestudy.taskmanager.service.ITaskService#createTask(com.cts.casestudy.taskmanager.entities.Task)}.
	 */
	@Test
	public void testCreateTask() {
		Task task= new Task();
		task.setTitle("My sample test task");
		Task savedTask=taskService.createTask(task);
		assertEquals(savedTask.getTaskId(),1);
	}

	/**
	 * Test method for {@link com.cts.casestudy.taskmanager.service.ITaskService#findAll()}.
	 */
	@Test
	public void testFindAll() {
		List<Task> taskList=taskService.findAll();
		assertEquals(taskList.size(),1);
	}

	/**
	 * Test method for {@link com.cts.casestudy.taskmanager.service.ITaskService#findAll()}.
	 */
	@Test
	public void findTask() {
		Task task=taskService.findTask(1);
		assertEquals(task,null);
	}
	/**
	 * Test method for {@link com.cts.casestudy.taskmanager.service.ITaskService#findAll()}.
	 */
	@Test
	public void endTask() {
		Task task=taskService.end(1);
		assertEquals(task,null);
	}
	
	/**
	 * Test method for {@link com.cts.casestudy.taskmanager.service.ITaskService#findAll()}.
	 */
	@Test
	public void updateTask() {
		Task task= new Task();
		task.setTitle("My sample test task");
		assertEquals(taskService.updateTask(task),null);
	}

}
