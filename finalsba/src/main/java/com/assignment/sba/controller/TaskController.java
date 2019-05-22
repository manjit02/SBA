package com.assignment.sba.controller;

import java.util.Date;
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

import com.assignment.sba.entities.Task;
import com.assignment.sba.service.ITaskService;



@RestController
@RequestMapping("/finalsba")
@CrossOrigin
public class TaskController implements ErrorController {

	@Autowired
	ITaskService service;

	private static final String PATH ="/error";

	/**
	 * @param task
	 * @return
	 */
	@PostMapping("/createtask")
	public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
		task.setEndDate(null);
		task.setStartDate(new Date());
		task.setStatus("Progress");
		if(task.isParent()) {
			task.setParentId(null);	
		}
		return ResponseEntity.ok().body(service.createTask(task));
	}


	/**
	 * find all tasks in DB
	 * @return taskList
	 */
	@GetMapping("/alltasks")
	public List<Task> getAllTasks() {
		return service.findAll();
	}

	/**
	 * @param taskId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@GetMapping("/viewtask/{id}")
	public ResponseEntity getTaskBy(@PathVariable(value = "id") int taskId) {
		return ResponseEntity.ok().body(service.findTask(taskId));
	}


	/**
	 * Update an employee if exists
	 * @param taskId
	 * @param taskDetails
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@PostMapping("/edittask/{id}")
	public ResponseEntity updateTask(@PathVariable(value = "id") Integer taskId, @Valid @RequestBody Task taskDetails) {
		return ResponseEntity.ok().body(service.updateTask(taskDetails));
	}
	
	/**
	 * Delete an employee if exists
	 * @param taskId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@PostMapping("/endtask/{id}")
	public ResponseEntity endTask(@PathVariable(value = "id") Integer taskId) {
		service.end(taskId);
		return ResponseEntity.ok().body("Task ended successfully");
	}
	
	@RequestMapping(value = PATH)
	public String error() {
		return "Error handling";
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping("/nexttaskid")
	public ResponseEntity getNextId() {
		return  ResponseEntity.ok().body(service.findNextId());
	}

	@RequestMapping(value="/deleteTask/id/{id}",method={RequestMethod.GET,RequestMethod.DELETE})
	public void deleteTaskById( @PathVariable("id") Integer id){
	service.end(id);
	}
	@Override
	public String getErrorPath() {
		return PATH;
	}
}
