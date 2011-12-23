 var prom=1;   // prom je Number
    prom="text";  // prom je String
    function nastavFokus()
    {
		document.getElementById("jmeno1").focus();
		expandCollapse('kontakt');
    }

	function jeEmail(value){
		reg_vyraz=/.+@.+/
		return reg_vyraz.test(value)
	}

    function jeCeleCislo(retezec)
    {
	  str = retezec.toString();
	  for (var i=0;i<str.length;i++){
        var znak = str.charAt(i);      //alert("znak="+znak+" i="+i);
        if (i==0 && znak=="-") {continue;}
        if (znak <"0" || znak>"9") {return false;}
      }
      return true;
    }
    
    function jePrazdny(retezec)
    {
      for(var i = 0; i < retezec.length; i++) {
        var znak = retezec.charAt(i);
        if ((znak != ' ') && (znak != '\n') && (znak != '\t')) return false;
      }
      return true;
    }

    function provedKontrolu(formular) {
      var prazdna_povinna_pole = "";
      var neciselna_pole = "\n\nChybný formát:\n";
      var kontrola = true;
      for(var i = 0; i < formular.length; i++) {
        var elem = formular.elements[i];
        if (((elem.type == "text") || (elem.type == "textarea")) && elem.povinne) {
            // kontrola prazdnosti elementu
            if ((elem.value == null) || (elem.value == "") || jePrazdny(elem.value)) {
                prazdna_povinna_pole += "\n    "+ elem.name;
                kontrola = false;
            }
         }
		 if (elem.email) {
			if (!jeEmail(elem.value)) {
				neciselna_pole +="\n    "+elem.name+" nemá platný formát";				
				kontrola = false;
			}
		 }
		 if (elem.cislo) {
           if (!jeCeleCislo(elem.value)){
                neciselna_pole +="\n    "+elem.name+" není číslo";
                kontrola = false;
              }
           }
      }
      if (!kontrola) alert("Chyba ve formuláři.\n Následujicí povinná pole jsou prázdná:\n"+prazdna_povinna_pole+neciselna_pole);
      return kontrola
    }                  
   


