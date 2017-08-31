package pf.com.butterfly.message.Protocols;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;
import pf.com.butterfly.message.MsgBase;
import pf.com.butterfly.message.MsgCodeId;
  public class info_bored_head_item extends MsgBase
  {
      public info_bored_head_item()
      {
          CodeId = MsgCodeId.info_bored_head_item;
      }
      public String id;    //
      public String userid;    //
      public String content;    //
      public int rownum;    //

}
