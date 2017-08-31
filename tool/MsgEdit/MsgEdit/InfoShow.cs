using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace MsgEdit
{
    class InfoShow
    {

        public static Label luyou;
        public static TextBox tb_explain;

        public static ListView lv_req;
        public static ListView lv_res;

        public static Button btn1_req;
        public static Button btn2_req;

        public static Button btn1_res;
        public static Button btn2_res;

        public static Button btn_add1;
        public static Button btn_add2;


        public static tree_data treedata=null;

       

        public static void initView()
        {
            ImageList iList1 = new ImageList();
            iList1.ImageSize = new Size(1, 24);
            lv_req.SmallImageList = iList1;

            ImageList iList2 = new ImageList();
            iList2.ImageSize = new Size(1, 24);
            lv_res.SmallImageList = iList2;

            btn1_req = new Button();
            btn1_req.Visible = false;
            btn1_req.Text = "删除";
            btn1_req.Click += btn_del_event;
            lv_req.Controls.Add(btn1_req);
            btn1_req.Size = new Size(60,
            24);

            btn2_req = new Button();
            btn2_req.Visible = false;
            btn2_req.Text = "编辑";
            btn2_req.Click += btn_modif_event;
            lv_req.Controls.Add(btn2_req);
            btn2_req.Size = new Size(60,
            24);

            btn1_res = new Button();
            btn1_res.Visible = false;
            btn1_res.Text = "删除";
            btn1_res.Click += btn_del_event;
            lv_res.Controls.Add(btn1_res);
            btn1_res.Size = new Size(60,
            24);

            btn2_res = new Button();
            btn2_res.Visible = false;
            btn2_res.Text = "编辑";
            btn2_res.Click += btn_modif_event;
            lv_res.Controls.Add(btn2_res);
            btn2_res.Size = new Size(60,
            24);

            lv_req.SelectedIndexChanged += SelectedIndexChanged;
            lv_res.SelectedIndexChanged += SelectedIndexChanged;

            btn_add1.Click += btn_add_event;
            btn_add2.Click += btn_add_event;

            tb_explain.LostFocus += OnLostFocus;
        }

       
        public static void SetMsgInfo(string path,tree_data data)
        {
            treedata = data;

            luyou.Text = path;


            updateView();
        }

        public static void updateView()
        {
            lv_req.Items.Clear();
            lv_res.Items.Clear();

            btn1_req.Visible = false;
            btn2_req.Visible = false;
            btn1_res.Visible = false;
            btn2_res.Visible = false;

            if(treedata == null)
                return;

            tb_explain.Text = treedata.data.explain;

            foreach(var item in treedata.data.req_params)
            {
                ListViewItem lv_item = new ListViewItem(item.param_name);
                lv_item.SubItems.Add(item.param_type);
                lv_item.SubItems.Add(item.param_explain);
                lv_item.SubItems.Add("");
                lv_item.SubItems.Add("");

                lv_req.Items.Add(lv_item);
            }



            foreach(var item in treedata.data.res_params)
            {
                ListViewItem lv_item = new ListViewItem(item.param_name);
                lv_item.SubItems.Add(item.param_type);
                lv_item.SubItems.Add(item.param_explain);
                lv_item.SubItems.Add("");
                lv_item.SubItems.Add("");

                lv_res.Items.Add(lv_item);
            }

            MsgList.WriteFileData();
        }


        //添加一个新参数
        public static void addOneParame(int type,info_data infodata)
        {
            if(type == 1)
            {
                if(treedata.data.req_params == null)
                {
                    treedata.data.req_params = new List<info_data>();
                }

                treedata.data.req_params.Add(infodata);
            }
            else
            {
                if(treedata.data.res_params == null)
                {
                    treedata.data.res_params = new List<info_data>();
                }

                treedata.data.res_params.Add(infodata);
            }

            updateView();
        }

        //删除一个参数
        public static void delOneParame(int type, string paramname)
        {
            if(type == 1)
            {
                foreach(var item in treedata.data.req_params)
                {
                    if(item.param_name == paramname)
                    {
                        treedata.data.req_params.Remove(item);
                        break;
                    }
                }
            }
            else
            {
                foreach(var item in treedata.data.res_params)
                {
                    if(item.param_name == paramname)
                    {
                        treedata.data.res_params.Remove(item);
                        break;
                    }
                }
            }
        }

        private static void SelectedIndexChanged(object sender, EventArgs e)
        {
            if(sender == lv_req)
            {
                if(lv_req.SelectedItems.Count > 0)
                {
                    btn2_req.Location = new Point(lv_req.SelectedItems[0].SubItems[3].Bounds.Left,
                        lv_req.SelectedItems[0].SubItems[3].Bounds.Top);

                    btn2_req.Visible = true;

                    btn1_req.Location = new Point(lv_req.SelectedItems[0].SubItems[4].Bounds.Left,
                        lv_req.SelectedItems[0].SubItems[4].Bounds.Top);

                    btn1_req.Visible = true;
                }
            }
            else
            {
                if(lv_res.SelectedItems.Count > 0)
                {
                    btn2_res.Location = new Point(lv_res.SelectedItems[0].SubItems[3].Bounds.Left,
                        lv_res.SelectedItems[0].SubItems[3].Bounds.Top);

                    btn2_res.Visible = true;

                    btn1_res.Location = new Point(lv_res.SelectedItems[0].SubItems[4].Bounds.Left,
                        lv_res.SelectedItems[0].SubItems[4].Bounds.Top);

                    btn1_res.Visible = true;
                }
            }
        }
        private static void btn_add_event(object sender, EventArgs e)
        {

            if(treedata == null)
            {
                return;
            }

            if(sender == btn_add1)
            {
                AddParamForm form = new AddParamForm();
                form.StartPosition = FormStartPosition.CenterParent;
                form.setShowType(1, true, null);
                form.ShowDialog();
            }
            else
            {
                AddParamForm form = new AddParamForm();
                form.StartPosition = FormStartPosition.CenterParent;
                form.setShowType(2, true, null);
                form.ShowDialog();
            }

        }
        private static void btn_modif_event(object sender, EventArgs e)
        {
            int type = 0;
            info_data infodata = null;
            string paramname = "";

            if(sender == btn2_req)
            {
                type = 1;

                if(lv_req.SelectedItems.Count > 0)
                {
                    paramname = lv_req.SelectedItems[0].SubItems[0].Text;
                }
            }
            else
            {
                type = 2;
                if(lv_res.SelectedItems.Count > 0)
                {
                    paramname = lv_res.SelectedItems[0].SubItems[0].Text;

                }
            }

            if(type == 1)
            {
                foreach(var item in treedata.data.req_params)
                {
                    if(item.param_name == paramname)
                    {
                        infodata = item;
                        break;
                    }
                }
            }
            else
            {
                foreach(var item in treedata.data.res_params)
                {
                    if(item.param_name == paramname)
                    {
                        infodata = item;
                        break;
                    }
                }
            }

            //弹出修改窗口
            AddParamForm form = new AddParamForm();
            form.StartPosition = FormStartPosition.CenterParent;
            form.setShowType(type, false, infodata);
            form.ShowDialog();
        }


        //删除按钮
        private static void btn_del_event(object sender, EventArgs e)
        {
            int type = 0;
            string paramname = "";

            if(sender == btn1_req)
            {
                type = 1;

                if(lv_req.SelectedItems.Count > 0)
                {
                    paramname = lv_req.SelectedItems[0].SubItems[0].Text;
                }
            }
            else
            {
                type = 2;
                if(lv_res.SelectedItems.Count > 0)
                {
                    paramname = lv_res.SelectedItems[0].SubItems[0].Text;

                }
            }

            if(type==1)
            {
                foreach(var item in treedata.data.req_params)
                {
                    if(item.param_name == paramname)
                    {
                        treedata.data.req_params.Remove(item);
                        break;
                    }
                }
            }
            else
            {
                foreach(var item in treedata.data.res_params)
                {
                    if(item.param_name == paramname)
                    {
                        treedata.data.res_params.Remove(item);
                        break;
                    }
                }
            }

            updateView();
        }

        public static info_data getParamData(int type,string paramname)
        {
            if(type == 1)
            {
                foreach(var item in treedata.data.req_params)
                {
                    if(item.param_name == paramname)
                    {
                        return item;
                    }
                }
            }
            else
            {
                foreach(var item in treedata.data.res_params)
                {
                    if(item.param_name == paramname)
                    {
                        return item;
                    }
                }
            }
            return null;
        }

        private static void OnLostFocus(object sender, EventArgs e)
        {
            if(treedata != null)
            {
                treedata.data.explain = tb_explain.Text;
                updateView();
            }
           
        }
    }
}
