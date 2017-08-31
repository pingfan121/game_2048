package pf.com.butterfly.component;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pf.com.butterfly.R;
import pf.com.butterfly.model.ItemData;
import pf.com.butterfly.module.ViewClick;

/**
 * Created by Administrator on 2016/6/23.
 */


public class ItemAdapter extends BaseAdapter
{

    private List<ItemData> itemdatas;

    private int layout;

    private IItemClick click;

    public ItemAdapter() {
        super();

        layout=R.layout.itemlayout;

    }


    public void setItemClick(IItemClick click)
    {
        this.click=click;
    }

    //添加项数据

    public void addData(ItemData data)
    {
        if(itemdatas == null)
        {
            itemdatas=new ArrayList<ItemData>();
        }
        itemdatas.add(data);
    }


    public void addData(List<ItemData> data)
    {
        itemdatas=data;
    }

    //设置布局资源id
    public void setLayout(int res)
    {
        layout=res;
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

            view= inflater.inflate(layout,null);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClick(view);
                }
            });

            view.setOnTouchListener(ViewClick.touchLis);

        }

        ItemData data=itemdatas.get(i);
        view.setTag(data);


        ResetView(view,data);


        return view;
    }

    private void onItemClick(View view)
    {
        onItemClick(view, (ItemData)view.getTag());

        this.notifyDataSetChanged();

    }

    //可在基类重写
    public void ResetView(View view,ItemData data)
    {
        ((ImageView)view.findViewById(R.id.icon)).setImageResource(data.icon);
        ((TextView)view.findViewById(R.id.text)).setText(data.text);
    }

    //可在子类重写函数
    public void onItemClick(View view ,ItemData data)
    {
          if(click!=null)
          {
              click.onItemClick(view,data);
          }
    }

}




