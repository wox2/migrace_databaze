--Provedené zmìny:
--1) zmìnil jsem názvy atributù v roli cizích klíèù tak, aby bylo mo¾né pou¾ívat pøirozené spojení,
--   tj. odstranil jsem pøedpony pøidané ER modeláøem
--2) pøenesl jsem definici integritních omezení (zejména cizích klíèù) ji¾ do pøíkazù pro vytvoøení
--   pøíslu¹né tabulky - ve snaze o snadnìj¹í orientaci (aby bylo jasné, na co který cizí klíè odkazuje,
--   kdy¾ u¾ jsem je pøejmenoval); v souvislosti s tím jsem zmìnil poøadí tabulek ve skriptu
--3) pøidal jsem pohled, který vyu¾iji v jednom z dotazù

SET AUTOCOMMIT ON;


DROP TABLE Skladatel		CASCADE CONSTRAINTS;
DROP TABLE Publikace		CASCADE CONSTRAINTS;
DROP TABLE Saty				CASCADE CONSTRAINTS;
DROP TABLE Vytisk			CASCADE CONSTRAINTS;
DROP TABLE Skladba			CASCADE CONSTRAINTS;
DROP TABLE Udalost			CASCADE CONSTRAINTS;
DROP TABLE Koncert			CASCADE CONSTRAINTS;
DROP TABLE Zkouska			CASCADE CONSTRAINTS;
DROP TABLE Cast_skladby		CASCADE CONSTRAINTS;
DROP TABLE Hlas				CASCADE CONSTRAINTS;
DROP TABLE Vedeni			CASCADE CONSTRAINTS;
DROP TABLE Zarazeni			CASCADE CONSTRAINTS;
DROP TABLE Zapujcka_satu	CASCADE CONSTRAINTS;
DROP TABLE Cast_programu	CASCADE CONSTRAINTS;
DROP TABLE Zapujcka_not		CASCADE CONSTRAINTS;
DROP TABLE Autorstvi		CASCADE CONSTRAINTS;
DROP TABLE Zpevak			CASCADE CONSTRAINTS;
DROP TABLE Ucast			CASCADE CONSTRAINTS;
DROP VIEW Rating_skladatelu;


CREATE TABLE Hlas (
      zkratka VARCHAR2(5) NOT NULL,
      nazev_h VARCHAR2(30) NOT NULL,
      CONSTRAINT PK_Hlas PRIMARY KEY (zkratka)
);
CREATE TABLE Zpevak (
      RC NUMBER(10, 0) NOT NULL,
      jmeno VARCHAR2(30) NOT NULL,
      prijmeni VARCHAR2(30) NOT NULL,
      adresa VARCHAR2(255) NULL,
      telefon NUMBER(9, 0) NULL,
      email VARCHAR2(50) NULL,
      hlas VARCHAR2(5) NOT NULL,
      CONSTRAINT PK_Zpevak PRIMARY KEY (RC),
      CONSTRAINT FK_Zpevak_1 FOREIGN KEY (hlas) REFERENCES Hlas(zkratka)
);
CREATE TABLE Vedeni (
      RC NUMBER(10, 0) NOT NULL,
      zkratka VARCHAR2(5) NOT NULL,
      Constraint UNQ_Vedeni_1 UNIQUE (RC),
      Constraint UNQ_Vedeni_2 UNIQUE (zkratka),
      Constraint FK_Vedeni_1 FOREIGN KEY (RC) REFERENCES Zpevak(RC),
      Constraint FK_Vedeni_2 FOREIGN KEY (zkratka) REFERENCES Hlas(zkratka)
);

CREATE TABLE Saty (
      inv_cislo INTEGER NOT NULL,
      velikost INTEGER NOT NULL,
      CONSTRAINT PK_Saty PRIMARY KEY (inv_cislo)
);
CREATE TABLE Zapujcka_satu (
      RC NUMBER(10, 0) NOT NULL,
      inv_cislo INTEGER NOT NULL,
      CONSTRAINT UNQ_Zapujcka_satu_1 UNIQUE (RC),
      CONSTRAINT UNQ_Zapujcka_satu_2 UNIQUE (inv_cislo),
      CONSTRAINT FK_Zapujcka_satu_1 FOREIGN KEY (RC) REFERENCES Zpevak(RC),
      CONSTRAINT FK_Zapujcka_satu_2 FOREIGN KEY (inv_cislo) REFERENCES Saty(inv_cislo)
);


