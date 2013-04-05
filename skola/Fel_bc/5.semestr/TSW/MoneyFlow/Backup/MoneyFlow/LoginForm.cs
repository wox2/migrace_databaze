using System;
using System.Linq;
using System.Windows.Forms;
using DAL;
using BLL;
using System.Drawing;

namespace MoneyFlow
{
    /// <summary> User login startup form. </summary>
    public partial class LoginForm : Form
    {

 // == INSTANCE VARIABLES =====================================================================

        #region instance variables
        /// <summary> All loaded accounts. </summary>
        private Account[] loadedAccounts;

        /// <summary> Indicates whether or not is current user logged. </summary>
        private bool isLogged = false;

        /// <summary> Main form from which is this login form run. </summary>
        private MFlowWin parent;
        #endregion instance variables

 // == CONSTRUCTORS  ==========================================================================

        /// <summary> Initialize login form. </summary>
        public LoginForm(MFlowWin parent)
        {
            InitializeComponent();
            this.parent = parent;
        }

 // == INSTANCE PRIVATE METHODS ===============================================================

        #region loading
        /// <summary>Loads user's nick and full name into List box. </summary>
        private void loadAccountsInfo()
        {
            AccountDAO accountDAO = new AccountDAO();

            // Loads all accounts from DB
            this.loadedAccounts = accountDAO.getAllAccounts().ToArray();

            for (int i = 0; i < loadedAccounts.Length; i++)
            {
                lstBoxLoginUsers.Items.Add(this.loadedAccounts[i]);
            }
        }
        #endregion loading

        #region services
        /// <summary> Cleans and disposes DB data context. </summary>
        private void cleanData()
        {
            LinqUtil.cleanUp();
        }
        #endregion services

        #region logging in
        /// <summary> Change login state to true (allows user to login). </summary>
        private void login(Account loggedAcc)
        {
            this.isLogged = true;
            showOkMSG("You have been logged! Welcome.", lblLoginMSG, lblLoginMSGPic);
            btnLoginLog.Visible = false;

            // Disables all buttons
            btnLoginExit.Enabled = false;
            btnLoginLog.Enabled = false;
            btnLoginCreateNew.Enabled = false;
            lstBoxLoginUsers.Enabled = false;

            // Updates and waits 2 seconds
            this.Update();
            System.Threading.Thread.Sleep(2000);

            // Sets logging account
            parent.LoggedAcc = loggedAcc;

            this.Close();
        }
        #endregion logging in

        #region GUI
        /// <summary> Disables validation error pictures showing in Create new account. </summary>
        private void disableValidationErrLbls()
        {
            lblErrLoginCreateName.Visible = false;
            lblErrLoginCreateNick.Visible = false;
            lblErrLoginCreatePass.Visible = false;
            lblErrLoginCreatePassRe.Visible = false;
        }

        /// <summary> Resets Login form info messages to default state. </summary>
        private void resetInfoMSGs()
        {
            showInfoMSG("Welcome! Please, select your account.",
                lblLoginMSG, lblLoginMSGPic);
            showInfoMSG("To create new user account, please, fill form below.",
                lblLoginCreateMSG, lblLoginCreateMSGPic);
        }

        /// <summary> Clears all textboxes in Login and Create new Account form. </summary>
        private void clearAllTxtBoxes()
        {
            disableValidationErrLbls();
            txtBoxLoginCreateName.Clear();
            txtBoxLoginCreateNick.Clear();
            txtBoxLoginCreatePass.Clear();
            txtBoxLoginCreateRePass.Clear();
            txtBoxLoginPass.Clear();
        }
        #endregion GUI

