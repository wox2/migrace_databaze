/* generated javascript */
var skin = 'monobook';
var stylepath = 'http://bits.wikimedia.org/skins-1.5';

/* MediaWiki:Common.js */
/*<source lang="javascript">*/
 
/********************************\
|* Nová položka v menu nástroje *|
|*  "Načíst soubor na Commons"  *|
\********************************/
 
function AddCommonsUpload ()
  {
  nodUpload = document.getElementById ("t-upload");
 
  if (nodUpload)
    {
    var nodToolsList = nodUpload.parentNode;
    var nodUploadCommons_li = document.createElement ("li");
    var nodUploadCommons_a = document.createElement ("a");
 
    nodUploadCommons_li.id = "t-upload-commons";
    nodUploadCommons_a.href = "http://commons.wikimedia.org/wiki/Special:Upload?uselang=cs";
 
    nodUploadCommons_a.appendChild (document.createTextNode ("Načíst na Commons"));
    nodUploadCommons_li.appendChild (nodUploadCommons_a);
 
    nodToolsList.insertBefore (nodUploadCommons_li, nodUpload.nextSibling);
    }
  }
 
/********************************\
|* Nový nahrávací formulář      *|
\********************************/
 
function EasyUpload()
 {
   uploadLink = document.getElementById("t-upload");
   if (!uploadLink) return;
 
   a = uploadLink.firstChild;
   a.setAttribute('href', '/wiki/Nápověda:Načíst_soubor');
 }
 
 
/*******************************\
|* Předvyplnění popisu souboru *|
\*******************************/
 
function PrefillUploadDescription ()
  {
  if (wgPageName == "Speciální:Upload")
    document.getElementById ("wpUploadDescription").value="{{Popis souboru\n  | popis =\n  | zdroj =\n  | datum =\n  | autor =\n  | povolení =\n  | jiné verze =\n}}";
  }
 
 
/* hvězdičky pro featured articles v jiných jazycích */
function LinkFA() 
{
    var langbox = document.getElementById("p-lang");
    if (!langbox) return;
    var interwiki = langbox.getElementsByTagName("li");
    // iterace přes všechny mezijazykové odkazy
    for (var i = 0; i < interwiki.length; ++i)
    {
        var link = interwiki[i];
        var language = link.className.substring(10); // smazat "interwiki-"
        // zkusit najít odpovídající FA element
        var falink = document.getElementById("fa-link-" + language);
        if (falink)
        {
            link.className += " featured";
            link.title = "Tento článek patří mezi nejlepší";
        }
    }
}

/*******************************\
|*     Podpora pro navboxy     *|
\*******************************/

/* Pro další informace vizte [[:en:Help:Collapsing]]. */

var autoCollapse = 2;
var collapseCaption = "skryj";
var expandCaption = "ukaž";
 
function collapseTable( tableIndex )
{
    var Button = document.getElementById( "collapseButton" + tableIndex );
    var Table = document.getElementById( "collapsibleTable" + tableIndex );
 
    if ( !Table || !Button ) {
        return false;
    }
 
    var Rows = Table.rows;
 
    if ( Button.firstChild.data == collapseCaption ) {
        for ( var i = 1; i < Rows.length; i++ ) {
            Rows[i].style.display = "none";
        }
        Button.firstChild.data = expandCaption;
    } else {
        for ( var i = 1; i < Rows.length; i++ ) {
            Rows[i].style.display = Rows[0].style.display;
        }
        Button.firstChild.data = collapseCaption;
    }
}
 
