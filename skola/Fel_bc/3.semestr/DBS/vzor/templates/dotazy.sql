

set pagesize 1000
set echo on
set markup html on spool on head "<title>X36DBS - Čtvrtek 7.30 - Zbyněk Lstibůrek - Výstup SQL příkazů </title> <style type ='text/css'><!--body {background: ffffc6} --></style>" body "<h2>X36DBS - Čtvrtek 7.30 - Zbyněk Lstibůrek - Výstup SQL příkazů</h2>"
spool dotazy.html

       	
 -- Seznam koncertů, na nichž bylo více než 100 diváků

SELECT * 
FROM Koncert
WHERE divaku > 100;
 

 -- Seznam zpěváků, jejichž vedoucím je Alena Havlíková.


SELECT Zpevak.* 
FROM Zpevak JOIN (
         SELECT zkratka FROM Vedeni NATURAL JOIN Zpevak
         WHERE jmeno='Alena' AND prijmeni='Havlíková'
         ) ON hlas=zkratka;
 

 -- Stejné zadání jako předchozí, nyní s poddotazem v klauzuli WHERE.

SELECT * 
FROM Zpevak 
WHERE hlas = (
    SELECT zkratka FROM Vedeni NATURAL JOIN Zpevak
    WHERE jmeno='Alena' AND prijmeni='Havlíková'
    );
 

 -- Názvy všech skladeb A. Dvořáka v repertoiru.

SELECT nazev 
FROM Skladba NATURAL JOIN Autorstvi NATURAL JOIN Skladatel
WHERE jmeno='Antonín' AND prijmeni='Dvořák'; 
 

 -- Seznam zpěváků, kteří nemají zapůjčeny šaty.

SELECT * FROM Zpevak 
MINUS
SELECT Zpevak.* 
    FROM Zpevak JOIN Zapujcka_satu
         ON Zpevak.RC = Zapujcka_satu.RC;
 

 -- Stejné zadání jako předchozí, řešení pomocí poddotazu.

SELECT * 
FROM Zpevak
WHERE RC NOT IN (SELECT RC FROM Zapujcka_satu); 
 

 -- Seznam dosud neprovedených skladeb (nebyly na programu žádného koncertu), včetně autorů.

SELECT jmeno, prijmeni, nazev
FROM (
        SELECT id, nazev FROM Skladba
        MINUS
        SELECT id, nazev FROM Skladba
        NATURAL JOIN Cast_programu NATURAL JOIN Koncert
       ) LEFT JOIN Autorstvi USING (id)
         LEFT JOIN Skladatel USING(jmeno, prijmeni);

 

 -- Seznam vícedílných skladeb.

SELECT jmeno, prijmeni, nazev
FROM (SELECT * FROM Skladba WHERE id IN (SELECT id FROM Cast_skladby)
      )LEFT JOIN Autorstvi USING (id)
       LEFT JOIN Skladatel USING(jmeno, prijmeni); 
 

 -- Program koncertu 18. 1. 2005.

ALTER Session SET NLS_DATE_FORMAT='DD.MM.YYYY';
SELECT jmeno, prijmeni, nazev 
FROM Skladatel 
     JOIN Autorstvi using (jmeno, prijmeni)
     JOIN Skladba using (id)
     JOIN Cast_programu using (id)
     JOIN Koncert using (misto, cas)
WHERE TRUNC(cas)=TRUNC(TO_DATE('18.01.2005')); 

 

 -- Seznam zpěváků se 100% docházkou.

SELECT Zpevak.* 
FROM Zpevak JOIN (
         SELECT DISTINCT RC FROM Ucast
         MINUS
         SELECT DISTINCT RC FROM Ucast WHERE pritomen=0
          ) Vzorni ON Zpevak.RC=Vzorni.RC; 
 

 -- Stejný dotaz jako předchozí, tentokrát řešený poddotazem.

SELECT * 
FROM Zpevak 
WHERE RC NOT IN (
     SELECT RC FROM Ucast WHERE pritomen=0
      );
 

 -- Opět stejný dotaz řešený vztaženým poddotazem.

SELECT * 
FROM Zpevak 
WHERE NOT EXISTS (
        SELECT * FROM Ucast WHERE pritomen=0 AND Ucast.RC=Zpevak.RC
         );
 

 -- Seznam zpěváků, kteří nemají noty na Čtyři písně o Marii.

SELECT jmeno, prijmeni FROM Zpevak
 MINUS
SELECT jmeno, prijmeni FROM Zpevak
     JOIN Zapujcka_not using (RC)
     JOIN Publikace using (kat_cislo)
     JOIN Zarazeni using (kat_cislo)
     JOIN Skladba using (id)
WHERE nazev='Čtyři písně o Marii'; 
 

 -- Skladatelé, kteří jsou autorem nějaké skladby a zároveň upravovatelem nějaké skladby.

SELECT jmeno, prijmeni FROM Skladatel JOIN Autorstvi using (jmeno, prijmeni) WHERE je_uprava=0
 INTERSECT
