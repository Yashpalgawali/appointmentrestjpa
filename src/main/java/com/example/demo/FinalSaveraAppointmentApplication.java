package com.example.demo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinalSaveraAppointmentApplication {
	 
	
	public static void main(String[] args) {
		SpringApplication.run(FinalSaveraAppointmentApplication.class, args);
	
		int n = 10;
		IntStream.range(1, n +1).map(num -> num * num * num).boxed().collect(Collectors.toList());
		 List<Integer> lis = List.of(1,24,5,78);
		 
		 
	}	
}