package com.assignment.sba.entities;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;


/**
 * The persistent class for the task database table.
 * 
 */
@Entity
@NamedQuery(name="Task.findAll", query="SELECT t FROM Task t")
public class Task implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TASK_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int taskId;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATE")
	@Type(type="date")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="GMT")
	private Date endDate;

	private int priority;
	
	@Column(name="USER_ID")
	private int userId;
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name="PARENT")
	private boolean parent;

	public boolean isParent() {
		return parent;
	}

	public void setParent(boolean parent) {
		this.parent = parent;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="START_DATE")
	@Type(type="date")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="GMT")
	private Date startDate;

	private String status;

	private String title;

	@Column(name="PARENT_ID")
	private Integer parentId;
	
	@Column(name="PROJECT_ID")
	private int projectId;

	@ManyToOne
	@JoinColumn(name="PROJECT_ID", insertable = false , updatable = false)
	private Project project;
	
	@ManyToOne
	@JoinColumn(name="PARENT_ID", insertable = false , updatable = false)
	private Parent parentTask;
	
	public Parent getParentTask() {
		return parentTask;
	}

	public void setParentTask(Parent parentTask) {
		this.parentTask = parentTask;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public Task() {
	}

	public int getTaskId() {
		return this.taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	
	public Integer getParentId() {
		return this.parentId;
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

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}