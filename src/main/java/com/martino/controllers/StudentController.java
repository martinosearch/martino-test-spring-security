package com.martino.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.martino.models.Student;

@RestController
@RequestMapping("api")
public class StudentController {

	private static final List<Student> STUDENTS = Arrays.asList(new Student(1, "James Bond"),
			new Student(2, "Maria Jones"), new Student(3, "Anna Smith"));

	@GetMapping("/student/{studentId}")
	public Student getStudent(@PathVariable Integer studentId) {
		return STUDENTS.stream().filter(student -> studentId.equals(student.getId())).findFirst()
				.orElseThrow(() -> new IllegalStateException(
						"Student " + studentId + " does note exists"));
	}
}
