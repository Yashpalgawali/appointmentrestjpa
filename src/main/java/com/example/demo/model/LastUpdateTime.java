package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tbl_updated_time")
public class LastUpdateTime {

	@Id
	@SequenceGenerator(name="last_update_seq",initialValue = 1,allocationSize = 1)
	@GeneratedValue(generator = "last_update_seq",strategy = GenerationType.AUTO)
	private Long last_update_time_id;
	
	private String updated_time;
}
