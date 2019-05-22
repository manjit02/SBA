package com.assignment.sba.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.sba.entities.User;
import com.assignment.sba.service.IUserService;



@RestController
@RequestMapping("/finalsba")
@CrossOrigin
public class UserController implements ErrorController {

	@Autowired
	IUserService service;

	private static final String PATH ="/user/error";

	/**
	 * @param user
	 * @return
	 */
	@PostMapping("/createuser")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		return ResponseEntity.ok().body(service.createUser(user));
	}


	/**
	 * find all users in DB
	 * @return userList
	 */
	@GetMapping("/alluser")
	public List<User> getAllUsers() {
		return service.findAll();
	}
	
	/**
	 * find all users in DB
	 * @return userList
	 */
	@GetMapping("/activeuser")
	public List<User> getActiveUsers() {
		return service.findActiveUsers();
	}

	/**
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@GetMapping("/viewuser/{id}")
	public ResponseEntity getUserBy(@PathVariable(value = "id") int userId) {
		return ResponseEntity.ok().body(service.findUser(userId));
	}


	/**
	 * Update an employee if exists
	 * @param userId
	 * @param userDetails
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@PostMapping("/edituser/{id}")
	public ResponseEntity updateUser(@PathVariable(value = "id") Integer userId, @Valid @RequestBody User userDetails) {
		return ResponseEntity.ok().body(service.updateUser(userDetails));
	}
	
	/**
	 * Make a user inactive status in database
	 * @param taskId
	 * @return
	 */
	@RequestMapping(value="/deleteUser/id/{id}",method={RequestMethod.GET,RequestMethod.DELETE})
	public void deleteUserById( @PathVariable("id") Integer id){
	service.delete(id);
	}
	
	@RequestMapping(value = PATH)
	public String error() {
		return "Error handling";
	}
	
	@Override
	public String getErrorPath() {
		return PATH;
	}
}
