# Tohle je komentar

# Nazev vysledne aplikace
TARGET = hello

# Nazev kompileru
CC = g++-3

# Dodatecne parametry pro kompilator
CFLAGS = 

# Dodatecne definice pro kompilator
DEFINE = 

# Pravidlo, ktere se vola defaultne pokud se zavola samotny prikaz make
all: $(TARGET)

# Oznaceni cilu, ktere nejsou vystupnim souborem
.PHONY: clean

# Pravidlo pro linkovani vysledne aplikace
$(TARGET): main.o printHello.o printWorld.o
	$(CC) -o $(TARGET) $(CFLAGS) $(DEFINE) main.o printHello.o printWorld.o

# Univerzalni pravidlo pro cile s priponou *.o
%.o: %.cpp
	$(CC) -c $(CFLAGS) $(DEFINE) $<

# Nasleduji pravidla bez tela, ktere se ridi univerzalnim pravidlem
main.o: main.cpp

printHello.o: printHello.cpp printHello.h

printWorld.o: printWorld.cpp printWorld.h

# Pravidlo, ktere vrati stav adresare do stavu pred kompilaci. 
# Vola se explicitne "make clean".
clean:
	rm -rf *.o
	rm $(TARGET)


