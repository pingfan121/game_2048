package pf.com.butterfly;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import c.b.BP;
import pf.com.butterfly.manager.DataManager;
import pf.com.butterfly.manager.ImageManager;
import pf.com.butterfly.manager.MediaManager;
import pf.com.butterfly.module.menu.MenuModule;
import pf.com.butterfly.module.rule.RuleHead;
import pf.com.butterfly.module.title.TitleModule;
import pf.com.butterfly.module.update.UpdateModule;
import pf.com.butterfly.okhttp.OkHttpHandler;
import pf.com.butterfly.util.HDLog;
import pf.com.butterfly.util.MixFun;
import pf.com.butterfly.util.PermissionManager;
import pf.com.butterfly.util.PhotoChoose;
import pf.com.butterfly.module.ShowPhoto;
import pf.com.butterfly.module.TestEditView;
import pf.com.butterfly.module.TestTextView;
import pf.com.butterfly.wxapi.WeiXinHead;

public class MainActivity  extends AppCompatActivity
{

    public static MainActivity main;

    public Boolean isdebug=false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        main=this;

        isdebug= MixFun.isDebug();

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        if(DataManager.app_over==false)
        {
            DataManager.app_over=true;

            OkHttpHandler.getInstance();

            //初始化图片管理器
            ImageManager.init();

            //检测版本更新
            UpdateModule.getInstance().init();

            //初始化支付
            BP.init("a5a2688114fb06e9156acaaee76ca9a0");

            //微信注册
            WeiXinHead.registToWX();
        }

        //其他activity切换回来..
        //新页面接收数据
        Bundle bundle = this.getIntent().getExtras();
        //接收name值

        if(bundle!=null)
        {
            String str = bundle.getString("oper");


                if ("weixin_login".equals(str) == true)
                {
                    //是从微信登录返回的  做一些事情....

                    OnWeiXinLoginResult(bundle);

                }

        }



    }


    /**
     * 分割 Dex 支持
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    private void InitModule()
    {
        //初始化测试图文混排界面
        TestTextView.Init();
        //初始化测试图文编辑界面
        TestEditView.Init();
        //初始化展示图片界面
        ShowPhoto.getInstance().init();

    }

    //其他activity返回事件
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        PhotoChoose.onActivityResult( requestCode,  resultCode, data);
        TestEditView.onActivityResult( requestCode,  resultCode, data);

    }


    // 捕获返回键的方法2
    @Override
    public void onBackPressed()
    {
        ControlManager.Rollback();
    }

    //-------------------------------下面是测试---------------------------


    //Activity创建或者从后台重新回到前台时被调用
    @Override
    protected void onStart() {
        super.onStart();
       HDLog.info("onStart called.");
    }

    //Activity从后台重新回到前台时被调用
    @Override
    protected void onRestart() {
        super.onRestart();
        HDLog.info("onRestart called.");

    }

    //测试
    //Activity创建或者从被覆盖、后台重新回到前台时被调用
    @Override
    protected void onResume() {
        super.onResume();
        HDLog.info("onResume called.");

        MediaManager.resume();

        main=this;

        //获取主视图
        View view=this.findViewById(R.id.main_aaa);


        //嘿嘿
        //创建函数在前后台切换的时候回多次调用 所以初始化函数要多多注意 不要重复创建视图

        try
        {

            //初始化mainactivity内部的的各个的视图

            InitModule();

            //初始化菜单
            MenuModule.getInstance().init(this.findViewById(R.id.drawer_layout));


            //初始化标题栏
            TitleModule.getInstance().init(view);


            //初始化图片下载器
            ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(MainActivity.this));

            //初始化支付


            //测试
            ControlManager.resetView();


        }
        catch (Exception ex)
        {
            HDLog.error(ex);
        }
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        MediaManager.release();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        MediaManager.pause();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults)
    {
        PermissionManager.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }




    //微信登录返回结果
    private void OnWeiXinLoginResult(Bundle bundle)
    {
        HDLog.Toast("code = " + WeiXinHead.code);

        if(WeiXinHead.err.equals("OK"))
        {
            HDLog.Toast("登录成功");

            //去请求token...
            WeiXinHead.getAccessToken();
        }
        else  if(WeiXinHead.err.equals("ERR"))
        {
            HDLog.Toast("登录失败");
        }
        else  if(WeiXinHead.err.equals("weizhi"))
        {
            HDLog.Toast("微信登录出现未知错误");
        }
    }



}














