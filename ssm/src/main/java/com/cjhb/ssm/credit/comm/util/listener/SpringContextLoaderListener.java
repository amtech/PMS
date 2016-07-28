/**
* 
*
*@author：
*@since： JDK1.6
*@version：1.0
*@date：2015年5月23日 下午2:24:15
*/ 

package com.cjhb.ssm.credit.comm.util.listener;

import com.cjhb.ssm.credit.comm.util.SpringUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
  
/**
 * @ClassName: SpringContextLoaderListener
 * @Description: 自定义spring加载器，把ApplicationContext装载到SpringUtil 
 * @author 
 * @date 2015年5月23日 下午2:24:15
 *
 */
public class SpringContextLoaderListener extends ContextLoaderListener {  
  
    public void contextInitialized(ServletContextEvent event) {  
  
        // 初始化父类 ContextLoaderListener  
        super.contextInitialized(event);  
  
        ServletContext context = event.getServletContext();  
  
        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);  
  
        // 装载 SpringUtil 中的 ApplicationContext  
        new SpringUtil().setApplicationContext(ctx);  
    }  
}  
