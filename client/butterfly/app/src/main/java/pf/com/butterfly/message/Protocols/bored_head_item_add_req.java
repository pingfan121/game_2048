package pf.com.butterfly.message.Protocols;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;
import pf.com.butterfly.message.MsgBase;
import pf.com.butterfly.message.MsgCodeId;
  public class bored_head_item_add_req extends MsgBase
  {
      public bored_head_item_add_req()
      {
          CodeId = MsgCodeId.bored_head_item_add_req;
      }
      public String content;    //

}