        #region updates
        /// <summary> Updates and adds new Account into list boxes on Login screen. </summary>
        /// <param name="newAcc">New Account instance.</param>
        private void lstBoxLoginUpdate(Account newAcc)
        {
            // Makes a new place in loadedAccs field
            Array.Resize(ref this.loadedAccounts, loadedAccounts.Length + 1);
            this.loadedAccounts[loadedAccounts.Length - 1] = newAcc;

            // Adds into list
            lstBoxLoginUsers.Items.Add(newAcc);
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
        #endregion messages

 // == LISTENERS METHODS ======================================================================

        #region loading
        /// <summary> Loads accounts into listBox when Login form loads. </summary>
        private void LoginForm_Load(object sender, EventArgs e)
        {
            resetInfoMSGs();
            loadAccountsInfo();
            cleanData();
        }
        #endregion loading

        #region closing
        /// <summary> Exits app when user clicks on Exit button. </summary>
        private void btnLoginExit_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        /// <summary> Exits app when login form is closed and user is not logged. </summary>
        private void LoginForm_FormClosed(object sender, FormClosedEventArgs e)
        {
            if (!this.isLogged) Application.Exit();
        }
        #endregion closing

        #region buttons
        /// <summary> Checks password from user against account password and logs in 
        /// if correspond. </summary>
        private void btnLoginLog_Click(object sender, EventArgs e)
        {
            int selectedAccount = lstBoxLoginUsers.SelectedIndex;
            string passToCheck = txtBoxLoginPass.Text;

            // Progress bar loading..
            proBarLogin.Visible = true;
            btnLoginLog.Visible = false;

            for (int i = 0; i < 10; i++)
            {
                proBarLogin.PerformStep();
                System.Threading.Thread.Sleep(100);
            }

            // Reseting layout
            proBarLogin.Value = 0;
            proBarLogin.Visible = false;
            btnLoginLog.Visible = true;

            try
            {
                if (this.loadedAccounts[selectedAccount].checkPassword(passToCheck)) 
                    login(this.loadedAccounts[selectedAccount]);
            }
            catch (ApplicationException ex)
            {
                showErrMSG(ex.Message, lblLoginMSG, lblLoginMSGPic);
                txtBoxLoginPass.Text = null;
            }
        }

        /// <summary> Validates and if ok, creates new account according to 
        /// information inserted into Create new Account form. </summary>
        private void btnLoginCreateAcc_Click(object sender, EventArgs e)
        {
            Account acc = new Account();
            AccountDAO accountDAO = new AccountDAO();

            bool isValid = true;
            disableValidationErrLbls();

            try
            {
                string errMSG = "Error(s) occured:";

                // Creates new Account
                acc.Account_id = accountDAO.getNewID();

                try
                {
                    acc.Name = txtBoxLoginCreateName.Text;
                }
                catch (ApplicationException ex)
                {
                    isValid = false;
                    lblErrLoginCreateName.Visible = true;
                    txtBoxLoginCreateName.Text = null;
                    errMSG += "\n" + ex.Message;
                }

                try
                {
                    acc.Nick = txtBoxLoginCreateNick.Text;
                }
                catch (ApplicationException ex)
                {
                    isValid = false;
                    txtBoxLoginCreateNick.Text = null;
                    lblErrLoginCreateNick.Visible = true;
                    errMSG += "\n" + ex.Message;
                }

                try
                {
                    if (!txtBoxLoginCreatePass.Text.Equals(txtBoxLoginCreateRePass.Text))
                        throw new ApplicationException("Your passwords do NOT match! Please, fill them again.");

                    acc.Pass = txtBoxLoginCreatePass.Text;
                }
                catch (ApplicationException ex)
                {
                    isValid = false;
                    lblErrLoginCreatePass.Visible = true;
                    lblErrLoginCreatePassRe.Visible = true;
                    txtBoxLoginCreatePass.Text = null;
                    txtBoxLoginCreateRePass.Text = null;
                    errMSG += "\n" + ex.Message;
                }

                // Validity check
                if (!isValid)
                    throw new ApplicationException(errMSG);

                accountDAO.insertAccount(acc);

                // Submits changes and inserts account into DB
                LinqUtil.submitChanges();

                // Informs user about success and updates List box
                panLoginCreateNew.Visible = false;
                showOkMSG("New account " + acc.Nick + " was created!", lblLoginMSG, lblLoginMSGPic);
                lstBoxLoginUpdate(acc);
            }
            catch (ApplicationException ex)
            {
                showErrMSG(ex.Message, lblLoginCreateMSG, lblLoginCreateMSGPic);
            }
            finally
            {
                cleanData();
            }
        }

        /// <summary> Displays Create new Account form when clicked on button. </summary>
        private void btnLoginCreateNew_Click(object sender, EventArgs e)
        {
            panLoginCreateNew.Visible = true;
        }

        /// <summary> Displays Create new Account form when clicked on button. </summary>
        private void btnLoginCreateBack_Click(object sender, EventArgs e)
        {
            panLoginCreateNew.Visible = false;
        }
        #endregion buttons

        #region listbox
        /// <summary> Triggers when user selects row in a listbox. </summary>
        private void lstBoxLoginUsers_SelectedIndexChanged(object sender, EventArgs e)
        {
            int selected = lstBoxLoginUsers.SelectedIndex;

            if (selected != -1)
            {
                txtBoxLoginPass.Enabled = true;
                btnLoginLog.Enabled = true;
                showInfoMSG("Please, enter your password and log in.", lblLoginMSG, lblLoginMSGPic);
            }
        }
        #endregion listbox

        #region panels
        /// <summary> Changes sorroundings when switching to Create New Account 
        /// panel and back</summary>
        private void panLoginCreateNew_VisibleChanged(object sender, EventArgs e)
        {
            if (panLoginCreateNew.Visible)
            {
                this.Text = "Money flow - Create new account";
            }
            else
            {
                this.Text = "Money flow - login";
            }
            clearAllTxtBoxes();
            resetInfoMSGs();
        }
        #endregion panels

    }
}
