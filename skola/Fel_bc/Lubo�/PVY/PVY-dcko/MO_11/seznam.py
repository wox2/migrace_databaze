#!/usr/bin/python

import re
rv = re.compile(
    r"(?P<jmeno>[-a-zA-Z]+):"
    r"(?P<prijmeni>[-a-zA-Z]+):"
    r"(?P<adresa>[-a-zA-Z]+):"
    r"(?P<zamestnani>[-a-zA-Z]+):"
    r"(?P<telefon>(\d\d\d\d-)?\d\d\d\d\d\d\d\d\d)"
    )

sb = open("seznam.txt","r")

for radek in sb.readlines():
    vysledek=rv.search(radek)
    if vysledek:
        telefon=vysledek.group("telefon")
        jmeno=vysledek.group("jmeno")
        prijmeni=vysledek.group("prijmeni")
        adresa=vysledek.group("adresa")
	zamestnani=vysledek.group("zamestnani")
        print jmeno,prijmeni+": "+telefon
    else:
        print "spatny vyraz:",radek

print ""
print "Vyhledavani"
dotaz = raw_input("Zadejte telefoni cislo: ")
while dotaz=="":
    dotaz = raw_input("Zadejte telefoni cislo: ")
    
sb.close()
sb = open("seznam.txt","r")

for radek in sb.readlines():
    vysledek=rv.search(radek)
    if vysledek:
        telefon=vysledek.group("telefon")
        jmeno=vysledek.group("jmeno")
        prijmeni=vysledek.group("prijmeni")
        adresa=vysledek.group("adresa")
	zamestnani=vysledek.group("zamestnani")
    	if (telefon==dotaz):
	    print jmeno,prijmeni,zamestnani,adresa,telefon
#	else:
	    #print "Nic nenalezeno:"
    else:
        print "Chyba syntaxe:",radek
    
sb.close()
