package pf.com.butterfly.hander;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.util.HashMap;

import pf.com.butterfly.okhttp.IMsgback;


/**
 * Created by Administrator on 2016/6/2.
 */
public class MsgHandler extends Handler
{
    private static MsgHandler _instance=null;

    private HashMap<Integer,IMsgback> dises = new HashMap<Integer,IMsgback>();

    public int what=100;

    public static MsgHandler getInstance()
    {
        if(_instance==null)
        {
            _instance=new MsgHandler();
        }

        return _instance;
    }
    public int getNewWhat()
    {
       return what++;
    }


   public synchronized void   addOneDispose(int what,IMsgback dispose)
   {

       if (dises.containsKey(what) == false)
       {
           dises.put(what, dispose);
       }
       else
       {
           //已经添加过处理接口了
           System.out.print("已经添加过处理接口的消息号:" + what);
       }

   }

    public  void removeWhat(int what)
    {
        if (dises.containsKey(what) == true)
        {
            dises.remove(what);
        }
    }

    @Override
    public void handleMessage(Message msg)
    {
        super.handleMessage(msg);

        if(dises.containsKey(msg.what))
        {
            Bundle bundle =msg.getData();

            dises.get(msg.what).onMsgDispose(bundle.getInt("error"),bundle.getString("result"),msg.obj);
        }
        else
        {
            //没有找到消息
            System.out.print("没有找到对应处理接口的消息号:"+msg.what);
        }

    }


}
