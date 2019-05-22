package com.assignment.sba.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.assignment.sba.dao.impl.UserDAOImpl;
import com.assignment.sba.entities.User;
import com.assignment.sba.repositories.UserRepositiryTestStub;
import com.assignment.sba.service.impl.UserServiceImpl;

public class UserServiceImplTest {

	private UserServiceImpl userService;

	private UserDAOImpl userDAO;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		userService = new UserServiceImpl();
		userDAO= new UserDAOImpl();
		userService.setUserDAO(userDAO);
		userDAO.setUserRepository(new UserRepositiryTestStub());
		
	}

	/**
	 * Test method for {@link com.cts.casestudy.usermanager.service.IUserService#createUser(com.cts.casestudy.usermanager.entities.User)}.
	 */
	@Test
	public void testCreateUser() {
		User user= new User();
		user.setUserId(1);
		User savedUser=userService.createUser(user);
		assertEquals(savedUser.getUserId(),1);
	}

	/**
	 * Test method for {@link com.cts.casestudy.usermanager.service.IUserService#findAll()}.
	 */
	@Test
	public void testFindAll() {
		List<User> userList=userService.findAll();
		assertEquals(userList.size(),1);
	}

	/**
	 * Test method for {@link com.cts.casestudy.usermanager.service.IUserService#findAll()}.
	 */
	@Test
	public void findUser() {
		User user=userService.findUser(1);
		assertEquals(user,null);
	}
	/**
	 * Test method for {@link com.cts.casestudy.usermanager.service.IUserService#findAll()}.
	 */
	@Test
	public void endUser() {
		userService.delete(1);
		User user=userService.findUser(1);
		assertEquals(user,null);
	}
	/**
	 * Test method for {@link com.cts.casestudy.usermanager.service.IUserService#findAll()}.
	 */
	@Test
	public void updateUser() {
		User user=new User();
		user.setUserId(1);
		assertEquals(userService.updateUser(user),null);
	}
}
