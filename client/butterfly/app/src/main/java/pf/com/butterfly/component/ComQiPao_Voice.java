package pf.com.butterfly.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import pf.com.butterfly.R;

/**
 * Created by admin on 2017/5/25.
 */

public class ComQiPao_Voice extends LinearLayout
{


    public ComQiPao_Voice(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public ComQiPao_Voice(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ComQiPao_Voice(Context context) {
        super(context);
        init(context);
    }


    private View view;

    private void init(Context context) {

        LayoutInflater inflater= LayoutInflater.from(context);

        view= inflater.inflate(R.layout.com_qipao_voice,null);

        this.addView(view);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        int action = event.getAction();

        switch (action)
        {
            case MotionEvent.ACTION_DOWN:
            {
                setBackground(true);

                break;
            }
            case MotionEvent.ACTION_CANCEL:
            {
                setBackground(false);
                break;
            }
            case MotionEvent.ACTION_UP:
            {
                setBackground(false);
                break;
            }
            default:
                break;
        }

        return false;
    }

    public void setBackground(boolean flag)
    {
        if(flag==false)
        {
           // tv_text.setBackgroundResource(R.drawable.qipao4);
        }
        else
        {
          //  tv_text.setBackgroundResource(R.drawable.qipao3);
        }
    }
}
