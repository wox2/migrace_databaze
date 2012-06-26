DELETE FROM AUTORSTVI;
DELETE FROM ZAPUJCKA_NOT;
DELETE FROM ZAPUJCKA_SATU;
DELETE FROM CAST_PROGRAMU;	  	
DELETE FROM CAST_SKLADBY;
DELETE FROM ZARAZENI;
DELETE FROM UCAST;
 	
DELETE FROM KONCERT;
DELETE FROM ZKOUSKA;
DELETE FROM UDALOST;

DELETE FROM VYTISK;
DELETE FROM PUBLIKACE;

DELETE FROM SATY;
DELETE FROM SKLADATEL;
DELETE FROM SKLADBA;
DELETE FROM ZPEVAK;
DELETE FROM HLAS; 
INSERT INTO Hlas (zkratka, nazev_h) VALUES ('S', 'Soprán');
INSERT INTO Hlas (zkratka, nazev_h) VALUES ('A', 'Alt');
INSERT INTO Hlas (zkratka, nazev_h) VALUES ('T', 'Tenor');
INSERT INTO Hlas (zkratka, nazev_h) VALUES ('B', 'Bas');
INSERT INTO Hlas (zkratka, nazev_h) VALUES ('CT', 'Kontratenor');

INSERT INTO Zpevak (jmeno, prijmeni, hlas, rc) VALUES ('Alena', 'Havlíková', 'S', 5953014321);
INSERT INTO Zpevak (jmeno, prijmeni, hlas, rc) VALUES ('Jana', 'Rinkeová',	 'S', 7263225148);
INSERT INTO Zpevak (jmeno, prijmeni, hlas, rc) VALUES ('Pavla', 'Kohlová',	 'S', 7061135522);
INSERT INTO Zpevak (jmeno, prijmeni, hlas, rc) VALUES ('Lenka', 'Pašková',	 'S', 6457021234);
INSERT INTO Zpevak (jmeno, prijmeni, hlas, rc) VALUES ('Kateřina', 'Orlová', 'A', 6555151278);
INSERT INTO Zpevak (jmeno, prijmeni, hlas, rc) VALUES ('Irena', 'Horová',	 'A', 6958216542);
INSERT INTO Zpevak (jmeno, prijmeni, hlas, rc) VALUES ('Jiřina', 'Zámostná', 'A', 4951032446);
INSERT INTO Zpevak (jmeno, prijmeni, hlas, rc) VALUES ('Eva', 'Janoušková',	 'A', 5561032947);
INSERT INTO Zpevak (jmeno, prijmeni, hlas, rc) VALUES ('Jaroslav', 'Doubek', 'T', 6706062648);
INSERT INTO Zpevak (jmeno, prijmeni, hlas, rc) VALUES ('Michal', 'Hospr',	 'T', 7622263841);
INSERT INTO Zpevak (jmeno, prijmeni, hlas, rc) VALUES ('Jindřich', 'Hojer',	 'T', 5402022234);
INSERT INTO Zpevak (jmeno, prijmeni, hlas, rc) VALUES ('Karel', 'Valtr',	 'T', 7007073737);
INSERT INTO Zpevak (jmeno, prijmeni, hlas, rc) VALUES ('Oldřich', 'Hrubý',	 'B', 6903034321);
INSERT INTO Zpevak (jmeno, prijmeni, hlas, rc) VALUES ('Jan', 'Havlík',		 'B', 5901012681);
INSERT INTO Zpevak (jmeno, prijmeni, hlas, rc) VALUES ('Josef', 'Prknař',	 'B', 6508283452);
INSERT INTO Zpevak (jmeno, prijmeni, hlas, rc) VALUES ('Stanislav', 'Pulec', 'B', 7711124554);

INSERT INTO Vedeni (RC, Zkratka) SELECT RC, zkratka FROM Zpevak, Hlas WHERE prijmeni='Havlíková' AND zkratka='S';
INSERT INTO Vedeni (RC, Zkratka) SELECT RC, zkratka FROM Zpevak, Hlas WHERE prijmeni='Orlová' AND zkratka='A';
INSERT INTO Vedeni (RC, Zkratka) SELECT RC, zkratka FROM Zpevak, Hlas WHERE prijmeni='Doubek' AND zkratka='T';
INSERT INTO Vedeni (RC, Zkratka) SELECT RC, zkratka FROM Zpevak, Hlas WHERE prijmeni='Hrubý' AND zkratka='B';

INSERT INTO Saty (inv_cislo, velikost) VALUES (1, 55);
INSERT INTO Saty (inv_cislo, velikost) VALUES (2, 55);
INSERT INTO Saty (inv_cislo, velikost) VALUES (3, 55);
INSERT INTO Saty (inv_cislo, velikost) VALUES (4, 57);
INSERT INTO Saty (inv_cislo, velikost) VALUES (5, 57);
INSERT INTO Saty (inv_cislo, velikost) VALUES (6, 42);
INSERT INTO Saty (inv_cislo, velikost) VALUES (7, 43);
INSERT INTO Saty (inv_cislo, velikost) VALUES (8, 46);
INSERT INTO Saty (inv_cislo, velikost) VALUES (9, 46);
INSERT INTO Saty (inv_cislo, velikost) VALUES (10, 48);
INSERT INTO Saty (inv_cislo, velikost) VALUES (11, 52);
INSERT INTO Saty (inv_cislo, velikost) VALUES (12, 52);
INSERT INTO Saty (inv_cislo, velikost) VALUES (13, 50);
INSERT INTO Saty (inv_cislo, velikost) VALUES (14, 56);
INSERT INTO Saty (inv_cislo, velikost) VALUES (15, 44);
INSERT INTO Saty (inv_cislo, velikost) VALUES (16, 49);
INSERT INTO Saty (inv_cislo, velikost) VALUES (17, 56);
INSERT INTO Saty (inv_cislo, velikost) VALUES (18, 50);

