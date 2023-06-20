package com.example.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
	
	@OneToOne(cascade = CascadeType.MERGE,targetEntity = Department.class,fetch = FetchType.LAZY)
	@JoinColumn(name="dept_id",referencedColumnName = "dept_id")
	private Department department;

	@OneToOne(cascade = CascadeType.MERGE, targetEntity = Designation.class ,fetch = FetchType.EAGER)
	@JoinColumn(name="desig_id" , referencedColumnName = "desig_id")
	private Designation designation;
	
	public Designation getDesignation() {
		return designation;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}

	public Long getEmp_status() {
		return emp_status;
	}

	public void setEmp_status(Long emp_status) {
		this.emp_status = emp_status;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Long getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(Long emp_id) {
		this.emp_id = emp_id;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public String getEmp_email() {
		return emp_email;
	}

	public void setEmp_email(String emp_email) {
		this.emp_email = emp_email;
	}

	public Employee() {}

	public Employee(Long emp_id, String emp_name, String emp_email, Long emp_status, Department department) {
		super();
		this.emp_id = emp_id;
		this.emp_name = emp_name;
		this.emp_email = emp_email;
		this.emp_status = emp_status;
		this.department = department;
	}

}
