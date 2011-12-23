function validace(){
    var nick = document.getElementById("loginValue1");
    var heslo = document.getElementById("loginValue2");
     
    if(nick.value.length==0 || heslo.value.length==0){
        alert("Vypln spravne nick a heslo!");  
        return false; 
    }else{
        return true;
    }
}