INSERT INTO Zapujcka_satu (RC, inv_cislo) SELECT RC, 6 	FROM Zpevak WHERE prijmeni='Havlíková';
INSERT INTO Zapujcka_satu (RC, inv_cislo) SELECT RC, 7 	FROM Zpevak WHERE prijmeni='Rinkeová';
INSERT INTO Zapujcka_satu (RC, inv_cislo) SELECT RC, 8 	FROM Zpevak WHERE prijmeni='Pašková';
INSERT INTO Zapujcka_satu (RC, inv_cislo) SELECT RC, 9 	FROM Zpevak WHERE prijmeni='Orlová';
INSERT INTO Zapujcka_satu (RC, inv_cislo) SELECT RC, 10 FROM Zpevak WHERE prijmeni='Horová';
INSERT INTO Zapujcka_satu (RC, inv_cislo) SELECT RC, 15 FROM Zpevak WHERE prijmeni='Janoušková';
INSERT INTO Zapujcka_satu (RC, inv_cislo) SELECT RC, 1 	FROM Zpevak WHERE prijmeni='Doubek';
INSERT INTO Zapujcka_satu (RC, inv_cislo) SELECT RC, 2 	FROM Zpevak WHERE prijmeni='Hospr';
INSERT INTO Zapujcka_satu (RC, inv_cislo) SELECT RC, 5 	FROM Zpevak WHERE prijmeni='Hojer';
INSERT INTO Zapujcka_satu (RC, inv_cislo) SELECT RC, 11 FROM Zpevak WHERE prijmeni='Valtr';
INSERT INTO Zapujcka_satu (RC, inv_cislo) SELECT RC, 12 FROM Zpevak WHERE prijmeni='Hrubý';
INSERT INTO Zapujcka_satu (RC, inv_cislo) SELECT RC, 14 FROM Zpevak WHERE prijmeni='Prknař';
INSERT INTO Zapujcka_satu (RC, inv_cislo) SELECT RC, 17 FROM Zpevak WHERE prijmeni='Pulec';



-------------------------------skladby---------------------------------------------------
INSERT INTO Skladatel (jmeno, prijmeni, rok_narozeni, rok_umrti) VALUES ('Antonín', 'Dvořák', 1841, 1904);
INSERT INTO Skladatel (jmeno, prijmeni, rok_narozeni, rok_umrti) VALUES ('Bohuslav', 'Martinů', 1890, 1959);
INSERT INTO Skladatel (jmeno, prijmeni, rok_narozeni, rok_umrti) VALUES ('Jan', 'Hanuš', 1905, 2004);
INSERT INTO Skladatel (jmeno, prijmeni, rok_narozeni, rok_umrti) VALUES ('Johann Sebastian', 'Bach', 1685, 1750);
INSERT INTO Skladatel (jmeno, prijmeni, rok_narozeni, rok_umrti) VALUES ('Wolfgang Amadeus', 'Mozart', 1756, 1791);
INSERT INTO Skladatel (jmeno, prijmeni, rok_narozeni, rok_umrti) VALUES ('Claude', 'Debussy', 1862, 1918);
INSERT INTO Skladatel (jmeno, prijmeni, rok_narozeni, rok_umrti) VALUES ('Zdeněk', 'Lukáš', 1945, NULL);
INSERT INTO Skladatel (jmeno, prijmeni, rok_narozeni, rok_umrti) VALUES ('Jakub Jan', 'Ryba', 1765, 1815);
INSERT INTO Skladatel (jmeno, prijmeni, rok_narozeni, rok_umrti) VALUES ('Martin', 'Grobár', 1976, NULL);
INSERT INTO Skladatel (jmeno, prijmeni, rok_narozeni, rok_umrti) VALUES ('Jaroslav', 'Krček', NULL, NULL);
INSERT INTO Skladatel (jmeno, prijmeni, rok_narozeni, rok_umrti) VALUES ('Franz Xaver', 'Gruber', NULL, NULL);


INSERT INTO Skladba (id, nazev) VALUES (1, 'Mše D dur');
INSERT INTO Autorstvi (jmeno, prijmeni, id, je_uprava) VALUES ('Antonín', 'Dvořák', 1, 0);
INSERT INTO Cast_skladby (id, poradi, nazev_c) VALUES (1, 1, 'Kyrie');
INSERT INTO Cast_skladby (id, poradi, nazev_c) VALUES (1, 2, 'Gloria');
INSERT INTO Cast_skladby (id, poradi, nazev_c) VALUES (1, 3, 'Credo');
INSERT INTO Cast_skladby (id, poradi, nazev_c) VALUES (1, 4, 'Sanctus');
INSERT INTO Cast_skladby (id, poradi, nazev_c) VALUES (1, 5, 'Benedictus');
INSERT INTO Cast_skladby (id, poradi, nazev_c) VALUES (1, 6, 'Agnus Dei');

INSERT INTO Skladba (id, nazev) VALUES (2, 'Napadly písně');
INSERT INTO Autorstvi (jmeno, prijmeni, id, je_uprava) VALUES ('Antonín', 'Dvořák', 2, 0);

