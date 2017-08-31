package pf.com.butterfly.message.ProtocolsFun;
import android.widget.Toast;

import pf.com.butterfly.MainActivity;
import pf.com.butterfly.message.MsgBase;
import pf.com.butterfly.message.IMsgCallback;
import pf.com.butterfly.message.Protocols.login_res;

public class login_res_fun implements IMsgCallback
{
    
      public void MsgCallback(MsgBase msg)
      {


            //这是在主线程里面了
            login_res res=(login_res)msg;

            if(res.state==0)
            {
                  //注册成功
                  Toast.makeText(MainActivity.main,"登录成功",Toast.LENGTH_SHORT).show();


            }
            else
            {
                  Toast.makeText(MainActivity.main,"错误码:"+res.state,Toast.LENGTH_SHORT).show();
            }
      }
}
