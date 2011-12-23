using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Windows.Forms;
using BLL;
using DAL;

namespace MoneyFlow
{
    /// <summary> Category management form providing CRUD interface for user. </summary>
    public partial class CategoryForm : Form
    {
        
 // == INSTANCE VARIABLES =====================================================================

        #region instance variables
        /// <summary> Currently logged user. </summary>
        private Account loggedAcc;

        /// <summary> Active profile. </summary>
        private Profile currentProf;
        public Profile ActiveProfileResult
        {
            get { return this.currentProf; }
        }

        /// <summary> All, currently loaded categories. </summary>
        private List<Category> loadedCategs;

        /// <summary> Category or subcategory selected in tree view. </summary>
        private Category selectedCateg
        {
            get
            {
                if (treViewCategsList.SelectedNode == null)
                    return null;

                switch (treViewCategsList.SelectedNode.Level){
                    case 0:
                        {
                            return this.loadedCategs[treViewCategsList.SelectedNode.Index];
                        }
                    case 1:
                        {
                            int itemIndx = treViewCategsList.SelectedNode.Index;
                            int parentsIndx = treViewCategsList.SelectedNode.Parent.Index;

                            return this.loadedCategs[parentsIndx][itemIndx];
                        }
                    default: return null;
                }
            }
        }

        /// <summary> If categories were modified somehow indicator.  </summary>
        private bool isChanged = false;
        public bool IsChanged
        {
            get { return this.isChanged; }
        }
        #endregion instance variables

 // == CONSTRUCTORS ===========================================================================

        public CategoryForm(Account loggedAcc, Profile currentProf)
        {
            InitializeComponent();
            this.loggedAcc = loggedAcc;
            this.currentProf = currentProf;
        }

 // == INSTANCE PRIVATE METHODS ===============================================================

        #region loading
        /// <summary> Load all available categories with subcategories from current profile. </summary>
        private void loadCategories()
        {
            CategoryDAO categoryDAO = new CategoryDAO();

            // Local Main categories list & All subcategories
            this.loadedCategs = categoryDAO.getAllCategories(currentProf, true);

            updateCategoriesTree();
        }
        #endregion loading

        #region update
        /// <summary> Loads main categories into Combo box of parent categories. </summary>
        private void loadComboBoxParentCategs()
        {
            // Remove old list
            int itmsCnt = cmbBoxCategsCreateParent.Items.Count;
            for (int i = 0; i < itmsCnt; i++)
            {
                cmbBoxCategsCreateParent.Items.RemoveAt(0);
            }

            // Add new items into combo box
            cmbBoxCategsCreateParent.Items.Add(" - Choose category -");
            cmbBoxCategsCreateParent.SelectedItem = " - Choose category -";

            for (int i = 0; i < this.loadedCategs.Count(); i++)
            {
                cmbBoxCategsCreateParent.Items.Add(this.loadedCategs[i]);
            }

            cmbBoxCategsCreateParent.Enabled = true;
        }

        /// <summary> Builds tree in treeview from all loaded categories and subcategories. </summary>
        private void updateCategoriesTree()
        {
            // Clear & Remove current tree Nodes
            int nodesCnt = treViewCategsList.Nodes.Count;
            for (int i = 0; i < nodesCnt; i++)
            {
                treViewCategsList.SelectedNode = null;
                treViewCategsList.Nodes.RemoveAt(0);
            }

            TreeNode node;
            foreach (Category categs in this.loadedCategs)
            {
                // Add main category to first level node
                node = treViewCategsList.Nodes.Add(categs.ToString());

                // Adds category's subcategories to second level node
                for (int i = 0; i < categs.subCategoriesCount(); i++)
                {
                    node.Nodes.Add(categs[i].ToString());
                }
            }
        }
        #endregion update

