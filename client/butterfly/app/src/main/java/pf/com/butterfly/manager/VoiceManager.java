//package pf.com.butterfly.manager;
//
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.widget.ImageView;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//import pf.com.butterfly.okhttp.IMsgback;
//import pf.com.butterfly.bmob.BmobHttp;
//import pf.com.butterfly.http.HeadHttp;
//import pf.com.butterfly.util.BitmapAndStringUtils;
//
///**
// * 声音管理器
// */
//
//public class VoiceManager
//{
//
//    private static HashMap<String,Bitmap> head_icons;
//
//    private static List<req_load> req_head;
//
//    private static BmobHttp http1;
//
//
//
//
//    public static void init()
//    {
//
//        if(head_icons!=null)
//            return ;
//
//        head_icons=new HashMap<String,Bitmap>();
//        other_icons=new HashMap<String,Bitmap>();
//
//        req_head=new ArrayList<req_load>();
//        req_other=new ArrayList<req_load>();
//
//        http1=new HeadHttp(new IMsgback()
//        {
//            @Override
//            public void onMsgDispose(int err, String result, Object userToken)
//            {
//                OnDisposeHead(err,result,userToken);
//            }
//        });
//
//        http2=new HeadHttp(new IMsgback()
//        {
//            @Override
//            public void onMsgDispose(int err, String result, Object userToken)
//            {
//                OnDisposeHead(err,result,userToken);
//            }
//        });
//
//        http1.setGet();
//        http2.setGet();
//
//    }
//
//    public static void OnDisposeHead(int err, String result, Object userToken)
//    {
//        req_load req=req_head.remove(0);
//
//        if(err!=0)
//        {
//            //下载图片失败...
//            // req.image.setImageResource();//设置默认
//
//        }
//        else
//        {
//            //把字符串转换成图片
//
//            Bitmap bitmap= BitmapAndStringUtils.convertStringToIcon(result);
//
//            req.image.setImageBitmap(bitmap);
//
//            head_icons.put(req.url,bitmap);
//
//        }
//
//        //继续下载头像
//        load1();
//
//
//    }
//
//    public static void OnDisposeOther(int err, String result, Object userToken)
//    {
//        req_load req=req_other.remove(0);
//
//        if(err!=0)
//        {
//            //下载图片失败...
//            // req.image.setImageResource();//设置默认
//
//        }
//        else
//        {
//            //把字符串转换成图片
//            byte[] temp=result.getBytes();
//            Bitmap bitmap= BitmapFactory.decodeByteArray(temp,0,temp.length);
//
//            req.image.setImageBitmap(bitmap);
//
//            other_icons.put(req.url,bitmap);
//
//        }
//
//        //继续下载图片
//        load2();
//    }
//
//    public static void load1()
//    {
//        while(true)
//        {
//            if(req_head.size()==0)
//            {
//                break;
//            }
//
//            req_load req=req_head.get(0);
//
//            if(head_icons.containsKey(req.url)==true)
//            {
//                req.image.setImageBitmap(head_icons.get(req.url));
//                req_head.remove(0);
//            }
//            else
//            {
//                http1.send(req.url,"");
//                break;
//            }
//        }
//    }
//
//    public static void load2()
//    {
//        while(true)
//        {
//            if(req_other.size()==0)
//            {
//                break;
//            }
//
//            req_load req=req_other.get(0);
//
//            if(other_icons.containsKey(req.url)==true)
//            {
//                req.image.setImageBitmap(other_icons.get(req.url));
//                req_other.remove(0);
//            }
//            else
//            {
//                http2.send(req.url,"");
//                break;
//            }
//        }
//    }
//
//
//
//    public static void LoadHead(String url, ImageView image)
//    {
//        req_load req=new req_load();
//        req.url=url;
//        req.image=image;
//
//        req_head.add(req);
//
//        if(req_head.size()==1)
//        {
//            load1();
//        }
//    }
//
//    public static void LoadImage(String url, ImageView image)
//    {
//        req_load req=new req_load();
//        req.url=url;
//        req.image=image;
//
//        req_other.add(req);
//
//        if(req_other.size()==1)
//        {
//            load2();
//        }
//    }
//}
