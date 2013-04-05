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
    /// <summary> Button - Button "Return" for going back. </summary>
    public partial class ButtonReturn : Button
    {

        public ButtonReturn()
        {
            InitializeComponent();
            initSettings();
        }

        /// <summary> Sets buttons's properties. </summary>
        private void initSettings()
        {
            this.Image = global::MoneyFlow.Properties.Resources.door_out;
            this.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.Location = new System.Drawing.Point(11, 211);
            this.Size = new System.Drawing.Size(64, 25);
            this.TabIndex = 9;
            this.Text = "Return";
            this.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.UseVisualStyleBackColor = true;
        }


    }
}
