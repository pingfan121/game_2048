package pf.com.butterfly.module.update;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;

import com.google.gson.Gson;

import android.os.Handler;
import android.os.Message;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import pf.com.butterfly.MainActivity;
import pf.com.butterfly.http.HttpBase;
import pf.com.butterfly.okhttp.IMsgback;
import pf.com.butterfly.okhttp.OkHttpOther;
import pf.com.butterfly.okhttp.OkHttpUtils;
import pf.com.butterfly.util.HDLog;

/**
 * Created by admin on 2017/3/20.
 */
public class UpdateModule
{

    public static String updateurl ="http://www.pfkj.online/app/UpdateInfo.txt";

   // public static String debug_updateurl ="http://192.168.0.88:8080/UpdateInfo.txt";


    private static UpdateModule _instance=null;

    public static UpdateModule getInstance()
    {
        if(_instance==null)
        {
            _instance=new UpdateModule();
        }

        return _instance ;
    }

    private HttpBase http;

    public static void init()
    {
        String url="";


        url=updateurl;


        OkHttpOther.getInstance().send(url,null,msgback);

    }

    //下载更新文件的返回
    public static IMsgback msgback=new IMsgback()
    {
        @Override
        public void onMsgDispose(int err, String result, Object userToken)
        {
            if(err!=0)
            {
                //判断更新文件出现问题.....
                HDLog.Toast("获取服务器更新信息失败");
                return;
            }

            UpdateInfo info=new Gson().fromJson(result,UpdateInfo.class);

            String currversion=getVersionName();

            if(info.version.equals(currversion))
            {
                //版本号一样...
            }
            else
            {
                //版本号不一样...
                //去下载
                showUpdataDialog(info);
            }
        }
    } ;


    /*
     * 获取当前程序的版本号
     */
    private static String getVersionName()
    {

        try
        {
            //获取packagemanager的实例
            PackageManager packageManager = MainActivity.main.getPackageManager();
            //getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo = packageManager.getPackageInfo(MainActivity.main.getPackageName(), 0);
            return packInfo.versionName;
        }
        catch (Exception ex)
        {
            return "";
        }
    }

    public static File getFileFromServer(String path, ProgressDialog pd) throws Exception
    {
        //如果相等的话表示当前的sdcard挂载在手机上并且是可用的
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            URL url = new URL(path);
            HttpURLConnection conn =  (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            //获取到文件的大小
            pd.setMax(conn.getContentLength());
            InputStream is = conn.getInputStream();
            File file = new File(Environment.getExternalStorageDirectory(), "updata.apk");
            FileOutputStream fos = new FileOutputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] buffer = new byte[1024];
            int len ;
            int total=0;
            while((len =bis.read(buffer))!=-1){
                fos.write(buffer, 0, len);
                total+= len;
                //获取当前下载量
                pd.setProgress(total);
            }
            fos.close();
            bis.close();
            is.close();
            return file;
        }
        else{
            return null;
        }
    }

    private static ProgressDialog pd;    //进度条对话框

    private static Handler handler=new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case 0:
                {
                    int token=(int)msg.obj;
                    switch (token)
                    {
                        case 1:
                        {
//                            Bundle bundle=msg.getData();
//                            int err=bundle.getInt("error");
//                            String result=bundle.getString("result");
//                            OnUpdateInfoBack(err,result);
                            break;
                        }
                    }

                    break;
                }
                case 101:
                {
                    File file=(File)msg.obj;

                    installApk(file);

                    if(pd!=null)
                    {
                        pd.dismiss();
                    }
                    break;
                }
                case 102:
                {
                    HDLog.Toast("下载最新版本失败");
                    if(pd!=null)
                    {
                        pd.dismiss();
                    }
                    break;
                }
            }
        }
    };

    /*
     *
     * 弹出对话框通知用户更新程序
     *
     * 弹出对话框的步骤：
     *  1.创建alertDialog的builder.
     *  2.要给builder设置属性, 对话框的内容,样式,按钮
     *  3.通过builder 创建一个对话框
     *  4.对话框show()出来
     */
    protected static void showUpdataDialog(UpdateInfo info)
    {
        AlertDialog.Builder builer = new AlertDialog.Builder(MainActivity.main) ;
        builer.setTitle("版本升级");
        builer.setMessage(info.explain);

        final String url=info.apkurl;

        //当点确定按钮时从服务器上下载 新的apk 然后安装
        builer.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
               HDLog.info("下载apk,更新");
                downLoadApk(url);
            }
        });
        //当点取消按钮时进行登录
        builer.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
               // LoginMain();
            }
        });
        AlertDialog dialog = builer.create();
        dialog.show();
    }

    /*
     * 从服务器中下载APK
     */
    private static void downLoadApk( final String url)
    {

        pd = new  ProgressDialog(MainActivity.main);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setMessage("正在下载更新");
        pd.show();
        new Thread(){
            @Override
            public void run() {
                try {
                    File file = getFileFromServer(url, pd);
                    sleep(3000);

                    Message msg = new Message();
                    msg.what = 101;
                    msg.obj=file;
                    handler.sendMessage(msg);


                } catch (Exception e)
                {
                    Message msg = new Message();
                    msg.what = 102;
                    handler.sendMessage(msg);
                }
            }}.start();
    }

    //安装apk
    private static  void installApk(File file) {
        Intent intent = new Intent();
        //执行动作
        intent.setAction(Intent.ACTION_VIEW);
        //执行的数据类型
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        MainActivity.main.startActivity(intent);
    }

}

class UpdateInfo
{
    public String version;  //版本号
    public String apkurl;  //下载地址
    public String explain;  //说明
}
