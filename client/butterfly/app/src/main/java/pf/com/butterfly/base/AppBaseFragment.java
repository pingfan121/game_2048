package pf.com.butterfly.base;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class AppBaseFragment extends Fragment
{
    protected View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //初始化视图
        initView(inflater, container, savedInstanceState);


        return view;
    }


    protected void initView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState)
    {

    }



    //设置第一视图
    protected void SetFirstControl(AppBaseControl control)
    {
        resetView();
        control.Show();
    }

    //重置视图
    public void resetView()
    {
        for(int i=0;i<listview.size();i++)
        {
            listview.get(i).Hide();
        }
        listview.clear();
    }

    public void resetTitleView()
    {
        if(listview.size()>0)
        {
            AppBaseControl abc2=listview.get(0);
            abc2.SetTitleView();
        }
    }

    public View getFatherView()
    {
        return view;
    }

    //回退

    public boolean rollback()
    {
        if(listview.size()>1)
        {
            listview.get(0).Hide();
            listview.remove(0);

           listview.get(0).Show();

            return true;
        }
        else
        {
            return false;
        }
    }

    private ArrayList<AppBaseControl> listview=new ArrayList<AppBaseControl>();
    //显示视图
    public void showView(AppBaseControl control)
    {
        if(listview.size()>0)
        {
            //取出第一个视图
            listview.get(0).Hide();
        }

        if(listview.contains(control))
        {
            listview.remove(control);
        }

        //放到列表第一个
        control.GetView().setVisibility(View.VISIBLE);
        control.SetTitleView();

        listview.add(0,control);
    }

    //隐藏视图
    //显示视图
    public void hideView(AppBaseControl control)
    {
        control.GetView().setVisibility(View.INVISIBLE);
    }

}
