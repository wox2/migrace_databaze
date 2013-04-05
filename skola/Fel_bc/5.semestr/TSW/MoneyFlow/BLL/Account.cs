using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace BLL
{
    /// <summary> Class providing basic info about user and his account. </summary>
    public partial class Account
    {

 // == INSTANCE PUBLIC METHODS ================================================================

        /// <summary>REPAIR: Checks if current password corresponds with given string. </summary>
        /// <param name="passToCompare"></param>
        /// <returns></returns>
        public bool checkPassword(String passToCompare)
        {
            if (passToCompare != this.Pass)
                throw new ApplicationException("Entered password is incorrent! Please, try again.");

            return true;
        }

 // == INSTANCE VALIDATION METHODS ============================================================

        #region validation
        /// <summary> Nickname validation before inserting account into DB. </summary>
        /// <param name="value"> Value to validate. </param>
        partial void OnNickChanging(string value)
        {
            if (value.Length == 0)
                throw new ApplicationException("Please, fill your account nick!");

            if (value.Length > 20)
                throw new ApplicationException("Your account nick cannot be longer " +
                    "than 20 characters.");
        }

        /// <summary> Fullname validation before inserting account into DB </summary>
        /// <param name="value"> Value to validate. </param>
        partial void OnNameChanging(string value)
        {
            if (value.Length > 20)
                throw new ApplicationException("Your full name cannot be longer " +
                    "than 20 characters.");
        }

        /// <summary> Password validation before inserting account into DB </summary>
        /// <param name="value"> Value to validate. </param>
        partial void OnPassChanging(string value)
        {
            if (value.Length == 0)
                throw new ApplicationException("Please, fill your password!");

            if (value.Length > 0 && value.Length < 6)
                throw new ApplicationException("Your password has to be at least 6 characters long.");

            if (value.Length > 20)
                throw new ApplicationException("Your password cannot be longer " +
                    "than 20 characters.");
        }
        #endregion validation

 // == INSTANCE OVERRIDEN METHODS =============================================================
        
        #region
        /// <summary> Transfers account's login name and full name into text 
        /// in format: [account's_login]([account's_name])</summary>
        /// <returns> Text in format: [account's_login]([account's_name])</returns>
        public override string ToString()
        {
            return this.Nick + " (" + this.Name + ") ";
        }
        #endregion

    }
}
