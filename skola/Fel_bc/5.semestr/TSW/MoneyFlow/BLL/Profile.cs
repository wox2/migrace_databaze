using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace BLL
{
    /// <summary> Class providing basic info about Profile. </summary>
    public partial class Profile
    {

 // == OPERATORS ==============================================================================

        #region operators
        /// <summary> Compares two Profiles by their Profile ID. </summary>
        /// <param name="first"> Profile to te left from == operator. </param>
        /// <param name="second"> Profile to the right from == operator. </param>
        /// <returns>True - Profile IDs are identical. 
        ///          False - Profile IDs are different. </returns>
        public static bool operator ==(Profile first, Profile second)
        {
            if (((object)first == null) || ((object)second == null))
                return false;

            return first.Profile_id == second.Profile_id;
        }

        /// <summary> Compares two Profiles by their Profile ID. </summary>
        /// <param name="first"> Profile to te left from == operator. </param>
        /// <param name="second"> Profile to the right from == operator. </param>
        /// <returns>True - Profile IDs are different. 
        ///          False - Profile IDs are identical.</returns>
        public static bool operator !=(Profile first, Profile second)
        {
            if (((object)first == null) || ((object)second == null))
                return true;

            return first.Profile_id != second.Profile_id;
        }
        #endregion operators

 // == INSTANCE VALIDATION METHODS ============================================================

        #region validation
        /// <summary> Profile name validation before inserting profile into DB. </summary>
        /// <param name="value"> Value to validate. </param>
        partial void OnNameChanging(string value)
        {
            if (value.Length == 0)
                throw new ApplicationException("Please, fill your profile name!");

            if (value.Length > 20)
                throw new ApplicationException("Your profile name cannot be longer " +
                    "than 20 characters.");
        }

        /// <summary> Type of profile validation before inserting profile into DB. </summary>
        /// <param name="value"> Value to validate. </param>
        partial void OnTypeChanging(string value)
        {
            if (!value.Equals("personal") && !value.Equals("business"))
                throw new ApplicationException("Type of your profile can be either personal or business!");
        }

        /// <summary> Profile's description validation before inserting profile into DB. </summary>
        /// <param name="value"> Value to validate. </param>
        partial void OnDescriptionChanging(string value)
        {
            if (value.Length > 100)
                throw new ApplicationException("Profile description cannot be longer " +
                    "than 100 characters.");
        }
        #endregion validation

 // == INSTANCE OVERRIDDEN METHODS ============================================================
        
        #region overrides
        /// <summary> Compares two Profiles by their IDs. </summary>
        /// <param name="obj"></param>
        /// <returns></returns>
        public override bool Equals(object obj)
        {
            if (obj == null) return false;

            Profile prof = (Profile)obj;
            return this.Profile_id == prof.Profile_id;
        }

        /// <summary> Gets hashcode of this instance. </summary>
        /// <returns> this instance's hashcode. </returns>
        public override int GetHashCode()
        {
            return base.GetHashCode();
        }

        /// <summary> Transfers Profile instance to text in format: [profile's_name]([profile's_type])</summary>
        /// <returns>Text in format: [profile's_name]([profile's_type])</returns>
        public override string ToString()
        {
            return "" + this.Name + " (" + this.Type + ")";
        }
        #endregion overrides

    }
}
