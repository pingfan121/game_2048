package pf.com.butterfly.message.Protocols;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;
import pf.com.butterfly.message.MsgBase;
import pf.com.butterfly.message.MsgCodeId;
  public class login_weixin_res extends MsgBase
  {
      public login_weixin_res()
      {
          CodeId = MsgCodeId.login_weixin_res;
      }
      public int state;    //
      public info_user info;    //

}
