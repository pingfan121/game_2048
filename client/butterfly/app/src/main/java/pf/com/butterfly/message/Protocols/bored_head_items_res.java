package pf.com.butterfly.message.Protocols;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;
import pf.com.butterfly.message.MsgBase;
import pf.com.butterfly.message.MsgCodeId;
  public class bored_head_items_res extends MsgBase
  {
      public bored_head_items_res()
      {
          CodeId = MsgCodeId.bored_head_items_res;
      }
      public info_bored_head_item[] infos;    //

}
