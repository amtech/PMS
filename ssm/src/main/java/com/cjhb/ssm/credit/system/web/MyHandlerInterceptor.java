	/**
 * 
 */
package com.cjhb.ssm.credit.system.web;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.credit.comm.security.FaUserDetails;

/**
 * @author teanger
 *
 */
public class MyHandlerInterceptor implements HandlerInterceptor {

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object object, ModelAndView model) throws Exception {
			
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		/**
		 * 2014-04-17 吴敬磊
		 * 处理ajax提交session超时问题
		 */
		if (request.getHeader("x-requested-with") != null
					&& request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){  
			Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (!(obj instanceof FaUserDetails)){  
				response.setHeader("sessionstatus", "timeout");
				return false;  
			}  
		}  
		
		String url = request.getRequestURL().toString();
		if (url.indexOf("?")>0){
			url=url.split("?")[0];
		}
		if (url.endsWith("/checkadmin") || url.endsWith("/hasadmin")
				|| url.endsWith("/haslogin") || url.endsWith("/logout")
				|| url.endsWith("/regadmin") || url.endsWith("/index.html")
				|| url.indexOf("/admin/app/")>=0
				|| url.indexOf("/admin/extjs/")>=0
				|| url.indexOf("/admin/i18n/")>=0
				|| url.indexOf("/admin/resources/")>=0
				|| url.indexOf("/admin/app.js")>=0) {
			return true;
		}
		if (request.getSession().getAttribute("ADMIN")!=null){
			return true;
		}
		
		//后台未登录  
		if (url.indexOf("/admin/")>=0){
			//如果是ajax请求响应头会有，x-requested-with；  
			 if (request.getHeader("x-requested-with") != null  
                     && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){  
                 response.setHeader("sessionstatus", "timeout");//在响应头设置session状态  
                 return false;  
             }else{
            	 response.setContentType("text/html;charset=UTF-8");  
				 response.setCharacterEncoding("UTF-8");  
				 PrintWriter out = response.getWriter();  
				 StringBuilder builder = new StringBuilder();  
	            builder.append("<script type=\"text/javascript\" charset=\"UTF-8\">");  
	            builder.append("alert(\"页面过期，请重新登录\");");  
	            builder.append("window.top.location.href=\"");  
	            builder.append(request.getContextPath());  
	            builder.append("/\";</script>");  
	            out.print(builder.toString());  
	            out.close();
             }
            return false;  
		}
		
		return true;
	}

}
