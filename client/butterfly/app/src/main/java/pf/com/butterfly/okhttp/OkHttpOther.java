package pf.com.butterfly.okhttp;

/**
 * Created by admin on 2017/7/26.
 */

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * HTTP通讯结构处理器
 *
 */
public class OkHttpOther extends OkHttpBase
{
    private static OkHttpOther mInstance;

    public OkHttpOther()
    {
        super();
        this.what=OkHttpHandler.what_other_msg;
    }

    public static OkHttpOther getInstance()
    {
        if (mInstance == null)
        {
            synchronized (LOCKER)
            {
                if (mInstance == null)
                {
                    mInstance = new OkHttpOther();
                }
            }
        }
        return mInstance;
    }

    public void send(String url, Map<String, String> params, IMsgback msgback)
    {
        send(url,params,msgback,null);
    }

    public void send(String url, Map<String, String> params, IMsgback msgback,Object userToken)
    {

        okhttp3.Request.Builder RequestBuilder = new okhttp3.Request.Builder();

        String param_str=setUrlParams(params);

        if(param_str.equals(""))
        {
            RequestBuilder.url(url);
        }
        else if(param_str.length()<200)
        {
            RequestBuilder.url(url+"?"+param_str);
        }
        else
        {
            RequestBuilder.url(url);

            RequestBody body= RequestBody.create(MediaType.parse("text/x-markdown; charset=utf-8"),param_str);

            RequestBuilder.post(body);
        }

       // RequestBuilder.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");

        CallUrl(RequestBuilder,msgback,userToken);
    }




}
