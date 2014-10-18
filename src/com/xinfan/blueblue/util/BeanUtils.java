package com.xinfan.blueblue.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

	public static void copyProperty(Object src, Object target) {

		try {

			if (target == null) {
				throw new Exception("目标对象不能为空");
			} else if (src == null) {
				target = null;
			} else {
				Field[] srcFields = Class.forName(src.getClass().getName()).getDeclaredFields();

				Field[] targetFields = Class.forName(target.getClass().getName()).getDeclaredFields();

				for (Field sf : srcFields) {
					sf.setAccessible(true);
					if (Modifier.isFinal(sf.getModifiers()))
						continue;
					for (Field tf : targetFields) {

						tf.setAccessible(true);
						if (Modifier.isFinal(tf.getModifiers()))
							continue;

						if (sf.getName().equals(tf.getName())) {

							if (sf.getType().equals(tf.getType()))// 如果数据类型相同，直接赋值
								tf.set(target, sf.get(src));
							else
								tf.set(target, sf.get(src).toString());
							break;

						}

					}

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.e(e.getMessage(), e);
		}
	}

	public static <T, S> List<S> copyList(List<T> src, Class<S> cls) {

		ArrayList<S> target = new ArrayList<S>();

		if (src != null) {
			for (int i = 0; i < src.size(); i++) {
				try {
					S item = (S) cls.newInstance();
					copyProperty(src.get(i), item);
					target.add(item);
				} catch (Exception e) {
					e.printStackTrace();
					LogUtil.e(e.getMessage(), e);
				}
			}
		}

		return target;
	}

}
