package pf.com.butterfly.util;

import com.google.gson.Gson;

/**
 * Created by admin on 2017/8/29.
 */

public class MyGson
{
    public static Gson gson=new Gson();

    public static <T> T parseJson(String result,Class<T> c)
    {
        return gson.fromJson(result,c);
    }

    public static String toJson(Object o)
    {
        return gson.toJson(o);
    }
}
