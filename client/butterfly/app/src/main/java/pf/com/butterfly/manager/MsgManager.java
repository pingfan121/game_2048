//package pf.com.butterfly.manager;
//
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//
//import com.google.gson.Gson;
//
//import java.net.URLEncoder;
//import java.util.Iterator;
//import java.util.Map;
//
//import pf.com.butterfly.okhttp.IMsgback;
//import pf.com.butterfly.module.info.MsgData;
//import pf.com.butterfly.okhttp.OkHttpBmob;
//import pf.com.butterfly.util.HDLog;
//import pf.com.butterfly.okhttp.OkHttpUtils;
//
///**
// * Created by admin on 2017/7/26.
// */
//
////app和后端通信管理器
//
//public class MsgManager
//{
//    public static String url="http://192.168.0.88:10012/";
//
//    public static Handler handler;
//
//    public static Gson gson=new Gson();
//
//    public static void init()
//    {
//        handler=new Handler()
//        {
//            @Override
//            public void handleMessage(Message msg)
//            {
//                switch (msg.what)
//                {
//                    case 0:
//                    {
//                        try
//                        {
//                            Bundle bundle = msg.getData();
//                            String result = bundle.getString("result");
//                            int err = bundle.getInt("error");
//
//
//                            if (err == 0)
//                            {
//                                MsgData msgdata = gson.fromJson(result, MsgData.class);
//
//                                result = gson.toJson(msgdata.msg);
//
//                                err = msgdata.error;
//                            }
//
//                            IMsgback imsg = (IMsgback) msg.obj;
//
//                            if (err < 0)
//                            {
//                                HDLog.error(result);
//                            } else if (err > 0)
//                            {
//                                HDLog.error("错误码:" + err);
//                            }
//
//                            if (imsg != null)
//                            {
//                                imsg.onMsgDispose(err, result, null);
//                            }
//                        }
//                        catch (Exception ex)
//                        {
//                            HDLog.error(ex);
//
//                            HDLog.Toast("消息处理出现异常请查看日志");
//                        }
//
//                        break;
//                    }
//                }
//
//                super.handleMessage(msg);
//            }
//        };
//
//    }
//
//    //发送消息
//    public static void sendMsg(String path, Map<String,String> params, IMsgback dispose)
//    {
//        if(params != null)
//        {
//            Iterator<String> iterator = params.keySet().iterator();
//            String key = "";
//            while (iterator.hasNext()) {
//                key = iterator.next().toString();
//                String val = params.get(key);
//                val= URLEncoder.encode(val);
//                params.put(key,val);
//            }
//        }
//
//        OkHttpUtils.getInstance().sendAppMsg(url+path,params,dispose,handler);
//    }
//
//    //发送消息
//    public static void sendMsg2(String modulename,String funname, Map<String,String> params, IMsgback dispose)
//    {
//        if(params != null)
//        {
//            Iterator<String> iterator = params.keySet().iterator();
//            String key = "";
//            while (iterator.hasNext()) {
//                key = iterator.next().toString();
//                String val = params.get(key);
//                val=URLEncoder.encode(val);
//                params.put(key,val);
//            }
//        }
//
//        OkHttpUtils.getInstance().sendAppMsg(url+"app/module/"+modulename+"/"+funname,params,dispose,handler);
//    }
//
//    //发送消息
//    public static void sendBmobmsg(String filepath, IMsgback dispose)
//    {
//        OkHttpBmob.getInstance().upBmobAMRFile(filepath,dispose,handler);
//    }
//
//    public static <T> T parseJson(String result,Class<T> c)
//    {
//        return gson.fromJson(result,c);
//    }
//}
