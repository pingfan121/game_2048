package pf.com.butterfly.component;

import pf.com.butterfly.model.ItemData;

/**
 * Created by admin on 2017/4/4.
 */
public class ItemSelectData extends ItemData
{
    public int select;
    public int select_icon;
    public int select_no_icon;

    public ItemSelectData(int icon, String text, int select, int select_icon, int select_no_icon)
    {
        super(icon,text);

        this.select=select;
        this.select_icon=select_icon;
        this.select_no_icon=select_no_icon;
    }
}
