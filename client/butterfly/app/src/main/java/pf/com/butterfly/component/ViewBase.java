package pf.com.butterfly.component;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by admin on 2017/7/31.
 */

public class ViewBase extends LinearLayout
{

    public ViewBase(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public ViewBase(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ViewBase(Context context) {
        super(context);
        init(context);
    }

    protected void init(Context context)
    {
    }
}
