namespace MoneyFlow
{
    partial class CashflowItemDialog
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(CashflowItemDialog));
            this.lblProfilesChangeReq = new System.Windows.Forms.Label();
            this.grpCashflowChoose = new System.Windows.Forms.GroupBox();
            this.datPickCashflowChooseDate = new System.Windows.Forms.DateTimePicker();
            this.lblErrCashflowChooseDate = new System.Windows.Forms.Label();
            this.lblCashflowChooseDate = new System.Windows.Forms.Label();
            this.lblErrCashflowChooseDescr = new System.Windows.Forms.Label();
            this.txtBoxCashflowChooseDescr = new System.Windows.Forms.TextBox();
            this.lblCashflowChooseDesc = new System.Windows.Forms.Label();
            this.lblErrCashflowChooseCash = new System.Windows.Forms.Label();
            this.radBtnCashflowChooseOut = new System.Windows.Forms.RadioButton();
            this.radBtnCashflowChooseInc = new System.Windows.Forms.RadioButton();
            this.lblCashflowChooseCashType = new System.Windows.Forms.Label();
            this.lblErrCashflowChooseCateg = new System.Windows.Forms.Label();
            this.lblErrCashflowChooseValue = new System.Windows.Forms.Label();
            this.txtBoxCashflowChooseVal = new System.Windows.Forms.TextBox();
            this.cmbBoxCashflowChooseCateg = new System.Windows.Forms.ComboBox();
            this.lblCashflowChooseValue = new System.Windows.Forms.Label();
            this.lblCashflowChooseCateg = new System.Windows.Forms.Label();
            this.lblCashflowChooseMSG = new System.Windows.Forms.Label();
            this.lblCashflowChooseMSGPic = new System.Windows.Forms.Label();
            this.toolTip = new System.Windows.Forms.ToolTip(this.components);
            this.btnCashflowChooseCancel = new MoneyFlow.Buttons.ButtonReturn();
            this.btnCashflowChooseAdd = new MoneyFlow.Buttons.ButtonReturn();
            this.btnCashflowChooseSave = new MoneyFlow.Buttons.ButtonReturn();
            this.grpCashflowChoose.SuspendLayout();
            this.SuspendLayout();
            // 
            // lblProfilesChangeReq
            // 
            this.lblProfilesChangeReq.AutoSize = true;
            this.lblProfilesChangeReq.Font = new System.Drawing.Font("Microsoft Sans Serif", 6.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.lblProfilesChangeReq.ForeColor = System.Drawing.SystemColors.WindowFrame;
            this.lblProfilesChangeReq.Location = new System.Drawing.Point(306, 254);
            this.lblProfilesChangeReq.Name = "lblProfilesChangeReq";
            this.lblProfilesChangeReq.Size = new System.Drawing.Size(44, 12);
            this.lblProfilesChangeReq.TabIndex = 20;
            this.lblProfilesChangeReq.Text = "* required";
            // 
            // grpCashflowChoose
            // 
            this.grpCashflowChoose.Controls.Add(this.datPickCashflowChooseDate);
            this.grpCashflowChoose.Controls.Add(this.lblErrCashflowChooseDate);
            this.grpCashflowChoose.Controls.Add(this.lblCashflowChooseDate);
            this.grpCashflowChoose.Controls.Add(this.lblErrCashflowChooseDescr);
            this.grpCashflowChoose.Controls.Add(this.txtBoxCashflowChooseDescr);
            this.grpCashflowChoose.Controls.Add(this.lblCashflowChooseDesc);
            this.grpCashflowChoose.Controls.Add(this.lblErrCashflowChooseCash);
            this.grpCashflowChoose.Controls.Add(this.radBtnCashflowChooseOut);
            this.grpCashflowChoose.Controls.Add(this.radBtnCashflowChooseInc);
            this.grpCashflowChoose.Controls.Add(this.lblCashflowChooseCashType);
            this.grpCashflowChoose.Controls.Add(this.lblErrCashflowChooseCateg);
            this.grpCashflowChoose.Controls.Add(this.lblErrCashflowChooseValue);
            this.grpCashflowChoose.Controls.Add(this.txtBoxCashflowChooseVal);
            this.grpCashflowChoose.Controls.Add(this.cmbBoxCashflowChooseCateg);
            this.grpCashflowChoose.Controls.Add(this.lblCashflowChooseValue);
            this.grpCashflowChoose.Controls.Add(this.lblCashflowChooseCateg);
            this.grpCashflowChoose.Location = new System.Drawing.Point(8, 59);
            this.grpCashflowChoose.Name = "grpCashflowChoose";
            this.grpCashflowChoose.Size = new System.Drawing.Size(342, 194);
            this.grpCashflowChoose.TabIndex = 20;
            this.grpCashflowChoose.TabStop = false;
            this.grpCashflowChoose.Text = " Cashflow item: ";
            // 
            // datPickCashflowChooseDate
            // 
            this.datPickCashflowChooseDate.Location = new System.Drawing.Point(105, 25);
            this.datPickCashflowChooseDate.MinDate = new System.DateTime(2000, 1, 1, 0, 0, 0, 0);
            this.datPickCashflowChooseDate.Name = "datPickCashflowChooseDate";
            this.datPickCashflowChooseDate.Size = new System.Drawing.Size(154, 20);
            this.datPickCashflowChooseDate.TabIndex = 1;
            this.toolTip.SetToolTip(this.datPickCashflowChooseDate, "Date of cashflow item payment.");
            // 
            // lblErrCashflowChooseDate
            // 
            this.lblErrCashflowChooseDate.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Italic, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.lblErrCashflowChooseDate.ForeColor = System.Drawing.Color.Red;
            this.lblErrCashflowChooseDate.Image = global::MoneyFlow.Properties.Resources.eventlogError;
            this.lblErrCashflowChooseDate.Location = new System.Drawing.Point(266, 28);
            this.lblErrCashflowChooseDate.Name = "lblErrCashflowChooseDate";
            this.lblErrCashflowChooseDate.Size = new System.Drawing.Size(16, 16);
            this.lblErrCashflowChooseDate.TabIndex = 20;
            this.lblErrCashflowChooseDate.Visible = false;
            // 
            // lblCashflowChooseDate
            // 
            this.lblCashflowChooseDate.Location = new System.Drawing.Point(5, 26);
            this.lblCashflowChooseDate.Name = "lblCashflowChooseDate";
            this.lblCashflowChooseDate.Size = new System.Drawing.Size(94, 17);
            this.lblCashflowChooseDate.TabIndex = 20;
            this.lblCashflowChooseDate.Text = "Date of payment*:";
            this.lblCashflowChooseDate.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // lblErrCashflowChooseDescr
            // 
            this.lblErrCashflowChooseDescr.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Italic, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.lblErrCashflowChooseDescr.ForeColor = System.Drawing.Color.Red;
            this.lblErrCashflowChooseDescr.Image = global::MoneyFlow.Properties.Resources.eventlogError;
            this.lblErrCashflowChooseDescr.Location = new System.Drawing.Point(266, 165);
            this.lblErrCashflowChooseDescr.Name = "lblErrCashflowChooseDescr";
            this.lblErrCashflowChooseDescr.Size = new System.Drawing.Size(16, 16);
            this.lblErrCashflowChooseDescr.TabIndex = 20;
            this.lblErrCashflowChooseDescr.Visible = false;
            // 
            // txtBoxCashflowChooseDescr
            // 
            this.txtBoxCashflowChooseDescr.Location = new System.Drawing.Point(105, 163);
            this.txtBoxCashflowChooseDescr.Name = "txtBoxCashflowChooseDescr";
            this.txtBoxCashflowChooseDescr.Size = new System.Drawing.Size(154, 20);
            this.txtBoxCashflowChooseDescr.TabIndex = 6;
            this.toolTip.SetToolTip(this.txtBoxCashflowChooseDescr, "Closer item description.");
            // 
            // lblCashflowChooseDesc
            // 
            this.lblCashflowChooseDesc.Location = new System.Drawing.Point(20, 163);
            this.lblCashflowChooseDesc.Name = "lblCashflowChooseDesc";
            this.lblCashflowChooseDesc.Size = new System.Drawing.Size(79, 17);
            this.lblCashflowChooseDesc.TabIndex = 20;
            this.lblCashflowChooseDesc.Text = "Description:";
            this.lblCashflowChooseDesc.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // lblErrCashflowChooseCash
            // 
            this.lblErrCashflowChooseCash.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Italic, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.lblErrCashflowChooseCash.ForeColor = System.Drawing.Color.Red;
            this.lblErrCashflowChooseCash.Image = global::MoneyFlow.Properties.Resources.eventlogError;
            this.lblErrCashflowChooseCash.Location = new System.Drawing.Point(266, 56);
            this.lblErrCashflowChooseCash.Name = "lblErrCashflowChooseCash";
            this.lblErrCashflowChooseCash.Size = new System.Drawing.Size(16, 16);
            this.lblErrCashflowChooseCash.TabIndex = 20;
            this.lblErrCashflowChooseCash.Visible = false;
            // 
            // radBtnCashflowChooseOut
            // 
            this.radBtnCashflowChooseOut.Image = global::MoneyFlow.Properties.Resources.money_delete;
            this.radBtnCashflowChooseOut.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.radBtnCashflowChooseOut.Location = new System.Drawing.Point(105, 77);
            this.radBtnCashflowChooseOut.Name = "radBtnCashflowChooseOut";
            this.radBtnCashflowChooseOut.Size = new System.Drawing.Size(85, 24);
            this.radBtnCashflowChooseOut.TabIndex = 3;
            this.radBtnCashflowChooseOut.TabStop = true;
            this.radBtnCashflowChooseOut.Text = "Outcome";
            this.radBtnCashflowChooseOut.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.toolTip.SetToolTip(this.radBtnCashflowChooseOut, "Outcome item.");
            this.radBtnCashflowChooseOut.UseVisualStyleBackColor = true;
            // 
            // radBtnCashflowChooseInc
            // 
            this.radBtnCashflowChooseInc.Image = global::MoneyFlow.Properties.Resources.money_add;
            this.radBtnCashflowChooseInc.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.radBtnCashflowChooseInc.Location = new System.Drawing.Point(105, 51);
            this.radBtnCashflowChooseInc.Name = "radBtnCashflowChooseInc";
            this.radBtnCashflowChooseInc.Size = new System.Drawing.Size(76, 24);
            this.radBtnCashflowChooseInc.TabIndex = 2;
            this.radBtnCashflowChooseInc.TabStop = true;
            this.radBtnCashflowChooseInc.Text = "Income";
            this.radBtnCashflowChooseInc.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.toolTip.SetToolTip(this.radBtnCashflowChooseInc, "Income item.");
            this.radBtnCashflowChooseInc.UseVisualStyleBackColor = true;
            // 
            // lblCashflowChooseCashType
            // 
            this.lblCashflowChooseCashType.Location = new System.Drawing.Point(17, 53);
            this.lblCashflowChooseCashType.Name = "lblCashflowChooseCashType";
            this.lblCashflowChooseCashType.Size = new System.Drawing.Size(82, 18);
            this.lblCashflowChooseCashType.TabIndex = 20;
            this.lblCashflowChooseCashType.Text = "Cashflow type*:";
            this.lblCashflowChooseCashType.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // lblErrCashflowChooseCateg
            // 
            this.lblErrCashflowChooseCateg.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Italic, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.lblErrCashflowChooseCateg.ForeColor = System.Drawing.Color.Red;
            this.lblErrCashflowChooseCateg.Image = global::MoneyFlow.Properties.Resources.eventlogError;
            this.lblErrCashflowChooseCateg.Location = new System.Drawing.Point(266, 107);
            this.lblErrCashflowChooseCateg.Name = "lblErrCashflowChooseCateg";
            this.lblErrCashflowChooseCateg.Size = new System.Drawing.Size(16, 16);
            this.lblErrCashflowChooseCateg.TabIndex = 20;
            this.lblErrCashflowChooseCateg.Visible = false;
            // 
            // lblErrCashflowChooseValue
            // 
            this.lblErrCashflowChooseValue.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Italic, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.lblErrCashflowChooseValue.ForeColor = System.Drawing.Color.Red;
            this.lblErrCashflowChooseValue.Image = global::MoneyFlow.Properties.Resources.eventlogError;
            this.lblErrCashflowChooseValue.Location = new System.Drawing.Point(266, 137);
            this.lblErrCashflowChooseValue.Name = "lblErrCashflowChooseValue";
            this.lblErrCashflowChooseValue.Size = new System.Drawing.Size(16, 16);
            this.lblErrCashflowChooseValue.TabIndex = 20;
            this.lblErrCashflowChooseValue.Visible = false;
            // 
            // txtBoxCashflowChooseVal
            // 
            this.txtBoxCashflowChooseVal.Location = new System.Drawing.Point(105, 135);
            this.txtBoxCashflowChooseVal.Name = "txtBoxCashflowChooseVal";
            this.txtBoxCashflowChooseVal.Size = new System.Drawing.Size(100, 20);
            this.txtBoxCashflowChooseVal.TabIndex = 5;
            this.toolTip.SetToolTip(this.txtBoxCashflowChooseVal, "Income or outcome value.");
            // 
            // cmbBoxCashflowChooseCateg
            // 
            this.cmbBoxCashflowChooseCateg.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cmbBoxCashflowChooseCateg.FormattingEnabled = true;
            this.cmbBoxCashflowChooseCateg.Location = new System.Drawing.Point(105, 104);
            this.cmbBoxCashflowChooseCateg.MaxDropDownItems = 15;
            this.cmbBoxCashflowChooseCateg.Name = "cmbBoxCashflowChooseCateg";
            this.cmbBoxCashflowChooseCateg.Size = new System.Drawing.Size(154, 21);
            this.cmbBoxCashflowChooseCateg.TabIndex = 4;
            this.toolTip.SetToolTip(this.cmbBoxCashflowChooseCateg, "Select category to which you want to add this item.");
            // 
            // lblCashflowChooseValue
            // 
            this.lblCashflowChooseValue.Location = new System.Drawing.Point(20, 135);
            this.lblCashflowChooseValue.Name = "lblCashflowChooseValue";
            this.lblCashflowChooseValue.Size = new System.Drawing.Size(79, 17);
            this.lblCashflowChooseValue.TabIndex = 20;
            this.lblCashflowChooseValue.Text = "Value*:";
            this.lblCashflowChooseValue.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // lblCashflowChooseCateg
            // 
            this.lblCashflowChooseCateg.Location = new System.Drawing.Point(20, 105);
            this.lblCashflowChooseCateg.Name = "lblCashflowChooseCateg";
            this.lblCashflowChooseCateg.Size = new System.Drawing.Size(79, 17);
            this.lblCashflowChooseCateg.TabIndex = 20;
            this.lblCashflowChooseCateg.Text = "To category*:";
            this.lblCashflowChooseCateg.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // lblCashflowChooseMSG
            // 
            this.lblCashflowChooseMSG.Anchor = System.Windows.Forms.AnchorStyles.Right;
            this.lblCashflowChooseMSG.ForeColor = System.Drawing.SystemColors.ControlDarkDark;
            this.lblCashflowChooseMSG.Location = new System.Drawing.Point(54, 7);
            this.lblCashflowChooseMSG.Name = "lblCashflowChooseMSG";
            this.lblCashflowChooseMSG.Size = new System.Drawing.Size(296, 49);
            this.lblCashflowChooseMSG.TabIndex = 20;
            this.lblCashflowChooseMSG.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // lblCashflowChooseMSGPic
            // 
            this.lblCashflowChooseMSGPic.Anchor = System.Windows.Forms.AnchorStyles.Right;
            this.lblCashflowChooseMSGPic.ForeColor = System.Drawing.SystemColors.ControlDarkDark;
            this.lblCashflowChooseMSGPic.Location = new System.Drawing.Point(12, 7);
            this.lblCashflowChooseMSGPic.Name = "lblCashflowChooseMSGPic";
            this.lblCashflowChooseMSGPic.Size = new System.Drawing.Size(36, 49);
            this.lblCashflowChooseMSGPic.TabIndex = 20;
            this.lblCashflowChooseMSGPic.TextAlign = System.Drawing.ContentAlignment.TopCenter;
            // 
            // btnCashflowChooseCancel
            // 
            this.btnCashflowChooseCancel.Image = ((System.Drawing.Image)(resources.GetObject("btnCashflowChooseCancel.Image")));
            this.btnCashflowChooseCancel.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.btnCashflowChooseCancel.Location = new System.Drawing.Point(8, 262);
            this.btnCashflowChooseCancel.Name = "btnCashflowChooseCancel";
            this.btnCashflowChooseCancel.Size = new System.Drawing.Size(66, 25);
            this.btnCashflowChooseCancel.TabIndex = 8;
            this.btnCashflowChooseCancel.Text = "Return";
            this.btnCashflowChooseCancel.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.toolTip.SetToolTip(this.btnCashflowChooseCancel, "Returns to MoneyFlow main.");
            this.btnCashflowChooseCancel.UseVisualStyleBackColor = true;
            this.btnCashflowChooseCancel.Click += new System.EventHandler(this.btnCashflowChooseCancel_Click_1);
            // 
            // btnCashflowChooseAdd
            // 
            this.btnCashflowChooseAdd.Image = global::MoneyFlow.Properties.Resources.page_white_add;
            this.btnCashflowChooseAdd.ImageAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.btnCashflowChooseAdd.Location = new System.Drawing.Point(77, 262);
            this.btnCashflowChooseAdd.Name = "btnCashflowChooseAdd";
            this.btnCashflowChooseAdd.Size = new System.Drawing.Size(93, 25);
            this.btnCashflowChooseAdd.TabIndex = 7;
            this.btnCashflowChooseAdd.Text = "Add new item";
            this.btnCashflowChooseAdd.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.toolTip.SetToolTip(this.btnCashflowChooseAdd, "Creates and adds new cashflow item.");
            this.btnCashflowChooseAdd.UseVisualStyleBackColor = true;
            this.btnCashflowChooseAdd.Click += new System.EventHandler(this.btnCashflowChooseAdd_Click);
            // 
            // btnCashflowChooseSave
            // 
            this.btnCashflowChooseSave.Image = global::MoneyFlow.Properties.Resources.page_edit;
            this.btnCashflowChooseSave.ImageAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.btnCashflowChooseSave.Location = new System.Drawing.Point(77, 262);
            this.btnCashflowChooseSave.Name = "btnCashflowChooseSave";
            this.btnCashflowChooseSave.Size = new System.Drawing.Size(100, 25);
            this.btnCashflowChooseSave.TabIndex = 7;
            this.btnCashflowChooseSave.Text = "Save Changes";
            this.btnCashflowChooseSave.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.toolTip.SetToolTip(this.btnCashflowChooseSave, "Saves all made changes.");
            this.btnCashflowChooseSave.UseVisualStyleBackColor = true;
            this.btnCashflowChooseSave.Visible = false;
            this.btnCashflowChooseSave.Click += new System.EventHandler(this.btnCashflowChooseSave_Click);
            // 
            // CashflowItemDialog
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(359, 295);
            this.ControlBox = false;
            this.Controls.Add(this.btnCashflowChooseCancel);
            this.Controls.Add(this.lblProfilesChangeReq);
            this.Controls.Add(this.btnCashflowChooseAdd);
            this.Controls.Add(this.btnCashflowChooseSave);
            this.Controls.Add(this.grpCashflowChoose);
            this.Controls.Add(this.lblCashflowChooseMSG);
            this.Controls.Add(this.lblCashflowChooseMSGPic);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.Name = "CashflowItemDialog";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterParent;
            this.Text = "MyFlow Item";
            this.Load += new System.EventHandler(this.CashflowItemDialog_Load);
            this.grpCashflowChoose.ResumeLayout(false);
            this.grpCashflowChoose.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label lblProfilesChangeReq;
        private MoneyFlow.Buttons.ButtonReturn btnCashflowChooseAdd;
        private MoneyFlow.Buttons.ButtonReturn btnCashflowChooseSave;
        private System.Windows.Forms.ToolTip toolTip;
        private System.Windows.Forms.GroupBox grpCashflowChoose;
        private System.Windows.Forms.Label lblCashflowChooseMSG;
        private System.Windows.Forms.Label lblCashflowChooseMSGPic;
        private System.Windows.Forms.Label lblErrCashflowChooseCateg;
        private System.Windows.Forms.Label lblErrCashflowChooseValue;
        private System.Windows.Forms.TextBox txtBoxCashflowChooseVal;
        private System.Windows.Forms.ComboBox cmbBoxCashflowChooseCateg;
        private System.Windows.Forms.Label lblCashflowChooseValue;
        private System.Windows.Forms.Label lblCashflowChooseCateg;
        private System.Windows.Forms.Label lblErrCashflowChooseDate;
        private System.Windows.Forms.Label lblCashflowChooseDate;
        private System.Windows.Forms.Label lblErrCashflowChooseDescr;
        private System.Windows.Forms.TextBox txtBoxCashflowChooseDescr;
        private System.Windows.Forms.Label lblCashflowChooseDesc;
        private System.Windows.Forms.Label lblErrCashflowChooseCash;
        private System.Windows.Forms.RadioButton radBtnCashflowChooseOut;
        private System.Windows.Forms.RadioButton radBtnCashflowChooseInc;
        private System.Windows.Forms.Label lblCashflowChooseCashType;
        private System.Windows.Forms.DateTimePicker datPickCashflowChooseDate;
        private MoneyFlow.Buttons.ButtonReturn btnCashflowChooseCancel;
    }
}