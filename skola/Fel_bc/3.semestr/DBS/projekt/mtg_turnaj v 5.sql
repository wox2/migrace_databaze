Set Echo on
Purge Recyclebin;
/* používate-li verzi nižší než Oracle 10g,
 zakomentujte p?íkaz PURGE */
Drop table osoba cascade constraints;
Drop table system cascade constraints;
Drop table format cascade constraints;
Drop table vysledek cascade constraints;
Drop table typ cascade constraints;
Drop table kolo cascade constraints;
Drop table barva cascade constraints;
Drop table rozhodci cascade constraints;
Drop table druh cascade constraints;
Drop table byl_hran cascade constraints;
Drop table Deck cascade constraints;
Drop table turnaj cascade constraints;
Drop table obsahuje cascade constraints;
Drop table hral cascade constraints;
Drop table byl_rozhodcim cascade constraints;
Drop table zapas cascade constraints;
Drop table MTG_karta cascade constraints;
Drop table hraji_se_zapasy cascade constraints;
Create table osoba (
      id Integer Not Null,
      jmeno VarChar2(20) Null,
      prijmeni VarChar2(50) Null,
      Constraint PK_osoba Primary Key (id)
);
Create table system (
      popis VarChar2(255) Not Null,
      nazev VarChar2(30) Not Null,
      id Integer Not Null,
      Constraint PK_system Primary Key (id)
);
Create table format (
      id Integer Not Null,
      nazev VarChar2(255) Null,
      Constraint PK_format Primary Key (id)
);
Create table vysledek (
      hrac_2_vyhral_her Integer Not Null,
      hrac_1_vyhral_her Integer Not Null,
      Constraint PK_vysledek Primary Key (hrac_2_vyhral_her, hrac_1_vyhral_her)
);
Create table typ (
      nazev VarChar2(20) Not Null,
      id Integer Not Null,
      Constraint PK_typ Primary Key (id)
);
Create table kolo (
      cislo Integer Not Null,
      Constraint PK_kolo Primary Key (cislo)
);
Create table barva (
      id Integer Not Null,
      nazev VarChar2(20) Not Null,
      Constraint PK_barva Primary Key (id)
);
Create table rozhodci (
      level Integer Not Null,
      id Integer Not Null,
      prijmeni VarChar2(50) Not Null,
      jmeno VarChar2(50) Not Null,
      hlavni Integer Not Null,
      Constraint PK_rozhodci Primary Key (id)
);
Create table druh (
      id Integer Not Null,
      nazev VarChar2(20) Not Null,
      Constraint PK_druh Primary Key (id)
);
Create table byl_hran (
      balice_id Integer Not Null,
      hral_n_id Integer Not Null,
      Constraint UNQ_byl_hran_1 Unique (balice_id, hral_n_id)
);
Create table Deck (
      druh_id Integer Not Null,
      id Integer Not Null,
      jmeno VarChar2(30) Null,
      barvy VarChar2(30) Null,
      Constraint PK_Deck Primary Key (id)
);
Create table turnaj (
      kolo_cislo Integer Not Null,
      byl_fo_id Integer Not Null,
      byl_hr_id Integer Not Null,
      dolarova_dotace Integer Null,
      jina_dotace VarChar2(255) Null,
      vstupne Integer Null,
      misto_konani VarChar2(255) Not Null,
      datum Date Not Null,
      nazev VarChar2(20) Not Null,
      id Integer Not Null,
      top8 Integer Null,
      pocet_ucastniku Integer Not Null,
      Constraint UNQ_turnaj_1 Unique (misto_konani),
      Constraint PK_turnaj Primary Key (id)
);
Create table obsahuje (
      obsahu_id Integer Not Null,
      MTG_ka_id Integer Not Null,
      Constraint UNQ_obsahuje_1 Unique (obsahu_id, MTG_ka_id)
);
Create table hral (
      hrala_id Integer Not Null,
      byl_hr_id Integer Not Null,
      Constraint UNQ_hral_1 Unique (hrala_id, byl_hr_id)
);
Create table byl_rozhodcim (
      Rozhod_id Integer Not Null,
      mel_ro_id Integer Not Null,
      Constraint UNQ_byl_rozhodcim_1 Unique (Rozhod_id, mel_ro_id)
);
Create table zapas (
      zapasu_hrac_2_vyhral_her Integer Not Null,
      zapasu_hrac_1_vyhral_her Integer Not Null,
      hrac_2 Integer Not Null,
      hrac_1 Integer Not Null,
      poznamky VarChar2(255) Null,
      bye Char(1) Null,
      Constraint PK_zapas Primary Key (hrac_2, hrac_1)
);
Create table MTG_karta (
      barva_id Integer Not Null,
      karta_id Integer Not Null,
      casting_cost VarChar2(30) Not Null,
      sb_pocet_kopii Integer Not Null,
      jmeno VarChar2(30) Not Null,
      id Integer Not Null,
      md_pocet_kopii Integer Not Null,
      Constraint PK_MTG_karta Primary Key (id)
);
Create table hraji_se_zapasy (
      zapas_hrac_2 Integer Not Null,
      zapas_hrac_1 Integer Not Null,
      kola_cislo Integer Not Null,
      Constraint UNQ_hraji_se_zapasy_1 Unique (zapas_hrac_2, zapas_hrac_1, kola_cislo)
);
Alter table byl_hran add (
      Constraint FK_byl_hran_1 Foreign Key (balice_id) References Deck(id),
      Constraint FK_byl_hran_2 Foreign Key (hral_n_id) References turnaj(id)
);
Alter table Deck add (
      Constraint FK_Deck_1 Foreign Key (druh_id) References druh(id)
);
Alter table turnaj add (
      Constraint FK_turnaj_1 Foreign Key (kolo_cislo) References kolo(cislo),
      Constraint FK_turnaj_2 Foreign Key (byl_fo_id) References format(id),
      Constraint FK_turnaj_3 Foreign Key (byl_hr_id) References system(id)
);
Alter table obsahuje add (
      Constraint FK_obsahuje_1 Foreign Key (obsahu_id) References Deck(id),
      Constraint FK_obsahuje_2 Foreign Key (MTG_ka_id) References MTG_karta(id)
);
Alter table hral add (
      Constraint FK_hral_1 Foreign Key (hrala_id) References osoba(id),
      Constraint FK_hral_2 Foreign Key (byl_hr_id) References Deck(id)
);
Alter table byl_rozhodcim add (
      Constraint FK_byl_rozhodcim_1 Foreign Key (Rozhod_id) References turnaj(id),
      Constraint FK_byl_rozhodcim_2 Foreign Key (mel_ro_id) References rozhodci(id)
);
Alter table zapas add (
      Constraint FK_zapas_1 Foreign Key (zapasu_hrac_2_vyhral_her, zapasu_hrac_1_vyhral_her) References vysledek(hrac_2_vyhral_her, hrac_1_vyhral_her)
);
Alter table MTG_karta add (
      Constraint FK_MTG_karta_1 Foreign Key (barva_id) References barva(id),
      Constraint FK_MTG_karta_2 Foreign Key (karta_id) References typ(id)
);
Alter table hraji_se_zapasy add (
      Constraint FK_hraji_se_zapasy_1 Foreign Key (zapas_hrac_2, zapas_hrac_1) References zapas(hrac_2, hrac_1),
      Constraint FK_hraji_se_zapasy_2 Foreign Key (kola_cislo) References kolo(cislo)
);
