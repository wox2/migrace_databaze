
<html>
	<title>Seznam nejlevnějších výrobků na každé objednávce</title>
	<body>
		<ul>
			{
				for $zbozi in fn:doc("../data.xml")//zbozi
					for $vyrobek in $zbozi//vyrobek
					where $vyrobek/@cena = min( $zbozi//vyrobek/@cena )
					return
						element li { fn:data( $vyrobek ), fn:data( $vyrobek/@ks ), fn:data( $vyrobek/@cena ) }
			}
		</ul>
	</body>
</html>

(: Seznam nejlevnějších výrobků na každé objednávce :)
(:

počítané konstruktory
jedno z "min", "max", "avg" nebo "sum" v podmínce za where

:)