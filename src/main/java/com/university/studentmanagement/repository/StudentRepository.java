package com.university.studentmanagement.repository;

import com.university.studentmanagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Vishrut Trivedi
 */

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByCourses_Name(String name);
    List<Student> findByCourses_Id(Long id);
}
