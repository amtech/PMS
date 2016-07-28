package com.cjhb.ssm.credit.comm.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.credit.entity.system.AuUser;

public class FaUserDetails extends User {

	private static final long serialVersionUID = -2014858939062424433L;

	public FaUserDetails(String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, true, true, true, true, authorities);
	}

	public FaUserDetails(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}

	private AuUser user;

	public AuUser getUser() {
		return user;
	}

	public void setUser(AuUser user) {
		this.user = user;
	}

}
