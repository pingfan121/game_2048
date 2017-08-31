package pf.com.butterfly.module;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import pf.com.butterfly.MainActivity;
import pf.com.butterfly.R;
import pf.com.butterfly.okhttp.IMsgback;
import pf.com.butterfly.hander.MsgHandler;
import pf.com.butterfly.util.HDLog;
import pf.com.butterfly.util.MixFun;
import pf.com.butterfly.util.SendPost;

/**
 * Created by admin on 2017/2/25.
 */
//显示图片模块
public class ShowPhoto implements IMsgback
{
    private ImageView img=null;
    private  Button btn_close=null;
    private  Button btn_up=null;
    private  Button btn_show=null;

    private  int WhatId;
    private  String url="https://api.bmob.cn/2/files/";

    private View father;

    private static ShowPhoto _instance=null;

    public static ShowPhoto getInstance()
    {
        if(_instance==null)
        {
            _instance=new ShowPhoto();
        }

        return _instance;
    }

    public void init()
    {
        View view= MainActivity.main.findViewById(R.id.layer1);
        father=view.findViewById(R.id.show_photo);
        img=(ImageView)father.findViewById(R.id.showphoto);
        btn_close=(Button) father.findViewById(R.id.btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img.setImageBitmap(null);
                Hide();
            }
        });
        btn_up=(Button) father.findViewById(R.id.btn_up);
        btn_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
               OnUpFile();
            }
        });
        btn_show=(Button) father.findViewById(R.id.btn_show);
        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                OnShowFile();
            }
        });

        WhatId= MsgHandler.getInstance().getNewWhat();

        MsgHandler.getInstance().addOneDispose(WhatId,this);

    }

    public  void Hide()
    {
        father.setVisibility(View.INVISIBLE);
    }

    private  String tupian_path="";


    public  void ShowPhoto(Bitmap bitmap,String path)
    {
        father.setVisibility(View.VISIBLE);
        img.setImageBitmap(bitmap);
        tupian_path=path;
        HDLog.error("图片地址1:"+path);
    }

    public  void OnUpFile()
    {
        try
        {
            byte[] data= MixFun.readFileSdcardFile(tupian_path);

            HDLog.error("图片地址2:"+tupian_path);

            String type=tupian_path.substring(tupian_path.length()-4,tupian_path.length());
            type=MixFun.getContentType(type);

            if(data!=null)
            {
                SendPost send=new SendPost(url+getupname(tupian_path),WhatId,MixFun.readFileSdcardFile(tupian_path),type);
                send.start();
            }

            //等待一会....这里应该播放上传动画
            //.....
        }
        catch (Exception ex)
        {
            HDLog.error("OnUpFile出现异常");
            HDLog.error(ex);
        }

    }

    //显示..
    public  void OnShowFile()
    {

    }


//
//    //读SD中的文件
//    public byte[] readFileSdcardFile(String fileName)
//    {
//
//        byte[] buffer=null;
//
//        try
//        {
//            FileInputStream fin = new FileInputStream(fileName);
//
//            int length = fin.available();
//
//            buffer = new byte[length];
//            fin.read(buffer);
//            fin.close();
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//        return buffer;
//    }

    public static String getupname(String filename)
    {
        String type=filename.substring(filename.length()-4,filename.length());

        String name= ""+System.currentTimeMillis()+type;

        return name;

    }




    //上传图片返回
    private String img_url;
    public void onMsgDispose(int err,String result,Object userToken)
    {
        String data=result;

        if(data == "" )
        {
            //上传出问题了....
            HDLog.error("上传失败");
        }
        else
        {
            //上传成功了
            //system.out.plant
            HDLog.info("上传成功了呀");

            Toast.makeText(MainActivity.main,"上传成功",Toast.LENGTH_LONG).show();
            img_url=data;
        }
    }



}
