print "Selection sort"

def selection(pole):
    for i in range(len(pole)):

        #Najit nejmensi prvek z casti neserazeneho pole
        imin = i
        for j in range(i+1, len(pole)):
            if(pole[j] < pole[imin]):
                imin = j

        #A tento prvek zamenit za prvni prvek neserazene casti pole
        if(imin != i):
            pomocna = pole[imin]
            pole[imin] = pole[i]
            pole[i] = pomocna
        
    return pole


pole = [1, 8, 3, 5, 23, 6, 28, 19, 2]

print "******************************"
print pole
print "******************************"
print ""

novePole = selection(pole)

print ""
print "******************************"
print novePole
print "******************************"