function createCollapseButtons()
{
    var tableIndex = 0;
    var NavigationBoxes = new Object();
    var Tables = document.getElementsByTagName( "table" );
 
    for ( var i = 0; i < Tables.length; i++ ) {
        if ( hasClass( Tables[i], "collapsible" ) ) {
 
            /* only add button and increment count if there is a header row to work with */
            var HeaderRow = Tables[i].getElementsByTagName( "tr" )[0];
            if (!HeaderRow) continue;
            var Header = HeaderRow.getElementsByTagName( "th" )[0];
            if (!Header) continue;
 
            NavigationBoxes[ tableIndex ] = Tables[i];
            Tables[i].setAttribute( "id", "collapsibleTable" + tableIndex );
 
            var Button     = document.createElement( "span" );
            var ButtonLink = document.createElement( "a" );
            var ButtonText = document.createTextNode( collapseCaption );
 
            Button.className = "collapseButton";  //Styles are declared in Common.css
 
            ButtonLink.style.color = Header.style.color;
            ButtonLink.setAttribute( "id", "collapseButton" + tableIndex );
            ButtonLink.setAttribute( "href", "javascript:collapseTable(" + tableIndex + ");" );
            ButtonLink.appendChild( ButtonText );
 
            Button.appendChild( document.createTextNode( "[" ) );
            Button.appendChild( ButtonLink );
            Button.appendChild( document.createTextNode( "]" ) );
 
            Header.insertBefore( Button, Header.childNodes[0] );
            tableIndex++;
        }
    }
 
    for ( var i = 0;  i < tableIndex; i++ ) {
        if ( hasClass( NavigationBoxes[i], "collapsed" ) || ( tableIndex >= autoCollapse && hasClass( NavigationBoxes[i], "autocollapse" ) ) ) {
            collapseTable( i );
        } 
        else if ( hasClass( NavigationBoxes[i], "innercollapse" ) ) {
            var element = NavigationBoxes[i];
            while (element = element.parentNode) {
                if ( hasClass( element, "outercollapse" ) ) {
                    collapseTable ( i );
                    break;
                }
            }
        }
    }
} 
 

/* Test if an element has a certain class *
 *
 * Převzato z [[:en:MediaWiki:Common.js]]
 */
 
var hasClass = (function () {
    var reCache = {};
    return function (element, className) {
        return (reCache[className] ? reCache[className] : (reCache[className] = new RegExp("(?:\\s|^)" + className + "(?:\\s|$)"))).test(element.className);
    };
})();


/*****************\
|*  csWikipedia  *|
|* customization *|
\*****************/
 
function CustomizeCsWikipedia ()
  {
  AddCommonsUpload ();
  PrefillUploadDescription ();
  EasyUpload ();
  LinkFA();
  createCollapseButtons ();
  }
 
if (window.addEventListener)
  {
  window.addEventListener ("load", CustomizeCsWikipedia, false)
  }
else if (window.attachEvent)
  {
  window.attachEvent ("onload", CustomizeCsWikipedia)
  };
 
/*</source>*/

/* MediaWiki:Monobook.js */
// <source lang="javascript">
/* tooltips and access keys */
ta = new Object();
ta['ca-unprotect'] = new Array('','Odemknout tuto stránku');
ta['ca-nomove'] = new Array('','Nemáte oprávnění přesunout tuto stránku'); 
ta['ca-nstab-portál'] = new Array('c','Zobrazit portál.');

/* přidat menu pro speciální znaky */
/* Musí odpovídat MediaWiki:Edittools! **/
/* podle en:wiktionary */
function addCharSubsetMenu() {
  var copywarn = document.getElementById('specialchars');

  if (copywarn) {
    var menu = "<select style=\"display:block\" onChange=\"chooseCharSubset(selectedIndex)\">";
    menu += "<option>Středoevropské jazyky a zvláštní znaky</option>";
    menu += "<option>Matematika</option>";
    menu += "<option>AHD</option>";
    menu += "<option>Arabština</option>";
    menu += "<option>Cyrilice</option>";
    menu += "<option>Esperanto</option>";
    menu += "<option>Estonština</option>";
    menu += "<option>Francouzština</option>";
    menu += "<option>Havajština</option>";
    menu += "<option>Hebrejština</option>";
    menu += "<option>Chorvatština</option>";
    menu += "<option>IPA</option>";
    menu += "<option>Islandština</option>";
    menu += "<option>Italština</option>";
    menu += "<option>Katalánština</option>";
    menu += "<option>Maltština</option>";
    menu += "<option>Němčina</option>";
    menu += "<option>Pinyin</option>";
    menu += "<option>Polština</option>";
    menu += "<option>Portugalština</option>";
    menu += "<option>Rōmaji</option>";
    menu += "<option>Rumunština</option>";
    menu += "<option>Řečtina</option>";
    menu += "<option>Skandinávské jazyky</option>";
    menu += "<option>Srbština</option>";
    menu += "<option>Staroangličtina</option>";
    menu += "<option>Španělština</option>";
    menu += "<option>Velština</option>";
    menu += "<option>Vietnamština</option>";
    menu += "</select>";
    copywarn.innerHTML = menu + copywarn.innerHTML;

    /* implicitní sada */
    chooseCharSubset(0);

    /*********************************\
    |* umožnění vkládání entity nbsp *|
    |* vytvořil Danny B.             *|
    \*********************************/
    ndlCharinserts = document.getElementById ("specialchars").getElementsByTagName ("p") [0].getElementsByTagName ("a");
    for (intCyc = 0; intCyc < ndlCharinserts.length; intCyc++)
      if (ndlCharinserts [intCyc].childNodes [0].nodeValue == " ")
        ndlCharinserts [intCyc].onclick = function () { insertTags('\x26nbsp;','',''); return false } ;
    
  }
}

