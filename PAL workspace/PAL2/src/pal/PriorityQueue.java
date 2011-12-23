///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package pal;
//
///**
// * Trida reprezentuje prioritni Frontu, do ktere se daji vkladat Nody a zase z ni byt vyndavany v poradi
// * od Nodu s nejmensi vzdalenosti po Node s nejvyssi vzdalenosti
// * @author woxie
// */
//public class PriorityQueue {
//
//    /**
//     *  Pole Nodu, ktere jsou ulozene v prioritni fronte.
//     */
//    private Node[] queue;
//    /**
//     *  Index pole za poslednim prvkem.
//     */
//    private int afterLast;
//
//    /**
//     * Konstruktor vytvori prioritni frontu s danym rozmerem - maximalni poctem prvku.
//     * @param rozmer rozmer fronty
//     */
//    public PriorityQueue(int rozmer) {
//        queue = new Node[rozmer];
//        afterLast = 0;
//    }
//
//    /**
//     *   Metoda, ktera vlozi Node do fronty.
//     * @param node vlozeny Node do fronty
//     */
//    public void enqueue(Node node) {
//        queue[afterLast] = node;
//        afterLast = afterLast + 1;
//        int i = afterLast - 1;
//        while (i > 0 && queue[getParent(i)].compareTo( queue[i]) == 1) {
//            switchNodes(i, getParent(i));
//            i = getParent(i);
//        }
//    }
//
//    /**
//     *  REKURZIVNI metoda, ktera zajistuje vlastnost heapify pro prvek s pozici int.
//     *  @param position pozice Nodu, od ktereho se dela heapify
//     */
//    private void heapify(int position) {
//        int left = getLeftChildPosition(position);
//        int right = getRightChildPosition(position);
//        int max;
//        if (left <= afterLast - 1 && queue[left].compareTo(queue[position]) == -1) {
//            max = left;
//        } else {
//            max = position;
//        }
//        if (right <= afterLast - 1 && queue[right].compareTo(queue[max])==-1) {
//            max = right;
//        }
//        if (max != position) {
//            switchNodes(position, max);
//            heapify(max);
//        }
//    }
//
//    /**
//     *  Metoda, ktera odstranuje node s minimalni hodnotou (vzdalenosti) z fronty. Navratova hodnota je dany Node.
//     *  Metoda vyhazuje vyjimku, pokud je zavolana a fronta je prazdna.
//     * @return Node s nejnizsi hodnotou vzdalenosti
//     * @throws PriorityQueueEmptyException v pripade, ze Fronta je prazdna a metoda je zavolana se vyhodi tato vyjimka
//     */
//    public Node dequeue() throws PriorityQueueEmptyException {
//        if (isEmpty()) {
//            throw new PriorityQueueEmptyException();
//        }
//        Node throwAway = queue[0];
//        queue[0] = queue[afterLast - 1];
//        //queue[afterLast - 1]=null;
//        afterLast--;
//        heapify(0);
//        return throwAway;
//    }
//
//    /**
//     * Staticka metoda navracejici index rodicovskeho Nodu. V pripade, ze se dostane k Nodu s indexem 0, tak vrati -1;
//     * @param position pozice Nodu, jehoz rodic se ma vratit
//     * @return pozice rodicovskeho Nodu
//     */
//    private static int getParent(int position) {
//        if (position == 0) {
//            return -1;
//        }
//        return (position - 1) / 2;
//    }
//
//    /**
//     * Staticka metoda navracejici pozici leveho potomka Nodu udaneho pozici. Pro svoji funkci metoda nepotrebuje Node, jen jeho pozici.
//     * @param position pozice Nodu
//     * @return pozice leveho potomka
//     */
//    private static int getLeftChildPosition(int position) {
//        return 2 * position + 1;
//    }
//
//    /**
//     * Metoda navracejici pozici praveho potomka. Pro svoji funkci metoda nepotrebuje Node, jen jeho pozici.
//     * @param position pozice Nodu
//     * @return pozice praveho potomka
//     */
//    private static int getRightChildPosition(int position) {
//        return 2 * position + 2;
//    }
//
//    /**
//     *  Metoda overujici, jestli je prioritni fronta prazdna.
//     * @return hodnotu typu boolean, zda-li je prioritni fronta prazdna
//     */
//    public boolean isEmpty() {
//        if (afterLast == 0) {
//            return true;
//        }
//        return false;
//    }
////
////    /**
////     * Metoda aktualizujici stav prioritni fronty po zmene stavu uzlu. Postupuje se od uzlu k jeho predkum.
////     *
////     * @param node uzel, jehoz potomci splnuji vlastnost prioritni fronty
////     */
////    public void actualize(Node node) {
////        if (node.equals(null)) {
////            return;
////        }
////        int position = -1;
////        for (int i = 0; i < afterLast; i++) {
////            if (node.getName().equals(queue[i].getName())) {
////                position = i;
////                break;
////            }
////        }
////
////        while (position > 0) {
////            if (queue[position].compareTo(queue[getParent(position)]) == -1) {
////                switchNodes(position, getParent(position));
////            }
////            position = getParent(position);
////
////        }
////    }
//
//    /**
//     * Pomocna metoda, zamenujici jeden prvek za druhy. Pozice prvku dostava jako parametry.
//     * @param position1 pozice prvniho Nodu
//     * @param position2 pozice druheho Nodu
//     */
//    private void switchNodes(int position1, int position2) {
//        Node switched = queue[position1];
//        queue[position1] = queue[position2];
//        queue[position2] = switched;
//    }
//    
//    public void decreaseKeys(int value){
//    	for(int i = 0 ; i < afterLast ; i++){
//    		queue[i].edge.algorithmWeight -=value;
//    	}
//    }
//}
//
//class PriorityQueueEmptyException extends Exception{
//    /**
//     * Konstruktor, vypise zpravu o pokusu o vyprazdneni jiz prazdne prioritni fronty.
//     */
//    public PriorityQueueEmptyException (){
//            System.out.println("Trying to dequeue empty priority queue");
//    }
//}

//abstract class CNode<T> implements Comparable<T>{
//
//	@Override
//	public int compareTo(T o) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//	
//}