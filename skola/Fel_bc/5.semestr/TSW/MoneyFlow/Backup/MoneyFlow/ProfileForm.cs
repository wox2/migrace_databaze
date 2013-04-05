using System;
using System.Collections.Generic;
using System.Drawing;
using System.Windows.Forms;
using BLL;
using DAL;

namespace MoneyFlow
{
    /// <summary>Profile management form providing CRUD interface for user. </summary>
    public partial class ProfileForm : Form
    {

 // == INSTANCE VARIABLES =====================================================================

        #region instance variables
        /// <summary> Currently logged user. </summary>
        private Account loggedAcc;

        /// <summary> Active profile. </summary>
        private Profile currentProf;
        public Profile ActiveProfileResult
        {
            get {return this.currentProf;}
        }

        /// <summary> Profile selected by user in list of all available profiles. </summary>
        private Profile selectedProf
        {
            get
            {
                // Concrete profile not selected = fail
                if (lstViewAvailProfiles.SelectedIndices.Count != 1)
                    return null;

                int selectedIndx = lstViewAvailProfiles.SelectedIndices[0];
                return this.loadedProfiles[selectedIndx];
            }
            set
            {
                if (lstViewAvailProfiles.SelectedIndices.Count == 1)
                {
                    int selectedIndx = lstViewAvailProfiles.SelectedIndices[0];
                    this.loadedProfiles[selectedIndx] = value;
                }
            }
        }

        /// <summary> List of all available profiles. </summary>
        private List<Profile> loadedProfiles;

        /// <summary> If profile was switched to another one indicator. </summary>
        private bool isSwitched = false;
        public bool IsSwitched
        {
            get { return this.isSwitched; }
        }

        /// <summary> If profile was modified somehow indicator. </summary>
        private bool isChanged = false;
        public bool IsChanged
        {
            get { return this.isChanged; }
        }

        #endregion instance variables

 // == INSTANCE CONSTANTS =====================================================================

        #region instance constants
        /// <summary>Icons indexes for list of all available profiles. </summary>
        private const int ICON_BUSINESS_PROFILE = 0;
        private const int ICON_PERSONAL_PROFILE = 1;
        private const int ICON_CURRENT = 2;
        #endregion instance constants

 // == CONSTRUCTORS ===========================================================================

        public ProfileForm(Account loggedAcc, Profile currentProf)
        {
            this.loggedAcc = loggedAcc;
            this.currentProf = currentProf;

            InitializeComponent();
        }

 // == INSTANCE PRIVATE METHODS ===============================================================

        #region loading
        /// <summary> Load all available profiles from current account. </summary>
        private void loadProfiles()
        {
            ProfileDAO profileDAO = new ProfileDAO();
            loadedProfiles = profileDAO.getAllProfiles(this.loggedAcc);

            updateProfilesInfo();
        }
        #endregion loading

        #region closing
        /// <summary> Closes Profile dialog if any profile is set to active. </summary>
        private void closeDialog()
        {
            if (loadedProfiles.Count > 0)
            {
                this.Close();
            }
            else
            {
                showErrMSG("You must switch to any profile first!", lblProfilesMSG, lblProfilesMSGPic);
            }
        }
        #endregion closing

        #region services
        /// <summary> Cleans and disposes DB data context. </summary>
        private void clean()
        {
            LinqUtil.cleanUp();
        }

        /// <summary> Makes selected profile active. </summary>
        private void switchToNewProfile()
        {
            // If profile is not selected 
            if (selectedProf == null) return;

            Profile newProf = selectedProf;

            try
            {
                if (newProf == currentProf)
                    throw new ApplicationException("This profile is already active.");

                // Updates
                this.currentProf = newProf;
                updateProfilesInfo();
                showOkMSG("Successfully switched to profile " + newProf.Name + ".",
                    lblProfilesMSG, lblProfilesMSGPic);

                isSwitched = true;
            }
            catch (ApplicationException ex)
            {
                showErrMSG(ex.Message, lblProfilesMSG, lblProfilesMSGPic);
            }
        }
        #endregion services

