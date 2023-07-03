package com.example.demo.repository;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Appointment;

@Repository("appointrepo")
public interface AppointmentRepo extends JpaRepository<Appointment, Long> {

	
	//@Query(value="SELECT * FROM tbl_appointment JOIN tbl_employee ON tbl_employee.emp_id=tbl_appointment.emp_id",nativeQuery = true)
	@Query("SELECT a FROM Appointment a JOIN a.employee")
	public List<Appointment> getAllAppointments();
	
	
	//@Query(value="SELECT * FROM tbl_appointment JOIN tbl_employee ON tbl_employee.emp_id=tbl_appointment.emp_id where tbl_appointment.apdate=?1",nativeQuery = true)
	@Query("SELECT a FROM Appointment a JOIN a.employee WHERE a.apdate=?1")
	public List<Appointment> getAllTodaysAppointments(String today); 
	
	
	//@Query(value="SELECT * FROM tbl_appointment JOIN tbl_employee ON tbl_employee.emp_id=tbl_appointment.emp_id JOIN tbl_department ON tbl_department.dept_id=tbl_employee.dept_id WHERE tbl_appointment.vis_email=?1",nativeQuery = true)
	@Query("SELECT a FROM Appointment a INNER JOIN a.employee e INNER JOIN e.department d INNER JOIN d.company c WHERE a.vis_email=?1")
	public List<Appointment> getAllVisitorAppointments(String vemail);
	
	//@Query(value="SELECT * FROM tbl_appointment JOIN tbl_employee ON tbl_employee.emp_id=tbl_appointment.emp_id WHERE tbl_employee.emp_email=?1",nativeQuery = true)
	@Query("SELECT a FROM Appointment a JOIN a.employee WHERE a.employee.emp_email=?1")
	public List<Appointment> getAllEmployeesAppointments(String eemail);
	
	@Query("SELECT a FROM Appointment a WHERE a.apdate=?1 AND a.vis_email=?2")
	public List<Appointment> getAllTodaysAppointmentsByEmail(String tdate, String email);
	
	
	@Transactional
	@Modifying
	@Query("UPDATE Appointment a SET a.status=?2 WHERE a.appoint_id=?1")
	public int updateAppointmentStatusById(Long id,String status);
	
	
	@Transactional
	@Modifying
	@Query("UPDATE Appointment a SET a.apdate=?1,a.aptime=?2,a.vcomp_name=?3,a.vis_contact=?4,a.vis_email=?5,a.vis_name=?6,a.vis_purpose=?7,a.employee.emp_id=?8,a.status=?9 WHERE a.appoint_id=?10")
	public int updateAppointmentById(String apdate, String aptime, String vcomp,String vcontact,String vemail,String vname,String purpose,Long empid,String status,Long apid);
	
	
	
}
