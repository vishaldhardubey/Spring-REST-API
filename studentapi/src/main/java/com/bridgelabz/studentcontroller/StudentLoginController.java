package com.bridgelabz.studentcontroller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.model.Student;
import com.bridgelabz.studentservice.StudentServiceImplementation;
import com.bridgelabz.token.TokenGenerator;

@RestController
@SuppressWarnings({ "rawtypes", "unchecked" })
public class StudentLoginController {
	@Autowired
	private StudentServiceImplementation studentImple;
	@Autowired
	private TokenGenerator token ;
	@RequestMapping(method=RequestMethod.POST,value="/studentLogin")
	public ResponseEntity loginEmail(@RequestBody Student student) {
		if(studentImple.loginStudent(student).get()!=null){
			String validToken=token.generator(student);
			System.out.println(validToken+"****************************************");
			token.parseJWT(validToken);
			return new ResponseEntity("Your Details ====="+student.toString(),HttpStatus.OK);	
		}
		return new ResponseEntity("Invalid Credentials",HttpStatus.CONFLICT);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/studentRegister")
	public ResponseEntity<Student> registration(@RequestBody Student student,HttpServletResponse resp){
		if(studentImple.registerStudent(student)) {
			return new ResponseEntity("Student Already Exists",HttpStatus.CONFLICT);	
		}
		else {
			@SuppressWarnings("unused")
			String validToken=token.generator(student);
			System.out.println(validToken+"****************************************");
			token.parseJWT(validToken);
			studentImple.saveStudent(student);
			return new ResponseEntity("Successfully Registered", HttpStatus.OK);
		}
	}
	@RequestMapping
	public String authorized() {
		return "You are authorized person";
	}
}
