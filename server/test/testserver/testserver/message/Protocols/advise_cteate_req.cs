using messages;

namespace messages.Protocols
{
  public class advise_cteate_req : MsgBase
  {
      public advise_cteate_req()
      {
          CodeId = MsgCodeId.advise_cteate_req;
      }
      public string userid;    //
      public string content;    //

	}
}
