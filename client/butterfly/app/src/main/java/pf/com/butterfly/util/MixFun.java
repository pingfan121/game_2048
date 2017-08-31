package pf.com.butterfly.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.io.FileInputStream;
import java.util.Comparator;

import pf.com.butterfly.MainActivity;

/**
 * Created by admin on 2017/3/17.
 */
public class MixFun
{
    /**
     * 检查某包名应用是否已经安装
     *
     * @param packageName 包名
     * @param browserUrl  如果没有应用市场，去官网下载
     * @return
     */
    public static boolean checkPackageInstalled(String packageName, String browserUrl) {
        try {
            // 检查是否有支付宝客户端
            MainActivity.main.getPackageManager().getPackageInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            // 没有安装支付宝，跳转到应用市场
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("market://details?id=" + packageName));
                MainActivity.main.startActivity(intent);
            } catch (Exception ee) {// 连应用市场都没有，用浏览器去支付宝官网下载
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(browserUrl));
                    MainActivity.main.startActivity(intent);
                } catch (Exception eee) {
                    Toast.makeText(MainActivity.main,
                            "您的手机上没有没有应用市场也没有浏览器，我也是醉了，你去想办法安装支付宝/微信吧",
                            Toast.LENGTH_SHORT).show();
                }
            }
        }
        return false;
    }

    private static ProgressDialog dialog;

    public static void showDialog(String message) {
        try {
            if (dialog == null) {
                dialog = new ProgressDialog(MainActivity.main);
                dialog.setCancelable(true);
            }
            dialog.setMessage(message);
            dialog.show();
        } catch (Exception e) {
            // 在其他线程调用dialog会报错
        }
    }

    public static void hideDialog() {
        if (dialog != null && dialog.isShowing())
            try {
                dialog.dismiss();
            } catch (Exception e) {
            }
    }


    //读SD中的文件
    public static byte[] readFileSdcardFile(String fileName)
    {

        byte[] buffer=null;

        try
        {
            FileInputStream fin = new FileInputStream(fileName);

            int length = fin.available();

            buffer = new byte[length];
            fin.read(buffer);
            fin.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return buffer;
    }

    public static String getContentType(String str)
    {
        if(str.equals(".png"))
        {
            return "image/png";
        }
        else if(str.equals(".gif"))
        {
            return "image/gif";
        }
        else if(str.equals(".jpg"))
        {
            return "image/jpeg";
        }
        else if(str.equals(".amr"))
        {
            return "audio/AMR";
        }
        return "";
    }

    public static String randName(String suffix)
    {
        return ""+System.currentTimeMillis()+suffix;
    }



    //关闭键盘

    public static void closeInputMethod() {
        InputMethodManager imm = (InputMethodManager)MainActivity.main.getSystemService(Context.INPUT_METHOD_SERVICE);
        boolean isOpen = imm.isActive();
        if (isOpen)
        {
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    //打开键盘
    public static void openInputMethod() {
        InputMethodManager imm = (InputMethodManager) MainActivity.main.getSystemService(Context.INPUT_METHOD_SERVICE);
        boolean isOpen = imm.isActive();
        if (isOpen==false)
        {
             imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);//没有显示则显示
        }
    }


    /** * 根据手机的分辨率从 dp 的单位 转成为 px(像素) */
    public static int dip2px(float dpValue)
    {
        final float scale = MainActivity.main.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    //是不是调试模式
    public static boolean isDebug() {
        try
        {
            ApplicationInfo info= MainActivity.main.getApplicationInfo();
            return (info.flags& ApplicationInfo.FLAG_DEBUGGABLE)!=0;
        }
        catch (Exception e)
        {

        }
        return false;
    }

}