        #region updates
        /// <summary> Updates all available profiles list. </summary>
        private void updateProfilesInfo()
        {
            lstViewAvailProfiles.BeginUpdate();

            // Clears list view
            int itemsCnt = lstViewAvailProfiles.Items.Count;
            for (int i = 0; i < itemsCnt; i++)
            {
                lstViewAvailProfiles.Items.RemoveAt(0);
            }

            // If any profiles load
            if (this.loadedProfiles.Count >= 1)
            {
                // Loads current profile data
                lblProfileCurrentProf.Text = currentProf.ToString();

                // Load all available profiles into list view
                foreach (Profile prof in loadedProfiles)
                {
                    ListViewItem item = new ListViewItem();

                    switch (prof.Type)
                    {
                        case "business": item.ImageIndex = ICON_BUSINESS_PROFILE; break;
                        case "personal": item.ImageIndex = ICON_PERSONAL_PROFILE; break;
                        default: break;
                    }

                    if (this.currentProf == prof)
                        item.ImageIndex = ICON_CURRENT;

                    item.Text = prof.Name;

                    // Description
                    ListViewItem.ListViewSubItem subItem = new ListViewItem.ListViewSubItem();

                    subItem.Text = prof.Description;
                    if (prof.Description == null)
                        subItem.Text = "-";

                    // Adds into list view
                    item.SubItems.Add(subItem);
                    lstViewAvailProfiles.Items.Add(item);
                }
            }
            // When no profiles are available
            else
            {
                // Loads current profile data
                lblProfileCurrentProf.Text = "-";

                ListViewItem item = new ListViewItem();
                item.Text = "- no profiles -";

                // Description
                ListViewItem.ListViewSubItem subItem = new ListViewItem.ListViewSubItem();
                subItem.Text = "No profiles are available. Please, create any.";

                // Adds into list view
                item.SubItems.Add(subItem);
                lstViewAvailProfiles.Items.Add(item);

                // Lock list view
                lstViewAvailProfiles.IsAccessible = false;
            }

            lstViewAvailProfiles.EndUpdate();
        }
        #endregion updates

        #region GUI
        /// <summary> Enables/Disables Delete, Edit and Switch buttons. </summary>
        /// <param name="isEnabled">True - enable buttons
        ///                         False - disable buttons.</param>
        private void enableControlButtons(bool isEnabled)
        {
            btnProfilesDelete.Enabled = isEnabled;
            btnProfilesEdit.Enabled = isEnabled;
            btnProfilesSwitch.Enabled = isEnabled;
        }

        /// <summary> Disables validation error pictures showing in Create/Edit profile. </summary>
        private void disableValidationErrLbls()
        {
            lblErrProfileChangeType.Visible = false;
            lblErrProfileChangeName.Visible = false;
            lblErrProfileChangeDescr.Visible = false;
        }

        /// <summary>Shows Create button and hides Edit button (or vica versa) in Profile 
        /// Change panel. </summary>
        /// <param name="isVisible">True - shows Create button and hides Edit button. 
        ///                         False - hides Create button and shows Edit button. </param>
        private void showCreateButton(bool isVisible)
        {
            btnProfileChangeChoose.Visible = isVisible;
            btnProfileChangeEdit.Visible = !isVisible;
        }

        /// <summary>Re-selects already selected Profile from available profiles list
        /// when selection is accidentaly lost. </summary>
        private void reselectSelectedItem()
        {
            if (lstViewAvailProfiles.SelectedItems.Count == 0)
                return;

            int selectedIndx = lstViewAvailProfiles.SelectedIndices[0];
            lstViewAvailProfiles.Focus();
            lstViewAvailProfiles.Items[selectedIndx].Selected = true;
        }

        /// <summary> Resets Profile form info messages to default state. </summary>
        private void resetInfoMSGs()
        {
            showEmptyMSG(lblProfilesMSG, lblProfilesMSGPic);
            showInfoMSG("To create new profile, please, fill form below.",
                lblProfilesChangeMSG, lblProfilesChangeMSGPic);
        }

