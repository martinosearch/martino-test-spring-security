package com.martino.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.martino.models.Student;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("management/api/student")
@Slf4j
public class StudentManagementController {

	private static final List<Student> STUDENTS = Arrays.asList(new Student(1, "James Bond"),
			new Student(2, "Maria Jones"), new Student(3, "Anna Smith"));

	@GetMapping("/list")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMINTRAINEE')")
	public List<Student> getAll() {
		return STUDENTS;
	}

	@PostMapping("/save")
	@PreAuthorize("hasAuthority('student:write')")
	public void registerNewStudent(@RequestBody Student student) {
		log.info("Student saved");
	}

	@GetMapping("/delete/{id}")
	@PreAuthorize("hasAuthority('student:write')")
	public void deleteStudent(@PathVariable Integer id) {
		log.info("Student {} deleted", id);
	}

	@PutMapping("/upadate/{id}")
	@PreAuthorize("hasAuthority('student:write')")
	public void updateStudent(@PathVariable Integer id, @RequestBody Student student) {
		log.info(String.format("%s %s", student, student));
	}

	@GetMapping("/student/{studentId}")
	@PreAuthorize("hasAuthority('student:write')")
	public Student getStudent(@PathVariable Integer studentId) {
		return STUDENTS.stream().filter(student -> studentId.equals(student.getId())).findFirst()
				.orElseThrow(() -> new IllegalStateException(
						"Student " + studentId + " does note exists"));
	}
}
