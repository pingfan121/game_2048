package pf.com.butterfly.module.title;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import pf.com.butterfly.ControlManager;
import pf.com.butterfly.R;
import pf.com.butterfly.module.rule.RuleHead;

/**
 * Created by admin on 2017/3/5.
 */
public class TitleModule
{
    private static TitleModule _instance=null;

    public static TitleModule getInstance()
    {
        if(_instance==null)
        {
            _instance=new TitleModule();
        }

        return _instance ;
    }


    private View view;
    private View btn_backawd ;//返回
    private View btn_shuoming;  //说明
    private ImageView iv_fanhui;  //返回
    private ImageView iv_shuoming;  //说明

    private TextView t_title;  //标题

    private ITitleExplain explain;  //说明按钮的接口




    public void init(View father)
    {
        view= father.findViewById(R.id.module_title);

        iv_fanhui=(ImageView) view.findViewById(R.id.iv1);
        iv_shuoming=(ImageView) view.findViewById(R.id.iv2);

        btn_backawd=(LinearLayout)view.findViewById(R.id.ll_fanhui);
        btn_shuoming=(LinearLayout)view.findViewById(R.id.ll_shuoming);
        t_title=(TextView)view.findViewById(R.id.t_title);

        btn_backawd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnBtnBack();
            }
        });

        btn_shuoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnBtnShuoMing();
            }
        });


    }

    //返回按钮事件
    public void OnBtnBack()
    {
        ControlManager.Rollback();
    }


    public void OnBtnShuoMing()
    {
        RuleHead.getInstance().show();

    }
    //其他事件

    //设置标题
    public void SetTitle(String title)
    {
        t_title.setText(title);
        explain=null;

    //    btn_shuoming.setVisibility(View.INVISIBLE);

        iv_fanhui.setImageResource(R.drawable.fanhui);
    }
    public void SetTitle(String title,ITitleExplain explain)
    {
        t_title.setText(title);
        this.explain=explain;

//        if(explain==null)
//        {
//            btn_shuoming.setVisibility(View.INVISIBLE);
//        }
//        else
//        {
//            btn_shuoming.setVisibility(View.VISIBLE);
//        }


        iv_fanhui.setImageResource(R.drawable.fanhui);
    }

    public void SetCatalogIcon()
    {
        iv_fanhui.setImageResource(R.drawable.mulu);
    }

}
