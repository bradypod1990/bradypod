package com.feng.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.feng.dao.UserDao;
import com.feng.model.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao{

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public void saveUser(User user) {
		hibernateTemplate.save(user);
	}

	@Override
	public List<User> findAll() {
		return null;
	}

	@Override
	public User getUserById(int id) {
		return null;
	}
	

	@Override
	public void update(User user) {
		
	}

	@Override
	public User findUserByName(String name) {
		List<User> list = (List<User>) hibernateTemplate.find("from User u where u.name=?", name);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}else {
			return null;
		}
	}

	@Override
	public boolean isLogin(String name, String password) {
		User user = this.findUserByName(name);
		if(user != null) {
			if(password.equals(user.getPassword())) {
				return true;
			}
		}
		return false;
	}  

}
