/* 
 * File:   NetworkCreator.h
 * Author: woxie
 *
 * Created on 8. leden 2011, 1:37
 */

#ifndef NETWORKCREATOR_H
#define	NETWORKCREATOR_H
#include "ComunicationNetwork.h"
/**
 * Trida, ktera vytvori komunikacni sit.
 */
class NetworkCreator {
public:
    /**
     * Konstruktor.
     */
    NetworkCreator();
    /**
     * Kopirovaci konstruktor.
     * @param orig
     */
    NetworkCreator(const NetworkCreator& orig);
    /**
     * Destruktor - neodstranuje jim vytvorenou sit.
     */
    virtual ~NetworkCreator();
    /**
     * Podle dat zadanych do standartniho vstupu vytvori nebo nevytvori komunikacni sit.
     * @return informace o vytvoreni ci nespravnosti dat. true - sit byla vytvorena, false - zadana data byla chybna.
     */
    bool createNetwork();
    /**
     * Ziska DRIVE vytvorenou sit metodou createNetwork.
     * @return
     */
    ComunicationNetwork * getNetwork();
private:
    /**
     * Pointer na sit vytvorenou touto tridou - Trida sit vytvori, ale neodstranuje.
     */
    ComunicationNetwork * net;
};

#endif	/* NETWORKCREATOR_H */

