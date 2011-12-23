using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Data;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace MoneyFlow
{    
    /// <summary> Label - Stopper Warning level. </summary>
    public partial class LabelStopperWarning : Label
    {
        public LabelStopperWarning()
        {
            InitializeComponent();
            initSettings();
        }

        /// <summary> Sets label's properties. </summary>
        private void initSettings()
        {
            this.AutoSize = true;
            this.Dock = System.Windows.Forms.DockStyle.Fill;
            this.ForeColor = System.Drawing.Color.DarkGoldenrod;
            this.Image = global::MoneyFlow.Properties.Resources.error1;
            this.ImageAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.Location = new System.Drawing.Point(3, 40);
            this.Margin = new System.Windows.Forms.Padding(3, 0, 0, 0);
            this.Size = new System.Drawing.Size(73, 20);
            this.TabIndex = 8;
            this.Text = "(Warning)";
            this.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
        }
    }
}
