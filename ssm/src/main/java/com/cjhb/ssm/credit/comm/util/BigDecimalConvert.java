/**
* 
*
*@author：xuy@rayootech.com
*@since： JDK1.6
*@version：1.0
*@date：2015年6月6日 下午2:10:00
*/ 

package com.cjhb.ssm.credit.comm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.apache.commons.beanutils.Converter;

/**
 * @ClassName: BigDecimalConvert
 * 
 * @author xuy@rayootech.com
 * @date 2015年6月6日 下午2:10:00
 *
 */

public class BigDecimalConvert implements Converter {

    public Object convert(Class c, Object src) {
        if(src == null){
            return null;
        }
        return src;
    }

}
