using System;
using System.Collections.Generic;
using System.Drawing;
using System.Windows.Forms;
using BLL;
using DAL;

namespace MoneyFlow
{
    /// <summary> Dialog providing cashflow item creation and editation. </summary>
    public partial class CashflowItemDialog : Form
    {

 // == INSTANCE VARIABLES =====================================================================

        #region variables
        /// <summary> Active profile. </summary>
        private Profile currentProf;

        /// <summary> If cashflow item was created or edited indicator. </summary>
        private bool isSubmitted = false;
        public bool IsSubmitted
        {
            get { return this.isSubmitted; }
        }

        /// <summary> Concrete loaded cash (Editation purposes). </summary>
        private Cashed cash;

        /// <summary> Currently loaded available categories. </summary>
        private List<Category> loadedCategs;
        #endregion variables

 // == CONSTRUCTORS ===========================================================================

        public CashflowItemDialog(Profile currentProf, Cashed categ)
        {
            InitializeComponent();

            this.currentProf = currentProf;
            this.cash = categ;
        }

 // == INSTANCE PRIVATE METHODS ===============================================================

        #region loading
        // Load categories names into ComboBox and sets selector
        private void loadCategories()
        {
            // Insert categories into ComboBox
            cmbBoxCashflowChooseCateg.Items.Add(" - Choose category -");
            cmbBoxCashflowChooseCateg.SelectedIndex = 0;

            const string INDENT = "     ";

            // Add categories
            for (int i = 0; i < this.loadedCategs.Count; i++)
            {
                cmbBoxCashflowChooseCateg.Items.Add(this.loadedCategs[i].Name);

                // Add subcategories
                for (int j = 0; j < this.loadedCategs[i].subCategoriesCount(); j++)
                {
                    cmbBoxCashflowChooseCateg.Items.Add(INDENT + this.loadedCategs[i][j].Name);
                }
            }
        }

        /// <summary> Initialize editation and fills form boxes with 
        /// loaded cashflow details. </summary>
        private void initEdit()
        {
            showInfoMSG("To change this cashflow item's details, please, edit form below.",
                lblCashflowChooseMSG, lblCashflowChooseMSGPic);

            // Show Save changes
            btnCashflowChooseSave.Visible = true;
            btnCashflowChooseAdd.Visible = false;

            // Load edited Cash data into form
            loadDataIntoForm();
        }

        /// <summary> Initialize form for creating new cashflow item. </summary>
        private void initCreate()
        {
            showInfoMSG("To create new cashflow item, please, fill form below.",
                lblCashflowChooseMSG, lblCashflowChooseMSGPic);

            loadCategories();
        }
        #endregion loading

        #region updates
        /// <summary> Loads categories names into Combobox selector. </summary>
        private void setCategoryBoxSelector()
        {
            int cnt = 0;

            for (int i = 0; i < this.loadedCategs.Count; i++)
            {
                cnt++;
                if (this.loadedCategs[i].Category_id == cash.Category_id)
                    break;

                for (int j = 0; j < this.loadedCategs[i].subCategoriesCount(); j++)
                {
                    cnt++;
                    if (this.loadedCategs[i][j].Category_id == cash.Category_id)
                        goto CHECK;
                }
            }

        CHECK:
            {
                cmbBoxCashflowChooseCateg.SelectedIndex = cnt;
            }
        }

        /// <summary> Checks selector according to type of cashflow. </summary>
        private void setCashtypeRadioSelector()
        {
            switch (cash.Cashtype)
            {
                case "income":
                    {
                        radBtnCashflowChooseInc.Checked = true;
                    } break;

                case "outcome":
                    {
                        radBtnCashflowChooseOut.Checked = true;
                    } break;
                default: ; break;
            }
        }

        /// <summary> Loads data of edited cashflow into form. </summary>
        private void loadDataIntoForm()
        {
            // Load categories into combobox & set selected
            loadCategories();

            setCategoryBoxSelector();

            // Text boxes fill
            txtBoxCashflowChooseDescr.Text = cash.Item.Description;
            txtBoxCashflowChooseVal.Text = "" + cash.Item.Item_value;

            datPickCashflowChooseDate.Text = cash.Paid.ToString("dd.MM.yyyy");

            // CashType fill
            setCashtypeRadioSelector();
        }
        #endregion updates

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
        /// <summary> Cleans and disposes DB data context. </summary>
        private void clean()
        {
            LinqUtil.cleanUp();
        }
        #endregion services

        #region GUI
        /// <summary> Disables validation error pictures showing in Create/Edit cashflow item. </summary>
        private void disableValidationErrLbls()
        {
            lblErrCashflowChooseCash.Visible = false;
            lblErrCashflowChooseCateg.Visible = false;
            lblErrCashflowChooseDate.Visible = false;
            lblErrCashflowChooseDescr.Visible = false;
            lblErrCashflowChooseValue.Visible = false;
        }
        #endregion GUI

