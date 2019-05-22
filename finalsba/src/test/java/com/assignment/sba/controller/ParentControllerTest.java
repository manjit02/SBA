package com.assignment.sba.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.assignment.sba.AbstractTest;
import com.assignment.sba.entities.Parent;

public class ParentControllerTest extends AbstractTest {

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}
	
	@Test
	public void getAllParents() throws Exception {
		String uri = "/finalsba/allparent";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Parent[] parentlist = super.mapFromJson(content, Parent[].class);
		assertTrue(parentlist.length > 0);
	}
	
	@Test
	public void getParentBy() throws Exception {
		String uri = "/finalsba/viewparent/1";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Parent parent = super.mapFromJson(content, Parent.class);
		assertEquals(1,parent.getParentId());
	}
	
	@Test
	public void createParent() throws Exception {
		String uri = "/finalsba/createparent";
		Parent parent = new Parent();
		parent.setParentTaskId(25);
		
		String inputJson = super.mapToJson(parent);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@Test
	public void updateUser() throws Exception {
		String uri = "/finalsba/editparent/30";
		Parent parent = new Parent();
		parent.setParentTaskId(25);

		String inputJson = super.mapToJson(parent);
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
		ParentController controller = new ParentController();
		assertEquals("/parent/error",controller.getErrorPath());
	}
	
}
