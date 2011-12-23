#ifndef __Tree_h__2384593245627436520437965024__
#define __Tree_h__2384593245627436520437965024__

#include <windows.h>


struct TSize 
 {
   int W, H, Baseline;
 };


class CNode 
 {
   public:
                             CNode               ( void );
    virtual                 ~CNode               ( void ) { };
    virtual void             Draw                ( HDC hdc, int X, int Y ) = 0;
    TSize                    GetSize             ( HDC hdc );
    
   protected:
    virtual TSize            getSize             ( HDC hdc ) const = 0;
    TSize                    m_Size;
    int                      m_Initialized;
 };

class CUNode : public CNode
 {
   public: 
                             CUNode              ( CNode * Chld );
    virtual                 ~CUNode              ( void );
                             
   protected:
    CNode                  * m_Child;
 };
 
class CParNode : public CUNode
 {
   public: 
                             CParNode            ( CNode * Child );
    virtual void             Draw                ( HDC hdc, int X, int Y );
    virtual CNode          * SkipParentheses     ( void );
   
   protected:
    virtual TSize            getSize             ( HDC hdc ) const;                 
 }; 

class CSqrtNode : public CUNode
 {
   public: 
                             CSqrtNode           ( CNode * Child );
    virtual void             Draw                ( HDC hdc, int X, int Y );
   
   protected:
    virtual TSize            getSize             ( HDC hdc ) const;                 
 }; 

 
class CBNode : public CNode
 {
   public: 
                             CBNode              ( CNode * L, CNode * R );
    virtual                 ~CBNode              ( void );
   
   protected:
    CNode                  * m_L, * m_R;
 }; 

class CBHorNode : public CBNode
 {
   public: 
                             CBHorNode           ( CNode * L, CNode * R );
    virtual void             Draw                ( HDC hdc, int X, int Y );
   
   protected:
    virtual TSize            getSize             ( HDC hdc ) const;                 
    virtual const char     * getOperator         ( void ) const = 0;
 };
 
class CNumNode : public CNode 
 {
   public:
                             CNumNode            ( int X );
    virtual void             Draw                ( HDC hdc, int X, int Y );
                             
   protected:
    virtual TSize            getSize             ( HDC hdc ) const;
    
    int                      m_Val;
 }; 
 
class CAddNode : public CBHorNode 
 {
   public:
                             CAddNode            ( CNode * L, CNode * R );
   
                             
   protected:
    virtual const char     * getOperator         ( void ) const;
 };

class CSubNode : public CBHorNode 
 {
   public:
                             CSubNode            ( CNode * L, CNode * R );
   
                             
   protected:
    virtual const char     * getOperator         ( void ) const;
 };

class CMulNode : public CBHorNode 
 {
   public:
                             CMulNode            ( CNode * L, CNode * R );
   
                             
   protected:
    virtual const char     * getOperator         ( void ) const;
 };


class CDivNode : public CBNode 
 {
   public:
                             CDivNode            ( CNode * L, CNode * R );
    virtual void             Draw                ( HDC hdc, int X, int Y );
                             
   protected:
    virtual TSize            getSize             ( HDC hdc ) const;
 };


#endif /* __Tree_h__2384593245627436520437965024__ */ 
