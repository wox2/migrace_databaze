print "Quick sort"

def quick(pole):
    if(len(pole) <= 1):
        return pole
    levaCast = []
    pravaCast = []
    
    pivot = 0
    for j in pole:
        pivot += j
    pivot /= len(pole)
    print "---------------------------"
    print "Pivot: ",pivot
        

    for i in pole:
        if(i <= pivot):
            print i, "mensi nebo rovno jak", pivot
            levaCast.append(i)
        else:
            print i, "vetsi jak", pivot
            pravaCast.append(i)
    print "Leva cast: ",levaCast
    print "Prava cast: ",pravaCast
    return quick(levaCast)+quick(pravaCast)


pole = [7, 8, 1, 30, 28, 4, 23, 6, 5, 19, 2, 3]

print "******************************"
print pole
print "******************************"
print ""

novePole = quick(pole)

print ""
print "******************************"
print novePole
print "******************************"
