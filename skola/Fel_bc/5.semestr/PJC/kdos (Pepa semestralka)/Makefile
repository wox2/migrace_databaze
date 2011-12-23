# Tohle je komentar

# Nazev vysledne aplikace
TARGET = Neogenetics_awesome_IDOS
PROJECT = KDOS.h KDOS.cpp Logger.h Logger.cpp OutputGenerator.h OutputGenerator.cpp RailWay.h RailWay.cpp TrackParser.h TrakParser.cpp libs/tinystr.h libs/tinystr.cpp libs/tinyxml.h libs/tinyxml.cpp libs/tinyxmlerror.cpp libs/tinyxmlparser.cpp

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
$(TARGET): KDOS.o Logger.o OutputGenerator.o RailWay.o TrackParser.o tinystr.o tinyxml.o tinyxmlerror.o tinyxmlparser.o
	$(CC) -o $(TARGET) $(CFLAGS) $(DEFINE) KDOS.o Logger.o OutputGenerator.o RailWay.o TrackParser.o tinystr.o tinyxml.o tinyxmlerror.o tinyxmlparser.o
# Univerzalni pravidlo pro cile s priponou *.o
%.o: %.cpp
	$(CC) -c $(CFLAGS) $(DEFINE) $<

# Nasleduji pravidla bez tela, ktere se ridi univerzalnim pravidlem
KDOS.o: KDOS.h KDOS.cpp

Logger.o: Logger.h Logger.cpp

OutputGenerator.o: OutputGenerator.h OutputGenerator.cpp

RailWay.o: RailWay.h RailWay.cpp

TrackParser.o: TrackParser.h TrackParser.cpp

tinystr.o: libs/tinystr.h libs/tinystr.cpp

tinyxml.o: libs/tinyxml.h libs/tinyxml.cpp 

tinyxmlerror.o: libs/tinyxmlerror.cpp

tinyxmlparser.o: libs/tinyxmlparser.cpp

# Pravidlo, ktere vrati stav adresare do stavu pred kompilaci. 
# Vola se explicitne "make clean".
clean:	
	rm $(TARGET)
	rm main.o
	rm KDOS.o
	rm Logger.o
	rm OutputGenerator.o
	rm RailWay.o
	rm TrackParser.o
	rm tinystr.o
	rm tinyxml.o
	rm tinyxmlerror.o
	rm tinyxmlparser.o


run :
	./$(TARGET)
tar:
	tar cfv XPJC_SEMESTRALNI_PRACE_lobotjos4.tar.gz $(PROJECT) 
