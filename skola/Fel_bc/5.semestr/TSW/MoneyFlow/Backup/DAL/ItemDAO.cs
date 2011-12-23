using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using BLL;

namespace DAL
{
    /// <summary> DataAccessObject class for Item Object. </summary>
    public class ItemDAO
    {

 // == INSTANCE PUBLIC METHODS ================================================================

        #region generators
        /// <summary> Generates new ID for new Item. </summary>
        /// <returns> Incremented biggest Item ID value. </returns>
        public int getNewID()
        {
            int max = (from items in LinqUtil.DB.Item
                       select items.Item_id).Max();

            return max + 1;
        }
        #endregion generators

    }
}
