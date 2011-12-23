def shellSort(pole):
    mezera = len(pole) / 2
    while mezera > 0:
        # Zde se provadi insert sort
        for i in range(mezera, len(pole)):
            hodnota_i = pole[i]
            j = i
            while j >= mezera and pole[j - mezera] > hodnota_i:
                pole[j] = pole[j - mezera]
                j -= mezera
            pole[j] = hodnota_i
        mezera /= 2
    return pole


pole = [7, 8, 1, 30, 28, 4, 23, 6, 5, 19, 2, 3]

print "******************************"
print pole
print "******************************"
print ""

novePole = shellSort(pole)

print ""
print "******************************"
print novePole
print "******************************"
