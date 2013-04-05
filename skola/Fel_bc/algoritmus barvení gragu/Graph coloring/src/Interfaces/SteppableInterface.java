/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Interfaces;

import Exceptions.AlgorithmFinishedException;
import Core.Graph;
/**
 * Tridy implementujici toto rozhrani musi zastupovat grafgove algoritmy, ktere lze krokovat.
 * @author woxie
 */
public interface SteppableInterface  {

    /**
     * Metoda provede jeden krok algoritmu. V pripade, ze jiz neni mozne ucinit zadny krok
     * (algoritmus je u konce) metoda vyhodi vyjimku
     * @return Graf po provedeni jednoho kroku
     * @throws AlgorithmFinishedException
     */
    Graph doStep() throws AlgorithmFinishedException;

    /**
     * Metoda nastavujici Graph do pocatecniho stavu.
     */
    void reset();

    
}
