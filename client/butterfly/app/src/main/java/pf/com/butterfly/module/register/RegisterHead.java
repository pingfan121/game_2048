package pf.com.butterfly.module.register;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import pf.com.butterfly.R;
import pf.com.butterfly.base.AppBaseViewControl;
import pf.com.butterfly.okhttp.IMsgback;
import pf.com.butterfly.bmob.BmobHttp;
import pf.com.butterfly.message.Protocols.register_req;
import pf.com.butterfly.util.HDLog;

/**
 * Created by admin on 2017/3/3.
 */
public class RegisterHead extends AppBaseViewControl
{
    private static RegisterHead _instance;

    public static RegisterHead getInstance()
    {
        if(_instance==null)
        {
            _instance=new RegisterHead();
        }
        return _instance;
    }

    protected void initValue()
    {
        title="注册";
        layout=R.layout.register_head;
    }


    private EditText et_mobile;
    private EditText et_password;
    private EditText et_nickname;
    private EditText et_verify;
    private Button btn_register;
    private Button btn_verify;

    private  BmobHttp http;


    @Override
    public void initControl()
    {

        et_mobile=(EditText)view.findViewById(R.id.et_tel);
        et_password=(EditText)view.findViewById(R.id.et_pass);
        et_nickname=(EditText)view.findViewById(R.id.et_name);
        et_verify=(EditText)view.findViewById(R.id.et_verify);

        btn_register=(Button) view.findViewById(R.id.btn_register);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnRegisterBtn();
            }
        });

        btn_verify=(Button) view.findViewById(R.id.btn_verify);

        btn_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnVerifyBtn();
            }
        });

        view.setVisibility(View.INVISIBLE);

        http=new BmobHttp(new IMsgback() {
            @Override
            public void onMsgDispose(int err,String result,Object userToken)
            {
                OnMessage(err,result,userToken);
            }
        });
    }

    //注册按钮事件
    private void OnRegisterBtn()
    {

//        //验证验证验证码
//        reqdata2 data2=new reqdata2();
//        data2.mobilePhoneNumber=et_mobile.getText().toString();
//
//        String str=new Gson().toJson(data2);
//
//        String code=et_verify.getText().toString();
//        //去获取验证码
//        http.send("https://api.bmob.cn/1/verifySmsCode/"+ code,str,2);

        RegisterUser();

    }

    //获取验证码按钮
    private void OnVerifyBtn()
    {
        reqdata data=new reqdata();
        data.mobilePhoneNumber=et_mobile.getText().toString();
        data.template="蝴蝶验证码";

        String str=new Gson().toJson(data);
       //去获取验证码
        http.send(BmobHttp.YZM_url,str,1);

    }


    //处理http返回
    private void OnMessage(int err,String result,Object userToken)
    {
        if(err==-99 ||err==-100 )
        {
            http.errerDispose(err,result);
            return ;
        }

        int token=(int)userToken;

        if(token==1)  //获取验证码
        {
            HDLog.info(result);
            HDLog.Toast("验证码已发送");
        }
        else if(token==2) //验证验证码
        {
            HDLog.Toast(result);

            //提交服务器注册....
            RegisterUser();
        }

    }

    private void RegisterUser()
    {
        //验证手机号
        String mob=et_mobile.getText().toString();

        String pass=et_password.getText().toString();

        String name=et_nickname.getText().toString();

        //发送消息

        register_req req=new register_req();

        req.tel=mob;
        req.pass=pass;
        req.nickname=name;

    //    NetManager.SendMsg(req);
    }


}

class reqdata
{
    public String mobilePhoneNumber;
    public String template;
}
class reqdata2
{
    public String mobilePhoneNumber;
}

