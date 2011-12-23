using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Data;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace MoneyFlow.Buttons
{
    /// <summary> Button - Button "Edit" for editing any item. </summary>
    public partial class ButtonEdit : Button
    {

        public ButtonEdit()
        {
            initSettings();
            InitializeComponent();
        }

        /// <summary> Sets buttons's properties. </summary>
        private void initSettings()
        {
            this.Image = global::MoneyFlow.Properties.Resources.pencil;
            this.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.Location = new System.Drawing.Point(261, 173);
            this.Size = new System.Drawing.Size(50, 32);
            this.TabIndex = 6;
            this.Text = "Edit";
            this.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.UseVisualStyleBackColor = true;
        }
    }
}
