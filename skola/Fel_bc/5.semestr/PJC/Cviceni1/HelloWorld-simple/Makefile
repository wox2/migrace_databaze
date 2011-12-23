
all: Hello.m Hello.s Hello

.PHONY: clean


Hello: Hello.o
	g++ -o Hello Hello.cpp

Hello.o: Hello.cpp
	g++ -c Hello.cpp

Hello.s: Hello.cpp
	g++ -S Hello.cpp

Hello.m: Hello.cpp
	g++ -E Hello.cpp > Hello.m

clean:
	rm Hello.o
	rm Hello.s
	rm Hello.m
	rm Hello

