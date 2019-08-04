package com.cenfotec.pm.domain;

import java.time.LocalDate;
import java.util.List;

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
	private LocalDate startDate;
	private LocalDate endDate;
	private Long plannedValue;
	private Long earnedValue;
	private Long scheduleVariance;
	private Long costVariance;
	private Long schedulePerformanceIndex;
	private Long costPerformanceIndex;
	@OneToMany(fetch=FetchType.EAGER, mappedBy="project", cascade = CascadeType.ALL)
	private List<Activity> activities;
}