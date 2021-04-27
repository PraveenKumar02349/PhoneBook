package com.angler.phonebook.Utils;

import java.math.BigDecimal;

public class PojoUtils {

    public static Object checkObject(Object result)
    {
        if (result == null || result.equals("null"))
            return "";
        return result;
    }
    
    public static BigDecimal checkResult(BigDecimal result)
    {
        if (result == null)
            return new BigDecimal(0);
        return result;
    }

    public static String checkResult(String result)
    {
        if (result == null || result.equals("null"))
            return "";
        return result;
    }
    
    public static Long checkResultAsLong(Long result)
    {
        if (result == null)
            return (long) 0;
        return result;
    }

    public static Integer checkResultAsString(String result)
    {
        if (result == null)
            return 0;
        if (result.equals(""))
            return 0;
        return Integer.parseInt(result);
    }

}
