package pf.com.butterfly.module.set;

import android.view.View;
import android.widget.ListView;

import pf.com.butterfly.R;
import pf.com.butterfly.base.AppBaseViewControl;
import pf.com.butterfly.component.IItemClick;
import pf.com.butterfly.component.ItemSelectAdapter;
import pf.com.butterfly.component.ItemSelectData;
import pf.com.butterfly.model.ItemData;
import pf.com.butterfly.module.DebugHead;

/**
 * Created by admin on 2017/3/3.
 */
public class SetingHead extends AppBaseViewControl
{
    private static SetingHead _instance;

    public static SetingHead getInstance()
    {
        if (_instance == null)
        {
            _instance = new SetingHead();
        }

        return _instance;
    }

    private ItemSelectAdapter adapter;

    protected void initValue()
    {
        layout=R.layout.seting_head;

        title="设置";

        adapter=new ItemSelectAdapter();

        adapter.addData(new ItemSelectData(R.drawable.shezhi,"内网",1,R.drawable.pay_select2,R.drawable.pay_select1));
        adapter.addData(new ItemSelectData(R.drawable.shezhi,"外网",0,R.drawable.pay_select2,R.drawable.pay_select1));

        adapter.setItemClick(new IItemClick()
        {
            @Override
            public void onItemClick(View view, ItemData data)
            {
                //设置网络.....
                if(data.text.equals("内网"))
                {
                //    NetManager.SetNet(false);
                }
                else
                {
              //      NetManager.SetNet(true);
                }

                //遍历
                for( int i=0;i<adapter.getCount();i++)
                {
                    ItemSelectData itemdata=(ItemSelectData)adapter.getItem(i);

                    itemdata.select=0;
                }

                ((ItemSelectData)data).select=1;

                adapter.notifyDataSetChanged();
            }
        });
    }

    protected void initControl()
    {

        ListView lv=(ListView)view.findViewById(R.id.lv_net);

        lv.setAdapter(adapter);

        view.findViewById(R.id.btn_debug).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                DebugHead.getInstance().show();
            }
        });

    }
}