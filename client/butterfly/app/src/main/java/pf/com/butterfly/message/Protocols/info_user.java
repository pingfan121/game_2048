package pf.com.butterfly.message.Protocols;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;
import pf.com.butterfly.message.MsgBase;
import pf.com.butterfly.message.MsgCodeId;
  public class info_user extends MsgBase
  {
      public info_user()
      {
          CodeId = MsgCodeId.info_user;
      }
      public String name;    //
      public int sex;    //
      public String face;    //

}
