package pf.com.butterfly.message.Protocols;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;
import pf.com.butterfly.message.MsgBase;
import pf.com.butterfly.message.MsgCodeId;
  public class bored_record_item_add_res extends MsgBase
  {
      public bored_record_item_add_res()
      {
          CodeId = MsgCodeId.bored_record_item_add_res;
      }
      public int state;    //
      public info_bored_record_item info;    //

}
