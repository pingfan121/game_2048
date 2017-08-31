package pf.com.butterfly.util;

import android.os.Message;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import pf.com.butterfly.hander.MsgHandler;

/**
 * 使用post 发送字节类型的数据
 */
public class SendPost extends Thread
{
    private String url="";
    private int what=0;
    private byte[] data;
    private String type;

    public SendPost(String url,int what,byte[] datas,String type)
    {
        this.url=url;
        this.what=what;
        this.data=datas;
        this.type=type;
    }


    public String send(byte[] datas)
    {

        PrintWriter out = null;
        BufferedReader in = null;

        URL realurl = null;

        HttpURLConnection conn = null;

        String result = "";

        try{

            realurl = new URL(url);
            conn = (HttpURLConnection)realurl.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("X-Bmob-Application-Id","a5a2688114fb06e9156acaaee76ca9a0");
            conn.setRequestProperty("X-Bmob-REST-API-Key","603fc86123b6ad09a9e9c264103fb5a4");
            conn.setRequestProperty("Content-Type",type);

            OutputStream temp_out = conn.getOutputStream();
            temp_out.write(datas);
            temp_out.flush();
            temp_out.close();

            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));

            String line;
            while ((line = in.readLine()) != null)
            {
                result += line;
            }

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            HDLog.error("SendPost出现异常");
           // HDLog.error(ex);
        }
        return result;

    }

    @Override
    public void run()
    {

        String temp="";
        try
        {
            temp =send(data);
        }
        catch (Exception ex)
        {

        }

        Message message=new Message();

        message.what=what;
        message.obj=temp;

        MsgHandler.getInstance().sendMessage(message);
    }
}
