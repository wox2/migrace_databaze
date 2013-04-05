create or replace
PACKAGE NAPLN_DATA 
is
--proc pouzil Halaska SIMPLE_INTEGER?
    C_POCET_OSOB CONSTANT INTEGER:=1000;
    
-- tuto vyjimku vyuzivaji procedury pro plneni jednotlivych tabulek
    dml_errors EXCEPTION;
    PRAGMA EXCEPTION_INIT(DML_ERRORS, -24381);
-- vytvor public synonumum dump_bulk_exceptions
procedure NAPLN_VSECHNY_TABULKY;
---function POCET_ucitelu return PLS_INTEGER;
end NAPLN_DATA;