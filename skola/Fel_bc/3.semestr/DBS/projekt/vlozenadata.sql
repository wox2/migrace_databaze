insert into vysledek (hrac_1_vyhral_her, hrac_2_vyhral_her) values (1,2);
insert into vysledek (hrac_1_vyhral_her, hrac_2_vyhral_her) values (0,2);
insert into vysledek (hrac_1_vyhral_her, hrac_2_vyhral_her) values (0,1);
insert into vysledek (hrac_1_vyhral_her, hrac_2_vyhral_her) values (0,0);
insert into vysledek (hrac_1_vyhral_her, hrac_2_vyhral_her) values (1,1);
insert into vysledek (hrac_1_vyhral_her, hrac_2_vyhral_her) values (1,0);
insert into vysledek (hrac_1_vyhral_her, hrac_2_vyhral_her) values (2,0);
insert into vysledek (hrac_1_vyhral_her, hrac_2_vyhral_her) values (2,1);

insert into typ(id, nazev) values (1, 'artifact');
insert into typ(id, nazev) values (2, 'creature');
insert into typ(id, nazev) values (3, 'enchantment');
insert into typ(id, nazev) values (4, 'land');
insert into typ(id, nazev) values (5, 'instant');
insert into typ(id, nazev) values (6, 'sorcery');
insert into typ(id, nazev) values (7, 'planeswalker');
insert into typ(id, nazev) values (8, 'artifact creature');
insert into typ(id, nazev) values (9, 'enchantment-aura');



insert into druh (id, nazev ) values (1, 'control');
insert into druh (id, nazev ) values (2, 'aggro');
insert into druh (id, nazev ) values (3, 'combo');
insert into druh (id, nazev ) values (4, 'aggro-control');
insert into druh (id, nazev ) values (5, 'combo-control');
insert into druh (id, nazev ) values (6, 'aggro-combo');
insert into druh (id, nazev ) values (7, 'combo-aggro-control');
insert into druh (id, nazev ) values (8, 'prison');

 
insert into osoba (id, jmeno, prijmeni) values (1,'Martin', 'Lukes');
insert into osoba (id, jmeno, prijmeni) values (2,'Michal', 'Hrebejk');
insert into osoba (id, jmeno, prijmeni) values (3,'Lukas', 'Kraft');
insert into osoba (id, jmeno, prijmeni) values (4,'David', 'Dobis');
insert into osoba (id, jmeno, prijmeni) values (5,'Karol', 'Cilik');
insert into osoba (id, jmeno, prijmeni) values (6,'Patrik', 'Adler');
insert into osoba (id, jmeno, prijmeni) values (7,'Tomas', 'Langer');
insert into osoba (id, jmeno, prijmeni) values (8,'Jan', 'Zuskac');

insert into hrac (osoba_id) values (1);
insert into hrac (osoba_id) values (2);
insert into hrac (osoba_id) values (3);
insert into hrac (osoba_id) values (4);
insert into hrac (osoba_id) values (5);
insert into hrac (osoba_id) values (6);
insert into hrac (osoba_id) values (7);
insert into hrac (osoba_id) values (8);



insert into osoba(id, prijmeni, jmeno) values (9, 'Arcimovic', 'Martin');
insert into osoba(id, prijmeni, jmeno) values (10, 'Kosek', 'Milan');
insert into osoba(id, prijmeni, jmeno) values (11, 'Hrebejk', 'Michal');

insert into rozhodci(uroven , osoba_id) values (3,9);
insert into rozhodci(uroven , osoba_id) values (1,10);
insert into rozhodci(uroven , osoba_id) values (2,11);


insert into deck ( id, druh_deck_id, jmeno, barvy) values (1, 2, 'KEEPER', 'UBRGW' ); /* done - up to 42  created */ 
insert into deck ( id, druh_deck_id, jmeno, barvy) values (2, 1, 'fritka', 'R' ); /* done - 68 - 83 simmiant fritka v.9.mwDeck created */
insert into deck ( id, druh_deck_id, jmeno, barvy) values (3, 3, 'fish', 'UBG' ); /* done 83 - 100 http://www.deckcheck.net/deck.php?id=28707 created */
insert into deck ( id, druh_deck_id, jmeno, barvy) values (4, 4, 'Grimlong', 'UBR' ); /* done 100 - D:\Program files\Magic Workstation\Vintage111\Longs\My Grimlong PBR v.1.mwDeck created */
insert into deck ( id, druh_deck_id, jmeno, barvy) values (5, 5, 'Meandeck Gifts', 'UBR' ); /*done 42  -  D:\Program files\Magic Workstation\Vintage111\Gifts-banned\gifts v. 8.3.mwDeck  67 created*/
insert into deck ( id, druh_deck_id, jmeno, barvy) values (6, 6, 'Stax', 'R' );
insert into deck ( id, druh_deck_id, jmeno, barvy) values (7, 7, 'Workshop Aggro', 'R' );
insert into deck ( id, druh_deck_id, jmeno, barvy) values (8, 6, 'Stax', 'UBRGW' );
insert into deck ( id, druh_deck_id, jmeno, barvy) values (9, 4, 'Ad Nauseam', 'UB' );
insert into deck ( id, druh_deck_id, jmeno, barvy) values (10, 4, 'Grimlong', 'UBW' );
insert into deck ( id, druh_deck_id, jmeno, barvy) values (11, 4, 'Drain Tendrils', 'UB' );

insert into system (nazev, id, popis) values ('svycarsky', 1, 'Na zacatku kola jsou sparovani (paired - vylosovani do dvojic hraci s podobnym nebo stejnym poctem bodu) vitez dostane 3 body, porazeny nula, v pripade remizi oba po jednom. V dalsim kole se pokracuje od prvniho kroku.');
insert into system (nazev, id, popis) values ('single-elimination', 2, 'Standartni pavouk. V kazdem kole rojde dale jen polovina hracu, zbyly jsou vyrazeni a turnaj pro ne konci. Vitezi neporazeny hrac.');
insert into system (nazev, id, popis) values ('double-elimination', 3, 'V prvnim kole se hraje standartni hra - v druhem se rozdeluje herni pole na dva pavouky - v jednom jsou 1 x porazeni, v druhem 0 x porazeni. Po prohre se 0 x porazeni presunuji do pavouka pro 1 x porazene a 1 x porazeni po prohre konci v turnaji.');

insert into barva (id, nazev) values (1, 'black');
insert into barva (id, nazev) values (2, 'blue');
insert into barva (id, nazev) values (3, 'white');
insert into barva (id, nazev) values (4, 'green');
insert into barva (id, nazev) values (5, 'red');
insert into barva (id, nazev) values (6, 'multicolor');
insert into barva (id, nazev) values (7, 'colorless');

insert into format (id, nazev) values (1 , 'Vintage');
insert into format (id, nazev) values (2 , 'Legacy');
insert into format (id, nazev) values (3 , 'Extended');

insert into turnaj (syst_turna_id, hlroz_turn_osoba_id, form_turna_id,
id,  top8,  vstupne, dolarova_dotace, jina_dotace, 
pocet_ucastniku, nazev, datum, misto_konani) 
values ( (select (id) from system where (nazev='svycarsky')) , 9, (select (id) from format where (nazev='Vintage')), 
1 ,0 , 5, 0, 'duallandy',
8, 'Patecni Vintage', to_date('2009/12/05', 'yyyy/mm/dd'), 'Praha');

insert into turnaj (syst_turna_id, hlroz_turn_osoba_id, form_turna_id,
id,  top8,  vstupne, dolarova_dotace, jina_dotace, 
pocet_ucastniku, nazev, datum, misto_konani) 
values ( (select (id) from system where (nazev='single-elimination')) , 10, (select (id) from format where (nazev='Vintage')), 
2 ,0 , 6, 0, 'duallandy + P9', 
8, 'Czech Vintage Series', to_date('2010/03/05', 'yyyy/mm/dd'), 'Brno');

