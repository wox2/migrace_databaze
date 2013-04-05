namespace MoneyFlow
{
    partial class CategoryForm
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(CategoryForm));
            System.Windows.Forms.TreeNode treeNode1 = new System.Windows.Forms.TreeNode("Drogy");
            System.Windows.Forms.TreeNode treeNode2 = new System.Windows.Forms.TreeNode("Chlast");
            System.Windows.Forms.TreeNode treeNode3 = new System.Windows.Forms.TreeNode("Zábava", new System.Windows.Forms.TreeNode[] {
            treeNode1,
            treeNode2});
            System.Windows.Forms.TreeNode treeNode4 = new System.Windows.Forms.TreeNode("Fastfood");
            System.Windows.Forms.TreeNode treeNode5 = new System.Windows.Forms.TreeNode("Domácnost");
            System.Windows.Forms.TreeNode treeNode6 = new System.Windows.Forms.TreeNode("Jídlo", new System.Windows.Forms.TreeNode[] {
            treeNode4,
            treeNode5});
            this.toolTip = new System.Windows.Forms.ToolTip(this.components);
            this.btnCategsCreate = new System.Windows.Forms.Button();
            this.btnCategsAdd = new System.Windows.Forms.Button();
            this.cmbBoxCategsCreateParent = new System.Windows.Forms.ComboBox();
            this.txtBoxCategsCreateDescr = new System.Windows.Forms.TextBox();
            this.txtBoxCategsCreateName = new System.Windows.Forms.TextBox();
            this.btnCategsSelectedUp = new System.Windows.Forms.Button();
            this.btnCategsSelectedDown = new System.Windows.Forms.Button();
            this.btnCategsCreateNew = new MoneyFlow.Buttons.ButtonReturn();
            this.btnCategsCreateEdit = new MoneyFlow.Buttons.ButtonReturn();
            this.btnCategsCreateCancel = new MoneyFlow.Buttons.ButtonReturn();
            this.btnCategsDelete = new MoneyFlow.Buttons.ButtonDelete();
            this.btnCategsEdit = new MoneyFlow.Buttons.ButtonEdit();
            this.btnCategsBack = new MoneyFlow.Buttons.ButtonReturn();
            this.panCategsMain = new System.Windows.Forms.Panel();
            this.lblCategsMSGPic = new System.Windows.Forms.Label();
            this.lblCategsMSG = new System.Windows.Forms.Label();
            this.lblCategsAvailCategs = new System.Windows.Forms.Label();
            this.treViewCategsList = new System.Windows.Forms.TreeView();
            this.imgList = new System.Windows.Forms.ImageList(this.components);
            this.panCategsCreate = new System.Windows.Forms.Panel();
            this.lblProfilesChangeReq = new System.Windows.Forms.Label();
            this.grpCategsCreate = new System.Windows.Forms.GroupBox();
            this.lblErrCategsCreateParent = new System.Windows.Forms.Label();
            this.lblCategsCreateParent = new System.Windows.Forms.Label();
            this.lblErrCategsCreateDescr = new System.Windows.Forms.Label();
            this.lblErrCategsCreateName = new System.Windows.Forms.Label();
            this.lblCategsCreateDesc = new System.Windows.Forms.Label();
            this.lblCategsCreateName = new System.Windows.Forms.Label();
            this.lblCategsCreateMSG = new System.Windows.Forms.Label();
            this.lblCategsCreateMSGPic = new System.Windows.Forms.Label();
            this.panCategsMain.SuspendLayout();
            this.panCategsCreate.SuspendLayout();
            this.grpCategsCreate.SuspendLayout();
            this.SuspendLayout();
            // 
            // btnCategsCreate
            // 
            this.btnCategsCreate.Location = new System.Drawing.Point(284, 58);
            this.btnCategsCreate.Name = "btnCategsCreate";
            this.btnCategsCreate.Size = new System.Drawing.Size(114, 23);
            this.btnCategsCreate.TabIndex = 11;
            this.btnCategsCreate.Text = "Create category";
            this.toolTip.SetToolTip(this.btnCategsCreate, "Create new main category.");
            this.btnCategsCreate.UseVisualStyleBackColor = true;
            this.btnCategsCreate.Click += new System.EventHandler(this.btnCategsCreate_Click);
            // 
            // btnCategsAdd
            // 
            this.btnCategsAdd.Enabled = false;
            this.btnCategsAdd.Location = new System.Drawing.Point(284, 84);
            this.btnCategsAdd.Name = "btnCategsAdd";
            this.btnCategsAdd.Size = new System.Drawing.Size(114, 23);
            this.btnCategsAdd.TabIndex = 19;
            this.btnCategsAdd.Text = "Add subcategory";
            this.toolTip.SetToolTip(this.btnCategsAdd, "Create new subcategory to selected category.");
            this.btnCategsAdd.UseVisualStyleBackColor = true;
            this.btnCategsAdd.Click += new System.EventHandler(this.btnCategsAdd_Click);
            // 
            // cmbBoxCategsCreateParent
            // 
            this.cmbBoxCategsCreateParent.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cmbBoxCategsCreateParent.Location = new System.Drawing.Point(102, 87);
            this.cmbBoxCategsCreateParent.Name = "cmbBoxCategsCreateParent";
            this.cmbBoxCategsCreateParent.Size = new System.Drawing.Size(122, 21);
            this.cmbBoxCategsCreateParent.TabIndex = 12;
            this.toolTip.SetToolTip(this.cmbBoxCategsCreateParent, "Choose parent category of this new subcategory.");
            // 
            // txtBoxCategsCreateDescr
            // 
            this.txtBoxCategsCreateDescr.Location = new System.Drawing.Point(102, 56);
            this.txtBoxCategsCreateDescr.Name = "txtBoxCategsCreateDescr";
            this.txtBoxCategsCreateDescr.Size = new System.Drawing.Size(122, 20);
            this.txtBoxCategsCreateDescr.TabIndex = 4;
            this.toolTip.SetToolTip(this.txtBoxCategsCreateDescr, "Short category description.");
            // 
            // txtBoxCategsCreateName
            // 
            this.txtBoxCategsCreateName.Location = new System.Drawing.Point(102, 26);
            this.txtBoxCategsCreateName.Name = "txtBoxCategsCreateName";
            this.txtBoxCategsCreateName.Size = new System.Drawing.Size(100, 20);
            this.txtBoxCategsCreateName.TabIndex = 3;
            this.toolTip.SetToolTip(this.txtBoxCategsCreateName, "Name of new category.");
            // 
            // btnCategsSelectedUp
            // 
            this.btnCategsSelectedUp.Enabled = false;
            this.btnCategsSelectedUp.Image = global::MoneyFlow.Properties.Resources.arrow_up;
            this.btnCategsSelectedUp.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.btnCategsSelectedUp.Location = new System.Drawing.Point(284, 159);
            this.btnCategsSelectedUp.Name = "btnCategsSelectedUp";
            this.btnCategsSelectedUp.Size = new System.Drawing.Size(114, 23);
            this.btnCategsSelectedUp.TabIndex = 18;
            this.btnCategsSelectedUp.Text = "To upper category";
            this.btnCategsSelectedUp.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.toolTip.SetToolTip(this.btnCategsSelectedUp, "Moves subcategory to upper category.");
            this.btnCategsSelectedUp.UseVisualStyleBackColor = true;
            this.btnCategsSelectedUp.Click += new System.EventHandler(this.btnCategsSelectedUp_Click);
            // 
            // btnCategsSelectedDown
            // 
            this.btnCategsSelectedDown.Enabled = false;
            this.btnCategsSelectedDown.Image = global::MoneyFlow.Properties.Resources.arrow_down;
            this.btnCategsSelectedDown.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.btnCategsSelectedDown.Location = new System.Drawing.Point(284, 185);
            this.btnCategsSelectedDown.Name = "btnCategsSelectedDown";
            this.btnCategsSelectedDown.Size = new System.Drawing.Size(114, 23);
            this.btnCategsSelectedDown.TabIndex = 17;
            this.btnCategsSelectedDown.Text = "To lower category";
            this.btnCategsSelectedDown.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.toolTip.SetToolTip(this.btnCategsSelectedDown, "Moves subcategory to lower category.");
            this.btnCategsSelectedDown.UseVisualStyleBackColor = true;
            this.btnCategsSelectedDown.Click += new System.EventHandler(this.btnCategsSelectedDown_Click);
            // 
            // btnCategsCreateNew
            // 
            this.btnCategsCreateNew.Image = global::MoneyFlow.Properties.Resources.package_add;
            this.btnCategsCreateNew.ImageAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.btnCategsCreateNew.Location = new System.Drawing.Point(81, 197);
            this.btnCategsCreateNew.Name = "btnCategsCreateNew";
            this.btnCategsCreateNew.Size = new System.Drawing.Size(109, 25);
            this.btnCategsCreateNew.TabIndex = 22;
            this.btnCategsCreateNew.Text = "Create Category";
            this.btnCategsCreateNew.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.toolTip.SetToolTip(this.btnCategsCreateNew, "Create new main Category.");
            this.btnCategsCreateNew.UseVisualStyleBackColor = true;
            this.btnCategsCreateNew.Click += new System.EventHandler(this.btnBoxCategsCreate_Click);
            // 
            // btnCategsCreateEdit
            // 
            this.btnCategsCreateEdit.Image = global::MoneyFlow.Properties.Resources.folder_edit;
            this.btnCategsCreateEdit.ImageAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.btnCategsCreateEdit.Location = new System.Drawing.Point(83, 197);
            this.btnCategsCreateEdit.Name = "btnCategsCreateEdit";
            this.btnCategsCreateEdit.Size = new System.Drawing.Size(100, 25);
            this.btnCategsCreateEdit.TabIndex = 23;
            this.btnCategsCreateEdit.Text = "Save Changes";
            this.btnCategsCreateEdit.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.toolTip.SetToolTip(this.btnCategsCreateEdit, "Saves all made changes.");
            this.btnCategsCreateEdit.UseVisualStyleBackColor = true;
            this.btnCategsCreateEdit.Visible = false;
            this.btnCategsCreateEdit.Click += new System.EventHandler(this.btnCategsCreateEdit_Click);
            // 
            // btnCategsCreateCancel
            // 
            this.btnCategsCreateCancel.Image = global::MoneyFlow.Properties.Resources.cancel;
            this.btnCategsCreateCancel.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.btnCategsCreateCancel.Location = new System.Drawing.Point(11, 197);
            this.btnCategsCreateCancel.Name = "btnCategsCreateCancel";
            this.btnCategsCreateCancel.Size = new System.Drawing.Size(64, 25);
            this.btnCategsCreateCancel.TabIndex = 17;
            this.btnCategsCreateCancel.Text = "Cancel";
            this.btnCategsCreateCancel.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.toolTip.SetToolTip(this.btnCategsCreateCancel, "Cancel creation and return back to Categories overview.");
            this.btnCategsCreateCancel.UseVisualStyleBackColor = true;
            this.btnCategsCreateCancel.Click += new System.EventHandler(this.btnBoxCategsCreateCancel_Click);
            // 
            // btnCategsDelete
            // 
            this.btnCategsDelete.Enabled = false;
            this.btnCategsDelete.Image = ((System.Drawing.Image)(resources.GetObject("btnCategsDelete.Image")));
            this.btnCategsDelete.ImageAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.btnCategsDelete.Location = new System.Drawing.Point(336, 211);
            this.btnCategsDelete.Name = "btnCategsDelete";
            this.btnCategsDelete.Size = new System.Drawing.Size(62, 32);
            this.btnCategsDelete.TabIndex = 0;
            this.btnCategsDelete.TabStop = false;
            this.btnCategsDelete.Text = "Delete";
            this.btnCategsDelete.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.toolTip.SetToolTip(this.btnCategsDelete, "Delete selected category, its subcategories and whole cashflow.");
            this.btnCategsDelete.UseVisualStyleBackColor = true;
            this.btnCategsDelete.Click += new System.EventHandler(this.btnCategsDelete_Click);
            // 
            // btnCategsEdit
            // 
            this.btnCategsEdit.Enabled = false;
            this.btnCategsEdit.Image = ((System.Drawing.Image)(resources.GetObject("btnCategsEdit.Image")));
            this.btnCategsEdit.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.btnCategsEdit.Location = new System.Drawing.Point(284, 211);
            this.btnCategsEdit.Name = "btnCategsEdit";
            this.btnCategsEdit.Size = new System.Drawing.Size(50, 32);
            this.btnCategsEdit.TabIndex = 13;
            this.btnCategsEdit.Text = "Edit";
            this.btnCategsEdit.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.toolTip.SetToolTip(this.btnCategsEdit, "Edit selected category details.");
            this.btnCategsEdit.UseVisualStyleBackColor = true;
            this.btnCategsEdit.Click += new System.EventHandler(this.btnCategsEdit_Click);
            // 
            // btnCategsBack
            // 
            this.btnCategsBack.Image = ((System.Drawing.Image)(resources.GetObject("btnCategsBack.Image")));
            this.btnCategsBack.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.btnCategsBack.Location = new System.Drawing.Point(11, 249);
            this.btnCategsBack.Name = "btnCategsBack";
            this.btnCategsBack.Size = new System.Drawing.Size(66, 25);
            this.btnCategsBack.TabIndex = 14;
            this.btnCategsBack.Text = "Return";
            this.btnCategsBack.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.toolTip.SetToolTip(this.btnCategsBack, "Save changes and returns to MoneyFlow overview.");
            this.btnCategsBack.UseVisualStyleBackColor = true;
            this.btnCategsBack.Click += new System.EventHandler(this.btnCategsBack_Click);
            // 
            // panCategsMain
            // 
            this.panCategsMain.Controls.Add(this.lblCategsMSGPic);
            this.panCategsMain.Controls.Add(this.lblCategsMSG);
            this.panCategsMain.Controls.Add(this.lblCategsAvailCategs);
            this.panCategsMain.Controls.Add(this.btnCategsAdd);
            this.panCategsMain.Controls.Add(this.btnCategsSelectedUp);
            this.panCategsMain.Controls.Add(this.btnCategsSelectedDown);
            this.panCategsMain.Controls.Add(this.treViewCategsList);
            this.panCategsMain.Controls.Add(this.btnCategsDelete);
            this.panCategsMain.Controls.Add(this.btnCategsEdit);
            this.panCategsMain.Controls.Add(this.btnCategsBack);
            this.panCategsMain.Controls.Add(this.btnCategsCreate);
            this.panCategsMain.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panCategsMain.Location = new System.Drawing.Point(0, 0);
            this.panCategsMain.Name = "panCategsMain";
            this.panCategsMain.Size = new System.Drawing.Size(408, 280);
            this.panCategsMain.TabIndex = 0;
            // 
            // lblCategsMSGPic
            // 
            this.lblCategsMSGPic.Anchor = System.Windows.Forms.AnchorStyles.Right;
            this.lblCategsMSGPic.ForeColor = System.Drawing.SystemColors.ControlDarkDark;
            this.lblCategsMSGPic.Location = new System.Drawing.Point(237, 6);
            this.lblCategsMSGPic.Name = "lblCategsMSGPic";
            this.lblCategsMSGPic.Size = new System.Drawing.Size(36, 49);
            this.lblCategsMSGPic.TabIndex = 24;
            this.lblCategsMSGPic.TextAlign = System.Drawing.ContentAlignment.TopCenter;
            // 
            // lblCategsMSG
            // 
            this.lblCategsMSG.Anchor = System.Windows.Forms.AnchorStyles.Right;
            this.lblCategsMSG.ForeColor = System.Drawing.SystemColors.ControlDarkDark;
            this.lblCategsMSG.Location = new System.Drawing.Point(279, 6);
            this.lblCategsMSG.Name = "lblCategsMSG";
            this.lblCategsMSG.Size = new System.Drawing.Size(121, 49);
            this.lblCategsMSG.TabIndex = 23;
            this.lblCategsMSG.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // lblCategsAvailCategs
            // 
            this.lblCategsAvailCategs.AutoSize = true;
            this.lblCategsAvailCategs.Location = new System.Drawing.Point(13, 41);
            this.lblCategsAvailCategs.Name = "lblCategsAvailCategs";
            this.lblCategsAvailCategs.Size = new System.Drawing.Size(105, 13);
            this.lblCategsAvailCategs.TabIndex = 20;
            this.lblCategsAvailCategs.Text = "Available categories:";
            // 
            // treViewCategsList
            // 
            this.treViewCategsList.Cursor = System.Windows.Forms.Cursors.Hand;
            this.treViewCategsList.FullRowSelect = true;
            this.treViewCategsList.HideSelection = false;
            this.treViewCategsList.Indent = 17;
            this.treViewCategsList.ItemHeight = 18;
            this.treViewCategsList.Location = new System.Drawing.Point(11, 58);
            this.treViewCategsList.Name = "treViewCategsList";
            treeNode1.Name = "Node1";
            treeNode1.Text = "Drogy";
            treeNode2.Name = "Chlast";
            treeNode2.Text = "Chlast";
            treeNode3.Name = "Node0";
            treeNode3.NodeFont = new System.Drawing.Font("Verdana", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            treeNode3.Text = "Zábava";
            treeNode4.Name = "Node3";
            treeNode4.Text = "Fastfood";
            treeNode5.Name = "Node4";
            treeNode5.Text = "Domácnost";
            treeNode6.Name = "Node0";
            treeNode6.NodeFont = new System.Drawing.Font("Verdana", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            treeNode6.Text = "Jídlo";
            this.treViewCategsList.Nodes.AddRange(new System.Windows.Forms.TreeNode[] {
            treeNode3,
            treeNode6});
            this.treViewCategsList.ShowNodeToolTips = true;
            this.treViewCategsList.Size = new System.Drawing.Size(262, 185);
            this.treViewCategsList.StateImageList = this.imgList;
            this.treViewCategsList.TabIndex = 16;
            this.treViewCategsList.AfterSelect += new System.Windows.Forms.TreeViewEventHandler(this.treViewCategsList_AfterSelect);
            // 
            // imgList
            // 
            this.imgList.ImageStream = ((System.Windows.Forms.ImageListStreamer)(resources.GetObject("imgList.ImageStream")));
            this.imgList.TransparentColor = System.Drawing.Color.Transparent;
            this.imgList.Images.SetKeyName(0, "package_go.png");
            this.imgList.Images.SetKeyName(1, "package_green.png");
            // 
            // panCategsCreate
            // 
            this.panCategsCreate.Controls.Add(this.lblProfilesChangeReq);
            this.panCategsCreate.Controls.Add(this.btnCategsCreateNew);
            this.panCategsCreate.Controls.Add(this.btnCategsCreateEdit);
            this.panCategsCreate.Controls.Add(this.btnCategsCreateCancel);
            this.panCategsCreate.Controls.Add(this.grpCategsCreate);
            this.panCategsCreate.Controls.Add(this.lblCategsCreateMSG);
            this.panCategsCreate.Controls.Add(this.lblCategsCreateMSGPic);
            this.panCategsCreate.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panCategsCreate.Location = new System.Drawing.Point(0, 0);
            this.panCategsCreate.Name = "panCategsCreate";
            this.panCategsCreate.Size = new System.Drawing.Size(408, 280);
            this.panCategsCreate.TabIndex = 21;
            this.panCategsCreate.Visible = false;
            this.panCategsCreate.VisibleChanged += new System.EventHandler(this.panCategsCreate_VisibleChanged);
            // 
            // lblProfilesChangeReq
            // 
            this.lblProfilesChangeReq.AutoSize = true;
            this.lblProfilesChangeReq.Font = new System.Drawing.Font("Microsoft Sans Serif", 6.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.lblProfilesChangeReq.ForeColor = System.Drawing.SystemColors.WindowFrame;
            this.lblProfilesChangeReq.Location = new System.Drawing.Point(354, 192);
            this.lblProfilesChangeReq.Name = "lblProfilesChangeReq";
            this.lblProfilesChangeReq.Size = new System.Drawing.Size(44, 12);
            this.lblProfilesChangeReq.TabIndex = 19;
            this.lblProfilesChangeReq.Text = "* required";
            // 
            // grpCategsCreate
            // 
            this.grpCategsCreate.Controls.Add(this.lblErrCategsCreateParent);
            this.grpCategsCreate.Controls.Add(this.cmbBoxCategsCreateParent);
            this.grpCategsCreate.Controls.Add(this.lblCategsCreateParent);
            this.grpCategsCreate.Controls.Add(this.lblErrCategsCreateDescr);
            this.grpCategsCreate.Controls.Add(this.lblErrCategsCreateName);
            this.grpCategsCreate.Controls.Add(this.txtBoxCategsCreateDescr);
            this.grpCategsCreate.Controls.Add(this.txtBoxCategsCreateName);
            this.grpCategsCreate.Controls.Add(this.lblCategsCreateDesc);
            this.grpCategsCreate.Controls.Add(this.lblCategsCreateName);
            this.grpCategsCreate.Location = new System.Drawing.Point(11, 62);
            this.grpCategsCreate.Name = "grpCategsCreate";
            this.grpCategsCreate.Size = new System.Drawing.Size(385, 127);
            this.grpCategsCreate.TabIndex = 21;
            this.grpCategsCreate.TabStop = false;
            this.grpCategsCreate.Text = " Category: ";
            // 
            // lblErrCategsCreateParent
            // 
            this.lblErrCategsCreateParent.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Italic, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.lblErrCategsCreateParent.ForeColor = System.Drawing.Color.Red;
            this.lblErrCategsCreateParent.Image = global::MoneyFlow.Properties.Resources.eventlogError;
            this.lblErrCategsCreateParent.Location = new System.Drawing.Point(230, 90);
            this.lblErrCategsCreateParent.Name = "lblErrCategsCreateParent";
            this.lblErrCategsCreateParent.Size = new System.Drawing.Size(16, 16);
            this.lblErrCategsCreateParent.TabIndex = 13;
            this.lblErrCategsCreateParent.Visible = false;
            // 
            // lblCategsCreateParent
            // 
            this.lblCategsCreateParent.Location = new System.Drawing.Point(6, 90);
            this.lblCategsCreateParent.Name = "lblCategsCreateParent";
            this.lblCategsCreateParent.Size = new System.Drawing.Size(90, 18);
            this.lblCategsCreateParent.TabIndex = 11;
            this.lblCategsCreateParent.Text = "Parent Category*:";
            this.lblCategsCreateParent.TextAlign = System.Drawing.ContentAlignment.TopRight;
            // 
            // lblErrCategsCreateDescr
            // 
            this.lblErrCategsCreateDescr.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Italic, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.lblErrCategsCreateDescr.ForeColor = System.Drawing.Color.Red;
            this.lblErrCategsCreateDescr.Image = global::MoneyFlow.Properties.Resources.eventlogError;
            this.lblErrCategsCreateDescr.Location = new System.Drawing.Point(230, 59);
            this.lblErrCategsCreateDescr.Name = "lblErrCategsCreateDescr";
            this.lblErrCategsCreateDescr.Size = new System.Drawing.Size(16, 16);
            this.lblErrCategsCreateDescr.TabIndex = 10;
            this.lblErrCategsCreateDescr.Visible = false;
            // 
            // lblErrCategsCreateName
            // 
            this.lblErrCategsCreateName.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Italic, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.lblErrCategsCreateName.ForeColor = System.Drawing.Color.Red;
            this.lblErrCategsCreateName.Image = global::MoneyFlow.Properties.Resources.eventlogError;
            this.lblErrCategsCreateName.Location = new System.Drawing.Point(208, 27);
            this.lblErrCategsCreateName.Name = "lblErrCategsCreateName";
            this.lblErrCategsCreateName.Size = new System.Drawing.Size(16, 16);
            this.lblErrCategsCreateName.TabIndex = 9;
            this.lblErrCategsCreateName.Visible = false;
            // 
            // lblCategsCreateDesc
            // 
            this.lblCategsCreateDesc.Location = new System.Drawing.Point(23, 59);
            this.lblCategsCreateDesc.Name = "lblCategsCreateDesc";
            this.lblCategsCreateDesc.Size = new System.Drawing.Size(73, 18);
            this.lblCategsCreateDesc.TabIndex = 2;
            this.lblCategsCreateDesc.Text = "Description:";
            this.lblCategsCreateDesc.TextAlign = System.Drawing.ContentAlignment.TopRight;
            // 
            // lblCategsCreateName
            // 
            this.lblCategsCreateName.Location = new System.Drawing.Point(20, 29);
            this.lblCategsCreateName.Name = "lblCategsCreateName";
            this.lblCategsCreateName.Size = new System.Drawing.Size(76, 15);
            this.lblCategsCreateName.TabIndex = 0;
            this.lblCategsCreateName.Text = "Name*:";
            this.lblCategsCreateName.TextAlign = System.Drawing.ContentAlignment.TopRight;
            // 
            // lblCategsCreateMSG
            // 
            this.lblCategsCreateMSG.Anchor = System.Windows.Forms.AnchorStyles.Right;
            this.lblCategsCreateMSG.ForeColor = System.Drawing.SystemColors.ControlDarkDark;
            this.lblCategsCreateMSG.Location = new System.Drawing.Point(69, 8);
            this.lblCategsCreateMSG.Name = "lblCategsCreateMSG";
            this.lblCategsCreateMSG.Size = new System.Drawing.Size(327, 49);
            this.lblCategsCreateMSG.TabIndex = 18;
            this.lblCategsCreateMSG.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // lblCategsCreateMSGPic
            // 
            this.lblCategsCreateMSGPic.Anchor = System.Windows.Forms.AnchorStyles.Right;
            this.lblCategsCreateMSGPic.ForeColor = System.Drawing.SystemColors.ControlDarkDark;
            this.lblCategsCreateMSGPic.Location = new System.Drawing.Point(27, 8);
            this.lblCategsCreateMSGPic.Name = "lblCategsCreateMSGPic";
            this.lblCategsCreateMSGPic.Size = new System.Drawing.Size(36, 49);
            this.lblCategsCreateMSGPic.TabIndex = 20;
            this.lblCategsCreateMSGPic.TextAlign = System.Drawing.ContentAlignment.TopCenter;
            // 
            // CategoryForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(408, 280);
            this.ControlBox = false;
            this.Controls.Add(this.panCategsCreate);
            this.Controls.Add(this.panCategsMain);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "CategoryForm";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterParent;
            this.Text = "MyFlow Categories";
            this.Load += new System.EventHandler(this.CategoryForm_Load);
            this.panCategsMain.ResumeLayout(false);
            this.panCategsMain.PerformLayout();
            this.panCategsCreate.ResumeLayout(false);
            this.panCategsCreate.PerformLayout();
            this.grpCategsCreate.ResumeLayout(false);
            this.grpCategsCreate.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.ToolTip toolTip;
        private System.Windows.Forms.Panel panCategsMain;
        private MoneyFlow.Buttons.ButtonDelete btnCategsDelete;
        private MoneyFlow.Buttons.ButtonEdit btnCategsEdit;
        private MoneyFlow.Buttons.ButtonReturn btnCategsBack;
        private System.Windows.Forms.Button btnCategsCreate;
        private System.Windows.Forms.TreeView treViewCategsList;
        private System.Windows.Forms.ImageList imgList;
        private System.Windows.Forms.Button btnCategsSelectedDown;
        private System.Windows.Forms.Button btnCategsSelectedUp;
        private System.Windows.Forms.Button btnCategsAdd;
        private System.Windows.Forms.Label lblCategsAvailCategs;
        private System.Windows.Forms.Panel panCategsCreate;
        private System.Windows.Forms.Label lblProfilesChangeReq;
        private MoneyFlow.Buttons.ButtonReturn btnCategsCreateNew;
        private MoneyFlow.Buttons.ButtonReturn btnCategsCreateCancel;
        private System.Windows.Forms.GroupBox grpCategsCreate;
        private System.Windows.Forms.Label lblErrCategsCreateDescr;
        private System.Windows.Forms.Label lblErrCategsCreateName;
        private System.Windows.Forms.TextBox txtBoxCategsCreateDescr;
        private System.Windows.Forms.TextBox txtBoxCategsCreateName;
        private System.Windows.Forms.Label lblCategsCreateDesc;
        private System.Windows.Forms.Label lblCategsCreateName;
        private System.Windows.Forms.Label lblCategsCreateMSG;
        private System.Windows.Forms.Label lblCategsCreateMSGPic;
        private System.Windows.Forms.Label lblCategsCreateParent;
        private System.Windows.Forms.ComboBox cmbBoxCategsCreateParent;
        private System.Windows.Forms.Label lblErrCategsCreateParent;
        private System.Windows.Forms.Label lblCategsMSGPic;
        private System.Windows.Forms.Label lblCategsMSG;
        private MoneyFlow.Buttons.ButtonReturn btnCategsCreateEdit;
    }
}