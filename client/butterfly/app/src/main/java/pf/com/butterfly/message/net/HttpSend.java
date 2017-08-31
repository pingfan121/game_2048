//package pf.com.butterfly.message.net;
//
//import android.os.Message;
//import android.util.Log;
//import android.util.Xml;
//
//import com.google.gson.Gson;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.io.PrintWriter;
//import java.io.UnsupportedEncodingException;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLConnection;
//import java.nio.charset.Charset;
//import java.util.concurrent.LinkedBlockingQueue;
//
//import pf.com.butterfly.hander.MsgHandler;
//import pf.com.butterfly.message.CSMsg;
//import pf.com.butterfly.message.MsgBase;
//import pf.com.butterfly.message.MsgMap;
//import pf.com.butterfly.util.HDLog;
//
///**
// * Created by admin on 2017/3/20.
// */
//public class HttpSend extends Thread
//{
//    private String url="";
//    private int what=0;
//    private byte[] data;
//    private String sendtype="POST";
//
//    public HttpSend(String url,int what)
//    {
//        this.url=url;
//        this.what=what;
//    }
//
//    //设置路径
//    public void setUrl(String url)
//    {
//        this.url=url;
//    }
//
//    //发送数据1
//    public void senddata(String str) throws UnsupportedEncodingException {
//        data=str.getBytes("UTF-8");
//        this.start();
//    }
//
//    //发送数据2
//    public void senddata(byte[] datas)
//    {
//        data=datas;
//        this.start();
//    }
//
//    public void sendGet()
//    {
//        sendtype="GET";
//        this.start();
//    }
//
//
//
//    private String sendPost(byte[] datas)
//    {
//
//        PrintWriter out = null;
//        BufferedReader in = null;
//        String result = "";
//        URL realurl = null;
//
//        HttpURLConnection conn = null;
//
//        try{
//            realurl = new URL(url);
//            conn = (HttpURLConnection)realurl.openConnection();
//
//            conn.setDoOutput(true);
//            conn.setRequestMethod("POST");
//
//            PrintWriter pw = new PrintWriter(conn.getOutputStream());
//
//            if(datas!=null && datas.length>0)
//            {
//                pw.print(datas);
//            }
//            pw.flush();
//            pw.close();
//            in = new BufferedReader(
//                    new InputStreamReader(conn.getInputStream()));
//
//            String line;
//            while ((line = in.readLine()) != null)
//            {
//                result += line;
//            }
//        }
//        catch(Exception ex)
//        {
//            return "-100";
//        }
//        return result;
//
//    }
//
//    private String sendGet(byte[] datas)
//    {
//
//        PrintWriter out = null;
//        BufferedReader in = null;
//        String result = "";
//        URL realurl = null;
//
//        HttpURLConnection conn = null;
//
//        try{
//            realurl = new URL(url);
//            conn = (HttpURLConnection)realurl.openConnection();
//
//            conn.setDoOutput(false);
//            conn.setDoInput(true);
//            conn.setUseCaches(false);
//
//            in = new BufferedReader(
//                    new InputStreamReader(conn.getInputStream()));
//
//            String line;
//            while ((line = in.readLine()) != null)
//            {
//                result += line;
//            }
//        }
//        catch(Exception ex)
//        {
//            return "-100";
//        }
//        return result;
//
//    }
//
//    @Override
//    public void run()
//    {
//
//        String temp="";
//        try
//        {
//            if(sendtype.equals("GET"))
//            {
//                temp =sendGet(data);
//            }
//            else
//            {
//                temp =sendPost(data);
//            }
//
//        }
//        catch (Exception ex)
//        {
//
//        }
//
//        Message message=new Message();
//
//        message.what=what;
//        message.obj=temp;
//
//        MsgHandler.getInstance().sendMessage(message);
//    }
//}
