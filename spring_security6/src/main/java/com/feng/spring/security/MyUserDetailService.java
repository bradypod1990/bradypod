package com.feng.spring.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.feng.dao.UserDao;

public class MyUserDetailService implements UserDetailsService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		//SimpleGrantedAuthority user = new SimpleGrantedAuthority("ROLE_USER");
		//SimpleGrantedAuthority admin = new SimpleGrantedAuthority("ROLE_ADMIN");
		com.feng.model.User u = userDao.findUserByName(username);
		if(u != null) {
			auths.add( new SimpleGrantedAuthority("ROLE_USER"));
			User user = new User(username, "123", true, true, true, true, auths);
			return user;
		}else {
			return null;
		}
	}
}
