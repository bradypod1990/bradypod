package com.feng.spring.security;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class MyAccessDecisionManager implements AccessDecisionManager {

	@Override
	public void decide(Authentication authentication, Object object,
			Collection<ConfigAttribute> configAttributes) throws AccessDeniedException,
			InsufficientAuthenticationException {
		System.out.println("--------------------------------------");
		if(null == configAttributes) {
			return;
		}
		Iterator<ConfigAttribute> ite = configAttributes.iterator();
		while(ite.hasNext()) {
			ConfigAttribute ca = ite.next();
//			String needRole = ((SecurityConfig)ca).getAttribute();
			String needRole = ca.toString();
			for(GrantedAuthority ga : authentication.getAuthorities()) {
				if(null != needRole && needRole.equals(ga.getAuthority())) {
					return;
				}
			}
		}
		 throw new AccessDeniedException("no right");     
	}

	@Override
	public boolean supports(ConfigAttribute arg0) {
		return true;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}


}
