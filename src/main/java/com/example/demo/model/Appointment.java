package com.example.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	
	@Transient
	private Integer otp_num;
	
	@Transient
	private Integer new_otp;
	
	@Transient
	private String app_name;
	
	@Transient
	private String admemail;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="emp_id",referencedColumnName = "emp_id") 
	private Employee employee;
	
	public String getAdmemail() {
		return admemail;
	}

	public void setAdmemail(String admemail) {
		this.admemail = admemail;
	}

	public Integer getNew_otp() {
		return new_otp;
	}

	public void setNew_otp(Integer new_otp) {
		this.new_otp = new_otp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
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
	
	public Integer getOtp_num() {
		return otp_num;
	}

	public void setOtp_num(Integer otp_num) {
		this.otp_num = otp_num;
	}

	public String getApp_name() {
		return app_name;
	}

	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}

	public Appointment() {}
	
	
}
