#include <typeinfo>
#include <cstdio>
using namespace std;
#include "Tree.h"


//-------------------------------------------------------------------------------------------------
static inline int max2 ( int X1, int X2 )
 {
   return ( X1 > X2 ? X1 : X2 );
 }
//-------------------------------------------------------------------------------------------------
                   CNode::CNode                  ( void )
 {
   m_Initialized = 0;
 }                             
//-------------------------------------------------------------------------------------------------
TSize              CNode::GetSize                ( HDC hdc )
 {
   if ( ! m_Initialized ) 
    {
      m_Size        = getSize ( hdc );
      m_Initialized = 1;
    }  
   return ( m_Size );
 }
//------------------------------------------------------------------------------------------------- 
//------------------------------------------------------------------------------------------------- 
                   CUNode::CUNode                ( CNode * Child )
 {
   m_Child = Child;
 }                   
//------------------------------------------------------------------------------------------------- 
                   CUNode::~CUNode               ( void )
 {
   delete m_Child;
 }                    
//------------------------------------------------------------------------------------------------- 
//------------------------------------------------------------------------------------------------- 
                   CNumNode::CNumNode            ( int Val )
 {
   m_Val = Val;
 }                   
//------------------------------------------------------------------------------------------------- 
void               CNumNode::Draw                ( HDC hdc, int X, int Y )
 {
   char Txt[30];
   
   snprintf ( Txt, sizeof ( Txt ), "%d", m_Val );
   TextOut  ( hdc,  X, Y, Txt, strlen ( Txt ) );
 }          
//------------------------------------------------------------------------------------------------- 
TSize             CNumNode::getSize              ( HDC hdc ) const
 {
   char  Txt[30];
   SIZE  sz;
   TSize Res;

   snprintf             ( Txt, sizeof ( Txt ), "%d", m_Val );
   GetTextExtentPoint32 ( hdc, Txt, strlen ( Txt ), &sz );
   Res . W              = sz . cx;
   Res . H              = sz . cy;
   Res . Baseline       = sz . cy / 2;
   return ( Res );
 }
//------------------------------------------------------------------------------------------------- 
//------------------------------------------------------------------------------------------------- 
                   CParNode::CParNode            ( CNode * Child ) : CUNode ( Child )
 {
 }                   
//------------------------------------------------------------------------------------------------- 
CNode            * CParNode::SkipParentheses     ( void )
 {
   CNode * res = m_Child;
   m_Child     = NULL;
   delete        this;
   return ( res );
 }
//------------------------------------------------------------------------------------------------- 
void               CParNode::Draw                ( HDC hdc, int X, int Y )
 {
   m_Child -> Draw ( hdc, X + 5, Y + 2 );
   
   MoveToEx ( hdc, X + 3, Y, NULL );
   LineTo   ( hdc, X,     Y );
   LineTo   ( hdc, X,     Y + m_Size . H - 1 );
   LineTo   ( hdc, X + 3, Y + m_Size . H - 1 );

   MoveToEx ( hdc, X + m_Size . W - 1 - 3, Y, NULL );
   LineTo   ( hdc, X + m_Size . W - 1, Y );
   LineTo   ( hdc, X + m_Size . W - 1, Y + m_Size . H - 1 );
   LineTo   ( hdc, X + m_Size . W - 1 - 3, Y + m_Size . H - 1 );
 }          
//------------------------------------------------------------------------------------------------- 
TSize             CParNode::getSize              ( HDC hdc ) const
 {
   TSize sz;
   
   sz             = m_Child -> GetSize ( hdc );
   sz . W        += 10;
   sz . H        += 4;
   sz . Baseline += 2;
   return ( sz );
 }
//------------------------------------------------------------------------------------------------- 
//------------------------------------------------------------------------------------------------- 
                   CSqrtNode::CSqrtNode          ( CNode * Child ) : CUNode ( Child )
 {
 }                   
//------------------------------------------------------------------------------------------------- 
void               CSqrtNode::Draw               ( HDC hdc, int X, int Y )
 {
   m_Child -> Draw ( hdc, X + 10, Y + 4 );
   
   MoveToEx ( hdc, X, Y + 2 * m_Size . H / 3, NULL );
   LineTo   ( hdc, X + 5, Y + m_Size . H - 1 );
   LineTo   ( hdc, X + 10, Y );
   LineTo   ( hdc, X + m_Size . W -1 , Y );
 }          
//------------------------------------------------------------------------------------------------- 
TSize             CSqrtNode::getSize             ( HDC hdc ) const
 {
   TSize sz;
   
   sz             = m_Child -> GetSize ( hdc );
   sz . W        += 10;
   sz . H        += 4;
   sz . Baseline += 4;

   return ( sz );
 }
//------------------------------------------------------------------------------------------------- 
//------------------------------------------------------------------------------------------------- 
                   CBNode::CBNode                ( CNode * L, CNode * R ) 
 {
   m_L = L;
   m_R = R;
 }                   