        #region GUI
        /// <summary> Enables/Disables Delete, Edit, Move subcategory up, 
        /// Move subcategory down and Add subcategory buttons. </summary>
        /// <param name="isEnabled">True - enable buttons
        ///                         False - disable buttons.</param>
        private void enableControlButtons(bool isEnabled)
        {
            btnCategsDelete.Enabled = isEnabled;
            btnCategsEdit.Enabled = isEnabled;
            btnCategsSelectedDown.Enabled = isEnabled;
            btnCategsSelectedUp.Enabled = isEnabled;
            btnCategsAdd.Enabled = isEnabled;
        }

        /// <summary> Disables validation error pictures showing in Create/Edit category. </summary>
        private void disableValidationErrLbls()
        {
            lblErrCategsCreateDescr.Visible = false;
            lblErrCategsCreateName.Visible = false;
            lblErrCategsCreateParent.Visible = false;
        }

        /// <summary> Clears all textboxes in Category Main and Category Create/Edit forms. </summary>
        private void clearAllTxtBoxes()
        {
            disableValidationErrLbls();
            txtBoxCategsCreateDescr.Clear();
            txtBoxCategsCreateName.Clear();
            loadComboBoxParentCategs();
        }

        /// <summary>Shows Create button and hides Edit button (or vica versa) in Create 
        /// Change category. </summary>
        /// <param name="isVisible">True - shows Create button and hides Edit button. 
        ///                         False - hides Create button and shows Edit button. </param>
        private void showCreateButton(bool isVisible)
        {
            btnCategsCreateNew.Visible = isVisible;
            btnCategsCreateEdit.Visible = !isVisible;
        }
        #endregion GUI

        #region messages
        /// <summary> Shows Error system message. </summary>
        /// <param name="msg">Message to show. </param>
        /// <param name="lbl"> Label to which a message will be shown. </param>
        /// <param name="picLbl"> Picture label for error icon. </param>
        private void showErrMSG(string msg, Label lbl, Label picLbl)
        {
            lbl.Text = msg;
            lbl.ForeColor = Color.Red;
            picLbl.Image = MoneyFlow.Properties.Resources.error;
        }

        /// <summary> Shows information system message. </summary>
        /// <param name="msg">Message to show. </param>
        /// <param name="lbl"> Label to which a message will be shown. </param>
        /// <param name="picLbl"> Picture label for info icon. </param>
        private void showInfoMSG(string msg, Label lbl, Label picLbl)
        {
            lbl.Text = msg;
            lbl.ForeColor = Color.FromArgb(105, 105, 105);
            picLbl.Image = MoneyFlow.Properties.Resources.info;
        }

        /// <summary> Shows OK system message. </summary>
        /// <param name="msg">Message to show. </param>
        /// <param name="lbl"> Label to which a message will be shown. </param>
        /// <param name="picLbl"> Picture label for OK icon. </param>
        private void showOkMSG(string msg, Label lbl, Label picLbl)
        {
            lbl.Text = msg;
            lbl.ForeColor = Color.FromArgb(48, 176, 26);
            picLbl.Image = MoneyFlow.Properties.Resources.ok;
        }

        /// <summary> Clears system message. </summary>
        /// <param name="lbl"> Label which will be erased. </param>
        /// <param name="picLbl"> Picture label for clear the icon. </param>
        private void showEmptyMSG(Label lbl, Label picLbl)
        {
            lbl.Text = "";
            picLbl.Image = null;
        }
        #endregion messages

