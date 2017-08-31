package pf.com.butterfly.module.menu;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.makeramen.roundedimageview.RoundedImageView;

import pf.com.butterfly.MainActivity;
import pf.com.butterfly.R;
import pf.com.butterfly.adapter.ListViewAdapter;
import pf.com.butterfly.manager.DataManager;
import pf.com.butterfly.manager.ImageManager;
import pf.com.butterfly.module.advice.AdviceHead;
import pf.com.butterfly.module.bored.BoredHead;
import pf.com.butterfly.module.game_2048.SelectGameHead;
import pf.com.butterfly.module.login.LoginHead;
import pf.com.butterfly.module.set.SetingHead;
import pf.com.butterfly.module.user.UserHead;


/**
 * A fragment with a Google +1 button.
 */
public class MenuModule
 {

    private static MenuModule _instance=null;

    public static MenuModule getInstance()
    {
        if(_instance==null)
        {
            _instance=new MenuModule();
        }

        return _instance ;
    }

    private static DrawerLayout slide;//菜单布局
    private View view;  //菜单视图

    private RoundedImageView riv_head;

    public void init( View father)
    {

        initValue();

        LayoutInflater inflater= LayoutInflater.from(father.getContext());
        //菜单布局
        slide = (DrawerLayout)father;
        //菜单视图
        view= father.findViewById(R.id.module_menu);

        riv_head=(RoundedImageView) view.findViewById(R.id.riv_head);

        ListView listView=(ListView)view.findViewById(R.id.menu_item_list);

        listView.setAdapter(adapter1);
        listView.setOnItemClickListener(aa);

        ListView listView2=(ListView)view.findViewById(R.id.menu_item_list2);

        listView2.setAdapter(adapter2);
        listView2.setOnItemClickListener(aa);

        ListView listView3=(ListView)view.findViewById(R.id.menu_item_list3);

        listView3.setAdapter(adapter3);
        listView3.setOnItemClickListener(aa);


        view.findViewById(R.id.riv_head).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ShowMenu(false);

                if(DataManager.login_flag==false)
                {
                    LoginHead.getInstance().show();
                }
                else
                {
                    UserHead.getInstance().show();
                }

            }
        });


    }

    private AdapterView.OnItemClickListener aa=new AdapterView.OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
        {
            MenuItemData data=(MenuItemData)view.getTag();

            if(data!=null)
            {
                MenuModule.getInstance().ShowMenu(false);

                if("退出".equals(data.title)==true)
                {
                    MainActivity.main.finish();
                }
                else
                {
                    data.viewControl.show();
                }
            }
        }
    };


    public void ShowMenu(boolean flag)
    {
        if(flag==false)
        {
            slide.closeDrawers();
        }
        else
        {
            slide.openDrawer(Gravity.LEFT);
        }
    }

     public boolean getState()
     {
         return  slide.isDrawerOpen(Gravity.LEFT);
     }

     private ListViewAdapter adapter1;
     private ListViewAdapter adapter2;
     private ListViewAdapter adapter3;

     private void initValue()
     {
         adapter1=new ListViewAdapter(R.layout.itemlayout,MenuItemControl.class.getName());


       //  adapter1.addOneItem(new MenuItemData(R.drawable.hudie,"蝴蝶",BenefitHead.getInstance()));
         adapter1.addOneItem(new MenuItemData(R.drawable.bored,"好无聊啊",BoredHead.getInstance()));
      //   adapter1.addOneItem(new MenuItemData(R.drawable.rou,"中午吃啥",BoredHead.getInstance()));
      //   adapter1.addOneItem(new MenuItemData(R.drawable.yu,"晚上吃啥",BoredHead.getInstance()));

         adapter2=new ListViewAdapter(R.layout.itemlayout,MenuItemControl.class.getName());

         adapter2.addOneItem(new MenuItemData(R.drawable.si_game,"2048", SelectGameHead.getInstance()));
//         adapter2.addOneItem(new MenuItemData(R.drawable.hudie,"回不去", AdviceHead.getInstance()));
//         adapter2.addOneItem(new MenuItemData(R.drawable.hudie,"舍不得",SetingHead.getInstance()));
//         adapter2.addOneItem(new MenuItemData(R.drawable.hudie,"来不及",SetingHead.getInstance()));
//         adapter2.addOneItem(new MenuItemData(R.drawable.hudie,"乌托邦",SetingHead.getInstance()));


         adapter3=new ListViewAdapter(R.layout.itemlayout,MenuItemControl.class.getName());


         adapter3.addOneItem(new MenuItemData(R.drawable.si_advice,"建议", AdviceHead.getInstance()));
         adapter3.addOneItem(new MenuItemData(R.drawable.si_set,"设置",SetingHead.getInstance()));
         adapter3.addOneItem(new MenuItemData(R.drawable.si_exit,"退出",SetingHead.getInstance()));


     }

     public void updateFace()
     {
         ImageManager.LoadHead(DataManager.userinfo.face,riv_head);
     }


}