insert into turnaj (syst_turna_id, hlroz_turn_osoba_id, form_turna_id,
id,  top8,  vstupne, dolarova_dotace, jina_dotace, 
pocet_ucastniku, nazev, datum, misto_konani) 
values ( (select (id) from system where (nazev='single-elimination')) , 10, (select (id) from format where (nazev='Vintage')), 
3 ,0 , 10, 0, 'duallandy + P9', 
8, 'Czech Vintage Series', to_date('2010/06/06', 'yyyy/mm/dd'), 'Praha');


select * from hral_deck_na_turnaji;

insert into hral_deck_na_turnaji (deck_hdnt_id, hrac_osoba_id, turnaj_id) 
values (1, 1,1);

insert into hral_deck_na_turnaji (deck_hdnt_id, hrac_osoba_id, turnaj_id) 
values (2, 2,1);

insert into hral_deck_na_turnaji (deck_hdnt_id, hrac_osoba_id, turnaj_id) 
values (1, 3,1);

insert into hral_deck_na_turnaji (deck_hdnt_id, hrac_osoba_id, turnaj_id) 
values (1, 4,1);

insert into hral_deck_na_turnaji (deck_hdnt_id, hrac_osoba_id, turnaj_id) 
values (3, 5,1);

insert into hral_deck_na_turnaji (deck_hdnt_id, hrac_osoba_id, turnaj_id) 
values (4, 6,1);

insert into hral_deck_na_turnaji (deck_hdnt_id, hrac_osoba_id, turnaj_id) 
values (5, 7,1);

insert into hral_deck_na_turnaji (deck_hdnt_id, hrac_osoba_id, turnaj_id) 
values (5, 8,1);


insert into hral_deck_na_turnaji (deck_hdnt_id, hrac_osoba_id, turnaj_id) 
values (1, 1,2);

insert into hral_deck_na_turnaji (deck_hdnt_id, hrac_osoba_id, turnaj_id) 
values (1, 2,2);

insert into hral_deck_na_turnaji (deck_hdnt_id, hrac_osoba_id, turnaj_id) 
values (1, 3,2);

insert into hral_deck_na_turnaji (deck_hdnt_id, hrac_osoba_id, turnaj_id) 
values (4, 4,2);

insert into hral_deck_na_turnaji (deck_hdnt_id, hrac_osoba_id, turnaj_id) 
values (5, 5,2);

insert into hral_deck_na_turnaji (deck_hdnt_id, hrac_osoba_id, turnaj_id) 
values (1, 6,2);

insert into hral_deck_na_turnaji (deck_hdnt_id, hrac_osoba_id, turnaj_id) 
values (1, 7,2);

insert into hral_deck_na_turnaji (deck_hdnt_id, hrac_osoba_id, turnaj_id) 
values (1, 8,2);

insert into hral_deck_na_turnaji (deck_hdnt_id, hrac_osoba_id, turnaj_id) 
values (5, 1,3);

insert into hral_deck_na_turnaji (deck_hdnt_id, hrac_osoba_id, turnaj_id) 
values (2, 2, 3);

insert into hral_deck_na_turnaji (deck_hdnt_id, hrac_osoba_id, turnaj_id) 
values (2, 3, 3);

insert into hral_deck_na_turnaji (deck_hdnt_id, hrac_osoba_id, turnaj_id) 
values (3, 4, 3);

insert into hral_deck_na_turnaji (deck_hdnt_id, hrac_osoba_id, turnaj_id) 
values (2, 5, 3);

insert into hral_deck_na_turnaji (deck_hdnt_id, hrac_osoba_id, turnaj_id) 
values (2, 6, 3);

insert into hral_deck_na_turnaji (deck_hdnt_id, hrac_osoba_id, turnaj_id) 
values (4, 7, 3);

insert into hral_deck_na_turnaji (deck_hdnt_id, hrac_osoba_id, turnaj_id) 
values (5, 8, 3);

insert into mtg_karta ( barva_kart_id , typ_karta_id , casting_cost , jmeno , id)
values ( (select (id)from barva where ( nazev = 'blue' )), (select (id) from typ where (nazev='instant')), 
'3UU', 'Force of Will',1);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='blue')), (select  (id) from typ where (nazev='instant')), 
'U', 'Ancestral Recall',2);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='colorless')), (select (id) from typ where (nazev='artifact creature')), 
'11', 'Darksteel Colossus',3);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='white')), (select (id) from typ where (nazev='creature')), 
'3WW', 'Exalted Angel',4);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='blue')), (select (id) from typ where (nazev='instant')), 
'U', 'Brainstorm',5);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='blue')), (select (id) from typ where (nazev='instant')), 
'2U', 'Cunning Wish',6);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='blue')), (select (id) from typ where (nazev='instant')), 
'3U', 'Fact or Fiction',7);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='multicolor')), (select (id) from typ where (nazev='instant')), 
'1U/1R', 'Fire/Ice',8);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='blue')), (select (id) from typ where (nazev='instant')), 
'UU', 'Mana Drain',9);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='blue')), (select (id) from typ where (nazev='instant')), 
'U', 'Mystical Tutor',10);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='black')), (select (id) from typ where (nazev='instant')), 
'XB', 'Skeletal Scrying',11);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='white')), (select (id) from typ where (nazev='instant')), 
'W', 'Swords to Plowshares',12);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='white')), (select (id) from typ where (nazev='sorcery')), 
'1W', 'Balance',13);


insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='white')), (select (id) from typ where (nazev='sorcery')), 
'2WWXX', 'Decree of Justice',14);


insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='black')), (select (id) from typ where (nazev='sorcery')), 
'1B', 'Demonic Tutor',15);


insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='black')), (select (id) from typ where (nazev='sorcery')), 
'XB', 'Mind Twist',16);


insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='blue')), (select (id) from typ where (nazev='sorcery')), 
'1U', 'Time Walk',17);


insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='blue')), (select (id) from typ where (nazev='sorcery')), 
'2U', 'Tinker',18);


insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='black')), (select (id) from typ where (nazev='sorcery')), 
'2B', 'Yawghmoth"s Will',19);


insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='colorless')), (select (id) from typ where (nazev='artifact')), 
'0', 'Black Lotus',20);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='colorless')), (select (id) from typ where (nazev='artifact')), 
'3', 'Crucible of Worlds',21);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='colorless')), (select (id) from typ where (nazev='artifact')), 
'0', 'Mox Emerald',22);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='colorless')), (select (id) from typ where (nazev='artifact')), 
'0', 'Mox Pearl',23);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='colorless')), (select (id) from typ where (nazev='artifact')), 
'0', 'Mox Sapphire',24);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='colorless')), (select (id) from typ where (nazev='artifact')), 
'0', 'Mox Jet',25);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='colorless')), (select (id) from typ where (nazev='artifact')), 
'0', 'Mox Ruby',26);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='colorless')), (select (id) from typ where (nazev='artifact')), 
'1', 'Sol Ring',27);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='colorless')), (select (id) from typ where (nazev='land')), 
'0', 'City of Brass',28);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='colorless')), (select (id) from typ where (nazev='land')), 
'0', 'Flooded Strand',29);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='colorless')), (select (id) from typ where (nazev='land')), 
'0', 'Library of Alexandria',30);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='colorless')), (select (id) from typ where (nazev='land')), 
'0', 'Strip Mine',31);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='colorless')), (select (id) from typ where (nazev='land')), 
'0', 'Tundra',32);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='colorless')), (select (id) from typ where (nazev='land')), 
'0', 'Underground Sea',33);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='colorless')), (select (id) from typ where (nazev='land')), 
'0', 'Volcanic Island',34);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='colorless')), (select (id) from typ where (nazev='land')), 
'0', 'Wasteland',35);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='blue')), (select (id) from typ where (nazev='creature')), 
'1UU', 'Barrin, Master Wizard',36);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='red')), (select (id) from typ where (nazev='creature')), 
'R', 'Gorilla Shaman',37);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='white')), (select (id) from typ where (nazev='instant')), 
'1W', 'Disenchant',38);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='blue')), (select (id) from typ where (nazev='instant')), 
'U', 'Hydroblast',39);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='red')), (select (id) from typ where (nazev='instant')), 
'R', 'Pyroblast',40);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='red')), (select (id) from typ where (nazev='instant')), 
'2R', 'Rack and Ruin',41);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='colorless')), (select (id) from typ where (nazev='artifact')), 
'X', 'Engineered Explosives',42);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='colorless')), (select (id) from typ where (nazev='land')), 
'0', 'Island',44);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='colorless')), (select (id) from typ where (nazev='land')), 
'0', 'Polluted Delta',45);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='colorless')), (select (id) from typ where (nazev='land')), 
'0', 'Tolarian Academy',46);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='blue')), (select (id) from typ where (nazev='instant')), 
'3U', 'Gifts Ungiven',47);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='blue')), (select (id) from typ where (nazev='instant')), 
'U', 'Chain of Vapor',48);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='colorless')), (select (id) from typ where (nazev='artifact')), 
'0', 'Lotus Petal',49);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='colorless')), (select (id) from typ where (nazev='artifact')), 
'0', 'Mana Crypt',50);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='colorless')), (select (id) from typ where (nazev='artifact')), 
'1', 'Mana Vault',51);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='blue')), (select (id) from typ where (nazev='sorcery')), 
'1U', 'Merchant Scroll',52);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='red')), (select (id) from typ where (nazev='sorcery')), 
'1R', 'Recoup',53);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='black')), (select (id) from typ where (nazev='instant')), 
'B', 'Vampiric Tutor',54);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='blue')), (select (id) from typ where (nazev='instant')), 
'XU', 'Repeal',55);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='black')), (select (id) from typ where (nazev='sorcery')), 
'2BB', 'Tendrils of Agony',56);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='black')), (select (id) from typ where (nazev='instant')), 
'B', 'Dark Ritual',57);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='red')), (select (id) from typ where (nazev='sorcery')), 
'3R', 'Empty the Warrens',58);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='blue')), (select (id) from typ where (nazev='instant')), 
'3UU', 'Misdirection',59);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='blue')), (select (id) from typ where (nazev='sorcery')), 
'3U', 'Deep Analysis',60);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='blue')), (select (id) from typ where (nazev='instant')), 
'2U', 'Rebuild',61);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='red')), (select (id) from typ where (nazev='sorcery')), 
'1R', 'Pyroclasm',62);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='red')), (select (id) from typ where (nazev='instant')), 
'R', 'Red Elemental Blast',63);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='black')), (select (id) from typ where (nazev='sorcery')), 
'B', 'Duress',64);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='colorless')), (select (id) from typ where (nazev='artifact')), 
'1', 'Pithing Needle',65);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='blue')), (select (id) from typ where (nazev='instant')), 
'1U', 'Hurkyl"s Recall',66);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='blue')), (select (id) from typ where (nazev='instant')), 
'1U', 'Brain Freeze',67);


insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='colorless')), (select (id) from typ where (nazev='land')), 
'0', 'Mountain',68);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='colorless')), (select (id) from typ where (nazev='land')), 
'0', 'Mishra"s Factory', 69 );

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='red')), (select (id) from typ where (nazev='creature')), 
'2R', 'Simian Spirit Guide',70);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='red')), (select (id) from typ where (nazev='creature')), 
'1R', 'Hellspark Elemental',71);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='red')), (select (id) from typ where (nazev='instant')), 
'4RR', 'Fireblast',72);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='red')), (select (id) from typ where (nazev='enchantment')), 
'R', 'Genju of the Spires',73);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='red')), (select (id) from typ where (nazev='enchantment')), 
'1R', 'Pyrostatic Pillar',74);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='colorless')), (select (id) from typ where (nazev='artifact')), 
'2', 'Null Rod',75);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='red')), (select (id) from typ where (nazev='instant')), 
'R', 'Lightning Bolt',76);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='red')), (select (id) from typ where (nazev='sorcery')), 
'R', 'Chain Lightning',77);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='colorless')), (select (id) from typ where (nazev='artifact')), 
'2', 'Ankh of Mishra',78);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='colorless')), (select (id) from typ where (nazev='artifact')), 
'XX', 'Chalice of the Void',79);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='red')), (select (id) from typ where (nazev='creature')), 
'9R', 'Greater Gargadon',80);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='colorless')), (select (id) from typ where (nazev='artifact')), 
'1', 'Relic of Progenitus',81);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='colorless')), (select (id) from typ where (nazev='artifact')), 
'1', 'Pithing Needle',82);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='red')), (select (id) from typ where (nazev='sorcery')), 
'R', 'Shattering Spree',83);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='black')), (select (id) from typ where (nazev='creature')), 
'1B', 'Dark Confidant',84);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='multicolor')), (select (id) from typ where (nazev='creature')), 
'1UB', 'Dimir Cutpurse',85);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='green')), (select (id) from typ where (nazev='creature')), 
'1G', 'Tarmogoyf',86);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='black')), (select (id) from typ where (nazev='creature')), 
'6BB', 'Toombstalker',87);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='black')), (select (id) from typ where (nazev='instant')), 
'1B', 'Diabolic Edict',88);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='black')), (select (id) from typ where (nazev='sorcery')), 
'BB', 'Hymn to tourach',90);


insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='black')), (select (id) from typ where (nazev='sorcery')), 
'B', 'Thoughtseize',89);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='colorless')), (select (id) from typ where (nazev='land')), 
'0', 'Bayou',91);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='colorless')), (select (id) from typ where (nazev='land')), 
'0', 'Bloodstained Mire',92);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='black')), (select (id) from typ where (nazev='creature')), 
'1B', 'Yixlid Jailer',93);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='black')), (select (id) from typ where (nazev='instant')), 
'B', 'Extirpate',94);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='green')), (select (id) from typ where (nazev='instant')), 
'2G', 'Krosan Grip',95);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='green')), (select (id) from typ where (nazev='instant')), 
'G', 'Oxidize',96);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='black')), (select (id) from typ where (nazev='instant')), 
'1B', 'Smother',97);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='black')), (select (id) from typ where (nazev='creature')), 
'1B', 'Yixlid Jailer',98);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='blue')), (select (id) from typ where (nazev='enchantment')), 
'2U', 'Energy Flux',99);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='green')), (select (id) from typ where (nazev='enchantment')), 
'1G', 'Seal of Primordium',100);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='colorless')), (select (id) from typ where (nazev='land')), 
'0', 'Badlands',101);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='colorless')), (select (id) from typ where (nazev='artifact')), 
'0', 'Lion"s Eye Diamond',102);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='colorless')), (select (id) from typ where (nazev='artifact')), 
'5', 'Memory Jar',103);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='black')), (select (id) from typ where (nazev='enchantment')), 
'BBB', 'Necropotence',104);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='black')), (select (id) from typ where (nazev='enchantment')), 
'4BB', 'Yawghmoth"s Bargain',105);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='black')), (select (id) from typ where (nazev='instant')), 
'1B', 'Cabal Ritual',106);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='black')), (select (id) from typ where (nazev='sorcery')), 
'1BB', 'Grim Tutor',107);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='black')), (select (id) from typ where (nazev='sorcery')), 
'B', 'Imperial Seal',108);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='black')), (select (id) from typ where (nazev='sorcery')), 
'1BB', 'Grim Tutor',109);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='blue')), (select (id) from typ where (nazev='sorcery')), 
'4UU', 'Mind"s Desire',110);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='blue')), (select (id) from typ where (nazev='sorcery')), 
'U', 'ponder',111);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='black')), (select (id) from typ where (nazev='sorcery')), 
'1BB', '',112);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='blue')), (select (id) from typ where (nazev='sorcery')), 
'2U', 'Timetwister',113);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='blue')), (select (id) from typ where (nazev='sorcery')), 
'2U', 'Windfall',114);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='red')), (select (id) from typ where (nazev='sorcery')), 
'2R', 'Wheel of Fortune',115);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='colorless')), (select (id) from typ where (nazev='artifact')), 
'0', 'Chrome Mox',116);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='blue')), (select (id) from typ where (nazev='instant')), 
'1U', 'Echoing Truth',117);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='colorless')), (select (id) from typ where (nazev='land')), 
'0', 'Swamp',118);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='black')), (select (id) from typ where (nazev='creature')), 
'2BB', 'Shimian Specter',119);

