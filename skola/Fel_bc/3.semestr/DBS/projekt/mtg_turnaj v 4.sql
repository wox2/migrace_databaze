Set Echo on
Purge Recyclebin;
/* používate-li verzi nižší než Oracle 10g,
 zakomentujte p?íkaz PURGE */
Drop table barva_obj cascade constraints;
Drop table system_obj cascade constraints;
Drop table kolo_obj cascade constraints;
Drop table rozhodci_obj cascade constraints;
Drop table typ_obj cascade constraints;
Drop table vysledek_obj cascade constraints;
Drop table druh_obj cascade constraints;
Drop table format_obj cascade constraints;
Drop table osoba_obj cascade constraints;
Drop table byl_rozhodcim_obj cascade constraints;
Drop table Rel8_obj cascade constraints;
Drop table zapas_obj cascade constraints;
Drop table turnaj_obj cascade constraints;
Drop table byl_hran_obj cascade constraints;
Drop table Deck_obj cascade constraints;
Drop table MTG_karta_obj cascade constraints;
Drop table obsahuje_obj cascade constraints;
Drop table hral_obj cascade constraints;
Drop type barva_t FORCE;
Drop type system_t FORCE;
Drop type kolo_t FORCE;
Drop type rozhodci_t FORCE;
Drop type typ_t FORCE;
Drop type vysledek_t FORCE;
Drop type druh_t FORCE;
Drop type format_t FORCE;
Drop type osoba_t FORCE;
Drop type byl_rozhodcim_t FORCE;
Drop type Rel8_t FORCE;
Drop type zapas_t FORCE;
Drop type turnaj_t FORCE;
Drop type byl_hran_t FORCE;
Drop type Deck_t FORCE;
Drop type MTG_karta_t FORCE;
Drop type obsahuje_t FORCE;
Drop type hral_t FORCE;
CREATE OR REPLACE TYPE barva_t AS OBJECT (
      id Integer,
      nazev Integer
);
/
CREATE OR REPLACE TYPE system_t AS OBJECT (
      popis VarChar2(255),
      nazev VarChar2(30),
      id Integer
);
/
CREATE OR REPLACE TYPE kolo_t AS OBJECT (
      cislo Integer
);
/
CREATE OR REPLACE TYPE rozhodci_t AS OBJECT (
      hlavni VarChar2(1),
      jmeno Integer,
      level Integer,
      prijmeni Integer,
      id Integer
);
/
CREATE OR REPLACE TYPE typ_t AS OBJECT (
      nazev VarChar2(20),
      id Integer
);
/
CREATE OR REPLACE TYPE vysledek_t AS OBJECT (
      hrac_2_vyhral_her Integer,
      hrac_1_vyhral_her Integer
);
/
CREATE OR REPLACE TYPE druh_t AS OBJECT (
      id Integer,
      nazev VarChar2(20)
);
/
CREATE OR REPLACE TYPE format_t AS OBJECT (
      id Integer,
      nazev VarChar2(255)
);
/
CREATE OR REPLACE TYPE osoba_t AS OBJECT (
      id Integer,
      jmeno VarChar2(20),
      prijmeni VarChar2(50)
);
/
CREATE OR REPLACE TYPE byl_rozhodcim_t AS OBJECT (
      mel_ro_id REF rozhodci_t,
      Rozhod_id REF turnaj_t
);
/
CREATE OR REPLACE TYPE Rel8_t AS OBJECT (
      kola_cislo REF kolo_t,
      zapas_hrac_2 REF zapas_t,
      zapas_hrac_1 REF zapas_t
);
/
CREATE OR REPLACE TYPE zapas_t AS OBJECT (
      hrac_2 Integer,
      hrac_1 Integer,
      poznamky VarChar2(255),
      bye Char(1)
);
/
CREATE OR REPLACE TYPE turnaj_t AS OBJECT (
      dolarova_dotace Integer,
      jina_dotace VarChar2(255),
      vstupne Integer,
      misto_konani VarChar2(255),
      datum Date,
      nazev VarChar2(20),
      id Integer,
      top8 VarChar2(1),
      pocet_ucastniku Integer
);
/
CREATE OR REPLACE TYPE byl_hran_t AS OBJECT (
      balice_id REF Deck_t,
      hral_n_id REF turnaj_t
);
/
CREATE OR REPLACE TYPE Deck_t AS OBJECT (
      id Integer,
      jmeno VarChar2(30),
      barvy VarChar2(30)
);
/
CREATE OR REPLACE TYPE MTG_karta_t AS OBJECT (
      casting_cost VarChar2(30),
      sb_pocet_kopii Integer,
      jmeno VarChar2(30),
      id Integer,
      md_pocet_kopii Integer
);
/
CREATE OR REPLACE TYPE obsahuje_t AS OBJECT (
      MTG_ka_id REF MTG_karta_t,
      obsahu_id REF Deck_t
);
/
CREATE OR REPLACE TYPE hral_t AS OBJECT (
      byl_hr_id REF Deck_t,
      hrala_id REF osoba_t
);
/
ALTER TYPE zapas_t ADD attribute zapasu_hrac_2_vyhral_her REF vysledek_t CASCADE;
ALTER TYPE zapas_t ADD attribute zapasu_hrac_1_vyhral_her REF vysledek_t CASCADE;
ALTER TYPE turnaj_t ADD attribute byl_fo_id REF format_t CASCADE;
ALTER TYPE turnaj_t ADD attribute kolo_cislo REF kolo_t CASCADE;
ALTER TYPE turnaj_t ADD attribute byl_hr_id REF system_t CASCADE;
ALTER TYPE Deck_t ADD attribute druh_id REF druh_t CASCADE;
ALTER TYPE MTG_karta_t ADD attribute karta_id REF typ_t CASCADE;
ALTER TYPE MTG_karta_t ADD attribute barva_id REF barva_t CASCADE;
CREATE TABLE barva_obj OF barva_t (
      id NOT NULL UNIQUE,
      nazev NOT NULL
);
CREATE TABLE system_obj OF system_t (
      popis NOT NULL,
      nazev NOT NULL,
      id NOT NULL UNIQUE
);
CREATE TABLE kolo_obj OF kolo_t (
      cislo NOT NULL UNIQUE
);
CREATE TABLE rozhodci_obj OF rozhodci_t (
      hlavni NOT NULL,
      jmeno NOT NULL,
      level NOT NULL,
      prijmeni NOT NULL,
      id NOT NULL UNIQUE
);
CREATE TABLE typ_obj OF typ_t (
      nazev NOT NULL,
      id NOT NULL UNIQUE
);
CREATE TABLE vysledek_obj OF vysledek_t (
      hrac_2_vyhral_her NOT NULL,
      hrac_1_vyhral_her NOT NULL,
      Unique(hrac_2_vyhral_her, hrac_1_vyhral_her)
);
CREATE TABLE druh_obj OF druh_t (
      id NOT NULL UNIQUE,
      nazev NOT NULL
);
CREATE TABLE format_obj OF format_t (
      id NOT NULL UNIQUE
);
CREATE TABLE osoba_obj OF osoba_t (
      id NOT NULL UNIQUE
);
CREATE TABLE byl_rozhodcim_obj OF byl_rozhodcim_t (
      mel_ro_id NOT NULL,
      Rozhod_id NOT NULL
);
CREATE TABLE Rel8_obj OF Rel8_t (
      kola_cislo NOT NULL,
      zapas_hrac_2 NOT NULL,
      zapas_hrac_1 NOT NULL
);
CREATE TABLE zapas_obj OF zapas_t (
      zapasu_hrac_2_vyhral_her NOT NULL,
      zapasu_hrac_1_vyhral_her NOT NULL,
      hrac_2 NOT NULL,
      hrac_1 NOT NULL,
      Unique(hrac_2, hrac_1)
);
CREATE TABLE turnaj_obj OF turnaj_t (
      byl_fo_id NOT NULL,
      kolo_cislo NOT NULL,
      byl_hr_id NOT NULL,
      misto_konani NOT NULL UNIQUE,
      datum NOT NULL,
      nazev NOT NULL,
      id NOT NULL UNIQUE,
      pocet_ucastniku NOT NULL
);
CREATE TABLE byl_hran_obj OF byl_hran_t (
      balice_id NOT NULL,
      hral_n_id NOT NULL
);
CREATE TABLE Deck_obj OF Deck_t (
      druh_id NOT NULL,
      id NOT NULL UNIQUE
);
CREATE TABLE MTG_karta_obj OF MTG_karta_t (
      karta_id NOT NULL,
      barva_id NOT NULL,
      casting_cost NOT NULL,
      sb_pocet_kopii NOT NULL,
      jmeno NOT NULL,
      id NOT NULL UNIQUE,
      md_pocet_kopii NOT NULL
);
CREATE TABLE obsahuje_obj OF obsahuje_t (
      MTG_ka_id NOT NULL,
      obsahu_id NOT NULL
);
CREATE TABLE hral_obj OF hral_t (
      byl_hr_id NOT NULL,
      hrala_id NOT NULL
);
ALTER TABLE byl_rozhodcim_obj ADD FOREIGN KEY (mel_ro_id) REFERENCES rozhodci_obj;
ALTER TABLE byl_rozhodcim_obj ADD FOREIGN KEY (Rozhod_id) REFERENCES turnaj_obj;
ALTER TABLE Rel8_obj ADD FOREIGN KEY (kola_cislo) REFERENCES kolo_obj;
ALTER TABLE Rel8_obj ADD FOREIGN KEY (zapas_hrac_2) REFERENCES zapas_obj;
ALTER TABLE Rel8_obj ADD FOREIGN KEY (zapas_hrac_1) REFERENCES zapas_obj;
ALTER TABLE zapas_obj ADD FOREIGN KEY (zapasu_hrac_2_vyhral_her) REFERENCES vysledek_obj;
ALTER TABLE zapas_obj ADD FOREIGN KEY (zapasu_hrac_1_vyhral_her) REFERENCES vysledek_obj;
ALTER TABLE turnaj_obj ADD FOREIGN KEY (byl_fo_id) REFERENCES format_obj;
ALTER TABLE turnaj_obj ADD FOREIGN KEY (kolo_cislo) REFERENCES kolo_obj;
ALTER TABLE turnaj_obj ADD FOREIGN KEY (byl_hr_id) REFERENCES system_obj;
ALTER TABLE byl_hran_obj ADD FOREIGN KEY (balice_id) REFERENCES Deck_obj;
ALTER TABLE byl_hran_obj ADD FOREIGN KEY (hral_n_id) REFERENCES turnaj_obj;
ALTER TABLE Deck_obj ADD FOREIGN KEY (druh_id) REFERENCES druh_obj;
ALTER TABLE MTG_karta_obj ADD FOREIGN KEY (karta_id) REFERENCES typ_obj;
ALTER TABLE MTG_karta_obj ADD FOREIGN KEY (barva_id) REFERENCES barva_obj;
ALTER TABLE obsahuje_obj ADD FOREIGN KEY (MTG_ka_id) REFERENCES MTG_karta_obj;
ALTER TABLE obsahuje_obj ADD FOREIGN KEY (obsahu_id) REFERENCES Deck_obj;
ALTER TABLE hral_obj ADD FOREIGN KEY (byl_hr_id) REFERENCES Deck_obj;
ALTER TABLE hral_obj ADD FOREIGN KEY (hrala_id) REFERENCES osoba_obj;
