create or replace
PROCEDURE NAPLN_OSOBA 
is

type T_OSOBA  is table of OSOBA%ROWTYPE index by PLS_INTEGER;
V_OSOBA                    T_OSOBA;

type T_ROZHODCI  is table of ROZHODCI%ROWTYPE index by PLS_INTEGER;
V_ROZHODCI                    T_ROZHODCI;

type T_HRAC  is table of HRAC%ROWTYPE index by PLS_INTEGER;
V_HRAC                    T_HRAC;

osoba_count pls_integer;
rozhodci_count pls_integer;
hrac_count pls_integer;

begin
select COUNT(*) into osoba_count from OSOBA;
select COUNT(*) into rozhodci_count from rozhodci;
select COUNT(*) into hrac_count from hrac_count;

--checks
if osoba_count > 0 then
   DBMS_OUTPUT.PUT_LINE('Tabulka OSOBA není prázdná, končím');
   return;
else if hrac_count > 0 then
   DBMS_OUTPUT.PUT_LINE('Tabulka HRAC není prázdná, končím');
   return ;
else if rozhodci_count > 0 then
   DBMS_OUTPUT.PUT_LINE('Tabulka ROZHODCI není prázdná, končím');
   return ;   
end if;


for V_PAR  in 1..napln_data.C_POCET_OSOB LOOP
   if mod(V_PAR, 10) < 5 then
      V_OSOBA(V_PAR).jmeno :=  'Martin';
   else if mod(V_PAR, 10) < 8 then
      V_OSOBA(V_PAR).jmeno := 'Michal'; 
   else
      V_OSOBA(V_PAR).jmeno := 'Jirka'; 
   end if;
   
   V_OSOBA(V_PAR).prijmeni :=  DBMS_RANDOM.STRING('A', 10);
   V_OSOBA(V_PAR).id_OSOBA := V_PAR;

end LOOP;
-------------------------------------------------------------------------
/* pro kontrolu lze vypsat
for I in 1 ..V_KATEDRY.COUNT LOOP
if V_KATEDRY.exists(I) then
DBMS_OUTPUT.PUT_LINE(V_KATEDRY(I).KATEDRA_ID||' '||V_KATEDRY(I).NAZEV_KATEDRY);
end if;
end LOOP;
DBMS_OUTPUT.PUT_LINE('Kateder je '||V_KATEDRY.count);
*/
-------------------------------------------------------------------------
----- uloz obsah pole do tabulky UCITEL
 
select COUNT(*) into V_POM from DEMO_KATEDRA;
DBMS_OUTPUT.PUT_LINE('Do tabulky DEMO_KATEDRA bylo vloľeno '||V_POM||' řádků');
exception
  when NAPLN_DATA.DML_ERRORS then DUMP_BULK_EXCEPTIONS;
end;
-- uklidim
V_KATEDRY := V_KATEDRY_PRAZDNE;
end DEMO_NAPL_KATEDRY ;