insert into mtg_karta (barva_kart_id,typ_karta_id,casting_cost,jmeno,id)
values ( (select (id) from barva where (nazev='blue')), (select (id) from typ where (nazev='instant')), 
'U', 'Ponder',120);





insert into sb_obsahuje (id, pocet, deck_sb_id, karta_sb_id) 
values ((select (id) from deck where (jmeno = 'KEEPER')),1, 1, (select (id) from mtg_karta where (jmeno = 'Engineered Explosives')));

insert into sb_obsahuje (deck_sb_id, pocet, id, karta_sb_id) 
values ((select (id) from deck where (jmeno = 'KEEPER')),1, 2, (select (id) from mtg_karta where (jmeno = 'Decree of Justice')));

insert into sb_obsahuje (deck_sb_id, pocet, id, karta_sb_id) 
values ((select (id) from deck where (jmeno = 'KEEPER')),2, 4, (select (id) from mtg_karta where (jmeno = 'Rack and Ruin')));

insert into sb_obsahuje (deck_sb_id, pocet, id, karta_sb_id) 
values ((select (id) from deck where (jmeno = 'KEEPER')),3, 5, (select (id) from mtg_karta where (jmeno = 'Pyroblast')));

insert into sb_obsahuje (deck_sb_id, pocet, id, karta_sb_id) 
values ((select (id) from deck where (jmeno = 'KEEPER')),1, 7, (select (id) from mtg_karta where (jmeno = 'Fire/Ice')));

insert into sb_obsahuje (deck_sb_id, pocet, id, karta_sb_id) 
values ((select (id) from deck where (jmeno = 'KEEPER')),2, 8, (select (id) from mtg_karta where (jmeno = 'Disenchant')));

insert into sb_obsahuje (deck_sb_id, pocet, id, karta_sb_id) 
values ((select (id) from deck where (jmeno = 'KEEPER')),1, 9, (select (id) from mtg_karta where (jmeno = 'Gorilla Shaman')));

insert into sb_obsahuje (deck_sb_id, pocet, id, karta_sb_id) 
values ((select (id) from deck where (jmeno = 'KEEPER')),2, 10, (select (id) from mtg_karta where (jmeno = 'Barrin, Master Wizard')));

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'KEEPER')), 4, 1, (select (id) from mtg_karta where (jmeno = 'Force of Will')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'KEEPER')), 4, 2, (select (id) from mtg_karta where (jmeno = 'Brainstorm')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'KEEPER')), 4, 3, (select (id) from mtg_karta where (jmeno = 'Mana Drain')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'KEEPER')), 4, 5, (select (id) from mtg_karta where (jmeno = 'Wasteland')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'KEEPER')), 3, 6, (select (id) from mtg_karta where (jmeno = 'Swords to Plowshares')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'KEEPER')), 3, 7, (select (id) from mtg_karta where (jmeno = 'Tundra')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'KEEPER')), 2, 8, (select (id) from mtg_karta where (jmeno = 'Exalted Angel')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'KEEPER')), 2, 9, (select (id) from mtg_karta where (jmeno = 'Cunning Wish')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'KEEPER')), 2, 10, (select (id) from mtg_karta where (jmeno = 'Skeletal Scrying')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'KEEPER')), 2, 11, (select (id) from mtg_karta where (jmeno = 'City of Brass')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'KEEPER')), 2, 12, (select (id) from mtg_karta where (jmeno = 'Underground Sea')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'KEEPER')), 2, 13, (select (id) from mtg_karta where (jmeno = 'Volcanic Island')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'KEEPER')), 1, 14, (select (id) from mtg_karta where (jmeno = 'Darksteel Colossus')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'KEEPER')), 1, 15, (select (id) from mtg_karta where (jmeno = 'Ancestral Recall')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'KEEPER')), 1, 16, (select (id) from mtg_karta where (jmeno = 'Fact or Fiction')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'KEEPER')), 1, 17, (select (id) from mtg_karta where (jmeno = 'Fire/Ice')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'KEEPER')), 1, 18, (select (id) from mtg_karta where (jmeno = 'Mystical Tutor')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'KEEPER')), 1, 19, (select (id) from mtg_karta where (jmeno = 'Balance')));

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'KEEPER')), 1, 20, (select (id) from mtg_karta where (jmeno = 'Decree of Justice')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'KEEPER')), 1, 21, (select (id) from mtg_karta where (jmeno = 'Demonic Tutor')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'KEEPER')), 1, 22, (select (id) from mtg_karta where (jmeno = 'Mind Twist')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'KEEPER')), 1, 23, (select (id) from mtg_karta where (jmeno = 'Time Walk')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'KEEPER')), 1, 24, (select (id) from mtg_karta where (jmeno = 'Tinker')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'KEEPER')), 1, 25, (select (id) from mtg_karta where (jmeno = 'Yawghmoth"s Will')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'KEEPER')), 1, 26, (select (id) from mtg_karta where (jmeno = 'Black Lotus')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'KEEPER')), 1, 27, (select (id) from mtg_karta where (jmeno = 'Crucible of Worlds')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'KEEPER')), 1, 28, (select (id) from mtg_karta where (jmeno = 'Mox Emerald')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'KEEPER')), 1, 29, (select (id) from mtg_karta where (jmeno = 'Mox Jet')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'KEEPER')), 1, 30, (select (id) from mtg_karta where (jmeno = 'Mox Pearl')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'KEEPER')), 1, 31, (select (id) from mtg_karta where (jmeno = 'Mox Ruby')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'KEEPER')), 1, 32, (select (id) from mtg_karta where (jmeno = 'Mox Sapphire')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'KEEPER')), 1, 33, (select (id) from mtg_karta where (jmeno = 'Library of Alexandria')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'KEEPER')), 1, 34, (select (id) from mtg_karta where (jmeno = 'Strip Mine')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'KEEPER')), 1, 35, (select (id) from mtg_karta where (jmeno = 'Mox Jet')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'Meandeck Gifts')), 2, 36, (select (id) from mtg_karta where (jmeno = 'Flooded Strand')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'Meandeck Gifts')), 2, 37, (select (id) from mtg_karta where (jmeno = 'Island')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'Meandeck Gifts')), 2, 38, (select (id) from mtg_karta where (jmeno = 'Polluted Delta')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'Meandeck Gifts')), 1, 39, (select (id) from mtg_karta where (jmeno = 'Tolarian Academy')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'Meandeck Gifts')), 3, 40, (select (id) from mtg_karta where (jmeno = 'Underground Sea')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'Meandeck Gifts')), 1, 41, (select (id) from mtg_karta where (jmeno = 'Library of Alexandria')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'Meandeck Gifts')), 3, 42, (select (id) from mtg_karta where (jmeno = 'Volcanic Island')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'Meandeck Gifts')), 1, 43, (select (id) from mtg_karta where (jmeno = 'Ancestral Recall')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'Meandeck Gifts')), 1, 44, (select (id) from mtg_karta where (jmeno = 'Black Lotus')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'Meandeck Gifts')), 4, 45, (select (id) from mtg_karta where (jmeno = 'Brainstorm')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'Meandeck Gifts')), 1, 46, (select (id) from mtg_karta where (jmeno = 'Demonic Tutor')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'Meandeck Gifts')), 4, 47, (select (id) from mtg_karta where (jmeno = 'Force of Will')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'Meandeck Gifts')), 4, 48, (select (id) from mtg_karta where (jmeno = 'Gifts Ungiven')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'Meandeck Gifts')), 1, 49, (select (id) from mtg_karta where (jmeno = 'Chain of Vapor')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'Meandeck Gifts')), 1, 50, (select (id) from mtg_karta where (jmeno = 'Lotus Petal')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'Meandeck Gifts')), 1, 51, (select (id) from mtg_karta where (jmeno = 'Mana Crypt')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'Meandeck Gifts')), 4, 52, (select (id) from mtg_karta where (jmeno = 'Mana Drain')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'Meandeck Gifts')), 1, 53, (select (id) from mtg_karta where (jmeno = 'Mana Vault')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'Meandeck Gifts')), 4, 54, (select (id) from mtg_karta where (jmeno = 'Flooded Merchant Scroll')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'Meandeck Gifts')), 1, 55, (select (id) from mtg_karta where (jmeno = 'Mox Emerald')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'Meandeck Gifts')), 1, 56, (select (id) from mtg_karta where (jmeno = 'Mox Jet')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'Meandeck Gifts')), 1, 57, (select (id) from mtg_karta where (jmeno = 'Mox Pearl')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'Meandeck Gifts')), 1, 58, (select (id) from mtg_karta where (jmeno = 'Mox Ruby')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'Meandeck Gifts')), 1, 59, (select (id) from mtg_karta where (jmeno = 'Mox Sapphire')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'Meandeck Gifts')), 1, 60, (select (id) from mtg_karta where (jmeno = 'Mystical Tutor')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'Meandeck Gifts')), 1, 61, (select (id) from mtg_karta where (jmeno = 'Recoup')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'Meandeck Gifts')), 1, 62, (select (id) from mtg_karta where (jmeno = 'Sol Ring')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'Meandeck Gifts')), 1, 63, (select (id) from mtg_karta where (jmeno = 'Time Walk')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'Meandeck Gifts')), 1, 64, (select (id) from mtg_karta where (jmeno = 'Vampiric Tutor')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'Meandeck Gifts')), 1, 65, (select (id) from mtg_karta where (jmeno = 'Yawghmoth"s Will')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'Meandeck Gifts')), 2, 66, (select (id) from mtg_karta where (jmeno = 'Repeal')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'Meandeck Gifts')), 1, 67, (select (id) from mtg_karta where (jmeno = 'Tendrils of Agony')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'Meandeck Gifts')), 1, 68, (select (id) from mtg_karta where (jmeno = 'Dark Ritual')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'Meandeck Gifts')), 1, 69, (select (id) from mtg_karta where (jmeno = 'Empty the Warrens')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'Meandeck Gifts')), 2, 71, (select (id) from mtg_karta where (jmeno = 'Misdirection')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'Meandeck Gifts')), 1, 72, (select (id) from mtg_karta where (jmeno = 'Deep Analysis')) );

