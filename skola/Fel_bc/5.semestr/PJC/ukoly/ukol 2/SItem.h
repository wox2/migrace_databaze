/* 
 * File:   SItem.h
 * Author: woxie
 *
 * Created on 23. říjen 2010, 15:17
 */

#ifndef SITEM_H
#define	SITEM_H
//#include "Element.h"
#include <cstddef>
#include "SItem.h"

class SItem {
public:
    SItem();
    SItem(int i);
    SItem(const SItem& orig);
    virtual ~SItem();
    void setElement(int sElement);
    int getElement()const;
    SItem operator=(const SItem &);
    bool equals(SItem & item);

private:
    int element;
};

#endif	/* SITEM_H */

