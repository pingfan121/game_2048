package pf.com.butterfly.module.bored;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import pf.com.butterfly.R;
import pf.com.butterfly.adapter.AdapterItemData;
import pf.com.butterfly.adapter.AdapterItemControl;
import pf.com.butterfly.manager.ImageManager;

/**
 * Created by admin on 2017/5/17.
 */

public class BoredAudioItemView extends AdapterItemControl
{
    private TextView t_time;//时间
    private TextView tv_name;
    private ImageView iv_face;

    @Override
    public void init(View view)
    {
        tv_name=(TextView)view.findViewById(R.id.tv_name);
        iv_face=(ImageView)view.findViewById(R.id.iv_face);
        t_time=(TextView)view.findViewById(R.id.tv_time);
    }

    @Override
    public void setData(AdapterItemData obj)
    {
        BoredAudioItemData itemdata=(BoredAudioItemData)obj;

        t_time.setText(itemdata.iteminfo.voice_len + "\"");
        tv_name.setText(itemdata.iteminfo.username);

        ImageManager.LoadHead(itemdata.iteminfo.userface,iv_face);


    }
}
