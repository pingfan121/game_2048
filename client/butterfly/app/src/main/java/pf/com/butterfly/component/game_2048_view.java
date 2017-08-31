package pf.com.butterfly.component;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

import pf.com.butterfly.module.game_2048.NumGrid;
import pf.com.butterfly.module.game_2048.TestGameHead;
import pf.com.butterfly.util.HDLog;
import pf.com.butterfly.util.Point;

/**
 * Created by admin on 2017/7/31.
 */

public class game_2048_view extends View
{
    public game_2048_view(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public game_2048_view(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public game_2048_view(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context)
    {
        setColor();
    }

    public void setGrids(NumGrid[][] grids)
    {
        this.grids=grids;
    }

    public void resetView()
    {
        this.invalidate();
    }

    private int gap = 5;
    private int width;

    private float grid_width;

    public NumGrid[][] grids;

    //画图
    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        canvas.getWidth();

        int width = canvas.getWidth();

        grid_width = (width - (grids.length+1) * gap) / (float)grids.length;

        Paint p = new Paint();
        p.setColor(0xff72777b);// 设置背景

        canvas.drawRect(0,0,width,width,p);

        Paint textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(grid_width/3);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTextAlign(Paint.Align.CENTER);

        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float top = fontMetrics.top;//为基线到字体上边框的距离,即上图中的top
        float bottom = fontMetrics.bottom;//为基线到字体下边框的距离,即上图中的bottom

        int baseLineY = (int) (grid_width/2 - top/2 - bottom/2);//基线中间点的y轴计算公式


        for(int i = 0; i < grids.length; i++)
        {
            for(int k = 0; k < grids[i].length; k++)
            {

                NumGrid grid = grids[i][k];

                float starty = gap + i * grid_width + i * gap;
                float startx = gap + k * grid_width + k * gap;

                p = new Paint();
                p.setColor(colors.get(grid.num));

                canvas.drawRect(startx, starty, startx+grid_width, starty+grid_width,p);

                if(grid.num != 0)
                {
                    if(grids[i][k].num<100000)
                    {
                        textPaint.setTextSize(grid_width / 3);
                    }
                    else if(grids[i][k].num<10000000)
                    {
                        textPaint.setTextSize(grid_width / 4);
                    }
                    else if(grids[i][k].num<100000000)
                    {
                        textPaint.setTextSize(grid_width / 5);
                    }
                    else if(grids[i][k].num<2100000000)
                    {
                        textPaint.setTextSize(grid_width / 6);
                    }

                    canvas.drawText(grids[i][k].num+"",startx+grid_width/2,starty+baseLineY,textPaint);
                }
            }
        }

//        int a=width/colors.size();
//        int index=0;
//
//        for (int v : colors.values())
//        {
//            p = new Paint();
//            p.setColor(v);
//
//            canvas.drawRect(a*index,width+10,a*index+a,width+10+30,p);
//
//            index++;
//        }

    }

    private Point p1=new Point();
    private Point p2=new Point();

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        int action = event.getAction();

        switch (action)
        {
            case MotionEvent.ACTION_DOWN:
            {
                p1.X=event.getX();
                p1.Y=event.getY();

                break;
            }
            case MotionEvent.ACTION_UP:
            {
                p2.X=event.getX();
                p2.Y=event.getY();

                TestGameHead.getInstance().MoveView(p1,p2);
                break;
            }
            default:
                break;
        }

        return true;
    }

    private Map<Integer, Integer> colors = new HashMap<>();

    private void setColor()
    {
        colors.clear();

        colors.put(0, 0xff9d9087);
        colors.put(2, 0xfffedcbd);
        colors.put(4, 0xffdec674);
        colors.put(8, 0xffc7a252);
        colors.put(16, 0xfff26522);
        colors.put(32, 0xffdbce8f);
        colors.put(64, 0xffc99979);
        colors.put(128, 0xffde773f);
        colors.put(256, 0xffbed742);
        colors.put(512, 0xffda765b);
        colors.put(1024, 0xfffdb933);
        colors.put(2048, 0xfff173ac);
        colors.put(4096, 0xfff7acbc);
        colors.put(8192, 0xffef5b9c);
        colors.put(16384, 0xff45b97c);
        colors.put(32768, 0xfff15b6c);
        colors.put(65536, 0xfff8aba6);
        colors.put(131072, 0xfff391a9);
        colors.put(262144, 0xffbd6758);
        colors.put(524288, 0xffd64f44);
        colors.put(1048576, 0xffc76968);
        colors.put(2097152, 0xfff26522);
        colors.put(4194304, 0xff78a355);
        colors.put(8388608, 0xfffaa755);
        colors.put(16777216, 0xfffab27b);
        colors.put(33554432, 0xff426ab3);
        colors.put(67108864, 0xffae6642);
        colors.put(134217728, 0xff9b95c9);
        colors.put(268435456, 0xffd5c59f);
        colors.put(536870912, 0xffdf9464);
        colors.put(1073741824, 0xff87843b);
    }
}
