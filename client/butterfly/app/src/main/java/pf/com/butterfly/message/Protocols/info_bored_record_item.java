package pf.com.butterfly.message.Protocols;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;
import pf.com.butterfly.message.MsgBase;
import pf.com.butterfly.message.MsgCodeId;
  public class info_bored_record_item extends MsgBase
  {
      public info_bored_record_item()
      {
          CodeId = MsgCodeId.info_bored_record_item;
      }
      public String id;    //
      public String headid;    //
      public String userid;    //
      public String recordurl;    //
      public int recordlength;    //

}
