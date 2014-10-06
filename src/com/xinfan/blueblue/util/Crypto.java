package com.xinfan.blueblue.util;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import android.util.Base64;

public class Crypto
{
  private static final String AES = "AES";
  private static final String AES_CBC_PKCS5_PADDING = "AES/CBC/PKCS5Padding";
  private static final String IV = "0102030405060708";
  private static final String MD5 = "MD5";
  private static final String UTF_8 = "utf-8";

  public static String decryptByAes(String paramString1, String paramString2)
    throws Exception
  {
    String str = encryptByMd5(paramString1);
    if (StringUtil.isBlank(str))
      throw new IllegalAccessException("key is empty");
    Cipher localCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    localCipher.init(2, generateSecretKeySpec(str), generateIvParameterSpec());
    return new String(localCipher.doFinal(fromBase64(paramString2)), "utf-8");
  }

  public static String encryptByAes(String paramString1, String paramString2)
    throws Exception
  {
    String str = encryptByMd5(paramString1);
    if (StringUtil.isBlank(str))
      throw new IllegalAccessException("key is empty");
    Cipher localCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    localCipher.init(1, generateSecretKeySpec(str), generateIvParameterSpec());
    return toBase64(localCipher.doFinal(paramString2.getBytes("utf-8")));
  }

  public static String encryptByMd5(String paramString)
  {
    String localObject;
    if (StringUtil.isBlank(paramString)){
      localObject = "";
    }
    else
    {
      try
      {
        String str = HexConverter.toHex(MessageDigest.getInstance("MD5").digest(paramString.getBytes("utf-8")));
        localObject = str;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        localObject = "";
      }
    }
    
    return localObject;
  }

  public static byte[] fromBase64(String paramString)
    throws Exception
  {
    return Base64.decode(paramString.getBytes("utf-8"), 2);
  }

  private static IvParameterSpec generateIvParameterSpec()
    throws Exception
  {
    return new IvParameterSpec("0102030405060708".getBytes("utf-8"));
  }

  private static SecretKeySpec generateSecretKeySpec(String paramString)
    throws Exception
  {
    return new SecretKeySpec(paramString.getBytes("utf-8"), "AES");
  }

  public static String toBase64(byte[] paramArrayOfByte)
    throws Exception
  {
    return new String(Base64.encode(paramArrayOfByte, 2), "utf-8");
  }
}