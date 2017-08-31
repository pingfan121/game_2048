namespace MsgEdit
{
    partial class Form4
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if(disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.c_tx2 = new System.Windows.Forms.TextBox();
            this.s_tx2 = new System.Windows.Forms.TextBox();
            this.btn_save = new System.Windows.Forms.Button();
            this.btn_liulan1 = new System.Windows.Forms.Button();
            this.btn_liulan2 = new System.Windows.Forms.Button();
            this.button1 = new System.Windows.Forms.Button();
            this.s_tx1 = new System.Windows.Forms.TextBox();
            this.label3 = new System.Windows.Forms.Label();
            this.button2 = new System.Windows.Forms.Button();
            this.c_tx1 = new System.Windows.Forms.TextBox();
            this.label4 = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(38, 71);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(71, 12);
            this.label1.TabIndex = 0;
            this.label1.Text = "客户端路径:";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(36, 157);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(71, 12);
            this.label2.TabIndex = 1;
            this.label2.Text = "服务端路径:";
            // 
            // c_tx2
            // 
            this.c_tx2.Location = new System.Drawing.Point(115, 68);
            this.c_tx2.Name = "c_tx2";
            this.c_tx2.Size = new System.Drawing.Size(269, 21);
            this.c_tx2.TabIndex = 2;
            // 
            // s_tx2
            // 
            this.s_tx2.Location = new System.Drawing.Point(115, 154);
            this.s_tx2.Name = "s_tx2";
            this.s_tx2.Size = new System.Drawing.Size(269, 21);
            this.s_tx2.TabIndex = 3;
            // 
            // btn_save
            // 
            this.btn_save.Location = new System.Drawing.Point(216, 201);
            this.btn_save.Name = "btn_save";
            this.btn_save.Size = new System.Drawing.Size(75, 23);
            this.btn_save.TabIndex = 4;
            this.btn_save.Text = "保存";
            this.btn_save.UseVisualStyleBackColor = true;
            this.btn_save.Click += new System.EventHandler(this.btn_save_Click);
            // 
            // btn_liulan1
            // 
            this.btn_liulan1.Location = new System.Drawing.Point(402, 66);
            this.btn_liulan1.Name = "btn_liulan1";
            this.btn_liulan1.Size = new System.Drawing.Size(75, 23);
            this.btn_liulan1.TabIndex = 5;
            this.btn_liulan1.Text = "浏览";
            this.btn_liulan1.UseVisualStyleBackColor = true;
            this.btn_liulan1.Click += new System.EventHandler(this.btn_liulan1_Click);
            // 
            // btn_liulan2
            // 
            this.btn_liulan2.Location = new System.Drawing.Point(402, 152);
            this.btn_liulan2.Name = "btn_liulan2";
            this.btn_liulan2.Size = new System.Drawing.Size(75, 23);
            this.btn_liulan2.TabIndex = 6;
            this.btn_liulan2.Text = "浏览";
            this.btn_liulan2.UseVisualStyleBackColor = true;
            this.btn_liulan2.Click += new System.EventHandler(this.btn_liulan2_Click);
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(402, 107);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(75, 23);
            this.button1.TabIndex = 9;
            this.button1.Text = "浏览";
            this.button1.UseVisualStyleBackColor = true;
            // 
            // s_tx1
            // 
            this.s_tx1.Location = new System.Drawing.Point(115, 109);
            this.s_tx1.Name = "s_tx1";
            this.s_tx1.Size = new System.Drawing.Size(269, 21);
            this.s_tx1.TabIndex = 8;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(14, 112);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(95, 12);
            this.label3.TabIndex = 7;
            this.label3.Text = "服务端测试路径:";
            // 
            // button2
            // 
            this.button2.Location = new System.Drawing.Point(402, 22);
            this.button2.Name = "button2";
            this.button2.Size = new System.Drawing.Size(75, 23);
            this.button2.TabIndex = 12;
            this.button2.Text = "浏览";
            this.button2.UseVisualStyleBackColor = true;
            // 
            // c_tx1
            // 
            this.c_tx1.Location = new System.Drawing.Point(115, 24);
            this.c_tx1.Name = "c_tx1";
            this.c_tx1.Size = new System.Drawing.Size(269, 21);
            this.c_tx1.TabIndex = 11;
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(14, 27);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(95, 12);
            this.label4.TabIndex = 10;
            this.label4.Text = "客户端测试路径:";
            // 
            // Form4
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(502, 236);
            this.Controls.Add(this.button2);
            this.Controls.Add(this.c_tx1);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.s_tx1);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.btn_liulan2);
            this.Controls.Add(this.btn_liulan1);
            this.Controls.Add(this.btn_save);
            this.Controls.Add(this.s_tx2);
            this.Controls.Add(this.c_tx2);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Name = "Form4";
            this.Text = "Form4";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox c_tx2;
        private System.Windows.Forms.TextBox s_tx2;
        private System.Windows.Forms.Button btn_save;
        private System.Windows.Forms.Button btn_liulan1;
        private System.Windows.Forms.Button btn_liulan2;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.TextBox s_tx1;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Button button2;
        private System.Windows.Forms.TextBox c_tx1;
        private System.Windows.Forms.Label label4;
    }
}