SELECT jmeno, prijmeni FROM Skladatel JOIN Autorstvi using (jmeno, prijmeni) WHERE je_uprava<>0;

 

 -- Seznam publikací, od kterých je v inventáři méně než pět exemplářů. První realizaci můžeme zjednodušit tak, vybereme ty publikace, kde existují jen exempláře s pořadovými čísly < 5.

SELECT * 
FROM Publikace A 
WHERE NOT EXISTS (
      SELECT * FROM Vytisk
      WHERE A.kat_cislo=Vytisk.kat_cislo AND cislo_exemplare >= 5);
 

 -- Stejný dotaz s použitím agregace, navíc bez výše použitého zjednodušeni.

SELECT * 
FROM Publikace
WHERE 5 > (SELECT COUNT(*) FROM Vytisk
            WHERE Publikace.kat_cislo=Vytisk.kat_cislo);
 

 -- Seznam publikací, které obsahují více než jednu skladbu.

SELECT * 
FROM Publikace NATURAL JOIN (
      SELECT DISTINCT A.kat_cislo 
      FROM Zarazeni A JOIN Zarazeni B 
           ON A.kat_cislo=B.kat_cislo AND A.id < B.id
);
 

 -- Stejný dotaz přehledněji s agregací.

SELECT * 
FROM Publikace 
WHERE 1 < (
            SELECT COUNT(*) FROM Zarazeni
            WHERE Zarazeni.kat_cislo=Publikace.kat_cislo
      ); 
 

 -- Seznam zpěváků, kteří nikdy nezpívali žádnou skladbu Jana Hanuše.

SELECT jmeno, prijmeni 
FROM Zpevak 
WHERE RC NOT IN (
      SELECT RC FROM Ucast 
      WHERE (misto, cas) IN (
         SELECT misto, cas
         FROM Skladatel
              JOIN Autorstvi using (jmeno, prijmeni)
              JOIN Skladba using (id)
              JOIN Cast_programu using (id)
              JOIN Udalost using (misto, cas)
         WHERE jmeno='Jan' AND prijmeni LIKE 'Hanuš'
         )
      AND pritomen <> 0
      );
 

 -- Seznam všech koncertů a generálek.

SELECT cas, misto, 'Koncert' AS druh FROM Koncert
 UNION
SELECT cas, misto, 'Generálka' AS druh FROM Zkouska WHERE je_generalka<>0
ORDER BY cas;
 

 -- Seznam koncertů, na nichž byly provedeny všechny skladby B. Martinů na repertoáru.

SELECT misto, cas FROM Koncert
 MINUS
SELECT misto, cas 
FROM (SELECT id FROM Skladatel
           JOIN Autorstvi using (jmeno, prijmeni)
           JOIN Skladba using (id)
      WHERE jmeno='Bohuslav' AND prijmeni='Martinů'
      ) CROSS JOIN Koncert
WHERE (id, misto, cas) NOT IN (SELECT id, misto, cas
                               FROM Cast_programu); 
 

 -- Který zpěvák má největší z půjčených šatů?

SELECT jmeno, prijmeni, velikost 
FROM Zpevak
     JOIN Zapujcka_satu using (RC)
     JOIN Saty using (inv_cislo)
WHERE velikost=(SELECT MAX(velikost) FROM Saty NATURAL JOIN Zapujcka_satu); 
 

 -- Přehled účasti na zkouškách (v procentech).

SELECT jmeno, prijmeni, 
   CAST((SELECT COUNT(*) FROM Ucast NATURAL JOIN Zkouska
         WHERE Zpevak.RC=Ucast.RC AND pritomen<>0)
         AS REAL)
        /(SELECT COUNT(*) FROM Ucast NATURAL JOIN Zkouska
          WHERE Zpevak.RC=Ucast.RC
        )*100 || ' %' AS dochazka
FROM Zpevak ORDER BY prijmeni; 
 

 -- Seznam hlasových skupin: název, počet členů, jméno vedoucího.

SELECT  nazev_h,
  poc_clenu,
  jmeno || ' ' || prijmeni AS vedouci
FROM (
      SELECT zkratka, nazev_h, COUNT(RC) AS poc_clenu
      FROM Hlas LEFT JOIN Zpevak ON Hlas.zkratka=Zpevak.hlas
      GROUP BY zkratka, nazev_h
     )
     LEFT JOIN Vedeni USING(zkratka) LEFT JOIN Zpevak USING(RC); 
 

 -- Seznam míst, na kterých se koncertovalo vícekrát a vždy bylo přítomno více než 50 diváků.

SELECT misto, count(misto) AS pocet_koncertu, AVG(divaku) AS prum_divaku
FROM Koncert
WHERE divaku > 50 
GROUP BY misto
HAVING count(misto) > 1; 
 

 -- Skladatel nejvíce zastoupený na koncertech co do počtu skladeb.

