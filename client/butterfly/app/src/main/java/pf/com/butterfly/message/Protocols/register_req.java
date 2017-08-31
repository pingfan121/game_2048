package pf.com.butterfly.message.Protocols;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;
import pf.com.butterfly.message.MsgBase;
import pf.com.butterfly.message.MsgCodeId;
  public class register_req extends MsgBase
  {
      public register_req()
      {
          CodeId = MsgCodeId.register_req;
      }
      public String tel;    ////手机号
      public String nickname;    ////昵称
      public String pass;    ////密码

}
