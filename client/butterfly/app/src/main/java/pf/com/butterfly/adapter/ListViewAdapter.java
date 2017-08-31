package pf.com.butterfly.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/5/17.
 */

public class ListViewAdapter extends BaseAdapter
{
    public List<AdapterItemData> datas=new ArrayList<AdapterItemData>();

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return datas.get(i);
    }

    public AdapterItemData getDataItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    private int layoutid;
    private String itemclassname;

    //设置项布局和处理类
    public ListViewAdapter(int layout_id, String item_name)
    {
        layoutid=layout_id;
        itemclassname=item_name;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        if(view==null)
        {
            LayoutInflater inflater= LayoutInflater.from(viewGroup.getContext());

            view= inflater.inflate(layoutid,null);

            AdapterItemControl item=null;
            try
            {
                Class cc=Class.forName(itemclassname);
                item=(AdapterItemControl)cc.newInstance();
            }
            catch (Exception ex)
            {

            }


            item.init(view);
            item.setData(datas.get(i));
            view.setTag(item);

        }
        else
        {
            ((AdapterItemControl) view.getTag()).setData(datas.get(i));
        }
        return view;
    }


    public void addOneItem(AdapterItemData t)
    {
        datas.add(t);

        this.notifyDataSetChanged();
    }


    public void SubOneItem(AdapterItemData t)
    {
        if(datas.size()>0)
        {
            datas.remove(t);
            this.notifyDataSetChanged();
        }
    }
}
