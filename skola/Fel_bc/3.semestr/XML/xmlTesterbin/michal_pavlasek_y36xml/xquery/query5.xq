(: vsechny filmy kratsi nez 90 minut :)
<ul> {
for $x in doc("../data.xml")/tv_program/porad
let $nazev := $x/nazev
where $x/delka < 90
return <li>{data($nazev)}</li>
}
</ul> 