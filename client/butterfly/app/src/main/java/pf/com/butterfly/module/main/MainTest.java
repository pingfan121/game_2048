package pf.com.butterfly.module.main;

import android.view.View;

import java.util.HashMap;
import java.util.Map;

import pf.com.butterfly.R;
import pf.com.butterfly.base.AppBaseViewControl;
import pf.com.butterfly.module.ControlLayer;
import pf.com.butterfly.module.DebugHead;
import pf.com.butterfly.module.TestEditView;
import pf.com.butterfly.module.TestTextView;
import pf.com.butterfly.module.menu.MenuModule;
import pf.com.butterfly.module.register.RegisterHead;
import pf.com.butterfly.util.PhotoChoose;

/**
 * Created by admin on 2017/2/25.
 */
public class MainTest extends AppBaseViewControl
{
    private static MainTest _instance=null;

    public static MainTest getInstance()
    {
        if(_instance==null)
        {
            _instance=new MainTest();
        }

        return _instance ;
    }

    protected void initValue()
    {
        title="测试";
        layout=R.layout.main_view;

        layer= ControlLayer.main_layers;


    }

    @Override
    public void initControl()
    {

        //注册按钮
        view.findViewById(R.id.btn_zhuce).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RegisterHead.getInstance().show();


            }
        });


        //登录按钮
        view.findViewById(R.id.btn_denglu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              //  LoginHead.getInstance().show();

                LoginApp();
            }
        });

        //打开菜单 按钮
        view.findViewById(R.id.btn_caidan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MenuModule.getInstance().ShowMenu(true);
            }
        });

        //打开图片 按钮
        view.findViewById(R.id.btn_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhotoChoose.doPickPhotoAction();
            }
        });

        //打开图文混排展示 按钮
        view.findViewById(R.id.btn_tuwen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TestTextView.SetView();
            }
        });

        //打开图文编辑 按钮
        view.findViewById(R.id.btn_bianji).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TestEditView.Show();
            }
        });

        //显示日志 按钮
        view.findViewById(R.id.btn_log).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DebugHead.getInstance().show();
            }
        });

        //显示日志 按钮
        view.findViewById(R.id.btn_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainHead.getInstance().show();
            }
        });

    }

    public void LoginApp()
    {
//        //创建网络处理的对象
//        OkHttpClient client = new OkHttpClient.Builder()
//                //设置读取数据的时间
//                .readTimeout(5, TimeUnit.SECONDS)
//                //对象的创建
//                .build();
//
//        FormBody body = new FormBody.Builder()
//                .add("name", "张鸿洋")
//                .add("pass", "123456")
//                .build();
//
//        //创建一个网络请求的对象，如果没有写请求方式，默认的是get
//        //在请求对象里面传入链接的URL地址
//        Request request = new Request.Builder()
//                .url("http://192.168.0.88:10012/app/module/user/login?name=刘胡桃&pass=1234567")
//                .build();
//
//        //call就是我们可以执行的请求类
//        Call call = client.newCall(request);
//        //异步方法，来执行任务的处理，一般都是使用异步方法执行的
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                //失败
//                HDLog.error(Thread.currentThread().getName() + "结果  " + e.toString());
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                //成功
//                //子线程
//                //main thread1
//                HDLog.error(Thread.currentThread().getName() + "结果  " + response.body().string());
//            }
//        });

        Map<String,String> maps=new HashMap<>();

//         maps.put("wx_token","12345");
//
//
//        MsgManager.sendMsg("app/module/user/wx_login",maps,null);

//        maps.put("userid","");
//        maps.put("content","heiheihei");
//        MsgManager.sendMsg("app/module/advice/add",maps,null);

    }

}
