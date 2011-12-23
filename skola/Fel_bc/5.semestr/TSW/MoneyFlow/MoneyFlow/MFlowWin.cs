using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Windows.Forms;
using BLL;
using DAL;
using MoneyFlow.Buttons;
using MoneyFlow.Labels;

namespace MoneyFlow
{   
    /// <summary> Providing main app overview and environment GUI. </summary>
    public partial class MFlowWin : Form
    {

 // == INSTANCE VARIABLES =====================================================================

        #region instance variables and properties
        // Memory leaking testing stack
        private Stack<List<Category>> leakStack = new Stack<List<Category>>();

        // Graphs
        /// <summary> Graphic context for doble buffering. </summary>
        private BufferedGraphicsContext doubleBuffContxt = BufferedGraphicsManager.Current;

        /// <summary> Date from which the graph will be generated. </summary>
        private DateTime graphFromDate = new DateTime(2010, 4, 15);

        /// <summary> Date to which the graph will be generated. </summary>
        private DateTime graphToDate = DateTime.Now;

        /// <summary> Actually logged account </summary>
        private Account loggedAcc;

        /// <summary> Sets currently logged account from out. </summary>
        public Account LoggedAcc
        {
            set { loggedAcc = value; }
        }

        /// <summary> Currently loaded profile. </summary>
        private Profile currentProf;

        // Sortings 
        /// <summary> Sort table column number. </summary>
        private int colSort = 1;

        /// <summary> Direction of sorting. </summary>
        private bool isAscending = false;

        // Paging
        /// <summary> Number of items on 1 page in the table. </summary>
        private const int PAGING_INTERVAL = 10;

        /// <summary> Actual number of row in the table. </summary>
        private int firstRow = 0;

        /// <summary> Currently loaded categories list. </summary>
        private List<Category> loadedCategs = null;
        private List<Category> LoadedCategs
        {
            get
            {
                if (loadedCategs == null)
                {
                    updateLoadedCategories();
                }

                return this.loadedCategs;
                //return (List<Category>)this.leakStack.Peek();
            }

            set
            {
                //this.loadedCategs.RemoveAll();
                this.loadedCategs = value;
                //this.leakStack.Push(this.loadedCategs);
                updateQuickInsertComboBox();
            }
        }

        /// <summary> Currently loaded Stoppers with danger level. </summary>
        private List<Monitoring> loadedStoppers = null;
        private List<Monitoring> LoadedStoppers
        {
            get
            {
                if (this.loadedStoppers == null)
                {
                    updateLoadedStoppers();
                }
                return this.loadedStoppers;
            }

            set
            {
                this.loadedStoppers = value;
                loadStoppers();
            }
        }

        /// <summary> Currently loaded cashflow items. </summary>
        private List<Cashed> loadedCashes = null;
        private List<Cashed> LoadedCashes
        {
            get
            {
                if (this.loadedCashes == null)
                {
                    updateLoadedCashes();
                }

                return this.loadedCashes;
            }

            set
            {
                this.loadedCashes = value;
                loadCashflowData();
            }
        }
        #endregion instance variables and properties

 // == CONSTRUCTORS  ==========================================================================
        
        /// <summary> Initialize main application. </summary>
        public MFlowWin()
        {
            InitializeComponent();
        }

 // == INSTANCE PRIVATE METHODS ===============================================================

        #region dialogs initialization
        /// <summary> Initialize about information dialog. </summary>
        private void initAboutDialog()
        {
            AboutForm about = new AboutForm();
            about.Show();
        }

        /// <summary> Initialize dialog for Editing/Creating new cashflow item. </summary>
        /// <param name="cash">null - creating cashflow item dialog.
        ///                    not null - editation cashflow item dialog. </param>
        private void initAddCashflowItem(Cashed cash)
        {
            CashflowItemDialog cashItemDialog = new CashflowItemDialog(this.currentProf, cash);
            cashItemDialog.ShowDialog();

            if (cashItemDialog.IsSubmitted)
            {
                updateInfo();
                updateLoadedStoppers();
                updateLoadedCashes();
            }

            // Informs user about success
            if (cash == null && cashItemDialog.IsSubmitted)
            {
                showOkMSG("New cashflow items were successfully added.", lblMSG, lblMSGPic);
            }
            else if (cash != null && cashItemDialog.IsSubmitted)
            {
                showOkMSG("Changes to " + cash.Cashtype + " ID " + cash.Item_id +
                          " has been successfully saved.", lblMSG, lblMSGPic);
            }
        }

        /// <summary> Initialize Category manager dialog where user can add/edit 
        /// or remove categories in current profile. </summary>
        private void initCategoryDialog()
        {
            CategoryForm categoryDialog = new CategoryForm(loggedAcc, currentProf);
            categoryDialog.ShowDialog(this);

            // Re-load info
            if (categoryDialog.IsChanged)
            {
                updateInfo();
                updateLoadedCategories();
                updateLoadedStoppers();
                updateLoadedCashes();
            }
            clean();
        }

        /// <summary> Initialize Profile manager dialog and returns active 
        /// profile after closing it. </summary>
        private void initProfileDialog()
        {
            ProfileForm profileDialog = new ProfileForm(loggedAcc, currentProf);
            profileDialog.ShowDialog(this);
            this.currentProf = profileDialog.ActiveProfileResult;

            // Re-load info
            if (profileDialog.IsSwitched)
            {
                updateInfo();
                updateLoadedCategories();
                updateLoadedStoppers();
                updateLoadedCashes();
            } else if (profileDialog.IsChanged)
            {
                updateInfo();
            }

            clean();
        }

