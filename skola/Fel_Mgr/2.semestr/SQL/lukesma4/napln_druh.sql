create or replace
PROCEDURE NAPLN_DRUH AS 
begin
            declare V_POM PLS_INTEGER;
            begin select COUNT(*) into V_POM from DRUH;
                  if V_POM > 0 then DBMS_OUTPUT.PUT_LINE('Tabulka DRUH neni prazdna, koncim');  
                  return;
                  end if;
            end;
            
-- napln druh
insert into druh (id_druh, nazev) values (1, 'control');
insert into druh (id_druh, nazev) values (2, 'aggro');
insert into druh (id_druh, nazev) values (3, 'combo');
insert into druh (id_druh, nazev) values (4, 'aggro-control');
insert into druh (id_druh, nazev) values (5, 'combo-control');
insert into druh (id_druh, nazev) values (6, 'prison');

--napln druh_poddruh
insert into druh_poddruh (druh_poddruh_id_naddruh, druh_poddruh_id_poddruh)
values (4, 1);
insert into druh_poddruh (druh_poddruh_id_naddruh, druh_poddruh_id_poddruh)
values (4, 2);
insert into druh_poddruh (druh_poddruh_id_naddruh, druh_poddruh_id_poddruh)
values (5,1);
insert into druh_poddruh (druh_poddruh_id_naddruh, druh_poddruh_id_poddruh)
values (5,3);

commit;
declare 
V_POM PLS_INTEGER; 
begin
select COUNT(*) into V_POM from DRUH;
DBMS_OUTPUT.PUT_LINE('Do tabulky DRUH bylo vlozeno '||V_POM||' radku');
end;
END NAPLN_DRUH;