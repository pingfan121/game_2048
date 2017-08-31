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
    class OutCsharp
    {
        public static int type = 1;

        public static string path = "";

        public static void getPath()
        {
            string str2=Application.ExecutablePath;

            int index=str2.IndexOf("tool");
            //截取
            string str3=str2.Substring(0,index);

            path=str3+"server\\hudie\\hudie\\";

        }
        //导出文件
        public static void OutFile(tree_data treedata)
        {
            getPath();

            //生成代码文件

            foreach(var item in treedata.nodes)
            {
                buildCodeFile(item);
            }

            //生成映射文件
            foreach(var item in treedata.nodes)
            {
                buildMapFile(item);
            }

            //生成协议文件
            string filepath = path + "app\\info\\";

            if(Directory.Exists(filepath) == false)
            {
                Directory.CreateDirectory(filepath);
            }

            foreach(var item in treedata.nodes)
            {
                buildMsgFile(item);
            }


           
        }

          //生成代码文件
          private static void buildCodeFile(tree_data treedata)
          {

              if(treedata.name == "info")
                  return;

              string filepath = path + getLuYouPath(treedata);

              if(treedata.isdir)
              {
                  if(Directory.Exists(filepath) == false)
                  {
                      Directory.CreateDirectory(filepath);
                  }
              }
              else
              {
                  if(File.Exists(filepath) == false)
                  {
                      //修改文件
                      FillCodeFile(treedata,filepath);
                  }
              }

              if(treedata.nodes != null)
              {
                  foreach(var item in treedata.nodes)
                  {
                      buildCodeFile(item);
                  }
              }
             
          }
        

          private static void FillCodeFile(tree_data treedata, string filepath)
          {
              //打开文件
              StreamWriter sw = new StreamWriter(filepath, false, Encoding.UTF8);

              sw.WriteLine("using hudie.net;");
              sw.WriteLine("using System;");
              sw.WriteLine("using System.Collections.Generic;");
              sw.WriteLine("using System.Linq;");
              sw.WriteLine("using System.Text;");
              sw.WriteLine("using GameServer.Define.EnumNormal;");
              sw.WriteLine(" using GameDb.Util;");
              sw.WriteLine("");
             

              sw.WriteLine("namespace hudie."+getLuYouPath(treedata.prev_node.prev_node).Replace('\\','.'));
              sw.WriteLine("{");
              sw.WriteLine("\tpublic partial class "+treedata.prev_node.name);
              sw.WriteLine("\t{");
              sw.WriteLine("\t\tpublic void " + treedata.name + "(HttpInfo reqinfo)");
              sw.WriteLine("\t\t{");
              sw.WriteLine("\t\t");
              sw.WriteLine("\t\t}");
              sw.WriteLine("\t}");
              sw.WriteLine("}");

            
              sw.Close();

              //生成一下上层类文件
              buildFatherFile(treedata);
          }

          private static void buildFatherFile(tree_data treedata)
          {
              tree_data father_data = treedata.prev_node;

              //打开文件
              string fatherpath = path + getLuYouPath(father_data);

              fatherpath += "\\"+father_data.name+".cs";

              if(File.Exists(fatherpath) == true)
              {
                  return;
              }

              

              StreamWriter sw = new StreamWriter(fatherpath, false, Encoding.UTF8);

              sw.WriteLine("using hudie.net;");
              sw.WriteLine("using System;");
              sw.WriteLine("using System.Collections.Generic;");
              sw.WriteLine("using System.Linq;");
              sw.WriteLine("using System.Text;");
              sw.WriteLine("using GameServer.Define.EnumNormal;");
              sw.WriteLine(" using GameDb.Util;");
              sw.WriteLine("");


              sw.WriteLine("namespace hudie." + getLuYouPath(father_data.prev_node).Replace('\\', '.'));
              sw.WriteLine("{");
              sw.WriteLine("\tpublic partial class " + father_data.name + " : ModuleBase");
              sw.WriteLine("\t{");
              sw.WriteLine("\t\tpublic " + father_data.name + "(GameApp app)");
              sw.WriteLine("\t\t{");
              sw.WriteLine("\t\t\tthis.app = app;");
              sw.WriteLine("\t\t}");
              sw.WriteLine("\t}");
              sw.WriteLine("}");


              sw.Close();
          }


          //生成映射文件
          private static void buildMapFile(tree_data treedata)
          {

              string filepath = path + "app\\MapAppMsg2.cs";

              Dictionary<string,string> dic1 = new Dictionary<string,string>();
              Dictionary<string, string> dic2 = new Dictionary<string, string>();

              Dictionary<string, List<string>> dic3 = new Dictionary<string, List<string>>();

              FillMapFile(treedata, dic1, dic2);

              FillReqFile(treedata, dic3);

              //然后填写

              //打开文件
              StreamWriter sw = new StreamWriter(filepath, false, Encoding.UTF8);

              sw.WriteLine("using hudie.net;");
              sw.WriteLine("using System;");
              sw.WriteLine("using System.Collections.Generic;");
              sw.WriteLine("using System.Linq;");
              sw.WriteLine("using System.Text;");
              sw.WriteLine("using GameServer.Define.EnumNormal;");
              sw.WriteLine("");

              sw.WriteLine("namespace hudie.app");
              sw.WriteLine("{");
              sw.WriteLine("\tpublic partial class MapAppMsg");
              sw.WriteLine("\t{");

              sw.WriteLine("\t\tprivate void init_class_map(GameApp app)");
              sw.WriteLine("\t\t{");
              foreach(var item in dic1.Values)
              {
                  sw.WriteLine("\t\t\t"+item);
              }
            //  sw.WriteLine("\t\t");
              sw.WriteLine("\t\t}");

              //消息映射函数
              sw.WriteLine("\t\tprivate void init_msg_map()");
              sw.WriteLine("\t\t{");
              foreach(var item in dic2.Values)
              {
                  sw.WriteLine("\t\t\t" + item);
              }
              //  sw.WriteLine("\t\t");
              sw.WriteLine("\t\t}");


              sw.WriteLine("\t\tprivate void init_req_map()");
              sw.WriteLine("\t\t{");
              foreach(var item in dic3)
              {
                  sw.WriteLine("\t\t\t" + "req_map.Add(\"" + item.Key + "\",new List<string>());");
              }
              foreach(var item in dic3)
              {
                  foreach(var temp in item.Value)
                  {
                      sw.WriteLine("\t\t\t" + "req_map[\"" + item.Key + "\"].Add(\"" + temp + "\");");
                  }
              }
             
              sw.WriteLine("\t\t}");


              sw.WriteLine("\t}");
              sw.WriteLine("}");

              sw.Close();
          }

          private static void FillMapFile(tree_data treedata, Dictionary<string, string> dic1, Dictionary<string, string> dic2)
          {

              if(treedata.name == "info")
                  return;

              if(treedata.isdir == false)
              {
                  string temp = getLuYouPath2(treedata.prev_node).Replace('\\', '.');
                  string temp2 = getLuYouPath2(treedata).Replace('\\', '.');

                  dic1["hudie." + temp] = getclassname1(temp);

                  dic2["hudie." + temp2] = getclassname2(temp, temp2, treedata.name);
              }

              if(treedata.nodes != null)
              {
                  foreach(var node in treedata.nodes)
                  {
                      FillMapFile(node, dic1, dic2);
                  }
              }
             
          }
          private static void FillReqFile(tree_data treedata, Dictionary<string, List<string>> dic1)
          {

              if(treedata.name == "info")
                  return;

              if(treedata.isdir == false)
              {
                  string temp2 = getLuYouPath2(treedata).Replace('\\', '.');

                  if(treedata.data.req_params!=null && treedata.data.req_params.Count!=0)
                  {
                      dic1["hudie." + temp2] = new List<string>();

                  
                      foreach(var temp in treedata.data.req_params)
                      {
                          dic1["hudie." + temp2].Add(temp.param_name);
                      }
                  }
                
              }

              if(treedata.nodes != null)
              {
                  foreach(var node in treedata.nodes)
                  {
                      FillReqFile(node, dic1);
                  }
              }

          }
        private static string getclassname1(string temp)
        {
            return "class_map.Add(\"hudie.\"+\"" + temp + "\", new " + temp + "(app));";
        }
        private static string getclassname2(string temp1,string temp2,string name)
        {
            return "msg_map.Add(\"hudie.\"+\""+temp2+"\", (("+temp1+")class_map[\"hudie." + temp1+"\"])."+name+");";
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

              string filepath = path + "app\\info\\";

            string classname="";

            if(treedata.prev_node.name!="info")
            {
                 classname="res_"+treedata.prev_node.name+"_"+treedata.name;
            }
            else
            {
                classname=treedata.name;
            }

            filepath+=classname+".cs";

            //打开文件
            StreamWriter sw = new StreamWriter(filepath, false, Encoding.UTF8);

           
            sw.WriteLine("using System;");
            sw.WriteLine("using System.Collections.Generic;");
            sw.WriteLine("using System.Linq;");
            sw.WriteLine("using System.Text;");
            sw.WriteLine("");

            sw.WriteLine("namespace hudie.app.info");
            sw.WriteLine("{");
            sw.WriteLine("\tpublic partial class "+classname);
            sw.WriteLine("\t{");

            foreach(var temp in treedata.data.res_params)
            {
                sw.WriteLine("\t\tpublic "+temp.param_type+" "+temp.param_name+";//"+temp.param_explain);
            }
        
            sw.WriteLine("\t}");
            sw.WriteLine("}");

            sw.Close();
        }



        //private static void CreateCodeID(List<DirectoryData> protos)
        //{
        //    List<string> ids = new List<string>();

        //    int index = 1;

        //    foreach(DirectoryData dir in protos)
        //    {
        //        foreach(msgdata data in dir.protos)
        //        {
        //            ids.Add(data.name + " = " + index + ",");
        //            index++;
        //        }
        //    }

        //    string path = GetSetverPath();
        //    //写入文件
        //    if(!Directory.Exists(path + "\\message\\"))
        //    {
        //        Directory.CreateDirectory(path + "\\message\\");
        //    }

        //    StreamWriter sw = new StreamWriter(path + "\\message\\MsgCodeId.cs", false, Encoding.Unicode);

        //    //   sw.WriteLine("using GameServer.Define.EnumNormal;");
        //    //   sw.WriteLine("");
        //    sw.WriteLine("namespace messages");
        //    sw.WriteLine("{");
        //    sw.WriteLine("\tpublic enum MsgCodeId");
        //    sw.WriteLine("\t{");

        //    foreach(string id in ids)
        //    {
        //        sw.WriteLine("\t\t" + id);
        //    }
        //    sw.WriteLine("\t}");
        //    sw.WriteLine("}");

        //    sw.Close();
        //}

        //private static void CreateProtoFiles(List<DirectoryData> protos)
        //{
        //    foreach(DirectoryData dir in protos)
        //    {
        //        foreach(msgdata data in dir.protos)
        //        {
        //            CreateProtoFiles2(data, dir.dic_name);
        //        }
        //    }
        //}

        //private static void CreateProtoFiles2(msgdata data, string dir_name)
        //{
        //    //分割属性

        //    string str = data.content.Replace("\r\n", "|");
        //    string[] attrstxt = str.Split('|');


        //    //属性
        //    List<string> attrs = new List<string>();
        //    List<string> sets = new List<string>();


        //    bool flag1 = false;
        //    bool flag2 = false;

        //    foreach(string attr in attrstxt)
        //    {
        //        string[] fenge = attr.Split(';');

        //        string[] temp2 = fenge[0].Split(' ');


        //        if(temp2[0] == "required")  //必须的
        //        {
        //            //属性
        //            attrs.Add("public " + temp2[1] + " " + temp2[2] + ";    //" + (fenge.Length == 2 ? fenge[1] : ""));
        //        }
        //        else if(temp2[0] == "array")  //重复的
        //        {
        //            //属性
        //            attrs.Add("public " + temp2[1] + "[] " + temp2[2] + ";    //" + (fenge.Length == 2 ? fenge[1] : ""));
        //        }
        //    }

        //    string path = GetSetverPath();

        //    string path2 = "\\message\\Protocols\\";


        //    if(!Directory.Exists(path + path2))
        //    {
        //        Directory.CreateDirectory(path + path2);
        //    }

        //    path2 += dir_name + "\\";

        //    if(!Directory.Exists(path + path2))
        //    {
        //        Directory.CreateDirectory(path + path2);
        //    }

        //    StreamWriter sw = new StreamWriter(path + path2 + data.name + ".cs", false, Encoding.Unicode);

        //    //使用命名空间
        //    sw.WriteLine("using messages;");
        //    sw.WriteLine("");

        //    //定义类
        //    sw.WriteLine("namespace messages.Protocols");
        //    sw.WriteLine("{");
        //    sw.WriteLine("  public class " + data.name + " : MsgBase");
        //    sw.WriteLine("  {");

        //    //构造函数
        //    sw.WriteLine("      public " + data.name + "()");
        //    sw.WriteLine("      {");
        //    sw.WriteLine("          CodeId = MsgCodeId." + data.name + ";");
        //    sw.WriteLine("      }");

        //    //写入属性

        //    foreach(string ss in attrs)
        //    {
        //        sw.WriteLine("      " + ss);
        //    }

        //    sw.WriteLine("");


        //    sw.WriteLine("\t}");
        //    sw.WriteLine("}");

        //    sw.Close();
        //}

        //private static void CreateProtoReq(List<DirectoryData> protos)
        //{
        //    foreach(DirectoryData dir in protos)
        //    {

        //        foreach(msgdata data in dir.protos)
        //        {
        //            if(data.name.IndexOf("_req") != -1)
        //            {
        //                CreateProtoReq2(data, dir.dic_name);
        //            }

        //        }
        //    }
        //}
        //private static void CreateProtoReq2(msgdata data, string dir_name)
        //{
        //    string path = GetSetverPath();

        //    string path2 = "\\message\\ProtocolsReq\\";


        //    if(!Directory.Exists(path + path2))
        //    {
        //        Directory.CreateDirectory(path + path2);
        //    }

        //    path2 += dir_name + "\\";

        //    if(!Directory.Exists(path + path2))
        //    {
        //        Directory.CreateDirectory(path + path2);
        //    }

        //    if(File.Exists(path + path2 + data.name + "_fun.cs"))
        //    {
        //        return;
        //    }


        //    StreamWriter sw = new StreamWriter(path + path2 + data.name + "_fun.cs", false, Encoding.Unicode);
        //    //使用命名空间
        //    sw.WriteLine("using GameDb.Logic;");
        //    sw.WriteLine("using GameDb.Util;");
        //    sw.WriteLine("using GameLib.Database;");
        //    sw.WriteLine("using messages;");
        //    sw.WriteLine("using System;");
        //    sw.WriteLine("using messages.Protocols;");

        //    sw.WriteLine("");

        //    //定义类
        //    sw.WriteLine("namespace messages");
        //    sw.WriteLine("{");
        //    sw.WriteLine("  public partial class MsgHandle");
        //    sw.WriteLine("  {");

        //    sw.WriteLine("      static public void handle_" + data.name + "(MsgBase msg)");
        //    sw.WriteLine("      {");
        //    sw.WriteLine("      ");
        //    sw.WriteLine("      }");

        //    sw.WriteLine("  }");
        //    sw.WriteLine("}");
        //    sw.Close();



        //}

        //private static void CreateprotoMapFile(List<DirectoryData> protos)
        //{
        //    string path = GetSetverPath();
        //    StreamWriter sw = new StreamWriter(path + "\\message\\MsgProcessor.cs", false, Encoding.Unicode);
        //    //使用命名空间
        //    sw.WriteLine("using messages;");
        //    sw.WriteLine("using messages.Protocols;");
        //    sw.WriteLine("using System.Collections.Generic;");
        //    sw.WriteLine("");

        //    //定义类
        //    sw.WriteLine("namespace messages");
        //    sw.WriteLine("{");
        //    //     sw.WriteLine("  public delegate void MsgHandleFun(MsgBase msg);");
        //    sw.WriteLine("");
        //    sw.WriteLine("  public partial class MsgHandle");
        //    sw.WriteLine("  {");

        //    //获取函数
        //    sw.WriteLine("       public void init()");
        //    sw.WriteLine("      {");

        //    foreach(DirectoryData dir in protos)
        //    {
        //        foreach(msgdata data in dir.protos)
        //        {

        //            if(data.name.IndexOf("_req") != -1)
        //                sw.WriteLine("           pros.Add(MsgCodeId." + data.name + ",new " + data.name + "());");
        //        }
        //    }

        //    foreach(DirectoryData dir in protos)
        //    {
        //        foreach(msgdata data in dir.protos)
        //        {

        //            if(data.name.IndexOf("_req") != -1)
        //                sw.WriteLine("           profuns.Add(MsgCodeId." + data.name + ",handle_" + data.name + ");");
        //        }
        //    }

        //    sw.WriteLine("      }");
        //    sw.WriteLine("  }");
        //    sw.WriteLine("}");
        //    sw.Close();

        //}

        private static string GetSetverPath()
        {
            if(type == 1)
            {
                Configuration config = ConfigurationManager.OpenExeConfiguration(ConfigurationUserLevel.None);

                return config.AppSettings.Settings["serverpath"].Value;
            }
            else
            {
                Configuration config = ConfigurationManager.OpenExeConfiguration(ConfigurationUserLevel.None);

                return config.AppSettings.Settings["servertestpath"].Value;
            }

        }

        public static string HanZiZhuanPinYin(string name)
        {
            WebClient web = new WebClient();

            string showapi_appid = "35119";
            string showapi_sign = "996ddf1386994f73b05292db6099f91e";
            string content = name;

            string str = String.Format("http://route.showapi.com/99-38?showapi_appid={0}&content={1}&showapi_sign={2}", showapi_appid, content, showapi_sign);

            byte[] bt = web.DownloadData(str);

            str = System.Text.Encoding.UTF8.GetString(bt);

            Hashtable ht = JSON.Decode<Hashtable>(str);

            string temp = ht["showapi_res_body"].ToString();

            ht = JSON.Decode<Hashtable>(temp);

            string py = ht["data"].ToString();

            py = py.Replace(' ', '_');

            return py;
        }

        public static string getLuYouPath(tree_data node)
        {

          
            string path = node.name;

            if(node.isdir == false)
            {
                path = node.prev_node.name + "_" + node.name+".cs";
            }

            tree_data node2 = node.prev_node;

            while(node2 != null && node2.prev_node != null)
            {
                path = node2.name + "\\" + path;

                node2 = node2.prev_node;
            }
            return path;
        }

        public static string getLuYouPath2(tree_data node)
        {


            string path = node.name;

            tree_data node2 = node.prev_node;

            while(node2 != null && node2.prev_node != null)
            {
                path = node2.name + "\\" + path;

                node2 = node2.prev_node;
            }
            return path;
        }

    }

    



}
