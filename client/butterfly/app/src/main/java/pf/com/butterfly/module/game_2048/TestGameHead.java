package pf.com.butterfly.module.game_2048;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pf.com.butterfly.R;
import pf.com.butterfly.base.AppBaseViewControl;
import pf.com.butterfly.component.game_2048_view;
import pf.com.butterfly.module.ControlLayer;
import pf.com.butterfly.util.HDLog;
import pf.com.butterfly.util.Point;

/**
 * Created by admin on 2017/3/3.
 */
public class TestGameHead extends AppBaseViewControl
{
    private static TestGameHead _instance;

    public static TestGameHead getInstance()
    {
        if(_instance==null)
        {
            _instance=new TestGameHead();
        }
        return _instance;
    }

    private SmallGame game;

    protected void initValue()
    {
        title="2048";
        layout=R.layout.game_2048_head;
        layer= ControlLayer.module_view1;
    }


    private game_2048_view gameview;

    @Override
    public void initControl()
    {
        gameview=(game_2048_view)view.findViewById(R.id.game_view);



        //重玩按钮
        view.findViewById(R.id.btn_reset).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                OnBtnReset();
            }
        });

        //排行按钮
        view.findViewById(R.id.btn_rank).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                OnBtnRank();
            }
        });

        //分享按钮
        view.findViewById(R.id.btn_share).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                OnBtnShare();
            }
        });

        //声音按钮
        view.findViewById(R.id.btn_sound).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                OnBtnSound();
            }
        });
    }

    public void showView(int type)
    {
        show();

        if(game==null)
        {
            game=new SmallGame();
        }

        game.init(type);

        gameview.setGrids(game.getGrids());
    }

    public void resetView()
    {
        gameview.resetView();
    }

    public void MoveView(Point orgPos, Point dirPos)
    {
        String dir=getdirection(orgPos,dirPos);

        if(dir!="")
        {
            game.OperGame(dir);
        }
    }


    public String getdirection(Point orgPos, Point dirPos)
    {
        Point TTpos = new Point();

        TTpos.X = (short)(dirPos.X - orgPos.X);
        TTpos.Y = (short)(dirPos.Y - orgPos.Y);

        float length = Math.max(Math.abs(TTpos.X), Math.abs(TTpos.Y));

        if(length < 5)
        {
            return "";
        }


        double tanx = Math.atan2(TTpos.Y, TTpos.X);

        double degrees = (180 / Math.PI) * tanx;

        while(degrees > 360)
        {
            degrees -= 360;
        }

        while(degrees < 0)
        {
            degrees += 360;
        }

        //角度得到了...怎么算方向
        if(degrees > 315 || degrees <= 45)   //朝右
        {
            return "right";
        }

        if(degrees > 45 && degrees <= 135)   //朝下
        {
            return "down";
        }

        if(degrees > 135 && degrees <= 225)   //朝左
        {
            return "left";
        }

        if(degrees > 225 && degrees <= 315)   //朝上
        {
            return "up";
        }

        return "";

    }


    //重玩
    private void OnBtnReset()
    {
        game.restartGame();
    }

    //排行
    private void OnBtnRank()
    {
        HDLog.Toast("排行功能暂未开放");
    }

    //分享
    private void OnBtnShare()
    {
        HDLog.Toast("分享功能暂未开放");
    }

    boolean sound_flag=true;
    //声音
    private void OnBtnSound()
    {
        sound_flag=!sound_flag;

        if(sound_flag==true)
        {
            ((Button)view.findViewById(R.id.btn_sound)).setText("声音(开)");
        }
        else
        {
            ((Button)view.findViewById(R.id.btn_sound)).setText("声音(关)");
        }

        game.setSoundFlag(sound_flag);
    }

    //设置分数
    public void setScore(int curr_score,int max_score)
    {
        ((TextView)view.findViewById(R.id.tv_curr_score)).setText(curr_score+"");

        ((TextView)view.findViewById(R.id.tv_max_score)).setText(max_score+"");

    }


}

