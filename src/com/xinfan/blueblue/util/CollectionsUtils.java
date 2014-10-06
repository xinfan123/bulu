package com.xinfan.blueblue.util;

import java.util.Collection;
import java.util.Map;

public class CollectionsUtils
{
  public static boolean isEmpty(Collection<?> paramCollection)
  {
    if ((paramCollection == null) || (paramCollection.isEmpty()));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public static boolean isEmpty(Map<?, ?> paramMap)
  {
    if ((paramMap == null) || (paramMap.isEmpty()));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public static boolean isNotEmpty(Collection<?> paramCollection)
  {
    if (!isEmpty(paramCollection));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public static boolean isNotEmpty(Map<?, ?> paramMap)
  {
    if (!isEmpty(paramMap));
    for (boolean bool = true; ; bool = false)
      return bool;
  }
}