package com.cenfotec.pm.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;

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
	@OneToMany(fetch=FetchType.EAGER, mappedBy="activity", cascade = CascadeType.ALL)
	private List<ActivityWeekData> activityWeekData;
	
	public Activity() {
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