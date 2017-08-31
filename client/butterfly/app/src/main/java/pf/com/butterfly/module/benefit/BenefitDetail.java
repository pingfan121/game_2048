package pf.com.butterfly.module.benefit;

import android.view.View;
import android.widget.TextView;

import pf.com.butterfly.R;
import pf.com.butterfly.base.AppBaseControl;
import pf.com.butterfly.base.AppBaseFragment;
import pf.com.butterfly.base.AppBaseViewControl;
import pf.com.butterfly.module.title.TitleModule;

/**
 * Created by admin on 2017/3/3.
 */
public class BenefitDetail extends AppBaseViewControl
{
    private static BenefitDetail _instance;

    public static BenefitDetail getInstance()
    {
        if(_instance==null)
        {
            _instance=new BenefitDetail();
        }

        return _instance;
    }

    protected void initValue()
    {
        title="详情";
        layout=R.layout.benefit_detail;
    }


    private TextView tv_show;
    @Override
    public void initControl()
    {

        tv_show=(TextView)view.findViewById(R.id.tv_detail);

        view.findViewById(R.id.btn_true).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnTrue();
            }
        });

        view.findViewById(R.id.btn_juankuan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnJuanKuan();
            }
        });

        view.findViewById(R.id.btn_false).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnFalse();
            }
        });

    }

    //论真按钮事件
    private void OnTrue()
    {
       // Toast.makeText(ModuleManager.main,"点击了论真按钮",Toast.LENGTH_LONG).show();
        BenefitProof.getInstance().show();
    }
    private void OnFalse()
    {
        //Toast.makeText(ModuleManager.main,"点击了论假按钮",Toast.LENGTH_LONG).show();

        BenefitFalsify.getInstance().show();
    }
    private void OnJuanKuan()
    {
       // Toast.makeText(ModuleManager.main,"点击了捐款按钮",Toast.LENGTH_LONG).show();
        BenefitDonation.getInstance().show();
    }

    //设置数据
    public void SetData(String id)
    {
        //去下载详情
        //这里要走网络流

        //第一 显示下载动画

        //等待数据
    }

    //设置数据
    public void  setItemID(String id)
    {
        this.show();
        //去下载详情
        //这里要走网络流

        //第一 显示下载动画

        //等待数据

    }


}
