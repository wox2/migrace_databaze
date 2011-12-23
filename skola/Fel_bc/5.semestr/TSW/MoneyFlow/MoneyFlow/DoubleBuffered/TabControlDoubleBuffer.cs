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
    public partial class TabControlDoubleBuffer : TabControl
    {
        public TabControlDoubleBuffer()
        {
            InitializeComponent();
            this.DoubleBuffered = true;
        }
    }
}
