create or replace
PACKAGE BODY NAPLN_DATA
is 
--------------------------------------------------------
-- upravy:
--------------------------------------------------------
--   function POCET_UCITELU return PLS_INTEGER is
--      begin 
--           return C_POCET_UCITELU;
--   end POCET_UCITELU;
--   
--
   procedure NAPLN_VSECHNY_TABULKY is
      begin
-- procedura postupne zavola procedury pro plneni tabulek DEMO schematu
-- procedury je nutne volat ve zde uvedenem poradi.
-- zatim jsou procedury lokalni, pozadeji je vlozim do balicku
-----------------------------------------------------------------------------------------------------------------
-- vyprazdni tabulky
-- aby bylo mozne tabulky vyprazdnit, je nutne vypnout kontrolu referencnich cizich klicu
-----------------------------------------------------------------------------------------------------------------
    NAPLN_BARVA;
 --   NAPLN_DECK;
 --napln druh + druh_poddruh
    NAPLN_DRUH;
    NAPLN_OSOBA;
 --   NAPLN_JE_DRUHU;
 --   NAPLN_JE_V_SIDE;
 --   NAPLN_KARTA;
 --   NAPLN_MAIN;
 --   NAPLN_OBSAHUJE;
 --   NAPLN_SIDE;
 --   NAPLN_TURNAJ;
 --   NAPLN_TYP;
    
DBMS_OUTPUT.put_line('Koncim, ...');
-- opatri tabulky statistikami
      DBMS_STATS.GATHER_SCHEMA_STATS(OWNNAME => user,ESTIMATE_PERCENT => 100);
end NAPLN_VSECHNY_TABULKY;
end NAPLN_DATA;