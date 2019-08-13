package com.cenfotec.pm.domain;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Activity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private long expectedCost;
	private long actualCost;
	private LocalDate actualStartDate;
	private LocalDate expectedStartDate;
	private LocalDate actualEndDate;
	private LocalDate expectedEndDate;
	private String name;
	private String description;
	private int state;
	
	@Column(name = "project")
	private Long projectId;
	@JsonIgnore
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "project", insertable = false, updatable = false)
	private Project project;
	
	
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