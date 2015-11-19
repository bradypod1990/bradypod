package com.feng.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.feng.mongo.model.Student;

@Repository
public interface StudentRepository extends MongoRepository<Student, String>{

	public List<Student> findByAgeGreaterThan(int age);
	
}
