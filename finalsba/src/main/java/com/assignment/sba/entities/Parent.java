package com.assignment.sba.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;


/**
 * The persistent class for the parent database table.
 * 
 */
@Entity
@NamedQuery(name="Parent.findAll", query="SELECT p FROM Parent p")
public class Parent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PARENT_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int parentId;

	@Column(name="PARENT_TASK")
	private int parentTaskId;

	
	@OneToOne
	@JoinColumn(name="PARENT_TASK", referencedColumnName="TASK_ID",insertable = false , updatable = false)
	private ParentTask parentTask;
	
	public int getParentTaskId() {
		return parentTaskId;
	}

	public void setParentTaskId(int parentTaskId) {
		this.parentTaskId = parentTaskId;
	}

	public ParentTask getParentTask() {
		return this.parentTask;
	}
	
	public void setParentTask(ParentTask parentTask) {
		this.parentTask = parentTask;
	}

	public Parent() {
	}

	public int getParentId() {
		return this.parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

}