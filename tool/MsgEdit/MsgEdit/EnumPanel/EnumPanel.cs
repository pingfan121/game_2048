using GameLib.Util;
using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace MsgEdit
{
    //枚举类型结构
    public class EnumInfo
    {
        public int index;
        public string enumstr;
        public string explain;
    }

    //枚举结构
    public class EnumList
    {
        public string name;
        public List<EnumInfo> info=new List<EnumInfo>();
    }

    public class EnumPanel
    {
        public static ListBox lb_enumlist;
        public static ListView lv_showenum;

        private static List<EnumList> infos = new List<EnumList>();
        
        //添加一个新枚举
        public static void addOneEnum(string name)
        {
            if(hasOneEnum(name)==true)
            {
                 return;
            }

            EnumList info=new EnumList();
            
            info.name=name;

            infos.Add(info);
            
            //更新
            updateList();

            int index = getIndex(name);

            //设置选中新的
            lb_enumlist.SelectedItems.Clear();
            lb_enumlist.SetSelected(index, true);

        }
        //添加一个新枚举
        public static void subOneEnum(string name)
        {
            if(hasOneEnum(name) == false)
            {
                return;
            }

            foreach(var item in infos)
            {
                if(item.name == name)
                {
                    infos.Remove(item);
                    break;
                }
            }

            //更新
            updateList();

            if(infos.Count > 0)
            {
                //设置选中新的
                lb_enumlist.SelectedItems.Clear();
                lb_enumlist.SetSelected(0, true);
            }
          

        }

        //是否已经存在一个枚举
        public static bool hasOneEnum(string name)
        {
            foreach(var item in infos)
            {
                if(item.name == name)
                    return true;
            }
            return false;
        }

        //添加一个枚举类型
        public static void addOneType(string name, EnumInfo info)
        {
            if(hasOneType(name, info) == true)
            {
                return;
            }

            foreach(var item in infos)
            {
                if(item.name == name)
                {
                    item.info.Add(info);
                    //刷新内容
                    updateContext(item);
                    return;
                }
            }

           
        }

        //移除一个枚举类型
        public static void subOneType(string name,string enumstr)
        {
            foreach(var item in infos)
            {
                if(item.name == name)
                {

                    foreach(var temp in item.info)
                    {
                        if(temp.enumstr == enumstr)
                        {
                            item.info.Remove(temp);
                            //刷新内容
                            updateContext(item);
                            return;
                        }
                    }
                   
                }
            }

        }

        public static EnumInfo getOneType(string name, string enumstr)
        {
            foreach(var item in infos)
            {
                if(item.name == name)
                {
                    foreach(var temp in item.info)
                    {
                        if(temp.enumstr == enumstr)
                        {
                            return temp;
                        }
                    }

                }
            }
            return null;
        }

        //是否已经存在一个枚举
        public static bool hasOneType(string name, EnumInfo info)
        {

            foreach(var item in infos)
            {
                if(item.name == name)
                {
                    foreach(var temp in item.info)
                    {
                        if(temp.index == info.index)
                        {
                            return true;
                        }

                        if(temp.enumstr == info.enumstr)
                        {
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        //是不是可以修改
        public static bool canModifyType(string name,EnumInfo oldinfo,EnumInfo newinfo)
        {
            foreach(var item in infos)
            {
                if(item.name == name)
                {
                    foreach(var temp in item.info)
                    {
                        if(temp == oldinfo)
                        {
                            continue;
                        }

                        if(temp.index == newinfo.index)
                        {
                            return false;
                        }

                        if(temp.enumstr == newinfo.enumstr)
                        {
                            return false;
                        }
                    }
                }
            }

            return true;
        }

        public static int getIndex(string name)
        {
            int index = 0;

            foreach(var item in infos)
            {
                if(item.name == name)
                {
                    return index;
                }
                index++;

            }
            return index;
        }



        public static void updateList()
        {
            infos.Sort(ListSort);

            lb_enumlist.Items.Clear();

            foreach(var item in infos)
            {
                lb_enumlist.Items.Add(item.name);
            }

            WriteFileData();
        }

        public static void updateContext(EnumList info)
        {
            lv_showenum.Items.Clear();

            Form1.btn.Visible=false;
            Form1.btn2.Visible = false;

            info.info.Sort(ListSort2);

            foreach(var item in info.info)
            {
                ListViewItem lv_item = new ListViewItem(item.index.ToString());
                lv_item.SubItems.Add(item.enumstr);
                lv_item.SubItems.Add(item.explain);
                lv_item.SubItems.Add("");
                lv_item.SubItems.Add("");

                lv_showenum.Items.Add(lv_item);


            }

            WriteFileData();
        }

        public static void SelectChange()
        {

            if(lb_enumlist.SelectedItem != null)
            {
                string name = lb_enumlist.SelectedItem.ToString();

                foreach(var item in infos)
                {
                    if(item.name == name)
                    {
                        //设置视图

                        updateContext(item);
                    }
                }
            }
          
        }

        public static string getCurrSelect()
        {
            if(lb_enumlist.SelectedItem == null)
            {
                return "";
            }
            return lb_enumlist.SelectedItem.ToString();
        }

        private static int ListSort(EnumList data1, EnumList data2)
        {
            return string.Compare(data1.name, data2.name);
        }

        private static int ListSort2(EnumInfo data1, EnumInfo data2)
        {
            return data1.index > data2.index ? 1 : -1;
        }

        //读取数据
        public static void ReadFileData()
        {
            if(File.Exists("data/enum.txt"))
            {
                StreamReader sr = new StreamReader("data/enum.txt");
                string str = sr.ReadToEnd();

                try
                {
                    infos = JSON.Decode<List<EnumList>>(str);
                }
                catch(Exception ex)
                {
                    infos = new List<EnumList>();
                }


                sr.Close();

                updateList();
            }
        }

        //写入
        public static void WriteFileData()
        {
            StreamWriter sw = new StreamWriter("data/enum.txt", false);

            sw.Write(JSON.Encode(infos));
            sw.Close();
        }


        public static void OutEnumFile()
        {
            OutEnum.OutEnumFile(infos);
        }

      
    }
}