/* volba speciálních znaků */
function chooseCharSubset(s) {
  var l = document.getElementById('specialchars').getElementsByTagName('p');
  for (var i = 0; i < l.length ; i++) {
    l[i].style.display = i == s ? 'inline' : 'none';
    l[i].style.visibility = i == s ? 'visible' : 'hidden';
  }
}

/* přidaná tlačítka na edit toolbar pro všechny */
function commonCustomizeEditToolbar()
  {
  if (!mwCustomEditButtons)
    return;
//Přesměrování
  mwCustomEditButtons[mwCustomEditButtons.length] =
    {
    "imageFile": "http://upload.wikimedia.org/wikipedia/en/c/c8/Button_redirect.png",
    "speedTip": "Přesměrování",
    "tagOpen": "#REDIRECT [[",
    "tagClose": "]]",
    "sampleText": "cíl"
    };
//Horní index
  mwCustomEditButtons[mwCustomEditButtons.length] =
    {
    "imageFile": "http://upload.wikimedia.org/wikipedia/en/8/80/Button_upper_letter.png",
    "speedTip": "Horní index",
    "tagOpen": "<sup>",
    "tagClose": "</sup>",
    "sampleText": "horní index"
    };
//Dolní index
  mwCustomEditButtons[mwCustomEditButtons.length] =
    {
    "imageFile": "http://upload.wikimedia.org/wikipedia/en/7/70/Button_lower_letter.png",
    "speedTip": "Dolní index",
    "tagOpen": "<sub>",
    "tagClose": "</sub>",
    "sampleText": "dolní index"
    };
//Přeškrtnutí
  mwCustomEditButtons[mwCustomEditButtons.length] =
    {
    "imageFile": "http://upload.wikimedia.org/wikipedia/en/c/c9/Button_strike.png",
    "speedTip": "Přeškrtnutí",
    "tagOpen": "<del>",
    "tagClose": "</del>",
    "sampleText": "přeškrtnutý text"
    };
//Odřádkování
  mwCustomEditButtons[mwCustomEditButtons.length] =
    {
    "imageFile": "http://upload.wikimedia.org/wikipedia/en/1/13/Button_enter.png",
    "speedTip": "Odřádkování",
    "tagOpen": "<br />",
    "tagClose": "",
    "sampleText": ""
    }
  }

/***********************************\
|* Uživatelský prostor pro anonymy *|
|* vytvořil Danny B.               *|
\***********************************/
function AddAnonUserSpace ()
  {
  var nodLogin = document.getElementById ("pt-login");
  if (nodLogin)
    {
    var nodLinksList = nodLogin.parentNode;
    //var nodUserPage_li = document.createElement ("li");
    var nodUserTalk_li = document.createElement ("li");
    //var nodUserPage_a = document.createElement ("a");
    var nodUserTalk_a = document.createElement ("a");

    //nodUserPage_li.id = "pt-anonuserpage";
    nodUserTalk_li.id = "pt-anontalk";
    //nodUserPage_a.href = "/wiki/Speci%C3%A1ln%C3%AD:Mypage";
    nodUserTalk_a.href = "/wiki/Speci%C3%A1ln%C3%AD:Mytalk";

    //nodUserPage_a.appendChild (document.createTextNode ("Moje stránka"));
    nodUserTalk_a.appendChild (document.createTextNode ("Moje diskuse"));
    //nodUserPage_li.appendChild (nodUserPage_a);
    nodUserTalk_li.appendChild (nodUserTalk_a);

    //nodLinksList.insertBefore (nodUserPage_li, nodLogin);
    nodLinksList.insertBefore (nodUserTalk_li, nodLogin);
    }
  }

