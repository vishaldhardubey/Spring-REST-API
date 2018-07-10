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
	
	public Optional<Student> loginStudent(Student student) {
		String id=student.getEmail();
		Optional<Student> student1=dao.findById(id);
		return student1;
	}
	
	public boolean registerStudent(Student student){
		if(dao.existsById(student.getEmail())) {
			return true;
		}
		return false;
	}
	public void saveStudent(Student student) {
		dao.insert(student);
	}
}
