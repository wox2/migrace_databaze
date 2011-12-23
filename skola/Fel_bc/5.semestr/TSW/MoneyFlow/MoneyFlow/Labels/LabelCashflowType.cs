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
    public partial class LabelCashflowType : Label
    {
        /// <summary> Label - Cashflow type. </summary>
        public LabelCashflowType()
        {
            InitializeComponent();
            initSettings();
        }

        /// <summary> Sets label's properties. </summary>
        private void initSettings()
        {
            this.AutoSize = true;
            this.Dock = System.Windows.Forms.DockStyle.Fill;
            this.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.Location = new System.Drawing.Point(72, 24);
            this.Margin = new System.Windows.Forms.Padding(1, 0, 1, 0);
            this.Size = new System.Drawing.Size(57, 24);
            this.TabIndex = 6;
            this.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
        }
    }
}