insert into sb_obsahuje (deck_sb_id, pocet, id, karta_sb_id) 
values ((select (id) from deck where (jmeno = 'Meandeck Gifts')), 1, 11, (select (id) from mtg_karta where (jmeno = 'Chain of Vapor')));

insert into sb_obsahuje (deck_sb_id, pocet, id, karta_sb_id) 
values ((select (id) from deck where (jmeno = 'Meandeck Gifts')), 2, 12, (select (id) from mtg_karta where (jmeno = 'Empty the Warrens')));

insert into sb_obsahuje (deck_sb_id, pocet, id, karta_sb_id) 
values ((select (id) from deck where (jmeno = 'Meandeck Gifts')), 1, 13, (select (id) from mtg_karta where (jmeno = 'Deep Analysis')));

insert into sb_obsahuje (deck_sb_id, pocet, id, karta_sb_id) 
values ((select (id) from deck where (jmeno = 'Meandeck Gifts')), 1, 14, (select (id) from mtg_karta where (jmeno = 'Rebuild')));

insert into sb_obsahuje (deck_sb_id, pocet, id, karta_sb_id) 
values ((select (id) from deck where (jmeno = 'Meandeck Gifts')), 1, 15, (select (id) from mtg_karta where (jmeno = 'Pyroblast')));

insert into sb_obsahuje (deck_sb_id, pocet, id, karta_sb_id) 
values ((select (id) from deck where (jmeno = 'Meandeck Gifts')), 2, 16, (select (id) from mtg_karta where (jmeno = 'Pyroclasm')));

insert into sb_obsahuje (deck_sb_id, pocet, id, karta_sb_id) 
values ((select (id) from deck where (jmeno = 'Meandeck Gifts')), 2, 17, (select (id) from mtg_karta where (jmeno = 'Duress')));

insert into sb_obsahuje (deck_sb_id, pocet, id, karta_sb_id) 
values ((select (id) from deck where (jmeno = 'Meandeck Gifts')), 1, 18, (select (id) from mtg_karta where (jmeno = 'Red Elemental Blast')));

insert into sb_obsahuje (deck_sb_id, pocet, id, karta_sb_id) 
values ((select (id) from deck where (jmeno = 'Meandeck Gifts')), 2, 19, (select (id) from mtg_karta where (jmeno = 'Pithing Needle')));

insert into sb_obsahuje (deck_sb_id, pocet, id, karta_sb_id) 
values ((select (id) from deck where (jmeno = 'Meandeck Gifts')), 1, 20, (select (id) from mtg_karta where (jmeno = 'Hurkyl"s Recall')));

insert into sb_obsahuje (deck_sb_id, pocet, id, karta_sb_id) 
values ((select (id) from deck where (jmeno = 'Meandeck Gifts')), 1, 21, (select (id) from mtg_karta where (jmeno = 'Brain Freeze')));

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'fritka')), 4, 73, (select (id) from mtg_karta where (jmeno = 'Wasteland')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'fritka')), 14, 74, (select (id) from mtg_karta where (jmeno = 'Mountain')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'fritka')), 4, 75, (select (id) from mtg_karta where (jmeno = 'Mishra"s Factory')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'fritka')), 1, 76, (select (id) from mtg_karta where (jmeno = 'Strip Mine')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'fritka')), 4, 77, (select (id) from mtg_karta where (jmeno = 'Simian Spirit Guide')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'fritka')), 4, 78, (select (id) from mtg_karta where (jmeno = 'Hellspark Elemental')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'fritka')), 3, 79, (select (id) from mtg_karta where (jmeno = 'Fireblast')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'fritka')), 3, 80, (select (id) from mtg_karta where (jmeno = 'Genju of the Spires')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'fritka')), 4, 81, (select (id) from mtg_karta where (jmeno = 'Pyrostatic Pillar')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'fritka')), 4, 82, (select (id) from mtg_karta where (jmeno = 'Null Rod')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'fritka')), 4, 83, (select (id) from mtg_karta where (jmeno = 'Lightning Bolt')));

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'fritka')), 4, 84, (select (id) from mtg_karta where (jmeno = 'Chain Lightning')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'fritka')), 4, 85, (select (id) from mtg_karta where (jmeno = 'Ankh of Mishra')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'fritka')), 4, 86, (select (id) from mtg_karta where (jmeno = 'Chalice of the Void')) );

