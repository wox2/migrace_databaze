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
    /// <summary> Button - Button "Delete" for deleting selected item (mini version). </summary>
    public partial class ButtonDeleteMini : Button
    {
        public ButtonDeleteMini()
        {
            InitializeComponent();
            initSettings();
        }

        /// <summary> Sets buttons's properties. </summary>
        private void initSettings()
        {
            this.Image = global::MoneyFlow.Properties.Resources.delete;
            this.Location = new System.Drawing.Point(703, 24);
            this.Margin = new System.Windows.Forms.Padding(0);
            this.Size = new System.Drawing.Size(26, 24);
            this.TabIndex = 16;
            this.UseVisualStyleBackColor = true;
        }
    }
}
