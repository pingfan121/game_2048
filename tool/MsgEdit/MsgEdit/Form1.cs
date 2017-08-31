using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace MsgEdit
{
    public partial class Form1 : Form
    {

        public static Button btn;
        public static Button btn2;

       
        public Form1()
        {
            InitializeComponent();

            if(!Directory.Exists("data/"))
            {
                Directory.CreateDirectory("data/");
            }

            //------------------协议界面---------------------
             ImageList il = new ImageList();

             il.Images.Add(Image.FromFile("ico1.png"));
             il.Images.Add(Image.FromFile("ico2.png"));
             
             treeView1.ImageList = il;
             treeView1.AfterSelect += OnNodeChange;

             MsgList.SetTreeView(treeView1);

//              MsgInfo.name = tb_name;
//              MsgInfo.explain = tb_shuoming;
//              MsgInfo.content = tb_content;

             MsgList.ReadFileData();


            //-------------------枚举界面-----------------------

            EnumPanel.lb_enumlist=lb_meiju;
            lb_meiju.SelectionMode=SelectionMode.One;
            lb_meiju.SelectedIndexChanged += lb_meiju_SelectedIndexChanged;
            EnumPanel.lv_showenum = lv_showenum;
            lv_showenum.SelectedIndexChanged += listView1_SelectedIndexChanged;



        

            ImageList iList = new ImageList();
            iList.ImageSize = new Size(1, 24);//宽度和高度值必须大于等于1且不超过256
            lv_showenum.SmallImageList = iList;

            btn = new Button();
            btn.Visible = false;
            btn.Text = "删除";
            btn.Click += button_Click;
            lv_showenum.Controls.Add(btn);
            btn.Size = new Size(60,
            24);

            btn2 = new Button();
            btn2.Visible = false;
            btn2.Text = "编辑";
            btn2.Click += button_Click2;
            lv_showenum.Controls.Add(btn2);
            btn2.Size = new Size(60,
            24);


         

            EnumPanel.ReadFileData();

            setInfoShowView();

        }

        public void setInfoShowView()
        {
            InfoShow.lv_req = lv_req;
            InfoShow.lv_res = lv_res;
            InfoShow.luyou = t_luyou;
            InfoShow.tb_explain = tb_explain;

            InfoShow.btn_add1 = btn_add1;
            InfoShow.btn_add2 = btn_add2;

            InfoShow.initView();
        }

        private void button2_Click(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            if(treeView1.SelectedNode != null)
            {
                //创建
                Form3 form = new Form3();
                form.StartPosition = FormStartPosition.CenterParent;
                form.setData(MsgList.gettreedata(treeView1.SelectedNode));
                form.ShowDialog();
            }
        }

        private void treeView1_AfterSelect(object sender, TreeViewEventArgs e)
        {

        }

        private void label3_Click(object sender, EventArgs e)
        {

        }

        private void OnNodeChange(object sender, TreeViewEventArgs e)
        {
            if(treeView1.SelectedNode.Parent != null)
            {
                 MsgList.SelectNodeChange(treeView1.SelectedNode);
            }
          
        }


        private void btn_del_Click(object sender, EventArgs e)
        {
            MsgList.delNode();
        }

     

        private void tb_shuoming_TextChanged(object sender, EventArgs e)
        {

        }

        //创建枚举按钮事件
        private void OnCreateEnum(object sender, EventArgs e)
        {
            //创建
            Form_CreateEnum form = new Form_CreateEnum();
            form.StartPosition = FormStartPosition.CenterParent;
            form.ShowDialog();
        }

        private void button9_Click(object sender, EventArgs e)
        {
            //添加
            Form_AddEnumType form = new Form_AddEnumType();
            form.StartPosition = FormStartPosition.CenterParent;
            form.ShowDialog();
        }

        private void OnChange(object sender, EventArgs e)
        {

        }

        private void lb_meiju_SelectedIndexChanged(object sender, EventArgs e)
        {
            EnumPanel.SelectChange();
        }

        private void listView1_SelectedIndexChanged(object sender, EventArgs e)
        {
            if(lv_showenum.SelectedItems.Count > 0)
            {
                btn2.Location = new Point(lv_showenum.SelectedItems[0].SubItems[3].Bounds.Left,
                    lv_showenum.SelectedItems[0].SubItems[3].Bounds.Top);

                btn2.Visible = true;

                btn.Location = new Point(lv_showenum.SelectedItems[0].SubItems[4].Bounds.Left,
                    lv_showenum.SelectedItems[0].SubItems[4].Bounds.Top);

                btn.Visible = true;
            }
        }
        private void button_Click(object sender, EventArgs e)
        {

            if(lv_showenum.SelectedItems == null || lv_showenum.SelectedItems.Count <= 0)
                return;
            //删除
            string name=EnumPanel.getCurrSelect();

            string enumstr = lv_showenum.SelectedItems[0].SubItems[1].Text;

            EnumPanel.subOneType(name, enumstr);
        }
        private void button_Click2(object sender, EventArgs e)
        {
            if(lv_showenum.SelectedItems == null || lv_showenum.SelectedItems.Count <= 0)
                return;
            //修改
            string name = EnumPanel.getCurrSelect();

            if(name == "")
                return;

            string enumstr = lv_showenum.SelectedItems[0].SubItems[1].Text;

            EnumInfo info = EnumPanel.getOneType(name, enumstr);

       
            Form_AddEnumType form = new Form_AddEnumType();
            form.SetInfo(info);
            form.StartPosition = FormStartPosition.CenterParent;
            form.ShowDialog();
            
        }

        private void button11_Click(object sender, EventArgs e)
        {
              string name=EnumPanel.getCurrSelect();
              EnumPanel.subOneEnum(name);
        }

        private void button10_Click(object sender, EventArgs e)
        {
            EnumPanel.WriteFileData();
        }

        private void button5_Click_1(object sender, EventArgs e)
        {
            MsgList.OutProtocolFile();
            EnumPanel.OutEnumFile();
        }

        private void button7_Click(object sender, EventArgs e)
        {
            if(treeView1.SelectedNode != null)
            {
                //创建
                Form2 form = new Form2();
                form.StartPosition = FormStartPosition.CenterParent;
                form.ShowDialog();
            }
        }

        private void label2_Click(object sender, EventArgs e)
        {

        }

        private void btn_add2_Click(object sender, EventArgs e)
        {

        }

        private void btn_add1_Click(object sender, EventArgs e)
        {

        }

    }
}
