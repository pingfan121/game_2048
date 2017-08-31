package pf.com.butterfly.module;

import android.view.MotionEvent;
import android.view.View;

import pf.com.butterfly.MainActivity;
import pf.com.butterfly.R;

/**
 * Created by admin on 2017/3/29.
 */
public class ViewClick
{

    public static View.OnTouchListener touchLis = new View.OnTouchListener()
    {
        public boolean onTouch(View v, MotionEvent event)
        {
            // TODO Auto-generated method stub
            //scheduler.getGestureDetector().onTouchEvent(event);
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:	//按下
                    Down(v);
                    break;
                case MotionEvent.ACTION_CANCEL:    //取消
                case MotionEvent.ACTION_UP://抬起
                    Up(v);
                    break;
                default:
                    break;
            }
            return false;
        }
    };

    //按下
    public static void Down(View view)
    {
            view.setBackgroundResource(R.color.app_view_down);
    }

    //弹起
    public static void Up(View view)
    {
        view.setBackgroundResource(R.color.app_white);
    }


}
