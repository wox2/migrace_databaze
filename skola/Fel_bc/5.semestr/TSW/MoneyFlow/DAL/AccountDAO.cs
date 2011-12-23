using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using BLL;
using System.Data.Linq;

namespace DAL
{
    /// <summary> DataAccessObject class for Account Object. </summary>
    public class AccountDAO
    {

 // == INSTANCE PUBLIC METHODS ================================================================

        #region data access
        /// <summary> Retrieves all Account records from DB. </summary>
        /// <returns> All Account records in Table. </returns>
        public Table<Account> getAllAccounts()
        {
            return LinqUtil.DB.GetTable<Account>();
        }

        /// <summary>Inserts Account into pending state before submitting changes. </summary>
        /// <param name="acc"> Account instance. </param>
        public void insertAccount(Account acc)
        {
            LinqUtil.DB.Account.InsertOnSubmit(acc);
        }

        /// <summary> Gets new ID for new Account instance. </summary>
        /// <returns> New ID. </returns>
        public int getNewID()
        {
            int max = (from acc in LinqUtil.DB.Account
                       select acc.Account_id).Max();
            return max + 1;
        }
        #endregion data access

    }
}
