(: Spoji seznam nafaulujicich politiku a sportovcu, kteri nevoli KSC :)
(:1 dotaz provádějící integraci heterogenních dat ze 2 různých XML dokumentů (viz. příklad 10 ze cvičení):)

let $seznam-politiku :=
	for $jmeno in fn:doc("../data.xml")//jmeno
	where not($jmeno/../faul)
	return element politik {fn:data($jmeno)}
	
let $seznam-sportovcu :=
	for $jmeno in fn:doc("../sportovci.xml")//jmeno
	where not($jmeno/../voli="KSC")    
			return
				$jmeno/..
				
return
<celebrity>{ 
	$seznam-politiku union $seznam-sportovcu 
}</celebrity>

