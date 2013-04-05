create or replace
PROCEDURE NAPLN_BARVY AS 
-- created - Sobota-�nor-12-2011 Ivan Halaska
-- dalsi upravy:
begin
            declare V_POM PLS_INTEGER;
            begin select COUNT(*) into V_POM from barva;
                  if V_POM > 0 then DBMS_OUTPUT.PUT_LINE('Tabulka BARVA nen� pr�zdn�, kon��m');  
                  return;
                  end if;
            end;
INSERT INTO BARVA (ID_BARVA, NAZEV, ZKRATKA) values (1, 'black', 'B');
INSERT INTO BARVA (ID_BARVA, NAZEV, ZKRATKA) values (2, 'blue', 'U');
INSERT INTO BARVA (ID_BARVA, NAZEV, ZKRATKA) values (3, 'white', 'W');
INSERT INTO BARVA (ID_BARVA, NAZEV, ZKRATKA) values (4, 'green', 'G');
INSERT INTO BARVA (ID_BARVA, NAZEV, ZKRATKA) values (5, 'red', 'R');
INSERT INTO BARVA (ID_BARVA, NAZEV, ZKRATKA) values (6, 'multicolor', 'M');
INSERT INTO BARVA (ID_BARVA, NAZEV, ZKRATKA) values (7, 'colorless', 'C');



commit;
declare 
V_POM PLS_INTEGER; 
begin
select COUNT(*) into V_POM from BARVA;
DBMS_OUTPUT.PUT_LINE('Do tabulky BARVY bylo vlozeno '||V_POM||' radku');
end;
END DEMO_NAPLN_SEMESTRY;