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
import org.springframework.web.bind.annotation.RestController;

import com.assignment.sba.entities.Project;
import com.assignment.sba.service.IProjectService;



@RestController
@RequestMapping("/finalsba")
@CrossOrigin
public class ProjectController implements ErrorController {

	@Autowired
	IProjectService service;

	private static final String PATH ="/project/error";

	/**
	 * @param project
	 * @return
	 */
	@PostMapping("/createproject")
	public ResponseEntity<Project> createProject(@Valid @RequestBody Project project) {
		return ResponseEntity.ok().body(service.createProject(project));
	}


	/**
	 * find all projects in DB
	 * @return projectList
	 */
	@GetMapping("/allprojects")
	public List<Project> getAllProjects() {
		return service.findAll();
	}

	/**
	 * @param projectId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@GetMapping("/viewproject/{id}")
	public ResponseEntity getProjectBy(@PathVariable(value = "id") int projectId) {
		return ResponseEntity.ok().body(service.findProject(projectId));
	}


	/**
	 * Update an employee if exists
	 * @param projectId
	 * @param projectDetails
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@PostMapping("/editproject/{id}")
	public ResponseEntity updateProject(@PathVariable(value = "id") Integer projectId, @Valid @RequestBody Project projectDetails) {
		projectDetails.setEndDate(new Date());
		return ResponseEntity.ok().body(service.updateProject(projectDetails));
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