 // == LISTENERS METHODS ======================================================================

        #region buttons
        /// <summary> Closes Add/Edit cashflow item dialog and returns back to 
        /// Main Frame. </summary>
        private void btnCashflowChooseCancel_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        /// <summary> Validates information from user and if ok creates new 
        /// cashflow item. </summary>
        private void btnCashflowChooseAdd_Click(object sender, EventArgs e)
        {
            Item newItem = new Item();
            Cashed newCash = new Cashed();

            ItemDAO itemDAO = new ItemDAO();
            CashedDAO cashDAO = new CashedDAO();

            bool isValid = true;
            disableValidationErrLbls();

            try
            {
                string errMSG = "Some error(s) occured:";

                // Creating new cashflow item
                newItem.Item_id = itemDAO.getNewID();
                newCash.Item_id = newItem.Item_id;

                // From user 
                try
                {
                    newCash.Paid = DateTime.Parse(datPickCashflowChooseDate.Text);
                }
                catch (ApplicationException ex)
                {
                    isValid = false;
                    lblErrCashflowChooseDate.Visible = true;
                    datPickCashflowChooseDate.Text = null;
                    errMSG += "\n" + ex.Message;
                }

                try
                {
                    newItem.Description = txtBoxCashflowChooseDescr.Text;
                }
                catch (ApplicationException ex)
                {
                    isValid = false;
                    lblErrCashflowChooseDescr.Visible = true;
                    txtBoxCashflowChooseDescr.Text = null;
                    errMSG += "\n" + ex.Message;
                }

                try
                {
                    try
                    {
                        newItem.Item_value = Int32.Parse(txtBoxCashflowChooseVal.Text);
                    }
                    catch (FormatException ex)
                    {
                        ex.ToString();
                        throw new ApplicationException("You have entered invalid number format into " +
                            "item's value. Please, insert digits from 0 to 9.");
                    }
                }
                catch (ApplicationException ex)
                {
                    isValid = false;
                    lblErrCashflowChooseValue.Visible = true;
                    txtBoxCashflowChooseVal.Text = null;
                    errMSG += "\n" + ex.Message;
                }

                try
                {
                    if (radBtnCashflowChooseInc.Checked)
                    {
                        newCash.Cashtype = "income";
                    }
                    else if (radBtnCashflowChooseOut.Checked)
                    {
                        newCash.Cashtype = "outcome";
                    }
                    else
                    {
                        newCash.Cashtype = "";
                    }
                }
                catch (ApplicationException ex)
                {
                    isValid = false;
                    lblErrCashflowChooseCash.Visible = true;
                    errMSG += "\n" + ex.Message;
                }

                try
                {
                    // If first item selected, no category was choosen = error
                    if (cmbBoxCashflowChooseCateg.SelectedIndex == 0)
                        throw new ApplicationException("Please, you have to choose any category. ");

                    int categIndx = cmbBoxCashflowChooseCateg.SelectedIndex - 1;

                    int sum = -1;
                    for (int i = 0; i < this.loadedCategs.Count; i++)
                    {
                        if ((sum + 1 + this.loadedCategs[i].subCategoriesCount()) >= categIndx)
                        {
                            // If category is selected
                            if (categIndx == (sum + 1))
                            {
                                newCash.Category_id = this.loadedCategs[i].Category_id;
                            }
                            // If subcategory of category is selected
                            else
                            {
                                newCash.Category_id = this.loadedCategs[i][(categIndx - sum - 2)].Category_id;
                            }
                            break;
                        }

                        sum += this.loadedCategs[i].subCategoriesCount() + 1;
                    }
                }
                catch (ApplicationException ex)
                {
                    isValid = false;
                    cmbBoxCashflowChooseCateg.SelectedIndex = 0;
                    lblErrCashflowChooseCateg.Visible = true;
                    errMSG += "\n" + ex.Message;
                }

                // Joining item to cash
                newCash.Item = newItem;

                // Validity check
                if (!isValid)
                    throw new ApplicationException(errMSG);

                cashDAO.insertCash(newCash);

                // Submits changes and inserts cash into DB
                LinqUtil.submitChanges();

                // Informs user about success
                showOkMSG("New " + newCash.Cashtype + " " + newItem.Item_value + ",- " +
                          "has been successfully inserted with payment date at " +
                          newCash.Paid.ToString("dd.MM.yyyy") + "!", lblCashflowChooseMSG,
                          lblCashflowChooseMSGPic);

                // Restart textfields
                txtBoxCashflowChooseVal.Text = null;
                txtBoxCashflowChooseDescr.Text = null;

                this.isSubmitted = true;
            }
            catch (ApplicationException ex)
            {
                showErrMSG(ex.Message, lblCashflowChooseMSG, lblCashflowChooseMSGPic);
            }
            finally
            {
                clean();
            }
        }

        /// <summary> Closes Add/Edit cashflow item dialog and returns back to 
        /// Main Frame. </summary>
        private void btnCashflowChooseCancel_Click_1(object sender, EventArgs e)
        {
            this.Close();
        }

