package com.assignment.sba.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.sba.entities.Task;

public interface ITaskRepository extends JpaRepository<Task, Integer>{

}

