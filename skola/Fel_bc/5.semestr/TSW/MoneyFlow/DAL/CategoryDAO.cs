using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using BLL;

namespace DAL
{
    /// <summary> DataAccessObject class for Category Objects. </summary>
    public class CategoryDAO
    {

 // == INSTANCE PUBLIC METHODS ================================================================

        #region data access
        /// <summary> Loads all Categories from given profile with or without their 
        /// subcategories</summary>
        /// <param name="currentProf"> Profile of categories to load. </param>
        /// <param name="withSubcats"> 
        /// True - Loads all categories and also their subcategories
        /// False - Loads only all categories without their subcategories. </param>
        /// <returns>All loaded categories from given profile. </returns>
        public List<Category> getAllCategories(Profile currentProf, bool withSubcats)
        {
            List<Category> categories = (from cats in LinqUtil.DB.Category
                                         where cats.Profile_id == currentProf.Profile_id &&
                                               cats.Sup_category_id == null
                                         select cats).ToList();

            if (withSubcats)
            {
                List<Category> subCategs = getAllSubCategories(currentProf);
                foreach (Category categs in categories)
                {
                    // Add subcategories to their category
                    categs.addSubCategories(subCategs);
                }
            }

            return categories;
        }

        /// <summary> Loads all available subcategories from given profile. </summary>
        /// <param name="currentProf">Profile of subcategories to load. </param>
        /// <returns></returns>
        public List<Category> getAllSubCategories(Profile currentProf)
        {
            var subcategories = from subcats in LinqUtil.DB.Category
                                where subcats.Profile_id == currentProf.Profile_id &&
                                      subcats.Sup_category_id != null
                                select subcats;

            return subcategories.ToList();
        }

        /// <summary> Inserts new Category (or subcategory) into pending state before saving into DB. </summary>
        /// <param name="newCateg"> Category to insert into pending state. </param>
        public void insertCategory(Category newCateg)
        {
            if (isCategoryInDB(newCateg))
                throw new ApplicationException("Cannot create category which already exists!");

            LinqUtil.DB.Category.InsertOnSubmit(newCateg);
        }

        /// <summary> Deletes category and also its subcategories if contains any. </summary>
        /// <param name="catToDelete"> Category which should be deleted. </param>
        public void deleteCategory(Category catToDelete)
        {
            // First, delete subcategories
            for (int i = 0; i < catToDelete.subCategoriesCount(); i++)
            {
                deleteCategory(catToDelete[i]);
            }

            // Delete category
            Category toDelete = (from cats in LinqUtil.DB.Category
                                 where cats.Category_id == catToDelete.Category_id
                                 select cats).First();

            // Delete cashed & monitoring first
            MonitoringDAO monitoringDAO = new MonitoringDAO();
            CashedDAO cashDAO = new CashedDAO();

            monitoringDAO.deleteAllStoppers(toDelete.Monitoring.ToList());
            cashDAO.deleteAllCashes(toDelete.Cashed.ToList());

            LinqUtil.DB.Category.DeleteOnSubmit(toDelete);
        }

        /// <summary> Moves subcategory under new parent. </summary>
        /// <param name="subcategToUpdate"> Subcategory which should be moved and updated. </param>
        /// <param name="parentCateg"> New parental category. </param>
        public void updateSubcatsParent(Category subcategToUpdate, Category parentCateg)
        {
            Category toUpdate = (from categs in LinqUtil.DB.Category
                                 where categs.Category_id == subcategToUpdate.Category_id
                                 select categs).First();

            // Updating record
            toUpdate.Sup_category_id = parentCateg.Category_id;
        }

        /// <summary> Updates given category in database. </summary>
        /// <param name="categToUpdate"> Category which should be updated. </param>
        public void updateCategory(Category categToUpdate)
        {
            if (isCategoryInDB(categToUpdate))
                throw new ApplicationException("Cannot create category which already exists!");

            Category toUpdate = (from categs in LinqUtil.DB.Category
                                 where categs.Category_id == categToUpdate.Category_id
                                 select categs).First();

            // Updating record
            toUpdate.Sup_category_id = categToUpdate.Sup_category_id;
            toUpdate.Name = categToUpdate.Name;
            toUpdate.Description = categToUpdate.Description;
        }

        /// <summary> Deletes all categories from given categories list. </summary>
        /// <param name="categs"> List of categories for deleteing. </param>
        internal void deleteAllCategories(List<Category> categs)
        {
            foreach (Category categ in categs)
            {
                deleteCategory(categ);
            }
        }
        #endregion data access

        #region generators
        /// <summary> Generates new ID for new Category. </summary>
        /// <returns> Incremented biggest Category ID value. </returns>
        public int getNewID()
        {
            int max = (from categs in LinqUtil.DB.Category
                       select categs.Category_id).Max();

            return max + 1;
        }
        #endregion generators

 // == INSTANCE PRIVATE METHODS ===============================================================

        #region consistency check
        private bool isCategoryInDB(Category categToCheck)
        {
            bool vysledek = LinqUtil.DB.Category.Where(p =>
                p.Profile_id == categToCheck.Profile_id &&
                (p.Sup_category_id == categToCheck.Sup_category_id ||
                (p.Sup_category_id == null && categToCheck.Sup_category_id == null)) &&
                p.Name == categToCheck.Name).Any();

            return vysledek;
        }
        #endregion consistency check

    }
}
