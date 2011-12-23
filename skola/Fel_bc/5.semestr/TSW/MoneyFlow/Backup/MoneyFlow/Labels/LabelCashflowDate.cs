using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Data;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace MoneyFlow.Labels
{
    /// <summary> Label - Cashflow date. </summary>
    public partial class LabelCashflowDate : Label
    {
        public LabelCashflowDate()
        {
            InitializeComponent();
            initSettings();
        }

        /// <summary> Sets label's properties. </summary>
        private void initSettings()
        {
            this.AutoSize = true;
            this.Dock = System.Windows.Forms.DockStyle.Fill;
            this.Font = new System.Drawing.Font("Tahoma", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.Location = new System.Drawing.Point(1, 24);
            this.Margin = new System.Windows.Forms.Padding(1, 0, 1, 0);
            this.Size = new System.Drawing.Size(69, 24);
            this.TabIndex = 7;
            this.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
        }
    }
}
