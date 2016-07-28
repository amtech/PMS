/**
* 
*
*@author：
*@since： JDK1.6
*@version：1.0
*@date：2014-4-14 上午10:51:56
*/ 

package com.cjhb.ssm.credit.comm.util.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @ClassName: ElementIsShowTag
 * @Description: 权限校验标签
 * @author 
 * @date 2014-4-14 上午10:51:56
 */
public class ElementIsShowTag extends TagSupport {

	private static final long serialVersionUID = -1867236566675195320L;
	
	private String code;
	
	private String url;
	
	@Override
	public int doStartTag() throws JspException {
		boolean result = false;
		// TODO 添加用户的页面级的权限校验
		return result? EVAL_BODY_INCLUDE : SKIP_BODY;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
}