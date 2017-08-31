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
    public partial class Form3 : Form
    {
        public Form3()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            data.name = tb1.Text;
            MsgList.UpdateTree();
            this.Close();
        }

        public tree_data data;
        public void setData(tree_data data)
        {
            this.data = data;

            tb1.Text = data.name;
        }
    }
}
