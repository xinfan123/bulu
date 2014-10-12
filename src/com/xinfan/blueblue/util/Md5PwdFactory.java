package com.xinfan.blueblue.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class Md5PwdFactory {
	public static Map<String,Md5PwdEncoder> md5EncoderMap = new ConcurrentHashMap<String, Md5PwdEncoder>();
	
	private static Object lock = new Object();
	
	public static Md5PwdEncoder getUserMd5PwdEncoder(){
		Md5PwdEncoder encoder = md5EncoderMap.get("userpwdencorder");
		if(encoder == null){
			synchronized(lock){
				 encoder = md5EncoderMap.get("userpwdencorder");
				 if(encoder == null){
					 encoder = new Md5PwdEncoder();
					 encoder.setDefaultSalt("msgbox");
					 md5EncoderMap.put("userpwdencorder", encoder);
				 }
			}
		}
		return encoder;
	}
}
