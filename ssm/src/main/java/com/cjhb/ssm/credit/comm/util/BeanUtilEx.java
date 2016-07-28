/**
 * 
 *
 *@author：xuy@rayootech.com
 *@since： JDK1.6
 *@version：1.0
 *@date：2015年6月6日 下午2:11:57
 */

package com.cjhb.ssm.credit.comm.util;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

/**
 * @ClassName: BeanUtilEx
 * 
 * @author xuy@rayootech.com
 * @date 2015年6月6日 下午2:11:57
 * 
 */
public class BeanUtilEx extends BeanUtils {

	private BeanUtilEx() {
	}

	static {
		// 注册sql.date的转换器，即允许BeanUtils.copyProperties时的源目标的sql类型的值允许为空
		ConvertUtils.register(new UtilDateConvert(), java.util.Date.class);
		ConvertUtils.register(new BigDecimalConvert(), java.math.BigDecimal.class);
	}

	public static void copyProperties(Object target, Object source)
			throws InvocationTargetException, IllegalAccessException {
		// 支持对日期copy
		org.apache.commons.beanutils.BeanUtils.copyProperties(target, source);

	}

}
