package com.xinfan.blueblue.util;

import android.text.TextUtils;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class BeanUtil
{
  private static final String TARGET_OBJECT = "target object: ";
  private static final String TARGET_OBJECT_CAN_NOT_BE_NULL = "target object can not be null";

  private static boolean equalFields(Object paramObject1, Object paramObject2)
  {
    boolean bool1 = false;
    Field[] arrayOfField1 = paramObject1.getClass().getDeclaredFields();
    Field[] arrayOfField2 = paramObject2.getClass().getDeclaredFields();
    if (arrayOfField1.length != arrayOfField2.length){
    	return bool1;
    }
    
      int i = 0;
      try
      {
        while (true)
        {
          if (i >= arrayOfField1.length)
            break;
          Field localField1 = arrayOfField1[i];
          localField1.setAccessible(true);
          Field localField2 = arrayOfField2[i];
          localField2.setAccessible(true);
          Object localObject1 = localField1.get(paramObject1);
          Object localObject2 = localField2.get(paramObject2);
          if ((localObject1 == null) && (localObject2 != null))
            break;
          if (localObject1 != null)
          {
            boolean bool2 = localObject1.equals(localObject2);
            if (!bool2)
              break;
          }
          i++;
        }
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        localIllegalArgumentException.printStackTrace();
        bool1 = true;
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
          label122: localIllegalAccessException.printStackTrace();
      }
      
      return bool1;
  }

  public static Object getField(String paramString, Object paramObject)
    throws Exception
  {
    if (TextUtils.isEmpty(paramString))
      throw new RuntimeException("field name can not be empty");
    if (paramObject == null)
      throw new RuntimeException("target object can not be null");
    Field localField = paramObject.getClass().getDeclaredField(paramString);
    if (localField == null)
      throw new RuntimeException("target object: " + paramObject.getClass().getName() + " do not have this field: " + paramString);
    localField.setAccessible(true);
    return localField.get(paramObject);
  }

  public static int hashCode(Object paramObject)
  {
    int i = 1;
    Field[] arrayOfField = paramObject.getClass().getDeclaredFields();
    int j = arrayOfField.length;
    for (int k = 0; ; k++)
    {
      int m;
      int i1;
      if (k < j)
      {
        Field localField = arrayOfField[k];
        localField.setAccessible(true);
        try
        {
          Object localObject = localField.get(paramObject);
          m = i * 31;
          if (localObject == null)
          {
            i1 = 0;
          }
          else
          {
            int n = localObject.hashCode();
            i1 = n;
          }
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
          localIllegalArgumentException.printStackTrace();
          continue;
        }
        catch (IllegalAccessException localIllegalAccessException)
        {
          localIllegalAccessException.printStackTrace();
          continue;
        }
      }
      else
      {
        return i;
      }
      i = m + i1;
    }
  }

  public static void invokeMethod(String paramString, Object paramObject, Object[] paramArrayOfObject)
    throws Exception
  {
    if (TextUtils.isEmpty(paramString))
      throw new RuntimeException("method name can not be empty");
    if (paramObject == null)
      throw new RuntimeException("target object can not be null");
    ArrayList localArrayList = new ArrayList();
    int i = paramArrayOfObject.length;
    for (int j = 0; j < i; j++)
      localArrayList.add(paramArrayOfObject[j].getClass());
    Method localMethod = paramObject.getClass().getDeclaredMethod(paramString, (Class[])localArrayList.toArray());
    if (localMethod == null)
      throw new RuntimeException("target object: " + paramObject.getClass().getName() + " do not have this method: " + paramString + " with parameters: " + localArrayList.toString());
    localMethod.setAccessible(true);
    localMethod.invoke(paramObject, paramArrayOfObject);
  }

  public static boolean isEquals(Object paramObject1, Object paramObject2)
  {
    boolean bool = false;
    if (paramObject1 == paramObject2);
    for (bool = true; ; bool = equalFields(paramObject1, paramObject2))
      do
        return bool;
      while ((paramObject1 == null) || (paramObject2 == null) || (paramObject1.getClass() != paramObject2.getClass()));
  }

  public static void setField(String paramString, Object paramObject1, Object paramObject2)
    throws Exception
  {
    if (TextUtils.isEmpty(paramString))
      throw new RuntimeException("field name can not be empty");
    if (paramObject1 == null)
      throw new RuntimeException("target object can not be null");
    Field localField = paramObject1.getClass().getDeclaredField(paramString);
    if (localField == null)
      throw new RuntimeException("target object: " + paramObject1.getClass().getName() + " do not have this field: " + paramString);
    localField.setAccessible(true);
    localField.set(paramObject1, paramObject2);
  }
}