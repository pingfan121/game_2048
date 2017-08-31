package pf.com.butterfly.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tencent.mm.sdk.openapi.BaseReq;
import com.tencent.mm.sdk.openapi.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.SendAuth;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import pf.com.butterfly.MainActivity;
import pf.com.butterfly.R;
import pf.com.butterfly.util.HDLog;

/**
 * Created by admin on 2017/5/26.
 */

public class WXEntryActivity extends Activity implements IWXAPIEventHandler
{
    private static final int RETURN_MSG_TYPE_LOGIN = 1;
    private static final int RETURN_MSG_TYPE_SHARE = 2;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.weixin_activity);

        //如果没回调onResp，八成是这句没有写
        WeiXinHead.wx_api.handleIntent(getIntent(), this);

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                WeiXinHead.code="123456";

                //切换activity
                //新建一个显式意图，第一个参数为当前Activity类对象，第二个参数为你要打开的Activity类
                Intent intent =new Intent(WXEntryActivity.this,MainActivity.class);

                //用Bundle携带数据
                Bundle bundle=new Bundle();
                //传递name参数为tinyphp
                bundle.putString("oper", "weixin_login");
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

    }

    // 微信发送请求到第三方应用时，会回调到该方法
    @Override
    public void onReq(BaseReq req)
    {
        HDLog.error("这里调用了???");
    }

    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    //app发送消息给微信，处理返回消息的回调
    @Override
    public void onResp(BaseResp resp)
    {

        switch (resp.errCode)
        {

            case BaseResp.ErrCode.ERR_AUTH_DENIED:  //用户拒绝授权
            case BaseResp.ErrCode.ERR_USER_CANCEL:  //用户取消
            {
                if (RETURN_MSG_TYPE_SHARE == resp.getType())//是什么功能
                {
                    HDLog.Toast("分享失败");
                }
                else
                {
                    HDLog.Toast("登录失败");

                    WeiXinHead.err="ERR";

                    switchActivity("weixin_login");
                }

                break;
            }
            case BaseResp.ErrCode.ERR_OK:  //正确返回
            {
                switch (resp.getType())
                {
                    case RETURN_MSG_TYPE_LOGIN:   //登录成功

                        WeiXinHead.code=((SendAuth.Resp) resp).token;
                        WeiXinHead.err="OK";

                        switchActivity("weixin_login");

                        break;

                    case RETURN_MSG_TYPE_SHARE: //分享成功
                        HDLog.Toast("微信分享成功");
                        finish();
                        break;
                }
                break;
            }
            default:
            {
                WeiXinHead.err="weizhi";
                switchActivity("weixin_login");
                break;
            }


        }
    }

    private void switchActivity(String str)
    {
        //切换activity

        Intent intent =new Intent(WXEntryActivity.this,MainActivity.class);

        //用Bundle携带数据
        Bundle bundle=new Bundle();
        //传递name参数为tinyphp
        bundle.putString("oper", str);
        intent.putExtras(bundle);

        startActivity(intent);

    }
}
