package pf.com.butterfly.message.Protocols;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;
import pf.com.butterfly.message.MsgBase;
import pf.com.butterfly.message.MsgCodeId;
  public class bored_record_item_add_req extends MsgBase
  {
      public bored_record_item_add_req()
      {
          CodeId = MsgCodeId.bored_record_item_add_req;
      }
      public String head_id;    //
      public String url;    //
      public int time;    //

}
