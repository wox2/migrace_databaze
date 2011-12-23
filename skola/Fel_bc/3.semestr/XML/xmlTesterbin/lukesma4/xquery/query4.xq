(: Vypise vsechny hrace, kteri dostali zlutou kartu :)
(:jedno z "every ... satisfies" nebo "some ... satisfies",:)

for $cislo in fn:doc("../data.xml")//cislo
where some $faul in $cislo/faul satisfies
$cislo/faul/faul_s_zlutou_kartou
return element jmeno{$cislo/jmeno}

