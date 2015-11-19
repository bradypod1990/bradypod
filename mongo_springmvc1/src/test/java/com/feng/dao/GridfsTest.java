package com.feng.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsCriteria;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongodb.gridfs.GridFSDBFile;


@RunWith(SpringJUnit4ClassRunner.class)
//@Transactional
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@ContextConfiguration(locations = { "classpath:ApplicationContext.xml" })
public class GridfsTest {

	@Autowired
	private GridFsTemplate gridFsTemplate;
	
	
	@Test
	public void addFiletest() throws FileNotFoundException {
		
		File file = new File("e:/app.properties");
		System.out.println("hello ");
		InputStream is = new FileInputStream(file);
		gridFsTemplate.store(is, "app.properties");
	}
	
	@Test
	public void findFiletest() throws IOException {
		
		File file = new File("e:/app1.properties");
		//GridFSDBFile gfs = gridFsTemplate.findOne(Query.query(GridFsCriteria.whereFilename().is("app.properties")));
		GridFSDBFile gfs = gridFsTemplate.findOne(Query.query(GridFsCriteria.where("md5").is("10b53db27882977265463bb6b9b57151")));
		System.out.println(gfs.getFilename());
//		IOUtils.copy(gfs.getInputStream(), new FileOutputStream(file));
		List<String> list = IOUtils.readLines(gfs.getInputStream());
		for(String s : list) {
			System.out.println(s);
		}
	}
}
