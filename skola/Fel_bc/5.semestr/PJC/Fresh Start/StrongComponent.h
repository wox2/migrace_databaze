/* 
 * File:   StrongComponent.h
 * Author: woxie
 *
 * Created on 4. leden 2011, 18:32
 */

#ifndef STRONGCOMPONENT_H
#define	STRONGCOMPONENT_H
#include <vector>
#include "City.h"
/**
 * Trida reprezentuje silnou komponentu = struktura, ktera obsahuje uzly, z kazdeho z nich je mozne se dostat do vsech
 * ostatnich 
 */
class StrongComponent {
public:

    /**
     * Konstruktor - vytvori diskretni bod - konponentu s jen jednim uzlem, bez hran vedoucich z uzlu nebo do uzlu.
     * @param city Mesto, ktere nalezi komponente.
     */
    StrongComponent(City * city);

    /**
     * Kopirovaci konstruktor
     * @param orig - komponenta, ktera ma byt zkopirovana.
     */
    StrongComponent(const StrongComponent& orig);

    /**
     * Destruktor.
     */
    virtual ~StrongComponent();

    /**
     * Spoji dve komponenty v jednu, vyprazdni druhou. Neodstrani druhou. Z parametru odstrani mesta, predchudce, nasledniky,
     * nahrazuje ve svych predchudcich a naslednicich Componentu za objekt, z ktereho se vola. TATO METODA POTREBUJE REVIZI - PRAVDEPODOBNE
     * NECHAVA V SEZNAMU DUPLICITNI PREDCHUDCE - NASLEDNIKY - sebe sama(nejspise je chyba v volane metode insert-update)...
     * To zneprijemnuje funkcni metody isRoot() a vlastne vsech update metod.
     * @param component Componenta, s kterou se ma dana Componenta spojit.
     */
    void merge(StrongComponent * component);

    /**
     * Ziska reprezentanta Componenty. Pokud je Componenta po merge, vrati NULL.
     * @return reprezentatn Componenty
     */
    City * getRepresentant() const;

    /**
     * Pomocna metoda - vytiskne jen reprezentanta
     */
    void print()const;

    /**
     * Pomocna metoda - vytiskne na standartni vstup reprezentanta, pocet a seznam nasledniku - predchudcu.
     */
    void printAll()const;

    /**
     * Metoda overuje, jestli objekt, na kterem je volana obsahuje mesto s danym ID.
     * @param cityId
     * @return
     */
    bool isContaining(int cityId)const;

    /**
     * Vlozi do seznamu predchudcu objektu Componentu, rekurzivni -> vola se pro kazdeho naslednika, s ukoncujici podminkou,
     * dokdy neprojde kolo(nepridava duplicitne, nepridava sam sebe)
     * @param component Pridany predchudce
     */
    void insertPrecedessor(StrongComponent * component);

    /**
     * Vlozi do seznamu nasledniku objektu Componentu, rekurzivni -> vola se pro kazdeho predchudce, s ukoncujici podminkou,
     * dokdy neprojde kolo(nepridava duplicitne, nepridava sam sebe)
     * @param component Pridany predchudce
     */

    void insertSuccesor(StrongComponent * component);

    /**
     * Zjisti, jestli ma object StrongComponentu jako naslednika.
     * @param component Componenta testovana na naslednictvi
     * @return Informaci, jestli je Component naslednik objektu
     */
    bool hasSuccessor(StrongComponent * component);

    /**
     * Zjisti, jestli ma object StrongComponentu jako predchudce.
     * @param component Componenta testovana na predchudkovstvi.
     * @return Informaci, jestli je Component predchudce objektu
     */
    bool hasPrecedessor(StrongComponent * component);

    /**
     * Ziska pocet prechudcu Componenty.
     * @return pocet prechudcu Componenty
     */
    int getPrecedessorCount();

    /**
     * Ziska pocet nasledniku Componenty.
     * @return pocet nasledniku Componenty
     */
    int getSuccesorCount();

    /**
     * Datova struktura uchovavajici pointery na predchudce
     */
    vector<StrongComponent *> precedessors;

     /**
     * Datova struktura uchovavajici pointery na nasledniky
     */

    vector<StrongComponent *> succesors;

    /**
     * Vymeni stareho predchudce za noveho. Nekontroluje, jestli je stary predchudce opravdu predchudce.
     * @param precedessor stary predchudce
     * @param newPrecedessor novy predchudce
     */
    void changePrecedessor(StrongComponent * precedessor, StrongComponent * newPrecedessor);

     /**
     * Vymeni stareho naslednika za noveho. Nekontroluje, jestli je stary naslednik opravdu naslednik.
     * @param successor stary naslednik
     * @param newSuccessor novy naslednik
     */
    void changeSuccessor(StrongComponent * successor, StrongComponent * newSuccessor);

    /**
     * Odstrani rekurzivne naslednika
     * @param succesor Odstranovany naslednik
     */
    void removeSuccesor(StrongComponent * succesor);
    
    /**
     * Odstrani rekurzivne predchudce
     * @param succesor Odstranovany predchudce
     */
    void removePrecedessor(StrongComponent * precedessor);

    /**
     * overi, jestli je objekt, na kterem je volana root. NYNI NENI IMPLEMENTOVANA UPLNE IDEALNE, NEBOT V PROGRAMU VZNIKA STAV, KDY MA
     * KOMPONENTA VICE DUPLICITNICH PREDCHUDCU.
     * @return Informace o tom, jestli je objekt root.
     */
    bool isRoot();
private:
    /**
     * Datova struktura udrzujici pointery na mesta, ktera jsou soucasti Componenty.
     */
    vector<City *> cities;
};

#endif	/* COMPONENT_H */