--Využívám pohled rating_skladatelu:
SELECT jmeno, prijmeni, pocet 
FROM Rating_skladatelu
WHERE pocet = (SELECT MAX(pocet) FROM Rating_skladatelu); 
 

 -- Pro divácky nejúspěšnější koncert určit rozdíl ceny vstupného od průměru.

 SELECT (
        SELECT vstupne FROM Koncert
            WHERE divaku = (
                            SELECT max(divaku) FROM Koncert
                           )
       ) - (
        SELECT AVG(vstupne) FROM Koncert
       )
AS odchylka FROM Dual;

 

 -- Skladatelé, kteří se mohli během života setkat s Bohuslavem Martinů.

SELECT * FROM skladatel
 WHERE rok_narozeni < (SELECT rok_umrti FROM skladatel
                       WHERE jmeno='Bohuslav' AND prijmeni='Martinů')
       AND rok_umrti > (SELECT rok_narozeni FROM skladatel
                        WHERE jmeno='Bohuslav' AND prijmeni='Martinů');
 

 -- Seznam skladatelů, jejichž skladby byly na programu koncertů s nadprůměrnou návštěvou.

SELECT DISTINCT jmeno, prijmeni 
FROM skladatel
   JOIN autorstvi using (jmeno, prijmeni)
   JOIN skladba using (id)
   JOIN cast_programu using (id)
   JOIN koncert using (misto, cas)
WHERE divaku > (SELECT AVG(divaku) FROM koncert); 
 

 -- Koncert, na němž bylo uvedeno nejvíce různých skladeb.

SELECT * 
FROM (
   SELECT misto, cas, COUNT(*) AS pocet 
   FROM cast_programu NATURAL JOIN koncert 
   GROUP BY misto, cas
      ) WHERE pocet = (
          SELECT MAX(pocet) FROM (
               SELECT COUNT(*) AS pocet
               FROM cast_programu NATURAL JOIN koncert 
               GROUP BY misto, cas
              )
     ); 
 

 -- Přehled ošacení - všichni zpěváci a jejich šaty, zároveň všechny šaty a jejich držitelé.

SELECT
    COALESCE(prijmeni, 'VOLNÉ') AS drzitel,
    inv_cislo, velikost
FROM Zpevak LEFT JOIN Zapujcka_satu USING(RC)
     FULL JOIN Saty USING(inv_cislo); 
 

 -- Skladatelé a počet uvedení jejich skladeb

CREATE OR REPLACE VIEW rating_skladatelu AS 
SELECT jmeno, prijmeni, count(*) AS pocet 
FROM Skladatel
      JOIN Autorstvi using (jmeno, prijmeni)
      JOIN Skladba using (id)
      JOIN Cast_programu using (id)
      JOIN Koncert using (misto, cas) 
GROUP BY jmeno, prijmeni;

select * from rating_skladatelu;
 

 -- Vytvoříme redundantní tabulku ucasti_zpeváků, kde budou předpočítané hodnoty). Tuto tabulku naplníme daty. Atribut koef vyjadruje koeficient (ponechme stranou, co to znamená).

drop table ucasti_zpevaku;

create table ucasti_zpevaku (
  rc number(10),
  jmeno varchar2(30),
  prijmeni varchar2(30),
  zkousek number(4),
  koncertu number(4),
  koef number(1));


insert into ucasti_zpevaku
  SELECT rc, jmeno, prijmeni, 
     (SELECT COUNT(*) FROM Ucast NATURAL JOIN Zkouska
           WHERE Zpevak.RC=Ucast.RC AND pritomen<>0)
            as zkousek, 
     (SELECT COUNT(*) FROM Ucast NATURAL JOIN Koncert
            WHERE Zpevak.RC=Ucast.RC AND pritomen<>0)
            as koncertu,
      0 as koef
  FROM Zpevak ORDER BY prijmeni;

commit;

select * from ucasti_zpevaku;
 

drop table ucasti_zpevaku;

create table UCASTI_ZPEVAKU as
  SELECT rc, jmeno, prijmeni, 
     (SELECT COUNT(*) FROM Ucast NATURAL JOIN Zkouska
           WHERE Zpevak.RC=Ucast.RC AND pritomen<>0)
            as zkousek, 
     (SELECT COUNT(*) FROM Ucast NATURAL JOIN Koncert
            WHERE Zpevak.RC=Ucast.RC AND pritomen<>0)
            as koncertu,
      0 as koef
   FROM Zpevak ORDER BY prijmeni;

select * from ucasti_zpevaku;
 

 -- Zpěvákům, kteří zpívají alt nastavíme v relaci ucasti_zpevaku hodnotu atributu koef tak, že vyjadřuje počet jimi zapůjčených výtisků not.

update ucasti_zpevaku u
set koef = 
    (select count(*) 
     from zapujcka_not z 
     where u.rc = z.rc)
where u.rc in (select rc from zpevak where hlas = 'A');

commit;
 

 -- Z tabulky ucasti_zpevaku vymažeme ty zpěváky, kteří zpívají basy.

delete from ucasti_zpevaku
where rc in (select rc from zpevak where hlas = 'B');

commit;
 

set markup html off
spool off