        #region services
        /// <summary> Moves selected subcategory under new parental category in loaded hierarchy.</summary>
        /// <param name="isDirectionDown">true - Move under downer parent.
        ///                               false - Move under upper parent. </param>
        private void moveSubcategory(bool isDirectionDown)
        {
            // If subcategory is not selected = error
            if (treViewCategsList.SelectedNode.Level != 1)
                return;

            CategoryDAO categoryDAO = new CategoryDAO();

            int itemIndx = treViewCategsList.SelectedNode.Index;
            int parentsIndx = treViewCategsList.SelectedNode.Parent.Index;
            // Subcategory on itemIndx in category on parentsIndx
            Category subcategToUpdate = this.loadedCategs[parentsIndx][itemIndx];

            // Remove subcategory in old category
            this.loadedCategs[parentsIndx].removeSubCategory(itemIndx);

            // New subcategory
            int newIndx;
            if (isDirectionDown)
            {
                newIndx = (++parentsIndx) % this.loadedCategs.Count;
            }
            else
            {
                newIndx = ((--parentsIndx) + this.loadedCategs.Count) % this.loadedCategs.Count;
            }

            Category newParentCateg = this.loadedCategs[newIndx];
            categoryDAO.updateSubcatsParent(subcategToUpdate, newParentCateg);

            // Submit changes & clean
            LinqUtil.submitChanges();
            clean();

            // Update tree view
            newParentCateg.addSubCategory(subcategToUpdate);
            updateCategoriesTree();

            isChanged = true;

            // Re-selects on new location
            treViewCategsList.Nodes[newIndx].Expand();
            treViewCategsList.SelectedNode =
                treViewCategsList.Nodes[newIndx].Nodes[(this.loadedCategs[newIndx].subCategoriesCount() - 1)];
        }

        /// <summary> Cleans and disposes DB data context. </summary>
        private void clean()
        {
            LinqUtil.cleanUp();
        }
        #endregion services
        
 // == LISTENERS METHODS ======================================================================

        #region loading
        /// <summary> Loads all available Categories and their subcategories and set up 
        /// environment. </summary>
        private void CategoryForm_Load(object sender, EventArgs e)
        {
            loadCategories();
            clean();
        }
        #endregion loading

        #region buttons
        /// <summary> Closes categories dialog and return to main overview.</summary>
        private void btnCategsBack_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        /// <summary> Shows create new main category form. </summary>
        private void btnCategsCreate_Click(object sender, EventArgs e)
        {
            panCategsCreate.Visible = true;
            cmbBoxCategsCreateParent.Enabled = false;

            // Controls texts
            btnCategsCreateNew.Text = "Create category";
            grpCategsCreate.Text = " Category: ";
            showInfoMSG("To create new category, please, fill form below.",
                lblCategsCreateMSG, lblCategsCreateMSGPic);
        }

        /// <summary> Shows add new subcategory category form. </summary>
        private void btnCategsAdd_Click(object sender, EventArgs e)
        {
            // If selected is subcategory = error
            if (treViewCategsList.SelectedNode.Level == 1)
                return;

            panCategsCreate.Visible = true;
            showInfoMSG("To", lblCategsCreateMSG, lblCategsCreateMSGPic);

            cmbBoxCategsCreateParent.Enabled = true;

            // Controls texts
            btnCategsCreateNew.Text = "Add subcategory";
            grpCategsCreate.Text = " Category: ";
            showInfoMSG("To create new subcategory, please, fill form below and select " +
                        "parental category.", lblCategsCreateMSG, lblCategsCreateMSGPic);

            // Select appropriate parental category in combobox
            cmbBoxCategsCreateParent.SelectedIndex = treViewCategsList.SelectedNode.Index + 1;
        }

        /// <summary> Cancels new category creation/editation and returns back to categories
        /// overview. </summary>
        private void btnBoxCategsCreateCancel_Click(object sender, EventArgs e)
        {
            panCategsCreate.Visible = false;
        }

