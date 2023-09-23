package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SequenceGenerator(name="desig_seq" , allocationSize = 1, initialValue = 1)
@Table(name="tbl_designation")
public class Designation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO ,generator = "desig_seq")
	@Column(name="desig_id")
	private Long desig_id;
	
	@Column(name="desig_name")
	private String desig_name;
	
//	@OneToOne(mappedBy = "designation",targetEntity = Employee.class,cascade = CascadeType.MERGE)
//	private Employee employee;
	
//	public Long getDesig_id() {
//		return desig_id;
//	}
//
//	public void setDesig_id(Long desig_id) {
//		this.desig_id = desig_id;
//	}
//
//	public String getDesig_name() {
//		return desig_name;
//	}
//
//	public void setDesig_name(String desig_name) {
//		this.desig_name = desig_name;
//	}
//	
}
