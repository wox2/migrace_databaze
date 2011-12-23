/* 
 * File:   Condensator.h
 * Author: woxie
 *
 * Created on 9. leden 2011, 17:54
 */

#ifndef CONDENSATOR_H
#define	CONDENSATOR_H

#include "StrongComponent.h"
#include <vector>
/**
 * Trida kondenzujici danou mnozinu silnych komponent.
 * @param condensableComponents - mnozina Component s jejich nasledniky a predchudci, ktera ma byt kondenzovana.
 */
class Condensator {
public:
    /**
     * Konstruktor vytvori kondenzator
     * @param condensableComponents - kondenyovane komponenty.
     */
    Condensator(vector<StrongComponent *> * condensableComponents);
    /**
     * Metoda zprostredkovava kondenzaci site.
     */
    void condensate();
    
private:
    /**
     * Pointer na Mnozinu silnych komponent.
     */
    vector<StrongComponent *> * components;
    /**
     * Kondenzace dane Componenty. Pro kazdeho predchudce Componenty zjisti, jestli je zaroven naslednik Componenty a pokud ano,
     * tak zavola metodu merge a odstrani nasledniko-predka z site.
     * @param component Componenta, ktera ma byt kondenzovana.
     */
    void condensate(StrongComponent * component);
    /**
     * Odstrani Componentu z mnoziny ("Listu") Component.
     * @param component Componenta, ktera ma byt odstranena.
     */
    void removeFromComponentList(StrongComponent * component);
};

#endif	/* CONDENSATOR_H */

