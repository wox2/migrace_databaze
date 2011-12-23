using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using BLL;
using System.Data.Linq;

namespace DAL
{
    /// <summary> DataAccessObject class for Profile Object. </summary>
    public class ProfileDAO
    {

 // == INSTANCE PUBLIC METHODS ================================================================

        #region data access
        /// <summary> Loads all profiles from DB belonging to given account. </summary>
        /// <param name="acc"> Cashflow Account. </param>
        /// <returns> List of profiles belonging to given account. </returns>
        public List<Profile> getAllProfiles(Account acc){
            var profs = from prof in LinqUtil.DB.Profile
                        where prof.Account_id == acc.Account_id
                        select prof;

            return profs.ToList<Profile>();
        }

        /// <summary> Inserts given profile (if it already doesn't exist) and
        /// sets operation into pending state. </summary>
        /// <param name="profile"> Profile which should be inserted. </param>
        public void insertProfile(Profile profile)
        {
            if (isProfileInDB(profile))
                throw new ApplicationException("Cannot create profile which already exists!");

            LinqUtil.DB.Profile.InsertOnSubmit(profile);
        }

        /// <summary> Deletes given profile and sets operation into pending state. </summary>
        /// <param name="profile">Profile which should be deleted. </param>
        public void deleteProfile(Profile profile)
        {
            Profile toDelete = (from profs in LinqUtil.DB.Profile
                                where profs.Profile_id == profile.Profile_id
                                select profs).First();

            CategoryDAO categoryDAO = new CategoryDAO();

            // Delete All categories first
            categoryDAO.deleteAllCategories(toDelete.Category.ToList());

            LinqUtil.DB.Profile.DeleteOnSubmit(toDelete);
        }

        /// <summary> Updates given profile (if it already doesn't exist) and sets 
        /// changes into pending state. </summary>
        /// <param name="changedProf">Profile which should be updated. </param>
        public void updateProfile(Profile changedProf)
        {
            Profile toUpdate = (from prof in LinqUtil.DB.Profile
                                where prof.Profile_id == changedProf.Profile_id
                                select prof).First();

            if (isProfileInDB(changedProf))
                throw new ApplicationException("This profile is already available!");

            // Updating record
            toUpdate.Name = changedProf.Name;
            toUpdate.Type = changedProf.Type;
            toUpdate.Description = changedProf.Description;
        }

        /// <summary> Creates profile with default settings. </summary>
        /// <param name="acc"> Cashflow account to which default profile should be created. </param>
        /// <returns> Default Profile. </returns>
        public Profile createDefaultProfile(Account acc)
        {
            Profile prof = new Profile();

            prof.Profile_id = getNewID();
            prof.Name = "Default";
            prof.Description = "Default profile";
            prof.Type = "personal";
            prof.Account_id = acc.Account_id;

            // Insert into DB (pending state)
            LinqUtil.DB.Profile.InsertOnSubmit(prof);

            return prof;
        }
        #endregion data access

        #region generators
        /// <summary> Generates new ID for new Profile</summary>
        /// <returns> Incremented biggest Profile ID value. </returns>
        public int getNewID()
        {
            int max = (from profs in LinqUtil.DB.Profile
                       select profs.Profile_id).Max();

            return max + 1;
        }
        #endregion generators

        #region statistics
        /// <summary> Retrieves total cashflow from given Profile. Sums up 
        /// all outcome items. </summary>
        /// <param name="prof"> Cashflow profile. </param>
        /// <returns> Sum of all outcome items. </returns>
        public int getSumOutcomeItems(Profile prof)
        {
            return getSumCashflowItems(prof, "outcome");
        }

        /// <summary> Retrieves total cashflow from given Profile. Sums up 
        /// all income items. </summary>
        /// <param name="prof"> Cashflow profile. </param>
        /// <returns> Sum of all income items. </returns>
        public int getSumIncomeItems(Profile prof)
        {
            return getSumCashflowItems(prof, "income");
        }

        /// <summary> Gets date of first inserted item (Date from which 
        /// profile is actively used). </summary>
        /// <param name="prof"> Cashflow profile. </param>
        /// <returns></returns>
        public DateTime getFirstCashed(Profile prof)
        {
            DateTime day;
            try
            {
                day = (from cats in LinqUtil.DB.Category
                       join cashed in LinqUtil.DB.Cashed
                       on cats.Category_id equals cashed.Category_id
                       where cats.Profile_id == prof.Profile_id
                       select cashed.Paid).Min();
            }
            catch (Exception ex)
            {
                ex.ToString();
                throw new ApplicationException("No added item yet");
            }

            return day;
        }

        /// <summary> Retrieves number of cashflow items from given Profile. </summary>
        /// <param name="prof"> Cashflow profile. </param>
        /// <returns> Number of all outcome items. </returns>        
        public int getNumOutcomeItems(Profile prof)
        {
            return getNumCashflowItems(prof, "outcome");
        }

        /// <summary> Retrieves number of cashflow items from given Profile. </summary>
        /// <param name="prof"> Cashflow profile. </param>
        /// <returns> Number of all income items. </returns>
        public int getNumIncomeItems(Profile prof)
        {
            return getNumCashflowItems(prof, "income");
        }

