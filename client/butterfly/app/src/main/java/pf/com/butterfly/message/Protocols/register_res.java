package pf.com.butterfly.message.Protocols;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;
import pf.com.butterfly.message.MsgBase;
import pf.com.butterfly.message.MsgCodeId;
  public class register_res extends MsgBase
  {
      public register_res()
      {
          CodeId = MsgCodeId.register_res;
      }
      public int state;    //

}
