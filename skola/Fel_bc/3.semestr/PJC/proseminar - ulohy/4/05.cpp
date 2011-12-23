#include <iostream>
#include <iomanip>
using namespace std;

class CSet2
 {
   public:
                             CSet2               ( void );
                             CSet2               ( const CSet2 & a );
                            ~CSet2               ( void ); 
    void                     Set                 ( int x );
    void                     Reset               ( int x );
    int                      IsSet               ( int x ) const;
    void                     Clear               ( void );
    void                     Print               ( ostream & os ) const;
   protected:
    struct TNode
     {
       TNode               * P;
       TNode               * L;
       TNode               * R;
       int                   Val;
     };
     
    TNode                  * m_Root;
    int                      m_Nodes; 
    static void              cloneTree           ( TNode ** Dst, TNode * DstParent, TNode * src );
    static void              delTree             ( TNode * Dst );
 };
 


//-------------------------------------------------------------------------------------------------
                   CSet2::CSet2                  ( void )
 {
   m_Nodes = 0;
   m_Root  = NULL;
 }                    
//-------------------------------------------------------------------------------------------------
                   CSet2::CSet2                  ( const CSet2 & x )
 {
   m_Nodes = x . m_Nodes;
   cloneTree ( &m_Root, NULL, x . m_Root );
 }
//-------------------------------------------------------------------------------------------------
                   CSet2::~CSet2                 ( void )
 {
   delTree ( m_Root );
 }                    
//-------------------------------------------------------------------------------------------------
void               CSet2::Set                    ( int x )
 {
   TNode **wr, *n, *p;
   
   p  = NULL;
   wr = & m_Root;
   
   while ( 1 )
    {
      if ( ! * wr )
       {
         n = new TNode;
         n -> Val = x;
         n -> L = n -> R = NULL;
         n -> P = p;
        *wr = n;
         m_Nodes ++;
         return;  
       }
      
      p = *wr;
      if ( p -> Val == x ) return; // already present
      
      if ( x < p -> Val )
        wr = &p -> L;
       else
        wr = &p -> R;
    }
 }                    
//-------------------------------------------------------------------------------------------------
void               CSet2::Reset                  ( int x )
 {
   TNode ** wr, * tmp, *p;
   
   wr = & m_Root;
   while ( 1 )
    {
      if ( ! *wr ) return;   // not present
      
      p = * wr;
      
      if ( p -> Val == x )
       {
         if ( p -> L && p -> R )
          {
            tmp = p -> L;
            wr  = & p -> L;
            
            while ( tmp -> R ) 
             {
               wr = & tmp -> R; 
               tmp = tmp -> R;
             }  
               
            p -> Val = tmp -> Val;
            p = tmp;
          }
          
         if ( p -> L ) 
           {
             *wr = p -> L;
              if ( p -> L ) p -> L -> P = p -> P;
           }   
          else
           {
             *wr = p -> R;
              if ( p -> R ) p -> R -> P = p -> P;
           }   
         m_Nodes --;
         delete p;
         return;
       }
      if ( x < p -> Val )
        wr = & p -> L;
       else
        wr = & p -> R; 
    }
 }                    
//-------------------------------------------------------------------------------------------------
int                CSet2::IsSet                  ( int x ) const
 {
   TNode * tmp = m_Root;
   
   while ( tmp )
    {
      if ( tmp -> Val == x ) return ( 1 );
      
      if ( x < tmp -> Val ) 
        tmp = tmp -> L;
       else
        tmp = tmp -> R;
    }
   return ( 0 ); 
 }
//-------------------------------------------------------------------------------------------------
void               CSet2::Clear                  ( void ) 
 {
   delTree ( m_Root );
   m_Root = NULL;
   m_Nodes = 0;
 }
//-------------------------------------------------------------------------------------------------
void               CSet2::Print                  ( ostream & os ) const
 {
   TNode * tmp, * P;
   int     first = 1;
   
   tmp = m_Root;
   if ( tmp )
    while ( tmp -> L ) tmp = tmp -> L;
   
   cout << "{";
   
   while ( tmp )
    {
      if ( ! first )
       cout << ", ";
      first = 0; 
      cout << tmp -> Val;
      
      if ( tmp -> R ) 
       {
         tmp = tmp -> R;
         while ( tmp -> L ) tmp = tmp -> L;
       }
      else
       { 
         while ( 1 )
          {
            P = tmp -> P;
            if ( ! P ) 
             { 
               tmp = NULL;
               break;
             }  
            if ( P -> L == tmp ) 
             {
               tmp = P;
               break;
             }  
            
            tmp = P; 
          }  
       }
    }
   
   cout << " }" << endl;
 }
//-------------------------------------------------------------------------------------------------
void               CSet2::cloneTree              ( TNode ** Dst, TNode * DstParent, TNode * src ) 
 {
   TNode * n;
   
   n        = new TNode;
   n -> Val = src -> Val;
   n -> P   = DstParent;
  *Dst      = n;

   if ( src -> L )
     cloneTree ( &n -> L, n, src -> L );
    else
     n -> L = NULL;
     
   if ( src -> R )
     cloneTree ( &n -> R, n, src -> R );
    else
     n -> R = NULL;
 } 
//-------------------------------------------------------------------------------------------------
void               CSet2::delTree                ( TNode * Dst ) 
 {
   if ( Dst -> L )
    delTree ( Dst -> L );
   if ( Dst -> R ) 
    delTree ( Dst -> R );
   delete Dst;
 }
//-------------------------------------------------------------------------------------------------
int                main                          ( int argc, char * argv [] )
 {
   CSet2 A;
   
   A . Set ( 10 );
   A . Set ( 20 );
   A . Set ( 5 );
   A . Set ( 30 );
   A . Set ( 8 );
   
   cout << "A = ";
   A . Print ( cout );


   A . Reset ( 30 );
   A . Reset ( 5 ); 
   A . Reset ( 10 ); 
   
   A . Set ( 3 );
   
   cout << A . IsSet ( 3 ) << " " << A . IsSet ( 20 ) << " " << A . IsSet ( 17 ) << endl;

   CSet2 B = A;

   B . Set ( 25 );

   A . Reset ( 10 );
   
   cout << "A = ";
   A . Print ( cout );
   
   cout << "B = ";
   B . Print ( cout );
   
   return ( 0 );
 }
//-------------------------------------------------------------------------------------------------
