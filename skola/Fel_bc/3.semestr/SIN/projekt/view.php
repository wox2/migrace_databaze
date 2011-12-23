<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="cs" xml:lang="cs">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="http://ocw.cvut.cz/moodle/theme/standard/styles.php" />
<link rel="stylesheet" type="text/css" href="http://ocw.cvut.cz/moodle/theme/standardwhite/styles.php" />

<!--[if IE 7]>
    <link rel="stylesheet" type="text/css" href="http://ocw.cvut.cz/moodle/theme/standard/styles_ie7.css" />
<![endif]-->
<!--[if IE 6]>
    <link rel="stylesheet" type="text/css" href="http://ocw.cvut.cz/moodle/theme/standard/styles_ie6.css" />
<![endif]-->


    <meta name="keywords" content="moodle, Y36SIN - ZS 09/10: Podmínky odevzdávání dokumentace projektů " />
    <title>Y36SIN - ZS 09/10: Podmínky odevzdávání dokumentace projektů</title>
    <link rel="shortcut icon" href="http://ocw.cvut.cz/moodle/theme/standardwhite/favicon.ico" />
    <!--<style type="text/css">/*<![CDATA[*/ body{behavior:url(http://ocw.cvut.cz/moodle/lib/csshover.htc);} /*]]>*/</style>-->

<script type="text/javascript" src="http://ocw.cvut.cz/moodle/lib/javascript-static.js"></script>
<script type="text/javascript" src="http://ocw.cvut.cz/moodle/lib/javascript-mod.php"></script>
<script type="text/javascript" src="http://ocw.cvut.cz/moodle/lib/overlib/overlib.js"></script>
<script type="text/javascript" src="http://ocw.cvut.cz/moodle/lib/overlib/overlib_cssstyle.js"></script>
<script type="text/javascript" src="http://ocw.cvut.cz/moodle/lib/cookies.js"></script>
<script type="text/javascript" src="http://ocw.cvut.cz/moodle/lib/ufo.js"></script>
<script type="text/javascript" src="http://ocw.cvut.cz/moodle/lib/dropdown.js"></script>  

<script type="text/javascript" defer="defer">
//<![CDATA[
setTimeout('fix_column_widths()', 20);
//]]>
</script>
<script type="text/javascript">
//<![CDATA[
function openpopup(url,name,options,fullscreen) {
  fullurl = "http://ocw.cvut.cz/moodle" + url;
  windowobj = window.open(fullurl,name,options);
  if (fullscreen) {
     windowobj.moveTo(0,0);
     windowobj.resizeTo(screen.availWidth,screen.availHeight);
  }
  windowobj.focus();
  return false;
}

function uncheckall() {
  void(d=document);
  void(el=d.getElementsByTagName('INPUT'));
  for(i=0;i<el.length;i++) {
    void(el[i].checked=0);
  }
}

function checkall() {
  void(d=document);
  void(el=d.getElementsByTagName('INPUT'));
  for(i=0;i<el.length;i++) {
    void(el[i].checked=1);
  }
}

function inserttext(text) {
  text = ' ' + text + ' ';
  if ( opener.document.forms['theform'].message.createTextRange && opener.document.forms['theform'].message.caretPos) {
    var caretPos = opener.document.forms['theform'].message.caretPos;
    caretPos.text = caretPos.text.charAt(caretPos.text.length - 1) == ' ' ? text + ' ' : text;
  } else {
    opener.document.forms['theform'].message.value  += text;
  }
  opener.document.forms['theform'].message.focus();
}

function getElementsByClassName(oElm, strTagName, oClassNames){
	var arrElements = (strTagName == "*" && oElm.all)? oElm.all : oElm.getElementsByTagName(strTagName);
	var arrReturnElements = new Array();
	var arrRegExpClassNames = new Array();
	if(typeof oClassNames == "object"){
		for(var i=0; i<oClassNames.length; i++){
			arrRegExpClassNames.push(new RegExp("(^|\\s)" + oClassNames[i].replace(/\-/g, "\\-") + "(\\s|$)"));
		}
	}
	else{
		arrRegExpClassNames.push(new RegExp("(^|\\s)" + oClassNames.replace(/\-/g, "\\-") + "(\\s|$)"));
	}
	var oElement;
	var bMatchesAll;
	for(var j=0; j<arrElements.length; j++){
		oElement = arrElements[j];
		bMatchesAll = true;
		for(var k=0; k<arrRegExpClassNames.length; k++){
			if(!arrRegExpClassNames[k].test(oElement.className)){
				bMatchesAll = false;
				break;
			}
		}
		if(bMatchesAll){
			arrReturnElements.push(oElement);
		}
	}
	return (arrReturnElements)
}
//]]>
</script>
</head>

<body  class="mod-resource course-165 dir-ltr lang-cs_utf8" id="mod-resource-view">

<div id="page">

    <div id="header" class=" clearfix">        <h1 class="headermain">Y36SIN - Úvod do softwarového inženýrství</h1>
        <div class="headermenu"><div class="navigation">
<ul><li><form action="http://ocw.cvut.cz/moodle/mod/resource/view.php" onclick="this.target='_top';"><fieldset class="invisiblefieldset"><input type="hidden" name="id" value="5287" /><button type="submit" title="Předchozí činnost"><span class="arrow ">&#x25C4;</span><span class="accesshide " >&nbsp;Předchozí činnost</span></button></fieldset></form></li><li><form action="http://ocw.cvut.cz/moodle/course/jumpto.php" method="get"  id="navmenupopup" class="popupform"><div><select id="navmenupopup_jump" name="jump" onchange="self.location=document.getElementById('navmenupopup').jump.options[document.getElementById('navmenupopup').jump.selectedIndex].value;">
   <option value="http://ocw.cvut.cz/moodle/mod/forum/view.php?id=5280" style="background-image: url(http://ocw.cvut.cz/moodle/mod/forum/icon.gif);">Novinky</option>
   <option value="http://ocw.cvut.cz/moodle/mod/resource/view.php?id=5281" style="background-image: url(http://ocw.cvut.cz/moodle/mod/resource/icon.gif);">Studijní materiály</option>
   <option value="http://ocw.cvut.cz/moodle/mod/resource/view.php?id=5313" style="background-image: url(http://ocw.cvut.cz/moodle/mod/resource/icon.gif);">Přednášky</option>
   <option value="http://ocw.cvut.cz/moodle/mod/resource/view.php?id=5283" style="background-image: url(http://ocw.cvut.cz/moodle/mod/resource/icon.gif);">Přednášky Arlow</option>
   <option value="http://ocw.cvut.cz/moodle/mod/resource/view.php?id=5284" style="background-image: url(http://ocw.cvut.cz/moodle/mod/resource/icon.gif);">Studentské referáry (ORM, CMS, SVN, Bugtracking,Ja...</option>
   <option value="http://ocw.cvut.cz/moodle/mod/resource/view.php?id=5361" style="background-image: url(http://ocw.cvut.cz/moodle/mod/resource/icon.gif);">Case nástroj Enterprise Architect - souhlas s lice...</option>
   <option value="http://ocw.cvut.cz/moodle/mod/resource/view.php?id=5285" style="background-image: url(http://ocw.cvut.cz/moodle/mod/resource/icon.gif);">Návod pro nastavení EA</option>
   <option value="http://ocw.cvut.cz/moodle/mod/resource/view.php?id=5286" style="background-image: url(http://ocw.cvut.cz/moodle/mod/resource/icon.gif);">Šablona pro EA</option>
   <option value="http://ocw.cvut.cz/moodle/mod/resource/view.php?id=5287" style="background-image: url(http://ocw.cvut.cz/moodle/mod/resource/icon.gif);">Vaše projekty</option>
   <option value="http://ocw.cvut.cz/moodle/mod/resource/view.php?id=5288" selected="selected" style="background-image: url(http://ocw.cvut.cz/moodle/mod/resource/icon.gif);">Přejít na...</option>
   <option value="http://ocw.cvut.cz/moodle/mod/resource/view.php?id=5289" style="background-image: url(http://ocw.cvut.cz/moodle/mod/resource/icon.gif);">Cvičení - Co a kdy se odevzdává?</option>
   <option value="http://ocw.cvut.cz/moodle/mod/resource/view.php?id=5290" style="background-image: url(http://ocw.cvut.cz/moodle/mod/resource/icon.gif);">Šablona na přerozdělování bodů</option>
   <option value="http://ocw.cvut.cz/moodle/mod/resource/view.php?id=5312" style="background-image: url(http://ocw.cvut.cz/moodle/mod/resource/icon.gif);">Šablona výkazu práce (=projektový deník)</option>
   <option value="http://ocw.cvut.cz/moodle/mod/resource/view.php?id=5331" style="background-image: url(http://ocw.cvut.cz/moodle/mod/resource/icon.gif);">Ukázkový projekt</option>
   <option value="http://ocw.cvut.cz/moodle/mod/forum/view.php?id=5292" style="background-image: url(http://ocw.cvut.cz/moodle/mod/forum/icon.gif);">Fórum -  Přesuny studentů mezi cvičeními</option>
   <option value="http://ocw.cvut.cz/moodle/mod/forum/view.php?id=5293" style="background-image: url(http://ocw.cvut.cz/moodle/mod/forum/icon.gif);">Fórum - Dotazy studentů studentům</option>
   <option value="http://ocw.cvut.cz/moodle/mod/forum/view.php?id=5294" style="background-image: url(http://ocw.cvut.cz/moodle/mod/forum/icon.gif);">Fórum - Dotazy studentů vyučujícím</option>
   <option value="http://ocw.cvut.cz/moodle/mod/forum/view.php?id=5298" style="background-image: url(http://ocw.cvut.cz/moodle/mod/forum/icon.gif);">Zakládání projektu </option>
   <option value="http://ocw.cvut.cz/moodle/mod/resource/view.php?id=5297" style="background-image: url(http://ocw.cvut.cz/moodle/mod/resource/icon.gif);">Podmínky a hodnocení předmětu</option>
</select><input type="hidden" name="sesskey" value="e4jxdc1fSw" /><div id="noscriptnavmenupopup" style="display: inline;"><input type="submit" value="Proveď" /></div><script type="text/javascript">
//<![CDATA[
document.getElementById("noscriptnavmenupopup").style.display = "none";
//]]>
</script></div></form></li><li><form action="http://ocw.cvut.cz/moodle/mod/resource/view.php"  onclick="this.target='_top';"><fieldset class="invisiblefieldset"><input type="hidden" name="id" value="5289" /><button type="submit" title="Další činnost"><span class="accesshide " >Další činnost&nbsp;</span><span class="arrow ">&#x25BA;</span></button></fieldset></form></li></ul>
</div></div>
    </div>    <div class="navbar clearfix">
        <div class="breadcrumb"><h2 class="accesshide " >Nacházíte se zde</h2> <ul>
<li class="first"><a onclick="this.target='_top'" href="http://ocw.cvut.cz/moodle/">moodle</a></li><li class="first"> <span class="accesshide " >/&nbsp;</span><span class="arrow sep">&#x25BA;</span> <a onclick="this.target='_top'" href="http://ocw.cvut.cz/moodle/course/view.php?id=165">Y36SIN - ZS 09/10</a></li><li class="first"> <span class="accesshide " >/&nbsp;</span><span class="arrow sep">&#x25BA;</span> <a onclick="this.target='_top'" href="http://ocw.cvut.cz/moodle/mod/resource/index.php?id=165">Studijní materiály</a></li><li class="first"> <span class="accesshide " >/&nbsp;</span><span class="arrow sep">&#x25BA;</span> Podmínky odevzdávání dokumentace projektů</li></ul></div>
        <div class="navbutton">&nbsp;</div>
    </div>
    <!-- END OF HEADER -->
    <div id="content" class=" clearfix"><div class="box generalbox generalboxcontent boxaligncenter clearfix"> <a title="Studijní materiál" href="../mod/resource/view.php?id=1771"></a>
<h3><span class="nolink">Podmínky odevzávání dokumentace projektů </span></h3>
<ul>
  <li>Každý projekt předmětu 36SIN bude mít po ustavení týmů připraven prostor, kde lze dokumentaci projektu vystavit. Jeho adresa je:<br /> &quot;<span style="font-style: italic;">https://piglet.felk.cvut.cz/courses/<span style="color: rgb(0, 0, 0); background-color: rgb(255, 255, 255);">36sin</span>/zkratkaprojektu</span><i>&quot;. </i><a href="http://service.felk.cvut.cz/pubguide/"><span style="font-style: italic;"></span></a></li>
  <li>Co není uloženo přímo na <span style="font-style: italic;">https://piglet.felk.cvut.cz/courses/36sin/.....</span>, jako kdyby nebylo.</li>
  <li>Práce se odevzdává v jednotlivých týdnech vždy v pdf souboru. Vyjimkou jsou zdrojové kódy. </li>
  <li>Úvodní wikistránka projektu závazně začíná <a href="https://piglet.felk.cvut.cz/courses/y36sin/sandbox/wiki/WikiStart">takto</a>. Zdroj níže.</li>
</ul>
<ul style="background-color: rgb(255, 255, 255);">
  <li>Prostor na dokumentaci projektu Vám vytvoří cvičící po nahlášení potřebných údajů do tohoto <a href="http://ocw.cvut.cz/moodle/mod/forum/view.php?id=5298">fóra</a>.</li>
  <li><style type="text/css"> &amp;amp;amp;amp;amp;amp;amp;lt;!-- @page { size: 21cm 29.7cm; margin: 2cm } P { margin-bottom: 0.21cm } --&amp;amp;amp;amp;amp;amp;amp;gt;</style>Dokumentace se odevzdává vložením dokumentu do SVN a vytvořením odkazu na tento soubor na úvodní stránce WIKI.</li>
  <li>SVN bude obsahovat základní rozdělení do adresářů podle následujícího schématu:<br /></li>
  <ul>
    <li>Ostatni – prostor pro jiné soubory, než je definováno níže <br /></li>
    <li>Modely – xml soubory z CASE nástroje Enterprise Architect</li>
    <li>Implementace - zdrojové kódy </li>
    <li>Odevzdane - obsahuje tyto soubory: <br /></li>
    <ul>
      <li> 01_Vize_zkratka_projektu.pdf <br /></li>
      <li> 02_Byznys_analyza_zkratka_projektu.pdf</li>
      <li> 03_Analyza_zkratka_projektu.pdf <br /></li>
      <li> 04_Koncept_architektury_zkratka_projektu.pdf</li>
      <li> 05_Oponentura1_na_zkratka_ciziho_projektu.pdf <br /></li>
      <li> 06_Oponentura1_na_zkratka_projektu.pdf <br /></li>
      <li> 07_Analyza_detail_UC_zkratka_projektu.pdf <br /></li>
      <li> 08_Navrhovy_model_zkratka_projektu.pdf <br /></li>
      <li> 09_Oponentura2_na_zkratka_ciziho_projektu.pdf <br /></li>
      <li> 10_Oponentura2_na_zkratka_projektu.pdf <br /></li>
      <li> 11_Kompletni_dokumentace_zkratka_projektu.pdf <br /></li>
      <li> 12_Zdrojove_soubory_modelu_projektu.zip <br /></li>
      <li> 13_Zdrojove_kody_projektu.zip <br /></li>
      <li> 14_Manualy_zkratka_projektu.pdf <br /></li>
      <li> 15_Oponentura3_na_zkratka_ciziho_projektu.pdf <br /></li>
      <li> 16_Oponentura3_na_zkratka_projektu.pdf <br /></li>
      <li> Prerozdeleni_bodu_zkratka_projektu.pdf <br /></li>
      <li> Vykaz_prace_zkratka_projektu.pdf </li>
    </ul>
  </ul>
  <li>Adresářová a souborová struktura ke stažení <a href="http://ocw.cvut.cz/moodle/file.php/165/komarek/struktura.ZIP">zde</a>.<br /></li>
</ul><span style="font-family: trebuchet ms,verdana,arial,helvetica,sans-serif; color: rgb(102, 102, 102);"><span style="background-color: rgb(255, 255, 255);">= Název projektu =<br />Řešitelé: ...... [[br]]<br />Email skupiny: ...... [[br]]<br />Cvičící: ...... [[br]]<br /><br />'''Odevdávané části projektu:'''[[BR]]<br />Vize projektu [https://piglet.felk.cvut.cz/svn/courses/36sin/zkratka_projektu/Odevzdane/01_Vize_zkratka_projektu.pdf][[BR]]<br />Byznys analýza analýza[https://piglet.felk.cvut.cz/svn/courses/36sin/zkratka_projektu/Odevzdane/02_Byznys_analyza_zkratka_projektu.pdf][[BR]]<br />Analytický model[https://piglet.felk.cvut.cz/svn/courses/36sin/zkratka_projektu/Odevzdane/03_Analyza_zkratka_projektu.pdf ][[BR]]<br />Koncept architektury SW[https://piglet.felk.cvut.cz/svn/courses/36sin/zkratka_projektu/Odevzdane/04_Koncept_architektury_zkratka_projektu.pdf][[BR]]<br />Analýza s detailním USE-CASE modelem [https://piglet.felk.cvut.cz/svn/courses/36sin/zkratka_projektu/Odevzdane/07_Analyza_detail_UC_zkratka_projektu.pdf][[BR]]<br />Návrhový model [https://piglet.felk.cvut.cz/svn/courses/36sin/zkratka_projektu/Odevzdane/08_Navrhovy_model_zkratka_projektu.pdf][[BR]]<br />Kompletní dokumentace [https://piglet.felk.cvut.cz/svn/courses/36sin/zkratka_projektu/Odevzdane/11_Kompletni_dokumentace_zkratka_projektu.pdf][[BR]]<br />Zdrojové soubory modelů <br />[https://piglet.felk.cvut.cz/courses/36sin/zkratka_projektu/browser/Odevzdane/12_Zdrojove_soubory_modelu_projektu.zip][[BR]]<br />Zdrojové kódy [https://piglet.felk.cvut.cz/courses/36sin/zkratka_projektu/browser/Odevzdane/13_Zdrojove_kody_projektu.zip][[BR]]<br />Instalační a uživatelský manuál<br />[https://piglet.felk.cvut.cz/courses/36sin/zkratka_projektu/browser/Odevzdane/14_Manualy_zkratka_projektu.pdf][[BR]]<br /><br /><br /><br />'''Oponentské posudky našeho projektu:'''[[BR]]<br />Vize projektu, byznys analýza a analytický model [https://piglet.felk.cvut.cz/svn/courses/36sin/zkratka_projektu/Odevzdane/06_Oponentura1_na_zkratka_projektu.pdf][[BR]]<br />Detailní USE-CASE model a návrhový model[https://piglet.felk.cvut.cz/svn/courses/36sin/zkratka_projektu/Odevzdane/10_Oponentura2_na_zkratka_projektu.pdf][[BR]]<br />Realizace a celý projekt [https://piglet.felk.cvut.cz/svn/courses/36sin/zkratka_projektu/Odevzdane/16_Oponentura3_na_zkratka_projektu.pdf][[BR]]<br /><br /><br />'''Oponentské posudky projektu: [https://piglet.felk.cvut.cz/svn/courses/36sin/zkratka_projektu/ název projektu]:'''[[BR]]<br />Vize projektu, byznys analýza a analytický model [https://piglet.felk.cvut.cz/svn/courses/36sin/zkratka_projektu/Odevzdane/05_Oponentura1_na_zkratka_ciziho_projektu.pdf][[BR]]<br />Detailní USE-CASE model a návrhový model[https://piglet.felk.cvut.cz/svn/courses/36sin/zkratka_projektu/Odevzdane/09_Oponentura2_na_zkratka_ciziho_projektu.pdf][[BR]]<br />Realizace a celý projekt[https://piglet.felk.cvut.cz/svn/courses/36sin/zkratka_projektu/Odevzdane/15_Oponentura3_na_zkratka_ciziho_projektu.pdf][[BR]]<br /><br />'''Tabulka s přerozdělením bodů ve skupině''' [https://piglet.felk.cvut.cz/svn/courses/36sin/zkratka_projektu/Odevzdane/Prerozdeleni_bodu_zkratka_projektu.pdf] [[BR]]<br /><br />'''Výkaz práce''' [https://piglet.felk.cvut.cz/svn/courses/36sin/zkratka_projektu/Odevzdane/Vykaz_prace_zkratka_projektu.pdf]<br /><br /></span></span><br /></div><div class="modified">Naposledy změněno: Tuesday,  22. September 2009, 13.27</div></div><div id="footer"><hr /><p class="helplink"></p><div class="logininfo">Momentálně na stránky přistupujete s právy hosta. (<a  href="https://ocw.cvut.cz/moodle/login/index.php">Přihlásit se</a>)</div><div class="homelink"><a  href="http://ocw.cvut.cz/moodle/course/view.php?id=165">Y36SIN - ZS 09/10</a></div></div>
</div>
</body>
</html>
