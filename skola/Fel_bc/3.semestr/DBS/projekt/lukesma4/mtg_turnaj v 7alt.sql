Set Echo on
Purge Recyclebin;
/* používate-li verzi nižší než Oracle 10g,
 zakomentujte p?íkaz PURGE */
Drop table system cascade constraints;
Drop table barva cascade constraints;
Drop table format cascade constraints;
Drop table vysledek cascade constraints;
Drop table druh cascade constraints;
Drop table typ cascade constraints;
Drop table Osoba cascade constraints;
Drop table rozhodci cascade constraints;
Drop table hrac cascade constraints;
Drop table Deck cascade constraints;
Drop table kolo cascade constraints;
Drop table hral_deck_na_turnaji cascade constraints;
Drop table turnaj cascade constraints;
Drop table zapas cascade constraints;
Drop table sb_obsahuje cascade constraints;
Drop table md_obsahuje cascade constraints;
Drop table MTG_karta cascade constraints;
Drop table byl_rozhodcim cascade constraints;
Create table system (
      id Integer Not Null,
      nazev VarChar2(30) Not Null,
      popis VarChar2(255) Not Null,
      Constraint UNQ_system_1 Unique (nazev),
      Constraint PK_system Primary Key (id)
);
Create table barva (
      nazev VarChar2(20) Not Null,
      id Integer Not Null,
      Constraint UNQ_barva_1 Unique (nazev),
      Constraint PK_barva Primary Key (id)
);
Create table format (
      id Integer Not Null,
      nazev VarChar2(255) Not Null,
      Constraint UNQ_format_1 Unique (nazev),
      Constraint PK_format Primary Key (id)
);
Create table vysledek (
      hrac_1_vyhral_her Integer Not Null,
      hrac_2_vyhral_her Integer Not Null,
      Constraint PK_vysledek Primary Key (hrac_1_vyhral_her, hrac_2_vyhral_her)
);
Create table druh (
      id Integer Not Null,
      nazev VarChar2(20) Not Null,
      Constraint UNQ_druh_1 Unique (nazev),
      Constraint PK_druh Primary Key (id)
);
Create table typ (
      id Integer Not Null,
      nazev VarChar2(20) Not Null,
      Constraint UNQ_typ_1 Unique (nazev),
      Constraint PK_typ Primary Key (id)
);
Create table Osoba (
      Id Integer Not Null,
      Prijmeni VarChar2(50) Not Null,
      Jmeno VarChar2(50) Not Null,
      Constraint PK_Osoba Primary Key (Id)
);
Create table rozhodci (
      uroven Integer Not Null,
      Osoba_Id Integer Not Null,
      Constraint PK_rozhodci Primary Key (Osoba_Id)
);
Create table hrac (
      Osoba_Id Integer Not Null,
      Constraint PK_hrac Primary Key (Osoba_Id)
);
Create table Deck (
      druh_deck_id Integer Not Null,
      barvy VarChar2(30) Not Null,
      id Integer Not Null,
      jmeno VarChar2(30) Not Null,
      Constraint PK_Deck Primary Key (id)
);
Create table kolo (
      turnaj_kol_id Integer Not Null,
      id Integer Not Null,
      cislo Integer Not Null,
      Constraint PK_kolo Primary Key (id)
);
Create table hral_deck_na_turnaji (
      Deck_hdnt_id Integer Not Null,
      hrac_Osoba_Id Integer Not Null,
      turnaj_id Integer Not Null,
      Constraint PK_hral_deck_na_turnaji Primary Key (hrac_Osoba_Id, turnaj_id)
);
Create table turnaj (
      syst_turna_id Integer Not Null,
      hlroz_turn_Osoba_Id Integer Not Null,
      form_turna_id Integer Not Null,
      id Integer Not Null,
      top8 Integer Null,
      vstupne Integer Not Null,
      dolarova_dotace Integer Null,
      jina_dotace VarChar2(255) Null,
      pocet_ucastniku Integer Not Null,
      nazev VarChar2(20) Not Null,
      datum Date Not Null,
      misto_konani VarChar2(255) Null,
      Constraint PK_turnaj Primary Key (id)
);
Create table zapas (
      kolo_zapas_id Integer Not Null,
      h1_zapas_Osoba_Id Integer Not Null,
      vysl_zapas_hrac_1_vyhral_her Integer Not Null,
      vysl_zapas_hrac_2_vyhral_her Integer Not Null,
      h2_zapas_Osoba_Id Integer Not Null,
      bye Integer Null,
      poznamky VarChar2(255) Null,
      id Integer Not Null,
      Constraint PK_zapas Primary Key (id)
);
Create table sb_obsahuje (
      Deck_sb_id Integer Not Null,
      karta_sb_id Integer Not Null,
      id Integer Not Null,
      pocet Integer Not Null,
      Constraint PK_sb_obsahuje Primary Key (id)
);
Create table md_obsahuje (
      Deck_md_id Integer Not Null,
      karta_md_id Integer Not Null,
      id Integer Not Null,
      pocet Integer Not Null,
      Constraint PK_md_obsahuje Primary Key (id)
);
Create table MTG_karta (
      typ_karta_id Integer Not Null,
      barva_kart_id Integer Not Null,
      id Integer Not Null,
      casting_cost VarChar2(30) Not Null,
      jmeno VarChar2(30) Not Null,
      Constraint UNQ_MTG_karta_1 Unique (jmeno),
      Constraint PK_MTG_karta Primary Key (id)
);
Create table byl_rozhodcim (
      turnaj_roz_id Integer Not Null,
      rozh_turna_Osoba_Id Integer Not Null,
      Constraint UNQ_byl_rozhodcim_1 Unique (turnaj_roz_id, rozh_turna_Osoba_Id)
);
Alter table rozhodci add (
      Constraint FK_rozhodci_1 Foreign Key (Osoba_Id) References Osoba(Id)
);
Alter table hrac add (
      Constraint FK_hrac_1 Foreign Key (Osoba_Id) References Osoba(Id)
);
Alter table Deck add (
      Constraint FK_Deck_1 Foreign Key (druh_deck_id) References druh(id)
);
Alter table kolo add (
      Constraint FK_kolo_1 Foreign Key (turnaj_kol_id) References turnaj(id)
);
Alter table hral_deck_na_turnaji add (
      Constraint FK_hral_deck_na_turnaji_1 Foreign Key (hrac_Osoba_Id) References hrac(Osoba_Id),
      Constraint FK_hral_deck_na_turnaji_2 Foreign Key (turnaj_id) References turnaj(id),
      Constraint FK_hral_deck_na_turnaji_3 Foreign Key (Deck_hdnt_id) References Deck(id)
);
Alter table turnaj add (
      Constraint FK_turnaj_1 Foreign Key (syst_turna_id) References system(id),
      Constraint FK_turnaj_2 Foreign Key (hlroz_turn_Osoba_Id) References rozhodci(Osoba_Id),
      Constraint FK_turnaj_3 Foreign Key (form_turna_id) References format(id)
);
Alter table zapas add (
      Constraint FK_zapas_1 Foreign Key (kolo_zapas_id) References kolo(id),
      Constraint FK_zapas_2 Foreign Key (h1_zapas_Osoba_Id) References hrac(Osoba_Id),
      Constraint FK_zapas_3 Foreign Key (vysl_zapas_hrac_1_vyhral_her, vysl_zapas_hrac_2_vyhral_her) References vysledek(hrac_1_vyhral_her, hrac_2_vyhral_her),
      Constraint FK_zapas_4 Foreign Key (h2_zapas_Osoba_Id) References hrac(Osoba_Id)
);
Alter table sb_obsahuje add (
      Constraint FK_sb_obsahuje_1 Foreign Key (Deck_sb_id) References Deck(id),
      Constraint FK_sb_obsahuje_2 Foreign Key (karta_sb_id) References MTG_karta(id)
);
Alter table md_obsahuje add (
      Constraint FK_md_obsahuje_1 Foreign Key (Deck_md_id) References Deck(id),
      Constraint FK_md_obsahuje_2 Foreign Key (karta_md_id) References MTG_karta(id)
);
Alter table MTG_karta add (
      Constraint FK_MTG_karta_1 Foreign Key (typ_karta_id) References typ(id),
      Constraint FK_MTG_karta_2 Foreign Key (barva_kart_id) References barva(id)
);
Alter table byl_rozhodcim add (
      Constraint FK_byl_rozhodcim_1 Foreign Key (turnaj_roz_id) References turnaj(id),
      Constraint FK_byl_rozhodcim_2 Foreign Key (rozh_turna_Osoba_Id) References rozhodci(Osoba_Id)
);
