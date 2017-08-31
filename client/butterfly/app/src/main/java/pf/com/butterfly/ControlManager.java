package pf.com.butterfly;


import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import pf.com.butterfly.base.AppBaseViewControl;
import pf.com.butterfly.module.main.MainHead;
import pf.com.butterfly.module.menu.MenuModule;

/**
 * Created by Administrator on 2016/6/10.
 */
public class ControlManager
{

    public static void resetView()
    {
        for(int i=0;i<layers.size();i++)
        {
            if(view_layers.get(layers.get(i))==null)
            {
                continue;
            }

            if(view_layers.get(layers.get(i)).getChildCount()>0)
            {
                ReSetBaseView();
                return ;
            }
        }

        MainHead.getInstance().show();
    }

    public static void show(AppBaseViewControl control)
    {
        ShowView(control);
    }

    public static void hide(AppBaseViewControl control)
    {
        HideView(control);
    }

    public static void Rollback()
    {

        //从最上层层级往下过滤 过滤到
        for(int i=layers.size()-1;i>=0;i--)
        {
            if(layers.get(i)!=-100)
            {
                if(view_layers.get(layers.get(i))==null)
                {
                    continue;
                }

                if(view_layers.get(layers.get(i)).getChildCount()>0)
                {
                    hide(view_controls.get(layers.get(i)));

                    return ;
                }
            }
        }

        if(MenuModule.getInstance().getState()==true)
        {
            MenuModule.getInstance().ShowMenu(false);
        }
        else
        {
            MenuModule.getInstance().ShowMenu(true);
        }
    }

    //--------------下面是视图显示层逻辑-----------------
    private static FrameLayout father;
    private static FrameLayout father2;
    private static HashMap<Integer,FrameLayout> view_layers=new HashMap<Integer,FrameLayout>();
    private static HashMap<Integer,AppBaseViewControl> view_controls=new HashMap<Integer,AppBaseViewControl>();
    public static List<Integer> layers=new ArrayList<Integer>();  //视图层级

    public static void ShowView(AppBaseViewControl viewControl)
    {

        father2 = (FrameLayout)MainActivity.main.findViewById(R.id.control_view);

        if(father!=father2)
        {
            ReSetBaseView();
        }

        if(layers.contains(viewControl.layer)==false)
        {
            layers.add(viewControl.layer);
            Collections.sort(layers, comp);
        }

        if(view_layers.containsKey(viewControl.layer)==false)
        {
            LayoutInflater inflater = LayoutInflater.from(father.getContext());

            FrameLayout temp_layout = (FrameLayout) inflater.inflate(R.layout.module_base, null);

            view_layers.put(viewControl.layer, temp_layout);
        }

        view_controls.put(viewControl.layer,viewControl);

        //重新加载
        father.removeAllViews();

        for (int i=0;i<layers.size();i++)
        {
            FrameLayout fl=(FrameLayout)view_layers.get(layers.get(i));
            if(fl!=null)
            {
                father.addView(fl);
            }
        }


        FrameLayout fl=view_layers.get(viewControl.layer);

        fl.removeAllViews();

        if(viewControl.view==null)
        {
            LayoutInflater inflater = LayoutInflater.from(fl.getContext());

            viewControl.view = inflater.inflate(viewControl.layout, null);

            fl.addView(viewControl.view);

            viewControl.view.setClickable(true);
            viewControl.showMode(true);

        }
        else
        {
            fl.addView(viewControl.view);

            viewControl.showMode(false);

        }

        //将层次高的全部清理掉
        for(int i=0;i<layers.size();i++)
        {
            if(layers.get(i)>viewControl.layer)
            {
                if( view_layers.get(layers.get(i))==null)
                {
                    continue;
                }

                view_layers.get(layers.get(i)).removeAllViews();
                father.removeView(view_layers.get(layers.get(i)));
                view_layers.remove(layers.get(i));
                view_controls.remove(layers.get(i));
            }
        }
    }

    public static void HideView(AppBaseViewControl viewControl)
    {

        if(layers.contains(viewControl.layer)==false)
        {
            return ;
        }

        if(view_layers.containsKey(viewControl.layer)==false)
        {
            return ;
        }

        FrameLayout fl=view_layers.get(viewControl.layer);

        fl.removeAllViews();



        //遍历最上层的层级显示
        for (int i=layers.size()-1;i>=0;i--)
        {
            fl=(FrameLayout)view_layers.get(layers.get(i));
            if(fl==null)
            {
                continue;
            }

            if(fl.getChildCount()>0)
            {
                AppBaseViewControl control = view_controls.get(layers.get(i));
                control.showMode(false);
                return ;
            }
            else
            {
                father.removeView(fl);

                view_layers.remove(layers.get(i));
                view_controls.remove(layers.get(i));
            }
        }
    }


    private static void ReSetBaseView()
    {
        if(father !=null)
        {
            //重新加载
            father.removeAllViews();
        }

        father = (FrameLayout)MainActivity.main.findViewById(R.id.control_view);

        for (int i=0;i<layers.size();i++)
        {
            FrameLayout fl=(FrameLayout)view_layers.get(layers.get(i));

            if(fl==null)
            {
                continue;
            }

            father.addView(fl);
        }

        for (int i=layers.size()-1;i>=0;i--)
        {
            FrameLayout fl=(FrameLayout)view_layers.get(layers.get(i));

            if(fl==null)
            {
                continue;
            }

            if(fl.getChildCount()>0)
            {
                AppBaseViewControl control = view_controls.get(layers.get(i));
                control.showMode(true);
                return ;
            }
        }
    }




    //...排序 从小到大
    private static Comparator comp = new Comparator()
    {
        public int compare(Object o1, Object o2) {
            int p1 = (int) o1;
            int p2 = (int) o2;
            if (p1 < p2)
                return -1;
            else if (p1 == p2)
                return 0;
            else if (p1 > p2)
                return 1;
            return 0;
        }
    };

}

























