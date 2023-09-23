package com.example.demo.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


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
	
//	@ToString.Exclude
	@ManyToOne(cascade = { CascadeType.MERGE },targetEntity = Department.class )
	@JoinColumn(name = "dept_id")
	private Department department;

	@OneToOne(cascade = { CascadeType.MERGE }, targetEntity = Designation.class  )
	@JoinColumn(name = "desig_id")
	private Designation designation;
	
}
