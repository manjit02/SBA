package com.assignment.sba.dao.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.assignment.sba.dao.IUserDAO;
import com.assignment.sba.entities.User;
import com.assignment.sba.repositories.IUserRepository;

@Repository
public class UserDAOImpl implements IUserDAO {

	
	private IUserRepository userRepository;
	
	@Autowired
	EntityManager em;
	
	@Autowired
	public void setUserRepository(IUserRepository userRepository) {
		this.userRepository=userRepository;
	}
	/*
	 * Save a User to DB
	 */
	@Override
	public User save(User user) {
		return userRepository.save(user);
	}
	/*
	 * To search
	 */
	@Override
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	/*
	 * A container object which may or may not contain a non-null value. 
	 * If a value is present, isPresent() will return true 
	 * and get() will return the value. 
	 */
	@Override
	public Optional<User> findUser(Integer userId){
		return userRepository.findById(userId);
	}
	
	/**
	 * Delete a task from database
	 * @param task
	 */
	@Override
	public void delete(User user) {
		userRepository.delete(user);
	}
	
	/*
	 * To search
	 */
	@Override
	public long findNextId(){
		return userRepository.count();
	}
	//update
	@Override
	public List<User> findUserByStatus(String status) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> book = cq.from(User.class);
        Predicate authorNamePredicate = cb.equal(book.get("status"), status);
        cq.where(authorNamePredicate);
        TypedQuery<User> query = em.createQuery(cq);
        return query.getResultList();
    }
 
	
}
