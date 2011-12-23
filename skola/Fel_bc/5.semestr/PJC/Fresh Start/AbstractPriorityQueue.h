/* 
 * File:   LowPriorityQueue.h
 * Author: woxie
 *
 * Created on 9. leden 2011, 9:36
 */

#ifndef ABSTRACTPRIORITYQUEUE_H
#define	ABSTRACTPRIORITYQUEUE_H
#include <vector>
#include "StrongComponent.h"
/**
 * Trida, ktera je predkem vsech Prioritnich front. Puvodne mel program funguje s dvema prioritnimi frontami trochu jineho razeni -
 * jednu pro zisk nejmensiho prvku a jednu pro zisku nejvetsiho, proto je trida implementovana skrz "pole" a nikoliv pres heap.
 */
class AbstractPriorityQueue {
public:
    /**
     * Neparametricky konstruktor, slozi k vytvoreni prazdne fronty.
     */
    AbstractPriorityQueue();
    /**
     * Destruktor, uklizi.
     */
    virtual ~AbstractPriorityQueue();
    /**
     * Metoda enque - slouzi k vlozeni prvku do prioritni fronty. Po vlozeni prvku musi byt zachovana vlastnost prioritni fronty -
     * prvky jsou serazeny od nejtetsiho z hlediska relace pouzite k porovnavani (nejvetsi prvek - je "vetsi" nez vsechny ostatni).
     * @param component vkladana komponenta
     */
    void enqueue(StrongComponent * component);
    /**
     * Odstranuje nejvetsi komponentu z fronty. V pripade prazdne fronty vraci NULL
     * @return nejvetsi Componenta.
     */
    StrongComponent * dequeue();
    /**
     * Ziska pozici Componenty zadane v parametru.
     * @param component - Componenta, jejiz pozice se hleda.
     * @return
     */
    int getComponentsPosition(StrongComponent * component);
    /**
     * Prohodi prvky na pozicich i a j
     * @param i pozice prvniho prohozeneho prvku.
     * @param j pozice druheho prohozeneho prvku.
     */
    void switchFields(int i, int j);
    /**
     * Overuje, zda ma komponenta naslednika v fronte. Componenta musi byt v relaci vetsi s naslednikem.
     * @param component - komponenta, jejiz naslednik se hleda.
     * @return bool hodnota vyjadrujici, jestli ma komponenta naslednika v fronte ci ne.
     */
    bool hasNext(StrongComponent * component);
    /**
     * Ziska naslednika dane Componenty. V pripade, ze Componenta nema naslednika v fronte, vraci NULL.
     * @param component Componenta, ke ktere hledame naslednika.
     * @return naslednik Componenty
     */
    StrongComponent * getNext(StrongComponent * component);

    /**
     * Actualizuje Componentu po navyseni sledovane hodnoty.
     * @param component - Componenta, jejiz hodnota se navysuje.
     */
    virtual void actualizeAfterHigheringValue(StrongComponent * component)=0;
    /**
     * Ziska velikost fronty.
     * @return velikost fronty.
     */
    int getSize();
    
protected:
    /**
     * Datova struktura slouzici k ulozeni Component.
     */
    vector<StrongComponent * > content;
    /**
     * Metoda zajistujici prioritni vlastnost Prioritni Fronty.
     */
    virtual void priorize()=0;
};

#endif	/* ABSTRACTPRIORITYQUEUE_H */

