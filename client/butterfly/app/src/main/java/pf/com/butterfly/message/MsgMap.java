package pf.com.butterfly.message;

import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/6/6.
 */
public class MsgMap
{
    public static HashMap<Integer,Class> msgmap =new HashMap<Integer,Class>();

    public static HashMap<Integer,IMsgCallback> reslutmap =new HashMap<Integer,IMsgCallback>();

    public static void init()
    {

    }

    public static Class GetMsgClass(int CodeId)
    {
        if(reslutmap.containsKey(CodeId))
        {
           return msgmap.get(CodeId);
        }
        else
        {
            //错误协议号
        }
        return null;
    }

    public static IMsgCallback GetResultClass(int CodeId)
    {
        if(reslutmap.containsKey(CodeId))
        {
            return reslutmap.get(CodeId);
        }
        else
        {
            //错误协议号
        }
        return null;
    }



}
