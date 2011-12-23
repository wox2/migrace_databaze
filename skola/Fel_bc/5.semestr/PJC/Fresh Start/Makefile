# Tohle je komentar

# Nazev vysledne aplikace
TARGET = CESTA_TAM_A_ZASE_ZPATKY
PROJECT = main.cpp AbstractPriorityQueue.h AbstractPriorityQueue.cpp Algorithm.h Algorithm.cpp City.cpp City.h ComunicationNetwork.h ComunicationNetwork.cpp Condensator.h Condensator.cpp HighPrecedessorsPriorityQueue.h HighPrecedessorsPriorityQueue.cpp NetworkCreator.cpp NetworkCreator.h Road.h Road.cpp StrongComponent.cpp StrongComponent.h Makefile

# Nazev kompileru
CC = g++

# Dodatecne parametry pro kompilator
CFLAGS = -Wall -Wno-long-long -pedantic

# Dodatecne definice pro kompilator
DEFINE =

# Pravidlo, ktere se vola defaultne pokud se zavola samotny prikaz make
all: $(TARGET)

# Oznaceni cilu, ktere nejsou vystupnim souborem
.PHONY: clean

# Pravidlo pro linkovani vysledne aplikace
$(TARGET): main.o AbstractPriorityQueue.o Algorithm.o City.o ComunicationNetwork.o  Condensator.o HighPrecedessorsPriorityQueue.o NetworkCreator.o Road.o StrongComponent.o
	$(CC) -o $(TARGET) $(CFLAGS) $(DEFINE) main.o AbstractPriorityQueue.o Algorithm.o City.o ComunicationNetwork.o  Condensator.o HighPrecedessorsPriorityQueue.o NetworkCreator.o Road.o StrongComponent.o
# Univerzalni pravidlo pro cile s priponou *.o
%.o: %.cpp
	$(CC) -c $(CFLAGS) $(DEFINE) $<

# Nasleduji pravidla bez tela, ktere se ridi univerzalnim pravidlem
main.o: main.cpp

AbstractPriorityQueue.o: AbstractPriorityQueue.h AbstractPriorityQueue.cpp

Algorithm.o: Algorithm.h Algorithm.cpp

City.o: City.h City.cpp

ComunicationNetwork.o: ComunicationNetwork.h ComunicationNetwork.cpp

Condensator.o: Condensator.h Condensator.cpp

HighPrecedessorsPriorityQueue.o: HighPrecedessorsPriorityQueue.h HighPrecedessorsPriorityQueue.cpp

NetworkCreator.o: NetworkCreator.h NetworkCreator.cpp

Road.o: Road.h Road.cpp

StrongComponent.o: StrongComponent.h StrongComponent.cpp

# Pravidlo, ktere vrati stav adresare do stavu pred kompilaci. 
# Vola se explicitne "make clean".
clean:
#	if [ -e $(TARGET) ]; then rm $(TARGET); fi
#	if [ -e $(TARGET) ]; then rm $(TARGET); fi
	if [ -e main.o ]; then rm main.o; fi
	if [ -e AbstractPriorityQueue.o ]; then rm AbstractPriorityQueue.o; fi
	if [ -e Algorithm.o ]; then rm Algorithm.o; fi
	if [ -e City.o ]; then rm City.o; fi
	if [ -e ComunicationNetwork.o ]; then rm ComunicationNetwork.o; fi
	if [ -e Condensator.o ]; then rm Condensator.o; fi
	if [ -e HighPrecedessorsPriorityQueue.o ]; then rm HighPrecedessorsPriorityQueue.o; fi
	if [ -e NetworkCreator.o ]; then rm NetworkCreator.o; fi
	if [ -e Road.o ]; then rm Road.o; fi
	if [ -e StrongComponent.o ]; then rm StrongComponent.o; fi
	
#	rm $(TARGET)
#	rm Algorithm.o
#	rm AbstractPriorityQueue.o
#	rm City.o
#	rm ComunicationNetwork.o
#	rm Condensator.o
#	rm HighPrecedessorsPriorityQueue.o
#	rm NetworkCreator.o
#	rm Road.o
#	rm StrongComponent.o


run :
	./$(TARGET)
tar:
	tar cfv XPJC_SEMESTRALNI_PRACE_lukesma4.tar.gz $(PROJECT) 
