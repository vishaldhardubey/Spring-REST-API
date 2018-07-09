package com.bridgelabz.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.model.Student;

@Repository
public interface DAO extends MongoRepository<Student, String>{
}
