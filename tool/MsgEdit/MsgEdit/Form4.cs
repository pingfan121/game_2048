using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Configuration;

namespace MsgEdit
{
    public partial class Form4 : Form
    {
        public Configuration config = ConfigurationManager.OpenExeConfiguration(ConfigurationUserLevel.None);
        public Form4()
        {
            InitializeComponent();

            c_tx1.Text = config.AppSettings.Settings["clienttestpath"].Value;
            c_tx2.Text = config.AppSettings.Settings["clientpath"].Value;
            s_tx1.Text = config.AppSettings.Settings["servertestpath"].Value;
            s_tx2.Text = config.AppSettings.Settings["serverpath"].Value;
        }

        private void btn_save_Click(object sender, EventArgs e)
        {
            config.AppSettings.Settings["clienttestpath"].Value =c_tx1.Text;
            config.AppSettings.Settings["clientpath"].Value = c_tx2.Text;
            config.AppSettings.Settings["servertestpath"].Value = s_tx1.Text;
            config.AppSettings.Settings["serverpath"].Value = s_tx2.Text;
            config.Save();
        }

        private void btn_liulan1_Click(object sender, EventArgs e)
        {
            FolderBrowserDialog dilog = new FolderBrowserDialog();
            dilog.Description = "请选择文件夹";

            DialogResult res=dilog.ShowDialog();

            if(res == DialogResult.OK || res == DialogResult.Yes)
            {
                c_tx2.Text = dilog.SelectedPath;
            }
        }

        private void btn_liulan2_Click(object sender, EventArgs e)
        {
            FolderBrowserDialog dilog = new FolderBrowserDialog();
            dilog.Description = "请选择文件夹";

            DialogResult res = dilog.ShowDialog();

            if(res == DialogResult.OK || res == DialogResult.Yes)
            {
                s_tx2.Text = dilog.SelectedPath;
            }
        }
    }
}
