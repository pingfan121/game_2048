using System;
using System.Collections.Generic;
using System.Text;
using System.Collections;
using messages.Protocols;
using messages;
using GameLib.Util;
using hudie.net;
using System.Net;
using GameDb.Util;
using Enum;

namespace messages
{
    public delegate MsgBase MsgCreateFun();

    public delegate void MsgHandleFun(MsgBase msg);

    public partial class MsgHandle
    {
        static private MsgHandle _instance;

        public Dictionary<MsgCodeId, MsgBase> pros;
        public Dictionary<MsgCodeId, MsgHandleFun> profuns;

        private Queue<MsgBase> messageQueue = new Queue<MsgBase>();

        public MsgHandle()
        {
            pros = new Dictionary<MsgCodeId, MsgBase>();
            profuns = new Dictionary<MsgCodeId, MsgHandleFun>();
        }

        static public MsgHandle getInstance()
        {
            if (MsgHandle._instance == null)
                MsgHandle._instance = new MsgHandle();
            return MsgHandle._instance;
        }

        public void release()
        {
        }
        public void analysis(HttpListenerContext context, int codeId, string data)
        {
            try
            {
                MsgBase msg = pros[(MsgCodeId)codeId];


                msg = (MsgBase)JSON.Decode(data, msg.GetType());

                msg.context = context;

                if (msg != null)
                {
                    addMessage(msg);
                }

            }
            catch
            {
                Log.error("解析协议出错--" + codeId);

                NetHttp.sendErrorMsg(context,EnumMsgState.pro_analysis_err);
            }

        }


        public void addMessage(MsgBase mess)
        {
            lock (messageQueue)
            {
                if (messageQueue.Count < 100)
                {
                    messageQueue.Enqueue(mess);
                }
                else
                {
                    //消息队列放不下了....
                    Console.WriteLine("消息队列满了");
                }

            }
        }
        public MsgBase getMessage()
        {
            lock (messageQueue)
            {
                if (messageQueue.Count > 0)
                {
                    return messageQueue.Dequeue();
                }
                else
                {
                    return null;
                }
            }
        }

    }
}
