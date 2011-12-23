/* 
 * File:   SList.cpp
 * Author: woxie
 * 
 * Created on 23. říjen 2010, 15:17
 */

#include "SList.h"
#include <cstddef>
#include <assert.h>
#include <iostream>
using namespace std;

SList::SList() {
    h = new SHandle();
    tail = h;
}

SList::SList(const SList & orig){
    h = new SHandle();
    tail = h;
    if(!orig.isEmpty()){
        SHandle * temp = orig.first();
        while((temp != orig.head())){
            this->pushBack(temp->getItem());
            temp = temp->getNext();
        }
    }
}


SList::~SList() {
    cout << "volam Destruktor List\n";
    SHandle * temp = head();
        SHandle * handle = head()->getNext();
        while(handle!=h){
            temp=handle;
            handle = handle ->getNext();
            delete temp;
        }
        delete h;
}

SHandle * SList::head()const{
    return h;
}

SHandle * SList::first()const{
    assert(!isEmpty());
    return h-> getNext();
}

SHandle * SList::last()const{
    assert(!isEmpty());
    return tail;
}

bool SList::isEmpty()const{
    return ( ( h-> getNext() )== h );
}

    SItem SList::popFront(){
        assert(!isEmpty());
        SHandle * handle = h->getNext();
        h->setNext(handle->getNext());
        handle->setNext(NULL);
        SItem ret = handle->getItem();
        if(isEmpty()){
            tail = h;
        }
        delete handle;
        return ret;
    }

    void SList::pushFront(SItem item){
        SHandle * handle = new SHandle(item);
        
        if(isEmpty()){
            tail = handle;
        }

        handle->setNext(h->getNext());
        h->setNext(handle);
    }

    void SList::pushBack(SItem item){
        SHandle * handle = new SHandle(item);
        tail->setNext(handle);
        tail = handle;
        tail->setNext(h);
    }

    void SList::insertAfter(SHandle * handle, SItem item){
        //assert handle is in list
        SHandle * temp = new SHandle(item);
        temp->setNext(handle->getNext());
        handle->setNext(temp);       
    }

    void SList::concat(SList * list){
        tail->setNext(list->first());
        tail=list->tail;
        tail->setNext(h);
        list->h->setNext(list->head());
        list->tail=list->h;
    }

    //to chce revizi
    void SList::makeEmpty(){
        SHandle * temp = head();
        SHandle * handle = head()->getNext();
        while(handle!=h){
            temp=handle;
            handle = handle ->getNext();
            delete temp;
        }
        h->setNext(handle);
    }

    void SList::moveAfter(SHandle * movedHandle , SHandle * after){
        assert(movedHandle != h);
        SHandle * temp = findPredecessor(movedHandle);

        if(temp == after ) return;
        if(movedHandle == tail ) {
            tail = temp;
        }
        else{
            if(after == tail){
                tail = movedHandle;
            }
        }
        // temp je predecessor-predchudce
        temp->setNext(movedHandle->getNext());
        movedHandle->setNext(after->getNext());
        after->setNext(movedHandle);
    }


    void SList::moveToFront(SHandle * handle){
        moveAfter(handle, head());
    }

    void SList::moveToBack(SHandle * handle){
        moveAfter(handle, tail);
    }


    void SList::remove(SHandle * handle){
        SHandle * predecessor = findPredecessor(handle);
        predecessor->setNext(handle->getNext());
        delete handle;
    }

    void SList::removeAfter(SHandle * handle){
        SHandle * temp = handle->getNext();
        assert(temp!=head());
        handle->setNext(temp->getNext());
        delete temp;
    }

    SHandle * SList::findNext(SItem * item, SHandle * from){
        SHandle * temp = from;
        while(!(temp->getItem().getElement()==item->getElement())){
            temp = (temp->getNext());
            if(temp == h) break;
        }
        return temp;
    }

    SHandle * SList::findPredecessor(SHandle * handle){
        SHandle * temp = head()->getNext();
        while(temp->getNext() != handle){
            temp = temp->getNext();
        }
        return temp;
}

   void SList::printElements(){
        cout << "List: ";
       if(isEmpty()) {
           cout << "je prazdny\n";
           return;
       }
        SHandle * temp = first();
       
        while(temp != h){
            cout << temp->getItem().getElement() << " ";
            temp = temp->getNext();
        }
        cout << "\n";
   }