package com.university.studentmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class UniversityStudentApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniversityStudentApplication.class, args);
	}
}
