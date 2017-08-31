package pf.com.butterfly.message.Protocols;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;
import pf.com.butterfly.message.MsgBase;
import pf.com.butterfly.message.MsgCodeId;
  public class benefit_head_res extends MsgBase
  {
      public benefit_head_res()
      {
          CodeId = MsgCodeId.benefit_head_res;
      }
      public info_benefit_head_item[] infos;    //

}