        /// <summary> Clears all textboxes in Profile Main and Profile Create/Edit form. </summary>
        private void clearAllTxtBoxes()
        {
            disableValidationErrLbls();
            txtBoxProfilesChangeName.Clear();
            txtBoxProfilesChangeDescr.Clear();
            radBtnProfilesChangeBusi.Checked = false;
            radBtnProfilesChangePers.Checked = false;
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

 // == LISTENERS METHODS ======================================================================

        #region loading
        /// <summary> Initialize list of all available profiles and sets default 
        /// environment settings.</summary>
        private void ProfileForm_Load(object sender, EventArgs e)
        {
            loadProfiles();
            enableControlButtons(false);
            clean();
        }
        #endregion loading

        #region buttons
        /// <summary> Tries to close Profile management dialog. </summary>
        private void btnProfilesReturn_Click(object sender, EventArgs e)
        {
            closeDialog();
        }

        /// <summary> Makes selected profile active. </summary>
        private void btnProfilesSwitch_Click(object sender, EventArgs e)
        {
            switchToNewProfile();
        }

        /// <summary> Cancels any changes and returns to Profiles overview. </summary>
        private void btnProfileChangeCancel_Click(object sender, EventArgs e)
        {
            panProfilesChange.Visible = false;
        }

        /// <summary> Shows Create new Profile form and sets environment to default. </summary>
        private void btnProfilesCreate_Click(object sender, EventArgs e)
        {
            panProfilesChange.Visible = true;
            showCreateButton(true);
        }

        /// <summary> Creates new profile based on information in Create new profile form. </summary>
        private void btnProfileChangeChoose_Click(object sender, EventArgs e)
        {
            Profile profile = new Profile();
            ProfileDAO profileDAO = new ProfileDAO();

            bool isValid = true;
            disableValidationErrLbls();

            try
            {
                string errMSG = "Error(s) occured:";

                // Creating new profile
                profile.Profile_id = profileDAO.getNewID();
                profile.Account_id = this.loggedAcc.Account_id;

                try
                {
                    if (radBtnProfilesChangePers.Checked)
                    {
                        profile.Type = "personal";
                    }
                    else if (radBtnProfilesChangeBusi.Checked)
                    {
                        profile.Type = "business";
                    }
                    else
                    {
                        profile.Type = "";
                    }
                }
                catch (ApplicationException ex)
                {
                    isValid = false;
                    lblErrProfileChangeType.Visible = true;
                    errMSG += "\n" + ex.Message;
                }

                try
                {
                    profile.Name = txtBoxProfilesChangeName.Text;
                }
                catch (ApplicationException ex)
                {
                    isValid = false;
                    lblErrProfileChangeName.Visible = true;
                    txtBoxProfilesChangeName.Text = null;
                    errMSG += "\n" + ex.Message;
                }

                try
                {
                    profile.Description = txtBoxProfilesChangeDescr.Text;
                }
                catch (ApplicationException ex)
                {
                    isValid = false;
                    lblErrProfileChangeDescr.Visible = true;
                    txtBoxProfilesChangeDescr.Text = null;
                    errMSG += "\n" + ex.Message;
                }

                // Validity check
                if (!isValid)
                    throw new ApplicationException(errMSG);

                profileDAO.insertProfile(profile);

                // Submits changes and inserts profile into DB
                LinqUtil.submitChanges();

                // Informs user about success and updates List box
                panProfilesChange.Visible = false;
                showOkMSG("New profile " + profile.Name + " was created!", lblProfilesMSG, lblProfilesMSGPic);

                // Update listview
                loadedProfiles.Add(profile);
                updateProfilesInfo();
            }
            catch (ApplicationException ex)
            {
                showErrMSG(ex.Message, lblProfilesChangeMSG, lblProfilesChangeMSGPic);
            }
            finally
            {
                clean();
            }
        }

        /// <summary> Asks user whether or not selected Profile should be deleted 
        /// with all its contents. </summary>
        private void btnProfilesDelete_Click(object sender, EventArgs e)
        {
            // Concrete profile not selected = fail
            if (selectedProf == null) return;

            Profile profile = selectedProf;

            if (MessageBox.Show("Are you sure you want to delete profile " + profile.Name +
                " and all its cashflow contents?", "Deleting profile " + profile.Name,
                MessageBoxButtons.YesNo) == DialogResult.Yes)
            {
                ProfileDAO profileDAO = new ProfileDAO();

                profileDAO.deleteProfile(profile);

                // Submit Delete
                try
                {
                    LinqUtil.submitChanges();
                }
                catch (NullReferenceException ex)
                {
                    ex.ToString();
                    showErrMSG("Unable to delete profile with any " +
                        "categories. Please, delete them first.", lblProfilesMSG, lblProfilesMSGPic);
                    return;
                }
                finally
                {
                    clean();
                }
                loadedProfiles.Remove(profile);
                updateProfilesInfo();
                showOkMSG("Profile " + profile.Name + " was successfully deleted.", lblProfilesMSG, lblProfilesMSGPic);

            }
            else
            {
                reselectSelectedItem();
            }
        }

       /// <summary> Loads editation and fills form with selected Profile's information. </summary>
        private void btnProfilesEdit_Click(object sender, EventArgs e)
        {
            showCreateButton(false);
            panProfilesChange.Visible = true;

            // Concrete profile not selected = fail
            if (selectedProf == null) return;

            Profile profile = selectedProf;

            // Filling forms GUI with info of selected profile
            switch (profile.Type)
            {
                case "personal": radBtnProfilesChangePers.Checked = true; break;
                case "business": radBtnProfilesChangeBusi.Checked = true; break;
                default: break;
            }
            txtBoxProfilesChangeDescr.Text = profile.Description;
            txtBoxProfilesChangeName.Text = profile.Name;

            showInfoMSG("To edit current profile details, please, change information in form below.",
                lblProfilesChangeMSG, lblProfilesChangeMSGPic);
        }

        /// <summary> Save changes made during Profile information editation 
        /// (if something has changed). </summary>
        private void btnProfileChangeEdit_Click(object sender, EventArgs e)
        {
            string profType = "";
            if (radBtnProfilesChangeBusi.Checked)
                profType = "profile";
            else if (radBtnProfilesChangePers.Checked)
            {
                profType = "business";
            }

            // Profile has to be selected
            if (selectedProf == null) return;

            if (!profType.Equals(selectedProf.Type) ||
                 !txtBoxProfilesChangeDescr.Text.Equals(selectedProf.Description) ||
                 !txtBoxProfilesChangeName.Text.Equals(selectedProf.Name))
            {
                Profile changedProf = new Profile();
                ProfileDAO profileDAO = new ProfileDAO();

                bool isValid = true;
                disableValidationErrLbls();

                try
                {
                    string errMSG = "Profile cannot be changed, Error(s) occured:";

                    // Sets profile for update
                    changedProf.Account_id = selectedProf.Account_id;
                    changedProf.Profile_id = selectedProf.Profile_id;

                    try
                    {
                        if (radBtnProfilesChangePers.Checked)
                        {
                            changedProf.Type = "personal";
                        }
                        else if (radBtnProfilesChangeBusi.Checked)
                        {
                            changedProf.Type = "business";
                        }
                        else
                        {
                            changedProf.Type = "";
                        }
                    }
                    catch (ApplicationException ex)
                    {
                        isValid = false;
                        lblErrProfileChangeType.Visible = true;
                        switch (selectedProf.Type)
                        {
                            case "personal":
                                {
                                    radBtnProfilesChangePers.Checked = true;
                                    radBtnProfilesChangeBusi.Checked = false;
                                } break;
                            case "business":
                                {
                                    radBtnProfilesChangeBusi.Checked = true;
                                } break;
                            default: break;
                        }
                        errMSG += "\n" + ex.Message;
                    }

                    try
                    {
                        changedProf.Name = txtBoxProfilesChangeName.Text;
                    }
                    catch (ApplicationException ex)
                    {
                        isValid = false;
                        lblErrProfileChangeName.Visible = true;
                        txtBoxProfilesChangeName.Text = selectedProf.Name;
                        errMSG += "\n" + ex.Message;
                    }

                    try
                    {
                        changedProf.Description = txtBoxProfilesChangeDescr.Text;
                    }
                    catch (ApplicationException ex)
                    {
                        isValid = false;
                        lblErrProfileChangeDescr.Visible = true;
                        txtBoxProfilesChangeDescr.Text = selectedProf.Description;
                        errMSG += "\n" + ex.Message;
                    }

                    // Validity check
                    if (!isValid)
                        throw new ApplicationException(errMSG);

                    profileDAO.updateProfile(changedProf);

                    // Submits changes and inserts account into DB
                    LinqUtil.submitChanges();

                    // Informs user about success and updates List box
                    panProfilesChange.Visible = false;
                    showOkMSG("Profile " + changedProf.Name + " was changed!", lblProfilesMSG, lblProfilesMSGPic);

                    // Update listview
                    if (currentProf.Profile_id == changedProf.Profile_id)
                    {
                        this.currentProf = changedProf;
                        lblProfileCurrentProf.Text = this.currentProf.ToString();
                    }

                    // Updates
                    selectedProf = changedProf;
                    updateProfilesInfo();

                    this.isChanged = true;
                }
                catch (ApplicationException ex)
                {
                    showErrMSG(ex.Message, lblProfilesChangeMSG, lblProfilesChangeMSGPic);
                }
                finally
                {
                    clean();
                }
            }
            else
            {
                showErrMSG("You have not made any changes so that there's nothing to save!.",
                    lblProfilesChangeMSG, lblProfilesChangeMSGPic);
            }
        }
        #endregion buttons

        #region listview
        /// <summary> Makes selected profile active. </summary>
        private void lstViewAvailProfiles_DoubleClick(object sender, EventArgs e)
        {
            switchToNewProfile();
        }

        /// <summary> Enables edit, delete and switch buttons if any item from list 
        /// is selected (disables if not).</summary>
        private void lstViewAvailProfiles_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (lstViewAvailProfiles.SelectedItems.Count > 0 && loadedProfiles.Count > 0)
            {
                showEmptyMSG(lblProfilesMSG, lblProfilesMSGPic);
                enableControlButtons(true);
            }
            else
            {
                enableControlButtons(false);
            }
        }
        #endregion listview

        #region panels 
        /// <summary> Resets to environments of Panel profile overview and 
        /// Panel profile change to default settings when panel is switched 
        /// to another one.</summary>
        private void panProfilesChange_VisibleChanged(object sender, EventArgs e)
        {
            resetInfoMSGs();
            clearAllTxtBoxes();
            reselectSelectedItem();
        }
        #endregion panels

    }
}
