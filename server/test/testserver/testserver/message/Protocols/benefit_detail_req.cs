using messages;

namespace messages.Protocols
{
  public class benefit_detail_req : MsgBase
  {
      public benefit_detail_req()
      {
          CodeId = MsgCodeId.benefit_detail_req;
      }
      public string themeid;    ////主题的id

	}
}
