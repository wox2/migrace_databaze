xquery version "1.0";

<prezdivky>{
	for $prezdivka in fn:distinct-values( fn:doc("../prezdivky.xml")//prezdivka )
	return
	     <hrac>
			element prezdivka {fn:data($prezdivka)}
			element jmeno  {fn:data($prezdivka/../jmeno)}
			
      </hrac>
}</prezdivky>

(: Vypis zbozi v 1. objednavce, ktere maji narok na slevu ( jsou uvedeny v slevy.xml ) :)

(:

1 dotaz provadejici spojeni dat ze 2 ruznych XML dokumentu nebo ze dvou logicky oddelenych casti XML dokumentu (viz. priklad 6 ze cviceni ),

:)