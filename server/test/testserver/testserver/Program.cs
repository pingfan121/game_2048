using GameLib.Util;
using messages.Protocols;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace testserver
{
    class Program
    {
        private static string url="http://192.168.1.16:10012";
        static void Main(string[] args)
        {
           // Console.WriteLine(long.MaxValue);

            while(true)
            {
                try
                {
                    string str = Console.ReadLine();

                    string[] sss = str.Split(' ');

                    if(sss.Length == 3)
                    {
                         sendMsg(sss[0], sss[1], sss[2],false);
                    }
                    else
                    {
                         sendMsg(sss[0], sss[1], sss[2],true);
                    }

                  
                }
                catch(Exception ex)
                {
                    Console.WriteLine(ex);
                }
               
            }

        }

        private static string sendMsg(string modulename,string funname,string param,bool flag)
        {
            WebClient web = new WebClient();

            byte[] bt = null;

            if(false == flag)
            {
                web.Encoding = Encoding.UTF8;
                bt = web.UploadData(url + "/app/module/"+modulename+"/"+funname+"?"+param, "post", new byte[0]);
            }
            else
            {
                 byte[] data = Encoding.UTF8.GetBytes(param);

                
                 bt = web.UploadData(url + "/app/module/"+modulename+"/"+funname, "post", data);
            }

            string str = System.Text.Encoding.UTF8.GetString(bt);

            Console.WriteLine(str);


            return str;
        }
    }
}
