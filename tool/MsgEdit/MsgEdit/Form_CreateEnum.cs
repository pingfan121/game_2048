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
    public partial class Form_CreateEnum : Form
    {
        public Form_CreateEnum()
        {
            InitializeComponent();
        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            string str = tb_newenum.Text;

            if(EnumPanel.hasOneEnum(str) == true)
            {
                MessageBox.Show("已经存在了相同的枚举");
                return;
            }
            else
            {
                EnumPanel.addOneEnum(str);

                this.Close();
            }
        }
    }
}
