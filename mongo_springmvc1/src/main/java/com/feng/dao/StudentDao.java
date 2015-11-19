package com.feng.dao;

import java.util.List;

import com.feng.mongo.model.Student;

public interface StudentDao {

	
	public void insert(Student student);
	
	
	public List<Student> findAll();
	
	
	public List<Student> findByName(String name);
	
	public void incAgeByName(String name, int age);
	
	
	public void addSportsByName(String name, List<String> sports);
}
