using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Linq;
using System.Reflection;
using System.Windows.Forms;

namespace MoneyFlow
{
    /// <summary> About form giving user closer application info details. </summary>
    partial class AboutForm : Form
    {

 // == CONSTRUCTORS ===========================================================================

        public AboutForm()
        {
            InitializeComponent();
        }

 // == LISTENERS METHODS ======================================================================

        #region buttons
        /// <summary> Closes About form dialog. </summary>
        private void okButton_Click(object sender, EventArgs e)
        {
            this.Close();
        }
        #endregion buttons

    }
}
