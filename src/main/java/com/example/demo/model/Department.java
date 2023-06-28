package com.example.demo.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@SequenceGenerator(sequenceName = "dept_seq" , allocationSize = 1 , initialValue = 1, name = "dept_seq")
@Table(name="tbl_department")
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO , generator = "dept_seq")
	@Column(name="dept_id")
	private Long dept_id;
	
	@Column(name="dept_name")
	private String dept_name;

	@ToString.Exclude
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER,targetEntity = Company.class)
	@JoinColumn(name="company_id")
	private Company company;

	
//	@OneToMany(mappedBy = "department",cascade = CascadeType.MERGE)
//	private List<Employee> employee; 
	
	
	
}