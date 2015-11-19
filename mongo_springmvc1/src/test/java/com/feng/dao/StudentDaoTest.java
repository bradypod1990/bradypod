package com.feng.dao;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.feng.mongo.model.Student;

@RunWith(SpringJUnit4ClassRunner.class)
//@Transactional
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@ContextConfiguration(locations = { "classpath:ApplicationContext.xml" })
public class StudentDaoTest {

	@Autowired
	private StudentDao studentDao;
	
	
	
	
	@Test
	public void insertTest() {
		Student s = new Student();
		s.setAddress("¸£½¨ÁúÑÒ");
		s.setAge(25);
		s.setName("zf");
		s.setSports(Arrays.asList(new String[]{"ÀºÇò","×ãÇò","ÓðÃ«Çò"}));
		studentDao.insert(s);
	}
	
	@Test
	public void findAll() {
		List<Student> list= studentDao.findAll();
		for(Student s : list) {
			System.out.println(s);
		}
		
	}
	
	@Test
	public void updateAgeTest() {
		studentDao.incAgeByName("zoufeng", 100);
	}
	
	@Test
	public void pushAllSportTest() {
		studentDao.addSportsByName("zoufeng", Arrays.asList(new String[]{"basketball", "football"}));
	}
}
