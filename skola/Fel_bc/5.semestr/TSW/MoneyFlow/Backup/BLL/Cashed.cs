using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace BLL
{
    /// <summary> Class providing basic info about Cashed 
    /// Cashflow items. </summary>
    public partial class Cashed: ICloneable
    {

 // == INSTANCE VALIDATION METHODS ============================================================

        #region validation
        /// <summary> Type of cash validation before inserting cash into DB. </summary>
        /// <param name="value"> Value to validate. </param>
        partial void OnCashtypeChanging(string value)
        {
            if (!value.Equals("income") && !value.Equals("outcome"))
                throw new ApplicationException("Please, choose type of cash (income or outcome)!");
        }
        #endregion validation     

 // == IMPLEMENTED INTERFACES =================================================================

        #region ICloneable Members
        /// <summary> Makes deep instance copy of Cashed. </summary>
        /// <returns> Copied, new instance. </returns>
        public object Clone()
        {
            Cashed clonedCash = new Cashed();

            clonedCash.Cashtype = this.Cashtype;
            clonedCash.Category_id = this.Category_id;
            clonedCash.Paid = this.Paid;
            clonedCash.Item_id = this.Item_id;

            // Objects deep copy
            clonedCash.Item = (Item)this.Item.Clone();
            clonedCash.Category = (Category)this.Category.Clone();
            return clonedCash;
        }
        #endregion
    
    }
}
