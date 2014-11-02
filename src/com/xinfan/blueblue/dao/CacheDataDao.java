package com.xinfan.blueblue.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.xinfan.blueblue.util.LogUtil;

public class CacheDataDao implements Dao {

	/**
	 * 获得url 缓存
	 * 
	 * @param url
	 * @return
	 */
	public String getURLData(String url) {
		Cursor cursor = null;
		SQLiteDatabase db = null;
		try {
			db = DBHelper.getInstance().getDb();
			cursor = db.rawQuery("select data,create_time from t_cachedata where url = ?", new String[] { url });
			while (cursor.moveToNext()) {
				String data = cursor.getString(0);
				long time = cursor.getLong(1);

				return data;
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.e(e.getMessage(), e);
		} finally {
			SqlUtils.closeCursor(cursor);
			SqlUtils.closeDb(db);
		}
		return null;
	}

	/**
	 * 删除记录
	 * 
	 * @return
	 */
	public boolean deleteURLData(String url) {
		SQLiteDatabase db = null;
		try {
			db = DBHelper.getInstance().getDb();
			return db.delete("t_cachedata", "url=?", new String[] { url }) > 0;
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.e(e.getMessage(), e);
		} finally {
			SqlUtils.closeDb(db);
		}
		return false;
	}

	public boolean clearURLData(String url) {
		SQLiteDatabase db = null;
		try {
			db = DBHelper.getInstance().getDb();
			return db.delete("t_cachedata", null, null) > 0;
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.e(e.getMessage(), e);
		} finally {
			SqlUtils.closeDb(db);
		}
		return false;
	}

	public void addOrUpdateURLData(String url, String jsonData) {
		SQLiteDatabase db = null;
		try {
			boolean isExists = checkURLData(url);
			db = DBHelper.getInstance().getDb(); // 获得数据库写对象
			// SimpleDateFormat sdf = new
			// SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (isExists) {
				db.execSQL("update t_cachedata set data =?, create_time = ? where url =? ", new Object[] { jsonData, new Date().getTime(), url });
			} else {
				db.execSQL("insert into t_cachedata (url, data, create_time) values(?, ?, ?)", new Object[] { url, jsonData, new Date().getTime() });
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.i("db", url);
			LogUtil.e(e.getMessage(), e);
		} finally {
			SqlUtils.closeDb(db);
		}
	}

	private boolean checkURLData(String url) {
		Cursor cursor = null;
		SQLiteDatabase db = null;
		try {
			db = DBHelper.getInstance().getDb();
			cursor = db.rawQuery("select data from t_cachedata where url = ?", new String[] { url });
			while (cursor.moveToNext()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.e(e.getMessage(), e);
		} finally {
			SqlUtils.closeCursor(cursor);
			SqlUtils.closeDb(db);
		}
		return false;
	}

}
