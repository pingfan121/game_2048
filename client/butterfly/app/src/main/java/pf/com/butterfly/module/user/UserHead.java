package pf.com.butterfly.module.user;

import android.widget.ImageView;
import android.widget.TextView;

import pf.com.butterfly.R;
import pf.com.butterfly.base.AppBaseViewControl;
import pf.com.butterfly.manager.DataManager;
import pf.com.butterfly.manager.ImageManager;

/**
 * Created by admin on 2017/4/8.
 */
public class UserHead extends AppBaseViewControl
{

    private static UserHead _instance;

    public static UserHead getInstance()
    {
        if(_instance==null)
        {
            _instance=new UserHead();
        }

        return _instance;
    }

    @Override
    protected void initValue()
    {
        super.initValue();

        title="用户";

        layout= R.layout.user_head;
    }

    private ImageView iv_face;
    private TextView tv_name;

    @Override
    protected void initControl()
    {
        iv_face=(ImageView)view.findViewById(R.id.riv_face);
        tv_name=(TextView)view.findViewById(R.id.tv_name);

        updateInfo();

    }

    @Override
    public void reShow()
    {
        //加载头像

    }

    public void updateInfo()
    {

        if(DataManager.login_flag==false)
        {
            if(iv_face!=null)
            {
                //设置默认图片
            }

            if(tv_name!=null)
            {
                tv_name.setText("");
            }
        }
        else
        {
            if(iv_face!=null)
            {
                //设置默认图片
                ImageManager.LoadHead(DataManager.userinfo.face,iv_face);
            }

            if(tv_name!=null)
            {
                tv_name.setText(DataManager.userinfo.name);
            }
        }

    }

}
