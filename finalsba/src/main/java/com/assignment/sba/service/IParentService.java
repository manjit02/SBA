package com.assignment.sba.service;

import java.util.List;

import com.assignment.sba.entities.Parent;

public interface IParentService {

	public Parent createParent(Parent parent) ;

	public List<Parent> findAll() ;

	public Parent findParent(Integer parentId) ;

	public void delete(Integer parentId) ;
	
	public Parent end(Integer parentId) ;

	public Parent updateParent(Parent parentDetails) ;
}
