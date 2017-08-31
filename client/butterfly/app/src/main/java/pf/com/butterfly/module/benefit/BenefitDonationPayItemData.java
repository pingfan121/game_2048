package pf.com.butterfly.module.benefit;

/**
 * Created by admin on 2017/3/15.
 */
public class BenefitDonationPayItemData
{
    public int icon;//图片
    public String title;//标题
    public int select;//图片

    public void init(int icon,String title,int select) {
        this.icon = icon;
        this.title = title;
        this.select = select;
    }
}
