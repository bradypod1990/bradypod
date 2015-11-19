package com.feng.dao;

import java.util.List;

import com.feng.model.User;

public interface UserDao {

	public void saveUser(User user);
	
	public List<User> findAll();
	
	public User getUserById(int id);
	
	public void update(User user);
	
	public User findUserByName(String name);
	
	public boolean isLogin(String name, String password);
}
