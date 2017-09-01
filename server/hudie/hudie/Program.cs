using GameLib.Util;
using hudie.app;
using hudie.net;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace hudie
{
    class Program
    {
        static void Main(string[] args)
        {            
            try
            {
                //初始化消息序列
               NetHttp.Start();

               GlobalApp.init();

               Console.WriteLine("服务器开始运行了....");

                while(true)
                {
                    Thread.Sleep(60000);
                    OtherUpdate();
                }

            }
            catch(Exception ex)
            {
                Console.WriteLine(ex);
            }
           

        }

        public static void OtherUpdate()
        {
            long time = DateUtil.NowToToUnixTime2();
        }

    }
}
