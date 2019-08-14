package com.cenfotec.pm.domain;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private String owner;
	private Long plannedValue;
	private Long earnedValue;
	private Long scheduleVariance;
	private Long costVariance;
	private Long schedulePerformanceIndex;
	private Long costPerformanceIndex;
	private int state;
	@Basic
	private Date startDate;
	@Basic
	private Date endDate;
	@OneToMany(fetch=FetchType.EAGER, mappedBy="project", cascade = CascadeType.ALL)
	private List<Activity> activities;
	
		
	public Project() {
		this.state = 1;
	}
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
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



	public String getOwner() {
		return owner;
	}



	public void setOwner(String owner) {
		this.owner = owner;
	}


	
	public Date getStartDate() {
		return startDate;
	}
	
	

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	
	public Date getEndDate() {
		return endDate;
	}
	

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}



	public Long getPlannedValue() {
		return plannedValue;
	}



	public void setPlannedValue(Long plannedValue) {
		this.plannedValue = plannedValue;
	}



	public Long getEarnedValue() {
		return earnedValue;
	}



	public void setEarnedValue(Long earnedValue) {
		this.earnedValue = earnedValue;
	}



	public Long getScheduleVariance() {
		return scheduleVariance;
	}



	public void setScheduleVariance(Long scheduleVariance) {
		this.scheduleVariance = scheduleVariance;
	}



	public Long getCostVariance() {
		return costVariance;
	}



	public void setCostVariance(Long costVariance) {
		this.costVariance = costVariance;
	}



	public Long getSchedulePerformanceIndex() {
		return schedulePerformanceIndex;
	}



	public void setSchedulePerformanceIndex(Long schedulePerformanceIndex) {
		this.schedulePerformanceIndex = schedulePerformanceIndex;
	}



	public Long getCostPerformanceIndex() {
		return costPerformanceIndex;
	}



	public void setCostPerformanceIndex(Long costPerformanceIndex) {
		this.costPerformanceIndex = costPerformanceIndex;
	}



	public List<Activity> getActivities() {
		return activities;
	}



	public void setActivities(List<Activity> activities) {
		this.activities = activities;
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