CREATE TABLE Skladatel (
      jmeno VARCHAR2(30) NOT NULL,
      prijmeni VARCHAR2(30) NOT NULL,
      rok_narozeni INTEGER NULL,
      rok_umrti INTEGER NULL,
      CONSTRAINT PK_Skladatel PRIMARY KEY (jmeno, prijmeni)
);
CREATE TABLE Skladba (
      id INTEGER NOT NULL,
      Nazev VARCHAR2(255) NOT NULL,
      rok_vzniku INTEGER NULL,
      durata INTEGER NULL,
      opus_c INTEGER NULL,
      CONSTRAINT PK_Skladba PRIMARY KEY (id)
);
CREATE TABLE Cast_skladby (
      id INTEGER NOT NULL,
      nazev_c VARCHAR2(50) NOT NULL,
      poradi INTEGER NOT NULL,
      CONSTRAINT PK_Cast_skladby PRIMARY KEY (id, poradi),
      CONSTRAINT FK_Cast_skladby FOREIGN KEY (id) REFERENCES Skladba(id)
);
CREATE TABLE Autorstvi (
      id INTEGER NOT NULL,
      jmeno VARCHAR2(30) NOT NULL,
      prijmeni VARCHAR2(30) NOT NULL,
      je_uprava INTEGER NOT NULL,
      CONSTRAINT UNQ_Autorstvi_1 UNIQUE (id, jmeno, prijmeni),
      CONSTRAINT FK_Autorstvi_1 FOREIGN KEY (id) REFERENCES Skladba(id),
      CONSTRAINT FK_Autorstvi_2 FOREIGN KEY (jmeno, prijmeni) REFERENCES Skladatel(jmeno, prijmeni)
);
CREATE TABLE Publikace (
      kat_cislo INTEGER NOT NULL,
      nazev_p VARCHAR2(100) NOT NULL,
      varianta VARCHAR2(20) NULL,
      pocet_stran INTEGER NULL,
      CONSTRAINT PK_Publikace PRIMARY KEY (kat_cislo)
);
CREATE TABLE Vytisk (
      cislo_exemplare INTEGER NOT NULL,
      kat_cislo INTEGER NOT NULL,
      CONSTRAINT PK_Vytisk PRIMARY KEY (cislo_exemplare, kat_cislo),
      CONSTRAINT FK_Vytisk FOREIGN KEY (kat_cislo) REFERENCES Publikace(kat_cislo)
);
CREATE TABLE Zarazeni (
      poradi INTEGER NULL,
      id INTEGER NOT NULL,
      kat_cislo INTEGER NOT NULL,
      CONSTRAINT UNQ_Zarazeni_1 UNIQUE (id, kat_cislo),
      CONSTRAINT FK_Zarazeni_1 FOREIGN KEY (id) REFERENCES Skladba(id),
      CONSTRAINT FK_Zarazeni_2 FOREIGN KEY (kat_cislo) REFERENCES Publikace(kat_cislo)
);


CREATE TABLE Udalost (
      misto VARCHAR2(100) NOT NULL,
      cas DATE NOT NULL,
      CONSTRAINT PK_Udalost PRIMARY KEY (misto, cas)
);
CREATE TABLE Koncert (
      misto VARCHAR2(100) NOT NULL,
      cas DATE NOT NULL,
      vstupne INTEGER NULL,
      divaku INTEGER NULL,
      CONSTRAINT PK_Koncert PRIMARY KEY (misto, cas),
      CONSTRAINT FK_Koncert FOREIGN KEY (misto, cas) REFERENCES Udalost(misto, cas)
);
CREATE TABLE Zkouska (
      misto VARCHAR2(100) NOT NULL,
      cas DATE NOT NULL,
      je_generalka INTEGER NULL,
      CONSTRAINT PK_Zkouska PRIMARY KEY (misto, cas),
      CONSTRAINT FK_Zkouska FOREIGN KEY (misto, cas) REFERENCES Udalost(misto, cas)
);
CREATE TABLE Cast_programu (
      poradi INTEGER NULL,
      id INTEGER NOT NULL,
      misto VARCHAR2(100) NOT NULL,
      cas DATE NOT NULL,
      CONSTRAINT UNQ_Cast_programu UNIQUE (id, misto, cas),
      CONSTRAINT FK_Cast_programu_1 FOREIGN KEY (id) REFERENCES Skladba(id),
      CONSTRAINT FK_Cast_programu_2 FOREIGN KEY (misto, cas) REFERENCES Udalost(misto, cas)
);


CREATE TABLE Zapujcka_not (
      cislo_exemplare INTEGER NOT NULL,
      kat_cislo INTEGER NOT NULL,
      RC NUMBER(10, 0) NOT NULL,
      CONSTRAINT UNQ_Zapujcka_not_1 UNIQUE (cislo_exemplare, kat_cislo),
      CONSTRAINT FK_Zapujcka_not_1 FOREIGN KEY (cislo_exemplare, kat_cislo) REFERENCES Vytisk(cislo_exemplare, kat_cislo),
      CONSTRAINT FK_Zapujcka_not_2 FOREIGN KEY (RC) REFERENCES Zpevak(RC)
);
CREATE TABLE Ucast (
      RC NUMBER(10, 0) NOT NULL,
      misto VARCHAR2(100) NOT NULL,
      cas DATE NOT NULL,
      pritomen INTEGER NOT NULL,
      CONSTRAINT UNQ_Ucast_1 UNIQUE (RC, misto, cas),
      CONSTRAINT FK_Ucast_1 FOREIGN KEY (RC) REFERENCES Zpevak(RC),
      CONSTRAINT FK_Ucast_2 FOREIGN KEY (misto, cas) REFERENCES Udalost(misto, cas)
);

--skladatelé a poèet uvedení jejich skladeb
CREATE VIEW rating_skladatelu AS SELECT jmeno, prijmeni, count(*) AS pocet 
			FROM Skladatel
				NATURAL JOIN Autorstvi
				NATURAL JOIN Skladba
				NATURAL JOIN Cast_programu
				NATURAL JOIN Koncert
			GROUP BY jmeno, prijmeni;
