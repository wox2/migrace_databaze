//Wikimediaplayer, Based on wikiminiatlas by dschwen
//<pre>
var wikimediaplayer =
{
 wmpconfig:
 {
  width  : 400,
  height : 450,
  playerurl : 'http://tools.wikimedia.de/~gmaxwell/jorbis/commonsJOrbisPlayer.php',
  buttonImage: 'http://upload.wikimedia.org/wikipedia/commons/thumb/0/0c/Nuvola_apps_kaboodle.png/32px-Nuvola_apps_kaboodle.png',
  largebuttonImage: 'http://upload.wikimedia.org/wikipedia/commons/thumb/0/0c/Nuvola_apps_kaboodle.png/120px-Nuvola_apps_kaboodle.png',
  imgbase   : 'http://tools.wikimedia.de/~dschwen/wikiminiatlas/tiles/'
 },

 strings:
 {
  buttonTooltip : {
   cs:'Přehrát soubor',
   da:'Afspil medie',
   de:'Abspielen',
   en:'Play media',
   es:'Reproducir',
   fr:'Jouer le média',
   he:'נגן מדיה',
   ja:'メディアを再生する',
   nl:'Afspelen',
   no:'Spill av',
   pt:'Tocar mídia',
   ru:'Проиграть',
   zh:'戏剧声音/录影'
  },
  close : {
   ca:'tanca',
   cs:'zavřít',
   da:'luk',
   de:'schließen',
   en:'close',
   eu:'itxi',
   es:'cerrar',
   fr:'Quitter',
   he:'לסגור',
   hu:'bezárás',
   id:'tutup',
   it:'chiudi',
   ja:'閉じる',
   mk:'zatvori',
   nl:'sluiten',
   no:'lukk',
   pl:'zamknij',
   pt:'fechar',
   ru:'закрыть',
   sk:'zatvoriť',
   sl:'zapri',
   fi:'sulje',
   sv:'stäng',
   zh:'关闭',
   ar:'غلق'
  }
 },

 link : null,
 links : null,

 language : 'en',
 iframe : { div: null, iframe: null, closebutton: null},

 playpath : '',

  // vertikale position auf der Seite bestimmen
 totalOffset : function( obj, offset )
 {
  if( obj.offsetParent == null || 
      obj.offsetParent.id == 'content' )
   return offset + obj.offsetTop;
  else
   return wikimediaplayer.totalOffset(obj.offsetParent, offset + obj.offsetTop );
 },

 // move iframe around and toggle visibility
 toggleIFrame : function( e )
 {
  with(wikimediaplayer)
  {
   var newurl = this.playparam;

   if(iframe.div.style.visibility != "visible" ||
      ( ( iframe.iframe.src != newurl ) && ( this.playparam != '' ) ) )
   {
    if( iframe.iframe.src != newurl )
    {
     iframe.iframe.src = newurl;
    }
    iframe.div.style.top = (totalOffset( this, 0 ) + 20 ) + 'px';
    iframe.div.style.visibility="visible";
   }
   else {
    iframe.div.style.visibility="hidden";
    iframe.iframe.src = "";
   }
  }
  return false;
 },

 // Insert the IFrame into the page.
 loader : function()
 {
  // apply settings

  with(wikimediaplayer)
  {
   language = wgUserLanguage;

   var neediframe = false;
   var len;

   links = document.getElementsByTagName('a');
   len = links.length;
   for( var key = 0; key < len; key++ )
   {
    link = links[key];
    if( link.href && 
        ( link.href.substr(0,wmpconfig.playerurl.length) == wmpconfig.playerurl ) )
    {
     playerlink = link.href;
   

      playbutton = document.createElement('img');
      playbutton.title = strings.buttonTooltip[language] || strings.buttonTooltip.en;
      playbutton.style.padding = '0px 3px 0px 0px';
      playbutton.style.cursor = 'pointer';
      playbutton.src = wmpconfig.buttonImage;
      playbutton.playparam = playerlink;
      addEvent( playbutton, 'click', toggleIFrame );
      neediframe = true;

      // link.parentNode.insertBefore(playbutton, link.nextSibling);
      link.parentNode.insertBefore(playbutton,link);
     }
   } //for

   //Big icon for image pages.
   if (wgNamespaceNumber==6){
   divi = document.getElementById('file');
   links = divi ? divi.getElementsByTagName('a') : [];
   len = links.length;
   var filere= /\/([^\/]*?.[Oo][Gg][Gg])$/
   for( var key = 0; key < len; key++ )
   {
    link = links[key];
    if( link.href && 
        ( filere.test(link.href) ))
    {
     filere.exec(link.href)
     playerlink = wmpconfig.playerurl + '?path=' + RegExp.$1 + '&wiki=en';

      playbutton = document.createElement('img');
      playbutton.title = strings.buttonTooltip[language] || strings.buttonTooltip.en;
      playbutton.style.padding = '0px 3px 0px 0px';
      playbutton.style.cursor = 'pointer';
      playbutton.src = wmpconfig.largebuttonImage;
      playbutton.playparam = playerlink;
      addEvent( playbutton, 'click', toggleIFrame );
      neediframe = true;

      // link.parentNode.insertBefore(playbutton, link.nextSibling);
      link.parentNode.insertBefore(playbutton,link);
     }
   } //for link
   }

   if (neediframe) {
   // prepare iframe
   iframe.div = document.createElement('div');
   iframe.div.style.visibility = 'hidden';
   iframe.div.style.width = (wmpconfig.width+2)+'px';
   iframe.div.style.height = (wmpconfig.height+2)+'px';
   iframe.div.style.margin = '0px';
   iframe.div.style.padding = '0px';
   iframe.div.style.backgroundColor = 'white';
   iframe.div.style.position = 'absolute';
   iframe.div.style.right = '2em';
   iframe.div.style.top = '1em';
   iframe.div.style.border = '1px solid gray';
   iframe.div.style.zIndex = 13;
   iframe.div.setAttribute('id', 'wmPlayer');

   iframe.closebutton = document.createElement('img');
   iframe.closebutton.title = strings.close[language] || strings.close.en;
   iframe.closebutton.src = 'http://upload.wikimedia.org/wikipedia/commons/d/d4/Button_hide.png';
   iframe.closebutton.style.zIndex = 15;
   iframe.closebutton.style.position = 'absolute';
   iframe.closebutton.style.right = '11px';
   iframe.closebutton.style.top = '9px';
   iframe.closebutton.style.width = '18px';
   iframe.closebutton.style.cursor = 'pointer';
   iframe.closebutton.playparam = '';
   addEvent( iframe.closebutton, 'click', toggleIFrame );

   iframe.iframe = document.createElement('iframe');
   iframe.iframe.frameBorder = '0';
   iframe.iframe.style.zIndex = 14;
   iframe.iframe.style.position = 'absolute';
   iframe.iframe.style.right = '1px';
   iframe.iframe.style.top = '1px';
   iframe.iframe.style.width = (wmpconfig.width)+'px';
   iframe.iframe.style.height = (wmpconfig.height)+'px';
   iframe.iframe.style.margin = '0px';
   iframe.iframe.style.padding = '0px';

   iframe.div.appendChild(iframe.iframe);
   iframe.div.appendChild(iframe.closebutton);

   var content = document.getElementById('content');
   content.insertBefore(iframe.div,content.childNodes[0]);
  } //with
 }
 }

}

//
// Hook up installation function
//
addOnloadHook(wikimediaplayer.loader);

//</pre>