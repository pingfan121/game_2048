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
    public partial class Form_AddEnumType : Form
    {
        public Form_AddEnumType()
        {
            InitializeComponent();

            button2.Visible = false;
        }

        private void textBox3_TextChanged(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            EnumInfo info = new EnumInfo();
            try
            {
                info.index = int.Parse(tb_1.Text);
                info.enumstr = tb_2.Text;
                info.explain = tb_3.Text;

                string curr =EnumPanel.getCurrSelect();

                if(EnumPanel.hasOneType(curr, info) == true)
                {
                    MessageBox.Show("已经有相同数据了");
                    return;
                }
                EnumPanel.addOneType(curr, info);

                this.Close();
            }
            catch
            {
                MessageBox.Show("输入的数据格式不对");
                return;
            }
           
        }

        private void button2_Click(object sender, EventArgs e)
        {
            EnumInfo info = new EnumInfo();
            try
            {
                info.index = int.Parse(tb_1.Text);
                info.enumstr = tb_2.Text;
                info.explain = tb_3.Text;

                string curr = EnumPanel.getCurrSelect();

                if(EnumPanel.canModifyType(curr,old,info) == false)
                {
                    MessageBox.Show("已经有相同数据了");
                    return;
                }

                old.index = info.index;
                old.enumstr = info.enumstr;
                old.explain = info.explain;

                EnumPanel.SelectChange();

                this.Close();
            }
            catch
            {
                MessageBox.Show("输入的数据格式不对");
                return;
            }
        }

        public EnumInfo old;
        public void SetInfo(EnumInfo oldinfo)
        {
            old=oldinfo;

            tb_1.Text = old.index + "";
            tb_2.Text = old.enumstr;
            tb_3.Text= old.explain;

            button1.Visible = false;
            button2.Visible = true;
        }
    }
}