INSERT INTO Skladba (id, nazev) VALUES (3, 'Píseň bratra Slunce');
INSERT INTO Autorstvi (jmeno, prijmeni, id, je_uprava) VALUES ('Jan', 'Hanuš', 3, 0);

INSERT INTO Skladba (id, nazev) VALUES (4, 'Čtyři písně o Marii');
INSERT INTO Autorstvi (jmeno, prijmeni, id, je_uprava) VALUES ('Bohuslav', 'Martinů', 4, 0);
INSERT INTO Cast_skladby (id, poradi, nazev_c) VALUES (4, 1, 'Zvěstování');
INSERT INTO Cast_skladby (id, poradi, nazev_c) VALUES (4, 2, 'Sen');
INSERT INTO Cast_skladby (id, poradi, nazev_c) VALUES (4, 3, 'Snídaní Panny Marie');
INSERT INTO Cast_skladby (id, poradi, nazev_c) VALUES (4, 4, 'Obraz Panny Marie');

INSERT INTO Skladba (id, nazev) VALUES (5, 'Česká mše vánoční');
INSERT INTO Autorstvi (jmeno, prijmeni, id, je_uprava) VALUES ('Jakub Jan', 'Ryba', 5, 0);
INSERT INTO Cast_skladby (id, poradi, nazev_c) VALUES (5, 1, 'Kyrie');
INSERT INTO Cast_skladby (id, poradi, nazev_c) VALUES (5, 2, 'Gloria');
INSERT INTO Cast_skladby (id, poradi, nazev_c) VALUES (5, 3, 'Graduale');
INSERT INTO Cast_skladby (id, poradi, nazev_c) VALUES (5, 4, 'Credo');
INSERT INTO Cast_skladby (id, poradi, nazev_c) VALUES (5, 5, 'Offertorium');
INSERT INTO Cast_skladby (id, poradi, nazev_c) VALUES (5, 6, 'Sanctus');
INSERT INTO Cast_skladby (id, poradi, nazev_c) VALUES (5, 7, 'Benedictus');
INSERT INTO Cast_skladby (id, poradi, nazev_c) VALUES (5, 8, 'Agnus Dei');
INSERT INTO Cast_skladby (id, poradi, nazev_c) VALUES (5, 9, 'Communio');

INSERT INTO Skladba (id, nazev) VALUES (6, 'Čerešničky, čerešničky');
INSERT INTO Autorstvi (jmeno, prijmeni, id, je_uprava) VALUES ('Martin', 'Grobár', 6, 1);

INSERT INTO Skladba (id, nazev) VALUES (7, 'Už vedou Martina');
INSERT INTO Autorstvi (jmeno, prijmeni, id, je_uprava) VALUES ('Zdeněk', 'Lukáš', 7, 1);

INSERT INTO Skladba (id, nazev) VALUES (8, 'Na horách');
INSERT INTO Autorstvi (jmeno, prijmeni, id, je_uprava) VALUES ('Zdeněk', 'Lukáš', 8, 1);

INSERT INTO Skladba (id, nazev) VALUES (9, 'Pater noster');
INSERT INTO Autorstvi (jmeno, prijmeni, id, je_uprava) VALUES ('Zdeněk', 'Lukáš', 9, 0);

INSERT INTO Skladba (id, nazev) VALUES (10, 'Ride the Chariot');

INSERT INTO Skladba (id, nazev) VALUES (11, 'Soon Ah Will Be Done');

INSERT INTO Skladba (id, nazev) VALUES (12, 'Nobody knows');

INSERT INTO Skladba (id, nazev) VALUES (13, 'Steel Away');

INSERT INTO Skladba (id, nazev) VALUES (14, 'Hymnus');
INSERT INTO Autorstvi (jmeno, prijmeni, id, je_uprava) VALUES ('Antonín', 'Dvořák', 14, 0);

INSERT INTO Skladba (id, nazev) VALUES (15, 'Chorál');
INSERT INTO Autorstvi (jmeno, prijmeni, id, je_uprava) VALUES ('Johann Sebastian', 'Bach', 15, 0);

INSERT INTO Skladba (id, nazev) VALUES (16, 'Flos florum');
INSERT INTO Autorstvi (jmeno, prijmeni, id, je_uprava) VALUES ('Jan', 'Hanuš', 16, 0);
INSERT INTO Cast_skladby (id, poradi, nazev_c) VALUES (16, 1, 'Ave Maria');
INSERT INTO Cast_skladby (id, poradi, nazev_c) VALUES (16, 2, 'Salve regina');
INSERT INTO Cast_skladby (id, poradi, nazev_c) VALUES (16, 3, 'Flos florum');

INSERT INTO Skladba (id, nazev) VALUES (17, 'Čarování a pomluvy');
INSERT INTO Autorstvi (jmeno, prijmeni, id, je_uprava) VALUES ('Bohuslav', 'Martinů', 17, 0);

INSERT INTO Skladba (id, nazev) VALUES (18, 'Chceme my se chceme');
INSERT INTO Autorstvi (jmeno, prijmeni, id, je_uprava) VALUES ('Bohuslav', 'Martinů', 18, 0);

INSERT INTO Skladba (id, nazev) VALUES (19, 'Trois chansons');
INSERT INTO Autorstvi (jmeno, prijmeni, id, je_uprava) VALUES ('Claude', 'Debussy', 19, 0);
INSERT INTO Cast_skladby (id, poradi, nazev_c) VALUES (19, 1, 'Dieu! qu''il la fait');
INSERT INTO Cast_skladby (id, poradi, nazev_c) VALUES (19, 2, 'Quant j''ai ouy le tabourin');
INSERT INTO Cast_skladby (id, poradi, nazev_c) VALUES (19, 3, 'Yver');

