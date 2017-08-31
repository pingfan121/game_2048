package pf.com.butterfly.message.net;

import pf.com.butterfly.message.MsgBase;

/**
 * Created by admin on 2017/6/15.
 */

public interface IMsgResult
{
    void onError(int err,String msg);

    void onResult(MsgBase msg);
}
