using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace BLL
{
    /// <summary> Class providing info about concrete 
    /// cashflow item. </summary>
    public partial class Item: ICloneable
    {

 // == INSTANCE VALIDATION METHODS ============================================================

        #region validation
        /// <summary> Cashflow item value validation before inserting item into DB. </summary>
        /// <param name="value"> Value to validate. </param>
        partial void OnItem_valueChanging(int value)
        {
            if (value.ToString().Length == 0)
                throw new ApplicationException("Please, you have to fill value of cashflow item!");

            if (value.ToString().Length > 7)
                throw new ApplicationException("You cannot insert cashflow value bigger " +
                    "than 9999999 !");

            if (value < 1)
                throw new ApplicationException("You cannot insert negative or zero cashflow value!");
        }

        /// <summary> Cashflow item's description validation before inserting item into DB. </summary>
        /// <param name="value"> Value to validate. </param>
        partial void OnDescriptionChanging(string value)
        {
            if (value.Length > 100)
                throw new ApplicationException("Your cashflow item's description cannot be longer " +
                    "than 100 characters.");
        }
        #endregion validation

 // == IMPLEMENTED INTERFACES =================================================================

        #region ICloneable Members
        /// <summary> Makes deep instance copy of Item. </summary>
        /// <returns> Copied, new instance. </returns>
        public object Clone()
        {
            Item clonedItem = new Item();
            clonedItem.Item_id = this.Item_id;
            clonedItem.Item_value = this.Item_value;
            clonedItem.Description = this.Description;

            return clonedItem;
        }
        #endregion

    }
}
