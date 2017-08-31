package pf.com.butterfly.module.bored;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import pf.com.butterfly.R;
import pf.com.butterfly.adapter.AdapterItemData;
import pf.com.butterfly.adapter.AdapterItemControl;
import pf.com.butterfly.component.ComQiPao_Text;
import pf.com.butterfly.manager.DataManager;
import pf.com.butterfly.manager.ImageManager;

/**
 * Created by admin on 2017/5/20.
 */

public class BoredItemView extends AdapterItemControl
{

    public ImageView iv_icon;
    public TextView tv_name;
    public TextView tv_rownum;
    public TextView tv_text;

    public ComQiPao_Text com_qipao;


    private View view;
    @Override
    public void init(View view)
    {
        this.view=view;

        iv_icon=(ImageView)view.findViewById(R.id.iv_face);

        tv_name=(TextView)view.findViewById(R.id.tv_name);
        tv_rownum=(TextView)view.findViewById(R.id.tv_rownum);

        com_qipao=(ComQiPao_Text)view.findViewById(R.id.com_qipao);

    }

    @Override
    public void setData(AdapterItemData obj)
    {
        data=obj;

        BoredHeadItemData itemdata=(BoredHeadItemData)obj;

        //读取图片
        ImageManager.LoadHead(itemdata.icon,iv_icon);

        tv_name.setText(itemdata.name);
        tv_rownum.setText("跟帖:"+itemdata.num);

        com_qipao.setText(itemdata.text);
        com_qipao.setBackground(false);

    }
}