        /// <summary> Login window initialization. </summary>
        public void initLogin()
        {
            this.tabConMain.Visible = false;
            showEmptyMSG(lblMSG, lblMSGPic);
            LoginForm login = new LoginForm(this);
            login.ShowDialog(this);
            this.tabConMain.Visible = true;

            // If login was closed by force.
            if (this.loggedAcc == null)
            {
                Exit();
                return;
            }

            // Available info loading
            loadDefaultInfo();
            updateLoadedStoppers();
            updateLoadedCategories();
            updateLoadedCashes();

            // Info msg
            showInfoMSG("Did you know you can roll in categories showed " +
                        "below by clicking on them?", lblMSG, lblMSGPic);

            // Quick Insert
            initQuickInsert();

            // Setting pops to default
            popPanel(panCashflowQuickAdd);
            popPanel(panInfo);
            popPanel(panLastItems);

            clean();
        }
        #endregion dialogs initialization

        #region updates
        /// <summary> Loads all available cashflow items from DB and 
        /// make list of deep copies. </summary>
        private void updateLoadedCashes()
        {
            CashedDAO cashDAO = new CashedDAO();

            List<Cashed> toCopy = cashDAO.getAllCashes(this.currentProf, this.colSort,
                                  this.isAscending);

            // Deep copy
            List<Cashed> copiedCashes = new List<Cashed>();
            foreach (Cashed cash in toCopy)
            {
                copiedCashes.Add((Cashed)cash.Clone());
            }

            this.LoadedCashes = copiedCashes;
        }

        /// <summary> Loads basic info and statistics data into info panel. </summary>
        private void updateInfo()
        {
            ProfileDAO profileDAO = new ProfileDAO();

            // Basic info
            lblInfoProfile.Text = "Current profile: " + this.currentProf.Name;
            lblInfoProfileDescrip.Text = this.currentProf.Description;
            lblInfoProfileType.Text = "Profile type: " + this.currentProf.Type;

            lblInfoActiveSince.Text = "Active from: ";
            try
            {
                lblInfoActiveSince.Text += profileDAO.getFirstCashed(this.currentProf).ToString("dd.MM.yyyy");
            }
            catch (ApplicationException ex)
            {
                lblInfoActiveSince.Text += ex.Message;
            }

            // Statistics - cashflow
            lblInfoIncomeNo.Text = "" + profileDAO.getNumIncomeItems(this.currentProf);
            lblInfoOutcomeNo.Text = "" + profileDAO.getNumOutcomeItems(this.currentProf);

            int totalIncome = profileDAO.getSumIncomeItems(this.currentProf);
            int totalOutcome = profileDAO.getSumOutcomeItems(this.currentProf);

            lblInfoTotalIncNo.Text = "" + totalIncome;
            lblInfoTotalOutNo.Text = "" + totalOutcome;

            int cashflow = totalIncome - totalOutcome;
            if (cashflow >= 0)
            {
                lblInfoCashflowNo.Text = "+" + cashflow;
                lblInfoCashflowNo.ForeColor = Color.SeaGreen;
            }
            else
            {
                lblInfoCashflowNo.Text = "-" + cashflow;
                lblInfoCashflowNo.ForeColor = Color.Red;
            }

            // Statistics - categories
            lblInfoCatsNo.Text = "" + profileDAO.getNumCategories(this.currentProf);
            lblInfoSubcatsNo.Text = "" + profileDAO.getNumSubcategories(this.currentProf);

            // Statistics - stoppers
            lblInfoStoppersNo.Text = "" + profileDAO.getNumStoppers(this.currentProf);
            lblInfoActiveStoppersNo.Text = "" + profileDAO.getNumActiveStoppers(this.currentProf);

            lblInfoCritStoppersNo.Text = "" + profileDAO.getNumCriticalStoppers(this.currentProf);
        }

        /// <summary> Loads available stoppers from DB and updates dependencies. </summary>
        private void updateLoadedStoppers()
        {
            MonitoringDAO monitoringDAO = new MonitoringDAO();

            // Loads all Danger Stoppers
            this.LoadedStoppers = monitoringDAO.getAllDangerStoppers(this.currentProf, true);
        }

        /// <summary> Loads available categories from DB and updates dependencies. </summary>
        private void updateLoadedCategories()
        {
            CategoryDAO categoryDAO = new CategoryDAO();

            // Load all categories and their subcategories
            this.LoadedCategs = categoryDAO.getAllCategories(this.currentProf, true);
        }

        /// <summary> Sets information and environment for cashflow table. </summary>
        /// <param name="itemsCnt"> Number of available cashflow items. </param>
        private void setCashflowTableInterface(int itemsCnt)
        {
            int top = this.firstRow + PAGING_INTERVAL;
            int actual = this.firstRow + 1;

            // Sets previous/next buttons
            if (this.firstRow == 0)
            {
                btnCashflowPrev.Enabled = false;
            }

            if (this.firstRow >= itemsCnt)
                actual = itemsCnt;

            if ((this.firstRow + PAGING_INTERVAL) >= itemsCnt)
            {
                btnCashflowNext.Enabled = false;
                top = itemsCnt;
            }
            else
            {
                btnCashflowNext.Enabled = true;
            }

            // Sets current page status
            lblCashflowResNum.Text = actual + "-" +
                    top + " (" + itemsCnt + ")";
        }

