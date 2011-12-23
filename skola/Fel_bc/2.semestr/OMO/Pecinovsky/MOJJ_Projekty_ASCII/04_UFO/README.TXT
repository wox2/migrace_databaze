================================================================================
NAZEV PROJEKTU:  03_UFO
================================================================================

AUTOR:           Rudolf PECINOVSKY 
VERZE A DATUM:   2.0 - 10/04
================================================================================


UCEL PROJEKTU:
==============
Definovat tridu UFO tak, aby byla pouzitelna ve hre, pri ktere dispecer
na pozadani vypousti instance tridy UFO a ukolem hrace je dovest tyto
instance do hangaru v co nejkratsim case.


JAK PROJEKT SPUSTIT:
====================
Uzivatel si zvoli typ dispecera, ktereho bude pouzivat, a zaslanim zpravy 
getDispecer() pozada tridu Dispecer o odkaz na jeji instanci.
Dalsi postup se lisi podle zpusobu ovladani, pro nejz se uzivatel rozhodne.

Prime zasilani zprav:
---------------------
Pri komunikaci s dispecerem za pomoci dialogovych oken prostredi BlueJ
spociva hra v posilan spravnych sprav ves srpavnou chvili.
Instance tridy UFO jsou ovladany zadanim vodorovne a svisle slozky 
jejich rychlosti zaslanim zpravy setRychlost(x,y).

Obladani z klavesnice:
----------------------
Druhou moznosti je ovladani primo z klavesnice. 
V takovem pripade musi byt nejprva aktivni okno "Vesmir s UFO".
O pristaveni dalsiho UFO pozadame tridu Dispecer stiskem klavesy Enter.
Instance tridy UFO jsou pak ovladany pomoci kurzorovych sipek,
jejichz stiskem nastavujeme tah motoru v danem smeru. 
Tah motoru udava zrychleni UFO. Ma-li se UFO pohybovat konstatni rychlosti, 
musi mit nulove zrychleni, tj. musi mit vypnute motory.

Motory lze vypnout (a tim nastavit nulove zrychleni) stiskem mezerniku.

Pri soucasnem ovladani nekolika UFO muzete zmenit ovladane UFO 
stiskem klavesy s cislem napsanym na danem UFO.


INSTRUKCE PRO UZIVATELE:
========================
Pocitejte s tim, ze dokud nevypnete motory, UFO stale zrychluje.
Chcete-li UFO zastavit, musite je nejprve zbrzdit a kdyz stoji,
tak nazapomenout vypnout motory, protoze jinak zacne zrychlovat
opacnym smerem.
