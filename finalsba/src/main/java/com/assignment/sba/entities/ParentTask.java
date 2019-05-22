package com.assignment.sba.entities;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.Immutable;

import java.util.Date;


/**
 * The persistent class for the task database table.
 * 
 */
@Entity
@Immutable
@Table(name ="task")
public class ParentTask implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TASK_ID")
	private int taskId;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATE")
	@Type(type="date")
	private Date endDate;

	private int priority;

	@Temporal(TemporalType.DATE)
	@Column(name="START_DATE")
	@Type(type="date")
	private Date startDate;

	private String status;

	private String title;

	@Column(name="PARENT_ID")
	private Integer parentId;
	
	@Column(name="PROJECT_ID")
	private int projectId;
	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public ParentTask() {
	}

	public int getTaskId() {
		return this.taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	
	public Integer getParentId() {
		return this.taskId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getPriority() {
		return this.priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}