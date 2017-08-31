package pf.com.butterfly.message.Protocols;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;
import pf.com.butterfly.message.MsgBase;
import pf.com.butterfly.message.MsgCodeId;
  public class advise_cteate_req extends MsgBase
  {
      public advise_cteate_req()
      {
          CodeId = MsgCodeId.advise_cteate_req;
      }
      public String userid;    //
      public String content;    //

}
