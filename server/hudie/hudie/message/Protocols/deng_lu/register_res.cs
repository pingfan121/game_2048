using messages;

namespace messages.Protocols
{
  public class register_res : MsgBase
  {
      public register_res()
      {
          CodeId = MsgCodeId.register_res;
      }
      public int state;    //

	}
}