insert into sb_obsahuje (deck_sb_id, pocet, id, karta_sb_id) 
values ((select (id) from deck where (jmeno = 'fritka')), 3, 22, (select (id) from mtg_karta where (jmeno = 'Pyroblast')));

insert into sb_obsahuje (deck_sb_id, pocet, id, karta_sb_id) 
values ((select (id) from deck where (jmeno = 'fritka')), 3, 23, (select (id) from mtg_karta where (jmeno = 'Greater Gargadon')));

insert into sb_obsahuje (deck_sb_id, pocet, id, karta_sb_id) 
values ((select (id) from deck where (jmeno = 'fritka')), 3, 24, (select (id) from mtg_karta where (jmeno = 'Relic of Progenitus')));

insert into sb_obsahuje (deck_sb_id, pocet, id, karta_sb_id) 
values ((select (id) from deck where (jmeno = 'fritka')), 3, 25, (select (id) from mtg_karta where (jmeno = 'Pithing Needle')));

insert into sb_obsahuje (deck_sb_id, pocet, id, karta_sb_id) 
values ((select (id) from deck where (jmeno = 'fritka')), 3, 26, (select (id) from mtg_karta where (jmeno = 'Shattering Spree')));

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'fish')), 4, 87, (select (id) from mtg_karta where (jmeno = 'Dark Confidant')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'fish')), 4, 88, (select (id) from mtg_karta where (jmeno = 'Dimir Cutpurse')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'fish')), 4, 89, (select (id) from mtg_karta where (jmeno = 'Tarmogoyf')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'fish')), 2, 90, (select (id) from mtg_karta where (jmeno = 'Tombstalker')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'fish')), 4, 91, (select (id) from mtg_karta where (jmeno = 'Dark Ritual')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'fish')), 3, 92, (select (id) from mtg_karta where (jmeno = 'Diabolic Edict')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'fish')), 1, 93, (select (id) from mtg_karta where (jmeno = 'Demonic Tutor')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'fish')), 3, 94, (select (id) from mtg_karta where (jmeno = 'Duress')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'fish')), 4, 95, (select (id) from mtg_karta where (jmeno = 'Hymn to Tourach')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'fish')), 3, 96, (select (id) from mtg_karta where (jmeno = 'Thoughtseize')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'fish')), 1, 97, (select (id) from mtg_karta where (jmeno = 'Time Walk')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'fish')), 1, 98, (select (id) from mtg_karta where (jmeno = 'Black Lotus')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'fish')), 1, 99, (select (id) from mtg_karta where (jmeno = 'Mox Emerald')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'fish')), 1, 100, (select (id) from mtg_karta where (jmeno = 'Mox Pearl')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'fish')), 1, 101, (select (id) from mtg_karta where (jmeno = 'Mox Ruby')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'fish')), 1, 102, (select (id) from mtg_karta where (jmeno = 'Mox Sapphire')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'fish')), 4, 103, (select (id) from mtg_karta where (jmeno = 'Null Rod')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'fish')), 3, 104, (select (id) from mtg_karta where (jmeno = 'Bayou')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'fish')), 2, 105, (select (id) from mtg_karta where (jmeno = 'Bloodstained Mire')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'fish')), 4, 106, (select (id) from mtg_karta where (jmeno = 'Polluted Delta')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'fish')), 1, 107, (select (id) from mtg_karta where (jmeno = 'Strip Mine')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'fish')), 1, 108, (select (id) from mtg_karta where (jmeno = 'Swamp')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'fish')), 3, 109, (select (id) from mtg_karta where (jmeno = 'Underground Sea')) );

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id ) 
values ( (select (id) from deck where (jmeno = 'fish')), 4, 110, (select (id) from mtg_karta where (jmeno = 'Wasteland')) );

insert into sb_obsahuje (deck_sb_id, pocet, id, karta_sb_id) 
values ((select (id) from deck where (jmeno = 'fish')), 2, 27, (select (id) from mtg_karta where (jmeno = 'Yixlid Jailer')));

insert into sb_obsahuje (deck_sb_id, pocet, id, karta_sb_id) 
values ((select (id) from deck where (jmeno = 'fish')), 2, 28, (select (id) from mtg_karta where (jmeno = 'Extirpate')));

insert into sb_obsahuje (deck_sb_id, pocet, id, karta_sb_id) 
values ((select (id) from deck where (jmeno = 'fish')), 3, 29, (select (id) from mtg_karta where (jmeno = 'Krosan Grip')));

insert into sb_obsahuje (deck_sb_id, pocet, id, karta_sb_id) 
values ((select (id) from deck where (jmeno = 'fish')), 1, 30, (select (id) from mtg_karta where (jmeno = 'Oxidize')));

insert into sb_obsahuje (deck_sb_id, pocet, id, karta_sb_id) 
values ((select (id) from deck where (jmeno = 'fish')), 3, 31, (select (id) from mtg_karta where (jmeno = 'Smother')));

insert into sb_obsahuje (deck_sb_id, pocet, id, karta_sb_id) 
values ((select (id) from deck where (jmeno = 'fish')), 3, 32, (select (id) from mtg_karta where (jmeno = 'Energy Flux')));

insert into sb_obsahuje (deck_sb_id, pocet, id, karta_sb_id) 
values ((select (id) from deck where (jmeno = 'fish')), 1, 33, (select (id) from mtg_karta where (jmeno = 'Seal of Primordium')));

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id) 
values (4, 1, 111, (select (id) from mtg_karta where (jmeno = 'Badlands')));

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id) 
values (4, 2, 112, (select (id) from mtg_karta where (jmeno = 'Bloodstained Mire')));

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id) 
values (4, 4, 113, (select (id) from mtg_karta where (jmeno = 'Polluted Delta')));

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id) 
values (4, 3, 114, (select (id) from mtg_karta where (jmeno = 'Underground Sea')));

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id) 
values (4, 1, 115, (select (id) from mtg_karta where (jmeno = 'Tolarian Academy')));

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id) 
values (4, 2, 116, (select (id) from mtg_karta where (jmeno = 'Simian Spirit Guide')));

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id) 
values (4, 1, 117, (select (id) from mtg_karta where (jmeno = 'Black Lotus')));

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id) 
values (4, 1, 118, (select (id) from mtg_karta where (jmeno = 'Lion"s Eye Diamond')));

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id) 
values (4, 1, 119, (select (id) from mtg_karta where (jmeno = 'Lotus Petal')));

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id) 
values (4, 1, 120, (select (id) from mtg_karta where (jmeno = 'Mana Crypt')));

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id) 
values (4, 1, 121, (select (id) from mtg_karta where (jmeno = 'Mana Vault')));

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id) 
values (4, 1, 122, (select (id) from mtg_karta where (jmeno = 'Memory Jar')));

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id) 
values (4, 1, 123, (select (id) from mtg_karta where (jmeno = 'Mox Emerald')));

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id) 
values (4, 1, 124, (select (id) from mtg_karta where (jmeno = 'Mox Jet')));

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id) 
values (4, 1, 125, (select (id) from mtg_karta where (jmeno = 'Mox Pearl')));

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id) 
values (4, 1, 126, (select (id) from mtg_karta where (jmeno = 'Mox Ruby')));

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id) 
values (4, 1, 127, (select (id) from mtg_karta where (jmeno = 'Mox Sapphire')));

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id) 
values (4, 1, 128, (select (id) from mtg_karta where (jmeno = 'Sol Ring')));

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id) 
values (4, 1, 129, (select (id) from mtg_karta where (jmeno = 'Necropotence')));

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id) 
values (4, 1, 130, (select (id) from mtg_karta where (jmeno = 'Yawghmoth"s Will')));

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id) 
values (4, 1, 131, (select (id) from mtg_karta where (jmeno = 'Yawghmoth"s Bargain')));

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id) 
values (4, 1, 132, (select (id) from mtg_karta where (jmeno = 'Ancestral Recall')));

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id) 
values (4, 1, 133, (select (id) from mtg_karta where (jmeno = 'Brainstorm')));

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id) 
values (4, 3, 134, (select (id) from mtg_karta where (jmeno = 'Cabal Ritual')));

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id) 
values (4, 1, 135, (select (id) from mtg_karta where (jmeno = 'Chain of Vapor')));

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id) 
values (4, 4, 136, (select (id) from mtg_karta where (jmeno = 'Dark Ritual')));

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id) 
values (4, 1, 137, (select (id) from mtg_karta where (jmeno = 'Mystical Tutor')));

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id) 
values (4, 1, 138, (select (id) from mtg_karta where (jmeno = 'Vampiric Tutor')));

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id) 
values (4, 1, 139, (select (id) from mtg_karta where (jmeno = 'Demonic Tutor')));

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id) 
values (4, 3, 140, (select (id) from mtg_karta where (jmeno = 'Duress')));

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id) 
values (4, 1, 141, (select (id) from mtg_karta where (jmeno = 'Empty the Warrens')));

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id) 
values (4, 3, 142, (select (id) from mtg_karta where (jmeno = 'Grim Tutor')));

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id) 
values (4, 1, 143, (select (id) from mtg_karta where (jmeno = 'Imperial Seal')));

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id) 
values (4, 1, 144, (select (id) from mtg_karta where (jmeno = 'Mind"s Desire')));

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id) 
values (4, 1, 145, (select (id) from mtg_karta where (jmeno = 'Ponder')));

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id) 
values (4, 1, 146, (select (id) from mtg_karta where (jmeno = 'Tendrils of Agony')));

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id) 
values (4, 2, 147, (select (id) from mtg_karta where (jmeno = 'Thoughtseize')));

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id) 
values (4, 1, 148, (select (id) from mtg_karta where (jmeno = 'Time Walk')));

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id) 
values (4, 1, 149, (select (id) from mtg_karta where (jmeno = 'Timetwister')));

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id) 
values (4, 1, 150, (select (id) from mtg_karta where (jmeno = 'Tinker')));

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id) 
values (4, 1, 151, (select (id) from mtg_karta where (jmeno = 'Wheel of Fortune')));

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id) 
values (4, 1, 152, (select (id) from mtg_karta where (jmeno = 'Windfall')));

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id) 
values (4, 1, 153, (select (id) from mtg_karta where (jmeno = 'Chrome Mox')));

