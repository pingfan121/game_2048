package pf.com.butterfly.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by admin on 2017/3/3.
 * app基本视图基类
 */
public class AppBaseControl
{
    protected  View view;
    private  View fatherview;
    private  AppBaseFragment fatherfragment;

    public void init(AppBaseFragment fragment,View father)
    {
        fatherfragment=fragment;
        fatherview=father;
    }

    public void Hide()
    {
        fatherfragment.hideView(this);
    }
    public void Show()
    {
        fatherfragment.showView(this);
    }
    public View GetView()
    {
        return view;
    }

    public void SetTitleView()
    {

    }
}
