print "**********************************************"
print "Rekurze - vypsani souboru a slozek (ve stromu)"
print "**********************************************"

import os


def projdi_adresar(cesta, vnoreni):
    pole = os.listdir(cesta)
    vnoreni += 1
    for i in range(len(pole)):
        print "|"
        for j in range(vnoreni):
            print "-",
        try:
            # Jedna se o soubor
            file(cesta+pole[i])
            print pole[i]
        except:
            # Jedna se o slozku
            print pole[i] + " [slozka]"
            projdi_adresar(cesta+pole[i]+'/', vnoreni)



projdi_adresar('/', 0)

#Zkuste zavolat funkci 'projdi_adresar' s parametrem '/' ;-) Treba se program za par dni zastavi :))
#projdi_adresar('/', 0)

