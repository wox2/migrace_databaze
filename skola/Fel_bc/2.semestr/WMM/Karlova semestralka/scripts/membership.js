//kontroluje validitu formulare
function validuj(){

    //kontrola validity    
    var validniJmeno;
    var validniPrijmeni;
    var validniMail;
    var validniTextArea;
    var validniRadio;
        
        
     if(kontrolaRadio('Jon', 'Richie', 'Tico', 'David', 'labelClen')==true){
        validniRadio=true;
     }else{
        validniRadio=false;
     }
        
    if(isEmpty('jmeno', 'labelJmeno')==true){
        validniJmeno=true;
    }else{
        validniJmeno=false;
    }
    
    if(isEmpty('prijmeni', 'labelPrijmeni')==true){
        validniPrijmeni=true;
    }else{
        validniPrijmeni=false;     
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
    if(validniRadio==true && validniJmeno==true && validniPrijmeni==true && validniTextArea==true && validniMail==true){
        return true;
    }else{
        alert("Vypln spravne formular!");
        return false;
    }
    
}

//kontrola radio buttonu
function kontrolaRadio(jon, richie, tico, david, label){
    var label = document.getElementById(label);
    var jon = document.getElementById(jon);
    var richie = document.getElementById(richie);
    var tico = document.getElementById(tico);
    var david = document.getElementById(david);

    if(jon.checked==true || richie.checked==true || tico.checked==true || david.checked==true){
        label.style.color='black';
        return true;           
    }else{
        label.style.color='red';
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