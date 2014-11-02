package com.xinfan.blueblue.dao;

import java.util.List;

import com.xinfan.blueblue.util.LogUtil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 
 * 本地数据库
 * 
 * @author Administrator
 * 
 */
public class DBHelper extends SQLiteOpenHelper {
	SQLiteDatabase db = null;
	private static DBHelper mInstance = null;
	private static final String DBNAME = "bulu.db";
	private static final int VERSION = 1;

	private DBHelper(Context context, CursorFactory factory) {
		super(context, DBNAME, factory, VERSION);
	}

	public static DBHelper getInstance() {
		return mInstance;
	}

	public static void init(Context context) {
		if (mInstance == null) {
			mInstance = new DBHelper(context, null);
			mInstance.open();
		}
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		List<String> list = SqlInit.getInitSqls();
		for (String sql : list) {
			database.execSQL(sql);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		List<String> list = SqlInit.getUpdateSqls();
		for (String sql : list) {
			database.execSQL(sql);
		}
	}

	public SQLiteDatabase getDb() {
		return db;
	}

	public void open() {
		try {
			this.db = this.getWritableDatabase();
		} catch (Exception e) {
			LogUtil.e(e.getMessage(), e);
		}
	}

	public void close() {
		if (db != null) {
			db.close();
		}
	}

}
