using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using BLL;


namespace DAL
{
    /// <summary> DataAccessObject class for Cashed Object providing
    /// info about cashflow. </summary>
    public class CashedDAO
    {

 // == INSTANCE PUBLIC METHODS ================================================================

        #region data access
        /// <summary> Deletes all cashes from given cashes list.</summary>
        /// <param name="cashes"> List of cashes for deleteing. </param>
        internal void deleteAllCashes(List<Cashed> cashes)
        {
            foreach (Cashed cash in cashes)
            {
                deleteByItemID(cash.Item_id);
            }
        }

        /// <summary> Puts given cash instance into pending state waiting 
        /// for insert into DB.</summary>
        /// <param name="newCash"> Cash which will be inserted into DB. </param>
        public void insertCash(Cashed newCash)
        {
            if (isCashInDB(newCash))
                throw new ApplicationException("Cannot create cashflow record which already exists!");

            LinqUtil.DB.Cashed.InsertOnSubmit(newCash);
        }

        /// <summary> Loads all cashes available for given profile. </summary>
        /// <param name="currentProf"> Current user profile. </param>
        /// <returns> List of all available cashes. </returns>
        public List<Cashed> getAllCashes(Profile currentProf)
        {
            return (from cashes in LinqUtil.DB.Cashed
                    join cats in LinqUtil.DB.Category
                    on cashes.Category_id equals cats.Category_id
                    where cats.Profile_id == currentProf.Profile_id
                    select cashes).ToList();
        }

        /// <summary> Loads cash using Item's ID. </summary>
        /// <param name="itemID"> ID of an item. </param>
        /// <returns> Item's cash instance. </returns>
        public Cashed getByItemID(int itemID)
        {
            return (from cashes in LinqUtil.DB.Cashed
                    where cashes.Item_id == itemID
                    select cashes).First();
        }

        /// <summary> Deletes cash using Item's ID.  </summary>
        /// <param name="itemID"> ID of an item. </param>
        public void deleteByItemID(int itemID)
        {
            Cashed cash = (from cashes in LinqUtil.DB.Cashed
                           where cashes.Item_id == itemID
                           select cashes).First();

            Item item = (from items in LinqUtil.DB.Item
                         where items.Item_id == itemID
                         select items).First();

            LinqUtil.DB.Cashed.DeleteOnSubmit(cash);
            LinqUtil.DB.Item.DeleteOnSubmit(item);
        }

        /// <summary> Updates given cash if it is not in DB currently.
        /// Inserts update request into pending state. </summary>
        /// <param name="newCash"> Changed cash for updating. </param>
        public void updateCash(Cashed newCash)
        {
            if (isCashInDB(newCash))
                throw new ApplicationException("Cannot create cashflow item which already exists!");

            deleteByItemID(newCash.Item_id);
            insertCash(newCash);
        }
        #endregion data access

        #region sorting
        /// <summary> Gets sorted cashes data. Sorts according to given column and 
        /// type of sorting (Ascending/Descending). </summary>
        /// <param name="currentProf"> Current profile. </param>
        /// <param name="colSort">
        /// 1 - Sort dates
        /// 2 - Sort cashtypes
        /// 3 - Sort values
        /// 4 - Sort description
        /// </param>
        /// <param name="isAscending">True - Data will be sorted ascendingly
        ///                           False - Data will be sorted descendingly.</param>
        /// <returns> List of sorted cahes. </returns>
        public List<Cashed> getAllCashes(Profile currentProf, int colSort, bool isAscending)
        {
            if (colSort < 1 || colSort > 4) throw new ApplicationException("Bad column index inserted");

            // Ascending or descending sort direction
            GenericSorter.SortDirection direction;
            if (isAscending)
            {
                direction = GenericSorter.SortDirection.Ascending;
            }
            else
            {
                direction = GenericSorter.SortDirection.Descending;
            }

            IQueryable<Cashed> sort = null;

            // Sortings
            if (colSort == 1)
            {
                sort = (from cashes in LinqUtil.DB.Cashed
                        join cats in LinqUtil.DB.Category
                        on cashes.Category_id equals cats.Category_id
                        where cats.Profile_id == currentProf.Profile_id
                        select cashes).
                        OrderBy("Paid", direction).OfType<Cashed>();
            }
            else if (colSort == 2)
            {
                sort = ((from cashes in LinqUtil.DB.Cashed
                         join cats in LinqUtil.DB.Category
                         on cashes.Category_id equals cats.Category_id
                         where cats.Profile_id == currentProf.Profile_id
                         select cashes).
                        OrderBy("Paid", GenericSorter.SortDirection.Descending).Cast<Cashed>().
                        OrderBy("Cashtype", direction).Cast<Cashed>());
            }
            else if (colSort == 3 || colSort == 4)
            {
                string colName = null;

                if (colSort == 3) colName = "Item_value";
                if (colSort == 4) colName = "Description";

                sort = (from items in
                            ((from items in LinqUtil.DB.Item
                              select items).OrderBy(colName, direction).Cast<Item>())
                        join cashes in
                            (from cashes in LinqUtil.DB.Cashed
                             join cats in LinqUtil.DB.Category
                             on cashes.Category_id equals cats.Category_id
                             where cats.Profile_id == currentProf.Profile_id
                             select cashes)
                       on items.Item_id equals cashes.Item_id
                        select cashes);
            }

            return sort.ToList();
        }
        #endregion sorting

 // == INSTANCE PRIVATE METHODS ===============================================================

        #region consistency check
        /// <summary> Checks whether or not is given cash in DB or not. </summary>
        /// <param name="newCash"> Cash which will be checked against DB. </param>
        /// <returns>True - Given cash is in DB. 
        ///          False - Given cash is NOT in DB yet. </returns>
        private bool isCashInDB(Cashed newCash)
        {
            bool isInDB = LinqUtil.DB.Cashed.Where(p =>
                            p.Category_id == newCash.Category_id &&
                            p.Item_id == newCash.Item_id &&
                            p.Item.Item_value == newCash.Item.Item_value &&
                            p.Item.Description == newCash.Item.Description &&
                            p.Cashtype == newCash.Cashtype &&
                            p.Paid == newCash.Paid).Any();

            return isInDB;
        }
        #endregion consistency check

    }
}