insert into sb_obsahuje (deck_sb_id, pocet, id, karta_sb_id) 
values (4, 2, 34, (select (id) from mtg_karta where (jmeno = 'Tendrils of Agony')));

insert into sb_obsahuje (deck_sb_id, pocet, id, karta_sb_id) 
values (4, 1, 35, (select (id) from mtg_karta where (jmeno = 'Pyroblast')));

insert into sb_obsahuje (deck_sb_id, pocet, id, karta_sb_id) 
values (4, 2, 36, (select (id) from mtg_karta where (jmeno = 'Rebuild')));

insert into sb_obsahuje (deck_sb_id, pocet, id, karta_sb_id) 
values (4, 1, 37, (select (id) from mtg_karta where (jmeno = 'Swamp')));

insert into sb_obsahuje (deck_sb_id, pocet, id, karta_sb_id) 
values (4, 1, 38, (select (id) from mtg_karta where (jmeno = 'Island')));

insert into sb_obsahuje (deck_sb_id, pocet, id, karta_sb_id) 
values (4, 2, 39, (select (id) from mtg_karta where (jmeno = 'Echoing Truth')));

insert into sb_obsahuje (deck_sb_id, pocet, id, karta_sb_id) 
values (4, 1, 40, (select (id) from mtg_karta where (jmeno = 'Red Elemental Blast')));

insert into sb_obsahuje (deck_sb_id, pocet, id, karta_sb_id) 
values (4, 1, 41, (select (id) from mtg_karta where (jmeno = 'Shimian Specter')));

insert into sb_obsahuje (deck_sb_id, pocet, id, karta_sb_id) 
values (4, 4, 42, (select (id) from mtg_karta where (jmeno = 'Dark Confidant')));

insert into md_obsahuje (deck_md_id, pocet, id, karta_md_id) 
values (4, 1, 145, (select (id) from mtg_karta where (jmeno = 'Ponder')));


insert into kolo (id, turnaj_kol_id, cislo) values (1, 1, 1);
insert into kolo (id, turnaj_kol_id, cislo) values (2, 1, 2);
insert into kolo (id, turnaj_kol_id, cislo) values (3, 1, 3);
insert into kolo (id, turnaj_kol_id, cislo) values (4, 2, 1);
insert into kolo (id, turnaj_kol_id, cislo) values (5, 2, 2);
insert into kolo (id, turnaj_kol_id, cislo) values (6, 2, 3);
insert into kolo (id, turnaj_kol_id, cislo) values (7, 3, 1);
insert into kolo (id, turnaj_kol_id, cislo) values (8, 3, 2);
insert into kolo (id, turnaj_kol_id, cislo) values (9, 3, 3);



insert into zapas (kolo_zapas_id,h1_zapas_id, 
vysl_zapas_hrac_1_vyhral_her,vysl_zapas_hrac_2_vyhral_her,
h2_zapas_id,  bye, poznamky, id)
values (4,1,
2,1,
2,0,'',1);

insert into zapas (kolo_zapas_id,h1_zapas_id, 
vysl_zapas_hrac_1_vyhral_her,vysl_zapas_hrac_2_vyhral_her,
h2_zapas_id,  bye, poznamky, id)
values (4,3,
2,1,
4,0,'',2);

insert into zapas (kolo_zapas_id,h1_zapas_id, 
vysl_zapas_hrac_1_vyhral_her,vysl_zapas_hrac_2_vyhral_her,
h2_zapas_id,  bye, poznamky, id)
values (4,5,
2,1,
6,0,'',3);

insert into zapas (kolo_zapas_id,h1_zapas_id, 
vysl_zapas_hrac_1_vyhral_her,vysl_zapas_hrac_2_vyhral_her,
h2_zapas_id,  bye, poznamky, id)
values (4,7,
2,1,
8,0,'',4);



insert into zapas (kolo_zapas_id,h1_zapas_id, 
vysl_zapas_hrac_1_vyhral_her,vysl_zapas_hrac_2_vyhral_her,
h2_zapas_id,  bye, poznamky, id)
values (5,1,
0,2,
3,0,'',5);

insert into zapas (kolo_zapas_id,h1_zapas_id, 
vysl_zapas_hrac_1_vyhral_her,vysl_zapas_hrac_2_vyhral_her,
h2_zapas_id,  bye, poznamky, id)
values (5,5,
0,2,
8,0,'',6);




insert into zapas (kolo_zapas_id,h1_zapas_id, 
vysl_zapas_hrac_1_vyhral_her,vysl_zapas_hrac_2_vyhral_her,
h2_zapas_id,  bye, poznamky, id)
values (6,3,
0,2,
8,0,'',7);



insert into zapas (kolo_zapas_id,h1_zapas_id, 
vysl_zapas_hrac_1_vyhral_her,vysl_zapas_hrac_2_vyhral_her,
h2_zapas_id,  bye, poznamky, id)
values (7,1,
2, 0,
2,0,'',8);

insert into zapas (kolo_zapas_id,h1_zapas_id, 
vysl_zapas_hrac_1_vyhral_her,vysl_zapas_hrac_2_vyhral_her,
h2_zapas_id,  bye, poznamky, id)
values (7,3,
2, 1,
4,0,'',9);


