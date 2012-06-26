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
    /// <summary> Label - Cashflow detail. </summary>
    public partial class LabelCashflowDetail : Label
    {
        public LabelCashflowDetail()
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
            this.Location = new System.Drawing.Point(206, 24);
            this.Margin = new System.Windows.Forms.Padding(5, 0, 5, 0);
            this.Size = new System.Drawing.Size(308, 24);
            this.TabIndex = 14;
            this.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
        }
    }
}
