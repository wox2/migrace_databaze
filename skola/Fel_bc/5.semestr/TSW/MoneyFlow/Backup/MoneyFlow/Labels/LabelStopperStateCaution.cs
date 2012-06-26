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
    /// <summary> Label - Displays current/set stopper treshold value. </summary>
    public partial class LabelStopperStateCaution : Label
    {
        public LabelStopperStateCaution()
        {
            InitializeComponent();
            initSettings();
        }

        /// <summary> Sets label's properties. </summary>
        private void initSettings()
        {
            this.AutoSize = true;
            this.Dock = System.Windows.Forms.DockStyle.Fill;
            this.Font = new System.Drawing.Font("Tahoma", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.ForeColor = System.Drawing.Color.SteelBlue;
            this.Location = new System.Drawing.Point(344, 0);
            this.Margin = new System.Windows.Forms.Padding(0);
            this.Size = new System.Drawing.Size(111, 20);
            this.TabIndex = 2;
            this.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
        }
    }
}
