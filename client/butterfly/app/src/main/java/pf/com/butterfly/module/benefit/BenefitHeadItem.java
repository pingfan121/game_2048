package pf.com.butterfly.module.benefit;

import android.view.View;
import android.widget.TextView;

import pf.com.butterfly.R;
import pf.com.butterfly.component.BenefitItemBar;
import pf.com.butterfly.model.BenefitItemData;

/**
 * Created by admin on 2017/3/9.
 */
public class BenefitHeadItem
{
    private TextView t_name;//名字
    private TextView t_addr; //地址
    private TextView t_reason;//原因
    private TextView t_needmoney;//需要的金额
    private TextView t_nowmoney; //现在有的金额
    private BenefitItemBar bar;

    private BenefitItemData data;

    public void setData(BenefitItemData bidata)
    {
        data=bidata;

        t_name.setText("救助对象: "+data.name);
        t_addr.setText("救助地址: "+data.addr);
        t_reason.setText("救助原因: "+data.reason);
        t_needmoney.setText("需要金额: "+data.needmoney+"");
        t_nowmoney.setText("已筹金额: "+data.nowmoney+"");
        bar.setProgress(90);

    }
    public void init(View view)
    {
        t_name=(TextView)view.findViewById(R.id.t_name);
        t_addr=(TextView)view.findViewById(R.id.t_addr);
        t_reason=(TextView)view.findViewById(R.id.t_reason);
        t_needmoney=(TextView)view.findViewById(R.id.t_needmoney);
        t_nowmoney=(TextView)view.findViewById(R.id.t_nowmoney);
        bar=(BenefitItemBar)view.findViewById(R.id.bar_1);

    }

    public void SetReason()
    {
        t_reason.setText("被点击了 哈哈哈");
    }


    public String getItemID()
    {
        return data.itemid;
    }
}
