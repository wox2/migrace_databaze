CPP=g++
CC=gcc
LD=g++
CFLAGS=-Wall -pedantic -Wno-long-long

all: a.out

a.out: main.o sqr.o
	$(LD) -o a.out main.o sqr.o
	strip --strip-unneeded a.out

main.o: main.cpp sqr.h
	$(CPP) $(CFLAGS) -c -o main.o main.cpp

sqr.o: sqr.c sqr.h
	$(CC) $(CFLAGS) -c -o sqr.o sqr.c

clean:
	rm -f main.o sqr.o a.out