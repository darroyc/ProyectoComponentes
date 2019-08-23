package com.cenfotec.pm.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class WeekDataId implements Serializable {
	private Integer week;
	private Long identifier;
	private String type = "";
}