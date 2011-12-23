xquery version "1.0";

for $hrac in fn:doc("../data.xml")//cislo
where some $faul in $cislo/faul satisfies
contains ($adresa/faul_s_cervenou_kartou)
return $cislo

(: Vypise vsechny zakazniky jejichz trvale bydliste je v Praze :)

(:

jedno z "every ... satisfies" nebo "some ... satisfies",

:)