// JavaScript Document
function navesUdalosti(){
  var menu = document.getElementBuId('menu');
  menu.addEventListener('click',klikOdkaz,false);
  var odkazy = menu.getElementsByTagName('a');
  
  for (var i = 0; i < odkazy.length; i++){
      odkazy[i].addEventListener('click',klikOdkaz,false);
  }
}

function klikOdkaz(event){
  event.stopPropagation();
  event.preventDefault();
  //alert("Kliknuto na odkaz");
  
  var reg = new XMLHttpRequest();
  var url = event.target;
  req.open('GET',url,true');
  req.addEventListener('readystatechange',httpObsluha, false);
  req.send(null);
}

function klikMenu(){
  alert("Kliknuto do menu");
}

function httpObsluha(event){
  var req = event.target;
  
  if(req.readyState == 4){
      alert('HTTP kod: '+ req.status);
      document.getElementById('content').innerHTML = req. 
  }
}                                         