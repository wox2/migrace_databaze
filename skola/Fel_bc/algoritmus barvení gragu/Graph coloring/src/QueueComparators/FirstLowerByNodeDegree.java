/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package QueueComparators;

import Core.PriorityNode;
import Interfaces.PriorityNodeInterface;
import Interfaces.PriorityQueueComparatorInterface;

/**
 *
 * @author woxie
 */
public class FirstLowerByNodeDegree implements PriorityQueueComparatorInterface{

    @Override
    public boolean compareByConditions(PriorityNodeInterface priorityNode1, PriorityNodeInterface priorityNode2) {
        if(((PriorityNode)priorityNode1).getNodeDegree() < ((PriorityNode)priorityNode2).getNodeDegree() ) return true;
        return false;
    }

}