INSERT INTO Skladba (id, nazev) VALUES (20, 'Ave verum corpus');
INSERT INTO Autorstvi (jmeno, prijmeni, id, je_uprava) VALUES ('Wolfgang Amadeus', 'Mozart', 20, 0);

INSERT INTO Skladba (id, nazev) VALUES (21, 'Jede sedlák do mlejna');
INSERT INTO Autorstvi (jmeno, prijmeni, id, je_uprava) VALUES ('Jaroslav', 'Krček', 21, 1);

INSERT INTO Skladba (id, nazev) VALUES (22, 'Tichá noc');
INSERT INTO Autorstvi (jmeno, prijmeni, id, je_uprava) VALUES ('Franz Xaver', 'Gruber', 22, 0);
INSERT INTO Autorstvi (jmeno, prijmeni, id, je_uprava) VALUES ('Martin', 'Grobár', 22, 1);

INSERT INTO Skladba (id, nazev) VALUES (23, 'Spi, spi, neviňátko');
INSERT INTO Autorstvi (jmeno, prijmeni, id, je_uprava) VALUES ('Jakub Jan', 'Ryba', 23, 0);

INSERT INTO Skladba (id, nazev) VALUES (24, 'Jedna je cestička k Táboru');
INSERT INTO Autorstvi (jmeno, prijmeni, id, je_uprava) VALUES ('Jaroslav', 'Krček', 24, 1);



--------------------------------------------noty----------------------------------------------------

INSERT INTO Publikace (kat_cislo, nazev_p, varianta) VALUES (1, 'České lidové písně', 'SATB');
INSERT INTO Zarazeni (kat_cislo, id) SELECT 1, id FROM Skladba
	WHERE nazev IN ('Čerešničky, čerešničky', 'Už vedou Martina', 'Na horách',
			'Jede sedlák do mlejna', 'Jedna je cestička k Táboru');

INSERT INTO Publikace (kat_cislo, nazev_p, varianta) VALUES (2, 'Four Negro Spirituals', 'SATB');
INSERT INTO Zarazeni (kat_cislo, id) SELECT 2, id FROM Skladba
	WHERE nazev IN ('Ride the Chariot', 'Soon Ah Will Be Done', 'Nobody knows', 'Steel Away');

INSERT INTO Publikace (kat_cislo, nazev_p, varianta) VALUES (3, 'Dvořák: Lužanská mše', 'SATB');
INSERT INTO Zarazeni (kat_cislo, id) SELECT 3, id FROM Skladba WHERE nazev='Mše D dur';

INSERT INTO Publikace (kat_cislo, nazev_p, varianta) VALUES (4, 'Napadly písně', 'S');
INSERT INTO Publikace (kat_cislo, nazev_p, varianta) VALUES (5, 'Napadly písně', 'A');
INSERT INTO Publikace (kat_cislo, nazev_p, varianta) VALUES (6, 'Napadly písně', 'T');
INSERT INTO Publikace (kat_cislo, nazev_p, varianta) VALUES (7, 'Napadly písně', 'B');
INSERT INTO Zarazeni (kat_cislo, id) SELECT kat_cislo, id FROM Publikace, Skladba
	WHERE nazev='Napadly písně' AND nazev_p='Napadly písně';

INSERT INTO Publikace (kat_cislo, nazev_p, varianta) VALUES (9, 'Hej, mistře!', 'S');
INSERT INTO Publikace (kat_cislo, nazev_p, varianta) VALUES (10, 'Hej, mistře!', 'A');
INSERT INTO Publikace (kat_cislo, nazev_p, varianta) VALUES (11, 'Hej, mistře!', 'T');
INSERT INTO Publikace (kat_cislo, nazev_p, varianta) VALUES (12, 'Hej, mistře!', 'B');
INSERT INTO Zarazeni (kat_cislo, id) SELECT kat_cislo, id FROM Publikace, Skladba
	WHERE nazev='Česká mše vánoční' AND nazev_p='Hej, mistře!';

INSERT INTO Publikace (kat_cislo, nazev_p, varianta) VALUES (13, 'Flos florum', 'SATB');
INSERT INTO Zarazeni (kat_cislo, id) SELECT 13, id FROM Skladba WHERE nazev='Flos florum';

INSERT INTO Publikace (kat_cislo, nazev_p, varianta) VALUES (14, 'Sborová tvorba B. Martinů', 'SATB');
INSERT INTO Zarazeni (kat_cislo, id) SELECT 14, id FROM Skladba
	WHERE nazev IN ('Čtyři písně o Marii', 'Čarování a pomluvy', 'Chceme my se chceme');

INSERT INTO Publikace (kat_cislo, nazev_p, varianta) VALUES (15, 'Lukáš: Pater noster', 'SATB');
INSERT INTO Zarazeni (kat_cislo, id) SELECT 15, id FROM Skladba WHERE nazev='Pater noster';

INSERT INTO Publikace (kat_cislo, nazev_p, varianta) VALUES (16, 'Debussy: Trois chansons', 'SATB');
INSERT INTO Zarazeni (kat_cislo, id) SELECT 16, id FROM Skladba WHERE nazev='Trois chansons';

