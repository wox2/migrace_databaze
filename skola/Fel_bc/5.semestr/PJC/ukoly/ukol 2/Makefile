# Tohle je komentar

# Nazev vysledne aplikace
TARGET = ukol2
PROJECT = main.cpp SItem.cpp SItem.h SHandle.h SHandle.cpp SList.h SList.cpp Makefile

# Nazev kompileru
CC = g++

# Dodatecne parametry pro kompilator
CFLAGS =

# Dodatecne definice pro kompilator
DEFINE =

# Pravidlo, ktere se vola defaultne pokud se zavola samotny prikaz make
all: $(TARGET)

# Oznaceni cilu, ktere nejsou vystupnim souborem
.PHONY: clean

# Pravidlo pro linkovani vysledne aplikace
$(TARGET): main.o SItem.o SHandle.o SList.o
	$(CC) -o $(TARGET) $(CFLAGS) $(DEFINE) main.o SItem.o SHandle.o SList.o

# Univerzalni pravidlo pro cile s priponou *.o
%.o: %.cpp
	$(CC) -c $(CFLAGS) $(DEFINE) $<

# Nasleduji pravidla bez tela, ktere se ridi univerzalnim pravidlem
main.o: main.cpp

SItem.o: SItem.h SItem.cpp

SHandle.o: SHandle.h SHandle.cpp

SList.o: SList.h SList.cpp

# Pravidlo, ktere vrati stav adresare do stavu pred kompilaci. 
# Vola se explicitne "make clean".
clean:
#	if [ -e $(TARGET) ]; then rm $(TARGET); fi
#	if [ -e main.o ]; then rm main.o; fi
	rm $(TARGET)
	rm main.o
	rm SItem.o
	rm SHandle.o
	rm SList.o

run :
	./$(TARGET)
tar:
	tar cfv XPJC_02_lukesma4.tar.gz $(PROJECT) 