package com.xinfan.blueblue.util;

public class HexConverter {
	private static final int BIT_SIZE_FOR_BYTE = 8;
	private static final int HEXADECIMAL = 16;

	private static void appendHex(StringBuffer paramStringBuffer, byte paramByte) {
		paramStringBuffer.append(Integer.toHexString(0xF & paramByte >> 4)).append(Integer.toHexString(paramByte & 0xF));
	}

	public static String fromHex(String paramString) {
		return new String(toByte(paramString));
	}

	public static byte[] toByte(String paramString) {
		int i = paramString.length() / 2;
		byte[] arrayOfByte = new byte[i];
		for (int j = 0; j < i; j++)
			arrayOfByte[j] = Integer.valueOf(paramString.substring(j * 2, 2 + j * 2), 16).byteValue();
		return arrayOfByte;
	}

	public static String toHex(String paramString) throws Exception {
		return toHex(paramString.getBytes("UTF-8"));
	}

	public static String toHex(byte[] paramArrayOfByte) {
		if (paramArrayOfByte == null) {
			return "";
		}

		StringBuffer localStringBuffer = new StringBuffer(2 * paramArrayOfByte.length);
		for (int i = 0; i < paramArrayOfByte.length; i++)
			appendHex(localStringBuffer, paramArrayOfByte[i]);
		return localStringBuffer.toString();
	}
}