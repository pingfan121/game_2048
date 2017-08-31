package pf.com.butterfly.input;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import pf.com.butterfly.MainActivity;
import pf.com.butterfly.R;
import pf.com.butterfly.base.AppBaseViewControl;
import pf.com.butterfly.message.MsgBase;
import pf.com.butterfly.message.Protocols.advise_cteate_req;
import pf.com.butterfly.message.net.IMsgResult;
import pf.com.butterfly.module.ControlLayer;
import pf.com.butterfly.okhttp.IMsgback;
import pf.com.butterfly.okhttp.OkHttpUtils;
import pf.com.butterfly.util.HDLog;
import pf.com.butterfly.util.MixFun;

/**
 * Created by admin on 2017/3/13.
 */
public class TextInput extends AppBaseViewControl
{
    private interface TextInputBack
    {
        void callback(String str);
    }

    private static TextInput _instance;

    public static TextInput getInstance()
    {
        if(_instance==null)
        {
            _instance=new TextInput();
        }

        return _instance;
    }

    protected void initValue()
    {
        title="正在输入中...";
        layout=R.layout.input_text;

        layer= ControlLayer.input_layers;
    }


    private EditText et;

    @Override
    public void initControl()
    {
        et=(EditText)view.findViewById(R.id.et_input);

        view.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnTiJiao();
            }
        });


    }

    @Override
    protected void reShow()
    {
        et.setText("");

        float curr_x =this.view.getX();
        float curr_weight=this.view.getWidth();

        Animation translateAnimation=new TranslateAnimation(-(curr_x+curr_weight),0,0,0);

        translateAnimation.setDuration(1000);               //设置动画持续时间
        view.setAnimation(translateAnimation);             //设置动画效果
        translateAnimation.startNow();                      //启动动画
        translateAnimation.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation)
            {

            }

            @Override
            public void onAnimationEnd(Animation animation)
            {
                et.setFocusable(true);
                MixFun.openInputMethod();
            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {

            }
        });
    }



    public void OnTiJiao()
    {
        // Toast.makeText(ModuleManager.main,"点击了提交按钮...",Toast.LENGTH_SHORT).show();

        String str = et.getText().toString();

        if(str.equals(""))
        {
            Toast.makeText(MainActivity.main,"填一些建议,让世界变得更美好。",Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String,String> map=new HashMap<>();
        map.put("content",str);

        OkHttpUtils.getInstance().sendAppMsg("advice/add",map,add_result);


    }

    private IMsgback add_result=new IMsgback()
    {
        @Override
        public void onMsgDispose(int err, String result, Object userToken)
        {
            if(err!=0)
            {
                HDLog.Toast(result);
                return ;
            }

            HDLog.Toast("建议提交成功,谢谢您的支持");
        }
    };

    public void CleanText()
    {
        et.setText("");
        et.setHint("感谢您的建议 ,您还可以继续提建议吖。");

    }

}
