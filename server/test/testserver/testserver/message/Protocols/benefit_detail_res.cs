using messages;

namespace messages.Protocols
{
  public class benefit_detail_res : MsgBase
  {
      public benefit_detail_res()
      {
          CodeId = MsgCodeId.benefit_detail_res;
      }
      public string themeid;    ////主题的id
      public string name;    ////被救助人的名字
      public string addr;    ////被救助人所在的地址
      public int needmoney;    //所需要救助的金额
      public int nowmoney;    ////现在一斤募集的金额
      public string detail;    ////详细介绍

	}
}
