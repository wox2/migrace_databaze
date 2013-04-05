/* 1) Turnaje, ktere se konaly v Praze */
SELECT * 
FROM Turnaj
WHERE misto_konani='Praha';
 
/* 2) Jmena a id balicku, ktere jsou modrocerne */ 
SELECT jmeno, id 
FROM Deck 
WHERE (barvy = 'UB');

/* 3) jmena a id balicku, ktere nejsou modrocerne*/
(SELECT jmeno, id 
FROM Deck)
MINUS
(SELECT jmeno, id 
FROM Deck 
WHERE barvy = 'UB');

/* 4) to same pomoci not in */
SELECT jmeno, id 
FROM Deck
WHERE id NOT IN (
select id 
FROM deck WHERE barvy = 'UB');

/* 5) to same jen jako podminka */
SELECT jmeno, id 
FROM Deck
WHERE (
barvy != 'UB');


/*6) Pocty vsech karet v md balicku s id 1*/
SELECT jmeno, pocet
FROM md_obsahuje JOIN mtg_karta ON md_obsahuje.karta_md_id = mtg_karta.id 
WHERE (md_obsahuje.deck_md_id = 1)
ORDER BY pocet;

/* 7) Pocty vsech karet v sb balicku s id 1*/
SELECT jmeno, pocet
FROM (sb_obsahuje JOIN mtg_karta ON sb_obsahuje.karta_sb_id = mtg_karta.id )
WHERE (sb_obsahuje.deck_sb_id = 1)
ORDER BY pocet;


/* 8) Pocty a jmena vsech karet v sideboardu a maindecku balicku s id 1(dotaz neni uplne 
optimalni - z reseni neni patrne, ktere radky jsou v sideboardu a ktere v maindecku) */
SELECT jmeno, pocet
FROM (md_obsahuje JOIN mtg_karta ON md_obsahuje.karta_md_id = mtg_karta.id )
WHERE (md_obsahuje.deck_md_id = 1)
UNION
SELECT jmeno, pocet
FROM (sb_obsahuje JOIN mtg_karta on sb_obsahuje.karta_sb_id = mtg_karta.id )
WHERE (sb_obsahuje.deck_sb_id = 1);

/* 9) Kombinace barev, ktere zastupuji dva a vice balicku */
SELECT DISTINCT d1.barvy
FROM deck d1 CROSS JOIN deck d2
WHERE (d1.id != d2.id AND d1.barvy = d2.barvy);

/* 10) Karty, ktere jsou v side i v maindecku s jejich poctem a nazvem*/
SELECT jmeno, pocet_md, pocet_sb 
FROM 
(
    SELECT pocet as pocet_md, karta_md_id as karta, deck_md_id as deck
    FROM md_obsahuje
)
NATURAL JOIN 
(
  SELECT pocet as pocet_sb, karta_sb_id as karta, deck_sb_id as deck
  FROM sb_obsahuje 
)
JOIN mtg_karta ON id = karta ;

/* 11) Vsechny karty s jejich poctem vyskytu v maindecich - karty, ktere se vyskytuji 0 x budou mit vyskyt NULL*/
SELECT * 
FROM mtg_karta 
LEFT OUTER JOIN md_obsahuje ON md_obsahuje.karta_md_id = mtg_karta.id 
ORDER BY jmeno;

/* 12) Jmena, pocet a pocet karet v side u karet, ktere jsou v sideboardu i maindecku*/
SELECT jmeno, pocet, sb_pocet,  deck 
FROM 
(
    SELECT md.karta_md_id, karta_sb_id,  md.pocet, sb.pocet as sb_pocet, deck_sb_id as deck
    FROM md_obsahuje md CROSS JOIN sb_obsahuje sb
    WHERE (md.karta_md_id = sb.karta_sb_id AND md.deck_md_id = sb.deck_sb_id)
) 
JOIN mtg_karta k ON k.id=karta_md_id ;

/* 13) Id decku s jmenem Grimlong*/ 
SELECT max(id) FROM deck WHERE jmeno='Grimlong';

/* 14) Jmena karet, ktere jsou v maindecku balicku s jmenem Grimlong (v pripade vice takovych balicku ten s nejmensim id)*/
SELECT DISTINCT jmeno 
FROM 
(
    SELECT karta_md_id AS karta 
    FROM md_obsahuje 
    WHERE deck_md_id = (
        SELECT min(id) 
         FROM deck 
        WHERE jmeno='Grimlong'
      )
)
JOIN mtg_karta ON karta = id
ORDER BY jmeno;


/*  15) Jmena karet v maindecku i sideboardu jednoho balicku*/
select jmeno from 
(
    SELECT karta_md_id as karta, deck_md_id as deck 
    FROM md_obsahuje 
      INTERSECT
    SELECT karta_sb_id as karta, deck_sb_id as deck 
    FROM sb_obsahuje
)
JOIN mtg_karta ON  karta = id;

/* 16) to same pres CROSS JOIN */
SELECT jmeno FROM 
(
    SELECT md.karta_md_id, karta_sb_id,  md.pocet, sb.pocet AS sb_pocet, deck_sb_id AS deck
    FROM md_obsahuje md CROSS JOIN sb_obsahuje sb
    WHERE (md.karta_md_id = sb.karta_sb_id AND md.deck_md_id = sb.deck_sb_id)
) 
JOIN mtg_karta k ON k.id=karta_md_id ;

