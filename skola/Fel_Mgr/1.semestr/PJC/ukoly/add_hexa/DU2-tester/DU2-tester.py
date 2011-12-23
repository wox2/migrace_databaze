'''
Created on Nov 6, 2011

@author: vikturek
'''

import os
import sys
import shutil
import random

def generateBytes(fil):
    for i in range(1, rand.randint(2,1024)):
        fil.write(" %02x" % rand.randint(0,255))
    fil.write("\n")

if __name__ == '__main__':
    score1 = 0.0
    testsCount1 = 0.0
    score2 = 0.0
    testsCount2 = 0.0
    score3 = 0.0
    testsCount3 = 0
    allowTest = 0 #0 - vsechny testy. 1, 2, 3 - test, ktery se ma provest
    
    print "\nTester pro domaci ukol 2 predmetu PJC v zimnim semestru 2011\n"
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
        if not (sys.argv[3] == str(1) or sys.argv[3] == str(2) or sys.argv[3] == str(3)):
            print "Chyba: Ctvrty argument musi byt 1, 2 nebo 3."
            sys.exit(1)
        else:
            allowTest = int(sys.argv[3])
    else:
        print "Tester vyzaduje jako prvni argument referencni reseni a jako druhy nazev zdrojoveho kodu k testovani. \
                Treti parametr je nepovinny a pomoci nej lze vybrat sadu testu (moznosti 1, 2, 3)."
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
    
    if allowTest == 1 or allowTest == 0:
        tests = os.listdir("./inputs/Test1")
        tests.sort()
        print "\nTestovaci sada 1 - parsovani vstupu"
        testsCount1 = len(tests)
        for i in range(0, len(tests)):
            refOutput = "outputs/output1.%02dref" % i
            testOutput = "outputs/output1.%02dtest" % i
            testInput = "inputs/Test1/" + tests[i] 
            
            sys.stdout.write("\n\tTest 1.%02d" % i + " ... " )
            
            input = file(testInput)
            if os.path.exists(refOutput):
                os.remove(refOutput)
            fil = file(refOutput, "w")
            fil.write("Vstup:\n")
            fil.write(input.read())
            fil.write("\n\nVystup:\n")
            fil.close()
            input.close()
            input = file(testInput)
            if os.path.exists(testOutput):
                os.remove(testOutput)
            fil = file(testOutput, "w")
            fil.write("Vstup:\n")
            fil.write(input.read())
            fil.write("\n\nVystup:\n")
            fil.close()
            input.close()
            
            os.system("./" + sys.argv[1] + " " + testInput + " >> " + refOutput)
            os.system("./testApp " + testInput + " >> " + testOutput)
            if os.system("diff " + refOutput + " " + testOutput + " > /dev/null") == 0:
                sys.stdout.write("OK\n")
                score1 += 1
            else:
                sys.stdout.write("Chyba!\n")
                print "\n\tVystupy " + refOutput + " a " + testOutput + " nejsou stejne!"
    
    if allowTest == 0 or allowTest == 2:
        tests = os.listdir("./inputs/Test2")
        tests.sort() 
        print "\nTestovaci sada 2 - kontrola vypoctu"
        testsCount2 = len(tests)
        for i in range(0, len(tests)):
            refOutput = "outputs/output2.%02dref" % i
            testOutput = "outputs/output2.%02dtest" % i
            testInput = "inputs/Test2/" + tests[i] 
            
            sys.stdout.write("\n\tTest 2.%02d" % i + " ... " )
            
            input = file(testInput)
            if os.path.exists(refOutput):
                os.remove(refOutput)
            fil = file(refOutput, "w")
            fil.write("Vstup:\n")
            fil.write(input.read())
            fil.write("\n\nVystup:\n")
            fil.close()
            input.close()
            input = file(testInput)
            if os.path.exists(testOutput):
                os.remove(testOutput)
            fil = file(testOutput, "w")
            fil.write("Vstup:\n")
            fil.write(input.read())
            fil.write("\n\nVystup:\n")
            fil.close()
            input.close()
            
            os.system("./" + sys.argv[1] + " " + testInput + " >> " + refOutput)
            os.system("./testApp " + testInput + " >> " + testOutput)
            if os.system("diff " + refOutput + " " + testOutput + " > /dev/null") == 0:
                sys.stdout.write("OK\n")
                score2 += 1
            else:
                sys.stdout.write("Chyba!\n")
                print "\n\tVystupy " + refOutput + " a " + testOutput + " nejsou stejne!"
     
    if allowTest == 0 or allowTest == 3:
        print "\nTestovaci sada 3 - nahodne generovana vstupni data"
        rand = random
        testsCount3 = 30
        for i in range(0, 30):
            refOutput = "outputs/output3.%02dref" % i
            testOutput = "outputs/output3.%02dtest" % i
            testInput = "inputs/Test3/test3.%02d" % i
            variables = []
            if os.path.exists(testInput):
                os.remove(testInput)
            fil = file(testInput, "w")
            countOfVariables = rand.randint(2, 40)
            for j in range(0, countOfVariables):
                variable = rand.randint(10000, 99999)
                variables.append(variable)
                fil.write("let %d 0x" % variable)
                generateBytes(fil)
            fil.write("\nbegin\n")
            for j in range(1, 100):
                fil.write("add %d" % variables[rand.randint(0,len(variables) - 1)])
                fil.write(" %d\n" % variables[rand.randint(0, len(variables) - 1)])
            resultVariable = variables[0]
            for j in variables[1:]:
                fil.write("add %d" % resultVariable)
                fil.write(" %d\n" % j)
            fil.write("print %d\nexit\n" % resultVariable)
            fil.close()
            
            sys.stdout.write("\n\tTest 3.%02d" % i + " ... " )
            
            os.system("./" + sys.argv[1] + " " + testInput + " > " + refOutput)
            os.system("./testApp " + testInput + " > " + testOutput)
            if os.system("diff " + refOutput + " " + testOutput + " > /dev/null") == 0:
                sys.stdout.write("OK\n")
                score3 += 1
            else:
                sys.stdout.write("Chyba!\n")
                print "\n\tVystupy " + refOutput + " a " + testOutput + " nejsou stejne!"
    
    print "Celkove vysledky:"
    if allowTest == 1 or allowTest == 0:
        print "\tTestovaci sada 1: " + str((score1/testsCount1) * 100) + "%"
    if allowTest == 0 or allowTest == 2:
        print "\tTestovaci sada 2: " + str((score2/testsCount2) * 100) + "%"
    if allowTest == 0 or allowTest == 3:
        print "\tTestovaci sada 3: " + str((score3/testsCount3) * 100) + "%"
    pass
