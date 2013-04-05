
// Skript poidávaný poed patieku všech dokumentu na JPW

// vyskládání ovládacího panýlku navigace 

var prevNextText = ""; // promenná, do které vyskládám odkazy

function pripisOdkaz(idecko,zobrText,tipText){
	if(document.getElementById(idecko)){
		var objekt = document.getElementById(idecko);
		prevNextText += ' <a href="' + objekt.href + '" title="' + tipText + objekt.innerHTML +'">'+zobrText+'</a>'
	}
}

function navigaciDolu(){
	document.getElementById("wherenext").innerHTML = document.getElementById("navigace").innerHTML;
}

pripisOdkaz("prev","&lt;","Pøedchozí: ");
if(!jeDomovska && !jeSekce && !jePodsekce)pripisOdkaz("sel","^","Obsah podsekce ");
pripisOdkaz("next","&gt;", "Dal¹í: ");

// vypsání panýlku do dokumentu
document.write('<div id="prevnexttop" class="panel"></div>');
document.write('<div id="prevnextbottom" class="panel"></div>');
document.write('<div id="wherenext"><a href="#navigace" onclick="navigaciDolu(); return false;">Kam dál?</a></div>');
// document.write('<div id="gototop"><a href="#">na zaeátek stránky</a></div>');

// naplniní panýlku vyskládaným textem
document.getElementById("prevnexttop").innerHTML = prevNextText;
document.getElementById("prevnextbottom").innerHTML = prevNextText;


// Toplist
var ref ;
if (document.referrer){
	ref = escape(document.referrer);
	if(ref.indexOf("dusan.pc-slany") > -1 ) ref="http://stare_jpw";
	if(ref.indexOf("jakpsatweb.cz/weblog") > -1 ) ref="http://jpw_weblog";
	if(ref.indexOf("jakpsatweb.cz") > -1 ) ref="http://vnitrni_jakpsatweb";
}
else 
	ref = "nepredan";

// toplist parametr s
var topS = new Array();
topS[""]=1;
topS["undefined"]=1;
topS["zak"]=1;
topS["html"]=2;
topS["css"]=3;
topS["js"]=4;
topS["clanky"]=5;
topS["ost"]=6;
topS["en"]=7;

var topId = "49306";
var topSrc = "http://www.toplist.cz/dot.asp";
topSrc += "?id=" + topId; 
topSrc += "&http=" + ref;
if(document.body.className) topSrc += "&s=" + topS[document.body.className];
topSrc += "&r=" + Math.random();

var topImg = new Image();
topImg.src = topSrc;

// Navrcholu
var nvRef ;
if (document.referrer){
	nvRef = escape(document.referrer);
	}
else 
	nvRef = "nepredan";

var nvId = "31373";
var nvSrc = "http://c1.navrcholu.cz/hit";
nvSrc += "?site=" + nvId; 
nvSrc += ";t=lb14"; // druh obrazku
nvSrc += ";ref=" + nvRef;
nvSrc += ";r=" + Math.random();

var nvImg = new Image();
nvImg.src = nvSrc;


// ##############################################################
// Pøidání hráškové navigace
// Øídící promìnné
var jeDomovska; if(jeDomovska != true) jeDomovska= false; 
var jeSekce; if(jeSekce != true) jeSekce = false; 
var jePodsekce; if(jePodsekce != true) jePodsekce = false;
var pridanyText = ""; // promìnná, ve které se skládá obsah hráškové navigace

//vypoèítám koøen, pøedpokládá existenci loga s odkazem na HP
korenHref = document.getElementById("logoa").href; 
koren = korenHref.substr(0, korenHref.length - 10); // zkracuju o 10 posledních znakù, 10 je délka "index.html"

/* Odkaz Jak psát web */
pridanyText += "<a href='" + korenHref + "'>Jak psát web</a> ";

/* První úroveò zálozek*/
// definice zálozek
var ouskoT = new Array(), ouskoU = new Array();
ouskoT["zak"] = "Návody"; // ouskoU["zak"] = "index.html"; // nebude potøeba
ouskoT["css"] = "CSS"; ouskoU["css"] = "css/index.html";
ouskoT["html"] = "HTML"; ouskoU["html"] = "html/index.html";
ouskoT["js"] = "JavaScript"; ouskoU["js"] = "javascript/index.html";
ouskoT["clanky"] = "Èlánky"; ouskoU["clanky"] = "clanky/index.html";
ouskoT["ost"] = "Ostatní"; ouskoU["ost"] = "navigace/ostatni.html";

ouskoA = document.body.className; // aktivní zálozka

pridanyText += "<b>&gt;</b> "; // první zobáèek

// zápis první úrovnì
// v pøípadì sekce návodù ("zak") se prvnímu hrášku nepøidává odkaz 
// odkaz se nepøidává také v pøípadì, ze je stránka sekcí (jeSekce)
if(ouskoA == "zak" || jeSekce) {
	pridanyText += ouskoT[ouskoA];
}
// domovská stránka
/*else if(jeDomovska)
{
	pridanyText += "jste na hlavní stránce";
}*/
// jinak se odkaz pøidává
else {
	pridanyText += " <a href='" + koren + ouskoU[ouskoA] + "'>" ; 
	pridanyText += ouskoT[ouskoA];
	pridanyText += "</a>";
} 

/* Podsekce */
// vypisuje se v pøípadì, ze to není domovská stránka a ze existuje prvek s id="sel"
if(!jeDomovska && document.getElementById("sel")){
	podsekce = document.getElementById("sel");
	
	// jestlize je to podsekce, nebo kdyz ma podsekce stejny odkaz jako sekce, bude jenom text	
	if((jePodsekce || podsekce.href == koren + ouskoU[ouskoA])&& !jeSekce){
		pridanyText += " <b>&gt;</b> "; //  zobáèek
		pridanyText += podsekce.innerHTML;
	}
	else if(jeSekce){ // uz vím, ze existuje id="sel"
		pridanyText += " &nbsp; (rozcestník podsekce " + podsekce.innerHTML +")";
	}
	else {
		pridanyText += " <b>&gt;</b> "; //  zobáèek
		pridanyText += " <a href='" + podsekce.href + "'>" + podsekce.innerHTML + "</a>";;
	}
}

/* Vlastní název stránky */

if(!jeDomovska && !jeSekce && !jePodsekce){
	pridanyText += " <b>&gt;</b> "; //  zobáèek
	nazev = document.getElementsByTagName("h1")[0];
	pridanyText += nazev.innerHTML;
}

/* Vypsání hráškové navigace do stránky */
if(document.getElementById("hrasek")) document.getElementById("hrasek").innerHTML += pridanyText;
if(document.getElementById("hrasekdole")) document.getElementById("hrasek").innerHTML += "Umístìní této stránky v hierarchii webu: " + pridanyText;

// konec hráškové navigace ############################################

