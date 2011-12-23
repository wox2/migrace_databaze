# Tohle je komentar

# Nazev vysledne aplikace
TARGET = BasicExample

# Nazev zdrojoveho kodu
SOURCE = BasicExample.cpp

# Nazev kompileru
CC = g++

# Dodatecne parametry pro kompilator
CFLAGS = -I/opt/sfw/include/boost_1_44_0 -L/opt/sfw/lib/boost_1_44_0/ -lboost_test_exec_monitor

# Dodatecne definice pro kompilator
DEFINE = 

# Pravidlo, ktere se vola defaultne pokud se zavola samotny prikaz make
all: $(TARGET)

# Oznaceni cilu, ktere nejsou vystupnim souborem
.PHONY: clean

# Pravidlo pro prelozeni aplikace
$(TARGET): $(SOURCE)
	$(CC) -o $(TARGET) $(SOURCE) $(CFLAGS) $(DEFINE)

# Peavidlo pro prelozeni a spusteni aplikace
run: $(TARGET)
	./$(TARGET)

# Pravidlo, ktere vrati stav adresare do stavu pred kompilaci. 
# Vola se explicitne "make clean".
clean:
	rm $(TARGET)


