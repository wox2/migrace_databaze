(: nazvy vsech poradu serazenych podle abecedy :)
<ul> {
for $x in doc("../data.xml")/tv_program/porad/nazev
order by $x
return <li>{data($x)}</li>
}
</ul> 