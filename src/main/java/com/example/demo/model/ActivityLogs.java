package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityLogs {

	@Id
	@SequenceGenerator(name="activity_seq",allocationSize = 1,initialValue = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator="activity_seq")
	private Long activity_id;
	
	private String activity_date;
	
	private String activity_time;
	
	private String activity;
}
