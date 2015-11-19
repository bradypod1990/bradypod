package com.feng.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.feng.dao.StudentDao;
import com.feng.mongo.model.Student;
import com.feng.mongo.repository.StudentRepository;

@Controller
public class MongoController {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private StudentDao studentDao;
	
	@RequestMapping("/mongo")
	public String mongo() {
		
		Student s = new Student();
		s.setAddress("fujian");
		s.setAge(32);
		s.setName("zoufeng");
		studentRepository.save(s);
		return "test";
	}

	
	
	@RequestMapping("/find")
	public String find(@RequestParam int age,HttpServletRequest request,HttpServletResponse response) {
//		List<Student> list = studentRepository.findByAgeGreaterThan(age);
		List<Student> list = studentDao.findAll();
		System.out.println(list);
		return "test";
	}
}
