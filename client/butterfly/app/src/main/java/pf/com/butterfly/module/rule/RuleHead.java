package pf.com.butterfly.module.rule;

import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;

import pf.com.butterfly.MainActivity;
import pf.com.butterfly.R;
import pf.com.butterfly.adapter.ListViewAdapter;
import pf.com.butterfly.base.AppBaseViewControl;
import pf.com.butterfly.component.IItemClick;
import pf.com.butterfly.component.ItemSelectAdapter;
import pf.com.butterfly.component.ItemSelectData;
import pf.com.butterfly.manager.DataManager;
import pf.com.butterfly.manager.ImageManager;
import pf.com.butterfly.model.ItemData;
import pf.com.butterfly.module.ControlLayer;
import pf.com.butterfly.module.DebugHead;
import pf.com.butterfly.module.advice.AdviceHead;
import pf.com.butterfly.module.bored.BoredHead;
import pf.com.butterfly.module.game_2048.SelectGameHead;
import pf.com.butterfly.module.login.LoginHead;
import pf.com.butterfly.module.menu.MenuItemControl;
import pf.com.butterfly.module.menu.MenuItemData;
import pf.com.butterfly.module.menu.MenuModule;
import pf.com.butterfly.module.set.SetingHead;
import pf.com.butterfly.module.user.UserHead;

/**
 * Created by admin on 2017/8/31.
 */

public class RuleHead extends AppBaseViewControl
{
    private static RuleHead _instance=null;

    public static RuleHead getInstance()
    {
        if(_instance==null)
        {
            _instance=new RuleHead();
            _instance.layer= ControlLayer.rule_head;
        }

        return _instance ;
    }


    private TextView tv_rule;

    protected void initValue()
    {
        layout=R.layout.rule_head;

        title="规则";
    }

    protected void initControl()
    {

        view.findViewById(R.id.btn_close).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                RuleHead.getInstance().hide();
            }
        });

        tv_rule=(TextView) view.findViewById(R.id.tv_rule);

        tv_rule.setText(str);
    }

    private String str="\n我想在这里制定一些app规则,如果大家有好的建议 可以在建议模块里给开发提供修改建议\n\n" +
            "1 敬畏生命.";

}
