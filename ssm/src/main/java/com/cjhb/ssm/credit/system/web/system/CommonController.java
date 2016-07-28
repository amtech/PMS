/**
* 
*
*@author：
*@since： JDK1.6
*@version：1.0
*@date：2014-4-18 上午9:59:30
*/ 

package com.cjhb.ssm.credit.system.web.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.credit.web.BaseController;

/**
 * @ClassName: CommonController
 * @Description: 公共Controller 主要处理404页面、timeout页面、权限不够的页面跳转
 * @author 
 * @date 2014-4-18 上午9:59:30
 *
 */
@Controller
@RequestMapping("/common")
public class CommonController extends BaseController {

	/**
	 * @author: kevin
	 * @date: 2014-4-18 下午3:05:45
	 * @Description: session-timeout
	 * @return
	 */
	@RequestMapping("/sessionOut")
	public ModelAndView sessionOut(){
		return new ModelAndView("comm/session-timeout.jsp");
	}
	
	/**
	 * @author: kevin
	 * @date: 2015年6月6日 下午5:40:38
	 * @Description: welcomePage
	 * @return
	 */
	@RequestMapping("/welcomePage")
	public ModelAndView welcomePage(){
		return new ModelAndView("welcome.jsp");
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-4-18 下午3:05:58
	 * @Description: 404
	 * @return
	 */
	@RequestMapping("/pageNotFind")
	public ModelAndView pageNotFind(){
		return new ModelAndView("comm/error-404.jsp");
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-4-18 下午3:06:02
	 * @Description: 权限不够
	 * @return
	 */
	@RequestMapping("/notEnoughAuth")
	public ModelAndView notEnoughAuth(){
		return new ModelAndView("comm/error-auth.jsp");
	}
}
