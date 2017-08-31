using System;
using System.Collections.Generic;
using System.Configuration;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MsgEdit
{
    public class OutEnum
    {
        public static void OutEnumFile(List<EnumList> list)
        {

            //----------------导出服务端的枚举文件---------------------

            string path = GetServerPath();


            if(Directory.Exists(path+"\\enum\\")==false)
            {
                Directory.CreateDirectory(path + "\\enum\\");
            }

            foreach(var item in list)
            {
                CreateServerEnumFile(path,item);
            }

            //----------------导出android端的枚举文件---------------------
            path = GetAndroidPath();


            if(Directory.Exists(path + "\\enumfile\\") == false)
            {
                Directory.CreateDirectory(path + "\\enumfile\\");
            }

            foreach(var item in list)
            {
                CreateAndroidEnumFile(path,item);
            }

        }

        private static void CreateServerEnumFile(string path,EnumList info)
        {
            StreamWriter sw = new StreamWriter(path + "\\enum\\" + info.name + ".cs", false, Encoding.Unicode);

            //使用命名空间
         //   sw.WriteLine("using messages;");
         //   sw.WriteLine("");

            //定义类
            sw.WriteLine("namespace Enum");
            sw.WriteLine("{");
            sw.WriteLine("  public enum " + info.name);
            sw.WriteLine("  {");

            foreach(var item in info.info)
            {
                sw.WriteLine("     "+item.enumstr+" = "+item.index+", //"+item.explain);
            }

            sw.WriteLine("  }");
            sw.WriteLine("}");

            sw.Close();

         
        }

        private static void CreateAndroidEnumFile(string path,EnumList info)
        {
            StreamWriter sw = new StreamWriter(path + "\\enumfile\\" + info.name + ".java", false);

            sw.WriteLine("package pf.com.butterfly.enumfile;");
            sw.WriteLine("");

            sw.WriteLine("public class " + info.name);
            sw.WriteLine("{");

            foreach(var item in info.info)
            {
                sw.WriteLine("   public static final int " + item.enumstr + " = " + item.index + "; //" + item.explain);
            }

            sw.WriteLine("}");

            sw.Close();


        }

        private static string GetServerPath()
        {
            Configuration config = ConfigurationManager.OpenExeConfiguration(ConfigurationUserLevel.None);

            return config.AppSettings.Settings["serverpath"].Value;
        }

        private static string GetAndroidPath()
        {
            Configuration config = ConfigurationManager.OpenExeConfiguration(ConfigurationUserLevel.None);

            return config.AppSettings.Settings["clientpath"].Value;
        }
    }
}
