(: Vypis seznamu hracu s jejich prezdivkami (pripadne poznamce "Bez prezdivky") a jejich uloze v teamu :)
(:
if then else
1 dotaz musí mít výstup do formátu XHTML
order by
:)

<html>
	<title>Tabulka hracu</title>
	<body>
		<table>
			<tr>
				<th>ID</th>
				<th>Jmeno</th>
				<th>Prezdivka</th>
				<th>Pozice</th>
			</tr>
			{
				for $hrac in fn:doc("../data.xml")//cislo
				order by $hrac/@id
				return
					<tr>
						<td>{ fn:data( $hrac/@id ) }</td>
						<td>{ fn:data( $hrac/jmeno ) }</td>
						{if ( $hrac/prezdivka )
						then
            <td>{ fn:data( $hrac/prezdivka ) }</td>
						else
						<td> Bez prezdivky </td>
						}
						{
							if ( $hrac/../kapitan )
							then
								<td>
                  kapitan
                </td>
							else
								<td>
									hrac
								</td>
            }
          </tr>  		
			}
		</table>
	</body>
</html>

