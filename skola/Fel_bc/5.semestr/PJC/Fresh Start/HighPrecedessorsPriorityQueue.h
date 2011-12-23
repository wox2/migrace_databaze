/* 
 * File:   HighPriorityQueue.h
 * Author: woxie
 *
 * Created on 9. leden 2011, 9:37
 */

#ifndef HIGHPRECEDESSORSPRIORITYQUEUE_H
#define	HIGHPRECEDESSORSPRIORITYQUEUE_H
#include "StrongComponent.h"
#include "AbstractPriorityQueue.h"
/**
 * Trida reprezentuje frontu, ktera definuje relaci vetsi mezi A a B, kdy A ma vetsi nebo stejny pocet predchudcu jako B.
 */
class HighPrecedessorsPriorityQueue: public AbstractPriorityQueue {
public:
    /**
     * Konstruktor.
     */
    HighPrecedessorsPriorityQueue();
    /**
     * Kopirovaci konstruktor, stale neimplementovany
     * @param orig
     */
    HighPrecedessorsPriorityQueue(const HighPrecedessorsPriorityQueue& orig);
    /**
     * Destruktor
     */
    virtual ~HighPrecedessorsPriorityQueue();
    /**
     * Po moznem navyseni poctu predchudcu Aktualizuje frontu.
     * @param component Actualizovana Componenta
     */
    virtual void actualizeAfterHigheringValue(StrongComponent * component);
protected:
    /**
     * Zachovava prioritni vlastnost -> prvky budou odstranovany z prioritni fronty metodou dequeue()jako neklesajici posloupnost
     *  podle poctu predchudcu (Predpoklad tohoto tvrzeni je, mezi volanimi dequeue nebude volano enqueue).
     */
    virtual void priorize();
};

#endif	/* HIGHPRIORITYQUEUE_H */

