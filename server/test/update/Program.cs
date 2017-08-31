using GameLib.Util;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace update
{
    public class UpdataInfo
    {
        public string version;  //版本号
        public string apkurl;  //下载地址
        public string explain;  //说明
    }
    class Program
    {
        static void Main(string[] args)
        {
            UpdataInfo info = new UpdataInfo();

            info.version = "2.2.3.23";
            info.apkurl = "192.168.0.88:8080/app/hudie.apk";
            info.explain = "这是一个版本说明\n真的是一个说明";

            StreamWriter writer = new StreamWriter("D:/UpdataInfo.json", false);

            writer.Write(JSON.Encode(info,Formatting.Indented));

            writer.Close();
        }
    }
}