        /// <summary> Retrieves number of categories from given profile. </summary>
        /// <param name="prof"> Cashflow profile.</param>
        /// <returns> Number of categories from given profile. </returns>
        public int getNumCategories(Profile prof)
        {
            int number = (from cats in LinqUtil.DB.Category
                          where cats.Profile_id == prof.Profile_id && cats.Sup_category_id == null
                          select cats).Count();

            return number;
        }

        /// <summary> Retrieves number of subcategories from given profile. </summary>
        /// <param name="prof"> Cashflow profile.</param>
        /// <returns> Number of subcategories from given profile. </returns>
        public int getNumSubcategories(Profile prof)
        {
            int number = (from cats in LinqUtil.DB.Category
                          where cats.Profile_id == prof.Profile_id && cats.Sup_category_id != null
                          select cats).Count();

            return number;
        }

        /// <summary> Retrieves number all Stoppers from given profile. </summary>
        /// <param name="prof"> Cashflow profile.</param>
        /// <returns> Number all Stoppers from given profile. </returns>
        public int getNumStoppers(Profile prof)
        {
            int number = (from cats in LinqUtil.DB.Category
                          join mon in LinqUtil.DB.Monitoring
                          on cats.Category_id equals mon.Category_id
                          where cats.Profile_id == prof.Profile_id
                          select mon).Count(o => true);

            return number;
        }

        /// <summary> Retrieves number of active Stoppers. </summary>
        /// <param name="prof"> Cashflow profile.</param>
        /// <returns> Number of active Stoppers. </returns>
        public int getNumActiveStoppers(Profile prof)
        {
            int number = (from cats in LinqUtil.DB.Category
                          join mon in LinqUtil.DB.Monitoring
                          on cats.Category_id equals mon.Category_id
                          where cats.Profile_id == prof.Profile_id &&
                                mon.From_date < DateTime.Now &&
                                mon.To_date > DateTime.Now
                          select mon).Count(o => true);

            return number;
        }

        /// <summary> Retrieves number of Stoppers in critical danger level. </summary>
        /// <param name="prof"> Cashflow profile.</param>
        /// <returns> Number of critical-danger level Stoppers. </returns>
        public int getNumCriticalStoppers(Profile prof)
        {
            int number = 0;

            var stoppers = from cats in LinqUtil.DB.Category
                           join mon in LinqUtil.DB.Monitoring
                           on cats.Category_id equals mon.Category_id
                           where cats.Profile_id == prof.Profile_id &&
                                 mon.From_date < DateTime.Now &&
                                 mon.To_date > DateTime.Now
                           select mon;

            foreach (Monitoring m in stoppers)
            {
                if (m != null && m.DangerLevel == "critical") number++;
            }

            return number;
        }
        #endregion statistics

 // == INSTANCE PRIVATE METHODS ===============================================================

        #region statistics
        /// <summary> Retrieves number of cashflow items from given Profile.
        /// Counts all items based on given cash type. </summary>
        /// <param name="prof"> Cashflow profile. </param>
        /// <param name="cashType"> Type of cash (income/outcome).</param>
        /// <returns> Number of all items of given cash type. </returns>
        private int getNumCashflowItems(Profile prof, string cashType)
        {
            int number = (from cats in LinqUtil.DB.Category
                          join cashed in LinqUtil.DB.Cashed
                          on cats.Category_id equals cashed.Category_id
                          where cats.Profile_id == prof.Profile_id && cashed.Cashtype == cashType
                          select cashed.Item).Count(o => true);

            return number;
        }

        /// <summary> Retrieves total cashflow from given Profile. Sums up 
        /// all items based on given cash type. </summary>
        /// <param name="prof"> Cashflow profile. </param>
        /// <param name="cashType"> Type of cash (income/outcome).</param>
        /// <returns> Sum of all items of given cash type. </returns>
        private int getSumCashflowItems(Profile prof, string cashType)
        {
            int total = 0;

            try
            {
                total = (from cats in LinqUtil.DB.Category
                         join cashed in LinqUtil.DB.Cashed
                         on cats.Category_id equals cashed.Category_id
                         where cats.Profile_id == prof.Profile_id && cashed.Cashtype == cashType
                         select cashed.Item).Sum(o => o.Item_value);
            }
            catch (Exception ex)
            {
                ex.ToString();
                total = 0;
            }

            return total;
        }
        #endregion statistics

        #region consistency check
        /// <summary> Checks if same Profile record exists in local DB. </summary>
        /// <param name="profileToCheck">Profile record for which will be searching for. </param>
        /// <returns>True - Record already exists.
        ///          False - Records does not exist.</returns>
        private bool isProfileInDB(Profile profileToCheck)
        {
            return LinqUtil.DB.Profile.Where(p =>
                p.Account_id == profileToCheck.Account_id &&
                p.Name == profileToCheck.Name &&
                p.Type == profileToCheck.Type).Any();
        }
        #endregion consistency check

    }    
}
