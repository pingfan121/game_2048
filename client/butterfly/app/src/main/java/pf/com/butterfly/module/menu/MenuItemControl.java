package pf.com.butterfly.module.menu;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import pf.com.butterfly.MainActivity;
import pf.com.butterfly.R;
import pf.com.butterfly.adapter.AdapterItemControl;
import pf.com.butterfly.adapter.AdapterItemData;
import pf.com.butterfly.module.bored.Recorder;

/**
 * Created by admin on 2017/6/3.
 */

public class MenuItemControl extends AdapterItemControl
{
    ImageView iv;
    TextView tv;

    @Override
    public void init(View view)
    {
        iv=(ImageView) view.findViewById(R.id.icon);
        tv=(TextView) view.findViewById(R.id.text);

        view.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(data!=null)
                {
                    MenuModule.getInstance().ShowMenu(false);

                    MenuItemData menu_data=(MenuItemData)data;

                    if("退出".equals(menu_data.title)==true)
                    {
                        MainActivity.main.finish();
                    }
                    else
                    {
                        menu_data.viewControl.show();
                    }
                }
            }
        });
    }

    @Override
    public void setData(AdapterItemData obj)
    {
        data=obj;

        MenuItemData menu_data=(MenuItemData)obj;

        iv.setImageResource(menu_data.icon);

        tv.setText(menu_data.title);
    }


}
