package pf.com.butterfly.message.Protocols;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;
import pf.com.butterfly.message.MsgBase;
import pf.com.butterfly.message.MsgCodeId;
  public class login_weixin_req extends MsgBase
  {
      public login_weixin_req()
      {
          CodeId = MsgCodeId.login_weixin_req;
      }
      public String acc_token;    //

}
