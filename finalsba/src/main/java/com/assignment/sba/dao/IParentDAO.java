package com.assignment.sba.dao;

import java.util.List;
import java.util.Optional;

import com.assignment.sba.entities.Parent;

public interface IParentDAO {

	public Parent save(Parent parent);
	
	public List<Parent> findAll();
	
	public Optional<Parent> findParent(Integer parentId);
	
	public void delete(Parent parent);
}
