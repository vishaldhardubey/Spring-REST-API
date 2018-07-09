package com.bridgelabz.studentcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.model.Student;
import com.bridgelabz.studentservice.StudentServiceImplementation;

@RestController
public class StudentLoginController {
	@Autowired
	private StudentServiceImplementation studentImple;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(method=RequestMethod.POST,value="/studentLogin")
	public ResponseEntity loginEmail(@RequestBody Student student) {
		if(studentImple.validateStudent(student).get()!=null){
			return new ResponseEntity("Your Details ====="+student.toString(),HttpStatus.OK);	
		}
		return new ResponseEntity("Invalid Credentials",HttpStatus.CONFLICT);
	}
}
