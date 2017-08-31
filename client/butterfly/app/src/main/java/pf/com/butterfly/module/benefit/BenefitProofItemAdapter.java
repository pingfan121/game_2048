package pf.com.butterfly.module.benefit;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import pf.com.butterfly.R;
import pf.com.butterfly.model.BenefitItemData;

/**
 * Created by Administrator on 2016/6/23.
 */

public class BenefitProofItemAdapter extends BaseAdapter  implements AdapterView.OnItemClickListener
{

    public List<BenefitItemData> datas;

    public BenefitProofItemAdapter() {
        super();

        datas=new ArrayList<BenefitItemData>();

        BenefitItemData data1=new BenefitItemData();
        data1.name="王尼玛";
        data1.addr="北京市武警医院";
        data1.needmoney=100000;
        data1.nowmoney=32435;
        data1.reason="快死了";

        datas.add(data1);

    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return (Object)datas.get(i);
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

            view= inflater.inflate(R.layout.benefit_item,null);

            BenefitHeadItem item=new BenefitHeadItem();

            item.init(view);
            item.setData(datas.get(i));
            view.setTag(item);

        }
        else
        {
            ((BenefitHeadItem) view.getTag()).setData(datas.get(i));
        }
        return view;
    }


    public void AddOneItem(String str)
    {
        datas.add(new BenefitItemData());

        this.notifyDataSetChanged();
    }

    public void SubOneItem(String str)
    {
        if(datas.size()>0)
        {
            datas.remove(datas.size()-1);
            this.notifyDataSetChanged();
        }

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {
        Log.e("测试", "被点击了");

        ((BenefitHeadItem)view.getTag()).SetReason();

        BenefitDetail.getInstance().setItemID(((BenefitHeadItem)view.getTag()).getItemID());
    }




}


