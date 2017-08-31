package pf.com.butterfly.message.ProtocolsFun;
import pf.com.butterfly.message.MsgBase;
import pf.com.butterfly.message.MsgCodeId;
import pf.com.butterfly.message.IMsgCallback;
import pf.com.butterfly.message.Protocols.bored_head_item_add_res;
import pf.com.butterfly.message.Protocols.bored_head_items_res;
import pf.com.butterfly.module.bored.BoredHead;

public class bored_head_item_add_res_fun implements IMsgCallback
{
    
      public void MsgCallback(MsgBase msg)
      {
          //  BoredHead.getInstance().tijiaoBack((bored_head_item_add_res)msg);
      }
}
