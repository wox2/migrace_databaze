all: usage

SOURCE = DU2.cpp
TESTER = DU2-tester.py

.PHONY: clean usage

usage:
	cat usage

test_solaris:
	chmod +x refApp.solaris
	python $(TESTER) refApp.solaris $(SOURCE)

test_linux_32:
	chmod +x refApp.linux_32
	python $(TESTER) refApp.linux_32 $(SOURCE)

test_linux_64:
	chmod +x refApp.linux_64
	python $(TESTER) refApp.linux_64 $(SOURCE)

test_macos:
	chmod +x refApp.macos
	python $(TESTER) refApp.macos $(SOURCE)

test_win:
	chmod +x refApp.exe
	python $(TESTER) refApp.exe $(SOURCE)


clean:
	rm -rf outputs/*
	rm -rf testApp
	rm -rf compileLog.txt