INSERT INTO Vytisk (kat_cislo, cislo_exemplare) SELECT kat_cislo, 1 FROM Publikace WHERE varianta='SATB';
INSERT INTO Vytisk (kat_cislo, cislo_exemplare) SELECT kat_cislo, 2 FROM Publikace WHERE varianta='SATB';
INSERT INTO Vytisk (kat_cislo, cislo_exemplare) SELECT kat_cislo, 3 FROM Publikace WHERE varianta='SATB';
INSERT INTO Vytisk (kat_cislo, cislo_exemplare) SELECT kat_cislo, 4 FROM Publikace WHERE varianta='SATB';
INSERT INTO Vytisk (kat_cislo, cislo_exemplare) SELECT kat_cislo, 5 FROM Publikace WHERE varianta='SATB';
INSERT INTO Vytisk (kat_cislo, cislo_exemplare) SELECT kat_cislo, 6 FROM Publikace WHERE varianta='SATB';
INSERT INTO Vytisk (kat_cislo, cislo_exemplare) SELECT kat_cislo, 7 FROM Publikace WHERE varianta='SATB';
INSERT INTO Vytisk (kat_cislo, cislo_exemplare) SELECT kat_cislo, 8 FROM Publikace WHERE varianta='SATB';
INSERT INTO Vytisk (kat_cislo, cislo_exemplare) SELECT kat_cislo, 9 FROM Publikace WHERE varianta='SATB';
INSERT INTO Vytisk (kat_cislo, cislo_exemplare) SELECT kat_cislo, 10 FROM Publikace WHERE varianta='SATB';
INSERT INTO Vytisk (kat_cislo, cislo_exemplare) SELECT kat_cislo, 11 FROM Publikace WHERE varianta='SATB';
INSERT INTO Vytisk (kat_cislo, cislo_exemplare) SELECT kat_cislo, 12 FROM Publikace WHERE varianta='SATB';
INSERT INTO Vytisk (kat_cislo, cislo_exemplare) SELECT kat_cislo, 13 FROM Publikace WHERE varianta='SATB';
INSERT INTO Vytisk (kat_cislo, cislo_exemplare) SELECT kat_cislo, 14 FROM Publikace WHERE varianta='SATB';
INSERT INTO Vytisk (kat_cislo, cislo_exemplare) SELECT kat_cislo, 15 FROM Publikace WHERE varianta='SATB';
INSERT INTO Vytisk (kat_cislo, cislo_exemplare) SELECT kat_cislo, 16 FROM Publikace WHERE varianta='SATB';
INSERT INTO Vytisk (kat_cislo, cislo_exemplare) SELECT kat_cislo, 17 FROM Publikace WHERE varianta='SATB';

INSERT INTO Vytisk (kat_cislo, cislo_exemplare) SELECT kat_cislo, 1 FROM Publikace WHERE varianta IN ('S', 'A', 'T', 'B');
INSERT INTO Vytisk (kat_cislo, cislo_exemplare) SELECT kat_cislo, 2 FROM Publikace WHERE varianta IN ('S', 'A', 'T', 'B');
INSERT INTO Vytisk (kat_cislo, cislo_exemplare) SELECT kat_cislo, 3 FROM Publikace WHERE varianta IN ('S', 'A', 'T', 'B');
INSERT INTO Vytisk (kat_cislo, cislo_exemplare) SELECT kat_cislo, 4 FROM Publikace WHERE varianta IN ('S', 'A', 'T', 'B');
INSERT INTO Vytisk (kat_cislo, cislo_exemplare) SELECT kat_cislo, 5 FROM Publikace WHERE varianta IN ('S', 'A', 'T', 'B');

DELETE FROM Vytisk WHERE kat_cislo IN (6, 7) AND cislo_exemplare=5;


--rozdat všem po exempláři
INSERT INTO Zapujcka_not (RC, cislo_exemplare, kat_cislo)
	SELECT RC, 1, kat_cislo FROM Zpevak, Publikace where RC=5953014321 and varianta='SATB';
INSERT INTO Zapujcka_not (RC, cislo_exemplare, kat_cislo)
	SELECT RC, 1, kat_cislo FROM Zpevak, Publikace where RC=5953014321 and varianta='S';
INSERT INTO Zapujcka_not (RC, cislo_exemplare, kat_cislo)
	SELECT RC, 2, kat_cislo FROM Zpevak, Publikace where RC=7263225148 and varianta='SATB';
INSERT INTO Zapujcka_not (RC, cislo_exemplare, kat_cislo)
	SELECT RC, 2, kat_cislo FROM Zpevak, Publikace where RC=7263225148 and varianta='S';
INSERT INTO Zapujcka_not (RC, cislo_exemplare, kat_cislo)
	SELECT RC, 3, kat_cislo FROM Zpevak, Publikace where RC=7061135522 and varianta='SATB';
INSERT INTO Zapujcka_not (RC, cislo_exemplare, kat_cislo)
	SELECT RC, 3, kat_cislo FROM Zpevak, Publikace where RC=7061135522 and varianta='S';
INSERT INTO Zapujcka_not (RC, cislo_exemplare, kat_cislo)
	SELECT RC, 4, kat_cislo FROM Zpevak, Publikace where RC=6457021234 and varianta='SATB';
INSERT INTO Zapujcka_not (RC, cislo_exemplare, kat_cislo)
	SELECT RC, 4, kat_cislo FROM Zpevak, Publikace where RC=6457021234 and varianta='S';
INSERT INTO Zapujcka_not (RC, cislo_exemplare, kat_cislo)
	SELECT RC, 5, kat_cislo FROM Zpevak, Publikace where RC=6555151278 and varianta='SATB';
