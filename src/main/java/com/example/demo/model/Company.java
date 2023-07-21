package com.example.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_company")
public class Company {

	
	@Id
	@SequenceGenerator(name="comp_seq" , initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.AUTO,generator = "comp_seq")
	@Column(name="company_id")
	private Long company_id;
	 
	@Column(name="comp_name")
	private String comp_name;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.MERGE,mappedBy = "company",targetEntity = Department.class )
	private List<Department> department;
	
}
