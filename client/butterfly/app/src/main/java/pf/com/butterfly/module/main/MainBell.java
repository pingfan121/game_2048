//package pf.com.butterfly.module.main;
//
//import android.media.MediaPlayer;
//import android.view.View;
//import android.widget.TextView;
//
//import pf.com.butterfly.MainActivity;
//import pf.com.butterfly.R;
//import pf.com.butterfly.base.AppBaseViewControl;
//import pf.com.butterfly.module.ControlLayer;
//import pf.com.butterfly.manager.MediaManager;
//import pf.com.butterfly.module.title.TitleModule;
//
///**
// * Created by admin on 2017/3/4.
// */
//public class MainHead extends AppBaseViewControl
//{
//    private static MainHead _instance=null;
//
//    private MediaPlayer player;
//
//    public static MainHead getInstance()
//    {
//        if(_instance==null)
//        {
//            _instance=new MainHead();
//        }
//
//
//        return _instance ;
//    }
//
//    protected void initValue()
//    {
//        title="首页";
//
//        layout=R.layout.main_head;
//
//        layer= ControlLayer.main_layers;
//    }
//
//    private TextView tv;
//
//
//    protected void initControl()
//    {
//        tv=(TextView)view.findViewById(R.id.tv_story);
//
//        //   setText(st);
//
//        view.findViewById(R.id.btn_fun).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                MainTest.getInstance().show();
//            }
//        });
//
//        view.findViewById(R.id.ib_bell).setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                //播放声音
//                player = MediaPlayer.create(MainActivity.main,R.raw.bell);
//                player.start();
//            }
//        });
//    }
//
//    public void setText(String str)
//    {
//        tv.setText(str);
//    }
//
//    @Override
//    public void resetTitle()
//    {
//        TitleModule.getInstance().SetTitle(title);
//
//        TitleModule.getInstance().SetCatalogIcon();
//    }
//
//
////    private String st="　　在暴风雨后的一个早晨，一个先生来到海边散步。他沿着海边走着，发现在沙滩的浅水洼里，有许多被昨夜的暴风雨卷上岸来的小鱼。它们被困在浅水洼里，尽管大海近在咫尺，却回不去了。用不了多久，浅水洼里的水就会被沙粒吸干，被太阳蒸干，这些小鱼就会干死。\n" +
////            "　　先生继续走着，忽然看见前面有一个小男孩，他边走边看，不停地在每个水洼旁弯下腰去，捡起里面的小鱼，用力地把它们扔回大海。\n" +
////            "　　这位先生忍不住走过去对小男孩说：“孩子，水洼里有成百上千条小鱼，你是救不过来的。”\n" +
////            "　　“我知道。”小男孩头也不抬地回答。\n" +
////            "　　“哦？那你为什么还在扔？谁在乎呢？”\n" +
////            "　　“这条小鱼在乎！”小男孩一边回答，一边捡起一条小鱼扔进大海，“这条在乎，这条也在乎！还有这一条、这一条、这一条……”\n" +
////            "\n";
//
//
//}
