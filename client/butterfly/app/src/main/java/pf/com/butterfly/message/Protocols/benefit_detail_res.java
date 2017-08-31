package pf.com.butterfly.message.Protocols;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;
import pf.com.butterfly.message.MsgBase;
import pf.com.butterfly.message.MsgCodeId;
  public class benefit_detail_res extends MsgBase
  {
      public benefit_detail_res()
      {
          CodeId = MsgCodeId.benefit_detail_res;
      }
      public String themeid;    ////主题的id
      public String name;    ////被救助人的名字
      public String addr;    ////被救助人所在的地址
      public int needmoney;    //所需要救助的金额
      public int nowmoney;    ////现在一斤募集的金额
      public String detail;    ////详细介绍

}
