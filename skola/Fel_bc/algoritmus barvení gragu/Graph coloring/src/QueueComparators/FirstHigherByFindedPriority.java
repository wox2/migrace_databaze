/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package QueueComparators;

import Interfaces.PriorityNodeInterface;
import Interfaces.PriorityQueueComparatorInterface;

/** Trida porovnava, jestli je priorita prvniho Nodu nizsi nez priorita druheho Node,
 *
 * @author woxie
 */
public class FirstHigherByFindedPriority implements PriorityQueueComparatorInterface{

    @Override
    public boolean compareByConditions(PriorityNodeInterface priorityNode1, PriorityNodeInterface priorityNode2) {
       if(priorityNode1.getValue() < priorityNode2.getValue() ) return true;
       return false;
    }

}