insert into zapas (kolo_zapas_id,h1_zapas_id, 
vysl_zapas_hrac_1_vyhral_her,vysl_zapas_hrac_2_vyhral_her,
h2_zapas_id,  bye, poznamky, id)
values (7,5,
2, 1,
6,0,'',10);

insert into zapas (kolo_zapas_id,h1_zapas_id, 
vysl_zapas_hrac_1_vyhral_her,vysl_zapas_hrac_2_vyhral_her,
h2_zapas_id,  bye, poznamky, id)
values (7,7,
0, 2,
8,0,'',11);



insert into zapas (kolo_zapas_id,h1_zapas_id, 
vysl_zapas_hrac_1_vyhral_her,vysl_zapas_hrac_2_vyhral_her,
h2_zapas_id,  bye, poznamky, id)
values (8,2,
2, 0,
3,0,'',12);

insert into zapas (kolo_zapas_id,h1_zapas_id, 
vysl_zapas_hrac_1_vyhral_her,vysl_zapas_hrac_2_vyhral_her,
h2_zapas_id,  bye, poznamky, id)
values (8,5,
0, 2,
8,0,'',13);



insert into zapas (kolo_zapas_id,h1_zapas_id, 
vysl_zapas_hrac_1_vyhral_her,vysl_zapas_hrac_2_vyhral_her,
h2_zapas_id,  bye, poznamky, id)
values (9,3,
0, 2,
8,0,'',14);




insert into zapas (kolo_zapas_id,h1_zapas_id, 
vysl_zapas_hrac_1_vyhral_her,vysl_zapas_hrac_2_vyhral_her,
h2_zapas_id,  bye, poznamky, id)
values (1,1,
1, 1,
2,0,'',15);

insert into zapas (kolo_zapas_id,h1_zapas_id, 
vysl_zapas_hrac_1_vyhral_her,vysl_zapas_hrac_2_vyhral_her,
h2_zapas_id,  bye, poznamky, id)
values (1,3,
2, 0,
4,0,'',16);

insert into zapas (kolo_zapas_id,h1_zapas_id, 
vysl_zapas_hrac_1_vyhral_her,vysl_zapas_hrac_2_vyhral_her,
h2_zapas_id,  bye, poznamky, id)
values (1,5,
0, 0,
6,0,'',17);


insert into zapas (kolo_zapas_id,h1_zapas_id, 
vysl_zapas_hrac_1_vyhral_her,vysl_zapas_hrac_2_vyhral_her,
h2_zapas_id,  bye, poznamky, id)
values (1,7,
0, 2,
8,0,'',18);




insert into zapas (kolo_zapas_id,h1_zapas_id, 
vysl_zapas_hrac_1_vyhral_her,vysl_zapas_hrac_2_vyhral_her,
h2_zapas_id,  bye, poznamky, id)
values (2,3,
0, 2,
8,0,'',19);

insert into zapas (kolo_zapas_id,h1_zapas_id, 
vysl_zapas_hrac_1_vyhral_her,vysl_zapas_hrac_2_vyhral_her,
h2_zapas_id,  bye, poznamky, id)
values (2,1,
2, 1,
6,0,'',20);


insert into zapas (kolo_zapas_id,h1_zapas_id, 
vysl_zapas_hrac_1_vyhral_her,vysl_zapas_hrac_2_vyhral_her,
h2_zapas_id,  bye, poznamky, id)
values (2,2,
1, 1,
5,0,'',21);

insert into zapas (kolo_zapas_id,h1_zapas_id, 
vysl_zapas_hrac_1_vyhral_her,vysl_zapas_hrac_2_vyhral_her,
h2_zapas_id,  bye, poznamky, id)
values (2,4,
2, 1,
7,0,'',22);

insert into zapas (kolo_zapas_id,h1_zapas_id, 
vysl_zapas_hrac_1_vyhral_her,vysl_zapas_hrac_2_vyhral_her,
h2_zapas_id,  bye, poznamky, id)
values (2,8,
2, 1,
1,0,'',23);


insert into zapas (kolo_zapas_id,h1_zapas_id, 
vysl_zapas_hrac_1_vyhral_her,vysl_zapas_hrac_2_vyhral_her,
h2_zapas_id,  bye, poznamky, id)
values (2,3,
1, 1,
6,0,'',24);




insert into zapas (kolo_zapas_id,h1_zapas_id, 
vysl_zapas_hrac_1_vyhral_her,vysl_zapas_hrac_2_vyhral_her,
h2_zapas_id,  bye, poznamky, id)
values (3,5,
0, 2,
7,0,'',25);

insert into zapas (kolo_zapas_id,h1_zapas_id, 
vysl_zapas_hrac_1_vyhral_her,vysl_zapas_hrac_2_vyhral_her,
h2_zapas_id,  bye, poznamky, id)
values (3,2,
0, 2,
4,0,'',26);



/*
select * from hraji_se_zapasy;

select * from kolo;

select * from byl_rozhodcim;

select * from hral_deck_na_turnaji;
select * from turnaj;

select * from system;

select * from rozhodci;

select * from turnaj;



select (id) from mtg_karta where (jmeno = 'Ponder');
select * from mtg_karta where (typ_karta_id = (select (id) from typ where nazev='land'));
select (id) from deck where (jmeno = 'Grimlong');
select * from md_obsahuje;
select (id) from mtg_karta where (jmeno = 'Yixlid Jailer');
delete from mtg_karta where (id=98);
select * from md_obsahuje where (id=90);
select * from sb_obsahuje;
select * from mtg_karta;
select * from deck;
select (id) from mtg_karta where (jmeno = 'Dark Confidant');

select * from sb_obsahuje where (id >10);
select (id) from mtg_karta where (jmeno = 'Pithing Needle');

delete from mtg_karta where (id = 120);
delete from sb_obsahuje where (id= 16);
select * from sb_obsahuje;
select * from mtg_karta where (typ_karta_id= (select (id) from typ where (nazev='creature')));

select (id) from typ where (nazev='creature');

select * from typ;

select * from mtg_karta where (typ_karta_id= (select (id) from typ where (jmeno='creature')));

select * from mtg_karta where (typ_karta_id= (select (id) from typ where (jmeno='creature')));

select * from mtg_karta where (typ='creature');

select * from sb_obsahuje;

select * from md_obsahuje;
delete from md_obsahuje where (id = 7);

select * from druh;
select (id) from deck where (jmeno = 'KEEPER');
select * from deck;

select * from typ;
select (id) from typ where (nazev='creature');
select * from mtg_karta where (typ_karta_id= (select (id) from typ where (nazev='creature')));

UPDATE mtg_karta SET jmeno = 'Hymn to Tourach' WHERE jmeno = 'Hymn to tourach';
select * from mtg_karta;
select * from mtg_karta where (mtg_karta.typ_karta_id = (select (id) from typ where (nazev = 'enchantment')));
delete from mtg_karta where (id=1) cascade ;
delete from mtg_karta where (mtg_karta.typ_karta_id = (select (id) from typ where (nazev = 'enchantment')));

delete from mtg_karta where (id =113);


update mtg_karta k set jmeno = 'Dark Confidant' where jmeno ='confi';

select * from mtg_karta where (jmeno = 'Dark Confidant');
select * from mtg_karta;

select * from mtg_karta where (id=88);
select * from typ;
select * from mtg_karta where (typ_karta_id = (select (id) from typ where (nazev = 'land') ));
select * from mtg_karta where (typ_karta_id = (select (id) from typ where (nazev = 'artifact') ));

select * from mtg_karta where (typ_karta_id = (select (id) from typ where (nazev = 'enchantment') ));
select * from mtg_karta where (typ_karta_id = (select (id) from typ where (nazev = 'instant') ));
select * from mtg_karta where (typ_karta_id = (select (id) from typ where (nazev = 'sorcery') ));

select * from rozhodci;


*/
