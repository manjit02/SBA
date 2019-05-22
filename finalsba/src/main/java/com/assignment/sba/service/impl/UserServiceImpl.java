package com.assignment.sba.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assignment.sba.dao.IUserDAO;
import com.assignment.sba.entities.User;
import com.assignment.sba.service.IUserService;

@Transactional
@Service
public class UserServiceImpl implements IUserService{
	

	
	private IUserDAO userDAO;
	
	@Autowired
	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	/* (non-Javadoc)
	 * @see com.cts.casestudy.usermanager.service.UserService#createUser(com.cts.casestudy.usermanager.entities.User)
	 */
	public User createUser(User user) {
		return userDAO.save(user);
	}

	/*
	 * To search
	 */
	/* (non-Javadoc)
	 * @see com.cts.casestudy.usermanager.service.UserService#findAll()
	 */
	public List<User> findAll() {
		return userDAO.findAll();
	}

	/**
	 * @param userId
	 * @return
	 * @throws UserNotFoundException
	 */
	public User findUser(Integer userId) {
		Optional<User> optional = userDAO.findUser(userId);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	/**
	 * Make a user inactive status in database
	 * 
	 * @param userId
	 */
	public void delete(Integer userId) {
		Optional<User> optional = userDAO.findUser(userId);
		if (optional.isPresent()) {
			optional.get().setStatus("Inactive");
			updateUser(optional.get());
		}
	}

	/**
	 * Update an employee if exist
	 * @param userDetails
	 * @return
	 */
	public User updateUser(User userDetails) {
		Optional<User> optional = userDAO.findUser(new Integer(userDetails.getUserId()));
		if (optional.isPresent()) {
			User user = optional.get();
			user.setFirstName(userDetails.getFirstName());
			user.setLastName(userDetails.getLastName());
			User updatedUser = createUser(user);
			return updatedUser;
		}
		return null;
	}
	public List<User> findActiveUsers(){
		return userDAO.findUserByStatus("Active");
	}
}
