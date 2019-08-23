package com.cenfotec.pm.domain;

import java.sql.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
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
	private int weeks;
	private long budget;
	private int state;
	@Basic
	private Date startDate;
	@Basic
	private Date endDate;
	@OneToMany(fetch=FetchType.EAGER, mappedBy="project", cascade = CascadeType.ALL)
	private List<Activity> activities;
	@OneToMany(fetch=FetchType.LAZY, mappedBy="project", cascade = CascadeType.ALL)
	private List<ProjectWeekData> projectWeekData;
	
		
	public Project() {
		this.state = 1;
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