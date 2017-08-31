package pf.com.butterfly.module.game_2048;

import android.view.View;

import pf.com.butterfly.MainActivity;
import pf.com.butterfly.R;
import pf.com.butterfly.base.AppBaseViewControl;
import pf.com.butterfly.manager.DataManager;
import pf.com.butterfly.module.login.LoginHead;
import pf.com.butterfly.util.HDLog;

/**
 * Created by admin on 2017/3/3.
 */
public class SelectGameHead extends AppBaseViewControl
{
    private static SelectGameHead _instance;

    public static SelectGameHead getInstance()
    {
        if(_instance==null)
        {
            _instance=new SelectGameHead();
        }
        return _instance;
    }

    private SmallGame game;

    protected void initValue()
    {
        title="2048";
        layout=R.layout.game_2048_select;
    }


    @Override
    public void initControl()
    {
        view.findViewById(R.id.btn_44).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                if(DataManager.checkLogin()==false)
                {
                    return ;
                }
                TestGameHead.getInstance().showView(4);
            }
        });

        view.findViewById(R.id.btn_66).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(DataManager.checkLogin()==false)
                {
                    return ;
                }
                TestGameHead.getInstance().showView(6);
            }
        });

    }



}

