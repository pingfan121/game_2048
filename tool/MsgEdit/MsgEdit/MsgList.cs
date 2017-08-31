using GameLib.Util;
using Newtonsoft.Json;
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
    //树节点
    public class tree_data
    {
        public bool isdir;  //是不是目录节点
        public string name;//节点名字
        [JsonIgnore]
        public tree_data prev_node;  //上一个节点
        public node_data data;
        public List<tree_data> nodes;
    }

    public class node_data
    {
        public List<info_data> req_params = new List<info_data>();
        public List<info_data> res_params = new List<info_data>();
        public string explain = "";
    }

    //返回定义
    public class info_data
    {
        public string param_name;
        public string param_type;
        public string param_explain;
    }

    //目录节点

    class MsgList
    {
        public static  TreeView tv;

        public static tree_data alldata = new tree_data();

        public static Stack<string> back_data = new Stack<string>();
        public static Stack<string> forward_data = new Stack<string>();

        public static void SetTreeView(TreeView treeview)
        {
            tv = treeview;

            tv.HideSelection = false;

            tv.DrawMode = TreeViewDrawMode.OwnerDrawText;
            tv.DrawNode += new DrawTreeNodeEventHandler(treeView1_DrawNode);

            tv.Font = new Font(tv.Font.FontFamily, tv.Font.Size + 2);
        }

        //在绘制节点事件中，按自已想的绘制
        private static void treeView1_DrawNode(object sender, DrawTreeNodeEventArgs e)
        {
            if((e.State & TreeNodeStates.Selected) != 0)
            {
                e.Graphics.FillRectangle(Brushes.DarkBlue, e.Node.Bounds);

                Font nodeFont = e.Node.NodeFont;
                if(nodeFont == null)
                    nodeFont = ((TreeView)sender).Font;
                e.Graphics.DrawString(e.Node.Text, nodeFont, Brushes.White, Rectangle.Inflate(e.Bounds, 2, 0));
            }
            else
            {
                e.DrawDefault = true;
            }

            if((e.State & TreeNodeStates.Focused) != 0)
            {
                using(Pen focusPen = new Pen(Color.Black))
                {
                    focusPen.DashStyle = System.Drawing.Drawing2D.DashStyle.Dot;
                    Rectangle focusBounds = e.Node.Bounds;
                    focusBounds.Size = new Size(focusBounds.Width,
                    focusBounds.Height);
                    e.Graphics.DrawRectangle(focusPen, focusBounds);
                }
            }

        }

        //添加目录节点
        public static void AddDirNode(string name)
        {
            //添加回退
            addBackData();

            TreeNode node = tv.SelectedNode;

            if(node.ImageIndex == 1)
            {
                node = node.Parent;
            }

            tree_data nodedata = new tree_data();

            nodedata.isdir = true;
            nodedata.name = name;

            tree_data datanode = gettreedata(node);

            if(datanode.nodes == null)
            {
                datanode.nodes = new List<tree_data>();
            }
            datanode.nodes.Add(nodedata);
            nodedata.prev_node = datanode;

            UpdateTree();
        }

        //添加文件节点
        public static void AddFileNode(string name)
        {
            //添加回退
            addBackData();

            TreeNode node = tv.SelectedNode;

            if(node.ImageIndex == 1)
            {
                node = node.Parent;
            }

            tree_data nodedata = new tree_data();

            nodedata.isdir = false;
            nodedata.name = name;
            nodedata.data = new node_data();

            tree_data datanode = gettreedata(node);

            if(datanode.nodes == null)
            {
                datanode.nodes = new List<tree_data>();
            }
            datanode.nodes.Add(nodedata);
            nodedata.prev_node = datanode;

            UpdateTree();

        }

        //删除一个节点
        public static void delNode()
        {
            TreeNode node = tv.SelectedNode;

            if(node == null)
                return;

            //添加回退
            addBackData();

            tree_data data_node = gettreedata(node);

            if(data_node != null)
            {
                if(data_node.prev_node != null)
                {
                    data_node.prev_node.nodes.Remove(data_node);
                    UpdateTree();
                }
            }
        }

        public static void SelectNodeChange(TreeNode node)
        {
            //变更展示界面
            tree_data treedata = gettreedata(node);

            if(treedata.isdir == false)
            {
                //设置数据
                InfoShow.SetMsgInfo(gettreepath(node), treedata);
            }
            else
            {
                InfoShow.SetMsgInfo("", null);
            }
        }
       

        

        //得到某个视图树节点的位置
        public static string gettreepath(TreeNode node)
        {
            string path=node.Text;

            TreeNode node2=node.Parent;

            while(node2 != null)
            {
                path = node2.Text +"\\"+ path;

                node2 = node2.Parent;
            }

            return path;
        }

        public static tree_data gettreedata(TreeNode node)
        {
            string path = gettreepath(node);

            string[] names = path.Split('\\');

            if(names.Length == 1)
            {
                return alldata;
            }

            List<tree_data> temp = alldata.nodes;

            int index = 1;

            while(temp != null)
            {
                foreach(var item in temp)
                {
                    if(item.name == names[index])
                    {
                        if(index == names.Length - 1)
                        {
                            return item;
                        }
                        else
                        {
                            temp = item.nodes;
                            index++;
                            break;
                        }
                    }
                }
            }

            return null;
        }

        public static TreeNode gettreenode(tree_data node)
        {

            string path = node.name;

            tree_data node2 = node.prev_node;

            while(node2 != null)
            {
                path = node2.name + "/" + path;

                node2 = node2.prev_node;
            }


            string[] names = path.Split('/');

          
            TreeNodeCollection temp = tv.Nodes;

            int index = 0;

            while(temp != null)
            {
                foreach(TreeNode item in temp)
                {
                    if(item.Text == names[index])
                    {
                        if(index == names.Length - 1)
                        {
                            return item;
                        }
                        else
                        {
                            temp = item.Nodes;
                            index++;
                            break;
                        }
                    }
                }
            }

            return null;
        }



        //更新树

        public static void UpdateTree()
        {
            tv.Nodes.Clear();

            TreeNode node = fillNode(alldata);

            tv.Nodes.Add(node);

            tv.HideSelection = false;

            WriteFileData();

            //获取显示
            if(InfoShow.treedata == null)
            {
                tv.ExpandAll();
            }
            else
            {
                tree_data temp=InfoShow.treedata.prev_node;

                if(temp==null)
                {
                     tv.ExpandAll();
                }

                foreach(var item in temp.nodes)
                {
                    if(item == InfoShow.treedata)
                    {
                        //找到树节点
                        tv.SelectedNode=gettreenode(item);
                        return;
                    }
                }

                tv.ExpandAll();
                InfoShow.SetMsgInfo("", null);

            }
        }

        public static TreeNode fillNode(tree_data nodedata)
        {
            TreeNode node = new TreeNode();

            node.Text = nodedata.name;

            if(nodedata.isdir == true)
            {
                node.ImageIndex = 0;
                node.SelectedImageIndex = 0;

                if(nodedata.nodes != null)
                {
                    foreach(var data in nodedata.nodes)
                    {
                        if(data.isdir == true)
                        {
                            TreeNode tempnode = fillNode(data);
                            node.Nodes.Add(tempnode);
                        }
                       
                    }
                    foreach(var data in nodedata.nodes)
                    {
                        if(data.isdir == false)
                        {
                            TreeNode tempnode = fillNode(data);
                            node.Nodes.Add(tempnode);
                        }

                    }
                }
            }
            else
            {
                node.ImageIndex = 1;
                node.SelectedImageIndex = 1;
            }

            return node;
        }


        //读取数据
        public static void ReadFileData()
        {
            if(File.Exists("data/data.txt"))
            {
                StreamReader sr = new StreamReader("data/data.txt");
                string str = sr.ReadToEnd();

                sr.Close();

                BuildTree(str);
            }
            else
            {
                BuildTree("");
            }

           
        }

        //写入协议数据
        public static void WriteFileData()
        {
            StreamWriter sw = new StreamWriter("data/data.txt", false);

            sw.Write(JSON.Encode(alldata));
            sw.Close();
        }

        public static void BuildTree(string data)
        {
           
            try
            {
                alldata = JSON.Decode<tree_data>(data);
            }
            catch(Exception ex)
            {
                alldata = new tree_data();
            }

            if(alldata.name != "...")
            {
                alldata.isdir = true;
                alldata.name = "...";
            }

            //设置树的上下级
            SetFather(alldata);
           
            UpdateTree();
        }

        public static void SetFather(tree_data data)
        {
            List<tree_data> temps = data.nodes;

            if(temps != null)
            {
                foreach(var node in temps)
                {
                    node.prev_node = data;
                    SetFather(node);
                }
            }
        }

        //导出协议文件
        public static void OutProtocolFile()
        {
            OutCsharp.OutFile(alldata);
            OutJava.OutFile(alldata);

            MessageBox.Show("导出成功");
        }

        //添加后退数据
        public static void addBackData()
        {
            back_data.Push(JSON.Encode(alldata));
        }

        //弹出后退数据
        public static string popBackData()
        {
            if(back_data.Count == 0)
            {
                return null;
            }

            return back_data.Pop();
        }

        //添加前进数据
        public static void addForwardData()
        {
            forward_data.Push(JSON.Encode(alldata));
        }

        //弹出前进数据
        public static string popForwardData()
        {
            if(forward_data.Count == 0)
            {
                return null;
            }

            return forward_data.Pop();
        }

    }
}
