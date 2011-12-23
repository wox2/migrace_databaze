/* 
 * File:   ComunicationNetwork.h
 * Author: woxie
 *
 * Created on 4. leden 2011, 15:24
 */

#ifndef COMUNICATIONNETWORK_H
#define	COMUNICATIONNETWORK_H
#include <vector>
#include "Road.h"
#include "City.h"
using namespace std;

/**
 * Trida reprezentuje celou komunikacni sit "Kralovstvi".
 * @param nodes pocet mest v kralovstvi.
 */
class ComunicationNetwork {
public:
    /**
     * Konstruktor - vytvori sit s zadanym poctem mest.
     * @param nodes - pocet mest v siti.
     */
    ComunicationNetwork(int nodes);
    /**
     * kopirovaci konctruktor. Vytvori kopie mest a silnic, ktere vlozi do nove ComunicationNetwork.
     * @param orig - original, ktery ma byt zkopirovan.
     */
    ComunicationNetwork(const ComunicationNetwork& orig);
    /**
     * Destruktor - odstrani mesta a silnice v siti.
     */
    virtual ~ComunicationNetwork();
    /**
     * Metoda vytvori a prida cestu do site.
     * @param start - Startovni pozice
     * @param end - Koncova pozice
     */
    void addRoad(int start, int end);
    /**
     * Ziska z CommunicationNetwork mesto s zadanym id, jinak NULL.
     * @param id ID hledaneho mesta.
     * @return mesto s zadanym id,
     */
    City * getCity(int id);
    /**
     * Vytiskne danou komunikacni sit na standartni vystup.
     */
    void print();
    /**
     * Pocet uzlu, ridi se C++ myslenkou, ze getter a zaroven i setter hodnoty, ktere nedelaji
     *  s hodnotou zadne vypocty rusi zaapouzdreni.
     */
    int nodeCount;
    /**
     * Silnice v siti. Asi by mely byt private, ale nebyl cas dodrzovat Law of Demeter.
     */
    vector<Road *> roads;
    
private:
    /**
     * Datova struktura udrzujici pocet silnic v Siti.
     */
    vector<City *> cities;
};

#endif	/* COMUNICATIONNETWORK_H */

