package pf.com.butterfly.message.Protocols;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;
import pf.com.butterfly.message.MsgBase;
import pf.com.butterfly.message.MsgCodeId;
  public class login_res extends MsgBase
  {
      public login_res()
      {
          CodeId = MsgCodeId.login_res;
      }
      public int state;    //

}
