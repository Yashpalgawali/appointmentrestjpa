package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Data
@Table(name= "tbl_users")
public class Users {
	
	@Id
	@SequenceGenerator(name = "user_seq", allocationSize = 1 , initialValue = 1)
	@GeneratedValue(strategy = GenerationType.AUTO , generator = "user_seq")
	private int user_id;
	
	private String user_name;
	
	private String user_pass;
	
	private String user_email;
	
	private String role;
	
	private int enabled;
	
	@Transient
	private String cnf_pass;
	
	@Transient
	private String cnf_otp;
	
}
