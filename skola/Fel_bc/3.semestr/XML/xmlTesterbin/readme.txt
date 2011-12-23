Testovatko na zapoctaky do PRG036 Technologie XML.


Co to potrebuje
---------------
Vyzaduje Java 1.5 a vyssi. Linux nebo Windows; na Macu netestovano.



Format dat ke kontrole
----------------------
zip archiv nebo adresar s projektem. Jmeno zip archivu ma tvar prg036_prijmeni_jmeno.zip . Obsah adresare/zip archivu musi byt nasledujici:

zadani.txt	- textovy popis zadani, ktere se bude v prubehu roku resit
data.xml	- XML soubor s daty
data.dtd	- DTD k data.xml
data.xsd	- XML Schema k data.xml
data2\w+.xsl	- XSL Transformace souboru data.xml do formatu urceneho casti \w+ (coz jsou alfanumericke znaky), ktery bude pouzit jako pripona vystupniho souboru. 
xpath/xpath1.xd	- 1. XPath vyraz, ktery bude vyhodnocen nad XML dokumenttem ../data.xml. Pouzivejte jen kodovani UTF-8 (ne UTF-8Y). Pokud v nem maji byt nejake komentare, musi vyhovovat jazyku XPath: (: komentar :).
xpath/xpath2.xd
...
xpath/xpath9.xd
xquery/query1.xd	- 1. XQuery dotaz, odkazuje se na soubor ../data.xml. Pouzivejte jen kodovani UTF-8 (ne UTF-8Y). Pokud v nem maji byt nejake komentare, musi vyhovovat jazyku XQuery: (: komentar :).
xquery/query2.xd
...
xquery/query9.xd
sax/TestSax*.java	- zdrojkovy kod ukazujici pouziti SAXu. Public trida se jmenuje TestSax.java a rozsiruje abstraktni tridu TestJava (pouze kvuli standardni metode run(), kterou volame. Odkazuje se na soubor ../data.xml. Muze obsahovat dalsi tridy, ktere ale nemohou byt public.
dom/TestDom*.java	- zdrojovy kod ukazujici pouziti DOMu. Public trida se jmenuje TestSax.java a rozsiruje abstraktni tridu TestJava (pouze kvuli standardni metode run(), kterou volame. Odkazuje se na soubor ../data.xml. Muze obsahovat dalsi tridy, ktere ale nemohou byt public.

Pozor na velikost pismen ve jmenech adresaru a souboru!


Jak to pouzivat
--------------
Vytvorit si adresar, ve kterem budou vsechny zapoctaky (kvuli bezpecnosti spouteni kodu pro SAX a DOM). Rozbalit do nej archiv z http://www.ms.mff.cuni.cz/~ulryj2am/big/prg036/xmlTester.zip .

Obsahuje mj. skripty na spusteni - run.bat a run.sh, kterym se jako parametr dava jmeno zip archivu (program ho automaticky rozbali do noveho adresare se jmenem jako ma zip archiv) nebo adresare s projektem.

Na konzoli vypise strucne informace o provedenych testech. Podrobnosti a chyby lze nalezt v souboru output.log. Ten je pri kazdem spusteni prepsan.



Co to umi
---------
 - kontroluje, zda je data.xml dobre formovany xml dokument
 - kontroluje maximalni fan-out elementu a maximalni hloubku xml dokumentu
 - kontroluje, zda data.xml vyhovuje DTD schematu data.dtd
 - kontroluje, zda DTD schema pouziva vsechny pozadovane konstrukce (pomoci regularnich vyrazu)
 - kontroluje, zda data.xml vyhovuje XML schematu data.xsd
 - kontroluje, zda XML schema pouziva vsechny pozadovane konstrukce (pomoci regularnich vyrazu)
 - spusti XSL transformaci data2(\w+).xsl a vystup ulozi do souboru data2$1.$1 (syntaxe z regularnich vyrazu)
 - kontroluje, zda XSL transformace pouziva vsechny pozadovane konstrukce (pomoci regularnich vyrazu)
 - spusti vsechny dotazy v adresari xquery a jejich vystupy ulozi do souboru queryX.xq.out.xml, kde X odpovida cislu dotazu
 - prelozi soubor sax/TestSax.java a spusti ho
 - prelozi soubor dom/TestDom.java a spusti ho

V pripade, ze se pri libovolne z akci neco nepovede, ze test neprobehl v poradku a vypise informace o chybe.



Known Issues
------------
Prestoze je vystup v DOMu odkazovan jako ../data.out.xml, zapise se do adresaare, kde je xmlTester.jar, misto do prg036_prijmeni_jmeno/dom/ .

Zabezceni (omezeni prava cteni a zapisu pouze do aktualniho adresare) zatim nefunguje.


To Do
-----
Hodilo by se kontrolu pouzitych kostrukci v XML Schema a XSLT prepsat pomoci nekolika XPath vyrazu. Kontrolovany dokument bude _vyhovovat_ XPath vyrazum, pokud bude jim vybrana mnozina uzlu dokumentu neprazdna. Hledam dobrovolniky na napsani takovych XPath vyrazu :-) 