package com.xinfan.blueblue.util;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * BEAN处理帮助类
 * 
 * @author cyp
 * 
 */
public class BeanUtils {

	/**
	 * 将pojo对象属性转换成map
	 * 
	 * @param bean
	 * @return
	 */
	public static Map toMap(Object bean) {
		Map hashMap = new HashMap();
		try {
			for (Class<?> clazz = bean.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
				Method m[] = clazz.getDeclaredMethods();
				for (int i = 0; i < m.length; i++) {
					String name = m[i].getName();
					if (name.startsWith("get")) {
						name = name.substring(3);
						hashMap.put(name, m[i].invoke(bean, new Object[0]));
					}
				}
			}
		} catch (Throwable e) {
			LogUtil.e(e.getMessage(), e);
		}
		return hashMap;
	}

	public static Map toDataMap(Object bean) {
		Map hashMap = new HashMap();
		try {
			for (Class<?> clazz = bean.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
				Method m[] = clazz.getMethods();
				for (int i = 0; i < m.length; i++) {
					String name = m[i].getName();
					if (name.startsWith("get")) {
						name = name.substring(3);
						hashMap.put(name, m[i].invoke(bean, new Object[0]));
					}
				}
			}

		} catch (Throwable e) {
			LogUtil.e(e.getMessage(), e);
		}
		return hashMap;
	}

}
