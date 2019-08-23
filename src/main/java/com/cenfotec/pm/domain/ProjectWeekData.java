package com.cenfotec.pm.domain;

import javax.persistence.CascadeType;
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
public class ProjectWeekData {
	@EmbeddedId
	private WeekDataId weekDataId;
	private long value = 0;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "identifier", insertable = false, updatable = false)
	private Project project;
	
	public ProjectWeekData(Integer week, Long identifier, String type) {
		weekDataId = new WeekDataId(week, identifier, type);
	}
}