        /// <summary> Initialize Quick insert environment. </summary>
        private void initQuickInsert()
        {
            // Environmental settings
            disableQuickInsetErrLbls();
            uncheckQuickInsertRadBtns();
        }

        /// <summary> Updates categories names in Quick Insert Combobox. </summary>
        private void updateQuickInsertComboBox()
        {
            // Remove all records in combobox
            int itemsCnt = cmbQuickAddCateg.Items.Count;
            for (int i = 0; i < itemsCnt; i++)
            {
                cmbQuickAddCateg.Items.RemoveAt(0);
            }

            // Insert categories into ComboBox
            cmbQuickAddCateg.Items.Add(" - Choose category -");
            cmbQuickAddCateg.SelectedIndex = 0;

            const string INDENT = "     ";

            // Add categories
            for (int i = 0; i < this.loadedCategs.Count; i++)
            {
                cmbQuickAddCateg.Items.Add(this.loadedCategs[i].Name);

                // Add subcategories
                for (int j = 0; j < this.loadedCategs[i].subCategoriesCount(); j++)
                {
                    cmbQuickAddCateg.Items.Add(INDENT + this.loadedCategs[i][j].Name);
                }
            }
        }
        #endregion updates

        #region data loadings
        /// <summary> Loads cashflow from available cash items into table.</summary>
        private void loadCashflowData()
        {
            // If zero cashflow items
            if (this.LoadedCashes.Count == 0)
            {
                lblCashflowInformation.Visible = true;
                tblCashflow.Visible = false;
            }
            else
            {
                lblCashflowInformation.Visible = false;
                tblCashflow.Visible = true;
            }

            // Sets cashflow table environment
            setCashflowTableInterface(this.LoadedCashes.Count);

            // Clear the table, left first row, 5 components in it
            removeTableRows(tblCashflow, 1, 5);

            // Add rows to table
            addRowsToTable(tblCashflow, PAGING_INTERVAL, 24);

            for (int i = 1; i <= PAGING_INTERVAL; i++)
            {
                int cashIndx = (this.firstRow + i - 1);
                // If index is bigger than cashes count
                if (cashIndx >= this.LoadedCashes.Count)
                    break;

                Cashed cash = this.LoadedCashes[cashIndx];

                // Cashflow Creation
                LabelCashflowDate lblDate = new LabelCashflowDate();
                lblDate.Text = cash.Paid.ToString("dd.MM.yyyy");

                LabelCashflowType lblType = new LabelCashflowType();
                lblType.Text = cash.Cashtype;

                LabelCashflowValue lblVal = new LabelCashflowValue();

                switch (cash.Cashtype)
                {
                    case "income":
                        {
                            lblVal.Text = "+" + cash.Item.Item_value;
                            lblVal.ForeColor = Color.Transparent;
                            lblVal.BackColor = Color.MediumSeaGreen;
                            lblVal.Font = new Font(lblVal.Font, FontStyle.Bold);
                        } break;
                    case "outcome":
                        {

                            lblVal.Text = "-" + cash.Item.Item_value;
                            lblVal.ForeColor = Color.Transparent;
                            lblVal.BackColor = Color.FromArgb(221, 63, 63);
                            lblVal.Font = new Font(lblVal.Font, FontStyle.Bold);
                        } break;
                    default: ; break;
                }

                LabelCashflowDetail lblDetail = new LabelCashflowDetail();
                lblDetail.Text = cash.Item.Description;

                if (cash.Item.Description.Equals(""))
                    lblDetail.Text = " - ";

                LabelCashflowCategory lblCateg = new LabelCashflowCategory();

                if (cash.Category.Sup_category_id == null)
                {
                    lblCateg.Text = cash.Category.Name;
                }
                else
                {
                    lblCateg.Text = cash.Category.Sup_category_.Name + " ~ " + cash.Category.Name;
                }

                ButtonEditMini btnEdit = new ButtonEditMini();
                btnEdit.Name = "" + cash.Item.Item_id;

                ButtonDeleteMini btnDelete = new ButtonDeleteMini();
                btnDelete.Name = "" + cash.Item.Item_id;

                btnEdit.Click += new EventHandler(editCashflowItem);
                btnDelete.Click += new EventHandler(deleteCashflowItem);

                // Adding all components to the table
                tblCashflow.Controls.Add(lblDate, 0, i);
                tblCashflow.Controls.Add(lblType, 1, i);
                tblCashflow.Controls.Add(lblVal, 2, i);
                tblCashflow.Controls.Add(lblDetail, 3, i);
                tblCashflow.Controls.Add(lblCateg, 4, i);
                tblCashflow.Controls.Add(btnEdit, 5, i);
                tblCashflow.Controls.Add(btnDelete, 6, i);
            }
        }

