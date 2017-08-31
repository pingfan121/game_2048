package pf.com.butterfly.module.menu;

import android.view.View;

import pf.com.butterfly.adapter.AdapterItemData;
import pf.com.butterfly.base.AppBaseControl;
import pf.com.butterfly.base.AppBaseViewControl;

/**
 * Created by admin on 2017/3/12.
 */
public class MenuItemData extends AdapterItemData
{
    public int icon;//图片
    public String title;//标题
    public AppBaseViewControl viewControl;//点击显示的视图

    public MenuItemData(int icon,String title, AppBaseViewControl viewControl) {
        this.icon = icon;
        this.title = title;
        this.viewControl = viewControl;
    }
}
