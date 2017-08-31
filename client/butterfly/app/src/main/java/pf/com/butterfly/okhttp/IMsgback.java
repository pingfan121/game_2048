package pf.com.butterfly.okhttp;

import android.os.Message;

/**
 * Created by admin on 2017/2/28.
 */
public interface IMsgback
{
    void onMsgDispose(int err,String result,Object userToken);
}
