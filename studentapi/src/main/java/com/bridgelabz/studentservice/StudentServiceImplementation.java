package com.bridgelabz.studentservice;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.dao.DAO;
import com.bridgelabz.model.Student;

@Service
public class StudentServiceImplementation {
	@Autowired
	private DAO dao;
	
	public Optional<Student> validateStudent(Student student) {
		String id=student.getEmail();
		Optional<Student> student1=dao.findById(id);
		return student1;
	}
}
