/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import Exceptions.PriorityQueueEmptyException;
import Interfaces.PriorityNodeInterface;
import Interfaces.PriorityQueueComparatorInterface;

/**
 * Trida reprezentuje prioritni Frontu, do ktere se daji vkladat Nody a zase z ni byt vyndavany v poradi
 * od Nodu s nejmensi vzdalenosti po Node s nejvyssi vzdalenosti
 * @author woxie
 */
public class PriorityQueue {

    /**
     *  Pole Nodu, ktere jsou ulozene v prioritni fronte.
     */
    private PriorityNodeInterface[] queue;
    /**
     *  Index pole za poslednim prvkem.
     */
    private int afterLast;

    private PriorityQueueComparatorInterface priorityQueueComparator;

    /**
     * Konstruktor vytvori prioritni frontu s danym rozmerem - maximalni poctem prvku.
     * @param rozmer rozmer fronty
     */
    public PriorityQueue(int rozmer) {
        queue = new PriorityNodeInterface[rozmer];
        afterLast = 0;
        priorityQueueComparator = null;
    }

    /**
     *   Metoda, ktera vlozi Node do fronty.
     * @param node vlozeny Node do fronty
     */
    public void enqueue(PriorityNodeInterface node) {
        queue[afterLast] = node;
        afterLast++;
        int i = afterLast - 1;
        while (i > 0 && priorityQueueComparator.compareByConditions(queue[getParent(i)], queue[i])) {
            switchNodes(i, getParent(i));
            i = getParent(i);
        }
    }

    /**
     *  REKURZIVNI metoda, ktera zajistuje vlastnost heapify pro prvek s pozici int.
     *  @param position pozice Nodu, od ktereho se dela heapify
     */
    private void heapify(int position) {
        int left = getLeftChildPosition(position);
        int right = getRightChildPosition(position);
        int max;
        if (left <= afterLast - 1 && priorityQueueComparator.compareByConditions(queue[position], queue[left])) {
            max = left;
        } else {
            max = position;
        }

        if (right <= afterLast - 1 &&  priorityQueueComparator.compareByConditions(queue[max],queue[right])) {
            max = right;
        }

        if (max != position) {
            switchNodes(position, max);
            heapify(max);
        }
    }

    /**
     *  Metoda, ktera odstranuje node s minimalni hodnotou (vzdalenosti) z fronty. Navratova hodnota je dany Node.
     *  Metoda vyhazuje vyjimku, pokud je zavolana a fronta je prazdna.
     * @return Node s nejnizsi hodnotou vzdalenosti
     * @throws PriorityQueueEmptyException v pripade, ze Fronta je prazdna a metoda je zavolana se vyhodi tato vyjimka
     */
    public PriorityNodeInterface dequeue() throws PriorityQueueEmptyException {
        if (isEmpty()) {
            throw new PriorityQueueEmptyException();
        }
        PriorityNodeInterface throwAway = queue[0];
        queue[0] = queue[afterLast - 1];
        //queue[afterLast - 1]=null;
        afterLast--;
        heapify(0);
        return throwAway;
    }

    /**
     * Staticka metoda navracejici index rodicovskeho Nodu. V pripade, ze se dostane k Nodu s indexem 0, tak vrati -1;
     * @param position pozice Nodu, jehoz rodic se ma vratit
     * @return pozice rodicovskeho Nodu
     */
    private static int getParent(int position) {
        if (position == 0) {
            return -1;
        }
        return (position - 1) / 2;
    }

    /**
     * Staticka metoda navracejici pozici leveho potomka Nodu udaneho pozici. Pro svoji funkci metoda nepotrebuje Node, jen jeho pozici.
     * @param position pozice Nodu
     * @return pozice leveho potomka
     */
    private static int getLeftChildPosition(int position) {
        return 2 * position + 1;
    }

    /**
     * Metoda navracejici pozici praveho potomka. Pro svoji funkci metoda nepotrebuje Node, jen jeho pozici.
     * @param position pozice Nodu
     * @return pozice praveho potomka
     */
    private static int getRightChildPosition(int position) {
        return 2 * position + 2;
    }

    /**
     *  Metoda overujici, jestli je prioritni fronta prazdna.
     * @return hodnotu typu boolean, zda-li je prioritni fronta prazdna
     */
    public boolean isEmpty() {
        if (afterLast == 0) {
            return true;
        }
        return false;
    }

    /**
     * Metoda aktualizujici stav prioritni fronty po zmene stavu uzlu. Postupuje se od uzlu k jeho predkum.
     *
     * @param node uzel, jehoz potomci splnuji vlastnost prioritni fronty
     */
    public void actualize(PriorityNodeInterface node) {
        if (node == null) {
            return;
        }
        int position = -1;
        for (int i = 0; i < afterLast; i++) {
            if (node.equals(queue[i])) {
                position = i;
                break;
            }
        }

        while (position > 0) {
            if ( priorityQueueComparator.compareByConditions(queue[getParent(position)],queue[position])) {
                switchNodes(position, getParent(position));
            }
            position = getParent(position);

        }
    }

    /**
     * Pomocna metoda, zamenujici jeden prvek za druhy. Pozice prvku dostava jako parametry.
     * @param position1 pozice prvniho Nodu
     * @param position2 pozice druheho Nodu
     */
    private void switchNodes(int position1, int position2) {
        PriorityNodeInterface s = queue[position1];
        queue[position1] = queue[position2];
        queue[position2] = s;
    }

    public void clear(){
        queue=new PriorityNodeInterface[queue.length];
    }

   public void setPriorityComparator(PriorityQueueComparatorInterface comparator){
        priorityQueueComparator = comparator;
   }

   public void actualize(int id){
        PriorityNodeInterface pNode;
        for(int i=0;i<afterLast;i++){
            pNode = queue[i];
            if(( (PriorityNode )pNode).getId() == id){
                actualize(pNode);
            }
        }
    }

}