        /// <summary> Loads data into Danger Stoppers table. </summary>
        private void loadStoppers()
        {
            // If no Danger Stoppers load
            if (this.LoadedStoppers.Count == 0)
            {
                lblStoppersEmptyMSG.Visible = true;
                tblStoppers.Visible = false;
                return;
            }
            else
            {
                lblStoppersEmptyMSG.Visible = false;
                tblStoppers.Visible = true;
            }

            // Deletes all table rows and their data
            removeTableRows(tblStoppers, 0 , 0);

            // Add appropriate number of rows into table.
            addRowsToTable(tblStoppers, this.LoadedStoppers.Count(), 20);

            // ??? Category cat = this.LoadedStoppers[0].Category;

            // Adds info into table
            for (int i = 0; i < this.LoadedStoppers.Count(); i++)
            {
                // Danger level
                Label lblDanger = null;

                // Current cashflow state
                Label lblState = null;

                // Danger level closer description
                Label lblStateInfo = new Label();
                lblStateInfo.Dock = DockStyle.Fill;
                lblStateInfo.TextAlign = ContentAlignment.MiddleLeft;
                lblStateInfo.Text = this.LoadedStoppers[i].getStateInfo() + ",-";

                switch (this.LoadedStoppers[i].DangerLevel)
                {
                    case "caution":
                        {
                            lblDanger = new LabelStopperCaution();
                            lblState = new LabelStopperStateCaution();
                        } break;
                    case "warning":
                        {
                            lblDanger = new LabelStopperWarning();
                            lblState = new LabelStopperStateWarning();
                        } break;
                    case "critical":
                        {
                            lblDanger = new LabelStopperCritical();
                            lblState = new LabelStopperStateCritical();
                        } break;
                    default:
                        {
                            lblDanger = new Label();
                            lblState = new Label();
                        } break;
                }

                lblState.Text = "[" + this.LoadedStoppers[i].getState() + "]";

                // Adds label components into cells
                tblStoppers.Controls.Add(lblDanger, 0, i);
                tblStoppers.Controls.Add(lblStateInfo, 1, i);
                tblStoppers.Controls.Add(lblState, 2, i);
            }
        }

        /// <summary> Loads default information about logged account. </summary>
        private void loadDefaultInfo()
        {
            // Account info
            lblInfoUser.Text = loggedAcc.ToString();

            // Profile info
            ProfileDAO profileDAO = new ProfileDAO();
            List<Profile> profs = profileDAO.getAllProfiles(loggedAcc);

            if (!profs.Any())
                profs.Add(profileDAO.createDefaultProfile(loggedAcc));

            this.currentProf = profs.First(); // First loaded profile will be default

            // Loads data to profile info and statistics
            LinqUtil.submitChanges();
            clean();

            updateInfo();
        }
        #endregion data loadings

        #region services
        /// <summary> Sorts cashflow data's given column (Ascendant/Descendant 
        /// direction)</summary>
        /// <param name="colSort"> Number of columnt to sort. </param>
        private void sortCashflowData(int colSort)
        {
            this.isAscending = !this.isAscending;
            this.colSort = colSort;

            updateLoadedCashes();
        }

        /// <summary> Remove all table rows and clear them. </summary>
        /// <param name="tblPan"> Tabel panel which will be cleared. </param>
        private void removeTableRows(TableLayoutPanel tblPan, int numOfRowsLeft, int numOfControlsLeft)
        {
            int rowCnt = tblPan.RowCount;
            for (int i = rowCnt - 1; i >= numOfRowsLeft; i--)
            {
                tblPan.RowCount--;
                tblPan.RowStyles.RemoveAt(i);
            }

            int controlsCnt = tblPan.Controls.Count;
            for (int i = controlsCnt - 1; i >= numOfControlsLeft; i--)
            {
                tblPan.Controls.RemoveAt(i);
            }
        }

        /// <summary> Adds certain number of rows into TableLayoutPanel. </summary>
        /// <param name="tblPan"> Panel to add rows into. </param>
        /// <param name="rowsNum"> Number of rows to add. </param>
        /// <param name="rowHeight"> Height row in pixels. </param>
        private void addRowsToTable(TableLayoutPanel tblPan, int rowsNum, int rowHeight)
        {
            tblPan.RowCount += rowsNum;

            // Adding rows
            for (int i = 0; i < rowsNum; i++)
            {
                tblPan.RowStyles.Add(new RowStyle(SizeType.Absolute, (float)rowHeight));
            }

            // Settings table's size
            tblPan.Size = new Size(tblPan.Size.Width, rowHeight * tblPan.RowCount);
        }

        /// <summary> Rolls given panel in or out. </summary>
        /// <param name="pan"> Pane to roll in/out. </param>
        private void popPanel(Panel pan)
        {
            if (pan.Height == 28)
            {
                pan.AutoSize = true;
            }
            else
            {
                pan.Height = 28;
                pan.AutoSize = false;
            }
        }

        /// <summary> Cleans and disposes DB data context. </summary>
        private void clean()
        {
            LinqUtil.cleanUp();
        }
        #endregion services

        #region GUI
        /// <summary> Unchecks Radion buttons in Quick cashflow insert. </summary>
        private void uncheckQuickInsertRadBtns()
        {
            radBtnQuickAddIncome.Checked = false;
            radBtnQuickAddOutcome.Checked = false;
        }

        /// <summary> Disables validation error pictures showing in Cashflow quick insert 
        /// when validation fails. </summary>
        private void disableQuickInsetErrLbls()
        {
            lblErrQuickAddCashtype.Visible = false;
            lblErrQuickAddCateg.Visible = false;
            lblErrQuickAddValue.Visible = false;
        }
        #endregion GUI

        #region graphs
        /// <summary> Count sum of cashflow income or outcome in specific 
        /// day in specific date period (from-to)</summary>
        /// <param name="fromDate"> Date when period starts. </param>
        /// <param name="toDate"> Date when period ends. </param>
        /// <param name="day"> Which day of period should be sumed up. </param>
        /// <param name="isIncome"> True - Sum up income
        ///                         False - Sum up outcome.</param>
        /// <returns> Sum of cashflow in specific day of time period. </returns>
        private int getFromDateToDateCashSum(DateTime fromDate, DateTime toDate, int day, bool isIncome)
        {
            // Cash total
            int sum = 0;

            // If should count income or outcome
            string cashtype = null;
            if (isIncome)
            {
                cashtype = "income";
            }
            else
            {
                cashtype = "outcome";
            }

            // Goes through all cashes and sum up those between FROM and TO dates
            foreach (Cashed cash in this.LoadedCashes)
            {
                if (cash.Paid.Date == fromDate.AddDays((double)day).Date &&
                    cash.Paid.Date >= fromDate.Date && cash.Paid.Date <= toDate.Date &&
                    cash.Cashtype.Equals(cashtype))
                {
                    sum += cash.Item.Item_value;
                }
            }

            return sum;
        }

