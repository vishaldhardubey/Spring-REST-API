package com.bridgelabz.studentservice;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.dao.DAO;
import com.bridgelabz.mail.Mailer;
import com.bridgelabz.model.Student;
import com.bridgelabz.token.TokenGenerator;

@Service
public class StudentServiceImplementation {
	@Autowired
	private DAO dao;
	
	@Autowired
	private TokenGenerator token ;
	                                                                                                                                                                                                        
	@Autowired
	private Mailer mail ;
	/**
	 * @param student
	 * @return student1 object
	 */
	public Optional<Student> loginStudent(Student student) {
		String id=student.getEmail();
		Optional<Student> student1=dao.findById(id);
		String validToken=token.generator(student);
		System.out.println(validToken+"****************************************");
		token.parseJWT(validToken);
		return student1;
	}
	
	/**
	 * Function is to register the student info inside database.
	 * @param student
	 * @return boolean
	 */
	public boolean registerStudent(Student student){
		if(dao.existsById(student.getEmail())) {
			return true;
		}
		return false;
	}
	public void saveStudent(Student student) {
		dao.insert(student);
	}

	/**
	 * Function is to send mail.
	 * @param student
	 * @return boolean
	 */
	public boolean forgetPassword(Student student) {
		String id=student.getEmail();
		Optional<Student> students=dao.findById(id);
		String userEmail=students.get().getEmail();
		String password=students.get().getPassword();
		if(loginStudent(student)!=null) {
		final String admin = "vishalcapgemini1807@gmail.com";
		final String passwordAdmin = "vish@1993";
	    	System.out.println("********************* Inside sendmailservice");
	    	String email=student.getEmail();
			mail.send(email, admin, passwordAdmin, userEmail, password);
			return true;
		}
		return false;
	}
}
