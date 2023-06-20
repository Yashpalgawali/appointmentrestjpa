package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ActivityLogs;

@Repository("actrepo")
public interface ActivityRepo extends JpaRepository<ActivityLogs, Long> {

}
