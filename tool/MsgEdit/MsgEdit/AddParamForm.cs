using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace MsgEdit
{
    public partial class AddParamForm : Form
    {
        private static info_data copy_data = new info_data();
        public AddParamForm()
        {
            InitializeComponent();

            btn_xiugai.Visible = false;
        }

        private void textBox3_TextChanged(object sender, EventArgs e)
        {

        }

        private void btn_add_click(object sender, EventArgs e)
        {
            EnumInfo info = new EnumInfo();
            try
            {
                if(tb_1.Text != "")
                {
                    info_data data = new info_data();

                    data.param_name = tb_1.Text;
                    data.param_type = tb_2.Text;
                    data.param_explain = tb_3.Text;
                    InfoShow.addOneParame(type, data);
                }
                this.Close();
            }
            catch
            {
                MessageBox.Show("输入的数据格式不对");
                return;
            }
           
        }

        private void btn_xiugai_click(object sender, EventArgs e)
        {
            try
            {
                infodata.param_name =    tb_1.Text;
                infodata.param_type =    tb_2.Text;
                infodata.param_explain = tb_3.Text;
                InfoShow.updateView();
                this.Close();
            }
            catch
            {
            }
        }

        public info_data infodata;
        public int type;
        public void setShowType(int type ,bool add,info_data data)
        {
            this.infodata = data;
            this.type = type;

            if(add == true)
            {
                btn_add.Visible = true;
                btn_xiugai.Visible = false;
            }
            else
            {
                btn_add.Visible = false;
                btn_xiugai.Visible = true;
            }

            if(data != null)
            {
                 tb_1.Text =infodata.param_name   ;
                 tb_2.Text =infodata.param_type   ;
                 tb_3.Text =infodata.param_explain;
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            copy_data.param_name = tb_1.Text;
            copy_data.param_type = tb_2.Text;
            copy_data.param_explain = tb_3.Text;
        }

        private void button2_Click(object sender, EventArgs e)
        {
            tb_1.Text = copy_data.param_name;
            tb_2.Text = copy_data.param_type;
            tb_3.Text = copy_data.param_explain;
        }
    }
}