INSERT INTO Zapujcka_not (RC, cislo_exemplare, kat_cislo)
	SELECT RC, 1, kat_cislo FROM Zpevak, Publikace where RC=6555151278 and varianta='A';
INSERT INTO Zapujcka_not (RC, cislo_exemplare, kat_cislo)
	SELECT RC, 6, kat_cislo FROM Zpevak, Publikace where RC=6958216542 and varianta='SATB';
INSERT INTO Zapujcka_not (RC, cislo_exemplare, kat_cislo)
	SELECT RC, 2, kat_cislo FROM Zpevak, Publikace where RC=6958216542 and varianta='A';
INSERT INTO Zapujcka_not (RC, cislo_exemplare, kat_cislo)
	SELECT RC, 7, kat_cislo FROM Zpevak, Publikace where RC=4951032446 and varianta='SATB';
INSERT INTO Zapujcka_not (RC, cislo_exemplare, kat_cislo)
	SELECT RC, 3, kat_cislo FROM Zpevak, Publikace where RC=4951032446 and varianta='A';
INSERT INTO Zapujcka_not (RC, cislo_exemplare, kat_cislo)
	SELECT RC, 8, kat_cislo FROM Zpevak, Publikace where RC=5561032947 and varianta='SATB';
INSERT INTO Zapujcka_not (RC, cislo_exemplare, kat_cislo)
	SELECT RC, 4, kat_cislo FROM Zpevak, Publikace where RC=5561032947 and varianta='A';
INSERT INTO Zapujcka_not (RC, cislo_exemplare, kat_cislo)
	SELECT RC, 9, kat_cislo FROM Zpevak, Publikace where RC=6706062648 and varianta='SATB';
INSERT INTO Zapujcka_not (RC, cislo_exemplare, kat_cislo)
	SELECT RC, 1, kat_cislo FROM Zpevak, Publikace where RC=6706062648 and varianta='T';
INSERT INTO Zapujcka_not (RC, cislo_exemplare, kat_cislo)
	SELECT RC, 10, kat_cislo FROM Zpevak, Publikace where RC=7622263841 and varianta='SATB';
INSERT INTO Zapujcka_not (RC, cislo_exemplare, kat_cislo)
	SELECT RC, 2, kat_cislo FROM Zpevak, Publikace where RC=7622263841 and varianta='T';
INSERT INTO Zapujcka_not (RC, cislo_exemplare, kat_cislo)
	SELECT RC, 11, kat_cislo FROM Zpevak, Publikace where RC=5402022234 and varianta='SATB';
INSERT INTO Zapujcka_not (RC, cislo_exemplare, kat_cislo)
	SELECT RC, 3, kat_cislo FROM Zpevak, Publikace where RC=5402022234 and varianta='T';
INSERT INTO Zapujcka_not (RC, cislo_exemplare, kat_cislo)
	SELECT RC, 12, kat_cislo FROM Zpevak, Publikace where RC=7007073737 and varianta='SATB';
INSERT INTO Zapujcka_not (RC, cislo_exemplare, kat_cislo)
	SELECT RC, 4, kat_cislo FROM Zpevak, Publikace where RC=7007073737 and varianta='T';
INSERT INTO Zapujcka_not (RC, cislo_exemplare, kat_cislo)
	SELECT RC, 13, kat_cislo FROM Zpevak, Publikace where RC=6903034321 and varianta='SATB';
INSERT INTO Zapujcka_not (RC, cislo_exemplare, kat_cislo)
	SELECT RC, 1, kat_cislo FROM Zpevak, Publikace where RC=6903034321 and varianta='B';
INSERT INTO Zapujcka_not (RC, cislo_exemplare, kat_cislo)
	SELECT RC, 14, kat_cislo FROM Zpevak, Publikace where RC=5901012681 and varianta='SATB';
INSERT INTO Zapujcka_not (RC, cislo_exemplare, kat_cislo)
	SELECT RC, 2, kat_cislo FROM Zpevak, Publikace where RC=5901012681 and varianta='B';
INSERT INTO Zapujcka_not (RC, cislo_exemplare, kat_cislo)
	SELECT RC, 15, kat_cislo FROM Zpevak, Publikace where RC=6508283452 and varianta='SATB';
INSERT INTO Zapujcka_not (RC, cislo_exemplare, kat_cislo)
	SELECT RC, 3, kat_cislo FROM Zpevak, Publikace where RC=6508283452 and varianta='B';
INSERT INTO Zapujcka_not (RC, cislo_exemplare, kat_cislo)
	SELECT RC, 16, kat_cislo FROM Zpevak, Publikace where RC=7711124554 and varianta='SATB';
INSERT INTO Zapujcka_not (RC, cislo_exemplare, kat_cislo)
	SELECT RC, 4, kat_cislo FROM Zpevak, Publikace where RC=7711124554 and varianta='B';

--nějaké výtisky odebrat - pro zajímavost
DELETE FROM Zapujcka_not WHERE kat_cislo=16 AND RC IN (7711124554, 5402022234, 6706062648);
DELETE FROM Zapujcka_not WHERE kat_cislo=14 AND RC IN (7711124554, 7263225148, 7007073737);
DELETE FROM Zapujcka_not WHERE kat_cislo=13 AND RC IN (7711124554, 6903034321);





-------------------------------akce-------------------------------------------------------
ALTER SESSION SET NLS_DATE_FORMAT='DD.MM.YYYY HH24:MI';

