package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("lastupdaterepo")
public interface LastUpdateTimeRepo extends JpaRepository<com.example.demo.model.LastUpdateTime, Long>{

	@Transactional
	@Modifying
	@Query("UPDATE LastUpdateTime t SET t.updated_time=?1")
	public int updateLastUpdateTime(String time);
}
