package pf.com.butterfly.bmob;

import com.google.gson.Gson;

import java.net.HttpURLConnection;

import pf.com.butterfly.okhttp.IMsgback;
import pf.com.butterfly.http.HttpBase;
import pf.com.butterfly.util.HDLog;
import pf.com.butterfly.util.MixFun;

/**
 * 使用post 发送字节类型的数据
 */
public class BmobHttp extends HttpBase
{
    private String content_type="application/json";

    public static String YZM_url="https://api.bmob.cn/1/requestSmsCode";  //验证码网址
    public static String FILE_url="https://api.bmob.cn/2/files/";  //文件上传网址

    public BmobHttp(IMsgback handler)
    {
        super(handler);
    }
    public void setContentType(String contenttype)
    {
        content_type=contenttype;
    }

    @Override
    public void SetHttpHeader(HttpURLConnection conn)
    {
        conn.setRequestProperty("X-Bmob-Application-Id","a5a2688114fb06e9156acaaee76ca9a0");
        conn.setRequestProperty("X-Bmob-REST-API-Key","603fc86123b6ad09a9e9c264103fb5a4");
        conn.setRequestProperty("Content-Type",content_type);
    }

    public void sendAudioFile(String path)
    {
        byte[] data= MixFun.readFileSdcardFile(path);

        this.send(FILE_url+MixFun.randName(".amr"),data);

    }

    public void errerDispose(int err,String result)
    {
        if(err==-100)
        {
            //请求出现异常了....
            HDLog.Toast(result);
            return ;
        }

        if(err==-99)
        {
            errinfo res =new Gson().fromJson(result,errinfo.class);

            if(res!=null)
            {
                HDLog.Toast("Bmob错误码:"+res.code+"  error:"+res.error);
                return;
            }
        }
    }


    public class fileback
    {
         public String filename;
         public String url;
         public String cdnname;
    }

    public class errinfo
    {
        public int code;
        public String error;
    }


}
