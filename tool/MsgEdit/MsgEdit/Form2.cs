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
    public partial class Form2 : Form
    {
        public Form2()
        {
            InitializeComponent();

            rb_1.Checked = false;
            rb_2.Checked = true;

            rb_1.CheckedChanged += CheckedChange;
            rb_2.CheckedChanged += CheckedChange;
        }

        private void CheckedChange(object sender, EventArgs e)
        {
            if(sender == rb_1)
            {
                rb_2.Checked = !rb_1.Checked;
            }
            else
            {
                rb_1.Checked = !rb_2.Checked;
            }
        }


        private void textBox1_TextChanged(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            if(tb1.Text!="")
            {
                try
                {
                    if(rb_1.Checked == true)
                    {
                        MsgList.AddDirNode(tb1.Text);
                    }
                    else
                    {
                        MsgList.AddFileNode(tb1.Text);
                    }

                    this.Close();

                }
                catch(Exception ex)
                {

                }
               
                
            }
           
        }

        private void label2_Click(object sender, EventArgs e)
        {

        }

        private void rb_1_CheckedChanged(object sender, EventArgs e)
        {

        }
    }
}