//------------------------------------------------------------------------------------------------- 
                   CBNode::~CBNode               ( void )
 {
   delete m_L;
   delete m_R;
 }                    
//------------------------------------------------------------------------------------------------- 
//------------------------------------------------------------------------------------------------- 
                   CBHorNode::CBHorNode          ( CNode * L, CNode * R ) : CBNode ( L, R )
 {
 }                   
//------------------------------------------------------------------------------------------------- 
TSize              CBHorNode::getSize            ( HDC hdc ) const
 {
   TSize        lsz, rsz;
   const char * Op;
   SIZE         sz;
   TSize        res;
   
   lsz = m_L -> GetSize ( hdc );
   rsz = m_R -> GetSize ( hdc );
   
   Op = getOperator ();
   
   GetTextExtentPoint32 ( hdc, Op, strlen ( Op ), &sz );
   
   res . Baseline = max2 ( lsz . Baseline, rsz . Baseline );
   res . W        = lsz . W + 5 + sz . cx + 5 + rsz . W;
   res . H        = res . Baseline + max2 ( lsz . H - lsz . Baseline, rsz . H - rsz . Baseline );
   
   return ( res );
 }
//------------------------------------------------------------------------------------------------- 
void               CBHorNode::Draw               ( HDC hdc, int X, int Y )
 {
   TSize        lsz, rsz;
   const char * Op;
   SIZE         sz;
   
   lsz = m_L -> GetSize ( hdc );
   rsz = m_R -> GetSize ( hdc );
   
   Op  = getOperator ();
   
   GetTextExtentPoint32 ( hdc, Op, strlen ( Op ), &sz );
   
   
   m_L -> Draw ( hdc, X, Y + m_Size . Baseline - lsz . Baseline );
   
   TextOut ( hdc, X + lsz . W + 5, Y + m_Size . Baseline - sz . cy / 2, Op, strlen ( Op ) );
   
   m_R -> Draw ( hdc, X + lsz . W + 5 + sz . cx + 5, Y + m_Size . Baseline - rsz . Baseline );
 }
//------------------------------------------------------------------------------------------------- 
//------------------------------------------------------------------------------------------------- 
                   CAddNode::CAddNode            ( CNode * L, CNode * R ) : CBHorNode ( L, R )
 {
 }                   
//------------------------------------------------------------------------------------------------- 
const char       * CAddNode::getOperator         ( void ) const
 {
   return ( "+" );
 }
//------------------------------------------------------------------------------------------------- 
//------------------------------------------------------------------------------------------------- 
                   CSubNode::CSubNode            ( CNode * L, CNode * R ) : CBHorNode ( L, R )
 {
 }                   
//------------------------------------------------------------------------------------------------- 
const char       * CSubNode::getOperator         ( void ) const
 {
   return ( "-" );
 }
//------------------------------------------------------------------------------------------------- 
//------------------------------------------------------------------------------------------------- 
                   CMulNode::CMulNode            ( CNode * L, CNode * R ) : CBHorNode ( L, R )
 {
 }                   
//------------------------------------------------------------------------------------------------- 
const char       * CMulNode::getOperator         ( void ) const
 {
   return ( "*" );
 }
//------------------------------------------------------------------------------------------------- 
//------------------------------------------------------------------------------------------------- 
                   CDivNode::CDivNode            ( CNode * L, CNode * R ) : CBNode ( L, R )
 {
   while ( typeid ( * m_L ) == typeid ( CParNode ) ) 
     m_L = ((CParNode*)m_L) -> SkipParentheses ();
  
   while ( typeid ( * m_R ) == typeid ( CParNode ) ) 
     m_R = ((CParNode*)m_R) -> SkipParentheses ();
 }                   
//------------------------------------------------------------------------------------------------- 
TSize              CDivNode::getSize             ( HDC hdc ) const
 {
   TSize        lsz, rsz;
   TSize        res;
   
   lsz = m_L -> GetSize ( hdc );
   rsz = m_R -> GetSize ( hdc );
   
   res . Baseline = lsz . H + 3; 
   res . W        = max2 ( lsz . W, rsz . W ) + 6;
   res . H        = lsz . H + 5 + rsz . H;
   
   return ( res );
 }
//------------------------------------------------------------------------------------------------- 
void               CDivNode::Draw                ( HDC hdc, int X, int Y )
 {
   TSize        lsz, rsz;
   
   lsz = m_L -> GetSize ( hdc );
   rsz = m_R -> GetSize ( hdc );
   
   m_L -> Draw ( hdc, X + ( m_Size . W - lsz . W ) / 2, Y );
   
   MoveToEx ( hdc, X,              Y + m_Size . Baseline, NULL );
   LineTo   ( hdc, X + m_Size . W, Y + m_Size . Baseline );
   
   m_R -> Draw ( hdc, X + ( m_Size . W - rsz . W ) / 2, Y + m_Size . Baseline + 5 );

 }
//------------------------------------------------------------------------------------------------- 
