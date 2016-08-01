package com.cjhb.pms.spm.web.controller;

import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.springagg.web.bean.Resource;
import org.springagg.web.service.ResourceService;
import org.springagg.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页控制器
 * 
 * @author ArchX[archx@foxmail.com]
 */
@Controller
public class IndexController {

	@Autowired
	private UserService usv;

	@Autowired
	private ResourceService rsv;

	@RequestMapping("/")
	public String index(Model model) {
		String username = (String) SecurityUtils.getSubject().getPrincipal();
		Set<String> permissions = usv.getPermissions(username);
		List<Resource> menus = rsv.getMenus(permissions);
		model.addAttribute("menus", menus);
		return "index";
	}

	@RequestMapping("/welcome")
	public String welcom(Model model) {
		String username = (String) SecurityUtils.getSubject().getPrincipal();
		model.addAttribute("roles", usv.getRoles(username));
		model.addAttribute("permissions", usv.getPermissions(username));
		return "welcome";
	}

}
