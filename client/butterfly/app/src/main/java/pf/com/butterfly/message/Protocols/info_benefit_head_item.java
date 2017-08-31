package pf.com.butterfly.message.Protocols;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;
import pf.com.butterfly.message.MsgBase;
import pf.com.butterfly.message.MsgCodeId;
  public class info_benefit_head_item extends MsgBase
  {
      public info_benefit_head_item()
      {
          CodeId = MsgCodeId.info_benefit_head_item;
      }
      public String itemid;    ////主题的id
      public String name;    ////被救助人的名字
      public String addr;    ////被救助人所在的地址
      public int needmoney;    //所需要救助的金额
      public int nowmoney;    ////现在已经募集的金额

}
