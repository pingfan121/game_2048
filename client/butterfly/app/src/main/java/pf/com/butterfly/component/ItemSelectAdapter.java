package pf.com.butterfly.component;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import pf.com.butterfly.R;
import pf.com.butterfly.model.ItemData;

/**
 * Created by admin on 2017/4/4.
 */
public class ItemSelectAdapter extends ItemAdapter
{
    public ItemSelectAdapter()
    {
        super();
        this.setLayout(R.layout.itemselectlayout);
    }
    //可在基类重写
    public void ResetView(View view, ItemData data)
    {
        ItemSelectData data2=(ItemSelectData)data;

        ((ImageView)view.findViewById(R.id.icon)).setImageResource(data.icon);
        ((TextView)view.findViewById(R.id.text)).setText(data.text);

        if(data2.select==0)
        {
            ((ImageView)view.findViewById(R.id.select)).setImageResource(data2.select_no_icon);
        }
        else
        {
            ((ImageView)view.findViewById(R.id.select)).setImageResource(data2.select_icon);
        }
    }
}
