print "*******************"
print "Rekurze - faktorial"
print "*******************"

def faktorial(x):
    if(x == 0):
        return 1
    return faktorial(x-1) * x

while 1:
    cislo = raw_input("Zadej cele nezaporne cislo: ")
    try:
        cislo = int(cislo)
        if(cislo >= 0):
            break
        else:
            print "Tohle neni nezaporne cislo!"
    except:
        print "Tohle neni cislo!"
            
    

print "Faktorial cisla "+str(cislo)+" je: "+str(faktorial(cislo))
