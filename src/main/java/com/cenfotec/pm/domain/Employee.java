package com.cenfotec.pm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "TEmployee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "lastName")
	private String lastName;
	@Column(name = "state")
	private int state;
	@Column(name = "type")
	private int type;
	@Transient
	private Long farmId;
	@Transient
	private String desType;
	@Transient
	private String desState;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "farm_id")
	private Farm farm;

	public Employee() {
		this.name = "";
		this.state = 1;
	}

	public Employee(String name) {
		this.name = name;
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Farm getFarm() {
		return farm;
	}

	public void setFarm(Farm farm) {
		this.farm = farm;
	}

	public Long getFarmId() {
		return farmId;
	}

	public void setFarmId(Long farmId) {
		this.farmId = farmId;
	}

	public String getDesType() {
		switch (state) {
		case 1:
			return "Veterinario";
		case 2:
			return "Peon";
		case 3:
			return "Supervisor";
		case 4:
			return "Seguridad";
		default:
			return "Sin definir";
		}
	}

	public void setDesType(String desType) {
		this.desType = desType;
	}

	public String getDesState() {
		switch (state) {
		case 0:
			return "Inactivo";
		case 1:
			return "Activo";
		default:
			return "";
		}
	}

	public void setDesState(String desState) {
		this.desState = desState;
	}

}
