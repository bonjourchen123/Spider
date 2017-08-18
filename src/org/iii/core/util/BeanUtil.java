package org.iii.core.util;

import java.util.Collection;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class BeanUtil {
	
	/**
	 * @return true if obj is null or obj is empty
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object obj) {
		boolean result = true;
		
		if ( obj instanceof Collection ) {
			result = (isNull(obj) || ((Collection) obj).isEmpty());
		} else if ( obj instanceof Map ) {
			result = (isNull(obj) || ((Map) obj).isEmpty());
		} else if ( obj instanceof Object[] ) {
			result = ArrayUtils.isEmpty((Object[]) obj);
		} else if ( obj instanceof String ) {
			result = StringUtils.isEmpty((String) obj);
		} else {
			result = isNull(obj);
		}
		
		return result;
	}
	
	/**
	 * @return true if obj is not null and obj is not empty
	 */
	public static boolean isNotEmpty(Object obj) {
		return !isEmpty(obj);
	}
	
	/**
	 * @return true if obj is null
	 */
	public static boolean isNull(Object obj) {
		return obj == null;
	}
	
	/**
	 * @return true if obj is not null
	 */
	public static boolean isNotNull(Object obj) {
		return !isNull(obj);
	}
	
	public static Object getSpringBean(ServletContext sc, String beanId) {
		WebApplicationContext contxt = WebApplicationContextUtils.getWebApplicationContext(sc);
		return contxt.getBean(beanId);
	}
}
