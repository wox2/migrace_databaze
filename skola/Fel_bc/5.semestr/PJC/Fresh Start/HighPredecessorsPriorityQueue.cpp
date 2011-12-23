/* 
 * File:   HighPriorityQueue.cpp
 * Author: woxie
 * 
 * Created on 9. leden 2011, 9:37
 */

#include "HighPrecedessorsPriorityQueue.h"

HighPrecedessorsPriorityQueue::HighPrecedessorsPriorityQueue():AbstractPriorityQueue() {
}

/*HighPrecedessorsPriorityQueue::HighPrecedessorsPriorityQueue(const HighPrecedessorsPriorityQueue& orig) {
}*/

HighPrecedessorsPriorityQueue::~HighPrecedessorsPriorityQueue() {
}


void HighPrecedessorsPriorityQueue::actualizeAfterHigheringValue(StrongComponent* component){
    int position = getComponentsPosition(component);
  while(position != 0){
      if(content[position]->getPrecedessorCount()> content[ position - 1 ]->getPrecedessorCount()){
          switchFields(position,position - 1);
      } else{
          break;
      }
      --position;
  }
}

void HighPrecedessorsPriorityQueue::priorize(){
    actualizeAfterHigheringValue(content[content.size()- 1 ]);
}

