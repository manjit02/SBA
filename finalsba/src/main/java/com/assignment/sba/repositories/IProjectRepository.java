package com.assignment.sba.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.sba.entities.Project;

public interface IProjectRepository extends JpaRepository<Project, Integer>{

}

