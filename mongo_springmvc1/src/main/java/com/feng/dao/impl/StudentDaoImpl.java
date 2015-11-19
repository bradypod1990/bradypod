package com.feng.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

import com.feng.dao.StudentDao;
import com.feng.mongo.model.Student;

@Repository
public class StudentDaoImpl implements StudentDao {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public void insert(Student student) {
		mongoTemplate.save(student);
	}

	@Override
	public List<Student> findAll() {
		return mongoTemplate.findAll(Student.class);
	}

	@Override
	public List<Student> findByName(String name) {
		
		//mongoTemplate.u
		
		return mongoTemplate.find(Query.query(Criteria.where("name").is(name)), Student.class);
		
	}

	@Override
	public void incAgeByName(String name, int age) {
		mongoTemplate.updateMulti(query(where("name").is(name)), new Update().inc("age",age), Student.class);
	}

	@Override
	public void addSportsByName(String name, List<String> sports) {
		mongoTemplate.updateMulti(query(where("name").is(name)), new Update().pushAll("sports", sports.toArray()), Student.class);
	}
	
	

}
