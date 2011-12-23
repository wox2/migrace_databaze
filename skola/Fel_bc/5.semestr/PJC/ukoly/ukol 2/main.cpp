/* 
 * File:   main.cpp
 * Author: woxie
 *
 * Created on 23. říjen 2010, 15:17
 */

#include <cstdlib>
#include "SList.h"
#include "SItem.h"
#include <iostream>
#include <assert.h>

using namespace std;

void print(SList & list);
/*
 * 
 */
int main(int argc, char** argv) {
    SList l;
    SItem i1(1);
    SHandle * handle=l.head();
    
    cout << "--Pred vlozenim prazdna: " << l.isEmpty() << endl;
    assert(l.isEmpty()==true);
    l.printElements();
    l.pushFront(i1);
    cout << "--Po vlozeni prvku 1 metodou pushFront\n  prazdna: "<< l.isEmpty();
    assert(!l.isEmpty());
    cout<< "\n  prvni prvek " << l.first() -> getItem().getElement() ;
    assert(l.first() -> getItem().getElement() == 1);
    cout << "\n  posledni prvek: " << l.last()->getItem().getElement() << endl;
    assert(l.last() -> getItem().getElement() == 1);
    l.printElements();

    //cout << "--Popnuty prvek metodou popFront:   " << l.popFront().getElement();
    assert(l.popFront().getElement()==1);
    cout << "\n  prazdny: " << l.isEmpty() << endl;
    assert(l.isEmpty());
    l.printElements();

    l.pushBack(i1);

    cout << "--Po vlozeni prvku 1 metodou pushBack()\n  prazdna: " << l.isEmpty();
    assert(!l.isEmpty());
    cout << "\n  prvni prvek: " << l.first() -> getItem().getElement() ;
    assert(l.first() -> getItem().getElement()==1);
    cout << "\n  posledni prvek: " << l.last()->getItem().getElement() << endl;
    assert(l.last() -> getItem().getElement()==1);
    l.printElements();

    SItem si = SItem(1);

    handle = l.findNext(&si,l.head());
    cout << "--Nalezeni dalsiho prvku 1 nasledujicim po head\n";
    cout << "   handle == 1.prvek " << (handle == l.first()) << endl;
    assert(handle == l.first());
    assert(handle != l.head());

    l.pushFront(SItem(2));

    cout << "--Po vlozeni prvku 2 metodou pushFront() \n  prazdna: " << l.isEmpty();
    cout << "\n    prvni prvek: " << l.first() -> getItem().getElement();
    cout << "\n   posledni prvek: " << l.last()->getItem().getElement();
    cout << "\n  predchudce first == head:" << (l.findPredecessor(l.first()) == l.head());
    cout << "\n  predchudce tail == first:" << (l.findPredecessor(l.last()) == l.first()) << endl;
    l.printElements();
    assert(l.last()->getItem().getElement() == 1);
    assert(l.first()->getItem().getElement() == 2);
    assert(l.first()->getNext()->getItem().getElement() == 1);


    l.moveToFront(l.last());
    cout << "--Po moveToFront(last)  \n";
    cout << "  predchudce first == head:" << (l.findPredecessor(l.first()) == l.head()) << endl;
    cout << "  predchudce tail == first:" << (l.findPredecessor(l.last()) == l.first()) << endl;
    print(l);
    assert(l.last()->getItem().getElement() == 2);
    assert(l.first()->getItem().getElement() == 1);
    assert(l.first()->getNext()->getItem().getElement() == 2);
    assert(l.findPredecessor(l.first())==l.head());
    assert(l.findPredecessor(l.last())==l.first());


    SItem item = SItem(55);
    handle = l.findNext(&item,l.head());
    cout << "--Nalezeni prvku, ktery v listu neni head == handle s nalezenym prvkem: ";
    cout << (handle == l.head()) << endl;
    assert(handle == l.head());


    l.moveToBack(l.first());
    cout << "--Po moveToBack(front)  \n";
    cout << "  predchudce first == head:" << (l.findPredecessor(l.first()) == l.head()) << endl;
    cout << "  predchudce tail == first:" << (l.findPredecessor(l.last()) == l.first()) << endl;
    print(l);
    assert(l.findPredecessor(l.first()) == l.head());
    assert(l.findPredecessor(l.last()) == l.first());
    assert(l.last()->getItem().getElement() == 1);
    assert(l.first()->getItem().getElement() == 2);
    assert(l.first()->getNext()->getItem().getElement() == 1);

    l.insertAfter(l.first(),SItem(7));
    cout << "--Po insertAfter(first, 7): ";
    print(l);
    assert(l.first()->getItem().getElement() == 2);
    assert(l.first()->getNext()->getItem().getElement() == 7);
    assert(l.first()->getNext()->getNext()->getItem().getElement() == 1);

    SList list2 = SList(l);
    cout << "--Zkopirovany list: ";
    print(list2);
    assert(list2.first()->getItem().getElement() == 2);
    assert(list2.first()->getNext()->getItem().getElement() == 7);
    assert(list2.first()->getNext()->getNext()->getItem().getElement() == 1);


    list2.popFront();
    cout << "--Kopie - po PopFront:\n";
    print(list2);
    assert(list2.first()->getItem().getElement() == 7);
    assert(list2.first()->getNext()->getItem().getElement() == 1);

    l.concat(&list2);
    cout << "--Po concat: ";
    cout << "List2 prazdny: " << list2.isEmpty() << endl;
    print(l);
    assert(l.first()->getItem().getElement() == 2);
    assert(l.first()->getNext()->getItem().getElement() == 7);
    assert(l.first()->getNext()->getNext()->getItem().getElement() == 1);
    assert(l.first()->getNext()->getNext()->getNext()->getItem().getElement() == 7);
    assert(l.first()->getNext()->getNext()->getNext()->getNext()->getItem().getElement() == 1);
    assert(list2.isEmpty());
    assert(!l.isEmpty());



    l.moveAfter(l.head()->getNext()->getNext()->getNext(), l.head()->getNext());
    cout << " Po moveAfter(presun 3. za 1.)" << endl;
    print(l);
    assert(l.first()->getItem().getElement() == 2);
    assert(l.first()->getNext()->getItem().getElement() == 1);
    assert(l.first()->getNext()->getNext()->getItem().getElement() == 7);
    assert(l.first()->getNext()->getNext()->getNext()->getItem().getElement() == 7);
    assert(l.first()->getNext()->getNext()->getNext()->getNext()->getItem().getElement() == 1);


    l.remove(l.first()->getNext()->getNext());
    cout << "--Po remove tretiho prvku:\n";
    print(l);
    assert(l.first()->getItem().getElement() == 2);
    assert(l.first()->getNext()->getItem().getElement() == 1);
    assert(l.first()->getNext()->getNext()->getItem().getElement() == 7);
    assert(l.first()->getNext()->getNext()->getNext()->getItem().getElement() == 1);


    l.removeAfter(l.head());
    cout << "--Po removeAfter(head):\n";
    print(l);
    assert(l.first()->getItem().getElement() == 1);
    assert(l.first()->getNext()->getItem().getElement() == 7);
    assert(l.first()->getNext()->getNext()->getItem().getElement() == 1);

    l.makeEmpty();
    cout << "--Po makeEmpty prazdny: " << l.isEmpty() << endl;
    l.printElements();
    assert(l.isEmpty());

    SItem i55 = SItem(55);
    handle = l.findNext(&i55,l.head());
    cout << "Handle == head v prazdnym listu: " << (handle == l.head()) << endl;
    assert(handle == l.head());
    return 0;
}

void print(SList & l){
    cout << "  prazdny: " << l.isEmpty();
    cout << "\n  prvni prvek: " << l.first() -> getItem().getElement() ;
    cout << "\n  posledni prvek: " << l.last()->getItem().getElement() << endl;
    l.printElements();
}

