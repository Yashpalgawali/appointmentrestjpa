package com.example.demo.model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tbl_appointment")
public class Appointment {

	@Id
	@SequenceGenerator(name = "appoint_seq", initialValue = 1 , allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO , generator = "appoint_seq")
	private Long appoint_id;
	
	private String vis_name;
	
	private String vis_email;
	
	private String vis_contact;
	
	private String vis_purpose;
	
	private String vcomp_name;
	
	private String apdate;
	
	private String aptime;
	
	private String status;
	
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="emp_id",referencedColumnName = "emp_id") 
	private Employee employee;

	public Long getAppoint_id() {
		return appoint_id;
	}

	public void setAppoint_id(Long appoint_id) {
		this.appoint_id = appoint_id;
	}

	public String getVis_name() {
		return vis_name;
	}

	public void setVis_name(String vis_name) {
		this.vis_name = vis_name;
	}

	public String getVis_email() {
		return vis_email;
	}

	public void setVis_email(String vis_email) {
		this.vis_email = vis_email;
	}

	public String getVis_contact() {
		return vis_contact;
	}

	public void setVis_contact(String vis_contact) {
		this.vis_contact = vis_contact;
	}

	public String getVis_purpose() {
		return vis_purpose;
	}

	public void setVis_purpose(String vis_purpose) {
		this.vis_purpose = vis_purpose;
	}

	public String getVcomp_name() {
		return vcomp_name;
	}

	public void setVcomp_name(String vcomp_name) {
		this.vcomp_name = vcomp_name;
	}

	public String getApdate() {
		return apdate;
	}

	public void setApdate(String apdate) {
		this.apdate = apdate;
	}

	public String getAptime() {
		return aptime;
	}

	public void setAptime(String aptime) {
		this.aptime = aptime;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	
	
	public Appointment(Long appoint_id, String vis_name, String vis_email, String vis_contact, String vis_purpose,
			String vcomp_name, String apdate, String aptime, String status, Employee employee) {
		super();
		this.appoint_id = appoint_id;
		this.vis_name = vis_name;
		this.vis_email = vis_email;
		this.vis_contact = vis_contact;
		this.vis_purpose = vis_purpose;
		this.vcomp_name = vcomp_name;
		this.apdate = apdate;
		this.aptime = aptime;
		this.status = status;
		this.employee = employee;
	}

	public Appointment() {}
	
	
	
}
