package pf.com.butterfly.module.benefit;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pf.com.butterfly.R;

/**
 * Created by Administrator on 2016/6/23.
 */

public class BenefitDonationPayItemAdapter extends BaseAdapter
{

    private List<BenefitDonationPayItemData> itemdatas=new ArrayList<BenefitDonationPayItemData>();

    public BenefitDonationPayItemAdapter() {
        super();

        initItemData();

    }


    private void initItemData()
    {

        //菜单列数据1
        BenefitDonationPayItemData data=new BenefitDonationPayItemData();
        data.init(R.drawable.pay_icon_wechat,"微信",1);
        itemdatas.add(data);

        data=new BenefitDonationPayItemData();
        data.init(R.drawable.pay_icon_alipay,"支付宝",0);
        itemdatas.add(data);
    }

    @Override
    public int getCount() {
        return itemdatas.size();
    }

    @Override
    public Object getItem(int i) {
        return (Object)itemdatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        if(view==null)
        {
            LayoutInflater inflater= LayoutInflater.from(viewGroup.getContext());

            view= inflater.inflate(R.layout.benefit_donation_pay_item,null);

        }

        BenefitDonationPayItemData data=itemdatas.get(i);
        ((ImageView)view.findViewById(R.id.icon)).setImageResource(data.icon);
        ((TextView)view.findViewById(R.id.t_name)).setText(data.title);

        if(data.select==0)
        {
            ((ImageView)view.findViewById(R.id.select)).setImageResource(R.drawable.pay_select1);
        }
        else
        {
            ((ImageView)view.findViewById(R.id.select)).setImageResource(R.drawable.pay_select2);
        }


        view.setTag(data);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick(view);
            }
        });

        return view;
    }


    public void onItemClick(View view)
    {
        Log.e("菜单被点击了", "被点击了");
        BenefitDonationPayItemData data= (BenefitDonationPayItemData)view.getTag();

        for( BenefitDonationPayItemData item :itemdatas)
        {
            item.select=0;
        }

        data.select=1;

        this.notifyDataSetChanged();

    }

    public String GetCurrSelect()
    {
        for( BenefitDonationPayItemData item :itemdatas)
        {
           if(item.select==1)
           {
               return item.title;
           }
        }
        return "";

    }




}


