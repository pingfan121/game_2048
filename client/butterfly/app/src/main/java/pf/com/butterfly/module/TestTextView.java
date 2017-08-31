package pf.com.butterfly.module;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import pf.com.butterfly.MainActivity;
import pf.com.butterfly.R;
import pf.com.butterfly.component.HtmlTextView;

/**
 * Created by admin on 2017/2/26.
 */
public class TestTextView
{
    private static ImageView img=null;
    public static Button btn_close=null;
    public static HtmlTextView htv=null;

    private static View father;

    //测试图文混排
    public static void Init()
    {
        View view= MainActivity.main.findViewById(R.id.layer1);
        father=view.findViewById(R.id.textView);
        htv=(HtmlTextView)father.findViewById(R.id.tuwen_view);
        btn_close=(Button) father.findViewById(R.id.btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Hide();
            }
        });

    }

    public static void Hide()
    {
        father.setVisibility(View.INVISIBLE);
    }

    public static void Show()
    {
        father.setVisibility(View.VISIBLE);
    }

    public static void SetView()
    {
        Show();

        String html = "下面是图片了 " +"<img src='http://www.qqpk.cn/Article/UploadFiles/201411/20141116135722282.jpg'/>" +
                "这也是图片 " +"<img src='http://h.hiphotos.baidu.com/image/pic/item/d000baa1cd11728b2027e428cafcc3cec3fd2cb5.jpg'/>" +
                "还有一张 "+  "<img src='http://img.61gequ.com/allimg/2011-4/201142614314278502.jpg' />";

        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(MainActivity.main));
        htv.setHtmlFromString(html,false);
    }

}