/**********************\
|* Změna názvu článku *|
|* vytvořil Danny B.  *|
\**********************/
function SpravnyNazev ()
  {
  var nodSpravnyNazev = document.getElementById ("Sablona__Nazev_Spravny-nazev");

  if (nodSpravnyNazev)
    {
    var nodSablonaNazev = document.getElementById ("Sablona__Nazev");
    var nodNadpis = document.getElementsByTagName ("h1") [0];
    var anodNadpisObsah = nodNadpis.childNodes;
    var nodPuvodniNazev = null;
    for (var i = 0; i < anodNadpisObsah.length; ++i)
      {
      var n = anodNadpisObsah[i];
      if (n.nodeType == 3) // Node.TEXT_NODE
        {
        nodPuvodniNazev = n;
        break;
        }
      }
    if (nodPuvodniNazev == null) return;
    nodNadpis.replaceChild (nodSpravnyNazev, nodPuvodniNazev);
    nodSablonaNazev.parentNode.removeChild (nodSablonaNazev);
    }
  }

/****************************************************************************\
|* Přidá položku "Vypsat pahýly" do seznamu nástrojů na stránkách kategorií *|
|* vytvořil Danny B.                                                        *|
\****************************************************************************/
function addToolListStubs() {
  
  if( wgNamespaceNumber != 14 & wgNamespaceNumber != 15 ) return;
  
  var nodLiListStubs = document.createElement( 'li' );
  nodLiListStubs.id = 't-liststubs';
  
  var nodAListStubs = document.createElement( 'a' );
  nodAListStubs.href = 'http://toolserver.org/~danny_b/powersearch/pahyly-v-kategorii.php?kategorie=' + encodeURIComponent( wgTitle );
  nodAListStubs.title = 'Zobrazit seznam všech pahýlů v této kategorii';
  
  var nodUlTb = document.getElementById( 'p-tb' ).getElementsByTagName( 'ul' )[0];
  
  nodAListStubs.appendChild( document.createTextNode( 'Vypsat pahýly' ) );
  nodLiListStubs.appendChild( nodAListStubs );
  nodUlTb.appendChild( nodLiListStubs );
  
}


/* úpravy české Wikipedie */
function customize() {
  commonCustomizeEditToolbar();
  addCharSubsetMenu();
  AddAnonUserSpace ();
  SpravnyNazev ();
  addToolListStubs();
}
addOnloadHook(customize);

// START Meziprojektova navigace (z de:wikt:Mediawiki:monobook.js)

 document.write('<style type="text/css">#interProject {display: none; speak: none;} #p-tb .pBody {padding-right: 0;}<\/style>');
 function iProject() {
  if (document.getElementById("interProject")) {
   var iProject = document.getElementById("interProject").innerHTML;
   var interProject = document.createElement("div");
   interProject.style.marginTop = "0.7em";
   interProject.innerHTML = '<h5>další projekty<\/h5><div class="pBody">'+iProject+'<\/div>';
   document.getElementById("p-tb").appendChild(interProject);
  }
 }
 hookEvent("load", iProject);

// KONEC Meziprojektova navigace

//
// Wikimediaplayer [[commons:User:Gmaxwell]]
//
 
document.write('<script type="text/javascript" src="' 
             + 'http://commons.wikimedia.org/w/index.php?title=Mediawiki:Wikimediaplayer.js' 
             + '&action=raw&ctype=text/javascript&dontcountme=s"></script>');

// </source>