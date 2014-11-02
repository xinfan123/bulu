package com.xinfan.blueblue.dao;

import java.util.ArrayList;
import java.util.List;

public class SqlInit {

	public static List<String> getInitSqls() {

		List<String> list = new ArrayList<String>();
		list.add("DROP TABLE IF EXISTS T_CACHEDATA;");
		list.add("CREATE TABLE T_CACHEDATA( URL VARCHAR(1020) ,  DATA  TEXT ,CREATE_TIME  VARCHAR,  PRIMARY KEY (URL));");

		return list;
	}

	public static List<String> getUpdateSqls() {

		List<String> list = new ArrayList<String>();
		list.add("DROP TABLE IF EXISTS T_CACHEDATA;");
		list.add("CREATE TABLE T_CACHEDATA( URL VARCHAR(1020) ,  DATA  TEXT,CREATE_TIME  VARCHAR,  PRIMARY KEY (URL));");

		return list;
	}

}
