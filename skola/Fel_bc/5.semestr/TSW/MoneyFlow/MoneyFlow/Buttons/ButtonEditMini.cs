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
    /// <summary> Button - Button "Edit" for editing any item (miniversion). </summary>
    public partial class ButtonEditMini : Button
    {
        public ButtonEditMini()
        {
            InitializeComponent();
            initSettings();
        }

        /// <summary> Sets buttons's properties. </summary>
        private void initSettings()
        {
            this.Image = global::MoneyFlow.Properties.Resources.pencil;
            this.Location = new System.Drawing.Point(676, 24);
            this.Margin = new System.Windows.Forms.Padding(0);
            this.Size = new System.Drawing.Size(26, 24);
            this.TabIndex = 15;
            this.UseVisualStyleBackColor = true;
        }
    }
}
