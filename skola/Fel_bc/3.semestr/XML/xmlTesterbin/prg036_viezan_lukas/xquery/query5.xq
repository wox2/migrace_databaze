let $zboziA :=
	for $vyrobekA in fn:doc("../vyrobky.xml")//vyrobek
		for $sleva in fn:doc("../slevy.xml")//vyrobek
		where $sleva/@id = $vyrobekA /@id
			return
				element vyrobek {
					attribute id { fn:data( $vyrobekA/@id ) },
					if ( count( $sleva ) > 0 )
					then attribute cena { fn:data( $sleva/@cena ) }
					else attribute cena { fn:data( $vyrobekA/@cena ) }
				}

let $zboziB :=
	for $vyrobekB in fn:doc("../Novezbozi.xml")//vyrobek
		for $slevaB in fn:doc("../slevy.xml")//vyrobek
		where $slevaB/@id = $vyrobekB/@id
			return
				element vyrobek {
					attribute id { fn:data( $vyrobekB/@id ) },
					if ( count( $slevaB ) > 0 )
					then attribute cena { fn:data( $slevaB/@cena ) }
					else attribute cena { fn:data( $vyrobekB/@cena ) }
				}

return
<vsechny_vyrobky>{ 
	$zboziA union $zboziB 
}</vsechny_vyrobky>

(: Vybere výrobky na ktere lze uplatnit slevu ze souboru vyrobky.xml a Novezbozi.xml a jejich vypis sjednoti a vrati :)

(:

1 dotaz provádějící integraci heterogenních dat ze 2 různých XML dokumentů (viz. příklad 10 ze cvičení)

:)