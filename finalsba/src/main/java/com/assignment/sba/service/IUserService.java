package com.assignment.sba.service;

import java.util.List;

import com.assignment.sba.entities.User;

public interface IUserService {

	public User createUser(User user) ;

	public List<User> findAll() ;

	public User findUser(Integer userId) ;

	public void delete(Integer userId) ;

	public User updateUser(User userDetails) ;
	
	public List<User> findActiveUsers();
}
