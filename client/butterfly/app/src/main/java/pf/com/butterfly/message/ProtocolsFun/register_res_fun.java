package pf.com.butterfly.message.ProtocolsFun;
import android.widget.Toast;

import pf.com.butterfly.MainActivity;
import pf.com.butterfly.message.MsgBase;
import pf.com.butterfly.message.IMsgCallback;
import pf.com.butterfly.message.Protocols.register_res;
import pf.com.butterfly.module.login.LoginHead;

public class register_res_fun implements IMsgCallback
{
    
      public void MsgCallback(MsgBase msg)
      {
            //这是在主线程里面了
            register_res res=(register_res)msg;

            if(res.state==0)
            {
                  //注册成功
                  Toast.makeText(MainActivity.main,"哈哈哈",Toast.LENGTH_SHORT).show();

                  LoginHead.getInstance().hide();

            }
            else
            {
                  Toast.makeText(MainActivity.main,"错误码:"+res.state,Toast.LENGTH_SHORT).show();
            }


      }
}
