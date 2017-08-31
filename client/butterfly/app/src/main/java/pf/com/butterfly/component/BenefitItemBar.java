package pf.com.butterfly.component;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by admin on 2017/3/9.
 */
public class BenefitItemBar extends View
{
    public BenefitItemBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public BenefitItemBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BenefitItemBar(Context context) {
        super(context);
    }

    private int rate=50;

    public void setProgress(int rate)
    {
        this.rate=rate;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
       // throw new RuntimeException("Stub!");

        Paint pp=new Paint();
        pp.setColor(Color.RED);// 设置红色

        canvas.drawRect(new Rect(0,0,canvas.getWidth()*rate/100,canvas.getHeight()),pp);
    }

}
