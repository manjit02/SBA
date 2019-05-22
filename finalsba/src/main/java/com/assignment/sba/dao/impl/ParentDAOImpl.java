package com.assignment.sba.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.assignment.sba.dao.IParentDAO;
import com.assignment.sba.entities.Parent;
import com.assignment.sba.repositories.IParentRepository;

@Repository
public class ParentDAOImpl implements IParentDAO {

	
	private IParentRepository parentRepository;
	
	@Autowired
	public void setParentRepository(IParentRepository parentRepository) {
		this.parentRepository=parentRepository;
	}
	/*
	 * Save a Parent to DB
	 */
	@Override
	public Parent save(Parent parent) {
		return parentRepository.save(parent);
	}
	/*
	 * To search
	 */
	@Override
	public List<Parent> findAll(){
		return parentRepository.findAll();
	}
	
	/*
	 * A container object which may or may not contain a non-null value. 
	 * If a value is present, isPresent() will return true 
	 * and get() will return the value. 
	 */
	@Override
	public Optional<Parent> findParent(Integer parentId){
		return parentRepository.findById(parentId);
	}
	
	/**
	 * Delete a task from database
	 * @param task
	 */
	@Override
	public void delete(Parent parent) {
		parentRepository.delete(parent);
	}
	
	//update
	
	
}
