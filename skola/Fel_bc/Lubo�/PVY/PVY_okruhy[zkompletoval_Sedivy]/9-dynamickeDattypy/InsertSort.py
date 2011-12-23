print "insert sort"

def insertsort(pole):
    for i in range(1, len(pole)):
        print ""
        print i,". pruchod, ",i+1,". prvek pole"        
        hodnota_i = pole[i]
        j = i
        while j > 0 and pole[j - 1] > hodnota_i:
            print "--", pole,
            pole[j] = pole[j - 1]
            print "=>", pole
            j = j - 1
        pole[j] = hodnota_i
        print "==", pole
    return pole


pole = [8, 1, 30, 28, 23, 6, 3, 19, 2]

print "******************************"
print pole
print "******************************"
print ""

novePole = insertsort(pole)

print ""
print "******************************"
print novePole
print "******************************"
