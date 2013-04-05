/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Core;

import Interfaces.PriorityNodeInterface;

/**
 *
 * @author woxie
 */
public class PriorityNode implements PriorityNodeInterface{
private Node nodeVal;
private int priority;
private int nodeDegree;
    public PriorityNode(Node node){
        nodeVal = node;
        priority = 0;
        nodeDegree = 0;
    }
    
    @Override
    public int getValue() {
        return priority;
    }

    @Override
    public boolean equals(PriorityNodeInterface node) {
        if(!(node instanceof PriorityNode)) return false;
        return (this.nodeVal.getID() == ((PriorityNode)node).nodeVal.getID());
    }

    public void increasePriority(){
        priority++;
    }

    public void setPriority(int i){
        priority = i;
    }

    public int getId(){
        return nodeVal.getID();
    }

    public Node getNode(){
        return nodeVal;
    }

    public int getNodeDegree(){
        return nodeDegree;
    }

    public void setNodeDegree(int degree){
        nodeDegree = degree;
    }
}
