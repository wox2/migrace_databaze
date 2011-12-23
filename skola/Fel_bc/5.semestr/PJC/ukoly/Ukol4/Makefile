all: bltree

.PHONY: run test test_full clean

bltree: main.cpp bltree.h
	g++ -g -Wall -Werror -pedantic -pedantic-errors -o bltree main.cpp

run: bltree
	./bltree

test: 
	python DU4Tester.pyc

full_test:
	python DU4Tester.pyc valgrind

clean:
	rm bltree

