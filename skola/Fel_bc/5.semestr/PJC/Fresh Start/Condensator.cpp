/* 
 * File:   Condensator.cpp
 * Author: woxie
 * 
 * Created on 9. leden 2011, 17:54
 */

#include "Condensator.h"
#include <cstddef>
#include <iostream>

Condensator::Condensator(vector<StrongComponent *> * condensableComponents) {
    components = condensableComponents;
}

void Condensator::condensate(StrongComponent * component) {
    cout << "component:" << component->getRepresentant()->_id << endl;

    for (unsigned int i = 0; i < component->precedessors.size(); i++) {
        if (component->hasSuccessor(component->precedessors[i]) && component->precedessors[i] != component) {
            cout << component->precedessors[i]->getRepresentant()->_id << endl;

            StrongComponent * temp = component->precedessors[i];
            component->merge(temp);
            
            for (unsigned int j = 0; j < temp->precedessors.size(); j++) {
                temp->precedessors[j]->changeSuccessor(temp, component);
            }

            for (unsigned int j = 0; j < temp ->succesors.size(); j++) {
                temp->succesors[j]->changePrecedessor(temp, component);
            }

            removeFromComponentList(temp);
            delete temp;
            i--;
        }
    }
}

void Condensator::condensate() {
    unsigned int i = 0;
    while(i < components->size()){
        condensate((*components)[i]);
        i++;
    }
}

void Condensator::removeFromComponentList(StrongComponent * component) {
    vector<StrongComponent *>::iterator it;
    for (it = components->begin(); it < components->end(); it++) {
        if (*it == component) {
            break;
        }
    }
    components->erase(it);
    //cout << "cp.size " <<components->size() << endl;
}
