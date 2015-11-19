package com.feng.spring.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.feng.dao.UserDao;

public class MallAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private UserDao userDao;

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		
		String name = authentication.getName();
		String password = (String) authentication.getCredentials();
		boolean isLogin = userDao.isLogin(name, password);
		if(isLogin) {
			ArrayList<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
			list.add(new SimpleGrantedAuthority("ROLE_USER"));
			User user = new User(name, password, list);
			MallAuthentication token = new MallAuthentication(user.getAuthorities());
			token.setAuthenticated(true);
			token.setPrincipal(user);
			token.setDetails(user);
			return token;
		}else {
			authentication.setAuthenticated(false);
			if(authentication instanceof UsernamePasswordAuthenticationToken) {
				UsernamePasswordAuthenticationToken upat = (UsernamePasswordAuthenticationToken)authentication;
				upat.setDetails("”√ªß√˚√‹¬Î¥ÌŒÛ");
				return upat;
			}
			return authentication;
		}
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

}
