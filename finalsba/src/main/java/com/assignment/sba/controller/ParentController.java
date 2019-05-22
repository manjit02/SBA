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
import org.springframework.web.bind.annotation.RestController;

import com.assignment.sba.entities.Parent;
import com.assignment.sba.service.IParentService;



@RestController
@RequestMapping("/finalsba")
@CrossOrigin
public class ParentController implements ErrorController {

	@Autowired
	IParentService service;

	private static final String PATH ="/parent/error";

	/**
	 * @param parent
	 * @return
	 */
	@PostMapping("/createparent")
	public ResponseEntity<Parent> createParent(@Valid @RequestBody Parent parent) {
		Parent addParent= new Parent();
		addParent.setParentTaskId(parent.getParentTaskId());
		return ResponseEntity.ok().body(service.createParent(addParent));
	}


	/**
	 * find all parents in DB
	 * @return parentList
	 */
	@GetMapping("/allparent")
	public List<Parent> getAllParents() {
		return service.findAll();
	}

	/**
	 * @param parentId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@GetMapping("/viewparent/{id}")
	public ResponseEntity getParentBy(@PathVariable(value = "id") int parentId) {
		return ResponseEntity.ok().body(service.findParent(parentId));
	}


	/**
	 * Update an employee if exists
	 * @param parentId
	 * @param parentDetails
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@PostMapping("/editparent/{id}")
	public ResponseEntity updateParent(@PathVariable(value = "id") Integer parentId, @Valid @RequestBody Parent parentDetails) {
		return ResponseEntity.ok().body(service.updateParent(parentDetails));
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
