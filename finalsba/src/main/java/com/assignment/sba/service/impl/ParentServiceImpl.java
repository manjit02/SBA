package com.assignment.sba.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assignment.sba.dao.IParentDAO;
import com.assignment.sba.entities.Parent;
import com.assignment.sba.service.IParentService;

@Transactional
@Service
public class ParentServiceImpl implements IParentService{
	

	
	private IParentDAO parentDAO;
	
	@Autowired
	public void setParentDAO(IParentDAO parentDAO) {
		this.parentDAO = parentDAO;
	}

	/* (non-Javadoc)
	 * @see com.cts.casestudy.parentmanager.service.ParentService#createParent(com.cts.casestudy.parentmanager.entities.Parent)
	 */
	public Parent createParent(Parent parent) {
		return parentDAO.save(parent);
	}

	/*
	 * To search
	 */
	/* (non-Javadoc)
	 * @see com.cts.casestudy.parentmanager.service.ParentService#findAll()
	 */
	public List<Parent> findAll() {
		return parentDAO.findAll();
	}

	/**
	 * @param parentId
	 * @return
	 * @throws ParentNotFoundException
	 */
	public Parent findParent(Integer parentId) {
		Optional<Parent> optional = parentDAO.findParent(parentId);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	/**
	 * Delete a parent from database
	 * 
	 * @param parentId
	 */
	public void delete(Integer parentId) {
		Optional<Parent> optional = parentDAO.findParent(parentId);
		if (optional.isPresent()) {
			parentDAO.delete(optional.get());
		}
	}
	
	/**
	 * Delete a parent from database
	 * 
	 * @param parentId
	 */
	public Parent end(Integer parentId) {
		Optional<Parent> optional = parentDAO.findParent(parentId);
		if (optional.isPresent()) {
			Parent parent = optional.get();
			Parent updatedParent = createParent(parent);
			return updatedParent;
		}
		return null;
	}

	/**
	 * Update an employee if exist
	 * @param parentDetails
	 * @return
	 */
	public Parent updateParent(Parent parentDetails) {
		Optional<Parent> optional = parentDAO.findParent(new Integer(parentDetails.getParentId()));
		if (optional.isPresent()) {
			Parent parent = optional.get();
			parent.setParentTaskId(parentDetails.getParentTaskId());
			Parent updatedParent = createParent(parent);
			return updatedParent;
		}
		return null;
	}

}
