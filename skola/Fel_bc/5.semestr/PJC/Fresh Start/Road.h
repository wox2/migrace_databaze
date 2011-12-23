/* 
 * File:   Road.h
 * Author: woxie
 *
 * Created on 4. leden 2011, 15:25
 */

#ifndef ROAD_H
#define	ROAD_H
/**
 * Trida reprezentuje silnici v programu.
 * @param start ID startovni pozice
 * @param end ID koncove pozice
 */
class Road {
public:
    /**
     * Konstruktor vytvori Road s zadanym cilovym id mesta end a pocatecnim id mesta start.
     * @param start ID pocatku
     * @param end ID konce
     */
    Road(int start, int end);
    /**
     * kopirovaci konstruktor
     * @param orig kopirovana Road
     */
    Road(const Road& orig);
    /**
     * Destruktor
     */
    virtual ~Road();
    /**
     * Pomocna metoda slouzici k vytisknuti silnice na standartni vstup.
     */
    void print();
    /**
     * Pocatecni pozice silnice. Mela by byt const.
     */
    int _start;
    /**
     * Koncova pozice dane silnice. Mela by byt const.
     */
    int _end;
private:

};

#endif	/* ROAD_H */

