/* 
 * File:   Algorithm.h
 * Author: woxie
 *
 * Created on 4. leden 2011, 15:24
 */

#ifndef ALGORITHM_H
#define	ALGORITHM_H
#include "ComunicationNetwork.h"
#include "HighPrecedessorsPriorityQueue.h"

/**
Trida Algorithm zprostredkovava funkci celeho algoritmu.
*/
class Algorithm {
public:
    /**
     *
     */
    Algorithm();
    /**
     * Kopirovaci konstruktor. Nevyuzit, neimplementovan. 
     */
    Algorithm(const Algorithm& orig);
    /**
     * Destruktor.
     */
    virtual ~Algorithm();
/**
Metoda getDataAndCreateNetwork() nacte z standartniho vstupu data a vytvori z nich (v pripade validity) Comunikacni sit.
*/
    bool getDataAndCreateNetwork();
/**
Metoda process() spusti algoritmus. Nyni nevaliduje, jestli jsou data vytvorena, ci ne.
*/
    void process();
/*
Metoda doStep() udela krok algoritmu. Je vyuzita v metode Process.
*/
    void doStep();
/**
Metoda printResults() vytiskne pridane cesty Algoritmem v prubehu metody process.
*/
    void printResults();
/**
Vytiskne komunikacni sit vytvorenou v metode getDataAndCreateNetwork().
*/
    void printNetwork();
private:
/**
Opravi predchudce a nasledniky v silnych komponentach, vyuziva k tomu prvky z vector<Road *> po vytvoreni silnych komponent
*/
    void updatePrecedessorsAndSuccesorsInStrongComponents();
/**
Zkondensuje vsechny uzly.
*/
    void condensate();
/**
Inicializuje prvky, pravdepodobne spatny nazev, nebot se nevola v konstruktoru.
*/
    void init();
/**
Inicializuje prioritni frontu.
*/
    void initPriorityQueue();
/*
Vlozi kazde mesto do jine Silne Componenty.
*/    
    void insertDiscretePointsToStrongComponents();
/**
Ziska komponentu obsahujici mesto s zadanym id.
*/    
    StrongComponent * getComponentIncludingCity(int cityId);
/**
Metoda kontrolujici stav algoritmu. Je pouzita v metode process().
*/
    bool isFinished();
/**
Inicializuje mnozinu korenu.
*/    
    void initRootSet();
/*
Ziska koren, pro danou componentu. Zabranuje chybne funkci v diskretnim grafu.
*/
    StrongComponent * getSuitableRoot(StrongComponent * component);
/**
Datova struktura uchovavajici hrany pridane algoritmem.
*/
    vector<Road *> addedRoads;
/**
Pointer na sit uzlu a hran, nad kterou je provaden algoritmus.
*/
    ComunicationNetwork * net;
/**
Datova struktura uchovavajici silne komponenty.
*/
    vector<StrongComponent *> components;
/**
Prioritni fronta, v ktere je hlavnim porovnavacim kriteriem pocet predchudcu. Mela byt pouzita heap, ale algoritmus se chvili tvaril tak, ze budou dve fronty - ta druha pro zisk nejmensiho successora.
*/
    HighPrecedessorsPriorityQueue precedessorsQueue;
/**
Datova struktura uchovavajici mnozinu korenu.
*/
    vector<StrongComponent *> roots;
/**
Hodnota urcujici, jestli byla sit spravne vytvorena.
*/
    bool netCreated;
    };

#endif	/* ALGORITHM_H */

