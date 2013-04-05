<map version="0.9.0_Beta_8">
<!-- To view this file, download free mind mapping software FreeMind from http://freemind.sourceforge.net -->
<node CREATED="1210411666181" ID="ID_1445371285" MODIFIED="1210431442558" STYLE="bubble" TEXT="Pam&#x11b;ti a pam&#x11b;&#x165;ov&#xfd;&#xa;       subsyst&#xe9;m" VGAP="13">
<node BACKGROUND_COLOR="#cfdbff" COLOR="#000000" CREATED="1210426807061" HGAP="3" ID="ID_545816194" MODIFIED="1210621408263" POSITION="left" TEXT="Hierarchie" VSHIFT="10">
<font BOLD="true" NAME="DejaVu Sans Condensed" SIZE="14"/>
<node BACKGROUND_COLOR="#bfdefc" COLOR="#000000" CREATED="1210427439065" ID="ID_1924609817" MODIFIED="1210620890861" TEXT="Typy">
<font BOLD="true" NAME="SansSerif" SIZE="12"/>
<node CREATED="1210426811275" ID="ID_381861661" MODIFIED="1210426814269" TEXT="Registry">
<node CREATED="1210427147580" ID="ID_4964305" MODIFIED="1210621178954" STYLE="fork" TEXT="sou&#x10d;&#xe1;st procesoru"/>
<node CREATED="1210427271063" ID="ID_1695732502" MODIFIED="1210621178954" STYLE="fork" TEXT="vysok&#xe1; rychlost p&#x159;&#xed;stupu"/>
</node>
<node COLOR="#990000" CREATED="1210426814508" ID="ID_898633702" MODIFIED="1210621145619" TEXT="Cache">
<font BOLD="true" NAME="SansSerif" SIZE="12"/>
<node CREATED="1210427105010" ID="ID_395882082" MODIFIED="1210621178955" STYLE="fork" TEXT="p&#x159;&#xed;mo v CPU, velikost v jednotk&#xe1;ch MB"/>
<node CREATED="1210427094419" ID="ID_28485856" MODIFIED="1210621178955" STYLE="fork" TEXT="rychl&#xe1;, statick&#xe1; vyrovn&#xe1;vac&#xed; pam&#x11b;&#x165;"/>
<node CREATED="1210508451016" ID="ID_1764387210" MODIFIED="1210621178955" STYLE="fork" TEXT="slou&#x17e;&#xed; ke zkr&#xe1;cen&#xed; vybavovac&#xed; doby hlavn&#xed; pam&#x11b;ti (RAM)"/>
<node CREATED="1210508560899" ID="ID_36328527" MODIFIED="1210621178955" STYLE="fork" TEXT="pro v&#xfd;m&#x11b;nu bloku se pou&#x17e;&#xed;v&#xe1; LRU (Least Recently Used)"/>
<node CREATED="1210508266868" ID="ID_549315386" MODIFIED="1210508284894" TEXT="v&#xed;ce &#xfa;rovn&#xed; (L1, L2, L3)">
<node CREATED="1210508530884" ID="ID_477452729" MODIFIED="1210621178955" STYLE="fork" TEXT="L1 d&#x11b;len&#xe1; na instrukce a data"/>
<node CREATED="1210508541484" ID="ID_807190339" MODIFIED="1210621178955" STYLE="fork" TEXT="L2 spole&#x10d;n&#xe1; (unified)"/>
</node>
<node CREATED="1210508297475" ID="ID_824442322" MODIFIED="1210508303102" TEXT="Z&#xe1;pis">
<node CREATED="1210508303804" ID="ID_113658946" MODIFIED="1210508308878" TEXT="write through">
<node CREATED="1210508867699" ID="ID_424716017" MODIFIED="1210621178956" STYLE="fork" TEXT="data zapisov&#xe1;na najednou do cache i do pam&#x11b;ti"/>
<node CREATED="1210510178016" ID="ID_1439311377" MODIFIED="1210621178955" STYLE="fork" TEXT="vy&#x161;&#x161;&#xed; n&#xe1;roky na &#x161;&#xed;&#x159;ku p&#xe1;sma pam&#x11b;&#x165;ov&#xe9; sb&#x11b;rnice"/>
</node>
<node CREATED="1210508960097" ID="ID_1130808395" MODIFIED="1210508962249" TEXT="write back">
<node CREATED="1210508963064" ID="ID_441752896" MODIFIED="1210621168846" STYLE="fork" TEXT="zm&#x11b;ny se ukl&#xe1;daj&#xed; jen do cache a &#xa;blok p&#x159;itom ozna&#x10d;en jako dirty"/>
<node CREATED="1210510311731" ID="ID_1392952305" MODIFIED="1210621168848" STYLE="fork" TEXT="zm&#x11b;ny se projev&#xed; a&#x17e; p&#x159;i z&#xe1;pisu bloku do pam&#x11b;ti"/>
<node CREATED="1210510321947" ID="ID_197891544" MODIFIED="1210621168848" STYLE="fork" TEXT="slo&#x17e;it&#xe1; implementace, je n&#xe1;ro&#x10d;n&#xe9; urd&#x17e;et konzistenci"/>
</node>
</node>
</node>
<node COLOR="#990000" CREATED="1210426818252" ID="ID_713374063" MODIFIED="1210621143300" TEXT="Hlavn&#xed;">
<font BOLD="true" NAME="SansSerif" SIZE="12"/>
<node CREATED="1210426975976" ID="ID_1209300614" MODIFIED="1210621168846" STYLE="fork" TEXT="fyzick&#xe1; pam&#x11b;&#x165; pro instrukce a data"/>
<node CREATED="1210426992519" ID="ID_1585082606" MODIFIED="1210621168845" STYLE="fork" TEXT="RAM"/>
</node>
<node BACKGROUND_COLOR="#ffffff" COLOR="#990000" CREATED="1210426821027" ID="ID_1536786209" MODIFIED="1210621267869" TEXT="Odkl&#xe1;dac&#xed;">
<font BOLD="true" NAME="SansSerif" SIZE="12"/>
<node CREATED="1210426870574" ID="ID_1800515128" MODIFIED="1210621168845" STYLE="fork" TEXT="ukl&#xe1;d&#xe1;n&#xed; str&#xe1;nek z hlavn&#xed; pam&#x11b;ti"/>
<node CREATED="1210426884086" ID="ID_206005374" MODIFIED="1210621168845" STYLE="fork" TEXT="pevn&#xfd; disk"/>
</node>
<node CREATED="1210426825435" ID="ID_475593199" MODIFIED="1210429319575" TEXT="Sekund&#xe1;rn&#xed;">
<node CREATED="1210426858828" ID="ID_1271039426" MODIFIED="1210621168845" STYLE="fork" TEXT="vn&#x11b;j&#x161;&#xed; pam&#x11b;&#x165; v n&#xe1;hod&#xfd;m p&#x159;&#xed;stupem"/>
<node CREATED="1210426953103" ID="ID_387117450" MODIFIED="1210621168845" STYLE="fork" TEXT="diskety, flash pam&#x11b;ti"/>
</node>
<node CREATED="1210426830462" ID="ID_1851484042" MODIFIED="1210426834654" TEXT="Archivn&#xed;">
<node CREATED="1210426838509" ID="ID_1181767103" MODIFIED="1210621168845" STYLE="fork" TEXT="trval&#xe9; odkl&#xe1;d&#xe1;n&#xed; velk&#xfd;ch objem&#x16f; dat, ke kter&#xfd;m se p&#x159;istupuje z&#x159;&#xed;dka"/>
<node CREATED="1210427649281" ID="ID_290728697" MODIFIED="1210621168844" STYLE="fork" TEXT="magnetick&#xe9; p&#xe1;sky, holografick&#xe1; m&#xe9;dia"/>
</node>
</node>
<node BACKGROUND_COLOR="#bfdefc" COLOR="#000000" CREATED="1210427455864" ID="ID_557336669" MODIFIED="1210620998772" TEXT="Lokalita referenc&#xed;" VGAP="8">
<font BOLD="true" NAME="SansSerif" SIZE="12"/>
<node CREATED="1210427563475" ID="ID_1167532684" MODIFIED="1210427603812" STYLE="fork" TEXT="p&#x159;&#xed;stupy do pam&#x11b;ti maj&#xed; tendenci se shlukovat do skupin - lokalit referenc&#xed;"/>
<node CREATED="1210429141920" ID="ID_172599696" MODIFIED="1210621376326" TEXT="Princip funkce" VSHIFT="4">
<font BOLD="true" NAME="SansSerif" SIZE="12"/>
<node CREATED="1210429147160" ID="ID_676956727" MODIFIED="1210621168850" STYLE="fork" TEXT="pam&#x11b;&#x165; rozd&#x11b;lena na &#xfa;seky, tvo&#x159;en&#xe9;&#xa;posloupnost&#xed; adresovateln&#xfd;ch jednotek"/>
<node CREATED="1210429201514" ID="ID_1988797313" MODIFIED="1210621168848" STYLE="fork" TEXT="podle po&#x17e;adavk&#x16f; procesoru je obsah hierarchicky ni&#x17e;&#x161;&#xed;&#xa;pam&#x11b;ti vym&#x11b;n&#x11b;n s vybranou oblast&#xed; vy&#x161;&#x161;&#xed; pam&#x11b;ti"/>
</node>
<node CREATED="1210620998763" ID="ID_1360015939" MODIFIED="1210621380422" STYLE="fork" TEXT="" VSHIFT="-3">
<node CREATED="1210427464607" ID="ID_1242846333" MODIFIED="1210621021214" TEXT="&#x10c;asov&#xe1;" VSHIFT="7">
<font BOLD="true" NAME="SansSerif" SIZE="12"/>
<node CREATED="1210427488070" HGAP="18" ID="ID_421931627" MODIFIED="1210621025134" TEXT="reference,&#xa0;kter&#xe1;&#xa0;byla&#xa0;pr&#xe1;v&#x11b;&#xa0;pou&#x17e;ita&#xa;a&#xa0;bude v&#xa0;kr&#xe1;tk&#xe9;&#xa0;dob&#x11b;&#xa0;pou&#x17e;ita&#xa0;znovu" VSHIFT="-10"/>
</node>
<node CREATED="1210427472968" ID="ID_673197099" MODIFIED="1210621015853" TEXT="Prostorov&#xe1;">
<font BOLD="true" NAME="SansSerif" SIZE="12"/>
<node CREATED="1210427499409" HGAP="19" ID="ID_87098051" MODIFIED="1210621317317" TEXT="n&#xe1;sleduj&#xed;c&#xed; reference&#xa0;bude pravd&#x11b;- &#xa;podobn&#x11b; le&#x17e;et&#xa0;pobl&#xed;&#x17e;&#xa0;pr&#xe1;v&#x11b;&#xa0;pou&#x17e;it&#xe9;" VSHIFT="-10"/>
</node>
</node>
</node>
</node>
<node BACKGROUND_COLOR="#ccff66" COLOR="#000000" CREATED="1210412122870" HGAP="3" ID="ID_411861444" MODIFIED="1210621411095" POSITION="left" TEXT="P&#x159;&#xed;stup k dat&#x16f;m" VGAP="9" VSHIFT="1">
<font BOLD="true" NAME="DejaVu Sans Condensed" SIZE="14"/>
<node BACKGROUND_COLOR="#bfdefc" COLOR="#000000" CREATED="1210412130647" ID="ID_1608192136" MODIFIED="1210620898500" TEXT="RAM">
<node CREATED="1210421469493" ID="ID_338296263" MODIFIED="1210430253063" STYLE="fork" TEXT="Random Access Memory"/>
<node CREATED="1210421475150" ID="ID_1836021930" MODIFIED="1210620940871" STYLE="fork" TEXT="doba odezvy je nez&#xe1;visl&#xe1; na adrese"/>
<node CREATED="1210421510925" ID="ID_577713625" MODIFIED="1210430253062" STYLE="fork" TEXT="data jsou po vypnut&#xed; nap&#xe1;jen&#xed; ztracena"/>
<node COLOR="#990000" CREATED="1210430254759" ID="ID_366623520" MODIFIED="1210620983829" TEXT="Fyzick&#xe9; uspo&#x159;&#xe1;d&#xe1;n&#xed;&#xa;        (moduly)">
<font BOLD="true" NAME="SansSerif" SIZE="12"/>
<node CREATED="1210430275982" ID="ID_1856103340" MODIFIED="1210430277776" TEXT="SIMM">
<node CREATED="1210430289053" ID="ID_1664134556" MODIFIED="1210505559990" STYLE="fork" TEXT="Single Inline Memory Module"/>
<node CREATED="1210430303377" ID="ID_1850172621" MODIFIED="1210505559990" STYLE="fork" TEXT="30 a 72 pin&#x16f;"/>
</node>
<node CREATED="1210430278069" ID="ID_175329628" MODIFIED="1210430279336" TEXT="DIMM">
<node CREATED="1210430310389" ID="ID_1290040181" MODIFIED="1210505559990" STYLE="fork" TEXT="Dual Inline Memory Module"/>
<node CREATED="1210430338329" ID="ID_688997678" MODIFIED="1210505559989" STYLE="fork" TEXT="168 a 184 pin&#x16f; (nejb&#x11b;&#x17e;n&#x11b;j&#x161;&#xed;)"/>
<node CREATED="1210431244079" ID="ID_921297149" MODIFIED="1210621291160" STYLE="fork" TEXT="SDRAM, DDR&#xa0;SDRAM"/>
</node>
<node CREATED="1210430279702" ID="ID_1275458316" MODIFIED="1210430280800" TEXT="RIMM">
<node CREATED="1210430573813" ID="ID_1608558918" MODIFIED="1210505559989" STYLE="fork" TEXT="Rambus Inline Memory Module"/>
<node CREATED="1210430583947" ID="ID_1775581301" MODIFIED="1210505559978" STYLE="fork" TEXT="184 pin&#x16f;"/>
</node>
</node>
</node>
<node BACKGROUND_COLOR="#bfdefc" COLOR="#000000" CREATED="1210412132188" ID="ID_378570078" MODIFIED="1210620898501" TEXT="SAM">
<node CREATED="1210421522892" ID="ID_1079599965" MODIFIED="1210621198684" STYLE="fork" TEXT="Serial Access Memory"/>
<node CREATED="1210421537022" ID="ID_357376709" MODIFIED="1210621198684" STYLE="fork" TEXT="sekven&#x10d;n&#xed; p&#x159;&#xed;stup (nap&#x159;. magnetick&#xe1; p&#xe1;ska)"/>
</node>
<node BACKGROUND_COLOR="#bfdefc" COLOR="#000000" CREATED="1210412133419" ID="ID_618763904" MODIFIED="1210621362254" TEXT="DAM">
<node CREATED="1210421569790" ID="ID_982450989" MODIFIED="1210621198683" STYLE="fork" TEXT="Direct Access Memory"/>
<node CREATED="1210421574676" ID="ID_687133604" MODIFIED="1210621198684" STYLE="fork" TEXT="p&#x159;&#xed;m&#xfd; p&#x159;&#xed;stup (nap&#x159;. pevn&#xfd; disk)"/>
<node CREATED="1210421593133" ID="ID_509878939" MODIFIED="1210621198684" STYLE="fork" TEXT="doba odezvy z&#xe1;visl&#xe1; na adrese"/>
</node>
<node BACKGROUND_COLOR="#bfdefc" COLOR="#000000" CREATED="1210412134877" ID="ID_1724733613" MODIFIED="1210620898500" TEXT="CAM">
<node CREATED="1210421606725" ID="ID_1914228402" MODIFIED="1210621198683" STYLE="fork" TEXT="Content Addressable Memory"/>
<node CREATED="1210421623824" ID="ID_856202200" MODIFIED="1210621198683" STYLE="fork" TEXT="adresovan&#xe1; obsahem"/>
</node>
</node>
<node BACKGROUND_COLOR="#ccff66" COLOR="#000000" CREATED="1210412138167" HGAP="15" ID="ID_47842455" MODIFIED="1210621390990" POSITION="right" TEXT="Implementace" VGAP="14">
<font BOLD="true" NAME="DejaVu Sans Condensed" SIZE="14"/>
<node BACKGROUND_COLOR="#bfdefc" COLOR="#000000" CREATED="1210412149279" ID="ID_1827861773" MODIFIED="1210620894613" TEXT="RWM">
<node CREATED="1210422256353" ID="ID_259858052" MODIFIED="1210621053299" STYLE="fork" TEXT="Read/Write Memory"/>
<node CREATED="1210423679346" ID="ID_120762697" MODIFIED="1210621053299" STYLE="fork" TEXT="&#x10d;ten&#xed; a z&#xe1;pis na libovolnou adresu"/>
</node>
<node BACKGROUND_COLOR="#bfdefc" COLOR="#000000" CREATED="1210412152533" ID="ID_1064844563" MODIFIED="1210620894614" TEXT="ROM">
<node CREATED="1210423693796" ID="ID_87844580" MODIFIED="1210621053300" STYLE="fork" TEXT="Read-Only Memory"/>
<node CREATED="1210423716866" ID="ID_1516771311" MODIFIED="1210621053299" STYLE="fork" TEXT="pouze &#x10d;ten&#xed;"/>
</node>
<node BACKGROUND_COLOR="#bfdefc" COLOR="#000000" CREATED="1210412154580" ID="ID_1344022197" MODIFIED="1210620894614" TEXT="SRAM">
<node CREATED="1210423727620" ID="ID_1893046388" MODIFIED="1210621053300" STYLE="fork" TEXT="Static Random Access Memory"/>
<node CREATED="1210423742516" ID="ID_1103446163" MODIFIED="1210621053300" STYLE="fork" TEXT="p&#x159;i ztr&#xe1;t&#x11b; nap&#xe1;jen&#xed;, ztr&#xe1;c&#xed; informaci"/>
<node CREATED="1210429899500" ID="ID_580559287" MODIFIED="1210621056495" STYLE="fork" TEXT="nen&#xed; nutn&#xe9; periodicky obnovovat">
<font BOLD="true" NAME="SansSerif" SIZE="12"/>
</node>
<node CREATED="1210430147514" ID="ID_1276745653" MODIFIED="1210621062368" STYLE="fork" TEXT="tvo&#x159;eny &#x161;esti tranzistory">
<font BOLD="true" NAME="SansSerif" SIZE="12"/>
</node>
</node>
<node BACKGROUND_COLOR="#bfdefc" COLOR="#000000" CREATED="1210412157588" ID="ID_1503091628" MODIFIED="1210621258068" TEXT="DRAM">
<font BOLD="true" NAME="SansSerif" SIZE="12"/>
<node CREATED="1210423750573" ID="ID_1537713131" MODIFIED="1210430970910" STYLE="fork" TEXT="Dynamic Random Access Memory"/>
<node CREATED="1210423765390" ID="ID_1281424994" MODIFIED="1210621048664" STYLE="fork" TEXT="informaci je nutn&#xe9; periodicky obnovovat cyklem &#x10d;ten&#xed;">
<font BOLD="true" NAME="SansSerif" SIZE="12"/>
</node>
<node CREATED="1210430010048" ID="ID_822546741" MODIFIED="1210621064382" STYLE="fork" TEXT="tvo&#x159;eny dvoj&#xed;c&#xed; kondenz&#xe1;toru a tranzistoru">
<font BOLD="true" NAME="SansSerif" SIZE="12"/>
</node>
<node CREATED="1210429940373" ID="ID_574031379" MODIFIED="1210621068752" STYLE="fork" TEXT="n&#xe1;ro&#x10d;n&#x11b;j&#x161;&#xed; na &#x159;&#xed;d&#xed;c&#xed; logiku">
<font BOLD="true" NAME="SansSerif" SIZE="12"/>
</node>
<node BACKGROUND_COLOR="#ffffff" COLOR="#990000" CREATED="1210430973982" ID="ID_1669275135" MODIFIED="1210621462968" TEXT="Typy" VSHIFT="3">
<font BOLD="true" NAME="SansSerif" SIZE="12"/>
<node CREATED="1210620713624" ID="ID_1234429523" MODIFIED="1210620715722" TEXT="DRAM"/>
<node CREATED="1210430978377" ID="ID_84326778" MODIFIED="1210431004474" TEXT="FPM"/>
<node CREATED="1210431004872" ID="ID_1550809758" MODIFIED="1210431012154" TEXT="(B)EDO"/>
<node CREATED="1210431013409" ID="ID_1775322832" MODIFIED="1210621455064" TEXT="SDRAM">
<font BOLD="true" NAME="SansSerif" SIZE="12"/>
<node CREATED="1210431162677" ID="ID_1480729675" MODIFIED="1210621339542" STYLE="fork" TEXT="datov&#xe9; slovo 64 bit&#x16f;"/>
<node CREATED="1210431173132" ID="ID_20204891" MODIFIED="1210621339542" STYLE="fork" TEXT="66-133MHz"/>
</node>
<node CREATED="1210431018825" ID="ID_606109476" MODIFIED="1210621455064" TEXT="DDR SDRAM">
<font BOLD="true" NAME="SansSerif" SIZE="12"/>
<node CREATED="1210431057473" ID="ID_776827626" MODIFIED="1210621216428" STYLE="fork" TEXT="Double Data Rate"/>
<node COLOR="#990000" CREATED="1210431067880" ID="ID_1565684864" MODIFIED="1210621223355" STYLE="fork" TEXT="b&#x11b;hem hodinov&#xe9;ho cyklu prov&#xe1;d&#xed; v&#xfd;b&#x11b;r data s&#xa;n&#xe1;b&#x11b;&#x17e;nou a sestupnou hranou hodinov&#xe9;ho impulsu"/>
<node CREATED="1210431192350" ID="ID_633355422" MODIFIED="1210621216860" STYLE="fork" TEXT="200-533MHz"/>
</node>
<node CREATED="1210431021496" ID="ID_1402944264" MODIFIED="1210621455064" TEXT="RD RAM">
<font BOLD="true" NAME="SansSerif" SIZE="12"/>
<node CREATED="1210431128972" ID="ID_32454919" MODIFIED="1210621339541" STYLE="fork" TEXT="Rambus"/>
<node CREATED="1210431132341" ID="ID_454671811" MODIFIED="1210621339542" STYLE="fork" TEXT="kr&#xe1;tk&#xe9; datov&#xe9; slovo (16 bit&#x16f;)"/>
<node CREATED="1210431135980" ID="ID_20438082" MODIFIED="1210621339542" STYLE="fork" TEXT="a&#x17e; 1200MHz"/>
</node>
</node>
</node>
<node BACKGROUND_COLOR="#bfdefc" COLOR="#000000" CREATED="1210421459710" ID="ID_1906409191" MODIFIED="1210620894613" TEXT="PROM">
<node CREATED="1210423771005" ID="ID_1654479343" MODIFIED="1210621212868" STYLE="fork" TEXT="Programmable Read Only Memory"/>
<node CREATED="1210423787638" ID="ID_1476024383" MODIFIED="1210621212868" STYLE="fork" TEXT="programovateln&#xe9; pouze jednou"/>
</node>
</node>
<node BACKGROUND_COLOR="#ff9900" CREATED="1210433406759" HGAP="11" ID="ID_1504542520" MODIFIED="1210621394046" POSITION="right" TEXT="Ochrana pam&#x11b;ti">
<font BOLD="true" NAME="DejaVu Sans Condensed" SIZE="14"/>
<node BACKGROUND_COLOR="#bfdefc" COLOR="#000000" CREATED="1210433525369" ID="ID_1753644505" MODIFIED="1210620904164" TEXT="Mezn&#xed; registry">
<node CREATED="1210433649203" ID="ID_1078350808" MODIFIED="1210433701367" TEXT="obsah&#xa0;MAR&#xa0;se&#xa0;porovn&#xe1;v&#xe1;&#xa0;s&#xa0;limitn&#xed;mi&#xa0;hodnotami"/>
<node CREATED="1210435802473" ID="ID_1633251817" MODIFIED="1210621098337" TEXT="p&#x159;i inicializaci programu se do b&#xe1;zov&#xe9;ho registru zanese po&#x10d;&#xe1;te&#x10d;n&#xed; &#xa;adresa p&#x159;id&#x11b;len&#xe9;ho adresov&#xe9;ho prostoru a do registru limit jeho d&#xe9;lka "/>
<node CREATED="1210435866307" ID="ID_1798216914" MODIFIED="1210435893598" TEXT="re&#x161;&#xed; relokaci adres">
<node CREATED="1210435894243" ID="ID_1961269191" MODIFIED="1210435923438" TEXT="fin&#xe1;ln&#xed; adresa se z&#xed;sk&#xe1; jako sou&#x10d;et offsetu a b&#xe1;ze"/>
</node>
<node CREATED="1210435925102" ID="ID_42572574" MODIFIED="1210435973703" TEXT="nev&#xfd;hodou je nutnost prov&#xe9;st jedno s&#x10d;&#xed;t&#xe1;n&#xed;&#xa;a porovn&#xe1;n&#xed; p&#x159;i ka&#x17e;d&#xe9;m p&#x159;&#xed;stupu do pam&#x11b;ti"/>
</node>
<node BACKGROUND_COLOR="#bfdefc" COLOR="#000000" CREATED="1210433529145" ID="ID_528495175" MODIFIED="1210621350181" TEXT="Maska" VSHIFT="11">
<node CREATED="1210433658019" ID="ID_1257743668" MODIFIED="1210433999902" TEXT="pam&#x11b;&#x165;&#xa0;je&#xa0;rozd&#x11b;lena&#xa0;na&#xa0;str&#xe1;nky"/>
<node CREATED="1210434000598" ID="ID_1507566148" MODIFIED="1210434004238" TEXT="maska&#xa0;=&#xa0;&#x10d;&#xed;slo&#xa0;str&#xe1;nky"/>
</node>
<node BACKGROUND_COLOR="#bfdefc" COLOR="#000000" CREATED="1210433535631" ID="ID_635240955" MODIFIED="1210620904164" TEXT="Kl&#xed;&#x10d;e">
<node CREATED="1210433665435" ID="ID_217795861" MODIFIED="1210433696351" TEXT="prakticky&#xa0;tot&#xe9;&#x17e;&#xa0;co&#xa0;maska"/>
<node CREATED="1210433696628" ID="ID_830055108" MODIFIED="1210433697303" TEXT="srovn&#xe1;n&#xed;&#xa0;prov&#xe1;d&#xed;&#xa0;software"/>
</node>
<node BACKGROUND_COLOR="#bfdefc" COLOR="#000000" CREATED="1210433538548" ID="ID_956216837" MODIFIED="1210620904164" TEXT="Metabity">
<node CREATED="1210433672260" ID="ID_125103519" MODIFIED="1210433678038" TEXT="pomocn&#xe9;&#xa0;bity&#xa0;p&#x159;idan&#xe9;&#xa0;k&#xa0;datov&#xe9;mu&#xa0;obsahu&#xa0;pam&#x11b;ti"/>
</node>
</node>
<node BACKGROUND_COLOR="#ff9900" CREATED="1210434781127" HGAP="11" ID="ID_335892128" MODIFIED="1210621395998" POSITION="right" TEXT="Metody alokace pam&#x11b;ti">
<font BOLD="true" NAME="DejaVu Sans Condensed" SIZE="14"/>
<node CREATED="1210434798546" ID="ID_1168423693" MODIFIED="1210621198682" STYLE="fork" TEXT="Prvn&#xed; vhodn&#xfd; v&#xfd;b&#x11b;r (first fit)"/>
<node CREATED="1210434820807" ID="ID_1630913508" MODIFIED="1210621198683" STYLE="fork" TEXT="N&#xe1;sledn&#xfd; vhodn&#xfd; v&#xfd;b&#x11b;r (next fit)"/>
<node CREATED="1210434837907" ID="ID_909392407" MODIFIED="1210621198683" STYLE="fork" TEXT="Nejlep&#x161;&#xed; v&#xfd;b&#x11b;r (best fit)"/>
<node CREATED="1210434847338" ID="ID_1129694645" MODIFIED="1210621198683" STYLE="fork" TEXT="Nejhor&#x161;&#xed; v&#xfd;b&#x11b;r (worst fit)"/>
</node>
<node BACKGROUND_COLOR="#cfdbff" CREATED="1210613000708" HGAP="11" ID="ID_1179121230" MODIFIED="1210621397727" POSITION="right" TEXT="Virtu&#xe1;ln&#xed; pam&#x11b;&#x165;" VSHIFT="5">
<font BOLD="true" NAME="DejaVu Sans Condensed" SIZE="14"/>
<node COLOR="#990000" CREATED="1210613473093" ID="ID_866424697" MODIFIED="1210620911669" TEXT="umo&#x17e;&#x148;uje roz&#x161;&#xed;&#x159;en&#xed; souvisl&#xe9;ho pam&#x11b;&#x165;ov&#xe9;ho prostoru dostupn&#xe9;ho jednotliv&#xfd;m proces&#x16f;m &#xa;na konstantn&#xed; hodnotu, kter&#xe1; m&#x16f;&#x17e;e p&#x159;esahovat velikost re&#xe1;ln&#xe9; dostupn&#xe9; pam&#x11b;ti">
<font ITALIC="true" NAME="SansSerif" SIZE="12"/>
</node>
<node CREATED="1210617621924" ID="ID_1293408586" MODIFIED="1210618172464" TEXT="hardware prov&#xe1;d&#xed; mapov&#xe1;n&#xed; virtu&#xe1;ln&#xed; adresy na fyzickou "/>
<node CREATED="1210617767844" ID="ID_303543751" MODIFIED="1210618169950" TEXT="virtu&#xe1;ln&#xed; prostor je d&#x11b;len na stejn&#x11b; velk&#xe9; str&#xe1;nky, &#xa;kter&#xe9; jsou opera&#x10d;n&#xed;m syst&#xe9;mem p&#x159;id&#x11b;lov&#xe1;ny"/>
<node CREATED="1210618012906" ID="ID_628352501" MODIFIED="1210618039421" TEXT="fyzick&#xe1; pam&#x11b;&#x165; je d&#x11b;lena na stejn&#x11b; velk&#xe9; r&#xe1;mce"/>
<node CREATED="1210618118275" ID="ID_1179754889" MODIFIED="1210618152045" TEXT="str&#xe1;nka je jednotkou mapov&#xe1;n&#xed; a sou&#x10d;asn&#x11b; je to jednotka&#xa;p&#x159;enosu mezi diskem a hlavn&#xed; pam&#x11b;t&#xed; (fyzickou pam&#x11b;t&#xed;)"/>
<node BACKGROUND_COLOR="#bfdefc" COLOR="#000000" CREATED="1210617515832" ID="ID_17823883" MODIFIED="1210620900868" TEXT="V&#xfd;hody">
<node CREATED="1210617519577" ID="ID_961935571" MODIFIED="1210618177160" TEXT="ka&#x17e;d&#xfd; proces m&#xe1; vlastn&#xed; pam&#x11b;&#x165;ov&#xfd; prostor (vz&#xe1;jemn&#xe1; ochrana proces&#x16f;) "/>
<node CREATED="1210617560184" ID="ID_1737942576" MODIFIED="1210618178968" TEXT="sou&#x10d;asn&#x11b; umo&#x17e;&#x148;uje snadn&#xe9; sd&#xed;len&#xed; pam&#x11b;ti "/>
<node CREATED="1210617575753" ID="ID_1076200676" MODIFIED="1210618180176" TEXT="o um&#xed;st&#x11b;n&#xed; program&#x16f; v pam&#x11b;ti rozhoduje OS a nikoliv ka&#x17e;d&#xfd; program zvl&#xe1;&#x161;&#x165; "/>
</node>
</node>
<node BACKGROUND_COLOR="#ff9900" COLOR="#000000" CREATED="1210411904604" HGAP="21" ID="ID_460509359" MODIFIED="1210621421239" POSITION="left" TEXT="Pam&#x11b;&#x165;" VSHIFT="5">
<font BOLD="true" NAME="DejaVu Sans Condensed" SIZE="14"/>
<node CREATED="1210411919075" ID="ID_1413560020" MODIFIED="1210429535938" STYLE="fork" TEXT="za&#x159;&#xed;zen&#xed; schopn&#xe9; p&#x159;ijmout informace, uchovat&#xa;je po ur&#x10d;itou dobu a na po&#x17e;&#xe1;d&#xe1;n&#xed; vydat"/>
<node CREATED="1210411994264" ID="ID_571584824" MODIFIED="1210429536332" STYLE="fork" TEXT="slo&#x17e;ena z pam&#x11b;&#x165;ov&#xfd;ch m&#xed;st - adresovateln&#xfd;ch jednotek"/>
<node CREATED="1210412029524" ID="ID_238562347" MODIFIED="1210412034116" TEXT="Charakterizov&#xe1;na">
<node CREATED="1210412035075" ID="ID_1734465425" MODIFIED="1210412037772" TEXT="funkc&#xed;"/>
<node CREATED="1210412038051" ID="ID_344159276" MODIFIED="1210412040084" TEXT="kapacitou"/>
<node CREATED="1210412045068" ID="ID_1037783462" MODIFIED="1210412062762" TEXT="&#x10d;asovou odezvou"/>
</node>
</node>
</node>
</map>
