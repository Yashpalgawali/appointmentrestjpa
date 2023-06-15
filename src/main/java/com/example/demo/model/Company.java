package com.example.demo.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
 
@Entity
@SequenceGenerator(name="comp_seq" , initialValue = 1, allocationSize = 1)
@Table(name="tbl_company")
public class Company {


	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO,generator = "comp_seq")
	@Column(name="company_id")
	private Long company_id;
	 
	@Column(name="comp_name")
	private String comp_name;

	public Long getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}

	public String getComp_name() {
		return comp_name;
	}

	public void setComp_name(String comp_name) {
		this.comp_name = comp_name;
	} 

	
	
	
}
