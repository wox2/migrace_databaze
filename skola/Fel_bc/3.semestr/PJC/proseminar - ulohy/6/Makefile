CXX=g++
LD=g++
CFLAGS=-Wall -pedantic -Wno-long-long

all: expr.exe

expr.exe: main.o Expr.o Tree.o
	$(LD) main.o Expr.o Tree.o -lgdi32 -mconsole -o expr.exe
	
Tree.o: Tree.cpp Tree.h
	$(CXX) $(CFLAGS) -c Tree.cpp
	
Expr.o: Expr.cpp Tree.h Expr.h
	$(CXX) $(CFLAGS) -c Expr.cpp
	
main.o: main.cpp Tree.h Expr.h
	$(CXX) $(CFLAGS) -c main.cpp
	
clean:
	rm -rf Expr.o	Tree.o main.o expr.exe
	