'''
Created on Nov 21, 2011

@author: vikturek
'''

import sys
import os
import random

def generateTest2(testInput):
    if os.path.exists(testInput):
        os.remove(testInput)
    rand = random
    testFile = file(testInput, "w")
    testFile.write("INT\n")
    for i in range(0, 10000):
        testFile.write("ADD ")
        testFile.write(str(rand.randint(0, 1000)) + "\n")
    testFile.close()

if __name__ == '__main__':
    
    score = 0.0
    valgrind = False
    
    print "\nTester pro domaci ukol 3 predmetu PJC v zimnim semestru 2011\n"
    if len(sys.argv) == 3:
        if os.path.exists(sys.argv[1]):
            open(sys.argv[1])
        else:
            print "Chyba: Nemohu najit zadane referencni reseni!"
            sys.exit(1)
        if os.path.exists(sys.argv[2]):
            open(sys.argv[2])
        else:
            print "Chyba: Nemohu najit zadany zdrojovy soubor!"
            sys.exit(1)
    elif len(sys.argv) == 4:
        if sys.argv[3] != "-valgrind":
            print "Chyba: Poslednim argumentem muze byt pouze text \'-valgrind!\'"
            sys.exit(1)
        else:
            valgrind = True
    else:
        print "Tester vyzaduje jako prvni argument referencni reseni a jako druhy nazev zdrojoveho kodu k testovani. \
Tretim nepovinnym argumentem je prikaz -valgrind, ktery otestuje kod i na memory leaky \
(- funguje pouze na linuxu s nainstalovanym valgrindem)."
        sys.exit(0)
    
    printOut = "\tKompiluji zadany zdrojovy soubor ... "
    retVal = os.system("g++ -o testApp -Wall -pedantic -Werror -pedantic-errors " + sys.argv[2] + " 2> compileLog.txt" )
    if retVal != 0:
        printOut += "Chyba!"
        print printOut
        print "\n\tZdrojovy kod se nepodarilo prelozit."
        print "\tKompletni log kompilace je ulozen v souboru compileLog.txt"
        sys.exit()
    printOut += "OK"
    print printOut
    
    sys.stdout.write("\n\tTest 1 - vstup podle ukazky ... ")
    testInput = "inputs/test1"
    refOutput = "outputs/output1ref"
    testOutput = "outputs/output1test"  
    os.system("./" + sys.argv[1] + " " + testInput + " > " + refOutput)
    os.system("./testApp " + testInput + " > " + testOutput)
    if os.system("diff " + refOutput + " " + testOutput + " > /dev/null") == 0:
        sys.stdout.write("OK\n")
        score += 1
    else:
        sys.stdout.write("Chyba!\n")
        print "\n\tVystupy " + refOutput + " a " + testOutput + " nejsou stejne!"
    
    sys.stdout.write("\n\tTest 2 - test nahodnymi hodnotami ... ")
    testInput = "inputs/test2"
    generateTest2(testInput)
    refOutput = "outputs/output2ref"
    testOutput = "outputs/output2test"
    os.system("./" + sys.argv[1] + " " + testInput + " > " + refOutput)
    os.system("./testApp " + testInput + " > " + testOutput)
    if os.system("diff " + refOutput + " " + testOutput + " > /dev/null") == 0:
        sys.stdout.write("OK\n")
        score += 1
    else:
        sys.stdout.write("Chyba!\n")
        print "\n\tVystupy " + refOutput + " a " + testOutput + " nejsou stejne!"
    
    if valgrind:
        sys.stdout.write("\n\tTest 3 - test prace s pameti ... ")
        testInput = "inputs/test2"
        testOutput = "outputs/output3test"
        os.system("valgrind ./testApp " + testInput + " 2> " + testOutput + " > /dev/null")
        result = file(testOutput, "r")
        resultText = result.read()
        if resultText.count("in use at exit: 0 bytes in 0 blocks") == 0:
            print "Chyba!"
            print "\n\tAplikace valgrind vratila nenulovy pocet chyb. Log valgrindu je v souboru " + testOutput + ".\n"
        else:
            print "OK\n"
            score += 1
    
    sys.stdout.write("\nDosazeno " + str(score/3*100) + "%.\n\n")
    if not valgrind:
        print "Pro maximalni hodnoceni je potreba spustit test 3 pomoci posledniho parametru \'-valgrind\'.\
Pokud netestujete na linuxu s nainstalovanym valgrindem, tak test 3 nespoustejte."
