package com.feng.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.feng.dao.UserDao;
import com.feng.model.User;

@Controller
public class IndexController {

	@Resource(name="userDao")
	private UserDao userDao;
	
	@RequestMapping("/index")
	public String index() {
		System.out.println("index------------");
		return "/index";
	}
	
	@RequestMapping("saveUser") 
	public String save() {
		User user = new User();
		user.setName("james");
		user.setPassword("james");
//		user.setId(4);
		userDao.saveUser(user);
		return "/index";
	}
	
	@RequestMapping("updateUser") 
	public String update() {
		User user = new User();
		user.setName("james");
		user.setPassword("james");
		user.setId(6);
		userDao.update(user);
		return "/index";
	}
	
	@RequestMapping("getUser") 
	public String get() {
		User user = userDao.getUserById(6);
		System.out.println(user.toString());
		return "/index";
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
