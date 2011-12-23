/* 
 * File:   Node.h
 * Author: woxie
 *
 * Created on 4. leden 2011, 0:16
 */

#ifndef CITY_H
#define	CITY_H
#include <vector>
using namespace std;
/**
 * Trida reprezentujici mesta v vytvorene siti.
 * @param id Identifikator mesta.
 */
class City {
public:
    /**
     * Konstruktor vytvori mesto s zadanym id, jako defaultni parametr je hodnota -1.
     * @param id Identifikator mesta
     */
    City(int id=-1);
    /**
     * Kopirovaci konstruktor.
     * @param orig - originalni mesto, ktere se ma zkopirovat.
     */
    City(const City& orig);
    /**
     * Destruktor
     */
    virtual ~City();
    /**
     * Id mesta, ridi se C++ myslenkou, ze getter a zaroven i setter hodnoty, ktere nedelaji
     *  s hodnotou zadne vypocty rusi zaapouzdreni.
     */
    int _id;
};


#endif	/* NODE_H */

