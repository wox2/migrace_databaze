/* 
 * File:   LowPriorityQueue.cpp
 * Author: woxie
 * 
 * Created on 9. leden 2011, 9:36
 */

#include "AbstractPriorityQueue.h"
#include <cstddef>

AbstractPriorityQueue::~AbstractPriorityQueue(){
}

StrongComponent * AbstractPriorityQueue::dequeue() {
    if (content.empty()) {
        return NULL;
    }
    StrongComponent * temp = content[0];
    content.erase(content.begin());
    return temp;
}

void AbstractPriorityQueue::enqueue(StrongComponent * component) {
    content.push_back(component);
    priorize();
}

AbstractPriorityQueue::AbstractPriorityQueue() {
    content = vector<StrongComponent *>();
}

int AbstractPriorityQueue::getComponentsPosition(StrongComponent * component) {
    unsigned int position = 0;
    while (position < content.size()) {
        if (content[position] == component) {
            return position;
        }
        ++position;
    }
    return -1;
}

void AbstractPriorityQueue::switchFields(int i, int j) {
    StrongComponent * temp = content[j];
    content[j] = content[i];
    content[i] = temp;
}

bool AbstractPriorityQueue::hasNext(StrongComponent* component) {
    unsigned int position = getComponentsPosition(component);
    if (position == content.size() - 1 || position == (unsigned int)-1) {
        return false;
    }
    return true;
}

StrongComponent * AbstractPriorityQueue::getNext(StrongComponent * component) {
    if (hasNext(component)) {
        return content[getComponentsPosition(component) + 1];
    }
    return NULL;
}

int AbstractPriorityQueue::getSize(){
    return content.size();
}
