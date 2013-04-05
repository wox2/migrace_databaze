using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Linq.Expressions;

using Type = System.Type;

namespace BLL
{
    /// <summary> Static class can sort data dynamically. </summary>
    public static class GenericSorter
    {

 // == ENUMERATIONS ===========================================================================

        #region enum
        /// <summary> Data sort direction. </summary>
        public enum SortDirection
        {
            Ascending,
            Descending
        }
        #endregion enum

 // == PUBLIC CLASS METHODS ===================================================================

        #region sorters
        public static IQueryable OrderBy<TEntity>(this IQueryable<TEntity> source, string fieldName) where TEntity : class
        {
            return OrderBy(source, fieldName, SortDirection.Ascending);
        }

        public static IQueryable OrderBy<TEntity>(this IQueryable<TEntity> source, string fieldName, SortDirection sortDirection) where TEntity : class
        {
            const string ORDER_BY_METHOD_NAME = "OrderBy";
            const string ORDER_BY_DESCENDING_METHOD_NAME = "OrderByDescending";
            const string PARAMETER_NAME = "Entity";

            // Get the type of the entity being sorted.
            var type = typeof(TEntity);

            // Create a parameter to pass into the Lambda expression (Entity => Entity.OrderByField).
            var parameter = Expression.Parameter(type, PARAMETER_NAME);

            // Get a reference to the type of the property being sorted.
            var property = type.GetProperty(fieldName);

            // Get a reference to the properties access member ( Entity.OrderByField ).
            var propertyAccess = Expression.MakeMemberAccess(parameter, property);

            // Create the order by expression.
            var orderByExp = Expression.Lambda(propertyAccess, parameter);

            string methodName;

            // Determine the method to actually call on the IQueryable interface.
            if (sortDirection == SortDirection.Ascending)
                methodName = ORDER_BY_METHOD_NAME;
            else
                methodName = ORDER_BY_DESCENDING_METHOD_NAME;

            // Get a reference to the method call.
            MethodCallExpression resultExp = Expression.Call(typeof(Queryable), methodName,
            new Type[] { type, property.PropertyType },
            source.Expression, Expression.Quote(orderByExp));

            // Now apply the sort.
            return source.Provider.CreateQuery(resultExp);
        }
        #endregion sorters

    }
    
}
