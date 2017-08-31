using System.Collections;
using System.Collections.Generic;
using System;
using messages;
using hudie.net;
using System.Net;
using Newtonsoft.Json;
using GameLib.Database;
using hudie;


public class MsgBase
{
    public MsgCodeId CodeId;//可序列化的类的id

    public String token;

    [JsonIgnore]
    public HttpListenerContext context;

    [JsonIgnore]
    public IDbConnect db;

    [JsonIgnore]
    public GameApp app;
}

public class BackMsg
{
    public int error;   //处理状态
    public Object msg;
}