INSERT INTO Udalost (misto, cas) VALUES ('Kostel sv. Antonína, Strossmayerovo nám.', '01.10.2004 19:00');
INSERT INTO Udalost (misto, cas) VALUES ('Sál Martinů, Malostranské nám.', '02.10.2004 17:30');
INSERT INTO Udalost (misto, cas) VALUES ('Kulturní dům Modřany', '22.10.2004 19:00');
INSERT INTO Udalost (misto, cas) VALUES ('Kostel sv. Mikuláše, Malostranské nám.', '01.12.2004 18:00');
INSERT INTO Udalost (misto, cas) VALUES ('Staroměstské náměstí', '18.12.2004 15:00');
INSERT INTO Udalost (misto, cas) VALUES ('Kostel sv. Martina ve zdi', '22.12.2004 19:30');
INSERT INTO Udalost (misto, cas) VALUES ('Městská knihovna, Mariánské náměstí', '18.01.2005 17:30');
INSERT INTO Udalost (misto, cas) VALUES ('Kostel sv. Antonína, Strossmayerovo nám.', '02.02.2005 19:00');

INSERT INTO Koncert (misto, cas, vstupne, divaku)
	SELECT misto, cas, mod(rownum*10, 50)+50, mod(rownum*34, 100)+30 FROM Udalost;

INSERT INTO Cast_programu (misto, cas, id) SELECT misto, cas, id
	FROM Koncert, Skladba WHERE nazev='Mše D dur' AND cas IN('01.10.2004 19:00', '02.10.2004 17:30');
INSERT INTO Cast_programu (misto, cas, id) SELECT misto, cas, id 
	FROM Koncert, Skladba NATURAL JOIN Zarazeni NATURAL JOIN Publikace
	WHERE nazev_p IN ('Four Negro Spirituals', 'České lidové písně') 
	AND cas IN('22.10.2004 19:00', '18.12.2004 15:00');
INSERT INTO Cast_programu (misto, cas, id) SELECT misto, cas, id
	FROM Koncert, Skladba WHERE nazev='Česká mše vánoční' AND cas IN('01.12.2004 18:00', '22.12.2004 19:30');
INSERT INTO Cast_programu (misto, cas, id) SELECT misto, cas, id
	FROM Koncert, Skladba NATURAL JOIN Autorstvi NATURAL JOIN Skladatel
	WHERE prijmeni IN ('Martinů', 'Debussy', 'Lukáš') AND cas='18.01.2005 17:30';
INSERT INTO Cast_programu (misto, cas, id) SELECT misto, cas, id
	FROM Koncert, Skladba NATURAL JOIN Autorstvi NATURAL JOIN Skladatel
	WHERE prijmeni IN ('Martinů', 'Hanuš') AND cas='02.02.2005 19:00';

INSERT INTO Udalost (misto, cas) VALUES ('Velká zkušebna', '02.09.2004 19:00');
INSERT INTO Udalost (misto, cas) VALUES ('Malá zkušebna',  '02.09.2004 19:00');
INSERT INTO Udalost (misto, cas) VALUES ('Velká zkušebna', '09.09.2004 19:00');
INSERT INTO Udalost (misto, cas) VALUES ('Malá zkušebna',  '09.09.2004 19:00');
INSERT INTO Udalost (misto, cas) VALUES ('Velká zkušebna', '16.09.2004 19:00');
INSERT INTO Udalost (misto, cas) VALUES ('Velká zkušebna', '23.09.2004 19:00');
INSERT INTO Udalost (misto, cas) VALUES ('Velká zkušebna', '30.09.2004 19:00');
INSERT INTO Udalost (misto, cas) VALUES ('Velká zkušebna', '07.10.2004 19:00');
INSERT INTO Udalost (misto, cas) VALUES ('Velká zkušebna', '14.10.2004 19:00');
INSERT INTO Udalost (misto, cas) VALUES ('Velká zkušebna', '21.10.2004 19:00');
INSERT INTO Udalost (misto, cas) VALUES ('Velká zkušebna', '04.11.2004 19:00');
INSERT INTO Udalost (misto, cas) VALUES ('Malá zkušebna',  '04.11.2004 19:00');
INSERT INTO Udalost (misto, cas) VALUES ('Velká zkušebna', '11.11.2004 19:00');
INSERT INTO Udalost (misto, cas) VALUES ('Velká zkušebna', '18.11.2004 19:00');
INSERT INTO Udalost (misto, cas) VALUES ('Velká zkušebna', '25.11.2004 19:00');
INSERT INTO Udalost (misto, cas) VALUES ('Velká zkušebna', '02.12.2004 19:00');
INSERT INTO Udalost (misto, cas) VALUES ('Velká zkušebna', '09.12.2004 19:00');
INSERT INTO Udalost (misto, cas) VALUES ('Velká zkušebna', '16.12.2004 19:00');
INSERT INTO Udalost (misto, cas) VALUES ('Velká zkušebna', '03.01.2005 19:00'); 
INSERT INTO Udalost (misto, cas) VALUES ('Malá zkušebna',  '03.01.2005 19:00'); 
INSERT INTO Udalost (misto, cas) VALUES ('Velká zkušebna', '10.01.2005 19:00'); 
INSERT INTO Udalost (misto, cas) VALUES ('Velká zkušebna', '17.01.2005 19:00'); 
INSERT INTO Udalost (misto, cas) VALUES ('Velká zkušebna', '24.01.2005 19:00'); 
INSERT INTO Udalost (misto, cas) VALUES ('Velká zkušebna', '31.01.2005 19:00'); 

