xquery version "1.0";

<html>
	<title>Tabulka zĂˇkaznĂ­kĹŻ</title>
	<body>
		<table>
			<tr>
				<th>ID</th>
				<th>JmĂ©no</th>
				<th>Email</th>
				<th>Telefon</th>
				<th>Adresa</th>
				<th>Typ adresy</th>
			</tr>
			{
				for $zakaznik in fn:doc("../data.xml")//zakaznik
				order by $zakaznik/jmeno
				return
					<tr>
						<td>{ fn:data( $zakaznik/@id ) }</td>
						<td>{ fn:data( $zakaznik/jmeno ) }</td>
						<td>{ fn:data( $zakaznik/email ) }</td>
						<td>{ fn:data( $zakaznik/telefon ) }</td>
						{
							if ( $zakaznik/adresa_dorucovaci )
							then
								<td>{ 
									fn:data( $zakaznik/adresa_dorucovaci/ulice  ), text {", "},
									fn:data( $zakaznik/adresa_dorucovaci/popisne_cislo  ), text {", "},
									fn:data( $zakaznik/adresa_dorucovaci/mesto  ), text {", "},
									fn:data( $zakaznik/adresa_dorucovaci/psc ) 
								}</td>
							
							else
								<td>{ 
									fn:data( $zakaznik/adresa_bydliste/ulice ), text {", "},
									fn:data( $zakaznik/adresa_bydliste/popisne_cislo  ), text {", "}, 
									fn:data( $zakaznik/adresa_bydliste/mesto ), text {", "},
									fn:data( $zakaznik/adresa_bydliste/psc ) 
								}</td>
						}
						
						<td>{
							if ( $zakaznik/adresa_dorucovaci )
							then
								text {" Dorucovaci "}	
							else
								text {" Bydliste "}	
						}</td>
					</tr>
			}
		</table>
	</body>
</html>

(: Výpis zákazníků, včetně poznámky, zda se jedná o adresu bydliště nebo doručovací, výpis je řazen abecedně dle jména zákazníka :)
(:

if then else
1 dotaz musí mít výstup do formátu XHTML
order by

:)