        /// <summary> Tries to validate new Category creation form and create 
        /// new category or subcategory. </summary>
        private void btnBoxCategsCreate_Click(object sender, EventArgs e)
        {
            Category newCateg = new Category();
            CategoryDAO categoryDAO = new CategoryDAO();

            bool isValid = true;
            disableValidationErrLbls();

            try
            {
                string errMSG = "Error(s) occured:";

                // Creating new category
                newCateg.Category_id = categoryDAO.getNewID();
                newCateg.Profile_id = this.currentProf.Profile_id;

                try
                {
                    newCateg.Name = txtBoxCategsCreateName.Text;
                }
                catch (ApplicationException ex)
                {
                    isValid = false;
                    txtBoxCategsCreateName.Text = null;
                    lblErrCategsCreateName.Visible = true;
                    errMSG += "\n" + ex.Message;
                }

                try
                {
                    newCateg.Description = txtBoxCategsCreateDescr.Text;
                }
                catch (ApplicationException ex)
                {
                    isValid = false;
                    txtBoxCategsCreateDescr.Text = null;
                    lblErrCategsCreateDescr.Visible = true;
                    errMSG += "\n" + ex.Message;
                }

                int itemIndx = -1;
                if (cmbBoxCategsCreateParent.Enabled)
                {
                    try
                    {
                        itemIndx = cmbBoxCategsCreateParent.SelectedIndex;

                        if (itemIndx < 0)
                            throw new ApplicationException("Please, select parent for this subcategory!");

                        int supCategID = this.loadedCategs[--itemIndx].Category_id;

                        newCateg.Sup_category_id = supCategID;
                    }
                    catch (Exception ex)
                    {
                        isValid = false;
                        lblErrCategsCreateParent.Visible = true;
                        errMSG += "\n" + ex.Message;
                    }
                }

                // Validity check
                if (!isValid)
                    throw new ApplicationException(errMSG);

                // Submits changes and inserts account into DB
                categoryDAO.insertCategory(newCateg);
                LinqUtil.submitChanges();

                // Inserts category or subcategory to local categories list
                if (cmbBoxCategsCreateParent.Enabled)
                {
                    if (itemIndx < 0)
                        throw new ApplicationException("Invalid parent category selected.");

                    this.loadedCategs[itemIndx].addSubCategory(newCateg);
                    showOkMSG("New subcategory " + newCateg.Name + " was created!", lblCategsMSG,
                        lblCategsMSGPic);
                }
                else
                {
                    if (itemIndx > -1)
                        throw new ApplicationException("Invalid parent category selected.");

                    this.loadedCategs.Add(newCateg);
                    showOkMSG("New category " + newCateg.Name + " was created!", lblCategsMSG,
                        lblCategsMSGPic);
                }

                // Update Tree view
                updateCategoriesTree();

                // Informs user about success and updates List box
                panCategsCreate.Visible = false;
                enableControlButtons(false);

                this.isChanged = true;
            }
            catch (ApplicationException ex)
            {
                showErrMSG(ex.Message, lblCategsCreateMSG, lblCategsCreateMSGPic);
            }
            finally
            {
                clean();
            }
        }

        /// <summary> Moves subcategory under upper category. </summary>
        private void btnCategsSelectedUp_Click(object sender, EventArgs e)
        {
            moveSubcategory(false);
        }

        /// <summary> Moves subcategory under downer category. </summary>
        private void btnCategsSelectedDown_Click(object sender, EventArgs e)
        {
            moveSubcategory(true);
        }

        /// <summary> Asks user whether or not he is sure about removing selected category and 
        /// all its subcategories and contents. </summary>
        private void btnCategsDelete_Click(object sender, EventArgs e)
        {
            int itemLevel = treViewCategsList.SelectedNode.Level;
            int itemIndx = treViewCategsList.SelectedNode.Index;

            // If any error in selection occured
            if (itemIndx < 0 || itemLevel < 0) return;

            Category catToDelete = null;

            switch (itemLevel)
            {
                case 0:
                    {
                        catToDelete = this.loadedCategs[itemIndx];
                    } break;
                case 1:
                    {
                        int parentsIndx = treViewCategsList.SelectedNode.Parent.Index;

                        // Subcategory on itemIndx in category on parentsIndx.
                        catToDelete = this.loadedCategs[parentsIndx][itemIndx];
                    } break;
                default: break;
            }

            if (MessageBox.Show("Are you sure you want to delete category " + catToDelete.Name +
                " and all its subcategories and contents?", "Deleting category " + catToDelete.Name,
                MessageBoxButtons.YesNo) == DialogResult.Yes)
            {
                // Deletes from DB
                CategoryDAO categoryDAO = new CategoryDAO();
                categoryDAO.deleteCategory(catToDelete);

                // Delets from tree view
                switch (itemLevel)
                {
                    case 0:
                        {
                            this.loadedCategs.RemoveAt(itemIndx);
                        } break;
                    case 1:
                        {
                            int parentsIndx = treViewCategsList.SelectedNode.Parent.Index;

                            // Subcategory on itemIndx in category on parentsIndx.
                            this.loadedCategs[parentsIndx].removeSubCategory(itemIndx);
                        } break;
                    default: break;
                }
                // Submits changes into DB
                LinqUtil.submitChanges();

                // Updates tree view
                updateCategoriesTree();
                treViewCategsList.SelectedNode = null;
                enableControlButtons(false);

                // Show info about successful delete
                showOkMSG("Category " + catToDelete.Name + " was deleted!", lblCategsMSG, lblCategsMSGPic);

                isChanged = true;
            }
        }

