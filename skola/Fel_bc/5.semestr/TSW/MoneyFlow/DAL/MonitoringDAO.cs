using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using BLL;
using System.Data.Linq;

namespace DAL
{   
    /// <summary> DataAccessObject class for Monitoring Object providing
    /// stoppers monitoring. </summary>
    public class MonitoringDAO
    {

 // == INSTANCE PUBLIC METHODS ================================================================

        #region data access
        /// <summary> Gets all active Stoppers in danger state from given profile. </summary>
        /// <param name="prof"> From which profile. </param>
        /// <param name="isActive"> True - Danger stoppers are active now.
        ///                         False - All danger stoppers (including inactive). </param>
        /// <returns> Stoppers in danger state. </returns>
        public List<Monitoring> getAllDangerStoppers(Profile prof, bool isActiveOnly)
        {
            List<Monitoring> mon;

            // Active or all stoppers
            if (isActiveOnly)
            {
                mon = getAllActiveStoppers(prof);
            }
            else
            {
                mon = getAllStoppers(prof);
            }

            List<Monitoring> dangerStoppers = new List<Monitoring>();

            // Sorting out these which are not in danger state
            for (int i = 0; i < mon.Count; i++)
            {
                if (mon[i].DangerLevel != null)
                    dangerStoppers.Add(mon[i]);
            }

            return dangerStoppers;
        }

        /// <summary> Gets all Stoppers from given profile out of DB. </summary>
        /// <param name="prof"> From which profile. </param>
        /// <returns> All Stoppers records. </returns>
        public List<Monitoring> getAllStoppers(Profile prof)
        {
            return (from mons in LinqUtil.DB.Monitoring
                    join cats in LinqUtil.DB.Category
                    on mons.Category_id equals cats.Category_id
                    where cats.Profile_id == prof.Profile_id
                    select mons).ToList();
        }

        /// <summary> Gets all Active Stoppers from given profile out of DB. </summary>
        /// <param name="prof"> From which profile. </param>
        /// <returns> All active Stoppers records. </returns>
        public List<Monitoring> getAllActiveStoppers(Profile prof)
        {
            return (from mons in LinqUtil.DB.Monitoring
                    join cats in LinqUtil.DB.Category
                    on mons.Category_id equals cats.Category_id
                    where cats.Profile_id == prof.Profile_id &&
                          mons.From_date < DateTime.Now &&
                          mons.To_date > DateTime.Now
                    select mons).ToList();
        }

        /// <summary> Deletes Monitoring using Stopper's ID. </summary>
        /// <param name="stopperID"> ID of a stopper. </param>
        public void deleteByStopperID(int stopperID)
        {
            Monitoring mon = (from mons in LinqUtil.DB.Monitoring
                              where mons.Stopper_id == stopperID
                              select mons).First();

            Stopper stop = (from stopps in LinqUtil.DB.Stopper
                            where stopps.Stopper_id == stopperID
                            select stopps).First();

            LinqUtil.DB.Stopper.DeleteOnSubmit(stop);
            LinqUtil.DB.Monitoring.DeleteOnSubmit(mon);
        }

        /// <summary> Deletes whole list of Monitorings one by one. </summary>
        /// <param name="stoppers"> List of Stopper's monitorings. </param>
        internal void deleteAllStoppers(List<Monitoring> stoppers)
        {
            foreach (Monitoring stopper in stoppers)
            {
                deleteByStopperID(stopper.Stopper_id);
            }
        }
        #endregion data access

    }
}
