package com.example.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tbl_employee")
public class Employee {

	@Id
	@SequenceGenerator(name="emp_seq",allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "emp_seq")
	private Long emp_id;
	
	private String emp_name;
	
	private String emp_email;
	
	private Long emp_status;
	
	@ToString.Exclude
	@JsonIgnore
	@OneToOne(cascade = CascadeType.MERGE,targetEntity = Department.class,fetch = FetchType.EAGER)
	@JoinColumn(name="dept_id",referencedColumnName = "dept_id")
	private Department department;


	@OneToOne(cascade = CascadeType.MERGE, targetEntity = Designation.class ,fetch = FetchType.EAGER)
	@JoinColumn(name="desig_id" , referencedColumnName = "desig_id")
	private Designation designation;
	
	

}
