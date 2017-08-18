package org.iii.core.util;

import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class StringCommonUtil {
	
	public static String trimToString(Object obj) {
		String result = "";
		
		if ( obj == null ) return result;
		
		if ( obj instanceof Collection ) {
			result = StringUtils.trimToEmpty(obj.toString());
		} else if ( obj instanceof Map ) {
			result = StringUtils.trimToEmpty(obj.toString());
		} else if ( obj instanceof Object[] ) {
			result = StringUtils.trimToEmpty(ArrayUtils.toString(obj));
		} else if ( obj instanceof String ) {
			result = StringUtils.trimToEmpty((String) obj);
		} else {
			result = "";
		}
		return result;
	}
	
	public static boolean isOnlyASCII(String str) {
		if ( BeanUtil.isEmpty(str) ) return true;
		Pattern p = Pattern.compile("[-\\w\\s]*");
		Matcher m = p.matcher(str);
		return m.matches();
	}
	
	public static String toString(Object obj) {
		return ReflectionToStringBuilder.toString(obj, ToStringStyle.MULTI_LINE_STYLE);
	}
	
	public static String parseNumber(String str) {
		String result = null;
		
		if ( BeanUtil.isNotEmpty(str) && StringCommonUtil.isDigitsOnly(str) ) {
			result = StringUtils.replace(StringUtils.trim(str), ",", "");
		}
		
		return result;
	}
	
	/**
     * 擷取字串裡的數字
     *
     * @param s     要擷取的字串
     * @return String   擷取結果
     */
	public static String getDigitsOnly(String s) {
		StringBuffer result = new StringBuffer();
		char c;
		for (int i = 0; i < s.length(); i++) {
			c = s.charAt(i);
			if (Character.isDigit(c)) {
				result.append(c);
			}
		}
		return result.toString();
	}

	/**
	 * @return
	 */
	public static boolean isDigitsOnly(String s) {
		String regex = "^0(\\.0*)?$|^(-?0?\\.0*[1-9]\\d*)$|^(-?[1-9]((\\d{0,2},(\\d{3},)*\\d{3})|(\\d)*)(\\.\\d*)?)$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(s);		
		return m.matches();
	}
	
	/**
	 * 將指定pattern的字串replace
	 * 
	 * @param str
	 * @param leftPattern
	 * @param rightPattern
	 * @param keyValues
	 * @return
	 */
	public static String replace(String str, String leftPattern, String rightPattern, Map<String, Object> keyValues) {
		for (String key : keyValues.keySet()) {
			if(BeanUtil.isNotEmpty(keyValues.get(key))) {
				str = StringUtils.replace(str, leftPattern + key + rightPattern, keyValues.get(key).toString());
			}
		}
		return str;
	}
	
	public static String toUpperCase(String str) {
		if ( !BeanUtil.isEmpty(str) ) {
			return StringUtils.trim(str).toUpperCase();
		} else {
			return "";
		}
	}
	
	public static String toLowerCase(String str) {
		if ( !BeanUtil.isEmpty(str) ) {
			return StringUtils.trim(str).toLowerCase();
		} else {
			return "";
		}
	}
	
	/**
	 * 將字串理指定範圍的字串轉大寫
	 * @param str
	 * @param indexBegin
	 * @param indexEnd
	 * @return
	 */
	public static String toUpperCase(String str, int indexBegin, int indexEnd) {
		StringBuilder sb = new StringBuilder();
		if(BeanUtil.isNotEmpty(str) 
				&& indexBegin >= 0 && indexBegin < str.length()
				&& indexEnd >= indexBegin && indexEnd < str.length()) {
			
			String rangeStr = str.substring(indexBegin, indexEnd + 1);
			sb.append(str).replace(indexBegin, indexEnd + 1, rangeStr.toUpperCase());
		}
		return sb.toString();
	}
	
	/**
	 * 將字串理指定範圍的字串轉小寫
	 * @param str
	 * @param indexBegin
	 * @param indexEnd
	 * @return
	 */
	public static String toLowerCase(String str, int indexBegin, int indexEnd) {
		StringBuilder sb = new StringBuilder();
		if(BeanUtil.isNotEmpty(str) 
				&& indexBegin >= 0 && indexBegin < str.length()
				&& indexEnd >= indexBegin && indexEnd < str.length()) {
			
			String rangeStr = str.substring(indexBegin, indexEnd + 1);
			sb.append(str).replace(indexBegin, indexEnd + 1, rangeStr.toLowerCase());
		}
		return sb.toString();
	}
	
	public static boolean isPositiveNum(String str) {
		if(BeanUtil.isNotEmpty(str)) {
			String pattern = "^[0-9]*[1-9][0-9]*$";
			return str.matches(pattern);
		}
		return false;
	}
	
	public static String concats(Object ...objs) {
		if (objs == null) return null;
		
		StringBuffer buf = new StringBuffer();
		for (Object obj : objs) {
			if (obj == null) continue;
			buf.append(obj.toString());
		}
		
		return buf.toString();
	}

}