        /// <summary> Validates details changed by user and if ok Saves changes
        /// closes dialog and returns into Main Frame.</summary>
        private void btnCashflowChooseSave_Click(object sender, EventArgs e)
        {
            Item newItem = new Item();
            Cashed newCash = new Cashed();

            ItemDAO itemDAO = new ItemDAO();
            CashedDAO cashDAO = new CashedDAO();

            bool isValid = true;
            disableValidationErrLbls();

            try
            {
                string errMSG = "Some error(s) occured:";

                // Creating new cashflow item
                newItem.Item_id = this.cash.Item_id;
                newCash.Item_id = this.cash.Item_id;

                // From user (changes made)
                try
                {
                    newCash.Paid = DateTime.Parse(datPickCashflowChooseDate.Text);
                }
                catch (ApplicationException ex)
                {
                    isValid = false;
                    lblErrCashflowChooseDate.Visible = true;
                    datPickCashflowChooseDate.Text = this.cash.Paid.ToString();
                    errMSG += "\n" + ex.Message;
                }

                try
                {
                    newItem.Description = txtBoxCashflowChooseDescr.Text;
                }
                catch (ApplicationException ex)
                {
                    isValid = false;
                    lblErrCashflowChooseDescr.Visible = true;
                    txtBoxCashflowChooseDescr.Text = this.cash.Item.Description;
                    errMSG += "\n" + ex.Message;
                }

                try
                {
                    try
                    {
                        newItem.Item_value = Int32.Parse(txtBoxCashflowChooseVal.Text);
                    }
                    catch (FormatException ex)
                    {
                        ex.ToString();
                        throw new ApplicationException("You have entered invalid number format into " +
                            "item's value. Please, insert digits from 0 to 9.");
                    }
                }
                catch (ApplicationException ex)
                {
                    isValid = false;
                    lblErrCashflowChooseValue.Visible = true;
                    txtBoxCashflowChooseVal.Text = "" + this.cash.Item.Item_value;
                    errMSG += "\n" + ex.Message;
                }

                try
                {
                    if (radBtnCashflowChooseInc.Checked)
                    {
                        newCash.Cashtype = "income";
                    }
                    else if (radBtnCashflowChooseOut.Checked)
                    {
                        newCash.Cashtype = "outcome";
                    }
                    else
                    {
                        newCash.Cashtype = "";
                    }
                }
                catch (ApplicationException ex)
                {
                    isValid = false;
                    lblErrCashflowChooseCash.Visible = true;
                    setCashtypeRadioSelector();
                    errMSG += "\n" + ex.Message;
                }

                try
                {
                    // If first item selected, no category was choosen = error
                    if (cmbBoxCashflowChooseCateg.SelectedIndex == 0)
                        throw new ApplicationException("Please, you have to choose any category. ");

                    int categIndx = cmbBoxCashflowChooseCateg.SelectedIndex - 1;

                    int sum = -1;
                    for (int i = 0; i < this.loadedCategs.Count; i++)
                    {
                        if ((sum + 1 + this.loadedCategs[i].subCategoriesCount()) >= categIndx)
                        {
                            // If category is selected
                            if (categIndx == (sum + 1))
                            {
                                newCash.Category_id = this.loadedCategs[i].Category_id;
                            }
                            // If subcategory of category is selected
                            else
                            {
                                newCash.Category_id = this.loadedCategs[i][(categIndx - sum - 2)].Category_id;
                            }
                            break;
                        }

                        sum += this.loadedCategs[i].subCategoriesCount() + 1;
                    }
                }
                catch (ApplicationException ex)
                {
                    isValid = false;
                    setCategoryBoxSelector();
                    lblErrCashflowChooseCateg.Visible = true;
                    errMSG += "\n" + ex.Message;
                }

                // Joining item to cash
                newCash.Item = newItem;

                // Validity check
                if (!isValid)
                    throw new ApplicationException(errMSG);

                cashDAO.updateCash(newCash);

                // Submits changes and saves cash into DB
                LinqUtil.submitChanges();

                // Closes editation dialog
                this.isSubmitted = true;
                this.Close();
            }
            catch (ApplicationException ex)
            {
                showErrMSG(ex.Message, lblCashflowChooseMSG, lblCashflowChooseMSGPic);
            }
            finally
            {
                clean();
            }
        }
        #endregion buttons

        #region loading
        /// <summary> Loads categories and init creation or editation form environment during 
        /// dialog initalization. </summary>
        private void CashflowItemDialog_Load(object sender, EventArgs e)
        {
            CategoryDAO categoryDAO = new CategoryDAO();

            // Load all categories and their subcategories
            this.loadedCategs = categoryDAO.getAllCategories(this.currentProf, true);

            if (this.cash == null)
            {
                initCreate();
            }
            else
            {
                initEdit();
            }

            clean();
        }
        #endregion loading

    }
}
