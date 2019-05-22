package com.assignment.sba.dao;

import java.util.List;
import java.util.Optional;

import com.assignment.sba.entities.User;

public interface IUserDAO {

	public User save(User user);
	
	public List<User> findAll();
	
	public Optional<User> findUser(Integer userId);
	
	public void delete(User user);

	public long findNextId();

	public List<User> findUserByStatus(String status);
}
