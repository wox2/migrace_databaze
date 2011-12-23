/* 
 * File:   SHandle.h
 * Author: woxie
 *
 * Created on 23. říjen 2010, 15:17
 */

#ifndef SHANDLE_H
#define	SHANDLE_H
#include <cstddef>
#include "SItem.h"

class SHandle {
public:
    SHandle();
    SHandle(SItem value);
    SHandle(const SHandle &orig);
    virtual ~SHandle();
    SItem getItem()const ;
    void setItem(SItem item);
    SHandle * getNext()const;
    void setNext(SHandle * nextHandle);
    static int destr;
    static int konstr;

private:
    SItem item;
    SHandle * next;
    
};

#endif	/* SHANDLE_H */

