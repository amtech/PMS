package com.cjhb.ssm.credit.comm.security;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.credit.comm.util.TreeOptions;
import com.credit.comm.util.TreeUtils;
import com.credit.comm.util.exception.TreeUtilsException;
import com.credit.entity.system.AuMenu;
import com.credit.entity.system.AuUser;
import com.credit.service.system.UserService;
import com.google.common.collect.Sets;

@SuppressWarnings("deprecation")
public class FaUserDetailsService implements UserDetailsService {

	private final Logger log = Logger.getLogger(getClass().getName());
	
	@Autowired
	private UserService userService;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", username);
		map.put("isValid", "1");
		AuUser user = userService.ifExists(map);
		
		if(user==null){
			throw new UsernameNotFoundException("用户名不正确!");
		}

		List<Map<String,String>> roleList = userService.getAllRole(user.getId());
		
		List<AuMenu> menuList = userService.getAllUserMenuByid(user.getId());
		
		user.setUserMenuList(menuList);
		
		TreeOptions options = new TreeOptions(menuList);
		options.setId("id");
		options.setText("menuName");
		options.setNodeLevel("menuLevel");
		options.setHasChild("hasChild");
		options.setParentId("superId");
		options.setExtendedFields(new String[]{"iconCode","menuPath"});
		
		try {
			user.setUserMenuJson(TreeUtils.getTree2String(options));
		} catch (TreeUtilsException e) {
			log.error(e.getMessage());
		}
		
		Set<GrantedAuthority> authSet = Sets.newHashSet();
		
		for (Map<String,String> roleMap : roleList) {
			authSet.add(new GrantedAuthorityImpl(roleMap.get("roleCode")));
		}
		
		if (menuList != null && menuList.size() > 0){
			for (AuMenu menu : menuList) {
				if (!StringUtils.isBlank(menu.getMenuCode())){
					authSet.add(new GrantedAuthorityImpl(menu.getMenuCode()));
				}
			}
		}
		
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		
		FaUserDetails faUserDetails = new FaUserDetails(user.getUsername(), user.getPasswd(), enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked,authSet);
		
		faUserDetails.setUser(user);
		
		return faUserDetails;
	}

	/**
	 * 获得用户所有角色的权限.
	
	private Set<GrantedAuthority> obtainGrantedAuthorities(List<role> roles) {
		Set<GrantedAuthority> authSet = Sets.newHashSet();
		for (Role role : roles) {
			authSet.add(new GrantedAuthorityImpl("ROLE_" + role.getName()));
		}
		return authSet;
	}
	 */
}
