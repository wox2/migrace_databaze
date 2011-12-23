(: Vypis seznamu prezdivek s jmeny, ktera onu prezdivku maji) :)

(:1 dotaz provadejici spojeni dat ze 2 ruznych XML dokumentu nebo ze dvou logicky oddelenych casti XML dokumentu (viz. priklad 6 ze cviceni ),
 Alespoň jednou musíte vhodně využít každou z následujících konstrukcí/funkcí/operátorů: distinct-values
:)


<seznam-prezdivek>{
	for $prezdivka in fn:distinct-values( fn:doc("../prezdivky.xml")//prezdivka )
	return
	    <hrac>
			<prezdivka> {fn:data($prezdivka)}</prezdivka>
			{
      for $cislo in fn:doc("../data.xml")//cislo
      where $cislo/prezdivka = $prezdivka
      return 
      			element jmeno
     {fn:data($cislo/jmeno)}

      }
      </hrac>
      }
</seznam-prezdivek>      
