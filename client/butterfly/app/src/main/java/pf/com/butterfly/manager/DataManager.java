package pf.com.butterfly.manager;

import pf.com.butterfly.MainActivity;
import pf.com.butterfly.model.UserInfo;
import pf.com.butterfly.module.login.LoginHead;
import pf.com.butterfly.util.HDLog;

/**
 * Created by admin on 2017/7/27.
 */

public class DataManager
{
    public static Boolean app_over=false;
    //登录标志
    public static Boolean login_flag=false;

    public static String token="";
    //用户信息
    public static UserInfo userinfo=new UserInfo();





    public static boolean checkLogin()
    {
        if(MainActivity.main.isdebug==false)
        {
            if (DataManager.login_flag == false)
            {
                LoginHead.getInstance().show();
                HDLog.Toast("请您先登录");
                return false;
            }
        }
        return true;
    }
}
