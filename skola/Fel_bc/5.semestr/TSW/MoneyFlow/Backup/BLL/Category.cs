using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace BLL
{
    /// <summary> Class providing basic info about Category 
    /// and its subcategories. </summary>
    public partial class Category: ICloneable
    {

 // == INSTANCE VARIABLES =====================================================================

        #region variables
        /// <summary> Subcategories belonging to this category. </summary>
        private List<Category> subCategs = new List<Category>();

        /// <summary> Returns this category's subcategory on given index. </summary>
        /// <param name="index"></param>
        /// <returns>Subcategory instance on given index. If zero-based index is >= length
        /// returns null. </returns>
        public Category this[int index]
        {
            get
            {
                if (index >= subCategs.Count()) return null;

                return this.subCategs[index];
            }
        }
        #endregion variables

 // == INSTANCE PUBLIC METHODS ================================================================

        #region subcategories
        /// <summary> Number of Subcategories in this category. </summary>
        /// <returns> Number of Subcategories. </returns>
        public int subCategoriesCount()
        {
            return this.subCategs.Count();
        }

        /// <summary> From the given list of Categories, adds only categories belonging under this
        /// Category (= this category is their parent). </summary>
        public void addSubCategories(List<Category> allAvailCategs)
        {
            foreach (Category subCat in allAvailCategs)
            {

                bool isIn = false;
                if (subCat.Sup_category_id != null && subCat.Sup_category_id == this.Category_id)
                {
                    //// Check
                    //for (int i = 0; i < this.subCategs.Count; i++)
                    //{
                    //    if (subCategs[i].Category_id == subCat.Category_id) isIn = true;
                    //}
                    //if (!isIn)
                    //{
                        this.subCategs.Add(subCat);
                    //}

                }
            }
        }
        /// <summary> Adds subcategory to this category. </summary>
        /// <param name="newCateg"></param>
        public void addSubCategory(Category newCateg)
        {
            this.subCategs.Add(newCateg);
        }

        /// <summary> Removes subcategory on given index. </summary>
        /// <param name="index"> Index of subcategory in this category. </param>
        public void removeSubCategory(int index)
        {
            this.subCategs.RemoveAt(index);
        }
        #endregion subcategories

        #region statistics
        /// <summary> Gets total cashflow of income or outcome in current category (depending on given cash type). </summary>
        /// <param name="cashType">income - retrieves total income.
        ///                        outcome - retrieves total outcome.</param>
        /// <returns>Total cashflow of income/outcome in current category. </returns>
        public int getTotalCashflow(string cashType)
        {
            switch (cashType)
            {
                case "income": return getIncomeTotal();
                case "outcome": return getOutcomeTotal(); ;
                default: return 0;
            }
        }

        /// <summary> Returns sum of all income items in this category. </summary>
        /// <returns> Sum of all income items in this category. </returns>
        private int getIncomeTotal()
        {
            int income = (from inc in this.Cashed
                          where inc.Cashtype == "income"
                          select inc.Item).Sum(o => o.Item_value);

            return income;
        }

        /// <summary> Returns sum of all outcome items in this category. </summary>
        /// <returns> Sum of all outcome items in this category. </returns>
        private int getOutcomeTotal()
        {
            int outcome = (from inc in this.Cashed
                           where inc.Cashtype == "outcome"
                           select inc.Item).Sum(o => o.Item_value);

            return outcome;
        }

        /// <summary> Gets number of cashflow items belonging under this category. </summary>
        /// <returns> Number of cashflow items. </returns>
        private int getCashflowCount()
        {
            return (from cflow in this.Cashed
                    select cflow).Count();
        }
        #endregion statistics

 // == INSTANCE PRIVATE METHODS ===============================================================

        #region formatting
        private string makeWhitespaces(int numOfSpaces)
        {
            string spaces = "";
            for (int i = 0; i < numOfSpaces; i++)
            {
                spaces += " ";
            }
            return spaces;
        }
        #endregion formatting

 // == INSTANCE VALIDATION METHODS ============================================================

        #region validation
        /// <summary> Category description validation before inserting category into DB. </summary>
        /// <param name="value"> Value to validate. </param>
        partial void OnDescriptionChanging(string value)
        {
        }

        /// <summary> Category Name validation before inserting category into DB. </summary>
        /// <param name="value"> Value to validate. </param>
        partial void OnNameChanging(string value)
        {
            if (value.Length == 0)
                throw new ApplicationException("Please, fill your category name!");

            if (value.Length > 20)
                throw new ApplicationException("Your category name cannot be longer " +
                    "than 20 characters.");
        }

        /// <summary> Category against subcategory validation before inserting category 
        /// into DB. </summary>
        /// <param name="value"> Value to validate. </param>
        partial void OnCategory_idChanging(int value)
        {
            if (value == this.Category_id)
                throw new ApplicationException("Category cannot be its subcategory in the same time!");
        }
        #endregion validation

 // == INSTANCE OVERRIDDEN METHODS ============================================================

        #region overrides
        /// <summary> Transfers Category instance to text in format: [category's_name]
        /// ([category's cashlfow items number])</summary>
        /// <returns> Text in format: [category's_name]([category's cashlfow items number])</returns>
        public override string ToString()
        {
            const int INDENT = 30;
            return "" + this.Name + makeWhitespaces(INDENT - this.Name.Length) + "(" + getCashflowCount() + " items)";
        }
        #endregion overrides

 // == IMPLEMENTED INTERFACES =================================================================

        #region ICloneable Members
        /// <summary> Makes deep instance copy of Category. </summary>
        /// <returns> Copied, new instance. </returns>
        public object Clone()
        {
            Category clonedCateg = new Category();

            clonedCateg.Description = this.Description;
            clonedCateg.Name = this.Name;
            clonedCateg.Profile_id = this.Profile_id;
            clonedCateg.Sup_category_id = this.Sup_category_id;
            clonedCateg.Sup_category_ = this.Sup_category_;
            clonedCateg.Category_id = this.Category_id;

            // Subcategories deep copy
            List<Category> copiedSubcategs = new List<Category>();
            foreach (Category subcat in this.subCategs)
            {
                Category subcategory = (Category)subcat.Clone();
                subcategory.Sup_category_ = clonedCateg;
                copiedSubcategs.Add(subcategory);
            }
            clonedCateg.subCategs = copiedSubcategs;

            return clonedCateg;
        }
        #endregion

    }
}
