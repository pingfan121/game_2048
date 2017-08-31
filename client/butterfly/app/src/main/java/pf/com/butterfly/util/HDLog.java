package pf.com.butterfly.util;

import android.util.Log;
import android.widget.Toast;

import pf.com.butterfly.MainActivity;

/**
 * Created by admin on 2017/3/1.
 */
public class HDLog
{
    public static String logs="";

    public static void error(String str)
    {
        Log.e("错误",str);

        logs+="\n";
        logs+=str;

        if(logs.length()>20000)
        {
            logs=logs.substring(logs.length()-5000);
        }
    }

    public static void info(String str)
    {
        Log.e("信息",str);

        logs+="\n";
        logs+=str;

        if(logs.length()>20000)
        {
            logs=logs.substring(logs.length()-5000);
        }
    }

    public static void error(Exception ex)
    {
         StackTraceElement[] stackTraceElements = ex.getStackTrace();
         String result = ex.toString() + "\n";
         for (int index = stackTraceElements.length - 1; index >= 0; --index) {
                result += "at [" + stackTraceElements[index].getClassName() + ",";
                 result += stackTraceElements[index].getFileName() + ",";
                result += stackTraceElements[index].getMethodName() + ",";
                 result += stackTraceElements[index].getLineNumber() + "]\n";
         }

        Log.e("错误",result);

        logs+="\n";
        logs+=result;

        if(logs.length()>20000)
        {
            logs=logs.substring(logs.length()-5000);
        }
    }

    public static void Toast(String str)
    {
        Toast.makeText(MainActivity.main,str,Toast.LENGTH_SHORT).show();
        HDLog.error(str);
    }
}
