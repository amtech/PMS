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
 * @ClassName: DateConvert
 * 
 * @author xuy@rayootech.com
 * @date 2015年6月6日 下午2:10:00
 *
 */

public class UtilDateConvert implements Converter {

    public Object convert(Class c, Object src) {
        if(src == null){
            return null;
        }
        return src;
        /*
        try{
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return df.parse(p.trim());
        }
        catch(Exception e){
            try {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                return df.parse(p.trim());
            } catch (ParseException ex) {
                return null;
            }
        }
        */
    }

}
