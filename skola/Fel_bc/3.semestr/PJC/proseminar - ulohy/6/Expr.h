#ifndef __Expr_h__2049387592465903246509327456__
#define __Expr_h__2049387592465903246509327456__

#include "Tree.h"

class CParser
 {
   public:
    static CNode           * Parse               ( const char * Str );
    
   protected:
    
    const char             * m_Str;
    int                      m_Int;
    int                      m_Token;
    
    static const int         LEX_EOF        = 0;
    static const int         LEX_ERR        = 1;
    static const int         LEX_INT        = 2;
    static const int         LEX_ADD        = 3;
    static const int         LEX_SUB        = 4;
    static const int         LEX_MUL        = 5;
    static const int         LEX_DIV        = 6;
    static const int         LEX_LPA        = 7;
    static const int         LEX_RPA        = 8;
    static const int         LEX_SQRT       = 9;
    
                             CParser             ( const char * Str );
    CNode                  * parse               ( void );
    
    int                      nextToken           ( void );
    
    CNode                  * n_e                 ( void );
    CNode                  * n_t                 ( void );
    CNode                  * n_f                 ( void );
 };   


#endif /* __Expr_h__2049387592465903246509327456__ */
