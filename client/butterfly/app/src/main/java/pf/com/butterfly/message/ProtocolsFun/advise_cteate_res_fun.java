package pf.com.butterfly.message.ProtocolsFun;
import android.widget.Toast;

import pf.com.butterfly.MainActivity;
import pf.com.butterfly.message.MsgBase;
import pf.com.butterfly.message.IMsgCallback;
import pf.com.butterfly.module.advice.AdviceHead;

public class advise_cteate_res_fun implements IMsgCallback
{
    
      public void MsgCallback(MsgBase msg)
      {
            Toast.makeText(MainActivity.main,"服务端收到了建议",Toast.LENGTH_SHORT).show();
            AdviceHead.getInstance().CleanText();
      }
}
