print "Bubble sort"

def bubble(pole):
    delkaPole = len(pole)
    for i in range(delkaPole):
        print ""
        print i, ". pruchod"
        vymena = False
        for j in range(delkaPole - i):
            print "--", pole,
            if(j != delkaPole - 1 and pole[j] > pole[j+1]):
                pomocna = pole[j]
                pole[j] = pole[j+1]
                pole[j+1] = pomocna
                print " ==> ", pole
                vymena = True
            else:
                print ""

        print "==", pole
        if(not vymena):
            print "V tomto pruchodu nedoslo k ani jednomu prohozeni cisel"
            print "=> Cisla jsou serazena a cyklus se muze ukoncit"
            break
    return pole
            
                
        



pole = [5, 8, 3, 1, 23, 6, 28, 19, 2]

print "******************************"
print pole
print "******************************"
print ""

novePole = bubble(pole)

print ""
print "******************************"
print novePole
print "******************************"


