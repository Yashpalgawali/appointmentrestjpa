package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(sequenceName = "dept_seq" , allocationSize = 1 , initialValue = 1, name = "dept_seq")
@Table(name="tbl_department")
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO , generator = "dept_seq")
	@Column(name="dept_id")
	private Long dept_id;
	
	@Column(name="dept_name")
	private String dept_name;
	
	@ManyToOne
	@JoinColumn(name="company_id")
	private Company company;

	
	@OneToOne(mappedBy = "department")
	private Employee employee; 
	
	
	public Long getDept_id() {
		return dept_id;
	}

	public void setDept_id(Long dept_id) {
		this.dept_id = dept_id;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
}