        /// <summary> Loads Category/Subcategory editation form with and fill textboxes with 
        /// category's details. </summary>
        private void btnCategsEdit_Click(object sender, EventArgs e)
        {
            // if nothing is selected in tree view = error
            if (treViewCategsList.SelectedNode == null)
                return;

            // 
            panCategsCreate.Visible = true;
            showCreateButton(false);

            // Filling forms GUI with details of selected category
            txtBoxCategsCreateDescr.Text = this.selectedCateg.Description;
            txtBoxCategsCreateName.Text = this.selectedCateg.Name;

            switch (treViewCategsList.SelectedNode.Level)
            {
                case 0:
                    {
                        showInfoMSG("To edit this category, please, modify details " +
                            "in form below. In case your category has no subcategories, " +
                            "you can also demote it to subcategory by choosing its parent.",
                            lblCategsCreateMSG, lblCategsCreateMSGPic);

                        // If category has subcategories, disable parent choose.
                        if (selectedCateg.subCategoriesCount() > 0)
                            cmbBoxCategsCreateParent.Enabled = false;

                        grpCategsCreate.Text = "Edit Category: ";
                        cmbBoxCategsCreateParent.Items[0] = " > currently parent <";

                        cmbBoxCategsCreateParent.Items.RemoveAt(treViewCategsList.SelectedNode.Index + 1);

                    } break;
                case 1:
                    {
                        showInfoMSG("To edit this subcategory, please, modify details " +
                                    "in form below. You can also promote this subcategory " +
                                    "to category by choosing it in choose bar.",
                                    lblCategsCreateMSG, lblCategsCreateMSGPic);

                        grpCategsCreate.Text = "Edit subcategory: ";

                        cmbBoxCategsCreateParent.Items[0] = " > promote to parent <";

                        cmbBoxCategsCreateParent.SelectedIndex =
                            treViewCategsList.SelectedNode.Parent.Index + 1;
                    } break;
                default: ; break;
            }
        }

