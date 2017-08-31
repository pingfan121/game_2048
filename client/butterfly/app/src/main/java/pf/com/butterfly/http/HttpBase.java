package pf.com.butterfly.http;

import android.os.Bundle;
import android.os.Message;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import pf.com.butterfly.okhttp.IMsgback;
import pf.com.butterfly.hander.MsgHandler;
import pf.com.butterfly.util.HDLog;

/**
 * Created by admin on 2017/4/2.
 */
public class HttpBase extends Thread
{
    private String url="";
    private int what=0;

    private byte[] data;

    private String mode="POST";


    public HttpBase(IMsgback handler)
    {
        this.what=MsgHandler.getInstance().getNewWhat();
        MsgHandler.getInstance().addOneDispose(what,handler);
    }

    public void setGet()
    {
        mode="GET";
    }


    public void send(String url,String data)
    {
        try
        {
            send(url,data.getBytes("UTF-8"),null);
        }
        catch (Exception ex)
        {

        }
    }

    public void send(String url,String data,Object userToken)
    {
        try
        {
            send(url,data.getBytes("UTF-8"),userToken);

        }
        catch (Exception ex)
        {

        }
    }

    public void send(String url,byte[] data)
    {
        send(url,data,null);
    }

    public void send(String url,byte[] data,Object userToken)
    {
        try
        {

            if(mode.equals("POST"))
            {
                SendThread thread=new SendThread(this,what,url,data,userToken);
                thread.start();
            }
            else
            {
                GetThread thread=new GetThread(this,what,url,data,userToken);
                thread.start();
            }

        }
        catch (Exception ex)
        {

        }
    }




    public void SetHttpHeader(HttpURLConnection conn)
    {

    }

    public String DisposeException(Exception ex)
    {
        return ex.getMessage();
    }
}

class SendThread extends Thread
{
    private HttpBase http;

    private String url="";
    private int what=0;

    private byte[] data;
    private Object userToken;


    public SendThread(HttpBase http,int what,String url,byte[] data,Object userToken)
    {
        this.http=http;
        this.what=what;
        this.url=url;
        this.data=data;
        this.userToken=userToken;
    }

    @Override
    public void run()
    {

        Message message=new Message();

        message.what=what;

        String temp="";

        HttpURLConnection conn = null;

        PrintWriter out = null;
        BufferedReader in = null;

        URL realurl = null;

        String result = "";

        Bundle bundle=new Bundle();


        try
        {
            realurl = new URL(url);
            conn = (HttpURLConnection)realurl.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            http.SetHttpHeader(conn);

            OutputStream temp_out = conn.getOutputStream();
            temp_out.write(data);
            temp_out.flush();
            temp_out.close();

            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));

            String line;
            while ((line = in.readLine()) != null)
            {
                result += line;
            }

          //  message.obj=result;

            bundle.putString("result",result);
            bundle.putInt("error",0);
        }
        catch (Exception ex)
        {
            HDLog.error("SendPost出现异常");

            try
            {
                in = new BufferedReader(
                        new InputStreamReader(conn.getErrorStream()));

                String line;
                while ((line = in.readLine()) != null)
                {
                    result += line;
                }

                bundle.putString("result",result);
                bundle.putInt("error",-99);
              //  message.obj=result;
              //  message.arg2=-99;
            }
            catch (Exception ex2)
            {
              //  message.obj= ex.getMessage();
              //  message.arg2=-100;

                bundle.putString("result",ex.getMessage());
                bundle.putInt("error",-100);

            }



        }
        message.obj=userToken;
        message.setData(bundle);

        MsgHandler.getInstance().sendMessage(message);
    }
}

class GetThread extends Thread
{
    private HttpBase http;

    private String url="";
    private int what=0;

    private byte[] data;
    private Object userToken;


    public GetThread(HttpBase http,int what,String url,byte[] data,Object userToken)
    {
        this.http=http;
        this.what=what;
        this.url=url;
        this.data=data;
        this.userToken=userToken;
    }

    @Override
    public void run()
    {

        Message message=new Message();

        message.what=what;

        String temp="";

        HttpURLConnection conn = null;

        PrintWriter out = null;
        BufferedReader in = null;

        URL realurl = null;

        String result = "";

        Bundle bundle=new Bundle();

        try
        {
            realurl = new URL(url);
            conn = (HttpURLConnection)realurl.openConnection();

            conn.setDoOutput(false);
            conn.setDoInput(true);
            conn.setUseCaches(false);

            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));

            String line;
            while ((line = in.readLine()) != null)
            {
                result += line;
            }

            bundle.putString("result",result);
            bundle.putInt("error",0);


        }
        catch (Exception ex)
        {
            HDLog.error("SendPost出现异常");

            try
            {
                in = new BufferedReader(
                        new InputStreamReader(conn.getErrorStream()));

                String line;
                while ((line = in.readLine()) != null)
                {
                    result += line;
                }

                bundle.putString("result",result);
                bundle.putInt("error",-99);
            }
            catch (Exception ex2)
            {
                bundle.putString("result",ex.getMessage());
                bundle.putInt("error",-100);

            }



        }
        message.obj=userToken;
        message.setData(bundle);

        MsgHandler.getInstance().sendMessage(message);
    }
}
