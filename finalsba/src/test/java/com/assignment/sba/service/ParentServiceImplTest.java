package com.assignment.sba.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.assignment.sba.dao.impl.ParentDAOImpl;
import com.assignment.sba.entities.Parent;
import com.assignment.sba.repositories.ParentRepositiryTestStub;
import com.assignment.sba.service.impl.ParentServiceImpl;

public class ParentServiceImplTest {

	private ParentServiceImpl parentService;

	private ParentDAOImpl parentDAO;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		parentService = new ParentServiceImpl();
		parentDAO= new ParentDAOImpl();
		parentService.setParentDAO(parentDAO);
		parentDAO.setParentRepository(new ParentRepositiryTestStub());
		
	}

	/**
	 * Test method for {@link com.cts.casestudy.parentmanager.service.IParentService#createParent(com.cts.casestudy.parentmanager.entities.Parent)}.
	 */
	@Test
	public void testCreateParent() {
		Parent parent= new Parent();
		parent.setParentId(1);
		Parent savedParent=parentService.createParent(parent);
		assertEquals(savedParent.getParentId(),1);
	}

	/**
	 * Test method for {@link com.cts.casestudy.parentmanager.service.IParentService#findAll()}.
	 */
	@Test
	public void testFindAll() {
		List<Parent> parentList=parentService.findAll();
		assertEquals(parentList.size(),1);
	}

	/**
	 * Test method for {@link com.cts.casestudy.parentmanager.service.IParentService#findAll()}.
	 */
	@Test
	public void findParent() {
		Parent parent=parentService.findParent(1);
		assertEquals(parent,null);
	}
	/**
	 * Test method for {@link com.cts.casestudy.parentmanager.service.IParentService#findAll()}.
	 */
	@Test
	public void endParent() {
		Parent parent=parentService.end(1);
		assertEquals(parent,null);
	}
	/**
	 * Test method for {@link com.cts.casestudy.parentmanager.service.IParentService#findAll()}.
	 */
	@Test
	public void updateParent() {
		Parent parent= new Parent();
		parent.setParentId(1);
		assertEquals(parentService.updateParent(parent),null);
	}

}