        /// <summary> Draws legend (Dates of certain period, lines) to X Axis in graph. </summary>
        /// <param name="g"> GDI Graphics context. </param>
        /// <param name="win"> Window in which labels will be shown. </param>
        /// <param name="from"> Date when period starts. </param>
        /// <param name="to"> Date when period ends. </param>
        /// <param name="daysTotal"> Total number of days in period. </param>
        /// <param name="lblNums"> Number of mean-labels. </param>
        private void drawXAxis(Graphics g, Rectangle win, DateTime from, DateTime to, int daysTotal, int lblNums)
        {
            int indent = 75;

            int lblsStops = (win.Width - indent) / (lblNums);
            int dayStep = daysTotal / lblNums;

            for (int i = 0; i <= lblNums; i++)
            {
                if (from > to) break;

                // Create string to draw.
                String drawString = from.AddDays(i * dayStep).ToString("dd.MM.yyyy");

                // Create font and brush.
                Font drawFont = new Font("Verdana", 7);
                SolidBrush drawBrush = new SolidBrush(Color.Black);

                // Create point for upper-left corner of drawing.
                PointF drawPoint = new PointF(indent / 4 + i * lblsStops, win.Height - 15.0F);
                g.DrawString(drawString, drawFont, drawBrush, drawPoint);

                // Paint stops
                paintLine(g, win, 2 * indent / 3 + i * lblsStops, win.Height - 28, 9, false);
            }
        }

        /// <summary> Draws vertical or horizontal line on given location of 
        /// given size. </summary>
        /// <param name="g"> GDI Graphics context. </param>
        /// <param name="win"> Window in which lines will be shown. </param>
        /// <param name="x">Coordinate X.</param>
        /// <param name="y">Coordinate Y.</param>
        /// <param name="size">Size of line. </param>
        /// <param name="isVertical">True - Vertical line
        ///                          False - Horizontal line.</param>
        private void paintLine(Graphics g, Rectangle win, int x, int y, int size, bool isVertical)
        {
            Rectangle vertLine = new Rectangle(x, y, 0, size);

            Point top = new Point();
            Point bot = new Point();

            // Paints horizontal line
            if (!isVertical)
            {
                top.X = x;
                top.Y = y + size;

                bot.X = x;
                bot.Y = y;
            }
            // Paints vertical line
            else
            {
                top.X = x;
                top.Y = y;

                bot.X = x + size;
                bot.Y = y;
            }

            // Draw that line
            g.DrawLine(new Pen(Color.Black), top, bot);
        }

        /// <summary> Draws legend (cashflow values, lines) to Y Axis in graph. </summary>
        /// <param name="g"> GDI Graphics context. </param>
        /// <param name="win"> Window in which labels will be shown. </param>
        /// <param name="intervals"> Maximum, minimum etc. of graph. </param>
        /// <param name="fractions"> Number of mean-labels. </param>
        private void drawYAxis(Graphics g, Rectangle win, Rectangle intervals, int fractions)
        {
            const int INDENT = 75;

            int cashStep = (win.Height - INDENT - 5) / fractions;
            int cashVal = (intervals.Height) / fractions;

            // Paints stops and Values 
            for (int i = 0; i <= fractions; i++)
            {
                if (cashVal * i > intervals.Height) break;

                // Create string to draw.
                String drawString = "" + (i * cashVal);
                StringFormat stringFormat = new StringFormat();
                stringFormat.Alignment = StringAlignment.Far;

                // Create font and brush.
                Font drawFont = new Font("Tahoma", 7);
                SolidBrush drawBrush = new SolidBrush(Color.Black);

                // Create point for upper-left corner of drawing
                PointF drawPoint = new PointF(INDENT / 2, win.Height - i * cashStep - INDENT / 2 - 10.0F);
                g.DrawString(drawString, drawFont, drawBrush, drawPoint, stringFormat);

                // Paint stops
                paintLine(g, win, 38, win.Height - i * cashStep - (INDENT / 2 + 4), 9, true);
            }
        }

        /// <summary> Counts days in time period. </summary>
        /// <param name="from"> Date when period starts. </param>
        /// <param name="to"> Date when period ends. </param>
        /// <returns> Number of days in time period. </returns>
        private int getNumOfDays(DateTime from, DateTime to)
        {
            for (int i = 1; i < Int32.MaxValue; i++)
            {
                if (from.Date == to.Date) return i;
                from = from.AddDays(1.0);
            }
            return 0;
        }

