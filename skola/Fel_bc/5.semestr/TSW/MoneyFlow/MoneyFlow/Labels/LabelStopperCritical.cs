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
    /// <summary> Label - Stopper Critical level. </summary>
    public partial class LabelStopperCritical : Label
    {

        public LabelStopperCritical()
        {
            InitializeComponent();
            initSettings();
        }
        
        /// <summary> Sets label's properties. </summary>
        public void initSettings()
        {
            this.AutoSize = true;
            this.Dock = System.Windows.Forms.DockStyle.Fill;
            this.ForeColor = System.Drawing.Color.OrangeRed;
            this.Image = global::MoneyFlow.Properties.Resources.exclamation;
            this.ImageAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.Location = new System.Drawing.Point(3, 20);
            this.Margin = new System.Windows.Forms.Padding(3, 0, 0, 0);
            this.Size = new System.Drawing.Size(73, 20);
            this.TabIndex = 4;
            this.Text = "(Critical)";
            this.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
        }
    }
}
