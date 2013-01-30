//This file was generated from (Academic) UPPAAL 4.0.13 (rev. 4577), September 2010

/*
Syst\u00e9m se nikdy nedostane do deadlocku
*/
A[] not deadlock

/*
V \u017e\u00e1dn\u00e9 h\u0159e se syst\u00e9m nedostane do v\u00edt\u011bzn\u00e9ho stavu hr\u00e1\u010de 3
*/
A[] !hra.ZVITEZIL_hrac3

/*
Ka\u017ed\u00e1 hra "skon\u010d\u00ed" (tzn projde stavem) v\u00edt\u011bzstv\u00edm hr\u00e1\u010de 1 (kde se zacykl\u00ed)
*/
A<> hra.ZVITEZIL_hrac1

/*
Hra se nikdy nedostane do v\u00edt\u011bzn\u00e9ho stavu hr\u00e1\u010d\u016f 2 ani 3 ani do nekorektn\u00edho stavu \u017e\u00e1dn\u00e9ho hr\u00e1\u010de (1,2,3)
*/
A[] !hra.ZVITEZIL_hrac3 && !hra.ZVITEZIL_hrac2 && !hra.NEKOREKTNI_TAH_hrace1 && !hra.NEKOREKTNI_TAH_hrace2 && !hra.NEKOREKTNI_TAH_hrace3
