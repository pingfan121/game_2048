package pf.com.butterfly.module.benefit;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import c.b.BP;
import c.b.PListener;
import pf.com.butterfly.MainActivity;
import pf.com.butterfly.R;
import pf.com.butterfly.base.AppBaseControl;
import pf.com.butterfly.base.AppBaseFragment;
import pf.com.butterfly.base.AppBaseViewControl;
import pf.com.butterfly.module.title.TitleModule;
import pf.com.butterfly.util.MixFun;

/**
 * Created by admin on 2017/3/10.
 * 捐款界面
 */
public class BenefitDonation extends AppBaseViewControl
{
    private static BenefitDonation _instance;

    public static BenefitDonation getInstance()
    {
        if(_instance==null)
        {
            _instance=new BenefitDonation();
        }

        return _instance;
    }

    protected void initValue()
    {
        title="救助";
        layout=R.layout.benefit_donation;
    }

    private TextView tv_show;
    private ListView lv;

    // 此为微信支付插件的官方最新版本号,请在更新时留意更新说明
    int PLUGINVERSION = 7;

    private BenefitDonationPayItemAdapter adapter=new BenefitDonationPayItemAdapter();
    @Override
    public void initControl()
    {
        lv=(ListView)view.findViewById(R.id.lv_pay);

        lv.setAdapter(adapter);

        view.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnBtnOK();
            }
        });

    }

    //确认按钮
    public void OnBtnOK()
    {
        String str = adapter.GetCurrSelect();

        if(str=="支付宝")
        {
            pay(true);
        }
        else
        {
            pay(false);
        }

    }

    /**
     * 调用支
     */
    void pay(boolean flag)
    {
        if (flag==true)
        {
            if (!MixFun.checkPackageInstalled("com.eg.android.AlipayGphone",
                    "https://www.alipay.com")) { // 支付宝支付要求用户已经安装支付宝客户端
                Toast.makeText(MainActivity.main, "请安装支付宝客户端", Toast.LENGTH_SHORT)
                        .show();
                return;
            }
        }
        else
        {
            if (MixFun.checkPackageInstalled("com.tencent.mm", "http://weixin.qq.com"))
            {
                // 需要用微信支付时，要安装微信客户端，然后需要插件
                // 有微信客户端，看看有无微信支付插件
                int pluginVersion = BP.getPluginVersion(MainActivity.main);
                if (pluginVersion < PLUGINVERSION)
                {
                    // 为0说明未安装支付插件,
                    // 否则就是支付插件的版本低于官方最新版
                    Toast.makeText(
                            MainActivity.main,
                            pluginVersion == 0 ? "监测到本机尚未安装支付插件,无法进行支付,请先安装插件(无流量消耗)"
                                    : "监测到本机的支付插件不是最新版,最好进行更新,请先更新插件(无流量消耗)",
                            Toast.LENGTH_SHORT).show();


                    installApk("bp.db");
                    return;
                }
            } else
            {
                // 没有安装微信
                Toast.makeText(MainActivity.main, "请安装微信客户端", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        MixFun.showDialog("正在获取订单...\nSDK版本号:" + BP.getPaySdkVersion());
        final String name = "一条狗";

        try {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            ComponentName cn = new ComponentName("com.bmob.app.sport",
                    "com.bmob.app.sport.wxapi.BmobActivity");
            intent.setComponent(cn);
            MainActivity.main.startActivity(intent);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        BP.pay(name, "狗粮", 0.02, flag, new PListener() {

            // 因为网络等原因,支付结果未知(小概率事件),出于保险起见稍后手动查询
            @Override
            public void unknow() {
                Toast.makeText(MainActivity.main, "支付结果未知,请稍后手动查询", Toast.LENGTH_SHORT)
                        .show();
             //   tv.append(name + "'s pay status is unknow\n\n");
                MixFun.hideDialog();
            }

            // 支付成功,如果金额较大请手动查询确认
            @Override
            public void succeed() {
                Toast.makeText(MainActivity.main, "支付成功!", Toast.LENGTH_SHORT).show();
             //   tv.append(name + "'s pay status is success\n\n");
                MixFun.hideDialog();
            }

            // 无论成功与否,返回订单号
            @Override
            public void orderId(String orderId) {
                // 此处应该保存订单号,比如保存进数据库等,以便以后查询
            //    order.setText(orderId);
            //    tv.append(name + "'s orderid is " + orderId + "\n\n");
               MixFun.showDialog("获取订单成功!请等待跳转到支付页面~");
            }

            // 支付失败,原因可能是用户中断支付操作,也可能是网络原因
            @Override
            public void fail(int code, String reason) {

                // 当code为-2,意味着用户中断了操作
                // code为-3意味着没有安装BmobPlugin插件
                if (code == -3) {
                    Toast.makeText(
                            MainActivity.main,
                            "监测到你尚未安装支付插件,无法进行支付,请先安装插件(已打包在本地,无流量消耗),安装结束后重新支付",
                            Toast.LENGTH_SHORT).show();
//                    installBmobPayPlugin("bp.db");
                    installApk("bp.db");
                } else {
                    Toast.makeText(MainActivity.main, "支付中断!", Toast.LENGTH_SHORT)
                            .show();
                }
         //       tv.append(name + "'s pay status is fail, error code is \n"
         //               + code + " ,reason is " + reason + "\n\n");
               MixFun.hideDialog();
            }
        });
    }


    private static final int REQUESTPERMISSION = 101;

    private void installApk(String s) {
        if (ContextCompat.checkSelfPermission(MainActivity.main, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //申请权限
            ActivityCompat.requestPermissions(MainActivity.main, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUESTPERMISSION);
        } else {
            installBmobPayPlugin(s);
        }
    }

    /**
     * 安装assets里的apk文件
     *
     * @param fileName
     */
    void installBmobPayPlugin(String fileName) {
        try {
            InputStream is = MainActivity.main.getAssets().open(fileName);
            File file = new File(Environment.getExternalStorageDirectory()
                    + File.separator + fileName + ".apk");
            if (file.exists())
                file.delete();
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            byte[] temp = new byte[1024];
            int i = 0;
            while ((i = is.read(temp)) > 0) {
                fos.write(temp, 0, i);
            }
            fos.close();
            is.close();

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(Uri.parse("file://" + file),
                    "application/vnd.android.package-archive");
            MainActivity.main.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