        /// <summary> Tries save changes of categories or subcategories made by user. </summary>
        private void btnCategsCreateEdit_Click(object sender, EventArgs e)
        {
            Category categToUpdate = this.selectedCateg;
            CategoryDAO categoryDAO = new CategoryDAO();

            bool isValid = true;
            disableValidationErrLbls();

            try
            {
                string errMSG = "Details cannot be changed, Error(s) occured:";

                // Updating category
                try
                {
                    categToUpdate.Name = txtBoxCategsCreateName.Text;
                }
                catch (ApplicationException ex)
                {
                    isValid = false;
                    txtBoxCategsCreateName.Text = categToUpdate.Name;
                    lblErrCategsCreateName.Visible = true;
                    errMSG += "\n" + ex.Message;
                }

                try
                {
                    categToUpdate.Description = txtBoxCategsCreateDescr.Text;
                }
                catch (ApplicationException ex)
                {
                    isValid = false;
                    txtBoxCategsCreateDescr.Text = categToUpdate.Description;
                    lblErrCategsCreateDescr.Visible = true;
                    errMSG += "\n" + ex.Message;
                }

                try
                {
                    int selectedIndx = cmbBoxCategsCreateParent.SelectedIndex;
                    // If any parrent category is selected
                    if (selectedIndx > 0)
                    {
                        categToUpdate.Sup_category_id = this.loadedCategs[selectedIndx - 1].Category_id;
                    }
                    // If "I am parrent now" is selected
                    else
                    {
                        categToUpdate.Sup_category_id = null;
                    }
                }
                catch (ApplicationException ex)
                {
                    isValid = false;
                    lblErrCategsCreateParent.Visible = true;
                    errMSG += "\n" + ex.Message;
                }

                // Validity check
                if (!isValid)
                    throw new ApplicationException(errMSG);

                // Submits changes and inserts account into DB
                categoryDAO.updateCategory(categToUpdate);
                LinqUtil.submitChanges();

                switch (treViewCategsList.SelectedNode.Level)
                {
                    case 0:
                        {
                            if (categToUpdate.Sup_category_id != null)
                            {
                                this.loadedCategs.RemoveAll(o => o.Category_id == categToUpdate.Category_id);
                                this.loadedCategs[cmbBoxCategsCreateParent.SelectedIndex - 1]
                                    .addSubCategory(categToUpdate);
                            }
                        } break;
                    case 1:
                        {
                            if (categToUpdate.Sup_category_id == null)
                            {
                                // Remove subcategory
                                int parentIndx = treViewCategsList.SelectedNode.Parent.Index;
                                int subCategIndx = treViewCategsList.SelectedNode.Index;
                                this.loadedCategs[parentIndx].removeSubCategory(subCategIndx);

                                // Add as a category
                                this.loadedCategs.Add(categToUpdate);
                            }
                            else
                            {
                                int parentIndx = treViewCategsList.SelectedNode.Parent.Index;
                                int subCategIndx = treViewCategsList.SelectedNode.Index;

                                if ((cmbBoxCategsCreateParent.SelectedIndex - 1) != parentIndx)
                                {
                                    // Remove subcategory
                                    this.loadedCategs[parentIndx].removeSubCategory(subCategIndx);

                                    // Ad as a different subcategory
                                    this.loadedCategs[cmbBoxCategsCreateParent.SelectedIndex - 1]
                                        .addSubCategory(categToUpdate);
                                }
                            }
                        } break;
                    default: ; break;
                }

                // OK message
                showOkMSG("Category " + categToUpdate.Name + " has been successfully updated!",
                    lblCategsMSG, lblCategsMSGPic);

                // Update Tree view
                updateCategoriesTree();

                // Informs user about success and updates List box
                panCategsCreate.Visible = false;
                enableControlButtons(false);

                this.isChanged = true;
            }
            catch (ApplicationException ex)
            {
                showErrMSG(ex.Message, lblCategsCreateMSG, lblCategsCreateMSGPic);
            }
            finally
            {
                clean();
            }
        }
        #endregion buttons

        #region treeview
        /// <summary> Changes appropriately GUI properties after selecting any 
        /// Node in tree. </summary>
        private void treViewCategsList_AfterSelect(object sender, TreeViewEventArgs e)
        {
            if (treViewCategsList.SelectedNode != null)
            {
                showEmptyMSG(lblCategsMSG, lblCategsMSGPic);
                enableControlButtons(true);

                if (treViewCategsList.SelectedNode.Level == 0)
                {
                    btnCategsSelectedDown.Enabled = false;
                    btnCategsSelectedUp.Enabled = false;
                }
                else if (treViewCategsList.SelectedNode.Level == 1)
                {
                    btnCategsAdd.Enabled = false;
                }
            }
            else
            {
                enableControlButtons(false);
            }
        }
        #endregion treeview

        #region panels
        /// <summary> Triggers when Create Panel shows on/off. </summary>
        private void panCategsCreate_VisibleChanged(object sender, EventArgs e)
        {
            if (panCategsCreate.Visible)
            {
                clearAllTxtBoxes();
                showEmptyMSG(lblCategsMSG, lblCategsMSGPic);
            }
            else
            {
                showCreateButton(true);
            }
        }
        #endregion panels

    }
}
