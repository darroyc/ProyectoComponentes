package com.cenfotec.examen2.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
@Table(name = "TProduction")
public class Production {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "date")
	private Date date;
	@Column(name = "eggsQuantity")
	private String eggsQuantity;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "farm_id")
	private Farm farm;

	@Transient
	private String dateText;
	@Transient
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	@Transient
	private SimpleDateFormat outFormat = new SimpleDateFormat("dd/MM/YYYY");

	public Production() {
	}

	public Production(Long id, String date, String eggsQuantity) throws ParseException {
		this.date = format.parse(date);
		this.eggsQuantity = eggsQuantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDate() {
		return outFormat.format(this.date);
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getEggsQuantity() {
		return eggsQuantity;
	}

	public void setEggsQuantity(String eggsQuantity) {
		this.eggsQuantity = eggsQuantity;
	}

	public SimpleDateFormat getFormat() {
		return format;
	}

	public void setFormat(SimpleDateFormat format) {
		this.format = format;
	}

	public Farm getFarm() {
		return farm;
	}

	public void setFarm(Farm farm) {
		this.farm = farm;
	}

	public String getDateText() {
		return dateText;
	}

	public void setDateText(String dateText) throws ParseException {
		this.date = format.parse(dateText);
	}
	

}
