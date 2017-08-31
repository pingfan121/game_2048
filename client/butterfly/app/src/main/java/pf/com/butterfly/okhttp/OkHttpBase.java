package pf.com.butterfly.okhttp;

/**
 * Created by admin on 2017/7/26.
 */

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import pf.com.butterfly.util.MixFun;

/**
 * HTTP通讯结构处理器
 *
 */
public class OkHttpBase
{
    protected OkHttpClient mOkHttpClient;

    protected static final byte[] LOCKER = new byte[0];

    protected int what=0;

    public OkHttpBase()
    {
        okhttp3.OkHttpClient.Builder ClientBuilder = new okhttp3.OkHttpClient.Builder();
        ClientBuilder.readTimeout(30, TimeUnit.SECONDS);//读取超时
        ClientBuilder.connectTimeout(10, TimeUnit.SECONDS);//连接超时
        ClientBuilder.writeTimeout(60, TimeUnit.SECONDS);//写入超时
        mOkHttpClient = ClientBuilder.build();

    }

    /**
     * 设置请求头
     * @param headersParams
     * @return
     */
    private Headers SetHeaders(Map<String, String> headersParams)
    {
        Headers headers=null;
        okhttp3.Headers.Builder headersbuilder=new okhttp3.Headers.Builder();

        if(headersParams != null)
        {
            Iterator<String> iterator = headersParams.keySet().iterator();
            String key = "";
            while (iterator.hasNext()) {
                key = iterator.next().toString();
                headersbuilder.add(key, headersParams.get(key));
            }
        }
        headers=headersbuilder.build();

        return headers;
    }

    /**
     * get方法连接拼加参数
     * @param mapParams
     * @return
     */
    protected String setUrlParams( Map<String, String> mapParams){
        String strParams = "";
        if(mapParams != null)
        {
            Iterator<String> iterator = mapParams.keySet().iterator();
            String key = "";
            while (iterator.hasNext()) {
                key = iterator.next().toString();
                strParams += "&"+ key + "=" + mapParams.get(key);
            }
        }

        if(strParams.length()>0)
        {
            strParams = strParams.substring(1);
        }

        return strParams;
    }

    protected void CallUrl(okhttp3.Request.Builder RequestBuilder,IMsgback msgback,Object userToken)
    {
       Map<String,Object> map=new HashMap<>();

        map.put("back",msgback);
        map.put("usertoken",userToken);

        RequestBuilder.tag(map);//添加请求标签
        Request request = RequestBuilder.build();

        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback()
        {
            @Override
            public void onFailure(Call arg0, IOException arg1)
            {
                httpback(-1,"通讯错误404",arg0.request().tag());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException
            {

                if (response.code() == 200)
                {
                    try
                    {
                        String Result = response.body().string();

                        httpback(0,Result,call.request().tag());

                    }
                    catch (Exception e)
                    {
                        httpback(-3,"数据异常-"+response.code(),call.request().tag());
                    }
                }
                else
                {
                    httpback(-2,"通讯异常-"+response.code(),call.request().tag());
                }
            }
        });
    }

    private void httpback(int error, String result, Object data)
    {
        Message msg=new Message();
        Bundle bundle=new Bundle();
        bundle.putInt("error",error);
        bundle.putString("result",result);
        msg.setData(bundle);
        msg.obj=data;
        msg.what=what;

        OkHttpHandler.getInstance().sendMessage(msg);
    }


}
