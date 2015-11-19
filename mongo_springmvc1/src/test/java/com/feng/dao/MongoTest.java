package com.feng.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.script.ExecutableMongoScript;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.feng.model.ValueObject;
import com.feng.model.XObject;
import com.feng.mongo.model.Student;

@RunWith(SpringJUnit4ClassRunner.class)
//@Transactional
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@ContextConfiguration(locations = { "classpath:ApplicationContext.xml" })
public class MongoTest {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Test
	public void insertTest() {
		Random r = new Random();
		List<Student> list = new ArrayList<Student>();
		for(int i=0; i<100; i++) {
			Student s = new Student();
			s.setAddress("fujian" + i);
			s.setName("zoufeng" + i);
			s.setAge(r.nextInt(100));
			list.add(s);
		}
		
		mongoTemplate.insertAll(list);
	}
	
	@Test
	public void mapreduceTest() {
		MapReduceResults<ValueObject> results =  mongoTemplate.mapReduce("student", "classpath:map.js", "classpath:reduce.js", ValueObject.class);
		for(ValueObject s:results) {
			System.out.println(s);
		}
	}
	
	@Test
	public void scriptTest() {
		ExecutableMongoScript echoScript = new ExecutableMongoScript("function(x) { return x; }");
		Object o = mongoTemplate.scriptOps().execute(echoScript, "directly excute script");
		System.out.println(o);
	}
	
	@Test
	public void groupTest() {
		GroupByResults<XObject> gbr =  mongoTemplate.group("student", GroupBy.key("age").initialDocument("{names:''}").reduceFunction("function(doc,prev){prev.names +='_'+doc.name}"), XObject.class);
		for(XObject vb : gbr) {
			System.out.println(vb);
		}
	}
}
