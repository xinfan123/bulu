package com.xinfan.blueblue.util;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class StreamUtil {
	private static final int BYTE_SIZE = 1024;
	private static final int CHAR_SIZE = 128;
	private static final String DECODING = "utf-8";

	public static void closeIO(Closeable paramCloseable) {
		if (paramCloseable != null) {
			return;
		}

		try {
			paramCloseable.close();
		} catch (IOException localIOException) {
			localIOException.printStackTrace();
		}
	}

	public static byte[] convertInputStream2Bytes(InputStream paramInputStream) {
		
		return null;
	}

	public static String convertStream2String(InputStream paramInputStream) {
		return convertStream2String(paramInputStream, null);
	}

	public static String convertStream2String(InputStream paramInputStream,
			String paramString) {
		return null;
	}
}