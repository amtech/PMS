/**
* 
*
*@author：
*@since： JDK1.6
*@version：1.0
*@date：2014-4-24 上午14:51:56
*/ 

package com.cjhb.ssm.credit.comm.util.taglib;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;

import com.credit.comm.util.BaseDataUtils;
import com.credit.entity.system.BaseDataItem;


/**
* @ClassName: DataBaseTag
* @Description: 数据字典标签
* @author 
* @date 2014-4-24 下午3:06:43
*
*/
public class DataBaseTag extends TagSupport {

	private static final long serialVersionUID = -8067977943211881109L;

	private String code;
	
	private String id;
	
	private String name;
	
	private String classStyle;
	
	private String resultType;
	
	private String readonly;
	
	private String value;
	
	private String placeholder;
	
	@Override
	public int doStartTag() throws JspException {
		try {

            JspWriter out = this.pageContext.getOut();

            if(StringUtils.isBlank(code)) {
                out.println("Err: No Code Found...");
                return SKIP_BODY;
            }
            
            List<BaseDataItem> itemList = BaseDataUtils.getBaseDataItems(code);
            
            if(itemList == null) {
                out.println("Err: (" + code + ")Item Is Empty...");
                return SKIP_BODY;
            }
            
            if ("text".equals(resultType)){
            	if (!StringUtils.isBlank(value)){
            		for (BaseDataItem baseDataItem : itemList) {
            			if (value.equals(baseDataItem.getCode())){
            				out.print(baseDataItem.getName());
            				break;
            			}
            		}
           		}else{
            		out.print("");
            	}
            }else if ("select".equals(resultType)){
            	out.print("<select ");
            	if (!StringUtils.isBlank(id)){
            		out.print("id='" + id + "' ");
            	}
            	if (!StringUtils.isBlank(name)){
            		out.print("name='" + name + "' ");
            	}
            	if (!StringUtils.isBlank(classStyle)){
            		out.print("class='" + classStyle + "' ");
            	}
            	out.print(">");

            	if(StringUtils.isBlank(placeholder)){
            		placeholder = "";
            	}
            	
            	out.print("<option value=''>请选择" + placeholder + "</option>");
            	for (BaseDataItem baseDataItem : itemList) {
            		out.print("<option value='" + baseDataItem.getCode() + "'");
            		if (!StringUtils.isBlank(value)){
            			if (value.equals(baseDataItem.getCode())){
            				out.print(" selected='true' ");
            			}
            		}
            		out.print(">" + baseDataItem.getName() + "</option>");
            	}
            	out.print("</select>");
            }else if ("hideList".equals(resultType)){
            	out.print("<ul id='hide-" + code + "' style='display: none'>");
            	for (BaseDataItem baseDataItem : itemList) {
            		out.print("<li id='hide-" + baseDataItem.getCode() + "'");
            		out.print(">" + baseDataItem.getName() + "</li>");
            	}
            	out.print("</ul>");
            }else if ("input".equals(resultType)){
            	out.print("<input type='text' ");
            	if (!StringUtils.isBlank(id)){
            		out.print("id='" + id + "' ");
            	}
            	if (!StringUtils.isBlank(name)){
            		out.print("name='" + name + "' ");
            	}
            	if (!StringUtils.isBlank(classStyle)){
            		out.print("class='" + classStyle + "' ");
            	}
            	if (!"false".equals(readonly)){
            		out.print("readonly='true' ");
            	}
            	for (BaseDataItem baseDataItem : itemList) {
            		if (!StringUtils.isBlank(value)){
            			if (value.equals(baseDataItem.getCode())){
            				out.print(" value='" + baseDataItem.getName() + "' ");
            			}
            		}
            	}
            	out.print(">");
            }
        } catch(Exception e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
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
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the classStyle
	 */
	public String getClassStyle() {
		return classStyle;
	}

	/**
	 * @param classStyle the classStyle to set
	 */
	public void setClassStyle(String classStyle) {
		this.classStyle = classStyle;
	}

	/**
	 * @return the resultType
	 */
	public String getResultType() {
		return resultType;
	}

	/**
	 * @param resultType the resultType to set
	 */
	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the readonly
	 */
	public String getReadonly() {
		return readonly;
	}

	/**
	 * @param readonly the readonly to set
	 */
	public void setReadonly(String readonly) {
		this.readonly = readonly;
	}

	/**
	 * @return the placeholder
	 */
	public String getPlaceholder() {
		return placeholder;
	}

	/**
	 * @param placeholder the placeholder to set
	 */
	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

}