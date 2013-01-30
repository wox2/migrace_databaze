//This file was generated from (Academic) UPPAAL 4.0.13 (rev. 4577), September 2010

/*
Syst\u00e9m se nikdy 
*/
A[] not deadlock

/*
There is no game when system 
*/
A[] !hra.ZVITEZIL_hrac3

/*

*/
A<> hra.ZVITEZIL_hrac1

/*

*/
A<> hra.ZVITEZIL_hrac3 || hra.ZVITEZIL_hrac2 || hra.ZVITEZIL_hrac1 || hra.NEKOREKTNI_TAH_hrace1 || hra.NEKOREKTNI_TAH_hrace2 || hra.NEKOREKTNI_TAH_hrace3

/*

*/
A[] not hra.ZVITEZIL_hrac3

/*

*/
E<> hra.ZVITEZIL_hrac1

/*

*/
A[] not hra.NEKOREKTNI_TAH_hrace3 and not hra.NEKOREKTNI_TAH_hrace2 and not hra.NEKOREKTNI_TAH_hrace1
