package pf.com.butterfly.base;

import android.content.Context;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;

import pf.com.butterfly.R;

/**
 * Created by admin on 2016/8/11.
 */
public class AppBaseActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //初始化数据
        initValue();

        //初始化视图
        initView();

        //加载数据
        loadData();

    }

    protected void initValue()
    {

    }

    protected void initView()
    {

    }
    protected void loadData()
    {

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

}