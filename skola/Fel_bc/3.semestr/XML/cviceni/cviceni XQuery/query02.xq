<predmety>
{
for ($predmet/(garant|autor)) in fn:doc("predmety.xml")//predmet
let $autor = predmet/(garant|autor)
let $pocet-vyucovanych :=fn:count($predmet/)
if (fn:count($predmet/autor) >2) then return
<vyucujici>
{$pocet-vyucujicich}
</vyucujici>
}
</predmety>
