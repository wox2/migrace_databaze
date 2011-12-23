Set Echo on
Purge Recyclebin;
/* používate-li verzi nižší než Oracle 10g,
 zakomentujte p?íkaz PURGE */
Drop table vysledek cascade constraints;
Drop table druh cascade constraints;
Drop table typ cascade constraints;
Drop table format cascade constraints;
Drop table hrac cascade constraints;
Drop table rozhodci cascade constraints;
Drop table barva cascade constraints;
Drop table system cascade constraints;
Drop table hrac_hral cascade constraints;
Drop table Deck cascade constraints;
Drop table hral_deck_na_turnaji cascade constraints;
Drop table kolo cascade constraints;
Drop table hraji_se_zapasy cascade constraints;
Drop table zapas cascade constraints;
Drop table byl_rozhodcim cascade constraints;
Drop table turnaj cascade constraints;
Drop table deck_byl_hran cascade constraints;
Drop table MTG_karta cascade constraints;
Drop table md_obsahuje cascade constraints;
Drop table sb_obsahuje cascade constraints;
Create table vysledek (
      hrac_2_vyhral_her Integer Not Null,
      hrac_1_vyhral_her Integer Not Null,
      Constraint PK_vysledek Primary Key (hrac_2_vyhral_her, hrac_1_vyhral_her)
);
Create table druh (
      id Integer Not Null,
      nazev VarChar2(20) Not Null,
      Constraint PK_druh Primary Key (id)
);
Create table typ (
      nazev VarChar2(20) Not Null,
      id Integer Not Null,
      Constraint PK_typ Primary Key (id)
);
Create table format (
      id Integer Not Null,
      nazev VarChar2(255) Null,
      Constraint PK_format Primary Key (id)
);
Create table hrac (
      id Integer Not Null,
      jmeno VarChar2(20) Null,
      prijmeni VarChar2(50) Null,
      Constraint PK_hrac Primary Key (id)
);
Create table rozhodci (
      id Integer Not Null,
      prijmeni VarChar2(50) Not Null,
      jmeno VarChar2(50) Not Null,
      uroven Integer Not Null,
      Constraint PK_rozhodci Primary Key (id)
);
Create table barva (
      id Integer Not Null,
      nazev VarChar2(20) Not Null,
      Constraint PK_barva Primary Key (id)
);
Create table system (
      popis VarChar2(255) Not Null,
      nazev VarChar2(30) Not Null,
      id Integer Not Null,
      Constraint PK_system Primary Key (id)
);
Create table hrac_hral (
      hdnt_hrac_id Integer Not Null,
      hrac_hdnt_id Integer Not Null,
      Constraint UNQ_hrac_hral_1 Unique (hdnt_hrac_id, hrac_hdnt_id)
);
Create table Deck (
      druh_deck_id Integer Not Null,
      jmeno VarChar2(30) Null,
      id Integer Not Null,
      barvy VarChar2(30) Null,
      Constraint PK_Deck Primary Key (id)
);
Create table hral_deck_na_turnaji (
      turnaj_hdn_id Integer Not Null,
      id Integer Not Null,
      Constraint PK_hral_deck_na_turnaji Primary Key (id)
);
Create table kolo (
      turnaj_kol_id Integer Not Null,
      id Integer Not Null,
      cislo Integer Not Null,
      Constraint PK_kolo Primary Key (id)
);
Create table hraji_se_zapasy (
      zapas_kolo_id Integer Not Null,
      kolo_zapas_id Integer Not Null,
      Constraint UNQ_hraji_se_zapasy_1 Unique (zapas_kolo_id, kolo_zapas_id)
);
Create table zapas (
      h2_zapas_id Integer Not Null,
      h1_zapas_id Integer Not Null,
      vysl_zapas_hrac_2_vyhral_her Integer Not Null,
      vysl_zapas_hrac_1_vyhral_her Integer Not Null,
      poznamky VarChar2(255) Null,
      bye Char(1) Null,
      id Integer Not Null,
      Constraint PK_zapas Primary Key (id)
);
Create table byl_rozhodcim (
      rozh_turna_id Integer Not Null,
      turnaj_roz_id Integer Not Null,
      Constraint UNQ_byl_rozhodcim_1 Unique (rozh_turna_id, turnaj_roz_id)
);
Create table turnaj (
      syst_turna_id Integer Not Null,
      hlroz_turn_id Integer Not Null,
      form_turna_id Integer Not Null,
      dolarova_dotace Integer Null,
      jina_dotace VarChar2(255) Null,
      vstupne Integer Null,
      datum Date Not Null,
      nazev VarChar2(20) Not Null,
      id Integer Not Null,
      pocet_ucastniku Integer Not Null,
      top8 Integer Null,
      misto_konani VarChar2(255) Null,
      Constraint PK_turnaj Primary Key (id)
);
Create table deck_byl_hran (
      hdnt_deck_id Integer Not Null,
      Deck_hdnt_id Integer Not Null,
      Constraint UNQ_deck_byl_hran_1 Unique (hdnt_deck_id, Deck_hdnt_id)
);
Create table MTG_karta (
      barva_kart_id Integer Not Null,
      typ_karta_id Integer Not Null,
      casting_cost VarChar2(30) Not Null,
      id Integer Not Null,
      jmeno VarChar2(30) Not Null,
      Constraint PK_MTG_karta Primary Key (id)
);
Create table md_obsahuje (
      Deck_md_id Integer Not Null,
      karta_md_id Integer Not Null,
      id Integer Not Null,
      pocet Integer Null,
      Constraint PK_md_obsahuje Primary Key (id)
);
Create table sb_obsahuje (
      karta_sb_id Integer Not Null,
      Deck_sb_id Integer Not Null,
      id Integer Not Null,
      pocet Integer Null,
      Constraint PK_sb_obsahuje Primary Key (id)
);
Alter table hrac_hral add (
      Constraint FK_hrac_hral_1 Foreign Key (hdnt_hrac_id) References hral_deck_na_turnaji(id),
      Constraint FK_hrac_hral_2 Foreign Key (hrac_hdnt_id) References hrac(id)
);
Alter table Deck add (
      Constraint FK_Deck_1 Foreign Key (druh_deck_id) References druh(id)
);
Alter table hral_deck_na_turnaji add (
      Constraint FK_hral_deck_na_turnaji_1 Foreign Key (turnaj_hdn_id) References turnaj(id)
);
Alter table kolo add (
      Constraint FK_kolo_1 Foreign Key (turnaj_kol_id) References turnaj(id)
);
Alter table hraji_se_zapasy add (
      Constraint FK_hraji_se_zapasy_1 Foreign Key (zapas_kolo_id) References zapas(id),
      Constraint FK_hraji_se_zapasy_2 Foreign Key (kolo_zapas_id) References kolo(id)
);
Alter table zapas add (
      Constraint FK_zapas_1 Foreign Key (h2_zapas_id) References hrac(id),
      Constraint FK_zapas_2 Foreign Key (h1_zapas_id) References hrac(id),
      Constraint FK_zapas_3 Foreign Key (vysl_zapas_hrac_2_vyhral_her, vysl_zapas_hrac_1_vyhral_her) References vysledek(hrac_2_vyhral_her, hrac_1_vyhral_her)
);
Alter table byl_rozhodcim add (
      Constraint FK_byl_rozhodcim_1 Foreign Key (rozh_turna_id) References rozhodci(id),
      Constraint FK_byl_rozhodcim_2 Foreign Key (turnaj_roz_id) References turnaj(id)
);
Alter table turnaj add (
      Constraint FK_turnaj_1 Foreign Key (syst_turna_id) References system(id),
      Constraint FK_turnaj_2 Foreign Key (hlroz_turn_id) References rozhodci(id),
      Constraint FK_turnaj_3 Foreign Key (form_turna_id) References format(id)
);
Alter table deck_byl_hran add (
      Constraint FK_deck_byl_hran_1 Foreign Key (hdnt_deck_id) References hral_deck_na_turnaji(id),
      Constraint FK_deck_byl_hran_2 Foreign Key (Deck_hdnt_id) References Deck(id)
);
Alter table MTG_karta add (
      Constraint FK_MTG_karta_1 Foreign Key (barva_kart_id) References barva(id),
      Constraint FK_MTG_karta_2 Foreign Key (typ_karta_id) References typ(id)
);
Alter table md_obsahuje add (
      Constraint FK_md_obsahuje_1 Foreign Key (Deck_md_id) References Deck(id),
      Constraint FK_md_obsahuje_2 Foreign Key (karta_md_id) References MTG_karta(id)
);
Alter table sb_obsahuje add (
      Constraint FK_sb_obsahuje_1 Foreign Key (karta_sb_id) References MTG_karta(id),
      Constraint FK_sb_obsahuje_2 Foreign Key (Deck_sb_id) References Deck(id)
);
