using System;
using BLL;

namespace DAL
{
    /// <summary> DAL ~ Class providing single DB data context to access database using Linq2SQL. </summary>
    public static class LinqUtil
    {

 // == CLASS PROPERTIES =======================================================================

        /// <summary> Storing only one DB data context. </summary>
        private static MFlowDB internalDataContext
        {
            get;
            [STAThread]
            set;
        }

        /// <summary> Returns currently used DB data context. </summary>
        internal static MFlowDB DB
        {
            get
            {   
                if ( internalDataContext == null ) 
                {
                    // Connection config
                    string connectionString = @"Data Source=MFlowDB.sdf;Password=nds1235";

                    // Creating new DB data context instance
                    internalDataContext = new MFlowDB(connectionString);
                }
                return internalDataContext;
            }
        }


// == CLASS PUBLIC METHODS ===================================================================

        /// <summary> Submits changes into current DB data context. </summary>
        public static void submitChanges()
        {
            DB.SubmitChanges();
        }

        /// <summary> Disposes current DB data context. </summary>
        public static void cleanUp()
        {
            if (internalDataContext != null)
            {
                internalDataContext.Dispose();
                internalDataContext = null;
            }
        }

    }
}
