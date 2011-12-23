// @params bb_Redir,bb_Height,bb_Width,bb_Flash,bb_Target,bb_Banner,bb_Wmode,bb_FlashVer,bb_Param,bb_Embed,bb_Object,bb_Swf;
function bb_getFlashVer(){
  var bb_plgv=0,x,axo;
  if(navigator.plugins && navigator.mimeTypes.length){
    var x = navigator.plugins["Shockwave Flash"];
    if(x && x.description) {
      bb_plgv = x.description.replace(/([a-zA-Z]|\s)+/, "").replace(/(\s+r|\s+b[0-9]+)/, ".").split(".")[0];
    }
  }else{
    try{
      var axo = new ActiveXObject("ShockwaveFlash.ShockwaveFlash.7");
    }catch(e){
      try {
        var axo = new ActiveXObject("ShockwaveFlash.ShockwaveFlash.6");
        bb_plgv = 6;
      } catch(e) {
      }
      try {
        var axo = new ActiveXObject("ShockwaveFlash.ShockwaveFlash");
      } catch(e) {}
    }
    if (axo != null) {
      bb_plgv = axo.GetVariable("$version").split(" ")[1].split(",")[0];
    }
  }
  return bb_plgv;
}
if(typeof bb_flashdetected!="number" || bb_flashdetected<1){
  bb_flashdetected=bb_getFlashVer();
}
var bb_movieVars = 'clickTag='+escape(bb_Redir)+'&clickthru='+escape(bb_Redir)+'&clickTarget='+bb_Target;
var bb_movie = bb_Swf!='' ? bb_Swf : bb_Flash;
if (bb_flashdetected<6) {
  bb_movie += '?'+bb_movieVars;
  bb_movieVars = '';
}
if (bb_flashdetected>=bb_FlashVer) {
  document.write('<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width='+bb_Width+' height='+bb_Height+' '+bb_Object+' >'
  +'<param name=movie value="'+bb_movie+'">'
  +'<param name=allowScriptAccess value="always">'
  +'<param name=FlashVars value="'+bb_movieVars+'">'
  +'<param name=wmode value="'+bb_Wmode+'">'+bb_Param
  +'<embed src="'+bb_movie+'" quality=high type="application/x-shockwave-flash" width='+bb_Width+' height='+bb_Height
  +' FlashVars="'+bb_movieVars+'" wmode="'+bb_Wmode+'" '+bb_Embed+' allowScriptAccess="always"></embed></object>');
} else {
  document.write("<a href='"+bb_Redir+"' target='"+bb_Target+"'><img src='"+bb_Banner+"' width="+bb_Width+" height="+bb_Height+" border=0 /></a>");
}
