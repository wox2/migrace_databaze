(: vsechny porady ve kterych hraje werich :)
<ul> {
for $x in doc("../data.xml")/tv_program/porad
let $nazev := $x/nazev
where $x/herci/@ref = 'werich'
return <li>{data($nazev)}</li>
}
</ul> 