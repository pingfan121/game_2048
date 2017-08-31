package pf.com.butterfly.util;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;

import pf.com.butterfly.MainActivity;

/**
 * Created by admin on 2017/5/18.
 */

public class PermissionManager
{
    public interface PermissionCallback
    {
        void callback(String permissions,Boolean result);
    }

    private static int req_code=0;

    public static HashMap<Integer,PermissionCallback> permmap =new HashMap<Integer,PermissionCallback>();

    //申请权限
    public static void applyForPermission(String permission,PermissionCallback back)
    {
        //检查是不是申请过了
        if (ContextCompat.checkSelfPermission(MainActivity.main,
                permission)
                != PackageManager.PERMISSION_GRANTED)
        {
            //还没有申请过...
            permmap.put(req_code,back);

            //还没有申请过
            ActivityCompat.requestPermissions(MainActivity.main,
                    new String[]{permission},
                    req_code);

            req_code++;
        }
        else
        {

            back.callback(permission,true);

        }
    }


    //申请权限回调
    public static void onRequestPermissionsResult(int requestCode,String[] permissions, int[] grantResults)
    {
        if(permmap.containsKey(requestCode)==true)
        {
            if (grantResults.length > 0  && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                //同意授权了...
                permmap.get(requestCode).callback(permissions[0],true);
            }
            else
            {
                permmap.get(requestCode).callback(permissions[0],false);
            }
        }

    }

    //检测权限
    public static Boolean checkPermission(String permission)
    {
        //检查是不是申请过了
        if (ContextCompat.checkSelfPermission(MainActivity.main,
                permission)
                != PackageManager.PERMISSION_GRANTED)
        {
            return false;
        }
        else
        {
            return true;
        }
    }



}
