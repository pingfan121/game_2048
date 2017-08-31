package pf.com.butterfly.message.Protocols;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;
import pf.com.butterfly.message.MsgBase;
import pf.com.butterfly.message.MsgCodeId;
  public class benefit_detail_req extends MsgBase
  {
      public benefit_detail_req()
      {
          CodeId = MsgCodeId.benefit_detail_req;
      }
      public String themeid;    ////主题的id

}
