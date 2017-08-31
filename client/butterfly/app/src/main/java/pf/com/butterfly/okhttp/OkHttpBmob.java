package pf.com.butterfly.okhttp;

/**
 * Created by admin on 2017/7/26.
 */

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
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
public class OkHttpBmob extends OkHttpBase
{
    private static OkHttpBmob mInstance;

    private OkHttpBmob()
    {
        super();

        what=OkHttpHandler.what_bmob_msg;
    }

    public static OkHttpBmob getInstance()
    {
        if (mInstance == null)
        {
            synchronized (LOCKER)
            {
                if (mInstance == null)
                {
                    mInstance = new OkHttpBmob();
                }
            }
        }
        return mInstance;
    }


    public String FILE_url="https://api.bmob.cn/2/files/";  //文件上传网址

    public void upBmobAMRFile(String filepath,IMsgback msgback)
    {
        upBmobAMRFile(filepath,msgback,null);
    }
    public void upBmobAMRFile(String filepath,IMsgback msgback,Object userToken)
    {

        okhttp3.Request.Builder RequestBuilder = new okhttp3.Request.Builder();

        String url=FILE_url+MixFun.randName(".amr");

        byte[] data= MixFun.readFileSdcardFile(filepath);

        RequestBuilder.url(url);

        RequestBody body= RequestBody.create(MediaType.parse(MixFun.getContentType(".amr")),data);

        RequestBuilder.post(body);


        RequestBuilder.addHeader("X-Bmob-Application-Id","a5a2688114fb06e9156acaaee76ca9a0");
        RequestBuilder.addHeader("X-Bmob-REST-API-Key","603fc86123b6ad09a9e9c264103fb5a4");
        RequestBuilder.addHeader("Content-Type", MixFun.getContentType(".amr"));

        CallUrl(RequestBuilder,msgback,userToken);


    }


}
