namespace MoneyFlow
{
    partial class LoginForm
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
            if (disposing && (components != null))
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
            this.panLoginMain = new System.Windows.Forms.Panel();
            this.panLoginCreateNew = new System.Windows.Forms.Panel();
            this.lblLoginCreateMSGPic = new System.Windows.Forms.Label();
            this.lblLoginCreateMSG = new System.Windows.Forms.Label();
            this.btnLoginCreateAcc = new System.Windows.Forms.Button();
            this.btnLoginCreateBack = new System.Windows.Forms.Button();
            this.lblLoginCreateReq = new System.Windows.Forms.Label();
            this.grpBoxLoginCreate = new System.Windows.Forms.GroupBox();
            this.lblErrLoginCreatePassRe = new System.Windows.Forms.Label();
            this.lblErrLoginCreatePass = new System.Windows.Forms.Label();
            this.lblErrLoginCreateNick = new System.Windows.Forms.Label();
            this.lblErrLoginCreateName = new System.Windows.Forms.Label();
            this.txtBoxLoginCreateRePass = new System.Windows.Forms.TextBox();
            this.lblLoginCreateRePass = new System.Windows.Forms.Label();
            this.txtBoxLoginCreatePass = new System.Windows.Forms.TextBox();
            this.lblLoginCreatePass = new System.Windows.Forms.Label();
            this.txtBoxLoginCreateNick = new System.Windows.Forms.TextBox();
            this.lblLoginCreateNick = new System.Windows.Forms.Label();
            this.txtBoxLoginCreateName = new System.Windows.Forms.TextBox();
            this.lblLoginCreateName = new System.Windows.Forms.Label();
            this.btnLoginExit = new System.Windows.Forms.Button();
            this.btnLoginCreateNew = new System.Windows.Forms.Button();
            this.btnLoginLog = new System.Windows.Forms.Button();
            this.lblLoginPass = new System.Windows.Forms.Label();
            this.txtBoxLoginPass = new System.Windows.Forms.TextBox();
            this.lblLoginUsers = new System.Windows.Forms.Label();
            this.lstBoxLoginUsers = new System.Windows.Forms.ListBox();
            this.lblLoginMSGPic = new System.Windows.Forms.Label();
            this.lblLoginMSG = new System.Windows.Forms.Label();
            this.proBarLogin = new System.Windows.Forms.ProgressBar();
            this.panLoginMain.SuspendLayout();
            this.panLoginCreateNew.SuspendLayout();
            this.grpBoxLoginCreate.SuspendLayout();
            this.SuspendLayout();
            // 
            // panLoginMain
            // 
            this.panLoginMain.Controls.Add(this.panLoginCreateNew);
            this.panLoginMain.Controls.Add(this.btnLoginExit);
            this.panLoginMain.Controls.Add(this.btnLoginCreateNew);
            this.panLoginMain.Controls.Add(this.btnLoginLog);
            this.panLoginMain.Controls.Add(this.lblLoginPass);
            this.panLoginMain.Controls.Add(this.txtBoxLoginPass);
            this.panLoginMain.Controls.Add(this.lblLoginUsers);
            this.panLoginMain.Controls.Add(this.lstBoxLoginUsers);
            this.panLoginMain.Controls.Add(this.lblLoginMSGPic);
            this.panLoginMain.Controls.Add(this.lblLoginMSG);
            this.panLoginMain.Controls.Add(this.proBarLogin);
            this.panLoginMain.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panLoginMain.Location = new System.Drawing.Point(0, 0);
            this.panLoginMain.Name = "panLoginMain";
            this.panLoginMain.Size = new System.Drawing.Size(384, 262);
            this.panLoginMain.TabIndex = 0;
            // 
            // panLoginCreateNew
            // 
            this.panLoginCreateNew.Controls.Add(this.lblLoginCreateMSGPic);
            this.panLoginCreateNew.Controls.Add(this.lblLoginCreateMSG);
            this.panLoginCreateNew.Controls.Add(this.btnLoginCreateAcc);
            this.panLoginCreateNew.Controls.Add(this.btnLoginCreateBack);
            this.panLoginCreateNew.Controls.Add(this.lblLoginCreateReq);
            this.panLoginCreateNew.Controls.Add(this.grpBoxLoginCreate);
            this.panLoginCreateNew.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panLoginCreateNew.Location = new System.Drawing.Point(0, 0);
            this.panLoginCreateNew.Name = "panLoginCreateNew";
            this.panLoginCreateNew.Size = new System.Drawing.Size(384, 262);
            this.panLoginCreateNew.TabIndex = 8;
            this.panLoginCreateNew.Visible = false;
            this.panLoginCreateNew.VisibleChanged += new System.EventHandler(this.panLoginCreateNew_VisibleChanged);
            // 
            // lblLoginCreateMSGPic
            // 
            this.lblLoginCreateMSGPic.Location = new System.Drawing.Point(17, 24);
            this.lblLoginCreateMSGPic.Name = "lblLoginCreateMSGPic";
            this.lblLoginCreateMSGPic.Size = new System.Drawing.Size(50, 50);
            this.lblLoginCreateMSGPic.TabIndex = 5;
            // 
            // lblLoginCreateMSG
            // 
            this.lblLoginCreateMSG.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.lblLoginCreateMSG.ForeColor = System.Drawing.SystemColors.ControlDarkDark;
            this.lblLoginCreateMSG.Location = new System.Drawing.Point(74, 21);
            this.lblLoginCreateMSG.Name = "lblLoginCreateMSG";
            this.lblLoginCreateMSG.Size = new System.Drawing.Size(288, 56);
            this.lblLoginCreateMSG.TabIndex = 4;
            this.lblLoginCreateMSG.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // btnLoginCreateAcc
            // 
            this.btnLoginCreateAcc.Location = new System.Drawing.Point(92, 213);
            this.btnLoginCreateAcc.Name = "btnLoginCreateAcc";
            this.btnLoginCreateAcc.Size = new System.Drawing.Size(152, 23);
            this.btnLoginCreateAcc.TabIndex = 3;
            this.btnLoginCreateAcc.Text = "Create new user account";
            this.btnLoginCreateAcc.UseVisualStyleBackColor = true;
            this.btnLoginCreateAcc.Click += new System.EventHandler(this.btnLoginCreateAcc_Click);
            // 
            // btnLoginCreateBack
            // 
            this.btnLoginCreateBack.Location = new System.Drawing.Point(11, 213);
            this.btnLoginCreateBack.Name = "btnLoginCreateBack";
            this.btnLoginCreateBack.Size = new System.Drawing.Size(75, 23);
            this.btnLoginCreateBack.TabIndex = 2;
            this.btnLoginCreateBack.Text = "<< Back";
            this.btnLoginCreateBack.UseVisualStyleBackColor = true;
            this.btnLoginCreateBack.Click += new System.EventHandler(this.btnLoginCreateBack_Click);
            // 
            // lblLoginCreateReq
            // 
            this.lblLoginCreateReq.AutoSize = true;
            this.lblLoginCreateReq.Font = new System.Drawing.Font("Microsoft Sans Serif", 6.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.lblLoginCreateReq.ForeColor = System.Drawing.SystemColors.WindowFrame;
            this.lblLoginCreateReq.Location = new System.Drawing.Point(328, 207);
            this.lblLoginCreateReq.Name = "lblLoginCreateReq";
            this.lblLoginCreateReq.Size = new System.Drawing.Size(44, 12);
            this.lblLoginCreateReq.TabIndex = 1;
            this.lblLoginCreateReq.Text = "* required";
            // 
            // grpBoxLoginCreate
            // 
            this.grpBoxLoginCreate.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.grpBoxLoginCreate.Controls.Add(this.lblErrLoginCreatePassRe);
            this.grpBoxLoginCreate.Controls.Add(this.lblErrLoginCreatePass);
            this.grpBoxLoginCreate.Controls.Add(this.lblErrLoginCreateNick);
            this.grpBoxLoginCreate.Controls.Add(this.lblErrLoginCreateName);
            this.grpBoxLoginCreate.Controls.Add(this.txtBoxLoginCreateRePass);
            this.grpBoxLoginCreate.Controls.Add(this.lblLoginCreateRePass);
            this.grpBoxLoginCreate.Controls.Add(this.txtBoxLoginCreatePass);
            this.grpBoxLoginCreate.Controls.Add(this.lblLoginCreatePass);
            this.grpBoxLoginCreate.Controls.Add(this.txtBoxLoginCreateNick);
            this.grpBoxLoginCreate.Controls.Add(this.lblLoginCreateNick);
            this.grpBoxLoginCreate.Controls.Add(this.txtBoxLoginCreateName);
            this.grpBoxLoginCreate.Controls.Add(this.lblLoginCreateName);
            this.grpBoxLoginCreate.ForeColor = System.Drawing.SystemColors.WindowFrame;
            this.grpBoxLoginCreate.Location = new System.Drawing.Point(11, 74);
            this.grpBoxLoginCreate.Name = "grpBoxLoginCreate";
            this.grpBoxLoginCreate.Size = new System.Drawing.Size(361, 131);
            this.grpBoxLoginCreate.TabIndex = 0;
            this.grpBoxLoginCreate.TabStop = false;
            // 
            // lblErrLoginCreatePassRe
            // 
            this.lblErrLoginCreatePassRe.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Italic, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.lblErrLoginCreatePassRe.ForeColor = System.Drawing.Color.Red;
            this.lblErrLoginCreatePassRe.Image = global::MoneyFlow.Properties.Resources.eventlogError;
            this.lblErrLoginCreatePassRe.Location = new System.Drawing.Point(215, 104);
            this.lblErrLoginCreatePassRe.Name = "lblErrLoginCreatePassRe";
            this.lblErrLoginCreatePassRe.Size = new System.Drawing.Size(16, 16);
            this.lblErrLoginCreatePassRe.TabIndex = 11;
            this.lblErrLoginCreatePassRe.Visible = false;
            // 
            // lblErrLoginCreatePass
            // 
            this.lblErrLoginCreatePass.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Italic, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.lblErrLoginCreatePass.ForeColor = System.Drawing.Color.Red;
            this.lblErrLoginCreatePass.Image = global::MoneyFlow.Properties.Resources.eventlogError;
            this.lblErrLoginCreatePass.Location = new System.Drawing.Point(215, 78);
            this.lblErrLoginCreatePass.Name = "lblErrLoginCreatePass";
            this.lblErrLoginCreatePass.Size = new System.Drawing.Size(16, 16);
            this.lblErrLoginCreatePass.TabIndex = 10;
            this.lblErrLoginCreatePass.Visible = false;
            // 
            // lblErrLoginCreateNick
            // 
            this.lblErrLoginCreateNick.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Italic, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.lblErrLoginCreateNick.ForeColor = System.Drawing.Color.Red;
            this.lblErrLoginCreateNick.Image = global::MoneyFlow.Properties.Resources.eventlogError;
            this.lblErrLoginCreateNick.Location = new System.Drawing.Point(215, 52);
            this.lblErrLoginCreateNick.Name = "lblErrLoginCreateNick";
            this.lblErrLoginCreateNick.Size = new System.Drawing.Size(16, 16);
            this.lblErrLoginCreateNick.TabIndex = 9;
            this.lblErrLoginCreateNick.Visible = false;
            // 
            // lblErrLoginCreateName
            // 
            this.lblErrLoginCreateName.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Italic, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.lblErrLoginCreateName.ForeColor = System.Drawing.Color.Red;
            this.lblErrLoginCreateName.Image = global::MoneyFlow.Properties.Resources.eventlogError;
            this.lblErrLoginCreateName.Location = new System.Drawing.Point(215, 26);
            this.lblErrLoginCreateName.Name = "lblErrLoginCreateName";
            this.lblErrLoginCreateName.Size = new System.Drawing.Size(16, 16);
            this.lblErrLoginCreateName.TabIndex = 8;
            this.lblErrLoginCreateName.Visible = false;
            // 
            // txtBoxLoginCreateRePass
            // 
            this.txtBoxLoginCreateRePass.Location = new System.Drawing.Point(109, 102);
            this.txtBoxLoginCreateRePass.Name = "txtBoxLoginCreateRePass";
            this.txtBoxLoginCreateRePass.Size = new System.Drawing.Size(100, 20);
            this.txtBoxLoginCreateRePass.TabIndex = 7;
            this.txtBoxLoginCreateRePass.UseSystemPasswordChar = true;
            // 
            // lblLoginCreateRePass
            // 
            this.lblLoginCreateRePass.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.lblLoginCreateRePass.ForeColor = System.Drawing.SystemColors.ActiveCaptionText;
            this.lblLoginCreateRePass.Location = new System.Drawing.Point(6, 102);
            this.lblLoginCreateRePass.Name = "lblLoginCreateRePass";
            this.lblLoginCreateRePass.Size = new System.Drawing.Size(100, 20);
            this.lblLoginCreateRePass.TabIndex = 6;
            this.lblLoginCreateRePass.Text = "Repeat Password*:";
            this.lblLoginCreateRePass.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // txtBoxLoginCreatePass
            // 
            this.txtBoxLoginCreatePass.Location = new System.Drawing.Point(109, 76);
            this.txtBoxLoginCreatePass.Name = "txtBoxLoginCreatePass";
            this.txtBoxLoginCreatePass.Size = new System.Drawing.Size(100, 20);
            this.txtBoxLoginCreatePass.TabIndex = 5;
            this.txtBoxLoginCreatePass.UseSystemPasswordChar = true;
            // 
            // lblLoginCreatePass
            // 
            this.lblLoginCreatePass.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.lblLoginCreatePass.ForeColor = System.Drawing.SystemColors.ActiveCaptionText;
            this.lblLoginCreatePass.Location = new System.Drawing.Point(11, 75);
            this.lblLoginCreatePass.Name = "lblLoginCreatePass";
            this.lblLoginCreatePass.Size = new System.Drawing.Size(95, 20);
            this.lblLoginCreatePass.TabIndex = 4;
            this.lblLoginCreatePass.Text = "Password*:";
            this.lblLoginCreatePass.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // txtBoxLoginCreateNick
            // 
            this.txtBoxLoginCreateNick.Location = new System.Drawing.Point(109, 50);
            this.txtBoxLoginCreateNick.Name = "txtBoxLoginCreateNick";
            this.txtBoxLoginCreateNick.Size = new System.Drawing.Size(100, 20);
            this.txtBoxLoginCreateNick.TabIndex = 3;
            // 
            // lblLoginCreateNick
            // 
            this.lblLoginCreateNick.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.lblLoginCreateNick.ForeColor = System.Drawing.SystemColors.ActiveCaptionText;
            this.lblLoginCreateNick.Location = new System.Drawing.Point(11, 50);
            this.lblLoginCreateNick.Name = "lblLoginCreateNick";
            this.lblLoginCreateNick.Size = new System.Drawing.Size(95, 20);
            this.lblLoginCreateNick.TabIndex = 2;
            this.lblLoginCreateNick.Text = "Nick*:";
            this.lblLoginCreateNick.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // txtBoxLoginCreateName
            // 
            this.txtBoxLoginCreateName.Location = new System.Drawing.Point(109, 24);
            this.txtBoxLoginCreateName.Name = "txtBoxLoginCreateName";
            this.txtBoxLoginCreateName.Size = new System.Drawing.Size(100, 20);
            this.txtBoxLoginCreateName.TabIndex = 1;
            // 
            // lblLoginCreateName
            // 
            this.lblLoginCreateName.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.lblLoginCreateName.ForeColor = System.Drawing.SystemColors.ActiveCaptionText;
            this.lblLoginCreateName.Location = new System.Drawing.Point(11, 24);
            this.lblLoginCreateName.Name = "lblLoginCreateName";
            this.lblLoginCreateName.Size = new System.Drawing.Size(95, 20);
            this.lblLoginCreateName.TabIndex = 0;
            this.lblLoginCreateName.Text = "Full Name:";
            this.lblLoginCreateName.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // btnLoginExit
            // 
            this.btnLoginExit.Location = new System.Drawing.Point(230, 227);
            this.btnLoginExit.Name = "btnLoginExit";
            this.btnLoginExit.Size = new System.Drawing.Size(147, 23);
            this.btnLoginExit.TabIndex = 7;
            this.btnLoginExit.Text = "Exit";
            this.btnLoginExit.UseVisualStyleBackColor = true;
            this.btnLoginExit.Click += new System.EventHandler(this.btnLoginExit_Click);
            // 
            // btnLoginCreateNew
            // 
            this.btnLoginCreateNew.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.btnLoginCreateNew.Location = new System.Drawing.Point(230, 201);
            this.btnLoginCreateNew.Name = "btnLoginCreateNew";
            this.btnLoginCreateNew.Size = new System.Drawing.Size(146, 23);
            this.btnLoginCreateNew.TabIndex = 5;
            this.btnLoginCreateNew.Text = "Create new account";
            this.btnLoginCreateNew.UseVisualStyleBackColor = true;
            this.btnLoginCreateNew.Click += new System.EventHandler(this.btnLoginCreateNew_Click);
            // 
            // btnLoginLog
            // 
            this.btnLoginLog.Anchor = System.Windows.Forms.AnchorStyles.Right;
            this.btnLoginLog.Enabled = false;
            this.btnLoginLog.Location = new System.Drawing.Point(301, 134);
            this.btnLoginLog.Name = "btnLoginLog";
            this.btnLoginLog.Size = new System.Drawing.Size(75, 23);
            this.btnLoginLog.TabIndex = 4;
            this.btnLoginLog.Text = "Log in";
            this.btnLoginLog.UseVisualStyleBackColor = true;
            this.btnLoginLog.Click += new System.EventHandler(this.btnLoginLog_Click);
            // 
            // lblLoginPass
            // 
            this.lblLoginPass.AutoSize = true;
            this.lblLoginPass.Location = new System.Drawing.Point(227, 92);
            this.lblLoginPass.Name = "lblLoginPass";
            this.lblLoginPass.Size = new System.Drawing.Size(56, 13);
            this.lblLoginPass.TabIndex = 3;
            this.lblLoginPass.Text = "Password:";
            // 
            // txtBoxLoginPass
            // 
            this.txtBoxLoginPass.Anchor = System.Windows.Forms.AnchorStyles.Right;
            this.txtBoxLoginPass.Cursor = System.Windows.Forms.Cursors.Default;
            this.txtBoxLoginPass.Enabled = false;
            this.txtBoxLoginPass.Location = new System.Drawing.Point(230, 109);
            this.txtBoxLoginPass.Name = "txtBoxLoginPass";
            this.txtBoxLoginPass.Size = new System.Drawing.Size(147, 20);
            this.txtBoxLoginPass.TabIndex = 2;
            this.txtBoxLoginPass.UseSystemPasswordChar = true;
            // 
            // lblLoginUsers
            // 
            this.lblLoginUsers.AutoSize = true;
            this.lblLoginUsers.Location = new System.Drawing.Point(12, 21);
            this.lblLoginUsers.Name = "lblLoginUsers";
            this.lblLoginUsers.Size = new System.Drawing.Size(86, 13);
            this.lblLoginUsers.TabIndex = 1;
            this.lblLoginUsers.Text = "User\'s accounts:";
            // 
            // lstBoxLoginUsers
            // 
            this.lstBoxLoginUsers.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.lstBoxLoginUsers.FormattingEnabled = true;
            this.lstBoxLoginUsers.Location = new System.Drawing.Point(11, 37);
            this.lstBoxLoginUsers.Name = "lstBoxLoginUsers";
            this.lstBoxLoginUsers.Size = new System.Drawing.Size(211, 212);
            this.lstBoxLoginUsers.TabIndex = 0;
            this.lstBoxLoginUsers.SelectedIndexChanged += new System.EventHandler(this.lstBoxLoginUsers_SelectedIndexChanged);
            // 
            // lblLoginMSGPic
            // 
            this.lblLoginMSGPic.Anchor = System.Windows.Forms.AnchorStyles.Right;
            this.lblLoginMSGPic.ForeColor = System.Drawing.SystemColors.ControlDarkDark;
            this.lblLoginMSGPic.Location = new System.Drawing.Point(228, 37);
            this.lblLoginMSGPic.Name = "lblLoginMSGPic";
            this.lblLoginMSGPic.Size = new System.Drawing.Size(50, 50);
            this.lblLoginMSGPic.TabIndex = 7;
            this.lblLoginMSGPic.TextAlign = System.Drawing.ContentAlignment.TopCenter;
            // 
            // lblLoginMSG
            // 
            this.lblLoginMSG.Anchor = System.Windows.Forms.AnchorStyles.Right;
            this.lblLoginMSG.ForeColor = System.Drawing.SystemColors.ControlDarkDark;
            this.lblLoginMSG.Location = new System.Drawing.Point(280, 37);
            this.lblLoginMSG.Name = "lblLoginMSG";
            this.lblLoginMSG.Size = new System.Drawing.Size(95, 50);
            this.lblLoginMSG.TabIndex = 6;
            this.lblLoginMSG.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // proBarLogin
            // 
            this.proBarLogin.Anchor = System.Windows.Forms.AnchorStyles.Right;
            this.proBarLogin.Location = new System.Drawing.Point(301, 135);
            this.proBarLogin.Name = "proBarLogin";
            this.proBarLogin.Size = new System.Drawing.Size(74, 21);
            this.proBarLogin.TabIndex = 12;
            this.proBarLogin.Visible = false;
            // 
            // LoginForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(384, 262);
            this.Controls.Add(this.panLoginMain);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedToolWindow;
            this.MaximizeBox = false;
            this.MinimizeBox = false;
            this.Name = "LoginForm";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Money Flow - login";
            this.Load += new System.EventHandler(this.LoginForm_Load);
            this.FormClosed += new System.Windows.Forms.FormClosedEventHandler(this.LoginForm_FormClosed);
            this.panLoginMain.ResumeLayout(false);
            this.panLoginMain.PerformLayout();
            this.panLoginCreateNew.ResumeLayout(false);
            this.panLoginCreateNew.PerformLayout();
            this.grpBoxLoginCreate.ResumeLayout(false);
            this.grpBoxLoginCreate.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Panel panLoginMain;
        private System.Windows.Forms.ListBox lstBoxLoginUsers;
        private System.Windows.Forms.Label lblLoginUsers;
        private System.Windows.Forms.Label lblLoginPass;
        private System.Windows.Forms.TextBox txtBoxLoginPass;
        private System.Windows.Forms.Button btnLoginLog;
        private System.Windows.Forms.Button btnLoginCreateNew;
        private System.Windows.Forms.Label lblLoginMSG;
        private System.Windows.Forms.Button btnLoginExit;
        private System.Windows.Forms.Panel panLoginCreateNew;
        private System.Windows.Forms.Button btnLoginCreateAcc;
        private System.Windows.Forms.Button btnLoginCreateBack;
        private System.Windows.Forms.Label lblLoginCreateReq;
        private System.Windows.Forms.GroupBox grpBoxLoginCreate;
        private System.Windows.Forms.TextBox txtBoxLoginCreateRePass;
        private System.Windows.Forms.Label lblLoginCreateRePass;
        private System.Windows.Forms.TextBox txtBoxLoginCreatePass;
        private System.Windows.Forms.Label lblLoginCreatePass;
        private System.Windows.Forms.TextBox txtBoxLoginCreateNick;
        private System.Windows.Forms.Label lblLoginCreateNick;
        private System.Windows.Forms.TextBox txtBoxLoginCreateName;
        private System.Windows.Forms.Label lblLoginCreateName;
        private System.Windows.Forms.Label lblLoginCreateMSG;
        private System.Windows.Forms.Label lblLoginCreateMSGPic;
        private System.Windows.Forms.Label lblLoginMSGPic;
        private System.Windows.Forms.Label lblErrLoginCreateName;
        private System.Windows.Forms.Label lblErrLoginCreateNick;
        private System.Windows.Forms.Label lblErrLoginCreatePassRe;
        private System.Windows.Forms.Label lblErrLoginCreatePass;
        private System.Windows.Forms.ProgressBar proBarLogin;
    }
}