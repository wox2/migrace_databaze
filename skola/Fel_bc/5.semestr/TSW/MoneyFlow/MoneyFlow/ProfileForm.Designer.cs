namespace MoneyFlow
{
    partial class ProfileForm
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
            this.components = new System.ComponentModel.Container();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(ProfileForm));
            this.lblProfileCurrentProf = new System.Windows.Forms.Label();
            this.lblProfileAvail = new System.Windows.Forms.Label();
            this.lstViewAvailProfiles = new System.Windows.Forms.ListView();
            this.lstViewColProfileName = new System.Windows.Forms.ColumnHeader();
            this.lstViewColProfileDesc = new System.Windows.Forms.ColumnHeader();
            this.imgListProfile = new System.Windows.Forms.ImageList(this.components);
            this.btnProfilesCreate = new System.Windows.Forms.Button();
            this.btnProfilesSwitch = new System.Windows.Forms.Button();
            this.lblProfilesCurrent = new System.Windows.Forms.Label();
            this.toolTip = new System.Windows.Forms.ToolTip(this.components);
            this.btnProfilesDelete = new MoneyFlow.Buttons.ButtonDelete();
            this.btnProfilesEdit = new MoneyFlow.Buttons.ButtonEdit();
            this.btnProfilesReturn = new MoneyFlow.Buttons.ButtonReturn();
            this.lblProfilesMSGPic = new System.Windows.Forms.Label();
            this.lblProfilesMSG = new System.Windows.Forms.Label();
            this.panProfilesMain = new System.Windows.Forms.Panel();
            this.panProfilesChange = new System.Windows.Forms.Panel();
            this.lblProfilesChangeReq = new System.Windows.Forms.Label();
            this.btnProfileChangeChoose = new MoneyFlow.Buttons.ButtonReturn();
            this.btnProfileChangeCancel = new MoneyFlow.Buttons.ButtonReturn();
            this.grpBoxProfileChange = new System.Windows.Forms.GroupBox();
            this.lblErrProfileChangeType = new System.Windows.Forms.Label();
            this.lblErrProfileChangeDescr = new System.Windows.Forms.Label();
            this.lblErrProfileChangeName = new System.Windows.Forms.Label();
            this.radBtnProfilesChangeBusi = new System.Windows.Forms.RadioButton();
            this.radBtnProfilesChangePers = new System.Windows.Forms.RadioButton();
            this.txtBoxProfilesChangeDescr = new System.Windows.Forms.TextBox();
            this.txtBoxProfilesChangeName = new System.Windows.Forms.TextBox();
            this.lblProfilesChangeDescr = new System.Windows.Forms.Label();
            this.lblProfilesChangeType = new System.Windows.Forms.Label();
            this.lblProfilesChangeName = new System.Windows.Forms.Label();
            this.lblProfilesChangeMSG = new System.Windows.Forms.Label();
            this.lblProfilesChangeMSGPic = new System.Windows.Forms.Label();
            this.btnProfileChangeEdit = new MoneyFlow.Buttons.ButtonReturn();
            this.panProfilesMain.SuspendLayout();
            this.panProfilesChange.SuspendLayout();
            this.grpBoxProfileChange.SuspendLayout();
            this.SuspendLayout();
            // 
            // lblProfileCurrentProf
            // 
            this.lblProfileCurrentProf.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.lblProfileCurrentProf.Location = new System.Drawing.Point(103, 12);
            this.lblProfileCurrentProf.Name = "lblProfileCurrentProf";
            this.lblProfileCurrentProf.Size = new System.Drawing.Size(135, 42);
            this.lblProfileCurrentProf.TabIndex = 1;
            // 
            // lblProfileAvail
            // 
            this.lblProfileAvail.AutoSize = true;
            this.lblProfileAvail.Location = new System.Drawing.Point(8, 40);
            this.lblProfileAvail.Name = "lblProfileAvail";
            this.lblProfileAvail.Size = new System.Drawing.Size(89, 13);
            this.lblProfileAvail.TabIndex = 2;
            this.lblProfileAvail.Text = "Available profiles:";
            // 
            // lstViewAvailProfiles
            // 
            this.lstViewAvailProfiles.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.lstViewColProfileName,
            this.lstViewColProfileDesc});
            this.lstViewAvailProfiles.HideSelection = false;
            this.lstViewAvailProfiles.LargeImageList = this.imgListProfile;
            this.lstViewAvailProfiles.Location = new System.Drawing.Point(11, 57);
            this.lstViewAvailProfiles.MultiSelect = false;
            this.lstViewAvailProfiles.Name = "lstViewAvailProfiles";
            this.lstViewAvailProfiles.ShowGroups = false;
            this.lstViewAvailProfiles.Size = new System.Drawing.Size(240, 148);
            this.lstViewAvailProfiles.TabIndex = 3;
            this.toolTip.SetToolTip(this.lstViewAvailProfiles, "Select any of your profile by clicking on it.");
            this.lstViewAvailProfiles.UseCompatibleStateImageBehavior = false;
            this.lstViewAvailProfiles.View = System.Windows.Forms.View.Tile;
            this.lstViewAvailProfiles.SelectedIndexChanged += new System.EventHandler(this.lstViewAvailProfiles_SelectedIndexChanged);
            this.lstViewAvailProfiles.DoubleClick += new System.EventHandler(this.lstViewAvailProfiles_DoubleClick);
            // 
            // lstViewColProfileName
            // 
            this.lstViewColProfileName.Width = 200;
            // 
            // lstViewColProfileDesc
            // 
            this.lstViewColProfileDesc.Width = 230;
            // 
            // imgListProfile
            // 
            this.imgListProfile.ImageStream = ((System.Windows.Forms.ImageListStreamer)(resources.GetObject("imgListProfile.ImageStream")));
            this.imgListProfile.TransparentColor = System.Drawing.Color.Transparent;
            this.imgListProfile.Images.SetKeyName(0, "user_suit.png");
            this.imgListProfile.Images.SetKeyName(1, "user.png");
            this.imgListProfile.Images.SetKeyName(2, "tick.png");
            // 
            // btnProfilesCreate
            // 
            this.btnProfilesCreate.Location = new System.Drawing.Point(261, 82);
            this.btnProfilesCreate.Name = "btnProfilesCreate";
            this.btnProfilesCreate.Size = new System.Drawing.Size(114, 23);
            this.btnProfilesCreate.TabIndex = 4;
            this.btnProfilesCreate.Text = "Create new";
            this.toolTip.SetToolTip(this.btnProfilesCreate, "Create new profile.");
            this.btnProfilesCreate.UseVisualStyleBackColor = true;
            this.btnProfilesCreate.Click += new System.EventHandler(this.btnProfilesCreate_Click);
            // 
            // btnProfilesSwitch
            // 
            this.btnProfilesSwitch.Enabled = false;
            this.btnProfilesSwitch.Location = new System.Drawing.Point(261, 57);
            this.btnProfilesSwitch.Name = "btnProfilesSwitch";
            this.btnProfilesSwitch.Size = new System.Drawing.Size(114, 23);
            this.btnProfilesSwitch.TabIndex = 5;
            this.btnProfilesSwitch.Text = "Switch to profile";
            this.toolTip.SetToolTip(this.btnProfilesSwitch, "Switch to selected profile (if it is not in use right now).");
            this.btnProfilesSwitch.UseVisualStyleBackColor = true;
            this.btnProfilesSwitch.Click += new System.EventHandler(this.btnProfilesSwitch_Click);
            // 
            // lblProfilesCurrent
            // 
            this.lblProfilesCurrent.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.lblProfilesCurrent.Location = new System.Drawing.Point(8, 9);
            this.lblProfilesCurrent.Name = "lblProfilesCurrent";
            this.lblProfilesCurrent.Size = new System.Drawing.Size(89, 19);
            this.lblProfilesCurrent.TabIndex = 0;
            this.lblProfilesCurrent.Text = "Current profile:";
            this.lblProfilesCurrent.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // btnProfilesDelete
            // 
            this.btnProfilesDelete.Enabled = false;
            this.btnProfilesDelete.Image = ((System.Drawing.Image)(resources.GetObject("btnProfilesDelete.Image")));
            this.btnProfilesDelete.ImageAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.btnProfilesDelete.Location = new System.Drawing.Point(313, 173);
            this.btnProfilesDelete.Name = "btnProfilesDelete";
            this.btnProfilesDelete.Size = new System.Drawing.Size(62, 32);
            this.btnProfilesDelete.TabIndex = 10;
            this.btnProfilesDelete.Text = "Delete";
            this.btnProfilesDelete.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.toolTip.SetToolTip(this.btnProfilesDelete, "Delete selected profile and its whole cashflow.");
            this.btnProfilesDelete.UseVisualStyleBackColor = true;
            this.btnProfilesDelete.Click += new System.EventHandler(this.btnProfilesDelete_Click);
            // 
            // btnProfilesEdit
            // 
            this.btnProfilesEdit.Enabled = false;
            this.btnProfilesEdit.Image = ((System.Drawing.Image)(resources.GetObject("btnProfilesEdit.Image")));
            this.btnProfilesEdit.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.btnProfilesEdit.Location = new System.Drawing.Point(261, 173);
            this.btnProfilesEdit.Name = "btnProfilesEdit";
            this.btnProfilesEdit.Size = new System.Drawing.Size(50, 32);
            this.btnProfilesEdit.TabIndex = 6;
            this.btnProfilesEdit.Text = "Edit";
            this.btnProfilesEdit.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.toolTip.SetToolTip(this.btnProfilesEdit, "Edit selected profile details.");
            this.btnProfilesEdit.UseVisualStyleBackColor = true;
            this.btnProfilesEdit.Click += new System.EventHandler(this.btnProfilesEdit_Click);
            // 
            // btnProfilesReturn
            // 
            this.btnProfilesReturn.Image = ((System.Drawing.Image)(resources.GetObject("btnProfilesReturn.Image")));
            this.btnProfilesReturn.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.btnProfilesReturn.Location = new System.Drawing.Point(11, 211);
            this.btnProfilesReturn.Name = "btnProfilesReturn";
            this.btnProfilesReturn.Size = new System.Drawing.Size(66, 25);
            this.btnProfilesReturn.TabIndex = 9;
            this.btnProfilesReturn.Text = "Return";
            this.btnProfilesReturn.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.toolTip.SetToolTip(this.btnProfilesReturn, "Save changes and returns to MoneyFlow overview.");
            this.btnProfilesReturn.UseVisualStyleBackColor = true;
            this.btnProfilesReturn.Click += new System.EventHandler(this.btnProfilesReturn_Click);
            // 
            // lblProfilesMSGPic
            // 
            this.lblProfilesMSGPic.Anchor = System.Windows.Forms.AnchorStyles.Right;
            this.lblProfilesMSGPic.ForeColor = System.Drawing.SystemColors.ControlDarkDark;
            this.lblProfilesMSGPic.Location = new System.Drawing.Point(244, 4);
            this.lblProfilesMSGPic.Name = "lblProfilesMSGPic";
            this.lblProfilesMSGPic.Size = new System.Drawing.Size(36, 49);
            this.lblProfilesMSGPic.TabIndex = 12;
            this.lblProfilesMSGPic.TextAlign = System.Drawing.ContentAlignment.TopCenter;
            // 
            // lblProfilesMSG
            // 
            this.lblProfilesMSG.Anchor = System.Windows.Forms.AnchorStyles.Right;
            this.lblProfilesMSG.ForeColor = System.Drawing.SystemColors.ControlDarkDark;
            this.lblProfilesMSG.Location = new System.Drawing.Point(281, 4);
            this.lblProfilesMSG.Name = "lblProfilesMSG";
            this.lblProfilesMSG.Size = new System.Drawing.Size(96, 49);
            this.lblProfilesMSG.TabIndex = 11;
            this.lblProfilesMSG.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // panProfilesMain
            // 
            this.panProfilesMain.Controls.Add(this.lblProfilesMSGPic);
            this.panProfilesMain.Controls.Add(this.btnProfilesDelete);
            this.panProfilesMain.Controls.Add(this.btnProfilesEdit);
            this.panProfilesMain.Controls.Add(this.btnProfilesReturn);
            this.panProfilesMain.Controls.Add(this.btnProfilesSwitch);
            this.panProfilesMain.Controls.Add(this.btnProfilesCreate);
            this.panProfilesMain.Controls.Add(this.lstViewAvailProfiles);
            this.panProfilesMain.Controls.Add(this.lblProfileAvail);
            this.panProfilesMain.Controls.Add(this.lblProfileCurrentProf);
            this.panProfilesMain.Controls.Add(this.lblProfilesCurrent);
            this.panProfilesMain.Controls.Add(this.lblProfilesMSG);
            this.panProfilesMain.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panProfilesMain.Location = new System.Drawing.Point(0, 0);
            this.panProfilesMain.Name = "panProfilesMain";
            this.panProfilesMain.Size = new System.Drawing.Size(384, 241);
            this.panProfilesMain.TabIndex = 13;
            // 
            // panProfilesChange
            // 
            this.panProfilesChange.Controls.Add(this.lblProfilesChangeReq);
            this.panProfilesChange.Controls.Add(this.btnProfileChangeChoose);
            this.panProfilesChange.Controls.Add(this.btnProfileChangeCancel);
            this.panProfilesChange.Controls.Add(this.grpBoxProfileChange);
            this.panProfilesChange.Controls.Add(this.lblProfilesChangeMSG);
            this.panProfilesChange.Controls.Add(this.lblProfilesChangeMSGPic);
            this.panProfilesChange.Controls.Add(this.btnProfileChangeEdit);
            this.panProfilesChange.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panProfilesChange.Location = new System.Drawing.Point(0, 0);
            this.panProfilesChange.Name = "panProfilesChange";
            this.panProfilesChange.Size = new System.Drawing.Size(384, 241);
            this.panProfilesChange.TabIndex = 13;
            this.panProfilesChange.Visible = false;
            this.panProfilesChange.VisibleChanged += new System.EventHandler(this.panProfilesChange_VisibleChanged);
            // 
            // lblProfilesChangeReq
            // 
            this.lblProfilesChangeReq.AutoSize = true;
            this.lblProfilesChangeReq.Font = new System.Drawing.Font("Microsoft Sans Serif", 6.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.lblProfilesChangeReq.ForeColor = System.Drawing.SystemColors.WindowFrame;
            this.lblProfilesChangeReq.Location = new System.Drawing.Point(327, 204);
            this.lblProfilesChangeReq.Name = "lblProfilesChangeReq";
            this.lblProfilesChangeReq.Size = new System.Drawing.Size(44, 12);
            this.lblProfilesChangeReq.TabIndex = 13;
            this.lblProfilesChangeReq.Text = "* required";
            // 
            // btnProfileChangeChoose
            // 
            this.btnProfileChangeChoose.Image = global::MoneyFlow.Properties.Resources.layout_add;
            this.btnProfileChangeChoose.ImageAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.btnProfileChangeChoose.Location = new System.Drawing.Point(82, 211);
            this.btnProfileChangeChoose.Name = "btnProfileChangeChoose";
            this.btnProfileChangeChoose.Size = new System.Drawing.Size(93, 25);
            this.btnProfileChangeChoose.TabIndex = 15;
            this.btnProfileChangeChoose.Text = "Create Profile";
            this.btnProfileChangeChoose.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.btnProfileChangeChoose.UseVisualStyleBackColor = true;
            this.btnProfileChangeChoose.Click += new System.EventHandler(this.btnProfileChangeChoose_Click);
            // 
            // btnProfileChangeCancel
            // 
            this.btnProfileChangeCancel.Image = global::MoneyFlow.Properties.Resources.cancel;
            this.btnProfileChangeCancel.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.btnProfileChangeCancel.Location = new System.Drawing.Point(12, 211);
            this.btnProfileChangeCancel.Name = "btnProfileChangeCancel";
            this.btnProfileChangeCancel.Size = new System.Drawing.Size(64, 25);
            this.btnProfileChangeCancel.TabIndex = 9;
            this.btnProfileChangeCancel.Text = "Cancel";
            this.btnProfileChangeCancel.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.btnProfileChangeCancel.UseVisualStyleBackColor = true;
            this.btnProfileChangeCancel.Click += new System.EventHandler(this.btnProfileChangeCancel_Click);
            // 
            // grpBoxProfileChange
            // 
            this.grpBoxProfileChange.Controls.Add(this.lblErrProfileChangeType);
            this.grpBoxProfileChange.Controls.Add(this.lblErrProfileChangeDescr);
            this.grpBoxProfileChange.Controls.Add(this.lblErrProfileChangeName);
            this.grpBoxProfileChange.Controls.Add(this.radBtnProfilesChangeBusi);
            this.grpBoxProfileChange.Controls.Add(this.radBtnProfilesChangePers);
            this.grpBoxProfileChange.Controls.Add(this.txtBoxProfilesChangeDescr);
            this.grpBoxProfileChange.Controls.Add(this.txtBoxProfilesChangeName);
            this.grpBoxProfileChange.Controls.Add(this.lblProfilesChangeDescr);
            this.grpBoxProfileChange.Controls.Add(this.lblProfilesChangeType);
            this.grpBoxProfileChange.Controls.Add(this.lblProfilesChangeName);
            this.grpBoxProfileChange.Location = new System.Drawing.Point(12, 59);
            this.grpBoxProfileChange.Name = "grpBoxProfileChange";
            this.grpBoxProfileChange.Size = new System.Drawing.Size(360, 143);
            this.grpBoxProfileChange.TabIndex = 14;
            this.grpBoxProfileChange.TabStop = false;
            this.grpBoxProfileChange.Text = " Profile: ";
            // 
            // lblErrProfileChangeType
            // 
            this.lblErrProfileChangeType.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Italic, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.lblErrProfileChangeType.ForeColor = System.Drawing.Color.Red;
            this.lblErrProfileChangeType.Image = global::MoneyFlow.Properties.Resources.eventlogError;
            this.lblErrProfileChangeType.Location = new System.Drawing.Point(200, 24);
            this.lblErrProfileChangeType.Name = "lblErrProfileChangeType";
            this.lblErrProfileChangeType.Size = new System.Drawing.Size(16, 16);
            this.lblErrProfileChangeType.TabIndex = 11;
            this.lblErrProfileChangeType.Visible = false;
            // 
            // lblErrProfileChangeDescr
            // 
            this.lblErrProfileChangeDescr.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Italic, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.lblErrProfileChangeDescr.ForeColor = System.Drawing.Color.Red;
            this.lblErrProfileChangeDescr.Image = global::MoneyFlow.Properties.Resources.eventlogError;
            this.lblErrProfileChangeDescr.Location = new System.Drawing.Point(200, 111);
            this.lblErrProfileChangeDescr.Name = "lblErrProfileChangeDescr";
            this.lblErrProfileChangeDescr.Size = new System.Drawing.Size(16, 16);
            this.lblErrProfileChangeDescr.TabIndex = 10;
            this.lblErrProfileChangeDescr.Visible = false;
            // 
            // lblErrProfileChangeName
            // 
            this.lblErrProfileChangeName.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Italic, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.lblErrProfileChangeName.ForeColor = System.Drawing.Color.Red;
            this.lblErrProfileChangeName.Image = global::MoneyFlow.Properties.Resources.eventlogError;
            this.lblErrProfileChangeName.Location = new System.Drawing.Point(200, 82);
            this.lblErrProfileChangeName.Name = "lblErrProfileChangeName";
            this.lblErrProfileChangeName.Size = new System.Drawing.Size(16, 16);
            this.lblErrProfileChangeName.TabIndex = 9;
            this.lblErrProfileChangeName.Visible = false;
            // 
            // radBtnProfilesChangeBusi
            // 
            this.radBtnProfilesChangeBusi.AutoSize = true;
            this.radBtnProfilesChangeBusi.Location = new System.Drawing.Point(94, 46);
            this.radBtnProfilesChangeBusi.Name = "radBtnProfilesChangeBusi";
            this.radBtnProfilesChangeBusi.Size = new System.Drawing.Size(67, 17);
            this.radBtnProfilesChangeBusi.TabIndex = 6;
            this.radBtnProfilesChangeBusi.TabStop = true;
            this.radBtnProfilesChangeBusi.Text = "Business";
            this.radBtnProfilesChangeBusi.UseVisualStyleBackColor = true;
            // 
            // radBtnProfilesChangePers
            // 
            this.radBtnProfilesChangePers.AutoSize = true;
            this.radBtnProfilesChangePers.Location = new System.Drawing.Point(94, 23);
            this.radBtnProfilesChangePers.Name = "radBtnProfilesChangePers";
            this.radBtnProfilesChangePers.Size = new System.Drawing.Size(66, 17);
            this.radBtnProfilesChangePers.TabIndex = 5;
            this.radBtnProfilesChangePers.TabStop = true;
            this.radBtnProfilesChangePers.Text = "Personal";
            this.radBtnProfilesChangePers.UseVisualStyleBackColor = true;
            // 
            // txtBoxProfilesChangeDescr
            // 
            this.txtBoxProfilesChangeDescr.Location = new System.Drawing.Point(94, 110);
            this.txtBoxProfilesChangeDescr.Name = "txtBoxProfilesChangeDescr";
            this.txtBoxProfilesChangeDescr.Size = new System.Drawing.Size(100, 20);
            this.txtBoxProfilesChangeDescr.TabIndex = 4;
            // 
            // txtBoxProfilesChangeName
            // 
            this.txtBoxProfilesChangeName.Location = new System.Drawing.Point(94, 81);
            this.txtBoxProfilesChangeName.Name = "txtBoxProfilesChangeName";
            this.txtBoxProfilesChangeName.Size = new System.Drawing.Size(100, 20);
            this.txtBoxProfilesChangeName.TabIndex = 3;
            // 
            // lblProfilesChangeDescr
            // 
            this.lblProfilesChangeDescr.Location = new System.Drawing.Point(15, 113);
            this.lblProfilesChangeDescr.Name = "lblProfilesChangeDescr";
            this.lblProfilesChangeDescr.Size = new System.Drawing.Size(73, 18);
            this.lblProfilesChangeDescr.TabIndex = 2;
            this.lblProfilesChangeDescr.Text = "Description:";
            this.lblProfilesChangeDescr.TextAlign = System.Drawing.ContentAlignment.TopRight;
            // 
            // lblProfilesChangeType
            // 
            this.lblProfilesChangeType.Location = new System.Drawing.Point(12, 25);
            this.lblProfilesChangeType.Name = "lblProfilesChangeType";
            this.lblProfilesChangeType.Size = new System.Drawing.Size(76, 17);
            this.lblProfilesChangeType.TabIndex = 1;
            this.lblProfilesChangeType.Text = "Type*:";
            this.lblProfilesChangeType.TextAlign = System.Drawing.ContentAlignment.TopRight;
            // 
            // lblProfilesChangeName
            // 
            this.lblProfilesChangeName.Location = new System.Drawing.Point(12, 84);
            this.lblProfilesChangeName.Name = "lblProfilesChangeName";
            this.lblProfilesChangeName.Size = new System.Drawing.Size(76, 15);
            this.lblProfilesChangeName.TabIndex = 0;
            this.lblProfilesChangeName.Text = "Name*:";
            this.lblProfilesChangeName.TextAlign = System.Drawing.ContentAlignment.TopRight;
            // 
            // lblProfilesChangeMSG
            // 
            this.lblProfilesChangeMSG.Anchor = System.Windows.Forms.AnchorStyles.Right;
            this.lblProfilesChangeMSG.ForeColor = System.Drawing.SystemColors.ControlDarkDark;
            this.lblProfilesChangeMSG.Location = new System.Drawing.Point(64, 7);
            this.lblProfilesChangeMSG.Name = "lblProfilesChangeMSG";
            this.lblProfilesChangeMSG.Size = new System.Drawing.Size(308, 49);
            this.lblProfilesChangeMSG.TabIndex = 13;
            this.lblProfilesChangeMSG.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // lblProfilesChangeMSGPic
            // 
            this.lblProfilesChangeMSGPic.Anchor = System.Windows.Forms.AnchorStyles.Right;
            this.lblProfilesChangeMSGPic.ForeColor = System.Drawing.SystemColors.ControlDarkDark;
            this.lblProfilesChangeMSGPic.Location = new System.Drawing.Point(24, 7);
            this.lblProfilesChangeMSGPic.Name = "lblProfilesChangeMSGPic";
            this.lblProfilesChangeMSGPic.Size = new System.Drawing.Size(36, 49);
            this.lblProfilesChangeMSGPic.TabIndex = 13;
            this.lblProfilesChangeMSGPic.TextAlign = System.Drawing.ContentAlignment.TopCenter;
            // 
            // btnProfileChangeEdit
            // 
            this.btnProfileChangeEdit.Image = global::MoneyFlow.Properties.Resources.layout_edit;
            this.btnProfileChangeEdit.ImageAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.btnProfileChangeEdit.Location = new System.Drawing.Point(83, 211);
            this.btnProfileChangeEdit.Name = "btnProfileChangeEdit";
            this.btnProfileChangeEdit.Size = new System.Drawing.Size(100, 25);
            this.btnProfileChangeEdit.TabIndex = 16;
            this.btnProfileChangeEdit.Text = "Save Changes";
            this.btnProfileChangeEdit.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.btnProfileChangeEdit.UseVisualStyleBackColor = true;
            this.btnProfileChangeEdit.Visible = false;
            this.btnProfileChangeEdit.Click += new System.EventHandler(this.btnProfileChangeEdit_Click);
            // 
            // ProfileForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(384, 241);
            this.ControlBox = false;
            this.Controls.Add(this.panProfilesChange);
            this.Controls.Add(this.panProfilesMain);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedDialog;
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.MaximizeBox = false;
            this.MinimizeBox = false;
            this.Name = "ProfileForm";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterParent;
            this.Text = "Myflow Profiles";
            this.Load += new System.EventHandler(this.ProfileForm_Load);
            this.panProfilesMain.ResumeLayout(false);
            this.panProfilesMain.PerformLayout();
            this.panProfilesChange.ResumeLayout(false);
            this.panProfilesChange.PerformLayout();
            this.grpBoxProfileChange.ResumeLayout(false);
            this.grpBoxProfileChange.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Label lblProfilesCurrent;
        private System.Windows.Forms.Label lblProfileCurrentProf;
        private System.Windows.Forms.Label lblProfileAvail;
        private System.Windows.Forms.ListView lstViewAvailProfiles;
        private System.Windows.Forms.ImageList imgListProfile;
        private System.Windows.Forms.ColumnHeader lstViewColProfileName;
        private System.Windows.Forms.ColumnHeader lstViewColProfileDesc;
        private System.Windows.Forms.Button btnProfilesCreate;
        private System.Windows.Forms.Button btnProfilesSwitch;
        private MoneyFlow.Buttons.ButtonReturn btnProfilesReturn;
        private MoneyFlow.Buttons.ButtonEdit btnProfilesEdit;
        private MoneyFlow.Buttons.ButtonDelete btnProfilesDelete;
        private System.Windows.Forms.ToolTip toolTip;
        private System.Windows.Forms.Label lblProfilesMSGPic;
        private System.Windows.Forms.Label lblProfilesMSG;
        private System.Windows.Forms.Panel panProfilesMain;
        private System.Windows.Forms.Panel panProfilesChange;
        private System.Windows.Forms.GroupBox grpBoxProfileChange;
        private System.Windows.Forms.Label lblProfilesChangeMSG;
        private System.Windows.Forms.Label lblProfilesChangeMSGPic;
        private System.Windows.Forms.Label lblProfilesChangeName;
        private System.Windows.Forms.Label lblProfilesChangeDescr;
        private System.Windows.Forms.Label lblProfilesChangeType;
        private System.Windows.Forms.TextBox txtBoxProfilesChangeName;
        private System.Windows.Forms.RadioButton radBtnProfilesChangeBusi;
        private System.Windows.Forms.RadioButton radBtnProfilesChangePers;
        private System.Windows.Forms.TextBox txtBoxProfilesChangeDescr;
        private System.Windows.Forms.Label lblErrProfileChangeType;
        private System.Windows.Forms.Label lblErrProfileChangeDescr;
        private System.Windows.Forms.Label lblErrProfileChangeName;
        private MoneyFlow.Buttons.ButtonReturn btnProfileChangeCancel;
        private MoneyFlow.Buttons.ButtonReturn btnProfileChangeChoose;
        private System.Windows.Forms.Label lblProfilesChangeReq;
        private MoneyFlow.Buttons.ButtonReturn btnProfileChangeEdit;
    }
}