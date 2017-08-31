using System.Collections;
using System.Collections.Generic;
using System;
using messages;
using System.Net;


public class MsgBase
{
    public MsgCodeId CodeId;//可序列化的类的id
}

public class BackMsg
{
    public int state;   //处理状态
    public int msgid;
    public Object msg;
}

