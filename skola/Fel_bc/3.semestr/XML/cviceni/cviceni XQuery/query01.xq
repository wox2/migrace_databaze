
<predmety>
{
for $predmet in fn:doc("predmety.xml")//predmet
where $predmet/katedra = "13136"
order by $predmet/nazev
return
<nazev-predmetu>{fn:data($predmet/nazev)}</nazev-predmetu>
}
</predmety>