//kontroluje validitu formulare
function validuj(){

    //kontrola validity    
    var validniNick;
    var validniMail;
    var validniTextArea;
        
        
    if(isEmpty('nick', 'labelNick')==true){
        validniJmeno=true;
    }else{
        validniJmeno=false;
    }
    
    if(kontrolaMail('email', 'labelMail')==true){
        validniMail=true;
    }else{
        validniMail=false;
    }
    
    if(isEmptyTextArea('textArea', 'labelTextArea')==true){
        validniTextArea=true;
    }else{
        validniTextArea=false;
    }
   
    //samotna kontrola formulare
    if(validniNick==true && validniTextArea==true && validniMail==true){
        return true;
    }else{
        alert("Vypln spravne formular!");
        return false;
    }
    
}

//kontrola jesli je poole vyplneno pismeny
function isEmpty(pole, labelPole){
    if(document.getElementById(pole).value.length==0){
        document.getElementById(labelPole).style.color='red';
        return false;
    }else{
        var poleText = document.getElementById(pole).value;
        var regex = /^[A-Z]{1}\D+$/;
        if(regex.test(poleText)){
            document.getElementById(labelPole).style.color='black';
            return true;
        }else{
            document.getElementById(labelPole).style.color='red';
            return false;
        }       
    }
}
    
//kontrola mailove adresy
function kontrolaMail(email, labelEmail){
    if(document.getElementById(email).value.length==0){
        document.getElementById(labelEmail).style.color='red';
        return false;
    }else{
        var mail = document.getElementById(email).value;
        var regex = /^.+@.+\..{2,4}$/;
        if(regex.test(mail)){
            document.getElementById(labelEmail).style.color='black';
            return true;
        }else{
            document.getElementById(labelEmail).style.color='red';
            return false;
        }
    }    
}

//kontrola text area
function isEmptyTextArea(textArea, labelTextArea){
    if(document.getElementById(textArea).value.length==0){
        document.getElementById(labelTextArea).style.color='red';
        return false;
    }else{
        document.getElementById(labelTextArea).style.color='black';
        return true;
    }
}