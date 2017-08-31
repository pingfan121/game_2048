using GameLib.Util;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Configuration;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace MsgEdit
{
    class OutJava
    {
        public static int type = 1;

        public static string path = "";

        public static void getPath()
        {
            string str2 = Application.ExecutablePath;

            int index = str2.IndexOf("tool");
            //截取
            string str3 = str2.Substring(0, index);

            path = str3 + @"client\butterfly\app\src\main\java\pf\com\butterfly\infofile\";

            if(Directory.Exists(path) == false)
            {
                Directory.CreateDirectory(path);
            }

        }
        //导出文件
        public static void OutFile(tree_data treedata)
        {
            getPath();

            foreach(var item in treedata.nodes)
            {
                buildMsgFile(item);
            }

        }


        //生成消息文件
        private static void buildMsgFile(tree_data treedata)
        {
            if(treedata.isdir == true)
            {
                if(treedata.nodes != null)
                {
                    foreach(var node in treedata.nodes)
                    {
                        buildMsgFile(node);
                    }
                }

                return;
            }

            string classname = "";

            if(treedata.prev_node.name != "info")
            {
                classname = "res_" + treedata.prev_node.name + "_" + treedata.name;
            }
            else
            {
                classname = treedata.name;
            }

            string filepath = path + classname + ".java";

            //打开文件
            StreamWriter sw = new StreamWriter(filepath, false);

            sw.WriteLine("package pf.com.butterfly.infofile;");
            sw.WriteLine("");

            sw.WriteLine("public class " + classname);
            sw.WriteLine("{");

            foreach(var temp in treedata.data.res_params)
            {
                string tt = temp.param_type.Replace("string", "String");

                sw.WriteLine("\tpublic " + (temp.param_type=="string"?"String":temp.param_type) + " " + temp.param_name + ";//" + temp.param_explain);
            }

            sw.WriteLine("}");

            sw.Close();
        }
    }





}