        /// <summary> Paints cashflow income/outcome sum graph. </summary>
        /// <param name="g"> GDI Graphics context. </param>
        /// <param name="rect"> Window in which graph will be shown. </param>
        private void paintGraph(Graphics g, Rectangle rect)
        {
            int graphDays = getNumOfDays(this.graphFromDate, this.graphToDate);

            // Income & Outcome graph points
            Point[] pointsInc = new Point[graphDays];
            Point[] pointsOut = new Point[graphDays];

            int sumInc = 0;
            int sumOut = 0;

            // Maximal top graph limit
            int max = 1500;

            // Count sums
            for (int i = 0; i < graphDays; i++)
            {
                sumInc += getFromDateToDateCashSum(this.graphFromDate, this.graphToDate, i, true);
                pointsInc[i].X = i;
                pointsInc[i].Y = sumInc;

                sumOut += getFromDateToDateCashSum(this.graphFromDate, this.graphToDate, i, false);
                pointsOut[i].X = i;
                pointsOut[i].Y = sumOut;

                if (sumInc > max) max = sumInc;
                if (sumOut > max) max = sumOut;
            }

            // If no items
            if (sumInc == 0 && sumOut == 0)
            {
                // Create string to draw.
                String drawString = "- No cashflow items in this time period -";
                StringFormat stringFormat = new StringFormat();
                stringFormat.Alignment = StringAlignment.Far;

                // Create font and brush.
                Font drawFont = new Font("Microsoft Sans Serif", 12);
                SolidBrush drawBrush = new SolidBrush(Color.Black);

                // Create point for upper-left corner of drawing
                PointF drawPoint = new PointF(rect.Width / 2 + 150, rect.Height / 2);
                g.DrawString(drawString, drawFont, drawBrush, drawPoint, stringFormat);

                return;
            }

            // Graph limits rectangle
            Rectangle interval = new Rectangle(0, 0, graphDays, max);

            // Axis info paintings
            drawXAxis(g, rect, this.graphFromDate, this.graphToDate, graphDays, 6);
            drawYAxis(g, rect, interval, 5);

            // Drawing graphs
            g.SmoothingMode = System.Drawing.Drawing2D.SmoothingMode.HighQuality;
            setTransform(g, rect, interval);

            g.DrawLines(new Pen(Color.Green, 1.0F), pointsInc);
            g.DrawLines(new Pen(Color.Red, 1.0F), pointsOut);
        }

        /// <summary> Transformates points to suit Graph panel. </summary>
        /// <param name="g"> GDI Graphics context. </param>
        /// <param name="win"> Window in which transformations will be used. </param>
        /// <param name="limits"> Rectangle of minimal and maximal graph's values. </param>
        private void setTransform(Graphics g, Rectangle win, Rectangle limits)
        {
            // INDENT from TOP + BOTTOM graph border
            const int INDENT = 75;

            // Transformations
            g.ScaleTransform((float)( win.Width - INDENT/2) / limits.Width,
                             -(float)(win.Height - INDENT) / limits.Height);

            g.TranslateTransform(2.0F, -limits.Height - (INDENT / 2) *
                (limits.Height / (win.Height - INDENT)));
        }
        #endregion graphs

        #region messages
        /// <summary> Shows Error system message. </summary>
        /// <param name="msg">Message to show. </param>
        /// <param name="lbl"> Label to which a message will be shown. </param>
        /// <param name="picLbl"> Picture label for error icon. </param>
        private void showErrMSG(string msg, Label lbl, Label picLbl)
        {
            lbl.Text = msg;
            lbl.ForeColor = Color.Red;
            picLbl.Image = MoneyFlow.Properties.Resources.error;
        }

        /// <summary> Shows information system message. </summary>
        /// <param name="msg">Message to show. </param>
        /// <param name="lbl"> Label to which a message will be shown. </param>
        /// <param name="picLbl"> Picture label for info icon. </param>
        private void showInfoMSG(string msg, Label lbl, Label picLbl)
        {
            lbl.Text = msg;
            lbl.ForeColor = Color.FromArgb(105, 105, 105);
            picLbl.Image = MoneyFlow.Properties.Resources.info;
        }

        /// <summary> Shows OK system message. </summary>
        /// <param name="msg">Message to show. </param>
        /// <param name="lbl"> Label to which a message will be shown. </param>
        /// <param name="picLbl"> Picture label for OK icon. </param>
        private void showOkMSG(string msg, Label lbl, Label picLbl)
        {
            lbl.Text = msg;
            lbl.ForeColor = Color.FromArgb(48, 176, 26);
            picLbl.Image = MoneyFlow.Properties.Resources.ok;
        }

        /// <summary> Clears system message. </summary>
        /// <param name="lbl"> Label which will be erased. </param>
        /// <param name="picLbl"> Picture label for clear the icon. </param>
        private void showEmptyMSG(Label lbl, Label picLbl)
        {
            lbl.Text = "";
            picLbl.Image = null;
        }
        #endregion messages

        #region closing
        /// <summary> Exits the MoneyFlow application. </summary>
        internal void Exit()
        {
            this.Close();
        }
        #endregion closing

 // == LISTENERS METHODS ======================================================================

        #region loading
        /// <summary> Loads login window firstly when app start, then initialize
        /// data and basic info. </summary>
        private void MFlowWin_Load(object sender, EventArgs e)
        {
            initLogin();
        }
        #endregion loading

        #region buttons - main
        /// <summary> Load editation form for cashflow item in same row of Table. </summary>
        void editCashflowItem(object sender, EventArgs e)
        {
            Button btn = (Button)sender;
            int itemID = Int32.Parse(btn.Name);

            Cashed cashToEdit = null;

            try
            {
                // Loads cash by Item ID
                CashedDAO cashDAO = new CashedDAO();
                cashToEdit = cashDAO.getByItemID(itemID);
            }
            catch (Exception ex)
            {
                ex.ToString();
                showErrMSG("Could not edit item ID " + itemID + ", sorry.", lblMSG, lblMSGPic);
                return;
            }

            // Inits editation dialog
            initAddCashflowItem(cashToEdit);

            clean();
        }

