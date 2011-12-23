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
    /// <summary> Button - Button "Delete" for deleting any item. </summary>
    public partial class ButtonDelete : Button
    {

        public ButtonDelete()
        {
            initSettings();
            InitializeComponent();
        }

        /// <summary> Sets buttons's properties. </summary>
        private void initSettings()
        {
            this.Image = global::MoneyFlow.Properties.Resources.delete;
            this.ImageAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.Location = new System.Drawing.Point(313, 173);
            this.Size = new System.Drawing.Size(62, 32);
            this.TabIndex = 10;
            this.Text = "Delete";
            this.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.UseVisualStyleBackColor = true;
        }
    }
}
