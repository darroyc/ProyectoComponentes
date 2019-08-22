package com.cenfotec.pm.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Entity
public class Activity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private long expectedCost;
	private long actualCost;
	private Date actualStartDate;
	private Date expectedStartDate;
	private Date actualEndDate;
	private Date expectedEndDate;
	private String name;
	private String description;
	private int state;
	
	@Column(name = "project")
	private Long projectId;
	@JsonIgnore
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "project", insertable = false, updatable = false)
	private Project project;
	
	public Activity() {
		this.state = 1;
	}
	public Long getProjectId() {
		return projectId;
	}


	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public long getExpectedCost() {
		return expectedCost;
	}


	public void setExpectedCost(long expectedCost) {
		this.expectedCost = expectedCost;
	}


	public long getActualCost() {
		return actualCost;
	}


	public void setActualCost(long actualCost) {
		this.actualCost = actualCost;
	}


	public Date getActualStartDate() {
		return actualStartDate;
	}


	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
	}


	public Date getExpectedStartDate() {
		return expectedStartDate;
	}


	public void setExpectedStartDate(Date expectedStartDate) {
		this.expectedStartDate = expectedStartDate;
	}


	public Date getActualEndDate() {
		return actualEndDate;
	}


	public void setActualEndDate(Date actualEndDate) {
		this.actualEndDate = actualEndDate;
	}


	public Date getExpectedEndDate() {
		return expectedEndDate;
	}


	public void setExpectedEndDate(Date expectedEndDate) {
		this.expectedEndDate = expectedEndDate;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Project getProject() {
		return project;
	}


	public void setProject(Project project) {
		this.project = project;
	}


	public void setState(int state) {
		this.state = state;
	}


	public String getState() {
		switch (state) {
		case 0:
			return "Inactivo";
		case 1:
			return "Activo";
		case 2:
			return "Finalizado";
		default:
			return "";
		}
	}
}