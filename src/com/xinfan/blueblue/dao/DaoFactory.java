package com.xinfan.blueblue.dao;

import java.util.HashMap;
import java.util.Map;

import com.xinfan.blueblue.util.LogUtil;

public class DaoFactory {

	public static Map<Class, Dao> daoCacheMap = new HashMap<Class, Dao>();

	public static <T> T getDao(Class<T> T) {
		Dao dao = daoCacheMap.get(T);
		if (dao == null) {
			try {
				dao = (Dao) init(T);
				daoCacheMap.put(T, dao);

			} catch (Exception e) {
				LogUtil.e("DAO", e.getCause());
			}
		}

		return (T) dao;
	}

	public static Object init(Class cls) throws InstantiationException, IllegalAccessException {
		return cls.newInstance();
	}
}
