package pf.com.butterfly.module.advice;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import pf.com.butterfly.MainActivity;
import pf.com.butterfly.R;
import pf.com.butterfly.base.AppBaseViewControl;
import pf.com.butterfly.okhttp.IMsgback;
import pf.com.butterfly.okhttp.OkHttpUtils;
import pf.com.butterfly.util.HDLog;

/**
 * Created by admin on 2017/3/13.
 */
public class AdviceHead extends AppBaseViewControl
{
    private static AdviceHead _instance;

    public static AdviceHead getInstance()
    {
        if(_instance==null)
        {
            _instance=new AdviceHead();
        }

        return _instance;
    }

    protected void initValue()
    {
        title="建议";
        layout=R.layout.advise_head;
    }


    private EditText et;

    @Override
    public void initControl()
    {
        et=(EditText)view.findViewById(R.id.et_advise);

        view.findViewById(R.id.btn_tijiao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnTiJiao();
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

        Map<String,String> maps=new HashMap<>();
        maps.put("userid","");
        maps.put("content", str);
        OkHttpUtils.getInstance().sendAppMsg("advice/add",maps,msg_result);


    }
    private IMsgback msg_result=new IMsgback()
    {
        @Override
        public void onMsgDispose(int err, String result, Object userToken)
        {
            if(err==0)
            {
                HDLog.Toast("建议提交成功,谢谢您的支持");
                AdviceHead.getInstance().CleanText();
            }
        }
    };

    public void CleanText()
    {
        et.setText("");
        et.setHint("感谢您的建议 ,您还可以继续提建议吖。");

    }

}
