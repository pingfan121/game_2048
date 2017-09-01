using hudie.net;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace hudie.app
{
    public class GlobalApp
    {
        //app消息的派发

        private static int proc_count = 1;

        private static GameApp[] apps = new GameApp[proc_count];


        public static void init()
        {
            Thread thread = new Thread(run);
            thread.IsBackground = true;
            thread.Start();

            //消息映射...
         

            for(int i = 0; i < proc_count; i++)
            {
                apps[i] = new GameApp();

                apps[i].init();
            }
        }

        public static void run()
        {
            //去取出消息

            while(true)
            {
                HttpInfo info = null;

                if(NetHttp.http_data.TryDequeue(out info) == true)
                {
                    //消息检查和分流

                    if(true)
                    {
                        apps[0].addMsg(info);
                    }
                }

            }
        }
    }
}
