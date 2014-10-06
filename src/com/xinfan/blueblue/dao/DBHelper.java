package com.xinfan.blueblue.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.xinfan.blueblue.util.LogUtil;

/**
 * 
 * 本地数据库
 * 
 * @author Administrator
 * 
 */
public class DBHelper extends SQLiteOpenHelper {
	private Context context;
	private SQLiteDatabase db;
	private static DBHelper mInstance = null;
	private static final String DBNAME = "db.db";
	private static final int VERSION = 1;

	private DBHelper(Context context, CursorFactory factory) {
		super(context, DBNAME, factory, VERSION);
	}

	public static DBHelper getInstance(Context context) {
		if (mInstance == null) {
			mInstance = new DBHelper(context, null);
		}
		mInstance.context = context;
		return mInstance;
	}

	public static DBHelper getInstance(Context context, SQLiteDatabase.CursorFactory factory) {
		if (mInstance == null) {
			mInstance = new DBHelper(context, factory);
		}
		mInstance.context = context;
		return mInstance;
	}

	/**
	 * 获得url 缓存
	 * 
	 * @param url
	 * @return
	 */
	public String[] getURLData(String url) {
		Cursor cursor = null;
		try {
			db = getReadableDatabase(); // 获得数据库读对象
			cursor = db.rawQuery("select cache_data,create_time from cache_url_data where url = ?", new String[] { url });
			while (cursor.moveToNext()) {
				String data = cursor.getString(0);
				String time = cursor.getString(1);
				cursor.close();
				return new String[] { data, time };
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// try {
			// if(db != null)
			// db.close();
			// } catch (Exception e) {
			// e.printStackTrace();
			// }
		}
		return null;
	}

	/**
	 * 添加或者更新 url 缓存
	 * 
	 * @param url
	 * @param jsonData
	 */
	public synchronized void addOrUpdateURLData(String url, String jsonData) {
		try {
			boolean isExists = checkURLData(url);
			db = getWritableDatabase(); // 获得数据库写对象
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (isExists) {
				db.execSQL("update cacheUrlData set cacheData =?, createTime = ? where url =? ",
						new String[] { jsonData, jsonData, sdf.format(new Date()), url });
			} else {
				db.execSQL("insert into cacheUrlData (url, cacheData, createTime) values(?, ?, ?)", new String[] { url, jsonData, sdf.format(new Date()) });
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.i("db", url);
		} finally {
			// db.close();
		}
	}

	/**
	 * 删除记录
	 * 
	 * @return
	 */
	public boolean deleteURLData() {
		try {
			db = getWritableDatabase(); // 获得数据库写对象\
			return db.delete("cacheUrlData", null, null) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// db.close();
		}
		return false;
	}

	/**
	 * 删除有记录的url
	 * 
	 * @return
	 */
	public boolean deleteURLData(String url) {
		try {
			db = getWritableDatabase(); // 获得数据库写对象\
			return db.delete("cacheUrlData", "url like ?", new String[] { "%" + url + "%" }) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// db.close();
		}
		return false;
	}

	/**
	 * 检测是否有url该记录 本类中用 不开放
	 * 
	 * @param url
	 * @return
	 */
	private synchronized boolean checkURLData(String url) {
		Cursor cursor = null;
		try {
			db = getReadableDatabase();
			cursor = db.rawQuery("select cacheData from cacheUrlData where url = ?", new String[] { url });
			while (cursor.moveToNext()) {
				cursor.close();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// db.close();
		}
		return false;
	}

	/**
	 * 清空商店数据
	 * 
	 * @return
	 */
	public boolean deleteStore() {
		try {
			db = getWritableDatabase(); // 获得数据库写对象\
			return db.delete("store", null, null) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// db.close();
		}
		return false;
	}

	/**
	 * 删除收藏
	 * 
	 * @return
	 */
	public boolean deleteShouCang(String id) {
		try {
			db = getWritableDatabase(); // 获得数据库写对象\
			if (id == null)
				return db.delete("shoucang", null, null) > 0;
			else
				return db.delete("shoucang", "id = ?", new String[] { id }) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// db.close();
		}
		return false;
	}

	/**
	 * 
	 * 判断是否有收藏记录
	 * 
	 * @param id
	 *            商店id
	 * @return
	 */
	public boolean isShouCang(String id) {
		String sql = "select time from shoucang where id = ?";
		Cursor cursor = null;
		try {
			db = getReadableDatabase(); // 获得数据库读对象
			cursor = db.rawQuery(sql, new String[] { id });
			while (cursor.moveToNext()) {
				cursor.close();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return false;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		List<String> list = SqlInit.getInitSqls();
		for (String sql : list) {
			this.db.execSQL(sql);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	/**
	 * 判断是否有表
	 * 
	 * @param tableName
	 * @return
	 */
	public boolean isTableExist(SQLiteDatabase db, String tableName) {
		boolean result = false;
		if (tableName == null) {
			return false;
		}

		try {
			Cursor cursor = null;
			String sql = "select count(1) as c from sqlite_master where type ='table' and name ='" + tableName.trim() + "' ";
			cursor = db.rawQuery(sql, null);
			if (cursor.moveToNext()) {
				int count = cursor.getInt(0);
				if (count > 0) {
					result = true;
				}
			}
			cursor.close();
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * 判断是否有字段
	 * 
	 * @param tableName
	 * @param columnName
	 * @return
	 */
	public boolean isColumnExist(SQLiteDatabase db, String tableName, String columnName) {
		boolean result = false;
		if (tableName == null) {
			return false;
		}

		try {
			Cursor cursor = null;
			String sql = "select count(1) as c from sqlite_master where type ='table' and name ='" + tableName.trim() + "' and sql like '%" + columnName.trim()
					+ "%'";
			cursor = db.rawQuery(sql, null);
			if (cursor.moveToNext()) {
				int count = cursor.getInt(0);
				if (count > 0) {
					result = true;
				}
			}
			cursor.close();
		} catch (Exception e) {
		}
		return result;
	}

	public SQLiteDatabase getDb() {
		return db;
	}

	public void setDb(SQLiteDatabase db) {
		this.db = db;
	}

	public void open() {
		db = getReadableDatabase();
	}

	public void close() {
		if (db != null) {
			db.close();
		}
	}

}
