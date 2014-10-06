package com.xinfan.blueblue.util;

import android.os.Parcelable;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

public class FieldUtils
{
  private static  Class<?>[] BASIC_TYPES ;

  static
  {
    Class[] arrayOfClass = new Class[16];
    arrayOfClass[0] = Integer.TYPE;
    arrayOfClass[1] = Integer.class;
    arrayOfClass[2] = Double.class;
    arrayOfClass[3] = Double.TYPE;
    arrayOfClass[4] = Float.class;
    arrayOfClass[5] = Float.TYPE;
    arrayOfClass[6] = Boolean.class;
    arrayOfClass[7] = Boolean.TYPE;
    arrayOfClass[8] = String.class;
    arrayOfClass[9] = Character.TYPE;
    arrayOfClass[10] = Long.class;
    arrayOfClass[11] = Long.TYPE;
    arrayOfClass[12] = Short.TYPE;
    arrayOfClass[13] = Short.class;
    arrayOfClass[14] = Byte.TYPE;
    arrayOfClass[15] = Byte.class;
  }

  public static Class<?> getArrayActualType(Field paramField)
  {
    return getFeildClass(paramField).getComponentType();
  }

  public static Class<?> getFeildClass(Field paramField)
  {
    return paramField.getType();
  }

  public static Class<?> getListActualType(Field paramField)
  {
    return (Class)((java.lang.reflect.ParameterizedType)paramField.getGenericType()).getActualTypeArguments()[0];
  }

  public static boolean isArray(Field paramField)
  {
    if (getFeildClass(paramField).isArray());
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public static boolean isAssignableFrom(Class<?> paramClass1, Class<?> paramClass2)
  {
    if (paramClass2.isAssignableFrom(paramClass1));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public static boolean isAssignableFrom(Field paramField, Class<?> paramClass)
  {
    return isAssignableFrom(getFeildClass(paramField), paramClass);
  }

  public static boolean isBasicType(Class<?> paramClass)
  {
    boolean bool = false;
    Class[] arrayOfClass = BASIC_TYPES;
    int i = arrayOfClass.length;
    for (int j = 0; ; j++)
      if (j < i)
      {
        if (arrayOfClass[j].equals(paramClass))
          bool = true;
      }
      else
        return bool;
  }

  public static boolean isBasicType(Field paramField)
  {
    if ((isBasicType(getFeildClass(paramField))) || (paramField.getType().isPrimitive()));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public static boolean isBooleanType(Class<?> paramClass)
  {
    if ((paramClass == Boolean.TYPE) || (paramClass == Boolean.class));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public static boolean isBooleanType(Field paramField)
  {
    return isBooleanType(getFeildClass(paramField));
  }

  public static boolean isByteType(Class<?> paramClass)
  {
    if ((paramClass == Byte.TYPE) || (paramClass == Byte.class));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public static boolean isByteType(Field paramField)
  {
    return isBasicType(getFeildClass(paramField));
  }

  public static boolean isCharType(Class<?> paramClass)
  {
    if (paramClass == Character.TYPE);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public static boolean isCharType(Field paramField)
  {
    return isCharType(getFeildClass(paramField));
  }

  public static boolean isDoubleType(Class<?> paramClass)
  {
    if ((paramClass == Double.TYPE) || (paramClass == Double.class));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public static boolean isDoubleType(Field paramField)
  {
    return isDoubleType(getFeildClass(paramField));
  }

  public static boolean isFloatType(Class<?> paramClass)
  {
    if ((paramClass == Float.TYPE) || (paramClass == Float.class));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public static boolean isFloatType(Field paramField)
  {
    return isFloatType(getFeildClass(paramField));
  }

  public static boolean isIntType(Class<?> paramClass)
  {
    if ((paramClass == Integer.TYPE) || (paramClass == Integer.class));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public static boolean isIntType(Field paramField)
  {
    return isIntType(getFeildClass(paramField));
  }

  public static boolean isList(Field paramField)
  {
    return List.class.isAssignableFrom(getFeildClass(paramField));
  }

  public static boolean isLongType(Class<?> paramClass)
  {
    if ((paramClass == Long.TYPE) || (paramClass == Long.class));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public static boolean isLongType(Field paramField)
  {
    return isLongType(getFeildClass(paramField));
  }

  public static boolean isParcelable(Class<?> paramClass)
  {
    if (isAssignableFrom(paramClass, Parcelable.class));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public static boolean isParcelable(Field paramField)
  {
    return isParcelable(getFeildClass(paramField));
  }

  public static boolean isSerializable(Class<?> paramClass)
  {
    if (isAssignableFrom(paramClass, Serializable.class));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public static boolean isSerializable(Field paramField)
  {
    return isSerializable(getFeildClass(paramField));
  }

  public static boolean isShortType(Class<?> paramClass)
  {
    if ((paramClass == Short.TYPE) || (paramClass == Short.class));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public static boolean isShortType(Field paramField)
  {
    return isShortType(getFeildClass(paramField));
  }

  public static boolean isStringType(Class<?> paramClass)
  {
    if (paramClass == String.class);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public static boolean isStringType(Field paramField)
  {
    return isStringType(getFeildClass(paramField));
  }
}