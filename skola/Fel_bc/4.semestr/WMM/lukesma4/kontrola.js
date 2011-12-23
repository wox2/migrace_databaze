//kontroluje validitu formulare
function validuj(){

    //kontrola validity    
    var validniNick;
    var validniMail;
    var validniTextArea;
        
        
    if(kontrolaPole('nick', 'labelNick')==true){
        validniNick=true;
    }else{
        validniNick=false;
    }
    
    if(kontrolaMail('email', 'labelMail')==true){
        validniMail=true;
    }else{
        validniMail=false;
    }
    
    if(kontrolaTextArea('textArea', 'labelTextArea')==true){
        validniTextArea=true;
    }else{
        validniTextArea=false;
    }
   
    //samotna kontrola formulare
    if(validniNick==true && validniTextArea==true && validniMail==true){
        return true;
    }else{
        alert("Vypln spravne formular! Modře vyznačená pole nejsou správně vyplněna");
        return false;
    }
    
}

//kontrola jesli je poole vyplneno pismeny
function kontrolaPole(pole, labelPole){
    if(document.getElementById(pole).value.length==0){
        document.getElementById(labelPole).style.color='#0000FF';
        return false;
    }else{
            document.getElementById(labelPole).style.color='#FFFF00';
            return true;      
    }
}
    
//kontrola mailove adresy
function kontrolaMail(email, labelEmail){
    if(document.getElementById(email).value.length==0){
        document.getElementById(labelEmail).style.color='#0000FF';
        return false;
    }else{
        var mail = document.getElementById(email).value;
        var regex = /^.+@.+\..{2,4}$/;
        if(regex.test(mail)){
            document.getElementById(labelEmail).style.color='#FFFF00';  
            return true;
        }else{
            document.getElementById(labelEmail).style.color='#0000FF';
            return false;
        }
    }    
}

//kontrola text area
function kontrolaTextArea(textArea, labelTextArea){
    if(document.getElementById(textArea).value.length==0){
        document.getElementById(labelTextArea).style.color='#0000FF';
        return false;
    }else{
        document.getElementById(labelTextArea).style.color='#FFFF00';
        return true;
    }
}