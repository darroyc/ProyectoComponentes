package com.cenfotec.pm.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class ActivityWeekData {
	@EmbeddedId
	private WeekDataId weekDataId;
	private long value = 0;

	@JsonIgnore
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "identifier", insertable = false, updatable = false)
	private Activity activity;
	
	public ActivityWeekData(Integer week, Long identifier, String type) {
		weekDataId = new WeekDataId(week, identifier, type);
	}
}