INSERT INTO Zkouska (misto, cas, je_generalka)
	SELECT misto, cas, 0 FROM Udalost WHERE misto LIKE '%zkušebna';

UPDATE Zkouska SET je_generalka=1
	WHERE cas IN ('30.09.2004 19:00', '21.10.2004 19:00', '17.01.2005 19:00');

INSERT INTO Cast_programu (misto, cas, id) SELECT misto, cas, id
	FROM Zkouska, Skladba WHERE nazev='Mše D dur' AND cas < '02.10.2004 17:30';
INSERT INTO Cast_programu (misto, cas, id) SELECT misto, cas, id
	FROM Zkouska, Skladba WHERE (cas BETWEEN '23.09.2004 19:00' AND '11.11.2004 19:00')
	AND nazev IN ('Čerešničky, čerešničky', 'Už vedou Martina', 'Na horách');
INSERT INTO Cast_programu (misto, cas, id) SELECT misto, cas, id
	FROM Zkouska, Skladba WHERE (cas BETWEEN '02.10.2004 17:30' AND '11.11.2004 19:00')
	AND nazev IN ('Jede sedlák do mlejna', 'Jedna je cestička k Táboru');
INSERT INTO Cast_programu (misto, cas, id) SELECT misto, cas, id
	FROM Zkouska, Skladba WHERE (cas BETWEEN '11.11.2004 19:00' AND '17.12.2004 19:00')
	AND nazev='Česká mše vánoční';
INSERT INTO Cast_programu (misto, cas, id) SELECT misto, cas, id
	FROM Zkouska, Skladba NATURAL JOIN Autorstvi NATURAL JOIN Skladatel
	WHERE prijmeni IN ('Martinů', 'Debussy', 'Lukáš') AND
	(cas BETWEEN '01.01.2005 00:00' AND '18.01.2005 17:30');
INSERT INTO Cast_programu (misto, cas, id) SELECT misto, cas, id
	FROM Zkouska, Skladba NATURAL JOIN Autorstvi NATURAL JOIN Skladatel
	WHERE prijmeni IN ('Martinů', 'Hanuš') AND
	(cas BETWEEN '18.01.2005 17:30' AND '02.02.2005 19:00');

INSERT INTO Ucast (RC, misto, cas, pritomen) SELECT RC, misto, cas, 1
	FROM Zpevak, Udalost;
--dělené zkoušky
DELETE FROM Ucast WHERE cas IN ('02.09.2004 19:00', '09.09.2004 19:00', '04.11.2004 19:00', '03.01.2005 19:00')
	AND misto='Velká zkušebna' AND RC IN (SELECT RC FROM Zpevak WHERE hlas IN ('T', 'B'));
DELETE FROM Ucast WHERE cas IN ('02.09.2004 19:00', '09.09.2004 19:00', '04.11.2004 19:00', '03.01.2005 19:00')
	AND misto='Malá zkušebna' AND RC IN (SELECT RC FROM Zpevak WHERE hlas NOT IN ('T', 'B'));
--přišli do sboru později
DELETE FROM Ucast WHERE cas < '23.09.2004 19:00' AND RC=5561032947;
DELETE FROM Ucast WHERE cas < '02.12.2004 19:00' AND RC=6508283452;
--neúčast
UPDATE Ucast SET pritomen=0 WHERE cas='02.09.2004 19:00' AND RC IN(5953014321, 6555151278, 6706062648, 7711124554);
UPDATE Ucast SET pritomen=0 WHERE cas='09.09.2004 19:00' AND RC IN(7263225148, 7007073737, 7711124554);
UPDATE Ucast SET pritomen=0 WHERE cas='23.09.2004 19:00' AND RC=5953014321;
UPDATE Ucast SET pritomen=0 WHERE cas='07.10.2004 19:00' AND RC IN(4951032446, 6706062648);
UPDATE Ucast SET pritomen=0 WHERE cas='21.10.2004 19:00' AND RC=6958216542;
UPDATE Ucast SET pritomen=0 WHERE cas='04.11.2004 19:00' AND RC=7711124554;
UPDATE Ucast SET pritomen=0 WHERE cas='18.11.2004 19:00' AND RC IN(7061135522, 6508283452);
UPDATE Ucast SET pritomen=0 WHERE cas='25.11.2004 19:00' AND RC IN(7263225148, 6457021234);
UPDATE Ucast SET pritomen=0 WHERE cas='09.12.2004 19:00' AND RC IN(7007073737, 5402022234);
UPDATE Ucast SET pritomen=0 WHERE cas='03.01.2005 19:00' AND RC=6555151278;
UPDATE Ucast SET pritomen=0 WHERE cas='10.01.2005 19:00' AND RC IN(7007073737, 7711124554);
UPDATE Ucast SET pritomen=0 WHERE cas='24.01.2005 19:00' AND RC=7711124554;
UPDATE Ucast SET pritomen=0 WHERE cas='31.01.2005 19:00' AND RC=7711124554;

UPDATE Ucast SET pritomen=0 WHERE cas='02.10.2004 17:30' AND RC IN(6508283452, 7711124554);
UPDATE Ucast SET pritomen=0 WHERE cas='22.10.2004 19:00' AND RC=5953014321;
UPDATE Ucast SET pritomen=0 WHERE cas='02.09.2004 19:00' AND RC=5901012681;
UPDATE Ucast SET pritomen=0 WHERE cas='02.02.2005 19:00' AND RC IN(6457021234, 7711124554);

COMMIT;
