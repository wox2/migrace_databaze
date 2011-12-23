1) Razeni boxu elementu

Mame zde 4 elementy div s class="sekce" (jejich id jsou "hlavicka", "navigace", "hlavni_text", napoveda). Definovali jsme jim padding, border a margin. Horni nargin je u kazdeho divu 10px a dolni margin je 50px. Je dulezite vysvetlit boxmodel elementu a vizualni formatovani. 

Mezi elementy div by mela byt vzdy mezera 50px (to je maximum z horniho marginu 10px a dolniho marginu 50px).


2) Float

div s id="napoveda" jsme udelali plovouci. Plovouci elementy jsou vyjmuty z toku stranky a drzi se na prave (hodnota right) nebo na leve (hodnota left) stranì stránky. Text tyto elementy obtéká.


3) Razeni float elementu

Pokud máme za sebou více plovoucich elementu pak se budou radit vedle sebe dokud se vedle sebe vejdou. Pokud se vedle sebe jiz nevejdou tak se ten posledni, ktery se nevejde zobrazi pod ostatnimi. Toto je jediny zpusob jak primet block level elementy aby se chovaly podobne jako inline elementy.

V nasem prikladu mame dva plovouci divy (maji id navigace a hlavni_text) za sebou. Nevejdou se vedle sebe a proto jsou pod sebou. Vsimete si kde je ted zobrazen element s id="napoveda".


4) Razeni float elementu a boxmodel

POZOR TOTO JE UKAZKA TOHO JAK SE TO DELAT NEMA!

Chceme aby se plovouci div elementy zobrazily vedle sebe. Proto jim nastavime sirku 25% a 75%. Pokud vsak zobrazime stranku budou stale pod sebou. Je to zpusobeno tim ze nastavenim sirky ovlivnime pouze velikost obsahu elementu. My vsak mame definovany jeste padding, border a margin - ty se k sirce obsahu pricitaji (pro vysku plati to same). Takze 25% + 75% + 4xborder + 4xpadding + 4xmargin je vice nez 100% sirky okna prohlizece.

5) Razeni float elementu a boxmodel 2

Zrusili jsme padding, border a margin div elementu s class="sekce" takze ted se plovouci divy vedle sebe vejdou.

Navic jsme pro div s id="napoveda" nastavili vlastnost "clear" na "both" (muze byt i left nebo right). Tim jsme rekli, ze tento element nesmi obtekat plovouci elementy na obou stranach (pro hodnotu "left" nalevo a pro hodnotu "right" napravo), tzn. ze se vzdy zobrazi az pod plovoucim elementem.

TOTO STALE NENI SPRAVNY ZPUSOB JAK UDELAT MENU NA LEVE STRANE! Zkuste zmensovat a zvetsovat okno prohlizece a uvidite ze se meni i sirka menu.

6) Menu na levo spravne

Plovouci div s id="navigace" ma ted pevnou sirku 300px.

Zrusili jsme float u divu s id="hlavni_text" a nastavili jsme mu margin 329px.

Nebot v teto situaci se nejak spatne radi divy za sebe musime vsem divum s class="sekce" nastavit margin na 9px a border na 1px solid transparen. Kdyz je tam border nebo padding tak se to zacne chovat spravne.

7) Zmena toku polozek seznamu

V div s id="napoveda" jsme nastavili zarovnavani textu na stred. Vsem polozkozkam seznamu (li) v tomto divu jsme rekli, ze se maji zobrazovat jako inline (standardne se zobrazuji jako list).


8) Relativni, abslutni a fixni polohovani elementu

Na divu s id="kategorie" ukazte relativni, absolutni a fixni polohovani elementu.

Pote co ukazete hodnoty position "relative", "absolute" a "fixed" elementu div s id="kategorie" Vyzkousejte take ruzne hodnoty position pro "div#kategorie ol".
Dulezite jsou hlavne tyto kombinace:

- div#kategorie { position: relative } 
  div#kategorie ol { position: relative }

- div#kategorie { position: absolute } 
  div#kategorie ol { position: relative }

- div#kategorie { position: fixed } 
  div#kategorie ol { position: relative }

- div#kategorie { position: relative } 
  div#kategorie ol { position: absolute }

- div#kategorie { position: absolute } 
  div#kategorie ol { position: absolute }

- div#kategorie { position: fixed } 
  div#kategorie ol { position: absolute }


Z ukazek kombinaci by studentum melo byt jasne kde je v kterem pripade pocatek od ktereho se pocita pozice elementu na strance.

9) Overflow

Vratte div s id="kategorie" do relativniho polohovani (vsimete si ze se na strance zobrazil horizontalni posuvnik) u div s id="hlavni_text" vyzkoujsejte vlastnost "overflow-x" s hodnotami "hidden" a "scroll".

Aby stranka vypadala stejne je treba pri hodnotach "hidden" a "scroll" zrusit margin-left u divu s id="hlavni_text".

10) Media - screen a print

Cely styl ktery jsme vytvorili uzavrete do @media screen { ... }

Vtvorte nove medium @media print { } a do nej zapiste pro divy s id hlavicka, navigace a napoveda pravidlo display: none. Tak zpusobime ze pri tisku se bude tisknout pouze div s id="hlavni_text".

Muzete si to overit ve firefoxu. Soubor -> Nahled tisku 



   