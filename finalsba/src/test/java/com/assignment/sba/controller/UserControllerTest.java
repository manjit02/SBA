package com.assignment.sba.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.assignment.sba.AbstractTest;
import com.assignment.sba.entities.User;

public class UserControllerTest extends AbstractTest {

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}
	
	@Test
	public void getUserList() throws Exception {
		String uri = "/finalsba/alluser";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		User[] userlist = super.mapFromJson(content, User[].class);
		assertTrue(userlist.length > 0);
	}
	
	@Test
	public void getActiveUserList() throws Exception {
		String uri = "/finalsba/activeuser";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		User[] userlist = super.mapFromJson(content, User[].class);
		assertTrue(userlist.length > 0);
	}
	
	@Test
	public void getTaskBy() throws Exception {
		String uri = "/finalsba/viewuser/1";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		User User = super.mapFromJson(content, User.class);
		assertEquals(1,User.getUserId());
	}
	
	@Test
	public void createUser() throws Exception {
		String uri = "/finalsba/createuser";
		User user = new User();
		user.setFirstName("JunitTask");
		user.setLastName("JunitTask");
		user.setStatus("Active");
		
		String inputJson = super.mapToJson(user);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	@Test
	public void updateUser() throws Exception {
		String uri = "/finalsba/edituser/27";
		User user = new User();
		user.setUserId(27);
		user.setFirstName("JunitTask");
		user.setLastName("Lemon");
		user.setStatus("Active");
		
		String inputJson = super.mapToJson(user);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	@Test
	public void deleteUser() throws Exception {
		String uri = "/finalsba/deleteUser/id/27";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@Test
	public void getError() throws Exception {
		String uri = "/finalsba/user/error";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@Test
	public void getErrorPath() throws Exception {
		UserController controller = new UserController();
		assertEquals("/user/error",controller.getErrorPath());
	}

}
