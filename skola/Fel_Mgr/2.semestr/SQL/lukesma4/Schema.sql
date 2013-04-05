
Drop table main_ cascade constraints;
Drop table Osoba cascade constraints;
Drop table turnaj cascade constraints;
Drop table side cascade constraints;
Drop table typ cascade constraints;
Drop table barva cascade constraints;
Drop table je_v_side cascade constraints;
Drop table druh cascade constraints;
Drop table hrac cascade constraints;
Drop table karta cascade constraints;
Drop table Rozhodci cascade constraints;
Drop table Deck cascade constraints;
Drop table hral cascade constraints;
Drop table je_v_main cascade constraints;
Drop table DRUH_PODDRUH cascade constraints;

Create table main_ (
      pocet_karet Integer Not Null,
      id_main Integer Not Null,
      Constraint PK_main2 Primary Key (id_main)
);
Create table Osoba (
      Jmeno VarChar2(255) Not Null,
      id_osoba Integer Not Null,
      Prijmeni VarChar2(255) Not Null,
      Constraint PK_Osoba Primary Key (id_osoba)
);
Create table turnaj (
      datum Date Null,
      misto_konani VarChar2(255) Null,
      nazev VarChar2(255) Not Null,
      pocet_ucastniku Integer Not Null,
      id_turnaj Integer Not Null,
      format VarChar2(255) Not Null,
      turnaj_id_rozhodci Integer Not Null,
      Constraint PK_turnaj Primary Key (id_turnaj)
);
Create table side (
      pocet_karet Integer Not Null,
      id_side Integer Not Null,
      Constraint PK_side Primary Key (id_side)
);
Create table typ (
      id_typ Integer Not Null,
      nazev Char(20) Not Null,
      Constraint UNQ_typ_1 Unique (nazev),
      Constraint PK_typ Primary Key (id_typ)
);
Create table barva (
      id_barva Integer Not Null,
      nazev VarChar2(20) Not Null,
      zkratka Char(1) Not Null,
      Constraint UNQ_barva_1 Unique (nazev, zkratka),
      Constraint PK_barva Primary Key (id_barva)
);
Create table je_v_side (
      je_v_side_id_karta Integer Not Null,
      je_v_side_id_side Integer Not Null,
      Constraint UNQ_je_v_side_1 Unique (je_v_side_id_karta, je_v_side_id_side)
);
Create table druh (
      nazev VarChar2(255) Not Null,
      id_druh Integer Not Null,
      Constraint PK_druh Primary Key (id_druh)
);
Create table hrac (
      hrac_id_osoba Integer Not Null,
      id_hrac Integer Not Null,
      Constraint PK_hrac Primary Key (id_hrac)
);
Create table karta (
      karta_id_barva Integer Not Null,
      karta_id_typ Integer Not Null,
      jmeno Integer Not Null,
      casting_cost Integer Not Null,
      id_karta Integer Not Null,
      Constraint UNQ_karta_1 Unique (jmeno),
      Constraint PK_karta Primary Key (id_karta)
);

Create table Rozhodci (
      rozhodci_id_osoba Integer Not Null,
      level_ Integer Not Null,
      id_rozhodci Integer Not Null,
      Constraint PK_Rozhodci Primary Key (id_rozhodci)
);
Create table Deck (
      deck_id_side Integer Not Null,
      deck_id_main Integer Not Null,
      nazev Integer Not Null,
      id_deck Integer Not Null,
      je_druhu_id_druh Integer Not Null,
      Constraint PK_Deck Primary Key (id_deck)
);
Create table hral (
      hral_id_hrac Integer Not Null,
      hral_id_deck Integer Not Null,
      hral_id_turnaj Integer Not Null,
      Constraint UNQ_hral_1 Unique (hral_id_hrac, hral_id_deck, hral_id_turnaj)
);
Create table je_v_main (
      je_v_main_id_karta Integer Not Null,
      je_v_main_id_main Integer Not Null,
      Constraint UNQ_je_v_main_1 Unique (je_v_main_id_karta, je_v_main_id_main)
);

Create table DRUH_PODDRUH (
      druh_poddruh_id_naddruh Integer Not Null,
      druh_poddruh_id_poddruh Integer Not Null,
      Constraint UNQ_TYP_PODTYP_1 Unique (druh_poddruh_id_naddruh, druh_poddruh_id_poddruh)
);

Alter table DRUH_PODDRUH add (
      Constraint FK_je_druhu_1 Foreign Key (druh_poddruh_id_naddruh) References DRUH(id_druh),
      Constraint FK_je_druhu_2 Foreign Key (druh_poddruh_id_poddruh) References DRUH(id_druh)
);


Alter table je_v_side add (
      Constraint FK_je_v_side_1 Foreign Key (je_v_side_id_karta) References karta(id_karta),
      Constraint FK_je_v_side_2 Foreign Key (je_v_side_id_side) References side(id_side)
);
Alter table hrac add (
      Constraint FK_hrac_1 Foreign Key (hrac_id_osoba) References Osoba(id_osoba)
);
Alter table karta add (
      Constraint FK_karta_1 Foreign Key (karta_id_barva) References barva(id_barva),
      Constraint FK_karta_2 Foreign Key (karta_id_typ) References typ(id_typ)
);
Alter table Rozhodci add (
      Constraint FK_Rozhodci_1 Foreign Key (rozhodci_id_osoba) References Osoba(id_osoba)
);
Alter table Deck add (
      Constraint FK_Deck_1 Foreign Key (deck_id_side) References side(id_side),
      Constraint FK_Deck_2 Foreign Key (deck_id_main) References main_(id_main),
      Constraint FK_Deck_3 Foreign Key (je_druhu_id_druh) References Druh (id_druh)
);
Alter table hral add (
      Constraint FK_hral_1 Foreign Key (hral_id_hrac) References hrac(id_hrac),
      Constraint FK_hral_2 Foreign Key (hral_id_deck) References Deck(id_deck),
      Constraint FK_hral_3 Foreign Key (hral_id_turnaj) References turnaj(id_turnaj)
);
Alter table je_v_main add (
      Constraint FK_je_v_main_1 Foreign Key (je_v_main_id_karta) References karta(id_karta),
      Constraint FK_je_v_main_2 Foreign Key (je_v_main_id_main) References main_(id_main)
);

Alter table turnaj add (
      Constraint FK_turnaj Foreign Key (turnaj_id_rozhodci) References rozhodci(id_rozhodci)
);

