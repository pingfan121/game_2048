package pf.com.butterfly.wxapi;

import com.google.gson.Gson;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.SendAuth;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import java.util.HashMap;
import java.util.Map;

import pf.com.butterfly.MainActivity;
import pf.com.butterfly.infofile.res_user_wx_login;
import pf.com.butterfly.module.menu.MenuModule;
import pf.com.butterfly.okhttp.IMsgback;
import pf.com.butterfly.http.HttpBase;
import pf.com.butterfly.manager.DataManager;
import pf.com.butterfly.message.Protocols.login_weixin_req;
import pf.com.butterfly.okhttp.OkHttpOther;
import pf.com.butterfly.okhttp.OkHttpUtils;
import pf.com.butterfly.module.user.UserHead;
import pf.com.butterfly.util.HDLog;
import pf.com.butterfly.util.MyGson;

/**
 * Created by admin on 2017/5/26.
 */

public class WeiXinHead
{
    private static String wx_app_id="wx075681f7f4975e1a";
    private static String wx_app_secret="b98554750c5d3747909f56cb11f9aac1";

    public static IWXAPI wx_api;




    public static String code="";
    public static String err="";


    public static HttpBase http;

    //注册app
    public static void registToWX() {
        //AppConst.WEIXIN.APP_ID是指你应用在微信开放平台上的AppID，记得替换。
        wx_api = WXAPIFactory.createWXAPI(MainActivity.main, wx_app_id, false);
        // 将该app注册到微信
        wx_api.registerApp(wx_app_id);
    }

    //授权请求....
    public static void wxLogin()
    {
        if (!wx_api.isWXAppInstalled())
        {
            HDLog.Toast("您还未安装微信客户端");
            return;
        }

        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "diandi_wx_login";
        wx_api.sendReq(req);
    }

    //获取token
    public static void getAccessToken(){

        HDLog.Toast("获取token....");

        Map<String,String> params=new HashMap<>();
        params.put("appid",wx_app_id);
        params.put("secret",wx_app_secret);
        params.put("code",code);
        params.put("grant_type","authorization_code");

        OkHttpOther.getInstance().send("https://api.weixin.qq.com/sns/oauth2/access_token",params,msgback);

    }

    private static IMsgback msgback=new IMsgback()
    {
        @Override
        public void onMsgDispose(int err, String result, Object userToken)
        {
            if(err!=0)
            {
                HDLog.Toast("微信登录出错:"+result);
                return ;
            }

            wx_result res =new Gson().fromJson(result,wx_result.class);

            login_weixin_req req=new login_weixin_req();

            req.acc_token=res.access_token;

            Map<String,String> params=new HashMap<>();
            params.put("wx_token",res.access_token);

            OkHttpUtils.getInstance().sendAppMsg("user/wx_login",params,msg_result);
        }
    };


    class wx_result
    {
        public String access_token;
        public String expires_in;
        public String refresh_token;
        public String openid;
        public String scope;
        public String unionid;
    }

    private static IMsgback msg_result=new IMsgback()
    {
        @Override
        public void onMsgDispose(int err,String result,Object userToken)
        {
            if(err!=0)
            {
                HDLog.Toast("微信登录失败");
                return ;
            }

            res_user_wx_login res = MyGson.parseJson(result, res_user_wx_login.class);

            //注册成功
            HDLog.Toast("微信登录成功");

            DataManager.login_flag=true;

            DataManager.userinfo.name=res.name;
            DataManager.userinfo.sex=res.sex;
            DataManager.userinfo.face=res.face;

            DataManager.token=res.token;

            //更新个人信息
            UserHead.getInstance().updateInfo();

            //菜单头像
            MenuModule.getInstance().updateFace();
        }
    };

}
