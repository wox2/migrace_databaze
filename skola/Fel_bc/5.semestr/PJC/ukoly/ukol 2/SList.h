/* 
 * File:   SList.h
 * Author: woxie
 *
 * Created on 23. říjen 2010, 15:17
 */

#ifndef SLIST_H
#define	SLIST_H
#include "SItem.h"
#include "SHandle.h"

class SList {
public:
    SList();
    SList(const SList & orig);
    virtual ~SList();
   
    SHandle * head()const; // const;
    bool isEmpty()const; //const;

    SHandle * first()const;
    SHandle * last()const;

    SItem popFront();
    void pushFront(SItem item);
    void pushBack(SItem item);
    void insertAfter(SHandle * handl, SItem item);
    void concat(SList * list);
    
    void makeEmpty();
    void moveAfter(SHandle * handle , SHandle * after);
    void moveToFront(SHandle * handle);
    void moveToBack(SHandle * handle);

    
    void remove(SHandle * handle);
    void removeAfter(SHandle * handle);
    SHandle * findNext(SItem * item, SHandle * handle);
    
    //Check Methods
    SHandle * findPredecessor(SHandle *);
    void printElements();
    
private:
    SHandle * h;
    SHandle * tail;
    
};

#endif	/* SLIST_H */

