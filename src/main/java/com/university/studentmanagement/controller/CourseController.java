package com.university.studentmanagement.controller;

import com.university.studentmanagement.exception.ResourceNotFoundException;
import com.university.studentmanagement.model.Course;
import com.university.studentmanagement.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController{
        @Autowired
        CourseRepository courseRepository;

        @GetMapping(
                value = {"", "/"},
                produces = {"application/json", "application/xml"}
        )
        public List<Course> getAllCouorses() {
            return courseRepository.findAll();
        }

    @PostMapping(
            value = {"", "/"},
            produces = {"application/json", "application/xml"},
            consumes= {"application/json", "application/xml"}
    )
        public Course createCouorse(@Valid @RequestBody Course course) {
            return courseRepository.save(course);
        }

        @GetMapping("/{id}")
        public Course getCouorseById(@PathVariable(value = "id") Long courseId) {
            return courseRepository.findById(courseId)
                    .orElseThrow(() -> new ResourceNotFoundException("Course", "id", courseId));
        }

        @PutMapping("/{id}")
        public Course updateCouorse(@PathVariable(value = "id") Long courseId,
                                     @Valid @RequestBody Course courseDetails) {

            Course course = courseRepository.findById(courseId)
                    .orElseThrow(() -> new ResourceNotFoundException("Course", "id", courseId));
            if(null!=courseDetails.getName() || !courseDetails.getName().trim().equals("")) {
                course.setName(courseDetails.getName());
            }

            Course updatedCouorse = courseRepository.save(course);
            return updatedCouorse;
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<?> deleteCouorse(@PathVariable(value = "id") Long courseId) {
            Course course = courseRepository.findById(courseId)
                    .orElseThrow(() -> new ResourceNotFoundException("Course", "id", courseId));

            courseRepository.delete(course);

            return ResponseEntity.ok().build();
        }




}
