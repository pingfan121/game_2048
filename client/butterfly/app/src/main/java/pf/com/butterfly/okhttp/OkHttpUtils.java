package pf.com.butterfly.okhttp;

/**
 * Created by admin on 2017/7/26.
 */

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import pf.com.butterfly.manager.DataManager;
import pf.com.butterfly.util.MixFun;

/**
 * HTTP通讯结构处理器
 *
 */
public class OkHttpUtils extends OkHttpBase
{
    private static OkHttpUtils mInstance;

    public OkHttpUtils()
    {
        super();
        this.what=OkHttpHandler.what_app_msg;
    }

    public static OkHttpUtils getInstance()
    {
        if (mInstance == null)
        {
            synchronized (LOCKER)
            {
                if (mInstance == null)
                {
                    mInstance = new OkHttpUtils();
                }
            }
        }
        return mInstance;
    }

    public String app_debug_msg_url="http://192.168.0.88:10012/app/module/";
    public String app_release_msg_url="http://www.pfkj.online:10012/app/module/";
   // public String app_release_msg_url="http://192.168.0.88:10012/app/module/";

    public void sendAppMsg(String modulename, Map<String, String> params, IMsgback msgback)
    {
        sendAppMsg(modulename,params,msgback,null);
    }

    public void sendAppMsg(String modulename, Map<String, String> params, IMsgback msgback,Object userToken)
    {
        //设置编码
        if(params != null)
        {
            Iterator<String> iterator = params.keySet().iterator();
            String key = "";
            while (iterator.hasNext()) {
                key = iterator.next().toString();
                String val = params.get(key);
                val= URLEncoder.encode(val);
                params.put(key,val);
            }
        }
        else
        {
            params=new HashMap<>();
        }
        params.put("token", DataManager.token);

        okhttp3.Request.Builder RequestBuilder = new okhttp3.Request.Builder();

        String reqUrl="";

        if(MixFun.isDebug()==true)
        {
            reqUrl = app_debug_msg_url+modulename;
        }
        else
        {
            reqUrl = app_release_msg_url+modulename;
        }


       String param_str=setUrlParams(params);

        if(param_str.equals(""))
        {
            RequestBuilder.url(reqUrl);
        }
        else if(param_str.length()<200)
        {
            RequestBuilder.url(reqUrl+"?"+param_str);
        }
        else
        {
            RequestBuilder.url(reqUrl);

            RequestBody body= RequestBody.create(MediaType.parse("text/x-markdown; charset=utf-8"),param_str);

            RequestBuilder.post(body);
        }



        RequestBuilder.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");


        CallUrl(RequestBuilder,msgback,userToken);
    }


}
