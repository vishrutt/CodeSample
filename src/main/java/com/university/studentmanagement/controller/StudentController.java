package com.university.studentmanagement.controller;

import com.university.studentmanagement.exception.ResourceNotFoundException;
import com.university.studentmanagement.model.Student;
import com.university.studentmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Vishrut Trivedi
 */
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping(
            value = {"", "/"},
            produces = {"application/json", "application/xml"}
    )
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @PostMapping(
            value = {"", "/"},
            produces = {"application/json", "application/xml"},
            consumes= {"application/json", "application/xml"}
    )
    public Student createStudent(@Valid @RequestBody Student student) {
        return studentRepository.save(student);
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable(value = "id") Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable(value = "id") Long studentId,
                              @Valid @RequestBody Student studentDetails) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));

        if(null!=studentDetails.getName() || !studentDetails.getName().trim().equals("")) {
            student.setName(studentDetails.getName());
        }
        if(null!=studentDetails.getDob() || !studentDetails.getDob().toString().trim().equals("")) {
            student.setDob(studentDetails.getDob());
        }

        Student updatedStudent = studentRepository.save(student);
        return updatedStudent;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable(value = "id") Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));

        studentRepository.delete(student);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(
            path = {"/bycourseid/{courseId}"},
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"}
    )
    public List<Student> getStudentsbyCourseId(@PathVariable(value = "courseId")Long courseId)
    {
        return studentRepository.findByCourses_Id(courseId);
    }

}
