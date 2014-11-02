package com.xinfan.blueblue.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SqlUtils {

	public static void closeDb(SQLiteDatabase db) {
		if (db != null) {
			//db.close();
		}
	}

	public static void closeCursor(Cursor cursor) {
		if (cursor != null) {
			cursor.close();
		}
	}
}