/* 17) to same pres NATURAL JOIN */
SELECT jmeno FROM 
(
    (
      SELECT karta_md_id as karta, deck_md_id AS deck 
      FROM md_obsahuje
    ) 
        NATURAL JOIN
    (
      SELECT karta_sb_id as karta, deck_sb_id AS deck
      FROM sb_obsahuje
    )
)
JOIN mtg_karta k ON k.id=karta;
 
/* 18) */
update md_obsahuje 
set pocet = 
    4
where (karta_md_id = select (id) from mtg_karta k where k.jmeno = 'Force of Will');

commit;
 
/* 19) Vlozi do databaze Cernou kartu s nazvem Demonic Tutor, typem sorcery, 
casting costem 1B, id 15 

***barva.nazev , typ.nazev jsou unique. Pokud by nebyly, musi se pouzit agregacni 
funkce max nebo min nebo jinak osetrit, ze vybrane id bude jedno.*** */

INSERT INTO mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
VALUES (
          (
            SELECT (ID) FROM barva WHERE (nazev='black')
          ),
          (
            SELECT (id) FROM typ WHERE (nazev='sorcery'))
            , '1B', 'Demonic Tutor',15); 

/* 20) karty, ktere nejsou v maindecku balicku*/
SELECT * 
FROM mtg_karta 
WHERE NOT EXISTS 
(
  SELECT * 
  FROM md_obsahuje 
  WHERE mtg_karta.id=md_obsahuje.karta_md_id
)
order by jmeno;


/* 21) smaze cerne karty*/
DELETE FROM mtg_karta 
WHERE barva_kart_id = 
(
      SELECT id 
      FROM barva 
      WHERE nazev = 'black'
);


/* 22) Seznam karet, ktere se vyskytuji v md, s poctem balicku, v nichz se vyskytuji v maindecku*/
SELECT jmeno, COUNT(jmeno) AS pocet_balicku
FROM (md_obsahuje JOIN mtg_karta ON mtg_karta.id = karta_md_id)
GROUP BY jmeno;

/* 23) Seznam karet s pocet hranych kopii karty v maindecku*/
SELECT jmeno, sum(COALESCE(pocet,0)) AS pocet_kopii
FROM 
(
      mtg_karta 
      LEFT OUTER JOIN md_obsahuje ON mtg_karta.id = karta_md_id
)
GROUP BY jmeno;


/* 24) Cerne karty s poctem kopii v maindeccich balicku 2 a vice */
SELECT jmeno, SUM (COALESCE(pocet,0)) AS pocet
FROM
(
mtg_karta 
      LEFT OUTER JOIN md_obsahuje ON mtg_karta.id = karta_md_id
)
WHERE barva_kart_id = (SELECT id FROM barva WHERE nazev='black')
GROUP BY jmeno
HAVING SUM (COALESCE(pocet,0)) >1
ORDER by jmeno;

/* 25) Cerny enchantmenty s Cacting_Costem*/
CREATE OR REPLACE VIEW cerny_enchantmenty AS 
SELECT JMENO, id, CASTING_COST FROM (SELECT barva.ID AS barva_id, barva.nazev AS barva_nazev FROM barva) JOIN 
MTG_KARTA ON (BARVA_ID=BARVA_KART_ID)JOIN (SELECT id AS typ_id, nazev AS TYP_NAZEV FROM TYP) ON 
(typ_karta_id = typ_id) WHERE (barva_nazev = 'black' AND TYP_NAZEV='enchantment') ;

select * from cerny_enchantmenty;

/* 26) Karty s CC jako ma enchantment s nejnizsim id */
SELECT * from MTG_karta WHERE CASTING_COST = (
SELECT CASTING_COST FROM cerny_enchantmenty WHERE id=(SELECT min(id) from cerny_enchantmenty ));

/* 27) Turnaje, na kterych hraly vsichni hraci*/
SELECT id as turnaj_id FROM turnaj minus
SELECT turnaj_id FROM (
SELECT osoba_id, id as turnaj_id FROM turnaj CROSS JOIN HRAC
      MINUS 
    SELECT hrac_id AS osoba_id,turnaj_id FROM Hral_deck_na_turnaji );

/*28) Nazvy formatu, jez jsou hrany na turnajich*/ 
SELECT DISTINCT format_nazev FROM TURNAJ NATURAL JOIN (SELECT id as form_turna_id, nazev as format_nazev FROM FORMAT); 

/*29) */
select id FROM cerny_enchantmenty;

/* 30) */
SELECT * FROM ZAPAS WHERE (vysl_zapas_hrac_1_vyhral_her = 1 AND vysl_zapas_hrac_2_vyhral_her = 1);

/* 31) */
SELECT count(*) FROM md_obsahuje where (karta_md_id=(SELECT id FROM mtg_karta WHERE jmeno = 'Brainstorm')); 

/* 32) Hrace s jmenem Martin*/

SELECT *
FROM OSOBA JOIN HRAC ON (id=osoba_id) 
where jmeno ='Martin';

/* 33) balicky typu aggro*/
SELECT * FROM deck JOIN druh ON (druh.id=druh_deck_id) where nazev = 'aggro';

/* 34) */
UPDATE osoba set jmeno = 'Michal' WHERE jmeno = 'Pavel' AND prijmeni = 'Valenta';

/* 35) */
INSERT INTO OSOBA (id, prijmeni, jmeno)values ((SELECT max (id) FROM OSOBA)+1, 'Klaus', 'SANTA');

select * from osoba;