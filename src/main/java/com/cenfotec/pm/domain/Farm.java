package com.cenfotec.pm.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TFarm")
public class Farm {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "address")
	private String address;
	@Column(name = "phones")
	private String phones;

	@Column(name = "constructedArea")
	private int constructedArea;
	@Column(name = "productionArea")
	private int productionArea;
	@Column(name = "storeArea")
	private int storeArea;

	@OneToMany(mappedBy = "farm", cascade = CascadeType.ALL)
	private List<Production> productionList;

	@OneToMany(mappedBy = "farm", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Employee> employeeList;

	public Farm() {
		this.name = "";
		this.address = "";
		this.phones = "";
	}

	public Farm(String name, String address, String phones) {
		this.name = name;
		this.address = address;
		this.phones = phones;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhones() {
		return phones;
	}

	public void setPhones(String phones) {
		this.phones = phones;
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	public List<Production> getProductionList() {
		return productionList;
	}

	public void setProductionList(List<Production> productionList) {
		this.productionList = productionList;
	}

	public int getConstructedArea() {
		return constructedArea;
	}

	public void setConstructedArea(int constructedArea) {
		this.constructedArea = constructedArea;
	}

	public int getProductionArea() {
		return productionArea;
	}

	public void setProductionArea(int productionArea) {
		this.productionArea = productionArea;
	}

	public int getStoreArea() {
		return storeArea;
	}

	public void setStoreArea(int storeArea) {
		this.storeArea = storeArea;
	}
}
