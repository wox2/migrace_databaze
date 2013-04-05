#include <iostream>
#include <iomanip>
using namespace std;
#include <windows.h>
#include "Tree.h"
#include "Expr.h"

static const char mainWndClass [] = "MainWndClass";

static CNode * Tree;

//-------------------------------------------------------------------------------------------------
static LRESULT CALLBACK mainWndCallback              ( HWND hwnd, unsigned Msg, WPARAM wparam, LPARAM lparam )
 {
   PAINTSTRUCT ps;
   TSize       sz;
   
   switch ( Msg )
    {
      case WM_DESTROY:
       PostQuitMessage ( 0 );
       break;
      case WM_PAINT:
       BeginPaint ( hwnd, &ps );

       sz = Tree -> GetSize ( ps . hdc );
       Tree -> Draw ( ps . hdc, 20, 20 );
       
       EndPaint ( hwnd, &ps );
       break;
      
    }
   return ( DefWindowProc ( hwnd, Msg, wparam, lparam ) );
 }
//-------------------------------------------------------------------------------------------------
int                main                          ( int argc, char * argv [] )
 {
   MSG        msg;
   WNDCLASS   wc;
   HWND       MainWnd;


   if ( argc != 2 ) 
    {
      cout << "expr <expression>" << endl;
      return ( 1 );
    }         
    
   Tree = CParser :: Parse ( argv[1] );
   
   if ( !Tree ) return ( 1 );

   
   wc . style        = CS_HREDRAW | CS_VREDRAW;
   wc . lpfnWndProc  = mainWndCallback;
   wc . cbClsExtra   = 0; 
   wc . cbWndExtra   = sizeof ( DWORD ); 
   wc . hInstance    = GetModuleHandle ( NULL );    
   wc . hIcon        = NULL; 
   wc . hCursor      = NULL; 
   wc . hbrBackground= (HBRUSH)GetStockObject ( WHITE_BRUSH ); 
   wc . lpszMenuName = NULL; 
   wc . lpszClassName= mainWndClass;    
   
   if ( !RegisterClass ( &wc ) ) return ( 0 );
   
   MainWnd = CreateWindow ( mainWndClass, "Expr", WS_OVERLAPPEDWINDOW | WS_CLIPCHILDREN | WS_CLIPSIBLINGS,
                           CW_USEDEFAULT, CW_USEDEFAULT, CW_USEDEFAULT,  CW_USEDEFAULT, 
                           NULL, NULL, GetModuleHandle ( NULL ), NULL );
   
   ShowWindow ( MainWnd, SW_SHOW );
   while ( GetMessage ( &msg, NULL, 0, 0 ) )
    {
      TranslateMessage ( &msg );
      DispatchMessage ( &msg );
    }
   delete Tree; 
    
   return ( 0 );
 }
//-------------------------------------------------------------------------------------------------
 



