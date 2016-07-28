package com.cjhb.ssm.credit.comm.security;

import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class FaAccessDecisionManager implements AccessDecisionManager {

	private final Logger log = Logger.getLogger(getClass().getName());	
	
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException,
			InsufficientAuthenticationException {
		
		log.debug("验证用户是否具有一定的权限--------");  
		if(configAttributes == null){
			return ;
		}
		
		Iterator<ConfigAttribute> ite = configAttributes.iterator();
		
		while(ite.hasNext()){
			ConfigAttribute ca = ite.next();
			String needRole = ((SecurityConfig)ca).getAttribute();
			if ("ROLE_ANONYMOUS".equals(needRole)){
				return;
			}
			if (log.isDebugEnabled()){
				log.debug("URL所要的权限:" + needRole);
			}
			for(GrantedAuthority ga:authentication.getAuthorities()){
				if (log.isDebugEnabled()){
					log.debug("取到的权限:" + ga.getAuthority());
				}
				if(needRole.equals(ga.getAuthority())){
					return;
				}
			}
		}
		
		throw new AccessDeniedException("权限认证失败！-------权限不足");
	}

	public boolean supports(ConfigAttribute attribute) {
		log.debug("角色名：" + attribute.getAttribute());  
		return true;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

}
