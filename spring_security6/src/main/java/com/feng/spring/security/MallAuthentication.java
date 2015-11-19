package com.feng.spring.security;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class MallAuthentication extends AbstractAuthenticationToken {

	private String credentials;
	private User principal;

	/**
	 * Creates a token with the supplied array of authorities.
	 * 
	 * @param authorities
	 *            the collection of <tt>GrantedAuthority</tt>s for the principal
	 *            represented by this authentication object.
	 */
	public MallAuthentication(Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
	}

	public String getCredentials() {
		return credentials;
	}

	public void setCredentials(String credentials) {
		this.credentials = credentials;
	}

	public Object getPrincipal() {
		return principal;
	}

    public void setPrincipal(User principal) {
        this.principal = principal;
    }

}