        /// <summary> Asks user whether or not he wants to delete cashflow item 
        /// in same row of Table. </summary>
        void deleteCashflowItem(object sender, EventArgs e)
        {
            Button btn = (Button)sender;
            int itemID = Int32.Parse(btn.Name);

            if (MessageBox.Show("Are you sure you want to delete this record?",
                "Deleting cashflow record ", MessageBoxButtons.YesNo) == DialogResult.Yes)
            {
                try
                {
                    // Loads cash by Item ID
                    CashedDAO cashDAO = new CashedDAO();
                    cashDAO.deleteByItemID(itemID);

                    showOkMSG("Item ID " + itemID + " was successfully deleted!",
                        lblMSG, lblMSGPic);

                    // Submit delete
                    LinqUtil.submitChanges();

                    updateLoadedCashes();
                    updateLoadedStoppers();
                    updateInfo();
                }
                catch (Exception ex)
                {
                    ex.ToString();
                    showErrMSG("Could not delete item ID " + itemID + ", sorry.", lblMSG, lblMSGPic);
                }
                finally
                {
                    // Dispose DB
                    clean();
                }
            }
        }

        /// <summary> Rolls in/out Stoppers panel when clicked on header. </summary>
        private void lblStoppers_Click(object sender, EventArgs e)
        {
            popPanel(panStoppers);
        }

        /// <summary> Rolls in/out Graphs panel when clicked on header. </summary>
        private void lblGraphs_Click(object sender, EventArgs e)
        {
            popPanel(panGraphs);
        }

        /// <summary> Rolls in/out Recently added items panel when clicked on header. </summary>
        private void lblLastItems_Click(object sender, EventArgs e)
        {
            popPanel(panLastItems);
        }

        /// <summary> Rolls in/out Info panel when clicked on header. </summary>
        private void lblInfo_Click(object sender, EventArgs e)
        {
            popPanel(panInfo);
        }

        /// <summary> Rolls in/out Quick Insert panel when clicked on header. </summary>
        private void lblQuickCashAdd_Click(object sender, EventArgs e)
        {
            popPanel(panCashflowQuickAdd);
        }

        /// <summary> Rolls in/out Cashflow panel when clicked on header. </summary>
        private void lblCashflow_Click(object sender, EventArgs e)
        {
            popPanel(panCashflow);
        }

        /// <summary> Quickly validates and adds new cashflow item 
        /// (do not insert description and inserts default date). </summary>
        private void btnQuickAdd_Click(object sender, EventArgs e)
        {

            Item newItem = new Item();
            Cashed newCash = new Cashed();

            ItemDAO itemDAO = new ItemDAO();
            CashedDAO cashDAO = new CashedDAO();

            bool isValid = true;
            disableQuickInsetErrLbls();

            try
            {
                string errMSG = "Some error(s) occured during quick insert:";

                // Creating new cashflow item
                newItem.Item_id = itemDAO.getNewID();
                newCash.Item_id = newItem.Item_id;

                // Quick insert
                newItem.Description = "";
                newCash.Paid = DateTime.Now;

                // From user 
                try
                {
                    try
                    {
                        newItem.Item_value = Int32.Parse(txtBoxQuickAddValue.Text);
                    }
                    catch (FormatException ex)
                    {
                        ex.ToString();
                        throw new ApplicationException("You have entered invalid number format into " +
                            "item's value. Please, insert digits from 0 to 9.");
                    }
                }
                catch (ApplicationException ex)
                {
                    isValid = false;
                    lblErrQuickAddValue.Visible = true;
                    txtBoxQuickAddValue.Text = null;
                    errMSG += "\n" + ex.Message;
                }

                try
                {
                    if (radBtnQuickAddIncome.Checked)
                    {
                        newCash.Cashtype = "income";
                    }
                    else if (radBtnQuickAddOutcome.Checked)
                    {
                        newCash.Cashtype = "outcome";
                    }
                    else
                    {
                        newCash.Cashtype = "";
                    }
                }
                catch (ApplicationException ex)
                {
                    isValid = false;
                    lblErrQuickAddCashtype.Visible = true;
                    errMSG += "\n" + ex.Message;
                }

                try
                {
                    // If first item selected, no category was choosen = error
                    if (cmbQuickAddCateg.SelectedIndex == 0)
                        throw new ApplicationException("Please, you have to choose any category. ");

                    int categIndx = cmbQuickAddCateg.SelectedIndex - 1;

                    int sum = -1;
                    for (int i = 0; i < this.loadedCategs.Count; i++)
                    {
                        if ((sum + 1 + this.loadedCategs[i].subCategoriesCount()) >= categIndx)
                        {
                            // If category is selected
                            if (categIndx == (sum + 1))
                            {
                                newCash.Category_id = this.loadedCategs[i].Category_id;
                            }
                            // If subcategory of category is selected
                            else
                            {
                                newCash.Category_id = this.loadedCategs[i][(categIndx - sum - 2)].Category_id;
                            }
                            break;
                        }

                        sum += this.loadedCategs[i].subCategoriesCount() + 1;
                    }
                }
                catch (ApplicationException ex)
                {
                    isValid = false;
                    cmbQuickAddCateg.SelectedIndex = 0;
                    lblErrQuickAddCateg.Visible = true;
                    errMSG += "\n" + ex.Message;
                }

                // Joining item to cash
                newCash.Item = newItem;

                // Validity check
                if (!isValid)
                    throw new ApplicationException(errMSG);

                cashDAO.insertCash(newCash);

                // Submits changes and inserts cash into DB
                LinqUtil.submitChanges();

                // Informs user about success
                showOkMSG("New " + newCash.Cashtype + " " + newItem.Item_value + ",- " +
                          "has been successfully inserted with payment date at "
                          + newCash.Paid.ToString("dd.MM.yyyy") + "!", lblMSG, lblMSGPic);

                // Restart textfields
                txtBoxQuickAddValue.Text = null;

                // Update data
                updateLoadedCashes();
                updateLoadedStoppers();
                updateInfo();
            }
            catch (ApplicationException ex)
            {
                showErrMSG(ex.Message, lblMSG, lblMSGPic);
            }
            finally
            {
                clean();
            }
        }
        #endregion buttons - main

