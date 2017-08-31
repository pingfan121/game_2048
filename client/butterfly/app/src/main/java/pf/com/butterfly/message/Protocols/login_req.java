package pf.com.butterfly.message.Protocols;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;
import pf.com.butterfly.message.MsgBase;
import pf.com.butterfly.message.MsgCodeId;
  public class login_req extends MsgBase
  {
      public login_req()
      {
          CodeId = MsgCodeId.login_req;
      }
      public String tel;    ////手机号
      public String pass;    ////密码

}
