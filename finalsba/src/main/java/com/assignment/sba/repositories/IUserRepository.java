package com.assignment.sba.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.sba.entities.User;

public interface IUserRepository extends JpaRepository<User, Integer>{

}