        #region buttons - infopanel
        /// <summary> Unlogs user out and shows login window. </summary>
        private void btnInfoLogout_Click(object sender, EventArgs e)
        {
            initLogin();
        }

        /// <summary> Initialize Profile manager dialog where user can manage his profiles. </summary>
        private void btnInfoChangeProfile_Click(object sender, EventArgs e)
        {
            initProfileDialog();
        }

        /// <summary> Initialize Categories and subcategories manager dialog where user 
        /// can manage his categories hierarchy and settings. </summary>
        private void btnInfoCats_Click(object sender, EventArgs e)
        {
            initCategoryDialog();
        }
        #endregion buttons - infopanel

        #region buttons - cashflow
        /// <summary> Loads previous page in Table when clicked </summary>
        private void btnCashflowPrev_Click(object sender, EventArgs e)
        {
            btnCashflowNext.Enabled = true;
            firstRow -= PAGING_INTERVAL;

            loadCashflowData();
        }

        /// <summary> Loads next page in Table when clicked </summary>
        private void btnCashflowNext_Click(object sender, EventArgs e)
        {
            btnCashflowPrev.Enabled = true;
            firstRow += PAGING_INTERVAL;

            loadCashflowData();
        }

        /// <summary> Initialize new cashflow item creation dialog. </summary>
        private void btnCashflowCreateNew_Click(object sender, EventArgs e)
        {
            initAddCashflowItem(null);
        }
        #endregion buttons - cashflow

        #region menu
        /// <summary> Quits MoneyFlow application. </summary>
        private void menuSubItmQuit_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        /// <summary> Initialize about dialog. </summary>
        private void menuSubItmAbout_Click(object sender, EventArgs e)
        {
            initAboutDialog();
        }

        /// <summary> Initialize new cashflow item creation dialog. </summary>
        private void menuSubItmCashflow_Click(object sender, EventArgs e)
        {
            initAddCashflowItem(null);
        }

        /// <summary> Unlogs user out and shows login window. </summary>
        private void menuSubItmLogout_Click(object sender, EventArgs e)
        {
            initLogin();
        }

        /// <summary> Initialize Profile manager dialog where user can manage his profiles. </summary>
        private void menuSubItmProfileChoose_Click(object sender, EventArgs e)
        {
            initProfileDialog();
        }

        /// <summary> Initialize Categories and subcategories manager dialog where user 
        /// can manage his categories hierarchy and settings. </summary>
        private void menuSubItmCategories_Click(object sender, EventArgs e)
        {
            initCategoryDialog();
        }
        #endregion menu

        #region sort labels
        /// <summary> Sorts cashflow data according to Date when clicked 
        /// (Ascendant/Descendant direction)</summary>
        private void lblCashflowDate_Click(object sender, EventArgs e)
        {
            sortCashflowData(1);
        }

        /// <summary> Sorts cashflow data according to cashtype (income/outcome) 
        /// and then according to date when clicked (Ascendant/Descendant direction)</summary>
        private void lblCashflowType_Click(object sender, EventArgs e)
        {
            sortCashflowData(2);
        }

        /// <summary> Sorts cashflow data according to item's value when clicked 
        /// (Ascendant/Descendant direction)</summary>
        private void lblCashflowValue_Click(object sender, EventArgs e)
        {
            sortCashflowData(3);
        }

        /// <summary> Sorts cashflow data according to details when clicked 
        /// (Ascendant/Descendant direction)</summary>
        private void lblCashflowDetails_Click(object sender, EventArgs e)
        {
            sortCashflowData(4);
        }
        #endregion sort labels

        #region graphs
        /// <summary> Paints graph into graph panel using double buffering
        /// technology. </summary>
        private void panGraphIncome_Paint(object sender, PaintEventArgs e)
        {
            // Windows rectangle
            Rectangle rect = panGraphIncome.ClientRectangle;

            // Paintings in doublebuffer mode
            using (Graphics g = panGraphIncome.CreateGraphics())
            {
                using (BufferedGraphics bufferOfCanvas = doubleBuffContxt.Allocate(g, rect))
                {
                    if (bufferOfCanvas != null)
                    {
                        using (Graphics gbuf = bufferOfCanvas.Graphics)
                        {
                            gbuf.FillRectangle(new SolidBrush(Color.LightGray), rect);

                            // Paints graph itself
                            paintGraph(gbuf, rect);
                            bufferOfCanvas.Render(g);
                        }
                    }
                }
            }

            // LEAKS
            updateLoadedCategories();
            this.leakStack.Push(this.LoadedCategs);
            LinqUtil.cleanUp();
        }
        #endregion graphs

    }
}
